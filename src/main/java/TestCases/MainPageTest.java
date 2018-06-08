package TestCases;

import Utils.BaseClass;
import Utils.CommonOps;
import Utils.PricesAndConstants;
import org.junit.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MainPageTest extends BaseClass {

    @Test
    public void test1_addToCardTesting() throws IOException, SAXException, ParserConfigurationException, InterruptedException {

        CommonOps.loginUser();
        CommonOps.verifyImageExists("Capture.PNG");
        mainPage.addItemToCard(mainPage.firstItem, PricesAndConstants.FADED_SHORT_SLEEVES_SHIRT, "1");
        mainPage.addItemToCard(mainPage.secondItem, PricesAndConstants.BLOUSE, "2");

    }

}
