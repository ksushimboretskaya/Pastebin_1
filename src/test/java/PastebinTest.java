import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Logger;

import org.testng.annotations.DataProvider;
import org.openqa.selenium.support.PageFactory;

public class PastebinTest {
    private WebDriver driver;
    private PageResultSearch pageSearch;
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
        pageSearch = PageFactory.initElements(driver, PageResultSearch.class);
        log.info("The profile setup process is completed");
    }

    @BeforeGroups("selectListItem")
    public void sendText() {
        pageSearch.sendText();
    }

    @Test(priority = 1, description = "Checking syntax highlighting input", groups = "selectListItem")
    public void syntaxListItemValidation() {
        pageSearch.setElementOfSyntaxDownList();
        String selectListItemSyntax = String.format("%s", "Bash");
        pageSearch.setSyntax(selectListItemSyntax);
        Assert.assertEquals(pageSearch.getActualNameListItemSyntax(), selectListItemSyntax, "Selected list item doesn't match the expected:" + selectListItemSyntax + "");
    }

    @Test(priority = 2, description = "Checking paste expiration input ", dataProvider = "set-time", groups = "selectListItem")
    public void pasteExpirationValidation(String stringFromDataProvider) {
        pageSearch.setElementPasteExpirationDownList();
        String selectItemPasteExp = stringFromDataProvider;
        pageSearch.setTime(selectItemPasteExp);
        Assert.assertEquals(pageSearch.getActualNameListItemPasteExp(), selectItemPasteExp, "Selected list item doesn't match the expected:" + selectItemPasteExp + "");
        log.info("The click element process is completed");
    }

    @Test(priority = 3, description = "Create New Paste", groups = "selectListItem")
    public void checkCreateNewPasteButton() {
        pageSearch.clickOnTheButtonToCreateNewPaste();
        log.info("The click create new paste process is completed");
    }
}