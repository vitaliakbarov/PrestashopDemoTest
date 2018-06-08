package Utils;

import PageObjects.ContactUsPage;
import PageObjects.HeaderNav;
import PageObjects.LoginPage;
import PageObjects.MainPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static Screen screen;
    public static String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(Calendar.getInstance().getTime());

    public static HeaderNav headerNav;
    public static MainPage mainPage;
    public static LoginPage loginPage;
    public static ContactUsPage contactUs;

    public  static WebDriverWait wait;


    public static String getData(String nodeName) throws ParserConfigurationException, IOException, SAXException {
        File fXmlFile = new File("params.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    public static void initBrowser(String browser) throws IOException, SAXException, ParserConfigurationException {
        switch (browser.toLowerCase()){
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFFDriver();
                break;
            case "ie":
                driver = initIEDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(getData("URL"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);

        screen = new Screen();
    }

    public static WebDriver initChromeDriver() throws IOException, SAXException, ParserConfigurationException {
        System.setProperty("webdriver.chrome.driver",getData("ChromeDriverPath"));
        WebDriver driver = new ChromeDriver();
        return driver;
    }
    public static WebDriver initFFDriver() throws ParserConfigurationException, SAXException, IOException {
        System.setProperty("webdriver.gecko.driver", getData("FFDriverPath"));
        WebDriver driverff = new FirefoxDriver();
        return driverff;
    }

    public static WebDriver initIEDriver() throws ParserConfigurationException, SAXException, IOException {
        System.setProperty("webdriver.ie.driver", getData("IEDriverPath"));
        WebDriver driverie = new InternetExplorerDriver();
        return driverie;
    }


    public static void InstanceReport() throws ParserConfigurationException, SAXException, IOException {
        extent = new ExtentReports(getData("ReportFilePath") + getData("ReportFileName") + timeStamp + ".html", true);
    }

    public static void initReportTest(String testName, String testDescription) {
        test = extent.startTest(testName, testDescription);
    }

    public static void finalizeReportTest() {
        extent.endTest(test);
    }

    public static void finalizeExtentReport() {
        extent.flush();
        extent.close();
    }

    public static String takeScreenShot() throws IOException, ParserConfigurationException, SAXException {
        String SSpath = getData("ReportFilePath") + "screenshot_" + getRandomNumber() + ".png";
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(SSpath));
        return SSpath;
    }

    public static int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(999999) + 1000;
    }

    @Rule
    public TestName name = new TestName();

    @BeforeClass
    public static void startSession() throws ParserConfigurationException, SAXException, IOException {
        initBrowser(getData("BrowserType"));
        wait = new WebDriverWait(driver,20);
        InstanceReport();
        PageObjects.ManagePages.init();
    }

    @AfterClass
    public static void closeSession() throws InterruptedException {
        System.out.println("close session");
        finalizeExtentReport();
        driver.quit();
    }

    @After
    public void doAfterTest() {
        finalizeReportTest();
    }

    @Before
    public void doBeforeTest() {
        initReportTest(name.getMethodName().split("_")[0], name.getMethodName().split("_")[1]);
    }

//    public void loginUser() throws IOException, SAXException, ParserConfigurationException {
//        test.log(LogStatus.INFO, "Login User.");
//        headerNav.loginBtn.click();
//        loginPage.email.sendKeys(getData("email"));
//        loginPage.password.sendKeys(getData("password"));
//        loginPage.signInBtn.click();
//        driver.get(getData("URL"));
//
//    }
//
//    public void switchToSubWin(){
//        String subWindowHandler = null;
//
//        Set<String> handles = driver.getWindowHandles(); // get all window handles
//        Iterator<String> iterator = handles.iterator();
//        while (iterator.hasNext()){
//            subWindowHandler = iterator.next();
//        }
//        driver.switchTo().window(subWindowHandler); // switch to popup window
//    }
//
//    public void switchToParentWin(){
//        String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
//        driver.switchTo().window(parentWindowHandler);  // switch back to parent window
//    }
}
