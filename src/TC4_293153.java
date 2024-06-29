import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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

    
    // CMS_14: Test Case for Download News
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
        
        // Handle success alert
        System.out.println("The news is successfull download.");
        
    }
        
        
    // CMS_15: Test Case for Decline Review Paper
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
        
        // Click the “Review” tab
        driver.findElement(By.xpath("//*[@id='mySidebar']/div[2]/a[2]")).click();

        // Click the “Details” button
        WebElement detailButton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/table/tbody/tr[3]/td[8]/a/button"));
        wait.until(ExpectedConditions.elementToBeClickable(detailButton)).click();
        
        // Sleep for 1 seconds
        Thread.sleep(1000);

        // Switch to the new tab
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Sleep for 2 seconds
        Thread.sleep(2000);

        // Scroll down a bit
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200);");
        
        // Click “Decline” button
        WebElement declineButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div[2]/a[2]/button")));
        declineButton.click();

        
        // Handle confirmation alert
        Alert confirmAlert = driver.switchTo().alert();
        String confirmAlertText = confirmAlert.getText();
        assertEquals("Refuse to review this paper? The paper will no longer available to review", confirmAlertText);
        confirmAlert.accept();

		// Handle success alert
        Alert successAlert = driver.switchTo().alert();
        String successAlertText = successAlert.getText();
        assertEquals("Success", successAlertText);
        successAlert.accept();

        Thread.sleep(2000);


        System.out.println("The paper is successfully submit.");
     
    }
    
    // CMS_16: Test Case for Submit Review Paper
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
        WebElement detailButton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/table/tbody/tr[2]/td[8]/a/button"));
        wait.until(ExpectedConditions.elementToBeClickable(detailButton)).click();
        
        // Sleep for 1 seconds
        Thread.sleep(1000);

        // Switch to the new tab
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Sleep for 2 seconds
        Thread.sleep(2000);

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

        // Scroll down a bit
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        
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
        assertEquals("Review Submitted", successAlertText);
        successAlert.accept();

        Thread.sleep(2000);


        System.out.println("The paper is successfully submit.");
    }

    // CMS_17: Test Case for Create New Message
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

    }
    
    // CMS_18: Test Case for Delete Message
    @Test
    public void testDeleteMessage() throws InterruptedException {
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

            // Click the “Delete” button
            WebElement addMsgButton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/ul/div[1]/table/tbody/tr[2]/td[7]/a[1]/button"));
            wait.until(ExpectedConditions.elementToBeClickable(addMsgButton)).click();

            // Handle confirmation alert
            Alert confirmAlert = wait.until(ExpectedConditions.alertIsPresent());
            assertEquals("Are you sure?", confirmAlert.getText());
        	confirmAlert.accept();
        
        	// Handle success alert
        	Alert successAlert = wait.until(ExpectedConditions.alertIsPresent());
        	assertEquals("Success", successAlert.getText());
        	successAlert.accept();
    }
    
    @After
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
    
}
