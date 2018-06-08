package Utils;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CommonOps extends BaseClass {

    public static void verifyElementExist(WebElement element) throws ParserConfigurationException, SAXException, IOException {
        try {
            element.isDisplayed();
            test.log(LogStatus.PASS, "Element Exist !");
        }catch (Exception e){
            test.log(LogStatus.FAIL, "Element NOT Exist !, see Screen shot: " + test.addScreenCapture(takeScreenShot()));
            fail("Element NOT Exist");
        }
    }

    public static void verifyValueExist(WebElement element, String expectedValue) throws ParserConfigurationException, SAXException, IOException {
        try{
            assertEquals(expectedValue,element.getText());
            test.log(LogStatus.PASS, "Value Exist !");

        }catch (Exception e){
            test.log(LogStatus.FAIL, "Value NOT Exist !, see Screen shot: " + e.getMessage() + test.addScreenCapture(takeScreenShot()));
            fail("Value NOT Exist !");

        }catch (AssertionError error){
            test.log(LogStatus.FAIL, "Assert failed !, see Screen shot: " + error.getMessage() + test.addScreenCapture(takeScreenShot()));
            fail("Value NOT Exist !");

        }
    }

    public static void verifyImageExists(String imageName) throws IOException, ParserConfigurationException, SAXException
    {
        try {
            screen.find(getData("ImagePath") + imageName);
            test.log(LogStatus.PASS, "Image "+ imageName +" Exists !");
        }catch (Exception e) {
            test.log(LogStatus.FAIL, "Image NOT Exists ! , see Screen Shot: " + test.addScreenCapture(takeScreenShot()));
            fail("Image NOT Exists !");
        }
    }
    public static void selectDropDown(WebElement elem, String subjectHeading) throws IOException, ParserConfigurationException, SAXException
    {
        try {
            Select myValue = new Select(elem);
            myValue.selectByValue(subjectHeading);
            test.log(LogStatus.PASS, "Element " + subjectHeading + " Selected");
        } catch (Exception e) {
            System.out.println("Element NOT Not Selected");
            test.log(LogStatus.FAIL, "Element NOT Selected , see Screen Shot: " + e.getMessage() + test.addScreenCapture(takeScreenShot()));
            fail("Element NOT Selected");
        }
    }

    public static void loginUser() throws IOException, SAXException, ParserConfigurationException {
        test.log(LogStatus.INFO, "Login User.");
        headerNav.loginBtn.click();
        loginPage.email.sendKeys(getData("email"));
        loginPage.password.sendKeys(getData("password"));
        loginPage.signInBtn.click();
        driver.get(getData("URL"));

    }

    public static void switchToSubWin(){
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler); // switch to popup window
    }

    public static void switchToParentWin(){
        String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        driver.switchTo().window(parentWindowHandler);  // switch back to parent window
    }
}
