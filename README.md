# SocialMediaChecker
This Java project is a tool designed to:

Find social media pages associated with a company's domain on popular platforms (e.g., Facebook, LinkedIn, Instagram, and Twitter).
Check if the social media pages are active, i.e., whether they have posted content in the last six months (though this logic currently uses a placeholder and can be enhanced).
Components Breakdown :


1. Social Media Platforms

private static final List<String> socialMediaPlatforms = List.of(
    "https://www.facebook.com/",
    "https://www.linkedin.com/company/",
    "https://www.instagram.com/",
    "https://twitter.com/"
);
Purpose: This list holds the base URLs of popular social media platforms where companies usually create their pages. When you pass in a company domain, the program will append it to each of these base URLs to form the full URL of the company's social media profile.

Example: If you pass in the domain "apple", the program will check URLs like:

https://www.facebook.com/apple

https://www.linkedin.com/company/apple

https://www.instagram.com/apple

https://twitter.com/apple

2. Main Method

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
Purpose: This is the entry point of the application. It performs the following steps:
Check Command-Line Arguments: It expects one argument, the company's domain name. If not provided, it prints the usage instruction and exits.
Call to findSocialMediaPages(): This method is called with the domain name as input. It returns a list of URLs of the company's social media pages that exist.
Print Found Pages: The tool prints all the social media pages found for the given company.
Check Activity: It calls the checkIfActive() method for each found page to check if it has been active in the last six months (this method is a placeholder for now but can be integrated with APIs later).
3. Find Social Media Pages Method

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
Purpose: This method takes the company's domain (e.g., "apple") and appends it to each platform's base URL. Then, it checks if the constructed URL exists.
For Each Platform: It constructs the social media URL (e.g., https://www.facebook.com/apple) and passes it to urlExists().
Return: It returns a list of URLs that actually exist, meaning the company has profiles on those social media platforms.
4. URL Existence Checker

private static boolean urlExists(String urlString) {
    try {
        URI uri = new URI(urlString);
        URL url = uri.toURL();
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("HEAD");
        int responseCode = huc.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_OK;
    } catch (IOException | URISyntaxException e) {
        return false;
    }
}
Purpose: This method checks if the URL for a company's social media profile exists. It does so by making a HEAD request, which only checks the headers of the HTTP response without downloading the entire page.
Steps:
Create a URI: Constructs a URI from the URL string, ensuring proper syntax.
Convert URI to URL: Converts the URI to a URL object.
Open a Connection: Opens a connection using HttpURLConnection and sends a HEAD request.
Check the Response Code: If the server responds with HTTP code 200 (HTTP_OK), the page exists, and the method returns true. If any errors occur (like URISyntaxException or IOException), the method returns false, meaning the URL does not exist.
5. Check Activity of Social Media Pages

private static boolean checkIfActive(String pageUrl) {
    // For actual implementation, integrate with Facebook, LinkedIn, Instagram, Twitter APIs.
    // This is just a placeholder logic for demonstration purposes.
    
    // API call would go here (e.g., Facebook Graph API for posts).
    return true; // Assume every page is active for now.
}
Purpose: This method is designed to check whether a given social media page has been active in the last six months. For now, it's just a placeholder that returns true for demonstration purposes.
In the Future: The placeholder logic can be replaced by integrating with APIs from social media platforms (e.g., Facebook Graph API, LinkedIn API, Instagram API, Twitter API) to fetch the latest posts and check their dates.
Key Points:
Finding Social Media Pages: The core functionality of the tool is to check if a company has active pages on social media by constructing URLs based on a given domain.
URL Validation: By making HTTP HEAD requests, the tool efficiently checks if a social media profile exists without downloading the entire page.
Activity Check Placeholder: The tool currently assumes that every found page is active. However, you can replace the placeholder logic with real API calls to check for recent posts.
Error Handling: The code includes basic error handling, such as catching IOException and URISyntaxException, ensuring that invalid URLs or connection failures don't crash the program.
How to run the code:

javac SocialMediaChecker.java

next:

java SocialMediaChecker exampleCompany

or

java SocialMediaChecker companyDomain

