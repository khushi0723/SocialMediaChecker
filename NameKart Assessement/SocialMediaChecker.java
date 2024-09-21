import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SocialMediaChecker {

    // List of social media platform URLs to check.
    private static final List<String> socialMediaPlatforms = List.of(
        "https://www.facebook.com/",
        "https://www.linkedin.com/company/",
        "https://www.instagram.com/",
        "https://twitter.com/"
    );

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java SocialMediaChecker <companyDomain>");
            return;
        }

        String domainName = args[0];
        List<String> foundPages = findSocialMediaPages(domainName);

        for (String page : foundPages) {
            System.out.println("Found Social Media Page: " + page);

            boolean isActive = checkIfActive(page);
            System.out.println("Page active in last 6 months: " + isActive);
        }
    }

    /**
     * Searches for social media pages associated with the given domain name.
     *
     * @param domain The company's domain name.
     * @return A list of URLs where the company's social media presence is found.
     */
    private static List<String> findSocialMediaPages(String domain) {
        List<String> foundPages = new ArrayList<>();

        for (String platform : socialMediaPlatforms) {
            String potentialUrl = platform + domain;
            if (urlExists(potentialUrl)) {
                foundPages.add(potentialUrl);
            }
        }

        return foundPages;
    }

    /**
     * Checks if a given URL exists by making a HEAD request.
     *
     * @param urlString The URL to check.
     * @return True if the URL exists, otherwise false.
     */
    private static boolean urlExists(String urlString) {
        try {
            // Use URI to create URLs more robustly
            URI uri = new URI(urlString);
            URL url = uri.toURL(); // Convert URI to URL
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("HEAD");
            int responseCode = huc.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException | URISyntaxException e) {
            return false;
        }
    }

    /**
     * Checks if the given social media page has been active in the last 6 months.
     * For simplicity, this method assumes some fake logic. API integration is needed.
     *
     * @param pageUrl The social media page URL.
     * @return True if the page is active, false otherwise.
     */
    private static boolean checkIfActive(String pageUrl) {
        // For actual implementation, integrate with Facebook, LinkedIn, Instagram, Twitter APIs.
        // This is just a placeholder logic for demonstration purposes.
        
        // API call would go here (e.g., Facebook Graph API for posts).
        return true; // Assume every page is active for now.
    }
}

