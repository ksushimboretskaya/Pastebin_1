import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import java.security.SecureRandom;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.UUID;



public class PastebinTest {
    private WebDriver driver;
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();

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
        WebElement pastelink = driver.findElement(By.className("header__btn"));
        pastelink.click();
        System.out.println("The app setup process is completed");
    }


    @BeforeMethod
        public void send_keys() {
        String arr[]=UUID.randomUUID().toString().split("-");

        WebElement searchfield = driver.findElement(By.id("postform-text"));
        searchfield.sendKeys(arr[0]);
    }


    @Test
    public void click_element() {
        WebElement selectElement = driver.findElement(By.id("select2-postform-format-container"));
        selectElement.click();
        WebElement options = driver.findElement(By.xpath("//li[contains(text(), 'Bash')]"));
        options.click();
    }

    @Test
    public void click_element1() {
        WebElement paste_expiration = driver.findElement(By.id("select2-postform-expiration-container"));
        paste_expiration.click();
        WebElement paste_exp = driver.findElement(By.xpath("//li[contains(text(), '10 Minutes')]"));
        paste_exp.click();
        System.out.println("The click element process is completed");
    }

    @AfterMethod
    public void click_create_new_paste() {
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Create New Paste')]"));
        button.click();
        System.out.println("The click create new paste process is completed");
    }

    @AfterClass
    public void closeUp() {
        driver.close();
        System.out.println("The close_up process is completed");
    }



}






