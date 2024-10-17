package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateTopicPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By titlePlaceholder = By.id("reply-title");
    private final By descriptionPlaceholder = By.cssSelector("textarea");
    private final By fancyTitle = By.cssSelector(".fancy-title");

    @FindBy(id = "create-topic")
    private WebElement createTopicButton;

    @FindBy(xpath = "//span[contains(.,'Create Topic')]")
    private WebElement createTopicButtonBottom;

    @FindBy(css = ".show-more-actions")
    private WebElement treeDots;

    @FindBy(css = ".delete")
    private WebElement deleteTopicButton;

    public CreateTopicPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        PageFactory.initElements(driver, this);
    }

    public void createTopic(String title, String description) {
        clickButton(createTopicButton);
        waitUntilVisible(By.cssSelector(".action-title"));
        WebElement titleField = waitUntilVisible(titlePlaceholder);
        titleField.click();
        titleField.clear();
        titleField.sendKeys(title);

        WebElement descriptionField = waitUntilVisible(descriptionPlaceholder);
        descriptionField.click();
        descriptionField.sendKeys(description);

        clickButton(createTopicButtonBottom);
    }

    public void deleteTopic() {
        clickButton(treeDots);
        clickButton(deleteTopicButton);

        waitForPageReload();
    }

    public void validateTitle(String expectedTitle) {
        WebElement fancyTitleElement = waitUntilVisible(fancyTitle);
        String actualTitle = fancyTitleElement.getText();

        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Validation Passed: The title matches.");
        } else {
            throw new AssertionError("Validation Failed: Expected title '" + expectedTitle + "' but found '"
                    + actualTitle + "'.");
        }
    }

    private void waitForPageReload() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".fancy-title")));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                .equals("complete"));
   }

    private WebElement waitUntilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void clickButton(WebElement button) {
        waitUntilClickable(button);
        button.click();
    }

    private WebElement waitUntilClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
