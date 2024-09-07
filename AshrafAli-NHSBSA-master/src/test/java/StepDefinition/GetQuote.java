
package StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utility.BrowserDriver;

import static pages.ValidDetails.*;
import static utility.BrowserDriver.driver;
public class GetQuote {

    @Given("I am quote generator using {string} for valid details")

    public void Browser_Choice(String browser) throws Throwable {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
        }

        driver.get("https://skryabin.com/market/quote.html");
        driver.manage().window().maximize();
        System.out.println("Title of the page:"+driver.getTitle());
    }

    @When("I enter valid Text box details")
    public void user_enter_valid_job_details() throws Throwable {
            enter_valid_TextBox_details();
        // Take a screenshot of the page loaded
        BrowserDriver.takeScreenshot(driver, "LandingPage");
    }
    @Then("I enter Country details")
    public void Enter_Country() throws Throwable {
        Country_Dropdown();
        // Take a screenshot of the page loaded
        BrowserDriver.takeScreenshot(driver, "Country_DD");
    }
    @Then("I look for Related Documents")
    public void Related_Documents() throws Throwable {
        Third_Party();
        BrowserDriver.takeScreenshot(driver, "ThirdParty");
        Next_tab();
        BrowserDriver.takeScreenshot(driver, "Nexttab");
        Previous_Tab();
        BrowserDriver.takeScreenshot(driver, "Back to previous");
        DOB();
        // Take a screenshot of the page loaded
        BrowserDriver.takeScreenshot(driver, "until DOB");

    }
    @Then("Provided the additional info and accepted the privacy policy")
    public void All_Remaining_Info() throws Throwable {
        Frame();
        Privacy_Policy();
        // Take a screenshot of the page loaded
        BrowserDriver.takeScreenshot(driver, "PrivacyPolicy");
    }

    @Then("I could see my quote was submitted successfully")
    public void Submit_Quote() throws Throwable {
        Submit_Success();
        // Take a screenshot of the page loaded
        BrowserDriver.takeScreenshot(driver, "submit");
        close();
    }
}
