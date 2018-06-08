package TestCases;

import Utils.BaseClass;
import org.junit.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DataDrivenTestingLogin extends BaseClass {

    @Test
    public void test_dataDrivenLogin() throws IOException, ParserConfigurationException, SAXException {
        loginPage.dataDrivenLogin();
    }
}
