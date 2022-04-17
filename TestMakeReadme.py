import unittest
import time

from makeReadme import MakeReadme, Data, IncompleteData

class TestMakeReadme(unittest.TestCase):
    # Data 직렬화, 역직렬화 검증
    def test_data_serialize_deserialize(self):
        # given
        row = '|2147|[Number of Ways to Divide a Long Corridor](https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor)|Hard|[Java](https://github.com/reb00ted/LeetCode/blob/main/Math/2147.%20Number%20of%20Ways%20to%20Divide%20a%20Long%20Corridor.java)|'

        # when
        data = Data.parse(row)

        # then
        self.assertEqual(row, str(data))
    

    # Data.parse 검증
    def test_data_parse(self):
        # given
        row = '|310|[Minimum Height Trees](https://leetcode.com/problems/minimum-height-trees)|Medium|[Java](dummyUrl)|'

        # when
        data = Data.parse(row)

        # then
        self.assertEqual(data.problemNumber, 310)
        self.assertEqual(data.problem.title, 'Minimum Height Trees')
        self.assertEqual(data.problem.url, 'https://leetcode.com/problems/minimum-height-trees')
        self.assertEqual(data.problem.isPremium, False)
        self.assertEqual(data.problem.difficulty, 'Medium')
        self.assertEqual(data.codeUrl, 'dummyUrl')


    # Data.parse 검증2
    def test_data_parse2(self):
        # given
        row = '|568|[Maximum Vacation Days](https://leetcode.com/problems/maximum-vacation-days) 🔒|Hard|[Java](dummyUrl)|'

        # when
        data = Data.parse(row)

        # then
        self.assertEqual(data.problemNumber, 568)
        self.assertEqual(data.problem.title, 'Maximum Vacation Days')
        self.assertEqual(data.problem.url, 'https://leetcode.com/problems/maximum-vacation-days')
        self.assertEqual(data.problem.isPremium, True)
        self.assertEqual(data.problem.difficulty, 'Hard')
        self.assertEqual(data.codeUrl, 'dummyUrl')


    # github scraping 검증(카테고리 개수)
    def test_github_scraping(self):
        # given
        makeReadme = MakeReadme()
        url = 'https://github.com/reb00ted/LeetCode/'

        # when
        time.sleep(1)
        result = makeReadme.collectGithubRepositoryDirectoryURL(url)

        # then
        self.assertEqual(len(result), 28)


    # github scraping 검증('Design' 카테고리의 솔루션 개수)
    def test_github_scraping2(self):
        # given
        makeReadme = MakeReadme()
        directoryUrlList = ['https://github.com/reb00ted/LeetCode/tree/main/Design']

        # when
        time.sleep(1)
        incompleteDataList = makeReadme.collectGithubRepositoryCode(directoryUrlList)

        # then
        self.assertEqual(len(incompleteDataList), 4)

    
    # github scraping 검증('Enumeration' 카테고리 스크레이핑 결과 비교)
    def test_github_scraping3(self):
        # given
        makeReadme = MakeReadme()
        directoryUrlList = ['https://github.com/reb00ted/LeetCode/tree/main/Enumeration']

        # when
        time.sleep(1)
        result = makeReadme.collectGithubRepositoryCode(directoryUrlList)
        incompleteData = result[0]

        # then
        self.assertEqual(incompleteData.problemNumber, 1291)
        self.assertEqual(incompleteData.codeUrl, 'https://github.com/reb00ted/LeetCode/blob/main/Enumeration/1291.%20Sequential%20Digits.java')


    # LeetCode scraping 검증(310번 문제 스크레이핑 결과 비교)
    def test_leetcode_scraping(self):
        # given
        makeReadme = MakeReadme()
        data = Data.parse('|310|[Minimum Height Trees](https://leetcode.com/problems/minimum-height-trees){:target="_blank"}|Medium|[Java](dummyUrl)|')
        incompleteDataList = [IncompleteData(310, 'dummyUrl')]

        # when
        result = makeReadme.collectLeetcodeProblemData(incompleteDataList)[0]
        
        # then
        self.assertEqual(data, result)



if __name__ == '__main__':
    unittest.main()