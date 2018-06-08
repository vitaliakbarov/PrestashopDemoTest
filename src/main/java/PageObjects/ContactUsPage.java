package PageObjects;

import Utils.BaseClass;
import Utils.CommonOps;
import Utils.PricesAndConstants;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ContactUsPage extends BaseClass{

    @FindBy(id = "id_contact")
    public static WebElement subjectHeading;

    @FindBy(id = "email")
    public static WebElement email;

    @FindBy(id = "id_order")
    public static WebElement orderReference;

    @FindBy(xpath = "//*[@id=\"fileUpload\"]")
    public static WebElement attachFile;

    @FindBy(id = "message")
    public static WebElement message;

    @FindBy(id = "submitMessage")
    public static WebElement sendBtn;

    @FindBy(css = "p[class='alert alert-success']")
    public static WebElement alertMsg;

    public void fillContactForm() throws ParserConfigurationException, SAXException, IOException {
        try {
            test.log(LogStatus.INFO, "Filling contact form");
            subjectHeading.sendKeys(PricesAndConstants.SUBJECT_Type);
            email.sendKeys(PricesAndConstants.EMAIL);
            orderReference.sendKeys(PricesAndConstants.ORDER_REFERENCE);
            attachFile.sendKeys(PricesAndConstants.ATTACH_FILE);
            message.sendKeys(PricesAndConstants.MESSAGE);
            sendBtn.click();
            wait.until(ExpectedConditions.visibilityOf(alertMsg));
            assertEquals(PricesAndConstants.ALERT_MESSAGE, alertMsg.getText());
            test.log(LogStatus.PASS,"Fill form PASS !!!");
        }catch (Exception e){
            test.log(LogStatus.FAIL, "Fill form FAIL! , see Screen Shot: " + e.getMessage() + test.addScreenCapture(takeScreenShot()));
            fail("Fill form FAIL! ");

        }catch (AssertionError error){
            test.log(LogStatus.FAIL, "Assert alert message FAIL! , see Screen Shot: " + error.getMessage() + test.addScreenCapture(takeScreenShot()));
            fail("Assert alert message FAIL! ");
        }
    }
}
