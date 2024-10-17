package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.io.IOException;

public class ExternalApiTextGenerator {

    // Fetch random sentences from Bacon Ipsum API
    private static final String API_URL = "https://baconipsum.com/api/?type=meat-and-filler&sentences=5";

    // Method to fetch random text and trim it according to the character limit
    public static String fetchRandomText(int minLength, int maxLength) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(API_URL);
            HttpResponse response = client.execute(request);

            String responseString = EntityUtils.toString(response.getEntity());
            JSONArray jsonArray = new JSONArray(responseString);

            // Combine the array of sentences into one string
            StringBuilder combinedText = new StringBuilder();
            for (int i = 0; i < jsonArray.length(); i++) {
                combinedText.append(jsonArray.getString(i)).append(" ");
                if (combinedText.length() >= maxLength) {
                    break;
                }
            }

            // Ensure the text meets the required length constraints
            if (combinedText.length() < minLength) {
                return combinedText.toString().trim();  // Return as is if it meets minLength
            } else {
                return combinedText.substring(0, Math.min(combinedText.length(), maxLength)).trim();  // Trim to maxLength
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Default random text in case of API failure.";
        }
    }
}
