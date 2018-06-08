package PageObjects;

import Utils.BaseClass;
import com.opencsv.CSVReader;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.fail;

public class LoginPage extends BaseClass {

    @FindBy(id = "email")
    public static WebElement email;

    @FindBy(id = "passwd")
    public static WebElement password;

    @FindBy(id = "SubmitLogin")
    public static WebElement signInBtn;

    @FindBy(xpath = "//*[@id=\"center_column\"]/div[1]/p")
    public static WebElement errorMsg;


    public void dataDrivenLogin() throws IOException, ParserConfigurationException, SAXException {
        try {
            headerNav.loginBtn.click();
            CSVReader reader = new CSVReader(new FileReader(getData("usersPath")));
            String [] csvCell;

            while ((csvCell = reader.readNext()) != null) {
                String mail = csvCell[0];
                String pass = csvCell[1];

                email.sendKeys(mail);
                password.sendKeys(pass);
                test.log(LogStatus.INFO, "Insert Email: " + mail + " Password: " + pass);
                wait.until(ExpectedConditions.elementToBeClickable(signInBtn));
                signInBtn.click();
                if (!(email.getAttribute("validationMessage").isEmpty())){
                    test.log(LogStatus.INFO, "Fail With Validation message. ");
                    email.clear();
                    password.clear();
                }
                else if (errorMsg.isDisplayed()){
                    test.log(LogStatus.INFO, "Fail With ERROR message. ");
                    email.clear();
                    password.clear();
                }
            }
            test.log(LogStatus.PASS, "Data driven LOGIN PASSED !");
        }catch (Exception e){
            test.log(LogStatus.FAIL, "Data driven LOGIN FAIL! , see Screen Shot: " + e.getMessage() + test.addScreenCapture(takeScreenShot()));
            fail("Data driven LOGIN FAIL!");
        }
    }
}
