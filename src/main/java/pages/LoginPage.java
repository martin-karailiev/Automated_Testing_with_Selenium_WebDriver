package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    By logInButton = By.className("d-button-label");;
    By telerikLoginLink = By.id("TelerikAcademyAD");
    By emailInput = By.xpath("//input[@placeholder='Email, phone, or Skype']");
    By nextButton = By.xpath("//input[@value='Next']");
    By passwordInput = By.id("i0118");
    By signInButton = By.xpath("//input[@value='Sign in']");
    By noButton = By.xpath("//input[@value='No']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // 10-second wait
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(logInButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(telerikLoginLink)).click();

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);

        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(noButton)).click();
    }
}
