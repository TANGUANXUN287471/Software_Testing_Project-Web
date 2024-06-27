import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class TC4_293153 { //Review Role
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        // Set the path to the chromedriver executable
//        System.setProperty("webdriver.chrome.driver", "C:\\\\selenium webdriver\\\\chromedriver-win64\\\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "/Users/choon/Documents/Java Libraries/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }



    // CMS_16: Test Case for Register Reviewer Account
    @Test
    public void testRegisterAccount() {
        // Open the login page
        driver.get("https://sandbox.soc-conferences.com/reviewer/login.php");

        // Maximize the browser window
        driver.manage().window().maximize();

        // Wait for the "Create here" link to be present in the DOM
        WebElement createHereLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/p/a[1]")));

        // Use JavaScript to click the link
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", createHereLink);

        // Wait for the new page to load
        wait.until(ExpectedConditions.urlToBe("https://sandbox.soc-conferences.com/reviewer/register.php"));


        // Fill the registration form
        //Expertise
        WebElement selectExp = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[1]/div[2]/div"));
        wait.until(ExpectedConditions.elementToBeClickable(selectExp)).click();

        WebElement f1 = driver.findElement(By.xpath("//*[@id=\"selectid1\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(f1)).click();

        WebElement selected = driver.findElement(By.xpath("//*[@id=\"idsubmitfields\"]/div/div/button"));
        wait.until(ExpectedConditions.elementToBeClickable(selected)).click();

        driver.findElement(By.xpath("//*[@id='idname']")).sendKeys("John Doe");
        driver.findElement(By.xpath("//*[@id='idemail']")).sendKeys("john.doe@example.com");
        driver.findElement(By.xpath("//*[@id='idphone']")).sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id='idorg']")).sendKeys("Organization Name");
        driver.findElement(By.xpath("//*[@id='idaddress']")).sendKeys("Mailing Address");
        driver.findElement(By.xpath("//*[@id='idurl']")).sendKeys("http://example.com");
        new Select(driver.findElement(By.xpath("//*[@id='country']"))).selectByVisibleText("Malaysia");
        new Select(driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[2]/div[1]/select"))).selectByVisibleText("Professor");

        driver.findElement(By.xpath("//*[@id='idpass']")).sendKeys("password123");
        driver.findElement(By.xpath("//*[@id='idpassb']")).sendKeys("password123");
        // Wait for the reCAPTCHA to be present in the DOM
        WebElement recaptchaCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='recaptcha-anchor']/div[1]")));

        // Use JavaScript to click the reCAPTCHA checkbox
        JavascriptExecutor jsrc = (JavascriptExecutor) driver;
        jsrc.executeScript("arguments[0].click();", recaptchaCheckbox);

        // Wait for a short time to ensure the reCAPTCHA is processed (usually you need to solve it manually)

        driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]/div[1]")).click(); // Assuming reCAPTCHA can be bypassed for test
        driver.findElement(By.xpath("//*[@id=\"idsubmit\"]")).click();

        // Handle confirmation alert
        Alert confirmAlert = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals("Register new account?", confirmAlert.getText());
        confirmAlert.accept();

        // Handle success alert
        Alert successAlert = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals("Success", successAlert.getText());
        successAlert.accept();
    }


    // CMS_17: Test Case for Download News
    @Test
    public void testDownloadNews() throws InterruptedException {
        //open website
        driver.get("https://sandbox.soc-conferences.com/reviewer/login.php");
        Thread.sleep(1000);

        // Enter email, password, and click "Remember Me"
        driver.findElement(By.id("idemail")).sendKeys("muyya67@gmail.com");
        driver.findElement(By.id("idpass")).sendKeys("Ipmr3avQ");
        driver.findElement(By.id("idremember")).click();

        // Handle the alert
        Alert alert = driver.switchTo().alert();
        alert.accept();

        // Click "Login" button
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/p[4]/button")).click();
        alert.accept();
        // Wait for the login process to complete
        Thread.sleep(2000);


        // Click the “News” tab
        WebElement newsTab = driver.findElement(By.xpath("//a[@href='index.php' and contains(@class, 'w3-bar-item')]"));
        newsTab.click();

        // Sleep for 1 seconds
        Thread.sleep(1000);


        // Click the “News” button
        WebElement newsButton = driver.findElement(By.xpath("/html/body/div[2]/div[3]/ul/div/table/tbody/tr[6]/td[6]/a/button"));
        wait.until(ExpectedConditions.elementToBeClickable(newsButton)).click();

        // Sleep for 1 seconds
        Thread.sleep(1000);

        // Click the “Download” button
        WebElement downloadButton = driver.findElement(By.xpath("//*[@id=\"newsdetails5\"]/div/div/table/tbody/tr[4]/td/a/button"));
        wait.until(ExpectedConditions.elementToBeClickable(downloadButton)).click();

        // Sleep for 1 seconds
        Thread.sleep(1000);

    }

    // Check download success (assumed to be verified by the presence of a specific element or text)
    // Add necessary assertions here


    // CMS_18: Test Case for Decline Review Paper
    @Test
    public void testDeclinePaper() throws InterruptedException {
        //open website
        driver.get("https://sandbox.soc-conferences.com/reviewer/login.php");
        Thread.sleep(1000);

        // Enter email, password, and click "Remember Me"
        driver.findElement(By.id("idemail")).sendKeys("muyya67@gmail.com");
        driver.findElement(By.id("idpass")).sendKeys("Ipmr3avQ");
        driver.findElement(By.id("idremember")).click();

        // Handle the alert
        Alert alert = driver.switchTo().alert();
        alert.accept();

        // Click "Login" button
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/p[4]/button")).click();
        alert.accept();
        // Wait for the login process to complete
        Thread.sleep(2000);

        // Click the "review” tab
        WebElement reviewTab = driver.findElement(By.xpath("//a[@href='reviews.php' and contains(@class, 'w3-bar-item')]"));
        reviewTab.click();

        // Sleep for 1 seconds
        Thread.sleep(1000);


        // Click the “Details” button
        WebElement detailButton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/table/tbody/tr[3]/td[8]/a/button"));
        wait.until(ExpectedConditions.elementToBeClickable(detailButton)).click();

        // Click “Decline” button
        WebElement declineButton = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[2]/a[2]/button"));
        wait.until(ExpectedConditions.elementToBeClickable(declineButton)).click();


        // Handle confirmation alert
        Alert confirmAlert = driver.switchTo().alert();
        String confirmAlertText = confirmAlert.getText();
        assertEquals("Refuse to review this paper? The paper will no longer available to review", confirmAlertText);
        confirmAlert.accept();

        // Check decline success (assumed to be verified by the presence of a specific element or text)
        // Add necessary assertions here
    }

    // CMS_19: Test Case for Submit Review Paper
    @Test
    public void testSubmitPaper() throws InterruptedException {
        //open website
        driver.get("https://sandbox.soc-conferences.com/reviewer/login.php");
        Thread.sleep(1000);

        // Enter email, password, and click "Remember Me"
        driver.findElement(By.id("idemail")).sendKeys("muyya67@gmail.com");
        driver.findElement(By.id("idpass")).sendKeys("Ipmr3avQ");
        driver.findElement(By.id("idremember")).click();

        // Handle the alert
        Alert alert = driver.switchTo().alert();
        alert.accept();

        // Click "Login" button
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/p[4]/button")).click();
        alert.accept();
        // Wait for the login process to complete
        Thread.sleep(2000);

        // Click the “Review” tab
        driver.findElement(By.xpath("//*[@id='mySidebar']/div[2]/a[2]")).click();

        // Click the “Details” button
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/table/tbody/tr[3]/td[8]/a/button")).click();

        // Fill out the review form
        //insert rubric items
        WebElement q1 = driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[2]/td[2]/textarea"));
        wait.until(ExpectedConditions.elementToBeClickable(q1)).sendKeys("Yes");
        driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[2]/td[3]/table/tbody/tr[1]/td[5]/input")).click();

        WebElement q2 = driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[3]/td[2]/textarea"));
        wait.until(ExpectedConditions.elementToBeClickable(q2)).sendKeys("Yes");
        driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[3]/td[3]/table/tbody/tr[1]/td[5]/input")).click();

        WebElement q3 = driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[4]/td[2]/textarea"));
        wait.until(ExpectedConditions.elementToBeClickable(q3)).sendKeys("Yes");
        driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[4]/td[3]/table/tbody/tr[1]/td[5]/input")).click();

        WebElement q4 = driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[5]/td[2]/textarea"));
        wait.until(ExpectedConditions.elementToBeClickable(q4)).sendKeys("Yes");
        driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[5]/td[3]/table/tbody/tr[1]/td[5]/input")).click();

        WebElement q5 = driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[6]/td[2]/textarea"));
        wait.until(ExpectedConditions.elementToBeClickable(q5)).sendKeys("Yes");
        driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[6]/td[3]/table/tbody/tr[1]/td[5]/input")).click();

        WebElement q6 = driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[7]/td[2]/textarea"));
        wait.until(ExpectedConditions.elementToBeClickable(q6)).sendKeys("Yes");
        driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[7]/td[3]/table/tbody/tr[1]/td[5]/input")).click();

        WebElement q7 = driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[8]/td[2]/textarea"));
        wait.until(ExpectedConditions.elementToBeClickable(q7)).sendKeys("Yes");
        driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[8]/td[3]/table/tbody/tr[1]/td[5]/input")).click();

        WebElement q8 = driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[9]/td[2]/textarea"));
        wait.until(ExpectedConditions.elementToBeClickable(q8)).sendKeys("Yes");
        driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[9]/td[3]/table/tbody/tr[1]/td[5]/input")).click();

        WebElement q9 = driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[10]/td[2]/textarea"));
        wait.until(ExpectedConditions.elementToBeClickable(q9)).sendKeys("Yes");
        driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[10]/td[3]/table/tbody/tr[1]/td[5]/input")).click();

        WebElement q10 = driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[11]/td[2]/textarea"));
        wait.until(ExpectedConditions.elementToBeClickable(q10)).sendKeys("Yes");
        driver.findElement(By.xpath("/html/body/a/div[1]/form/div/div[1]/table/tbody/tr[11]/td[3]/table/tbody/tr[1]/td[5]/input")).click();

        WebElement finalRemarks = driver.findElement(By.xpath("/html/body/a/div[1]/form/div/p[2]/textarea"));
        wait.until(ExpectedConditions.elementToBeClickable(finalRemarks)).sendKeys("Good");

        WebElement conferenceOrganizer = driver.findElement(By.xpath("/html/body/a/div[1]/form/div/p[4]/textarea"));
        wait.until(ExpectedConditions.elementToBeClickable(conferenceOrganizer)).sendKeys("Good");

        // Click the “Submit” button
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"idsubmit\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        // Handle confirmation alert
        Alert confirmAlert = driver.switchTo().alert();
        String confirmAlertText = confirmAlert.getText();
        assertEquals("Submit this review?", confirmAlertText);
        confirmAlert.accept();

        // Handle success alert
        Alert successAlert = driver.switchTo().alert();
        String successAlertText = successAlert.getText();
        assertEquals("Success", successAlertText);
        successAlert.accept();



        // Check submit success (assumed to be verified by the presence of a specific element or text)
        // Add necessary assertions here
    }

    // CMS_20: Test Case for Create New Message
    @Test
    public void testCreateMessage() throws InterruptedException {
        //open website
        driver.get("https://sandbox.soc-conferences.com/reviewer/login.php");
        Thread.sleep(1000);

        // Enter email, password, and click "Remember Me"
        driver.findElement(By.id("idemail")).sendKeys("muyya67@gmail.com");
        driver.findElement(By.id("idpass")).sendKeys("Ipmr3avQ");
        driver.findElement(By.id("idremember")).click();

        // Handle the alert
        Alert alert = driver.switchTo().alert();
        alert.accept();

        // Click "Login" button
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/p[4]/button")).click();
        alert.accept();
        // Wait for the login process to complete
        Thread.sleep(2000);

        // Click the “Messages” tab
        driver.findElement(By.xpath("//*[@id='mySidebar']/div[2]/a[3]")).click();

        // Click the “New Message” button
        WebElement addMsgButton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/a/div"));
        wait.until(ExpectedConditions.elementToBeClickable(addMsgButton)).click();

        // Find the dropdown element
        WebElement selectConf = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/form/p[1]/select"));
        Select dropdown = new Select(selectConf);// Create a Select object
        dropdown.selectByVisibleText("SANDBOX24 - Sandbox International Conference 2024 June");// Select the option 'All'


        driver.findElement(By.xpath("//*[@id='idtitle']")).sendKeys("Test Add Msg");
        driver.findElement(By.xpath("//*[@id='idmessage']")).sendKeys("Alhamdulillah");
        driver.findElement(By.xpath("//*[@id='idsubmit']")).click();

        // Handle confirmation alert
        Alert confirmAlert = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals("Are you sure", confirmAlert.getText());
        confirmAlert.accept();

        // Check message creation success (assumed to be verified by the presence of a specific element or text)
        // Add necessary assertions here
    }
    @After
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

}