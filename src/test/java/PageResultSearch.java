import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class PageResultSearch {
    WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement searchFieldInputFrame;
    @FindBy(id = "select2-postform-format-container")
    private WebElement selectElementSyntax;
    @FindBy(id = "select2-postform-expiration-container")
    private WebElement selectElementsPasteExpiration;
    @FindBy(xpath = "//button[contains(text(),'Create New Paste')]")
    private WebElement buttonCreateNewPaste;
    @FindBy(className = "textarea")
    private WebElement searchFieldWithEnteredText;

    public static String pasteContent = RandomUtil.generateRandomString();

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public void enterRandomStringToSearchField() {
        searchFieldInputFrame.sendKeys(pasteContent);
    }

    public String getAttributeFromTextField() {
        return searchFieldWithEnteredText.getAttribute("value");

    }

    public void setElementOfSyntaxDownList() {
        selectElementSyntax.click();
    }

    public void setSyntax(String syntax) {
        driver.findElement(By.xpath("//li[contains(text(),'" + syntax + "')]"))
                .click();
    }

    public String getChosenSyntaxValue() {
        return selectElementSyntax.getAttribute("title");
    }

    public void setElementOfPasteExpirationDownList() {
        selectElementsPasteExpiration.click();
    }

    public void setTime(String time) {
        driver.findElement(By.xpath("//li[contains(text(),'" + time + "')]"))
                .click();
    }

    public String getChosenTimeValue() {
        return selectElementsPasteExpiration.getAttribute("title");
    }

    public void clickOnTheButtonToCreateNewPaste() {
        buttonCreateNewPaste.click();
    }
}