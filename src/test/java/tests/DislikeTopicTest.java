package tests;

import org.junit.jupiter.api.Test;
import pages.TopicDetailsPage;

public class DislikeTopicTest extends BaseTest {

    @Test
    public void likeOriginalPostTest() {
        TopicDetailsPage topicDetailsPage = new TopicDetailsPage(driver);

        topicDetailsPage.openTopic();

        topicDetailsPage.navigateToTopic();
        topicDetailsPage.dislikeTopic();
        topicDetailsPage.islikeButtonVisible();
        System.out.println("Original post disliked successfully!");
        try {
            Thread.sleep(15000);  // 6000 milliseconds = 6 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
