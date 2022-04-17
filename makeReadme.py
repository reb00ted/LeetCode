import os
import datetime
import time
import sys

import requests
from bs4 import BeautifulSoup

from selenium import webdriver
from selenium.webdriver.common.by import By


class Problem:
    def __init__(self, number, title, isPremium, difficulty, url):
        self.number = number
        self.title = title
        self.isPremium = isPremium
        self.difficulty = difficulty
        self.url = url
    

class Data:
    def __init__(self, problemNumber, problem, codeUrl):
        self.problemNumber = problemNumber
        self.problem = problem
        self.codeUrl = codeUrl
    
    @classmethod
    def parse(cls, line):
        none, number, title, difficulty, codeUrl, none = line.split('|')
        number = int(number)
        problemUrl = title[title.rfind('(') + 1:title.rfind(')')]
        isPremium = title[-1] == '🔒'
        title = title[title.find('[') + 1:title.find(']')]
        codeUrl = codeUrl[codeUrl.find('(') + 1:codeUrl.rfind(')')]

        return cls(number, Problem(number, title, isPremium, difficulty, problemUrl), codeUrl)
    
    def __eq__(self, other):
        if not isinstance(other, Data):
            return False
        return str(self) == str(other)
    
    def __repr__(self):
        return f'|{self.problem.number}|[{self.problem.title}]({self.problem.url}){" 🔒" if self.problem.isPremium else ""}|{self.problem.difficulty}|[Java]({self.codeUrl})|'
    
    def __str__(self):
        return f'|{self.problem.number}|[{self.problem.title}]({self.problem.url}){" 🔒" if self.problem.isPremium else ""}|{self.problem.difficulty}|[Java]({self.codeUrl})|'


class IncompleteData:
    def __init__(self, problemNumber, codeUrl):
        self.problemNumber = problemNumber
        self.problem = None
        self.codeUrl = codeUrl
    
    def setProblem(self, problem):
        self.problem = problem
    
    def build(self):
        if self.problem is None:
            print(f'{self.problemNumber} has not problem data')
            exit(0)
        
        return Data(self.problemNumber, self.problem, self.codeUrl)


class MakeReadme:
    def __init__(self):
        self.today = datetime.datetime.now().strftime('%Y-%m-%d')
        self.githubUrl = 'https://github.com'
        self.database = dict()
    
    def make(self, githubId, githubRepository):
        self.readLocalData(os.getcwd() + '\\data.txt')

        githubDirectoryUrlList = self.collectGithubRepositoryDirectoryURL('/'.join([self.githubUrl, githubId, githubRepository]))
        incompleteDataList = self.collectGithubRepositoryCode(githubDirectoryUrlList)
        dataList = self.collectLeetcodeProblemData(incompleteDataList)
        
        self.insert(dataList)
        self.updateLocalData()
        self.updateReadme()


    def readLocalData(self, path):
        print('readLocalData ...')
        if os.path.exists(path):
            file = open(path, 'r', encoding='utf-8')
            for line in file.readlines():
                data = Data.parse(line)
                self.database[data.problemNumber] = data


    def collectGithubRepositoryDirectoryURL(self, url):
        print('collectGihubDirectoryURL ...')
        directoryUrlList = list()
        resp = requests.get(url)
        if resp.status_code != 200:
            print(url, resp.status_code)
            exit()
        
        bs = BeautifulSoup(resp.text, 'html.parser')
        rows = bs.find_all(attrs={'role': 'row'})
        for row in rows[1:]:
            if row.find(attrs={'aria-label': 'Directory'}):
                directoryUrlList.append(self.githubUrl + row.find(attrs={'data-pjax': '#repo-content-pjax-container'})['href'])
        return directoryUrlList


    def collectGithubRepositoryCode(self, githubDirectoryUrlList):
        print('collectGihubRepositoryCode ...')
        incompleteDataList = []
        for githubDirectoryUrl in githubDirectoryUrlList:
            time.sleep(1)
            resp = requests.get(githubDirectoryUrl)
            if resp.status_code != 200:
                print(githubDirectoryUrl, resp.status_code)
                continue

            bs = BeautifulSoup(resp.text, 'html.parser')
            grid = bs.find(attrs={"role": "grid"})
            codeDatas = grid.find_all(attrs={"data-pjax": "#repo-content-pjax-container"})
            for codeData in codeDatas:
                codeUrl = self.githubUrl + codeData['href']
                htmlText = codeData.text
                
                problemNumber, none = htmlText.split(".", 1)
                problemNumber = int(problemNumber)
                if problemNumber not in self.database:
                    print(problemNumber)
                    incompleteDataList.append(IncompleteData(problemNumber, codeUrl))
        
        return incompleteDataList


    def collectLeetcodeProblemData(self, incompleteDataList):
        print('collectLeetcodeProblemData ...')
        driver = webdriver.Chrome()
        driver.get('https://leetcode.com/problemset/all/')
        premium = 'text-brand-orange'
        result = []

        for incompletData in incompleteDataList:
            textArea = driver.find_element(by=By.CSS_SELECTOR, value='input[type="text"]')
            textArea.clear()
            time.sleep(5)
            textArea = driver.find_element(by=By.CSS_SELECTOR, value='input[type="text"]')
            textArea.send_keys(incompletData.problemNumber)
            time.sleep(5)

            row = driver.find_elements(by=By.CSS_SELECTOR, value='div[role="row"]')[2]
            innerHTML = row.get_attribute('innerHTML')
            difficulty = ''
            if innerHTML.find('Easy') > -1:
                difficulty = 'Easy'
            elif innerHTML.find('Medium') > -1:
                difficulty = 'Medium'
            elif innerHTML.find('Hard') > -1:
                difficulty = 'Hard'
            else:
                difficulty = '몰?루'
            
            isPremium = False
            if innerHTML.find(premium) > -1:
                isPremium = True
            
            problemUrl = row.find_element(by=By.TAG_NAME, value='a').get_attribute('href')
            title = row.find_element(by=By.TAG_NAME, value='a').get_attribute('innerHTML')
            title = title.split('.', 1)[1].strip()

            incompletData.setProblem(Problem(incompletData.problemNumber, title, isPremium, difficulty, problemUrl))
            result.append(incompletData.build())

        driver.quit()
        return result
    

    def insert(self, dataList):
        for data in dataList:
            self.database[data.problemNumber] = data


    def updateLocalData(self):
        print('updateLocalData ...')
        file = open('data_' + self.today + '.txt', 'w', encoding='utf-8')
        for problemNumber in sorted(self.database):
            file.write(str(self.database[problemNumber]))
            file.write('\n')
        
        file.close()
        return
    
    def updateReadme(self):
        print('updateReadme ...')
        baseTemplate = open('baseTemplate.md', 'r', encoding='utf-8')
        result = open('README_' + self.today + '.md', 'w', encoding='utf-8')

        for line in baseTemplate.readlines():
            result.write(line)
        
        for problemNumber in sorted(self.database):
            result.write(str(self.database[problemNumber]))
            result.write('\n')
        
        baseTemplate.close()
        result.close()


if __name__ == '__main__':
    if len(sys.argv) != 3:
        print('Usage: py makeReadme.py [githubId] [githubRepositoryName]')
        exit(0)
    
    githubId = sys.argv[1]
    githubRepository = sys.argv[2]
    MakeReadme().make(githubId, githubRepository)