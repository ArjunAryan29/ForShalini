package utility;


import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;


public class BrowserDriver {
    public static RemoteWebDriver driver;

    public static void close() throws InterruptedException {
        driver.close();
    }

    public static void sendKeysByXPath(WebDriver driver, String xpath, String text) {
        WebElement element = driver.findElement(By.xpath(xpath));
        element.clear();  // Clears the field before entering text
        element.sendKeys(text);
    }
    public static void clickByXpath(WebDriver driver, String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }
    public static void DDByVisibleText(WebDriver driver, String xpath, String text) {
        WebElement dropdown = driver.findElement(By.xpath(xpath));
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    public static void Search_in_source(WebDriver driver,String text) {
        // Get the page source or the text of a specific element
        String pageSource = driver.getPageSource();

        // Check if the keyword is present
        if (pageSource.contains(text)) {
            System.out.println("Keyword found!");
        } else {
            System.out.println("Keyword not found!");
        }
    }

    public static void PageSource_Assertion(WebDriver driver,String text) {
        // Check if the keyword is present in the page source
        String pageSource = driver.getPageSource();

        // Check if the keyword is present
        if (pageSource.contains(text)) {
            System.out.println("Keyword found!");
        } else {
            System.out.println("Keyword not found!");
        }
    }

    public static void SearchBox_SearchText(WebDriver driver,String xpath1,String xpath2,String text) {
        // Get the page source or the text of a specific element

        WebElement element1 = driver.findElement(By.xpath(xpath1));
        WebElement element2 = driver.findElement(By.xpath(xpath2));
        element1.clear();
        element1.click();
        element1.sendKeys(text);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
        element2.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));

    }

    public static void Google_SearchBox_SearchText(WebDriver driver,String xpath1,String xpath2,String xpath3,String text) {
        // Get the page source or the text of a specific element

        WebElement element1 = driver.findElement(By.xpath(xpath1));
        WebElement element2 = driver.findElement(By.xpath(xpath2));
        WebElement element3 = driver.findElement(By.xpath(xpath3));
        element1.click();
        element1.clear();
        element1.sendKeys(text);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        element2.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        element3.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

    }

    public static void switchToNextTab() {
        // Get the window handles
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//        // Switch to the newly opened tab
        driver.switchTo().window(tabs.get(1));
        // Close the newly opened tab
        driver.close();
//        // Switch back to the original tab
        driver.switchTo().window(tabs.get(0));
    }

    // Reusable method to take screenshots
    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        // Convert web driver object to TakeScreenshot
        TakesScreenshot ts = (TakesScreenshot) driver;

        // Call getScreenshotAs method to create an image file
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Specify the location where screenshot will be saved
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + dateName + ".png";

        // Save the screenshot to the desired path
        try {
            FileHandler.copy(source, new File(destination));
            System.out.println("Screenshot taken: " + destination);
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }

    public static void acceptAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            // Alert not present, do nothing or handle the exception as needed
        }
    }

    public static void switchToFrame(WebDriver driver, String frameLocator) {
        WebElement frameElement = driver.findElement(By.xpath(frameLocator));
        driver.switchTo().frame(frameElement);
    }

    public static void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public static void clearText(WebDriver driver, String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        element.clear();
    }

    public static void SelectByVText(WebDriver driver, String DDName,String DDoption) {
        Select fromMonth = new Select(driver.findElement(By.name(DDName)));
        fromMonth.selectByVisibleText(DDoption);

    }


    public  static String getTextMethod(String actual) {
        String Text = driver.findElement(By.xpath(actual)).getText();
        return Text;
    }


}
