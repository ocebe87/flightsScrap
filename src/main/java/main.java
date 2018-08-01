import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class main {

    public static final String BOX_DEST = "//*[@id=\"cityPair-dest-0\"]";
    public static final String BOX_ORIG = "//*[@id=\"cityPair-orig-0\"]";
    public static final String OUT_DATE = "//*[@id=\"cityPair-outDate-0\"]";
    public static final String RET_DATE = "//*[@id=\"cityPair-retDate-0\"]";

    public static void main(String[] args) {

        FirefoxDriver driver = new FirefoxDriver();
        driver.get("http://matrix.itasoftware.com/");
        WebDriverWait wait = new WebDriverWait(driver, 100);

        fillSugestionBox(driver, wait, "BCN", BOX_ORIG);
        fillSugestionBox(driver, wait, "VLC", BOX_DEST);
        fillDateBox(driver, OUT_DATE, "08/03/2018");
        fillDateBox(driver, RET_DATE, "08/03/2018");

        driver.findElementByXPath("//*[@id=\"searchButton-0\"]").click();
    }

    private static void fillDateBox(FirefoxDriver driver, String selector, String date) {
        WebElement elem = fillBox(driver, date, selector);
        elem.sendKeys(Keys.ESCAPE);
    }

    private void fillSugestionBox(FirefoxDriver driver, WebDriverWait wait, String airport, String selector) {
        fillBox(driver, airport, selector);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'(" + airport + ")')]"))).click();
    }

    private static WebElement fillBox(FirefoxDriver driver, String airport, String selector) {
        WebElement elem = driver.findElementByXPath(selector);
        elem.clear();
        elem.sendKeys(airport);
        return elem;
    }
}
