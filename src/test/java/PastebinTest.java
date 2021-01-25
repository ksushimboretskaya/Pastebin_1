import org.openqa.selenium.WebDriver;
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
        return new Object[][]{{"10 Minutes"}, {"Never"}};
    }

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        log.info("The profile setup process is completed");
    }

    @BeforeGroups("selectListItem")
    public void sendText() {
        PageResultSearch pageSearch = PageFactory.initElements(driver, PageResultSearch.class);
        pageSearch.sendText();
    }

    @Test(priority = 1, description = "Checking syntax highlighting input", groups = "selectListItem")
    public void syntaxValidation() {
        PageResultSearch page = PageFactory.initElements(driver, PageResultSearch.class);
        page.setElementOfSyntaxDownList();
        String selectItemSyntax = "Bash";
        String selectListItemSintax = String.format("%s", selectItemSyntax);
        page.setSyntax(selectItemSyntax);
        Assert.assertEquals(page.getActualName(), "Bash", "Selected list item doesn't match the expected:" + selectListItemSintax + "");
    }

    @Test(priority = 2, description = "Checking paste expiration input ", dataProvider = "set-time", groups = "selectListItem")
    public void pasteExpirationValidation(String data) {
        PageResultSearch page = PageFactory.initElements(driver, PageResultSearch.class);
        page.setElementPasteExpirationDownList();
        String expectedTittle = data;
        String selectItemPasteExpiration = "10 Minutes";
        String selectItemPasteExp = String.format("%s", selectItemPasteExpiration);
        page.setTime(selectItemPasteExpiration);
        Assert.assertEquals(page.getActualNamePasteExp(), expectedTittle, "Selected list item doesn't match the expected:" + selectItemPasteExp + "");
        log.info("The click element process is completed");
    }

    @Test(priority = 3, description = "Create New Paste", groups = "selectListItem")
    public void checkCreateNewPasteButton() {
        PageResultSearch page = PageFactory.initElements(driver, PageResultSearch.class);
        page.clickOnTheButtonToCreateNewPaste();
        log.info("The click create new paste process is completed");
    }
}