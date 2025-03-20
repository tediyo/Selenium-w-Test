package web_testing_app;  // Ensure this matches the package of WebTesting

public class TestRunner {
    public static void main(String[] args) {
        WebTesting.initSystemProperties();
        WebTesting.launchDriver("https://www.facebook.com", "chrome");
        WebTesting.quitDriver();
    }
}
