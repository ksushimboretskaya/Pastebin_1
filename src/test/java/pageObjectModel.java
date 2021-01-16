import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class pageObjectModel {
    WebDriver driver;
    private By searchFieldLocator = By.id("postform-text");
    private By selectElementLocator = By.id("select2-postform-format-container");
    private By optionsLocator = By.xpath("//li[contains(text(),'Bash')]");
    private By pasteExpirationLocator = By.id("select2-postform-expiration-container");
    private By pasteExpLocator = By.xpath("//li[contains(text(), '10 Minutes')]");
    private By buttonLocator = By.xpath("//button[contains(text(),'Create New Paste')]");
    public pageObjectModel(WebDriver driver){
        this.driver=driver;
    }
    public void setText(String random){
        driver.findElement(searchFieldLocator).sendKeys(random);
    }
    public void setElementDownList(){
        WebElement selectElement = driver.findElement(selectElementLocator);
        selectElement.click();
        WebElement options = driver.findElement(optionsLocator);
        options.click();
        String actualTitle = driver.findElement(selectElementLocator).getAttribute("title");
    }
    public String getActualName(){
        return	driver.findElement(optionsLocator).getAttribute("title");
    }
    public void setElementPasteExpiration(){
        WebElement pasteExpiration = driver.findElement(pasteExpirationLocator);
        pasteExpiration.click();
        WebElement pasteExp = driver.findElement(pasteExpLocator);
        pasteExp.click();
    }
    public String getActualNamePasteExp(){
        return driver.findElement(pasteExpLocator).getAttribute("title");
    }
    public void clickOnTheButtonToCreateNewPaste(){
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Create New Paste')]"));
        button.click();
    }
}
