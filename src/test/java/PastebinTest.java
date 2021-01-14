import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import java.util.UUID;

public class PastebinTest {
    private WebDriver driver;

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("The setup process is completed");
    }

    @BeforeTest
    public void profileSetup() {
        driver.manage().window().maximize();
        System.out.println("The profile setup process is completed");
    }

    @BeforeClass
    public void appSetup() {
        driver.get("http://pastebin.com/N3kpQamu");
        WebElement pasteLink = driver.findElement(By.className("header__btn"));
        pasteLink.click();
        System.out.println("The app setup process is completed");
    }

    @Test(priority = 0)
        public void sendKeys() {
        String arr[]=UUID.randomUUID().toString().split("-");
        WebElement searchField = driver.findElement(By.id("postform-text"));
        searchField.sendKeys(arr[0]);
        String textAgain = searchField.getAttribute("value");
        System.out.println("Text in textarea:");
        System.out.println(textAgain);
    }

    @Test(priority = 1)
    public void clickElement() {
        WebElement selectElement = driver.findElement(By.id("select2-postform-format-container"));
        selectElement.click();
        WebElement options = driver.findElement(By.xpath("//li[contains(text(), 'Bash')]"));
        options.click();
        WebElement pasteExpiration = driver.findElement(By.id("select2-postform-expiration-container"));
        pasteExpiration.click();
        WebElement pasteExp = driver.findElement(By.xpath("//li[contains(text(), '10 Minutes')]"));
        pasteExp.click();
        System.out.println("The click element process is completed");
    }

    @AfterClass
    public void clickCreateNewPaste() {
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Create New Paste')]"));
        button.click();
        System.out.println("The click create new paste process is completed");
    }
}






