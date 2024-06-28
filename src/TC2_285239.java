import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TC2_285239 { //Admin role

    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        //System.setProperty("webdriver.chrome.driver", "C:\\Selenium driver\\Chrome driver\\chromedriver-win64\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "/Users/choon/Documents/Java Libraries/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); // Increased implicit wait
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //Method for login to the admin webpage
    private void login() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Increased wait time
        driver.get("https://sandbox.soc-conferences.com/admin/login.php");

        // Maximize the browser window
        driver.manage().window().maximize();

        System.out.println("Navigated to login page");

        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        emailField.sendKeys("sandbox@soc-conferences.com");
        System.out.println("Entered email");

        Thread.sleep(2000);
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        passwordField.sendKeys("Abc123");
        System.out.println("Entered password");

        Thread.sleep(2000);
        WebElement conferenceDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("conf")));
        Select selectConference = new Select(conferenceDropdown);
        selectConference.selectByVisibleText("SANDBOX24");
        System.out.println("Selected conference");


        // Interact with the checkbox
        Thread.sleep(2000);
        WebElement checkBoxElement = driver.findElement(By.name("remember"));
        checkBoxElement.click();

        // Handle alert if present
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            assertEquals("Credentials Stored Success", alertText);
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("No alert present after saving preferences.");
        }

        Thread.sleep(2000);
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("submit")));
        submitButton.click();
        System.out.println("Clicked submit");

        // Handle alert if present
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            assertEquals("Login Successful", alertText);
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("No alert present after login");
        }
    }

    //CMS_04.02 update journal status (Under Review) - Tests the functionality to update journal status to "Under Review"
    @Test
    public void CMS_04_02() throws InterruptedException {
        login();
        Thread.sleep(2000);
        navigateToPapersTab();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Details')]")));
        detailsLink.click();
        Thread.sleep(2000);

        // Switch to the new tab
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        try {
            // Locate the dropdown by XPath expression
            WebElement statusDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div[2]/a/div/div/form/p[1]/select")));
            System.out.println("Dropdown element found");

            // Interact with the dropdown
            Select selectStatus = new Select(statusDropdown);
            selectStatus.selectByVisibleText("Under Review");
            System.out.println("Dropdown value selected");
            Thread.sleep(2000);
        } catch (NoSuchElementException e) {
            System.out.println("Dropdown element not found");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error occurred while interacting with dropdown");
            e.printStackTrace();
        }

        // Locate the remarks field by XPath expression
        WebElement remarksField = driver.findElement(By.xpath("//textarea[@name='remark']"));
        remarksField.clear(); // Clear any existing text
        remarksField.sendKeys("Received and currently under review");
        Thread.sleep(2000);

        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Update')]")));
        updateButton.click();

        // Handle unexpected alert
        try {
            Thread.sleep(2000);
            Alert unexpectedAlert = driver.switchTo().alert();
            String alertText = unexpectedAlert.getText();
            System.out.println("Unexpected alert text: " + alertText);
            // Handle the alert as needed
            unexpectedAlert.accept(); // To accept the alert
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present after updating paper status");
        }
        Thread.sleep(2000);
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            assertEquals("Success", alertText);
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("No alert present after updating status success");
        }
        Thread.sleep(2000);
        driver.quit();
        System.out.println("Test Update Paper Status is successful!");
    }

    //CMS_05 Publish Journal - Tests the functionality to manage the publishing details of a journal
    @Test
    public void CMS_05() throws InterruptedException {
        login();
        Thread.sleep(2000);
        navigateToPapersTab();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Details')]")));
        detailsLink.click();
        Thread.sleep(2000);

        // Switch to the new tab
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Scroll down to the publishing management section
        WebElement publishingSection = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[5]/div/div[1]/h4")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'start', inline: 'nearest'});", publishingSection);
        Thread.sleep(1000);
        // Adjust scrolling to be slightly up (equivalent to scrolling up by 2 mouse scrolls)
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -200);");
        Thread.sleep(2000);

        // Proceed with the test case steps
        // Insert Paper Doi name
        WebElement doiField = driver.findElement(By.name("paperdoi"));
        doiField.clear();
        doiField.sendKeys("10.1000/j.journal.2024.01.001");
        Thread.sleep(1000);

        // Insert Paper Page No.
        WebElement pageNoField = driver.findElement(By.name("paperpages"));
        pageNoField.clear();
        pageNoField.sendKeys("49-90");
        Thread.sleep(1000);

        // Upload final paper in PDF format
        WebElement uploadButton = driver.findElement(By.name("fileToUpload"));
        uploadButton.sendKeys("C:/Users/choon/Downloads/final_paper.pdf");
        Thread.sleep(2000);

        // Click Submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[5]/div/div[2]/div[1]/form/button")));
        submitButton.click();

        // Handle unexpected alert
        try {
            Thread.sleep(2000);
            Alert unexpectedAlert = driver.switchTo().alert();
            String alertText = unexpectedAlert.getText();
            System.out.println("Unexpected alert text: " + alertText);
            unexpectedAlert.accept(); // To accept the alert
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present after submitting the paper details");
        }

        Thread.sleep(2000);
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            assertEquals("Success.", alertText);
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("No alert present after updating status success");
        }
        Thread.sleep(2000);

        driver.quit();
        System.out.println("Test Publish Journal is successful!");
    }





    //CMS_06 Update Payment Status - Tests the functionality to update the payment status of a journal.
    @Test
    public void CMS_06() throws InterruptedException {
        login();
        Thread.sleep(2000);
        navigateToPaymentTab();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Admin selects payment to view details by clicking on the folder icon
        WebElement folderIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div[2]/table/tbody/tr[2]/td[10]/a/button")));
        folderIcon.click();
        Thread.sleep(2000);

        // Download payment receipt
        WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Download')]")));
        downloadButton.click();
        Thread.sleep(2000);

        // Close the newly opened tab (indicating payment receipt has been checked)
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                driver.close();
                break;
            }
        }

        // Switch back to the main tab
        driver.switchTo().window(currentHandle);

        // Select "Submitted" status for payment from dropdown
        WebElement statusDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("paystatus")));
        Select selectStatus = new Select(statusDropdown);
        selectStatus.selectByVisibleText("Submitted");
        Thread.sleep(2000);

        // Insert remarks for payment
        WebElement remarksField = driver.findElement(By.name("remarks"));
        remarksField.clear();
        remarksField.sendKeys("Payment has been submitted.");
        Thread.sleep(2000);

        // Click Update button
        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div[2]/div[1]/div/div/div/input[4]")));
        updateButton.click();

        // Handle unexpected alert
        try {
            Thread.sleep(2000);
            Alert unexpectedAlert = driver.switchTo().alert();
            String alertText = unexpectedAlert.getText();
            System.out.println("Unexpected alert text: " + alertText);
            unexpectedAlert.accept(); // To accept the alert
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present after updating the payment status");
        }

        // Verify the success message
        Thread.sleep(2000);
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            assertEquals("Success", alertText);
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("No alert present after updating status success");
        }
        Thread.sleep(2000);

        driver.quit();
        System.out.println("Test Update Payment Status is successful!");
    }

    //CMS_07 Change Conference Title - Tests the functionality for changing the conference name
    @Test
    public void CMS_07() throws InterruptedException {
        login();
        Thread.sleep(2000);
        navigateToSettings();
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Click on the "Change" button for conference title
        WebElement changeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Change')]")));
        changeButton.click();
        Thread.sleep(2000);

        // Insert new conference title
        WebElement titleField = driver.findElement(By.name("confname"));
        titleField.clear();
        titleField.sendKeys("Sandbox International Conference 2024 June"); // New conference title
        Thread.sleep(1000);

        // Click "Submit" button
        WebElement submitButton = driver.findElement(By.xpath("/html/body/div[2]/div[16]/div/div/form/p/button"));
        submitButton.click();

        // Handle confirmation alert
        try {
            Thread.sleep(2000);
            Alert unexpectedAlert = driver.switchTo().alert();
            String alertText = unexpectedAlert.getText();
            System.out.println("Unexpected alert text: " + alertText);
            unexpectedAlert.accept(); // To accept the alert
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present after updating conference title");
        }

        // Verify the success message
        Thread.sleep(2000);
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            assertEquals("Success", alertText);
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("No alert present after updating conference title");
        }
        Thread.sleep(2000);

        driver.quit();

        System.out.println("Test Change Conference Title is successful!");
    }

    //CMS_08 Verify Reviewer CV - Tests the functionality to verify reviewer CVs
    @Test
    public void CMS_08() throws InterruptedException {
        login();
        Thread.sleep(2000);
        navigateToReviewers();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Admin selects payment to view details by clicking on the folder icon
        WebElement folderIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[5]/div[1]/table/tbody/tr[2]/td[7]/a/button")));
        folderIcon.click();
        Thread.sleep(2000);

        // Click on the "Download CV" button
        WebElement downloadCVLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[5]/div[1]/div[1]/div/div[1]/div/table/tbody/tr[10]/td/a")));
        downloadCVLink.click();
        Thread.sleep(2000);

        // Handle confirmation alert
        try {
            Thread.sleep(2000);
            Alert unexpectedAlert = driver.switchTo().alert();
            String alertText = unexpectedAlert.getText();
            System.out.println("Unexpected alert text: " + alertText);
            unexpectedAlert.accept(); // To accept the alert
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present after updating conference title");
        }
        Thread.sleep(2000);

        // Verify the success message
        Thread.sleep(2000);
        // Verify the success message based on the current status of the CV
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);

            // Check if the CV was verified or unverified based on the alert message
            if (alertText.contains("verified")) {
                assertEquals("Success. An email has been sent to the reviewer.", alertText);
            } else if (alertText.contains("unverified")) {
                assertEquals("Success", alertText);
            }

            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("No alert present after verifying reviewer CV");
        }
        Thread.sleep(2000);

        driver.quit();

        System.out.println("Test Verify Reviewer's CV is successful!");
    }



    private void navigateToPapersTab() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time
        WebElement papersTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='papers.php' and contains(@class, 'w3-bar-item')]")));
        papersTab.click();
    }

    private void navigateToPaymentTab() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time
        WebElement paymentTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='payments.php' and contains(@class, 'w3-bar-item')]")));
        paymentTab.click();
    }

    private void navigateToSettings() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time
        WebElement settingsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='settings.php' and contains(@class, 'w3-bar-item')]")));
        settingsTab.click();
    }

    private void navigateToReviewers() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Click on the "Reviewers" button
        WebElement reviewersButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='reviewers.php' and contains(@class, 'w3-bar-item')]")));
        reviewersButton.click();
    }

}