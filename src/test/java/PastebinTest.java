import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.support.PageFactory;

public class PastebinTest {
    pageObjectModel objPOM;
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
        driver.get(URL);
        log.info("The profile setup process is completed");
    }

    @Test(priority =1,description ="Check entered text")
    public void sendTextAndOutput() {
        objPOM = new pageObjectModel(driver);
        String symbols = "abcdefghijklmnopqrstuvwxyz"+"1234567890"+"ABCDEFGHIJKLNMOPQRSTUVWXYZ";
        String random = new Random().ints(10, 0, symbols.length()).mapToObj(symbols::charAt).map(Object::toString).collect(Collectors.joining());
        objPOM.setText(random);
    }
    @Test(priority = 2, description = "Check syntax highlighting ")
    public void clickSyntaxHighlighting() {
        objPOM = new pageObjectModel(driver);
        String expectedTitle = "Bash";
        objPOM.setElementDownList();
        Assert.assertEquals(objPOM.getActualName(), expectedTitle,"The title of the button doesn't match the expected(bash)");
    }
    @Test(priority = 3,description = "Check paste expiration",dataProvider = "data-provider")
    public void clickPasteExpiration(String data){
        objPOM = new pageObjectModel(driver);
        String time = data;
        objPOM.setElementPasteExpiration();
        String expectedTittle="10 Minutes";
        Assert.assertEquals(objPOM.getActualNamePasteExp(), expectedTittle,"The title of the button doesn't match the expected(10 Minutes)");
        log.info("The click element process is completed");
    }
    @Test(priority=4,description ="Create New Paste")
    public void clickCreateNewPaste() {
        objPOM = new pageObjectModel(driver);
        objPOM.clickOnTheButtonToCreateNewPaste();
        log.info("The click create new paste process is completed");
    }
}