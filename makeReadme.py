import os
import datetime
import time
import sys

import requests
from bs4 import BeautifulSoup

from selenium import webdriver
from selenium.webdriver.common.by import By


githubUrl = 'https://github.com'
githubId = ''
githubRepository = ''


class Solution:
    def __init__(self, problemNumber, problemTitle, difficulty, solution):
        self.problemNumber = problemNumber
        self.problemTitle = problemTitle
        self.difficulty = difficulty
        self.solution = solution
    
    def __str__(self):
        return f'|{self.problemNumber}|{self.problemTitle}|{self.difficulty}|{self.solution}|'


class Code:
    def __init__(self, problemNumber, problemTitle, solutionUrl):
        self.problemNumber = problemNumber
        self.problemTitle = problemTitle
        self.isPremium = False
        self.difficulty = None
        self.problemUrl = None
        self.solutionUrl = solutionUrl
    
    def setDifficulty(self, difficulty):
        self.difficulty = difficulty
    
    def setProblemUrl(self, problemUrl):
        self.problemUrl = problemUrl

    def setPremium(self, isPremium):
        self.isPremium = isPremium
    
    def toSolution(self):
        problemTitle = f'[{self.problemTitle}]({self.problemUrl})'
        if self.isPremium:
            problemTitle += ' 🔒'
        solution = f'[Java]({self.solutionUrl})'
        return Solution(self.problemNumber, problemTitle, self.difficulty, solution)


class MakeReadme:
    def __init__(self):
        self.now = datetime.datetime.now().strftime('%Y-%m-%d')
        self.directory = os.getcwd()
        self.solutionList = dict()
        self.codeList = list()
    
    def make(self):
        self.readExistingSolutionList()
        self.collectGithubRepositoryCode()
        self.collectLeetcodeProblemData()
        self.updateSolutionList()
        self.updateReadme()


    def readExistingSolutionList(self):
        filePath = self.directory + '\\data.txt'

        if os.path.exists(filePath):
            file = open(filePath, 'r', encoding='utf-8')
            for line in file.readlines():
                none, problemNumber, problemTitle, difficulty, solution, none = line.split('|')
                print(problemNumber, problemTitle, difficulty, solution)
                self.solutionList[int(problemNumber)] = Solution(problemNumber, problemTitle, difficulty, solution)
        return


    def collectGithubRepositoryCode(self):
        rootURL = '/'.join([githubUrl, githubId, githubRepository])
        directoryUrlList = self.collectGithubRepositoryDirectoryURL(rootURL)

        for directoryUrl in directoryUrlList:
            time.sleep(1)
            resp = requests.get(directoryUrl)
            if resp.status_code != 200:
                print(directoryUrl, resp.status_code)
                continue

            bs = BeautifulSoup(resp.text, 'html.parser')
            grid = bs.find(attrs={"role": "grid"})
            codes = grid.find_all(attrs={"data-pjax": "#repo-content-pjax-container"})
            for code in codes:
                solutionUrl = githubUrl + code['href']
                htmlText = code.text
                
                problemNumber, problemTitle = htmlText.split(".", 1)
                problemNumber = int(problemNumber)
                problemTitle = problemTitle.split(".")[0].strip()
                if not problemNumber in self.solutionList:
                    self.codeList.append(Code(problemNumber, problemTitle, solutionUrl))


    def collectGithubRepositoryDirectoryURL(self, rootUrl):
        directoryUrlList = list()
        resp = requests.get(rootUrl)
        if resp.status_code != 200:
            print(rootUrl, resp.status_code)
            exit()
        
        bs = BeautifulSoup(resp.text, 'html.parser')
        rows = bs.find_all(attrs={'role': 'row'})
        for row in rows[1:]:
            if row.find(attrs={'aria-label': 'Directory'}):
                directoryUrlList.append(githubUrl + row.find(attrs={'data-pjax': '#repo-content-pjax-container'})['href'])
        
        return directoryUrlList


    def collectLeetcodeProblemData(self):
        driver = webdriver.Chrome()
        driver.get('https://leetcode.com/problemset/all/')
        premium = 'text-brand-orange'

        for code in self.codeList:
            textArea = driver.find_element(by=By.CSS_SELECTOR, value='input[type="text"]')
            textArea.clear()
            time.sleep(5)
            textArea = driver.find_element(by=By.CSS_SELECTOR, value='input[type="text"]')
            textArea.send_keys(code.problemNumber)
            time.sleep(5)

            row = driver.find_elements(by=By.CSS_SELECTOR, value='div[role="row"]')[2]
            innerHTML = row.get_attribute('innerHTML')
            if innerHTML.find('Easy') >= 0:
                code.setDifficulty('Easy')
            elif innerHTML.find('Medium') >= 0:
                code.setDifficulty('Medium')
            elif innerHTML.find('Hard') >= 0:
                code.setDifficulty('Hard')
            else:
                code.setDifficulty('몰?루')
            
            if innerHTML.find(premium) >= 0:
                code.setPremium(True)
            
            code.setProblemUrl(row.find_element(by=By.TAG_NAME, value='a').get_attribute('href'))

        driver.quit()


    def updateSolutionList(self):
        for code in self.codeList:
            self.solutionList[code.problemNumber] = code.toSolution()

        file = open('data_' + self.now + '.txt', 'w', encoding='utf-8')
        for problemNumber in sorted(self.solutionList):
            file.write(str(self.solutionList[problemNumber]))
            file.write('\n')
        
        file.close()
        return
    
    def updateReadme(self):
        baseTemplate = open('baseTemplate.md', 'r', encoding='utf-8')
        result = open('README_' + self.now + '.md', 'w', encoding='utf-8')

        for line in baseTemplate.readlines():
            result.write(line)
        
        for problemNumber in sorted(self.solutionList):
            result.write(str(self.solutionList[problemNumber]))
            result.write('\n')
        
        baseTemplate.close()
        result.close()


if __name__ == '__main__':
    if len(sys.argv) != 3:
        print('Usage: py makeReadme.py [githubId] [githubRepositoryName]')
        exit(0)
    
    githubId = sys.argv[1]
    githubRepository = sys.argv[2]
    MakeReadme().make()