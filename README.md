
# STIW3034 Web Testing Project 

## Overview

This project involves performing functional testing on a web application using Selenium Web Driver and JUnit. The testing is divided into different roles: Admin, Author, and Reviewer. Each group member will create test scripts, code test cases, apply assertions, and compile test results. The following system will be tested:

- **Landing Page**: [https://sandbox.soc-conferences.com/](https://sandbox.soc-conferences.com/)
- **Admin Login**: [https://sandbox.soc-conferences.com/admin/login.php](https://sandbox.soc-conferences.com/admin/login.php)
  - **Login**: sandbox@soc-conferences.com
  - **Password**: Abc123
- **Author Login**: [https://sandbox.soc-conferences.com/author/login.php](https://sandbox.soc-conferences.com/author/login.php)
  - **Login**: slumberjer@gmail.com
  - **Password**: Abc123
- **Reviewer Login**: [https://sandbox.soc-conferences.com/reviewer/](https://sandbox.soc-conferences.com/reviewer/)

## Project Requirements

### 1. Test Scripts
- Each group member must write a minimum of 5 test cases.
- Roles covered: Admin, Author, Reviewer.
- Do not change the Admin password.
- Account creation and forgot password functionality do not need to be tested.

### 2. Test Cases
- **Code the test cases using Java, Selenium Web Driver, and JUnit.**
- **Apply Assert in ALL test cases.**
- **Use JUnit TestSuites and @Test annotations.**
- **Add comments in ALL test cases to improve readability.**
- **Standardize test case names among group members. Use Testcase ID and meaningful names, consistent with the test script.**
- **Compile all group members' test cases using @RunWith(Suite.class) and/or @SuiteClasses annotations.**
- **Compile all test results.**

## Project Structure

### Test Cases
The test cases are divided into different classes based on roles:

#### Admin Role
- **TC1_286623**:
  - `CMS_01 Add Field`: Tests adding field settings.
  - `CMS_02 Add news`: Tests adding new news.
  - `CMS_03.01 Assign Reviewer (Name)`: Tests assigning reviewers by name.
  - `CMS_03.02 Assign Reviewer (Organization)`: Tests assigning reviewers by organization.
  - `CMS_04.01 Update journal status (Accepted)`: Tests updating the journal status to "Accepted".

- **TC2_285239**:
  - `CMS_04.02 Update journal status (Camera Ready)`: Tests updating journal status to "Camera Ready".
  - `CMS_05 Publish Journal`: Tests managing journal publishing details.
  - `CMS_06 Update Payment Status`: Tests updating payment status.
  - `CMS_07 Change Conference Title`: Tests changing the conference title.
  - `CMS_08 Verify Reviewer CV`: Tests verifying reviewer CVs.

#### Author Role
- **TC3_287471**:
  - `CMS_09 Submit Paper`: Tests paper submission.
  - `CMS_10 Edit Submission`: Tests editing submission.
  - `CMS_11 Upload Camera Ready Form`: Tests uploading camera-ready forms.
  - `CMS_12 Update Payment Details`: Tests updating payment details.
  - `CMS_13 Add Co-Authors`: Tests adding co-authors.

#### Reviewer Role
- **TC4_293153**:
  - `CMS_15 Decline Review Paper`: Tests declining review papers.
  - `CMS_16 Submit Review Paper`: Tests submitting review papers.
  - `CMS_17 Create New Message`: Tests creating new messages.
  - `CMS_18 Delete Message`: Tests deleting messages.

### Test Suite
All test cases are compiled into a test suite:

```java
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TC1_286623.class,
        TC2_285239.class,
        TC3_287471.class,
        TC4_293153.class
})
public class TestSuite {
}
```

## How to Run the Tests

1. **Setup Selenium Web Driver**:
   - Download and configure the Selenium Web Driver (ChromeDriver recommended).
   - Ensure the WebDriver executable is in your system PATH or specify its location in your code.

2. **Clone the Project**:
   - Clone the repository or download the project files.

3. **Open the Project**:
   - Open the project in your preferred IDE (e.g., IntelliJ).

4. **Run the Test Suite**:
   - Execute the `TestSuite` class to run all test cases.

5. **Review Test Results**:
   - Review the results in the JUnit test runner.

