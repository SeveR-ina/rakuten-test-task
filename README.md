## Essentials:

UI Automation tests of https://rakuten.co.uk/ with using:
Java 11 + Selenium + TestNG + PageObject + Lombok + Maven + Allure report.

### The solution is in 2 branches:

- 'master': 1 automated Test case from 'Coding Exercise';
- 'master-plus-additional-task': 3 automated Test cases from both 'Coding Exercise' and 'Non-Coding Exercise'.

#### Test classes :

- MyAccountTest.class.

#### Test methods :

- checkPersonalInfoUrlAndAccountPointsAfterRegistration();
- checkFirstAndLastNameAfterRegistration();
- checkFullNameAfterRegistration().

#### Linear tests :

- linearTests.xml;

#### Parallel tests :

- parallelTests.xml.

--------------------------------------

Allure report allows seeing the manual test cases (& steps) as a solution for the 'Non-Coding Exercise'. 
We should run all automated tests via Maven in the 'master-plus-additional-task' branch. For Allure instructions check ALLURE.md file.
