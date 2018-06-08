package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderNav {

    @FindBy(css = "a[class='login']")
    public static WebElement loginBtn;

    @FindBy(css = "a[title='Contact us']")
    public static WebElement contactUs;
}
