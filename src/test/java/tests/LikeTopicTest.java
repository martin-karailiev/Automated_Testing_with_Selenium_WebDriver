package tests;

import org.junit.jupiter.api.Test;
import pages.TopicDetailsPage;

public class LikeTopicTest extends BaseTest {

    @Test
    public void likeOriginalPostTest() {
        TopicDetailsPage topicDetailsPage = new TopicDetailsPage(driver);

        topicDetailsPage.openTopic();
        topicDetailsPage.navigateToTopic();
        topicDetailsPage.likeTopic();
        topicDetailsPage.isDislikeButtonVisible();
        System.out.println("Original post liked successfully!");
        try {
            Thread.sleep(15000);  // 6000 milliseconds = 6 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
