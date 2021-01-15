import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.testng.annotations.DataProvider;

public class PastebinTest{
    private WebDriver driver;
    private static final String URL = "https://pastebin.com";
    private static Logger log = Logger.getLogger(PastebinTest.class.getName());
    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "10 Minutes" }, { "Never" } };
    }
    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        log.info("The setup process is completed");
    }
    @BeforeTest
    public void profileSetup() {
        driver.manage().window().maximize();
        log.info("The profile setup process is completed");
        driver.get(URL);
        log.info("The app setup process is completed");
    }
    @Test(priority =1,description ="Check entered text")
    public void sendTextAndOutput() {
        String symbols = "abcdefghijklmnopqrstuvwxyz"+"1234567890"+"ABCDEFGHIJKLNMOPQRSTUVWXYZ";
        String random = new Random().ints(10, 0, symbols.length()).mapToObj(symbols::charAt).map(Object::toString).collect(Collectors.joining());
        WebElement searchField = driver.findElement(By.id("postform-text"));
        searchField.sendKeys(random);
        String textAgain = searchField.getAttribute("value");
        log.info("Text in textarea:" + textAgain);
    }
    @Test(priority = 2, description = "Check syntax highlighting ")
    public void clickSyntaxHighlighting(String data) {
        String expectedTitle = "Bash";
        WebElement selectElement = driver.findElement(By.id("select2-postform-format-container"));
        selectElement.click();
        WebElement options = driver.findElement(By.xpath("//li[contains(text(),'"+expectedTitle+"')]"));
        options.click();
        String actualTitle = selectElement.getAttribute("title");
        Assert.assertEquals(actualTitle, expectedTitle,"The title of the button doesn't match the expected(bash)");
    }
    @Test(priority = 3,description = "Check paste expiration",dataProvider = "data-provider")
    public void clickPasteExpiration(String data){
        String time = data;
        WebElement pasteExpiration = driver.findElement(By.id("select2-postform-expiration-container"));
        pasteExpiration.click();
        WebElement pasteExp = driver.findElement(By.xpath("//li[contains(text(), '"+time+"')]"));
        pasteExp.click();
        String actualTittle=pasteExpiration.getAttribute("title");
        String expectedTittle="10 Minutes";
        Assert.assertEquals(actualTittle, expectedTittle,"The title of the button doesn't match the expected(10 Minutes)");
        log.info("The click element process is completed");
    }
    @Test(priority=4,description ="Create New Paste")
    public void clickCreateNewPaste() {
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Create New Paste')]"));
        button.click();
        log.info("The click create new paste process is completed");
    }
}