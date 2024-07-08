import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TC3_287471.CMS_09_SubmitPaper.class,
        TC3_287471.CMS_10_EditSubmission.class,
        TC3_287471.CMS_11_UploadCameraReadyForm.class,
        TC3_287471.CMS_12_UpdatePaymentDetails.class,
        TC3_287471.CMS_13_AddCoAuthors.class
})
public class TC3_287471 {

    public static WebDriver driver;

    @BeforeClass
    public static void setUp() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "C:/Users/guanx/OneDrive/Documents/chromedriver-win64/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "/Users/choon/Documents/Java Libraries/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://sandbox.soc-conferences.com/author/login.php");
        login();
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void login() throws InterruptedException {
        driver.findElement(By.id("idemail")).sendKeys("guanxunmy405@gmail.com");
        Thread.sleep(1000);

        driver.findElement(By.id("idpass")).sendKeys("Abc123456");
        Thread.sleep(1000);

        WebElement rememberMeCheckbox = driver.findElement(By.id("idremember"));
        if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
            Thread.sleep(1000);

            // Handle the save preference alert
            Alert prefAlert = driver.switchTo().alert();
            String prefAlertText = prefAlert.getText();
            assertEquals("Credentials Stored Success", prefAlertText);
            prefAlert.accept();
        }

        Thread.sleep(1000);

        driver.findElement(By.name("submit")).click();
        Thread.sleep(1000);

        Alert loginAlert = driver.switchTo().alert();
        String loginAlertText = loginAlert.getText();
        assertEquals("Login Successful", loginAlertText);
        loginAlert.accept();
    }

    public static class CMS_09_SubmitPaper {
        @Test
        public void testSubmitPaper() throws InterruptedException {
            // Navigate to Papers section
            driver.findElement(By.linkText("Papers")).click();
            Thread.sleep(1000);

            // Click on the Submit Paper button
            driver.findElement(By.cssSelector(".w3-bar-item.w3-button.w3-cyan.w3-right.w3-margin.w3-round")).click();
            Thread.sleep(1000);

            // Select the first journal from the list
            driver.findElement(By.cssSelector("#selectConfList .w3-ul .w3-bar a")).click();
            Thread.sleep(1000);

            // Select the paper field
            driver.findElement(By.id("idfield")).click();
            Thread.sleep(1000);

            // Select the first checkbox in the modal
            driver.findElement(By.id("selectid1")).click();
            Thread.sleep(1000);

            // Click the select fields button in the modal
            driver.findElement(By.cssSelector("#idsubmitfields > div > div > button")).click();
            Thread.sleep(1000);

            // Fill in the paper submission form
            driver.findElement(By.id("idtitle")).sendKeys("New Paper Title");
            Thread.sleep(1000);

            driver.findElement(By.id("idabstract")).sendKeys("Abstract of the paper in 350 words");
            Thread.sleep(1000);

            driver.findElement(By.id("idkeyword")).sendKeys("Relevant Paper Keywords");
            Thread.sleep(1000);

            // Upload the files
            driver.findElement(By.id("idfilea")).sendKeys("C:/Users/choon/Downloads/Software Testing (with aff).docx");
            driver.findElement(By.id("idfileb")).sendKeys("C:/Users/choon/Downloads/Software Testing (no aff).docx");

            // Submit the form
            driver.findElement(By.id("idsubmit")).click();
            Thread.sleep(1000);

            // Handle the confirmation alert
            Alert confirmationAlert = driver.switchTo().alert();
            confirmationAlert.accept();
            confirmationAlert.accept();
            Thread.sleep(1000);



        }
    }

    public static class CMS_10_EditSubmission {
        @Test
        public void testEditSubmission() throws InterruptedException {
            // Navigate to Papers section
            driver.findElement(By.linkText("Papers")).click();
            Thread.sleep(1000);

            // Click on the Details button for "New Paper Title"
            driver.findElement(By.xpath("//td[text()='New Paper Title']/following-sibling::td/a/button")).click();
            Thread.sleep(1000);

            // Verify if we are on the correct details page
            assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Paper Details')]")).isDisplayed());

            // Click on the Edit Submission button
            driver.findElement(By.linkText("Edit Submission")).click();
            Thread.sleep(1000);

            // Verify if we are on the edit submission page
            assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Update Paper Form')]")).isDisplayed());

            // Perform edit actions
            WebElement titleField = driver.findElement(By.id("idtitle"));
            titleField.clear();
            titleField.sendKeys("Updated Paper Title");
            Thread.sleep(1000);

            WebElement abstractField = driver.findElement(By.id("idabstract"));
            abstractField.clear();
            abstractField.sendKeys("Updated abstract of the paper");
            Thread.sleep(1000);

            WebElement keywordField = driver.findElement(By.id("idkeyword"));
            keywordField.clear();
            keywordField.sendKeys("Updated Keywords");
            Thread.sleep(1000);

            // Click the Update button
            driver.findElement(By.id("idsubmit")).click();
            Thread.sleep(1000);

            // Handle the confirmation alert
            Alert confirmationAlert = driver.switchTo().alert();
            confirmationAlert.accept();
            confirmationAlert.accept();
            Thread.sleep(1000);


            // Close the current browser window
            driver.quit();

            // Open new browser window for admin login and update paper status
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Navigate to the admin login page
            driver.get("https://sandbox.soc-conferences.com/admin/login.php");

            // Perform admin login
            WebElement emailField = driver.findElement(By.xpath("//input[@name='email']"));
            emailField.sendKeys("sandbox@soc-conferences.com");
            Thread.sleep(1000);

            WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
            passwordField.sendKeys("Abc123");
            Thread.sleep(1000);

            Select conferenceDropdown = new Select(driver.findElement(By.xpath("//select[@name='conf']")));
            conferenceDropdown.selectByValue("SANDBOX24");
            Thread.sleep(1000);

            WebElement rememberCheckbox = driver.findElement(By.xpath("//input[@name='remember']"));
            if (!rememberCheckbox.isSelected()) {
                rememberCheckbox.click();
            }
            Thread.sleep(1000);

            // Handle the save preference alert
            Alert prefAlert = driver.switchTo().alert();
            String prefAlertText = prefAlert.getText();
            assertEquals("Credentials Stored Success", prefAlertText);
            prefAlert.accept();
            Thread.sleep(1000);

            WebElement loginButton = driver.findElement(By.xpath("//button[@name='submit']"));
            loginButton.click();

            // Handle the login alert
            Alert loginAlert = driver.switchTo().alert();
            String loginAlertText = loginAlert.getText();
            assertEquals("Login Successful", loginAlertText);
            loginAlert.accept();

            // Wait for the login to complete
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.titleContains("SANDBOX24 - Admin"));
            String title = driver.getTitle();
            assertEquals("SANDBOX24 - Admin", title);

            // Perform the function to change the paper status to accepted
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            driver.findElement(By.xpath("//a[@href='papers.php' and contains(@class, 'w3-bar-item')]")).click();
            Thread.sleep(2000);

            //Enter Author's name 'Tan Guan Xun'
            WebElement searchAuthor = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/form/div[1]/input"));
            wait.until(ExpectedConditions.elementToBeClickable(searchAuthor)).sendKeys("Tan Guan Xun");

            Thread.sleep(2000);

            // Click the "Search" button
            WebElement searchButton = driver.findElement(By.xpath("        /html/body/div[2]/div[2]/div/form/div[3]/button\n"));
            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

            Thread.sleep(2000);

            //Click on "details"
            WebElement detailsTextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='Submitted']/following-sibling::td/a[contains(text(),'Details')]")));
            detailsTextButton.click();

            // Sleep for 3 seconds
            Thread.sleep(2000);

            // Switch to the new tab
            String currentHandle = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(currentHandle)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }

            // Sleep for 1 second
            Thread.sleep(1000);

            // Select "Accepted" from the drop-down button
            WebElement selectStatus = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div[2]/a/div/div/form/p[1]/select")));
            Select dropdown = new Select(selectStatus);
            dropdown.selectByVisibleText("Accepted");
            System.out.println("Paper Status- Accepted is selected");

            // Sleep for 1 second
            Thread.sleep(1000);

            // Enter conference remarks
            WebElement remarksText = driver.findElement(By.xpath("//*[@id=\"idremark\"]"));
            remarksText.clear(); // Clear any existing text
            wait.until(ExpectedConditions.elementToBeClickable(remarksText)).sendKeys("The paper is accepted and ready to proceed.");

            // Sleep for 1 second
            Thread.sleep(1000);

            // Click the "Update" button
            WebElement updateButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/a/div/div/form/p[3]/button"));
            wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();

            // Sleep for 1 second
            Thread.sleep(1000);

            // Handle confirmation alert
            Alert confirmDeleteAlert = driver.switchTo().alert();
            String confirmDeleteAlertText = confirmDeleteAlert.getText();
            assertEquals("Are you sure?", confirmDeleteAlertText);
            confirmDeleteAlert.accept();

            // Sleep for 1 second
            Thread.sleep(1000);

            // Handle success alert
            Alert successAlert = driver.switchTo().alert();
            String successAlertText = successAlert.getText();
            assertEquals("Success", successAlertText);
            successAlert.accept();

            System.out.println("The journal status successfully updated to Accepted.");

            // Close the browser window
            driver.quit();
        }
    }

    public static class CMS_11_UploadCameraReadyForm {
        @BeforeClass
        public static void setUp() throws InterruptedException {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://sandbox.soc-conferences.com/author/login.php");
            login();
        }

        @AfterClass
        public static void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }

        @Test
        public void testUploadCameraReadyForm() throws InterruptedException {

            // Navigate to Papers section
            driver.findElement(By.linkText("Papers")).click();
            Thread.sleep(1000);

            // Click on the Details button for "Updated Paper Title"
            driver.findElement(By.xpath("//td[text()='Updated Paper Title']/following-sibling::td/a/button")).click();
            Thread.sleep(1000);

            // Verify if we are on the correct details page
            assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Paper Details')]")).isDisplayed());

            // Scroll to Pre-Camera/Camera Ready section
            WebElement cameraReadySection = driver.findElement(By.xpath("//h4[contains(text(),'Pre-Camera/Camera Ready - Step 3')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cameraReadySection);
            Thread.sleep(500);

            // Verify if we are in the Camera Ready section
            assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Pre-Camera/Camera Ready - Step 3')]")).isDisplayed());

            // Choose file for Camera Ready
            WebElement cameraReadyUpload = driver.findElement(By.name("fileToUpload"));
            cameraReadyUpload.sendKeys("C:/Users/choon/Downloads/Software Testing (no aff).docx");

            // Choose file for Copyright Form
            WebElement copyrightFormUpload = driver.findElement(By.name("fileToUploadcr"));
            copyrightFormUpload.sendKeys("C:/Users/choon/Downloads/Software Testing (with aff).docx");

            // Click the Upload button
            driver.findElement(By.id("idsubmitcamera")).click();
            Thread.sleep(1000);

            // Handle the confirmation alert
            Alert confirmationAlert = driver.switchTo().alert();
            confirmationAlert.accept();

            Thread.sleep(1000);


            // Close the current browser window
            driver.quit();

            // Open new browser window for admin login and update paper status
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Navigate to the admin login page
            driver.get("https://sandbox.soc-conferences.com/admin/login.php");

            // Perform admin login
            WebElement emailField = driver.findElement(By.xpath("//input[@name='email']"));
            emailField.sendKeys("sandbox@soc-conferences.com");
            Thread.sleep(1000);

            WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
            passwordField.sendKeys("Abc123");
            Thread.sleep(1000);

            Select conferenceDropdown = new Select(driver.findElement(By.xpath("//select[@name='conf']")));
            conferenceDropdown.selectByValue("SANDBOX24");
            Thread.sleep(1000);

            WebElement rememberCheckbox = driver.findElement(By.xpath("//input[@name='remember']"));
            if (!rememberCheckbox.isSelected()) {
                rememberCheckbox.click();
            }
            Thread.sleep(1000);

            // Handle the save preference alert
            Alert prefAlert = driver.switchTo().alert();
            String prefAlertText = prefAlert.getText();
            assertEquals("Credentials Stored Success", prefAlertText);
            prefAlert.accept();
            Thread.sleep(1000);

            WebElement loginButton = driver.findElement(By.xpath("//button[@name='submit']"));
            loginButton.click();

            // Handle the login alert
            Alert loginAlert = driver.switchTo().alert();
            String loginAlertText = loginAlert.getText();
            assertEquals("Login Successful", loginAlertText);
            loginAlert.accept();

            // Wait for the login to complete
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.titleContains("SANDBOX24 - Admin"));
            String title = driver.getTitle();
            assertEquals("SANDBOX24 - Admin", title);

            WebElement papersTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='papers.php' and contains(@class, 'w3-bar-item')]")));
            papersTab.click();

            Thread.sleep(2000);

            //Enter Author's name 'Tan Guan Xun'
            WebElement searchAuthor = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/form/div[1]/input"));
            wait.until(ExpectedConditions.elementToBeClickable(searchAuthor)).sendKeys("Tan Guan Xun");

            Thread.sleep(2000);

            // Click the "Search" button
            WebElement searchButton = driver.findElement(By.xpath("        /html/body/div[2]/div[2]/div/form/div[3]/button\n"));
            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

            Thread.sleep(2000);

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
                selectStatus.selectByVisibleText("Camera Ready");
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
            remarksField.sendKeys("The paper is Camera Ready");
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
    }

    public static class CMS_12_UpdatePaymentDetails {
        @BeforeClass
        public static void setUp() throws InterruptedException {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://sandbox.soc-conferences.com/author/login.php");
            login();
        }

        @Test
        public void testUpdatePaymentDetails() throws InterruptedException {
            // Navigate to Papers section
            driver.findElement(By.linkText("Papers")).click();
            Thread.sleep(1000);

            // Click on the Details button for "Updated Paper Title"
            driver.findElement(By.xpath("//td[text()='Updated Paper Title']/following-sibling::td/a/button")).click();
            Thread.sleep(1000);

            // Verify if we are on the correct details page
            assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Paper Details')]")).isDisplayed());

            // Scroll to Payment Details section
            WebElement paymentDetailsSection = driver.findElement(By.xpath("//h4[contains(text(),'Payment Details - Step 4')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", paymentDetailsSection);
            Thread.sleep(1000);

            // Verify if we are in the Payment Details section
            assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Payment Details - Step 4')]")).isDisplayed());

            // Fill out the payment details form
            driver.findElement(By.id("idpayment")).sendKeys("150");
            Thread.sleep(1000);

            WebElement paymentMethodDropdown = driver.findElement(By.id("idpaymethod"));
            Select paymentMethodSelect = new Select(paymentMethodDropdown);
            paymentMethodSelect.selectByVisibleText("Debit Card");
            Thread.sleep(1000);

            // Upload the proof of payment
            WebElement paymentProofUpload = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/form/p[3]/input"));
            paymentProofUpload.sendKeys("C:/Users/choon/Downloads/Payment_Proof.pdf");
            Thread.sleep(2000);

            // Click the Upload button
            driver.findElement(By.id("idsubmit")).click();
            Thread.sleep(1000);

            // Handle the confirmation alert
            Alert confirmationAlert = driver.switchTo().alert();
            confirmationAlert.accept();
            confirmationAlert.accept();
            Thread.sleep(1000);

        }
    }

    public static class CMS_13_AddCoAuthors {
        @Test
        public void testAddCoAuthors() throws InterruptedException {
            // Scroll to Co-Author section
            WebElement coAuthorSection = driver.findElement(By.xpath("//h4[contains(text(),'Co-author/s - Step 5')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", coAuthorSection);
            Thread.sleep(1000);

            // Click on Add Co-Author button
            WebElement addCoAuthorButton = driver.findElement(By.xpath("//button[contains(text(),'Add Co-Author')]"));
            addCoAuthorButton.click();
            Thread.sleep(1000);

            // Verify if the Add Co-Author modal is displayed
            WebElement addCoAuthorModal = driver.findElement(By.id("id01"));
            assertTrue(addCoAuthorModal.isDisplayed());

            // Fill out the co-author form
            WebElement coAuthorNameInput = driver.findElement(By.name("coaname"));
            coAuthorNameInput.sendKeys("John Doe");
            Thread.sleep(1000);

            WebElement coAuthorEmailInput = driver.findElement(By.name("coaemail"));
            coAuthorEmailInput.sendKeys("johndoe@example.com");
            Thread.sleep(1000);

            WebElement coAuthorOrgInput = driver.findElement(By.name("coaorg"));
            coAuthorOrgInput.sendKeys("Example University");
            Thread.sleep(1000);

            // Click the Add button
            WebElement addButton = driver.findElement(By.name("submitcoauthor"));
            addButton.click();
            Thread.sleep(1000);

            // Handle the confirmation alert
            Alert confirmationAlert = driver.switchTo().alert();
            confirmationAlert.accept();
            Thread.sleep(1000);

        }
    }

}