package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TopicDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private String topicUrl = "https://stage-forum.telerikacademy.com/t/alpha-61-qa-we-are-awesome-and-great/11547";
    private By topicTitle = By.linkText("Alpha 61 QA - We are awesome and great");
    private By replyButton = By.cssSelector(".btn.btn-icon-text.btn-primary.create");
    private By dislikeButton = By.xpath("//button[@title='undo like']");
    private By likeButton = By.xpath("//button[@title='like this post']");

    public TopicDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Initialize the wait object
        PageFactory.initElements(driver, this);
    }

    public void openTopic() {
        driver.findElement(topicTitle).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(replyButton));
        try {
            Thread.sleep(2000);  // 2 seconds for page to settle
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void navigateToTopic() {
        driver.get(topicUrl);
        waitForPageToLoad();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Alpha topic page loaded and ready.");
    }

    public void scrollUntilReplyVisible() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement replyBtn = driver.findElement(replyButton);

        js.executeScript("arguments[0].scrollIntoView(true);", replyBtn);

        wait.until(ExpectedConditions.elementToBeClickable(replyBtn));
    }

    public void replyToTopic() {
        scrollUntilReplyVisible();

        WebElement replyBtn = driver.findElement(replyButton);
        replyBtn.click();

        CommentOnTopicPage commentOnTopicPage = new CommentOnTopicPage(driver);
        commentOnTopicPage.replyToComment();
    }


    private void waitForPageToLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(likeButton));
        System.out.println("Navigated to the topic page successfully.");
    }

    public void likeTopic() {
        WebElement likeButtonElement = driver.findElement(likeButton);
        likeButtonElement.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void dislikeTopic() {
        WebElement likeButtonElement = driver.findElement(dislikeButton);
        likeButtonElement.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isDislikeButtonVisible() {
        WebElement dislikeButtonElement = driver.findElement(dislikeButton);
        return dislikeButtonElement.isDisplayed();
    }
    public boolean islikeButtonVisible() {

        WebElement dislikeButtonElement = driver.findElement(likeButton);
        return dislikeButtonElement.isDisplayed();
    }

}
