package Pages;

import Utility.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {
    private final By User_Name = By.id("user-name");
    private final By Password = By.id("password");
    private final By Login_Button = By.id("login-button");
    private final WebDriver driver;

    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;

    }

    public P01_LoginPage enter_username(String username) {
        Utilities.SendData(driver, User_Name, username);
        return this;
    }

    public P01_LoginPage enter_password(String password) {
        Utilities.SendData(driver, Password, password);
        return this;
    }

    public P02_ProductsPage ClickLogin() {
        Utilities.ClickElement(driver, Login_Button);
        return new P02_ProductsPage(driver);

    }

    public boolean assertlogin(String expected) {
        return driver.getCurrentUrl().equals(expected);
    }

}
