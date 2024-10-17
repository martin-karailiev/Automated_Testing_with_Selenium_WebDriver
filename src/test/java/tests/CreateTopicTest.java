package tests;

import org.junit.jupiter.api.RepeatedTest;
import pages.CreateTopicPage;
import utils.RandomDataGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateTopicTest extends BaseTest {

    @RepeatedTest(5)
    public void createTopicTest() {
        String initialUrl = getBaseUrl();

        String randomTitle = RandomDataGenerator.generateRandomTitle();
        String randomDescription = RandomDataGenerator.generateRandomDescription();

        CreateTopicPage createTopicPage = new CreateTopicPage(driver);
        createTopicPage.createTopic(randomTitle, randomDescription);

        createTopicPage.validateTitle(randomTitle);
        createTopicPage.deleteTopic();

        String finalUrl = driver.getCurrentUrl();
        System.out.println("Final URL: " + finalUrl);

        assertEquals(initialUrl, finalUrl, "The URL has changed after performing the actions.");
        try {
            Thread.sleep(15000);  // 6000 milliseconds = 6 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
