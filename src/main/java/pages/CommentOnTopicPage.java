package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommentOnTopicPage {
    private WebDriver driver;
    private WebDriverWait wait;


    private By textarea = By.xpath("//textarea");
    private By submitReplyButton = By.cssSelector(".btn-primary:nth-child(1) > .d-button-label");
    private By commentLocator = By.xpath("//*[contains(text(), 'Hi, I am Martin , and I did it')]");
    private By showMore = By.xpath("//button[@title='show more']");
    private By deleteTheReply = By.xpath("//button[@title='delete this post']");
    private By deletedCommentLocator = By.xpath("//*[contains(text(), '(post deleted by author)')]");
    private final String replyText = "Hi, I am Martin , and I did it :smile: :partying_face: :beers: :see_no_evil: :hear_no_evil: :speak_no_evil: :heart:";

    public CommentOnTopicPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Initialize wait object
        PageFactory.initElements(driver, this);
    }

    public void replyToComment() {
        WebElement textareaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(textarea));
        textareaElement.click();
        textareaElement.clear();
        textareaElement.sendKeys(replyText);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(submitReplyButton));
        submitButton.click();

        waitForCommentToBeCreated();
    }
    public boolean isCommentCreated() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(commentLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private void waitForCommentToBeCreated() {
        boolean commentCreated = wait.until(ExpectedConditions.visibilityOfElementLocated(commentLocator)).isDisplayed();
        if (!commentCreated) {
            throw new IllegalStateException("Comment was not created successfully.");
        }
        System.out.println("Comment was created successfully!");
    }

    }

