import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;
import java.util.stream.Collectors;

public class PageResultSearch {
    WebDriver driver;
    @FindBy(id="postform-text")
    private WebElement searchFieldLocatorInputFrame;
    @FindBy(id="select2-postform-format-container")
    private WebElement selectElementLocatorSyntax;
    @FindBy(id ="select2-postform-expiration-container")
    private WebElement pasteExpirationLocator;
    @FindBy(xpath = "//button[contains(text(),'Create New Paste')]")
    private WebElement buttonLocatorCreateNewPaste;
    public void init(final WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void sendText(){
        String symbols = "abcdefghijklmnopqrstuvwxyz"+"1234567890"+"ABCDEFGHIJKLNMOPQRSTUVWXYZ";
        String random = new Random()
                .ints(10, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
        searchFieldLocatorInputFrame.sendKeys(random);
    }
    public void setElementOfSyntaxDownList(){
        selectElementLocatorSyntax.click();
    }
    public void setSyntax(String syntax){
        driver.findElement(By.xpath("//li[contains(text(),'"+syntax+"')]"))
                .click();
    }
    public String getActualName(){
        return	selectElementLocatorSyntax.getAttribute("title");
    }
    public void setElementPasteExpirationDownList(){
        pasteExpirationLocator.click();
    }
    public void setTime(String time){
        driver.findElement(By.xpath("//li[contains(text(),'"+time+"')]"))
                .click();
    }
    public String getActualNamePasteExp(){
        return pasteExpirationLocator.getAttribute("title");
    }
    public void clickOnTheButtonToCreateNewPaste(){
        buttonLocatorCreateNewPaste.click();
    }
}