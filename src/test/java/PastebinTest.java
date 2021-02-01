import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Logger;

import org.testng.annotations.DataProvider;
import org.openqa.selenium.support.PageFactory;

public class PastebinTest {
    private PageResultSearch itemForSearch;
    private static final String URL = "https://pastebin.com";
    private static final Logger log = Logger.getLogger(PastebinTest.class.getName());

    @DataProvider(name = "set-time")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{"10 Minutes"}, {"Never"}};
    }

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        itemForSearch = PageFactory.initElements(driver, PageResultSearch.class);
        log.info("The profile setup process is completed");
    }

    @Test(priority = 1, description = "Checking syntax highlighting input")
    public void downListValidationBash() {
        itemForSearch.setElementOfSyntaxDownList();
        String expectedSyntax = String.format("%s", "Bash");
        itemForSearch.setSyntaxElementOfDownList(expectedSyntax);
        Assert.assertEquals(itemForSearch.getChosenSyntaxValue(), expectedSyntax, "Selected list item doesn't match the expected:" + expectedSyntax + "");
        log.info("The downlist validation process is completed");
    }

    @Test(priority = 2, description = "Checking syntax highlighting input")
    public void downListValidationJava() {
        itemForSearch.setElementOfSyntaxDownList();
        String expectedSyntax = String.format("%s", "Java");
        itemForSearch.setSyntaxElementOfDownList(expectedSyntax);
        Assert.assertEquals(itemForSearch.getChosenSyntaxValue(), expectedSyntax, "Selected list item doesn't match the expected:" + expectedSyntax + "");
        log.info("The downlist validation process is completed");
    }

    @Test(priority = 3, description = "Checking syntax highlighting input")
    public void downListValidationC() {
        itemForSearch.setElementOfSyntaxDownList();
        String expectedSyntax = String.format("%s", "C");
        itemForSearch.setSyntaxElementOfDownList(expectedSyntax);
        Assert.assertEquals(itemForSearch.getChosenSyntaxValue(), expectedSyntax, "Selected list item doesn't match the expected:" + expectedSyntax + "");
        log.info("The downlist validation process is completed");
    }

    @Test(priority = 4, description = "Checking syntax highlighting input")
    public void downListValidationJavaScript() {
        itemForSearch.setElementOfSyntaxDownList();
        String expectedSyntax = String.format("%s", "JavaScript");
        itemForSearch.setSyntaxElementOfDownList(expectedSyntax);
        Assert.assertEquals(itemForSearch.getChosenSyntaxValue(), expectedSyntax, "Selected list item doesn't match the expected:" + expectedSyntax + "");
        log.info("The downlist validation process is completed");
    }

    @Test(priority = 5, description = "Checking syntax highlighting input")
    public void downListValidation() {
        itemForSearch.setElementOfSyntaxDownList();
        String expectedSyntax = String.format("%s", "Bash");
        itemForSearch.setSyntaxElementOfDownList(expectedSyntax);
        Assert.assertEquals(itemForSearch.getChosenSyntaxValue(), expectedSyntax, "Selected list item doesn't match the expected:" + expectedSyntax + "");
        log.info("The downlist validation process is completed");
    }

    @Test(priority = 6, description = "Checking paste expiration input ", dataProvider = "set-time")
    public void downListItemValidation(String stringFromDataProvider) {
        itemForSearch.setElementOfPasteExpirationDownList();
        String expectedPasteExpiration = stringFromDataProvider;
        itemForSearch.setTime(expectedPasteExpiration);
        Assert.assertEquals(itemForSearch.getChosenTimeValue(), expectedPasteExpiration, "Selected list item doesn't match the expected:" + expectedPasteExpiration + "");
        log.info("The downlist item validation process is completed");
    }

    @Test(priority = 7, description = "Checking actual and expected text")
    public void checkTextValidation() {
        itemForSearch.enterRandomStringToSearchField();
        itemForSearch.clickOnTheButtonToCreateNewPaste();
        Assert.assertEquals(itemForSearch.getAttributeFromTextField(), itemForSearch.pasteContent, "Paste content doesn't match");
        log.info("The check text validation process is completed");
    }
}