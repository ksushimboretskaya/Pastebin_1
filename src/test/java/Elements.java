import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Elements {
    @FindBy(id = "postform-text")
    WebElement searchFieldInputFrame;
    @FindBy(id = "select2-postform-format-container")
    WebElement selectElementSyntax;
    @FindBy(id = "select2-postform-expiration-container")
     WebElement selectElementsPasteExpiration;
    @FindBy(xpath = "//button[contains(text(),'Create New Paste')]")
     WebElement buttonCreateNewPaste;
    @FindBy(className = "textarea")
     WebElement fieldWithEnteredText;
}
