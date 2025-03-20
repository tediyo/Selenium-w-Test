package web_testing_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

public class WebTesting {
    private static WebDriver localDriver;

    // Initialize system properties (e.g., path to chromedriver)
    public static void initSystemProperties() {
        // Ensure this path is correct and points to your ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");  // Update path here
    }

    // Launch the driver (browser) and navigate to a given URL, and return the driver
    public static WebDriver launchDriver(String url, String browserType) {
        if (browserType.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            // Uncomment if you want to run in headless mode
            // options.addArguments("--headless");
            localDriver = new ChromeDriver(options);
        }
        // Open the URL
        localDriver.get(url);
        return localDriver;  // Return the WebDriver instance
    }

    // FluentWait for handling dynamic waits
    public static FluentWait<WebDriver> createFluentWait() {
        return new FluentWait<>(localDriver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
    }

    // Example method to click an element after waiting for it to be clickable
    public static void clickElementWhenClickable(By locator) {
        try {
            FluentWait<WebDriver> fluentWait = createFluentWait();
            WebElement element = fluentWait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e) {
            System.out.println("Element not clickable: " + e.getMessage());
        }
    }

    // Example method to send keys to an input field after waiting for it to be present
    public static void sendKeysWhenPresent(By locator, String text) {
        try {
            FluentWait<WebDriver> fluentWait = createFluentWait();
            WebElement element = fluentWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            element.sendKeys(text);
        } catch (Exception e) {
            System.out.println("Element not found: " + e.getMessage());
        }
    }

    // Quit the driver and close the browser
    public static void quitDriver() {
        if (localDriver != null) {
            localDriver.quit();
            localDriver = null;
        }
    }

    // Getter method to access the WebDriver instance from other classes
    public static WebDriver getDriver() {
        return localDriver;
    }

    public static void main(String[] args) {
        // Example of how to use the WebTesting class to navigate to a website and interact with it

        // Initialize system properties
        initSystemProperties();

        // Launch the driver and navigate to a URL
        WebDriver driver = launchDriver("http://yourwebsite.com", "chrome");

        // Example: Click an element when it is clickable
        clickElementWhenClickable(By.id("elementId"));

        // Example: Send text to an input field when it is present
        sendKeysWhenPresent(By.name("searchBox"), "selenium");

        // Quit the driver after actions
        quitDriver();
    }
}
