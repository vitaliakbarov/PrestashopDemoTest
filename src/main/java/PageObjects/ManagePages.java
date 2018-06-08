package PageObjects;

import Utils.BaseClass;
import org.openqa.selenium.support.PageFactory;

public class ManagePages extends BaseClass {
    public  static void init(){

        headerNav = PageFactory.initElements(driver, HeaderNav.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        contactUs = PageFactory.initElements(driver, ContactUsPage.class);
    }
}
