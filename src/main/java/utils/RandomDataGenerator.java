package utils;

public class RandomDataGenerator {

    // Define min and max lengths for titles and descriptions
    private static final int TITLE_MIN_LENGTH = 5;
    private static final int TITLE_MAX_LENGTH = 255;
    private static final int DESCRIPTION_MIN_LENGTH = 100;
    private static final int DESCRIPTION_MAX_LENGTH = 500;

    // Method to generate a random title
    public static String generateRandomTitle() {
        return ExternalApiTextGenerator.fetchRandomText(TITLE_MIN_LENGTH, TITLE_MAX_LENGTH);
    }

    // Method to generate a random description
    public static String generateRandomDescription() {
        return ExternalApiTextGenerator.fetchRandomText(DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH);
    }
}
