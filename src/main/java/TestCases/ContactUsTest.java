package TestCases;

import Utils.BaseClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ContactUsTest extends BaseClass {
    @Test
    public void test01_fillForm() throws IOException, SAXException, ParserConfigurationException, InterruptedException {
        headerNav.contactUs.click();
        contactUs.fillContactForm();
    }
}
