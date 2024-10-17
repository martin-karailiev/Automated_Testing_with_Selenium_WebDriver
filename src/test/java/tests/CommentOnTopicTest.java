package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import pages.CommentOnTopicPage;
import pages.TopicDetailsPage;

public class CommentOnTopicTest extends BaseTest {

    @RepeatedTest(1)
    public void replyToTopicTest() {
        TopicDetailsPage topicDetailsPage = new TopicDetailsPage(driver);

        topicDetailsPage.openTopic();

        topicDetailsPage.replyToTopic();
        CommentOnTopicPage commentOnTopicPage = new CommentOnTopicPage(driver);

        boolean isCommentCreated = commentOnTopicPage.isCommentCreated();
        Assertions.assertTrue(isCommentCreated, "The comment was not created successfully.");
        try {
            Thread.sleep(15000);  // 6000 milliseconds = 6 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }}


