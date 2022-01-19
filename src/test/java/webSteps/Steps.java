package webSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Parser;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Steps {
    public static WebDriver driver;
    Parser parser = new Parser();
    WebDriverWait wait;
    final long SECOND_AS_MILLIS = 1000L;
    final String EMPTY_STRING = "";
    final String NEW_LINE_STRING = "\n";
    final String DOUBLE_QUOTE_STRING = "\"";

    @Given("I open browser")
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        final String EMPTY_STRING = "";
        final String NEW_LINE_STRING = "\n";
        final String DOUBLE_QUOTE_STRING = "\"";
    }

    @And("I maximize browser")
    public void maximizeBrowser() {
        driver.manage().window().maximize();
    }

    @And("I open \"([^\"]*)\" page")
    public void iOpenPage(String pageKey) throws IOException, ParseException {
        parser.setPageKey(pageKey);
        driver.get(parser.getPageObject("urlKey"));
    }

    private By bySelector(String selector) {
        if (selector.matches("^#[\\w-]+$")) {
            return By.id(selector.substring(1));
        } else if (selector.charAt(0) == '/' || selector.charAt(0) == '(' || selector.startsWith("./")) {
            return By.xpath(selector);
        } else {
            return By.cssSelector(selector);
        }
    }

    public WebElement getElement(String elementKey) throws IOException, ParseException {
        String elementValue = parser.getElementKey(elementKey);
        By selector = bySelector(elementValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return driver.findElement(selector);
    }


    @And("I click \"([^\"]*)\"")
    public void iClick(String elementKey) throws IOException, ParseException {
        if (getElement(elementKey) != null) {
            getElement(elementKey).click();
        }
    }

    @And("I fill:")
    public void iFill(Map<String, String> dataMap) throws IOException, ParseException {
        for (Map.Entry<String, String> item : dataMap.entrySet()) {
            getElement(item.getKey()).clear();
            getElement(item.getKey()).sendKeys(item.getValue());
        }
    }


    @And("I wait for {int} seconds")
    public void waitForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds * SECOND_AS_MILLIS);
    }

    @And("I see \"([^\"]*)\"")
    public void isee(String elementKey) throws IOException, ParseException {
        Assert.assertEquals(true, getElement(elementKey).isDisplayed());
    }
}

