package PageObjects;

import Utils.BaseClass;
import Utils.CommonOps;
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

public class MainPage extends BaseClass{


    @FindBy(xpath = "//*[@id='homefeatured']/li[1]")
    public static WebElement firstItem;

    @FindBy(xpath = "//*[@id='homefeatured']/li[2]")
    public static WebElement secondItem;

    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[2]/a[1]/span")
    public static WebElement addToCardBtn;

    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[2]/div/div[2]/div[2]/a[1]/span")
    public static WebElement addToCardBtn2;

    @FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span/span")
    public static WebElement continueShoppingBtn;

    @FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[1]/span")
    public static WebElement totalProducts;

    private static double total = 0;

    public void addItemToCard(WebElement item, String itemDesc, String itemId) throws IOException, SAXException, ParserConfigurationException {
        test.log(LogStatus.INFO, "Add item to card. #id = " + itemId);
        WebElement element = null;
            Actions actions = new Actions(driver);
            switch (itemId){
                case "1":
                    element = addToCardBtn;
                    break;
                case "2":
                    element = addToCardBtn2;
                    break;
            }
            actions.moveToElement(item).click().build().perform();
            element.click();
            total += Double.parseDouble(itemDesc);
            checkTotalPrice(itemDesc);
            test.log(LogStatus.PASS, "Item added. #id = " + itemId);
    }

    public void checkTotalPrice(String item) throws ParserConfigurationException, SAXException, IOException {
        try {
            CommonOps.switchToSubWin();
            wait.until(ExpectedConditions.visibilityOf(continueShoppingBtn));
            String totalProducts = this.totalProducts.getText();

            assertEquals(String.valueOf(String.format("%.2f",total)), totalProducts.substring(1));
            test.log(LogStatus.PASS, "Total price CORRECT !");
            continueShoppingBtn.click();
            CommonOps.switchToParentWin();
        }catch (Exception e){
            test.log(LogStatus.FAIL, "Total price FAIL! , see Screen Shot: " + e.getMessage() + test.addScreenCapture(takeScreenShot()));
            fail("Total price FAIL! ");
        }catch (AssertionError error){
            test.log(LogStatus.FAIL, "ASSERT Total price FAIL! , see Screen Shot: " + error.getMessage() + test.addScreenCapture(takeScreenShot()));
            fail("ASSERT Total price FAIL! ");
        }

    }
}
