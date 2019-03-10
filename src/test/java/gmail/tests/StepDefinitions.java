package gmail.tests;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class StepDefinitions {

    // Variables
    private WebDriver driver;
    private final String gmailHomePage = "https://www.gmail.com";
    private final String chromeDriverPath = "/Users/Admin/Downloads/chromedriver.exe";
    private final String gmailUser = "dummy428pg";
    private final String gmailPass = "DummyPass428";

    private final String attach1 = "";
    private final String attach2 = "";
    private final String attach3 = "";
    
    private int currNumberOfSent = 0;
    

    /* 
     * The following step definitions are listed and defined 
     * in the order that they are encountered in the Cucumber
     * feature file, within their type (Given, When...)
     */

    // Given
    @Given("^I am on the Gmail homepage$")
    public void gmailHomePage() throws Throwable {
    	setupSeleniumWebDrivers();
    	driver.get(gmailHomePage);
    	login();
    	System.out.println("Accessed Gmail Home Page !");
    }

    // When
    @When("^I set Recipient ([0-9]) as recipient$")
    public void setRecipient(int rcpNum) throws Throwable {
        String recipToSet = "";
        if (rcpNum == 1)
        	recipToSet = "patrick.ghazal@mail.mcgill.ca";
        else if (rcpNum == 2)
        	recipToSet = "patrick.ghazal@yahoo.com";
        
        new WebDriverWait(driver, 10)
		   .until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea[class='vO']")))
		   .sendKeys(recipToSet);
        
    	System.out.println("Set Recipient as \"" + recipToSet + "\" !");
    }

    @When("^I set Subject ([0-9]) as Subject$") 
    public void setSubject(int subjNum) throws Throwable {
        String subjToSet = "Test Subject ";
        if (subjNum == 1)
        	subjToSet += "1";
        else if (subjNum == 2)
        	subjToSet += "2";
        else if (subjNum == -1)
        	subjToSet = ""; // no subject, empty string
        
        new WebDriverWait(driver, 10)
		   .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='subjectbox']")))
		   .sendKeys(subjToSet);

        System.out.println("Set Subject as \"" + subjToSet + "\" !");
    }

    // And
    @And("^I click Compose$")
    public void clickCompose() throws Throwable {
    	
    	new WebDriverWait(driver, 20)
		   .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='T-I J-J5-Ji T-I-KE L3']")))
		   .click();
    	
    	System.out.println("Clicked on Compose !");
    }
    
    @And("^I set Subject ([0-9]) as subject$")
    public void andSetSubj(int subjNum) throws Throwable {
    	setSubject(subjNum);
    }

    @And("^I set Body ([0-9]) as body$")
    public void setBody(int bodyNum) throws Throwable {
    	String bodyToSet = "Test Body ";
    	if (bodyNum == 1)
    		bodyToSet += "1";
    	else if (bodyNum == 2)
    		bodyToSet += "2";
    	else if (bodyNum == -1)
    		bodyToSet = ""; //no body, empty string
    	
    	new WebDriverWait(driver, 10)
		   .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[aria-label='Message Body']")))
		   .sendKeys(bodyToSet);
    	
    	System.out.println("Set Body as \"" + bodyToSet + "\" !");
    }

    @And("^I set Attachment ([0-9]) as attachment$")
    public void setAttachment(int attNum) throws Throwable {
//        String attToSet;
//        switch (attNum) {
//            case 1:
//                attToSet = attach1;
//                break;
//            case 2:
//                attToSet = attach2;
//                break;
//            case 3:
//                attToSet = attach3;
//                break;
//            default:
//                return;
//        }
    	System.out.println("setAttach");
    }

    @And("^the email is sent$")
    public void emailIsSent() throws Throwable {
    	
    	driver.get("https://mail.google.com/mail/#sent");
    	
    	int newNumberOfSent = new WebDriverWait(driver, 10)
		   .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("tr[class='zA yO']")))
		   .size();
    	
    	if (newNumberOfSent == currNumberOfSent + 1) {
    		currNumberOfSent++;
    		System.out.println("The email was successfully sent !");
    	} else {
    		emailNotSent();
    	}
    }

    @And("^I Click on Send$")
    public void clickOnSend() throws Throwable {
    	
    	driver.findElement(By.cssSelector("div[role='button'][data-tooltip-delay='800']")).click();
    	
    	System.out.println("Clicked on Send !");
    }

    @And("^the system sets the empty string as subject$")
    public void emptySubject() throws Throwable {
    	setSubject(-1);
    }

    @And("^the email is not sent$")
    public void emailNotSent() throws Throwable {
    	System.out.println("The email was not sent !");
    }

    @And("^the email is sent with no attachment$")
    public void emailSentNoAtt() throws Throwable {
    	System.out.println("The email was sent with no attachment !");
    }

    // Then
    @Then("^I click on Send$")
    public void thenClickSend() throws Throwable {
    	clickOnSend();
    }

    @Then("^the system sets the empty string as body$")
    public void emptyBody() throws Throwable {
    	setBody(-1);
    }

    @Then("^I cannot click on Send$")
    public void cantClickSend() throws Throwable {
    	System.out.println("Cannot click on Send !");
    }
    
    
    // Helper functions
    private void setupSeleniumWebDrivers() throws MalformedURLException {
        if (driver == null) {
            System.out.println("Setting up ChromeDriver... ");
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            driver = new ChromeDriver();
            System.out.print("Done!\n");
        }
    }
    
    private void login() {
    	driver.findElement(By.id("identifierId")).sendKeys(gmailUser);
    	driver.findElement(By.id("identifierNext")).click();
    	
    	new WebDriverWait(driver, 10)
 			   .until(ExpectedConditions.elementToBeClickable(By.name("password")))
 			   .sendKeys(gmailPass);

    	driver.findElement(By.id("passwordNext")).click();
    	System.out.println("Logged in !");
    }

}