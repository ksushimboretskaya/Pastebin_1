import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.logging.Logger;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.support.PageFactory;

public class PastebinTest {
    private WebDriver driver;
    private static final String URL = "https://pastebin.com";
    private static Logger log = Logger.getLogger(PastebinTest.class.getName());
    @DataProvider(name = "set-time")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "10 Minutes" }, { "Never" } };
    }
    @BeforeSuite
    public void setWebSite() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        log.info("The profile setup process is completed");
    }
    @Test(priority =1,description ="Check entered text")
    public void sendTextAndOutput() {
        pageObjectModel page = PageFactory.initElements(driver, pageObjectModel.class);
        page.sendText();
    }
    @Test(priority = 2, description = "Check syntax highlighting ")
    public void clickSyntaxHighlighting() {
        pageObjectModel page = PageFactory.initElements(driver, pageObjectModel.class);
        page.setElementDownList();
        page.setSyntax("Bash");
        Assert.assertEquals(page.getActualName(), "Bash","The title of the button doesn't match the expected(bash)");
    }
    @Test(priority = 3,description = "Check paste expiration", dataProvider = "set-time")
    public void clickPasteExpiration(String data){
        pageObjectModel page = PageFactory.initElements(driver, pageObjectModel.class);
        page.setElementPasteExpiration();
        String expectedTittle = data;
        page.setTime("10 Minutes");
        Assert.assertEquals(page.getActualNamePasteExp(), expectedTittle,"The title of the button doesn't match the expected(10 Minutes)");
        log.info("The click element process is completed");
    }
    @Test(priority=4,description ="Create New Paste")
    public void clickCreateNewPaste() {
        pageObjectModel page = PageFactory.initElements(driver, pageObjectModel.class);
        page.clickOnTheButtonToCreateNewPaste();
        log.info("The click create new paste process is completed");
    }
}