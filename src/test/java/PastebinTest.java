import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Logger;

import org.testng.annotations.DataProvider;
import org.openqa.selenium.support.PageFactory;

public class PastebinTest {
    private PageResultSearch itemForSearchOnPage;
    private static final String URL = "https://pastebin.com";
    private static final Logger log = Logger.getLogger(PastebinTest.class.getName());
    public static String pasteRandomContent = RandomUtil.generateRandomString();

    @DataProvider(name = "set-time")
    public Object[][] dataProviderMethodSetTime() {
        return new Object[][]{{"10 Minutes"}, {"Never"}, {"1 Hour"}, {"1 Day"}, {"1 Week"}};
    }

    @DataProvider(name = "set-syntax")
    public Object[][] dataProviderMethodSetSyntax() {
        return new Object[][]{{"Bash"}, {"JavaScript"}, {"C"}, {"CSS"}, {"Java"}};
    }

    @DataProvider(name = "send-text")
    public Object[][] dataProviderMethodSendText() {
        return new Object[][]{{pasteRandomContent}, {"Hello"}, {"Hi"}, {"Goodbye"}, {"65874"}};
    }

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        itemForSearchOnPage = PageFactory.initElements(driver, PageResultSearch.class);
        log.info("The setup process is completed");
    }

    @Test(priority = 1, description = "Checking syntax highlighting input", dataProvider = "set-syntax")
    public void dropDownSyntaxValidation(String dataSyntax) {
        itemForSearchOnPage.setElementOfSyntaxDropDown();
        String expectedSyntax = dataSyntax;
        itemForSearchOnPage.setSyntaxElementOfDropDown(expectedSyntax);
        Assert.assertEquals(itemForSearchOnPage.getChosenSyntaxValue(), expectedSyntax, "Selected list item doesn't match the expected:" + expectedSyntax + "");
        log.info("The dropdown validation process is completed");
    }

    @Test(priority = 2, description = "Checking paste expiration input ", dataProvider = "set-time")
    public void dropDownTimeValidation(String stringTime) {
        itemForSearchOnPage.setElementOfPasteExpirationDropDown();
        String expectedPasteExpiration = stringTime;
        itemForSearchOnPage.setTime(expectedPasteExpiration);
        Assert.assertEquals(itemForSearchOnPage.getChosenTimeValue(), expectedPasteExpiration, "Selected list item doesn't match the expected:" + expectedPasteExpiration + "");
        log.info("The dropdown item validation process is completed");
    }

    @Test(priority = 3, description = "Checking actual and expected text", dataProvider = "send-text")
    public void checkTextValidation(String pasteContent) {
        itemForSearchOnPage.enterRandomStringToSearchField(pasteContent);
        itemForSearchOnPage.clickCreateNewPasteButton();
        Assert.assertEquals(itemForSearchOnPage.getEnteredTextValue(), pasteContent, "Paste content doesn't match");
        log.info("The check text validation process is completed");
    }
}