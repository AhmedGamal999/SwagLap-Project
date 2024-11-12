package Pages;

import Utility.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06_FinishPage {
    private final WebDriver driver;
    private final By ThanksMassage = By.xpath("//h2");

    public P06_FinishPage(WebDriver driver) {
        this.driver = driver;
    }

    public String GetThanksMessage() {

        return Utilities.GetData(driver, ThanksMassage);

    }

    public boolean CheckMessage(String msg) {
        return GetThanksMessage().equals(msg);
    }

    public boolean CheckURL(String Url) {
        return driver.getCurrentUrl().equals(Url);
    }

}
