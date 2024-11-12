package Pages;

import Utility.LogUtility;
import Utility.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_InformationPage {
    private static final By FirstName = By.xpath("//input[@id='first-name']");
    private static final By LastName = By.xpath("//input[@id='last-name']");
    private static final By ZipCode = By.xpath("//input[@id='postal-code']");
    private static final By ContinueButton = By.xpath("//input[@type='submit']");
    private static final By CancelButton = By.xpath("//a[text()='CANCEL']");


    private final WebDriver driver;

    public P04_InformationPage(WebDriver driver) {
        this.driver = driver;
    }

    public P04_InformationPage FillFirstName(String FName) {
        Utilities.SendData(driver, FirstName, FName);
        LogUtility.Info(FName);
        return this;

    }

    public P04_InformationPage FillLastName(String LName) {
        Utilities.SendData(driver, LastName, LName);
        LogUtility.Info(LName);
        return this;

    }

    public P04_InformationPage FillZipCode(String zipcode) {
        Utilities.SendData(driver, ZipCode, zipcode);
        LogUtility.Info(zipcode);
        return this;

    }

    public P03_CartPage CancelButton() {
        Utilities.ClickElement(driver, CancelButton);
        return new P03_CartPage(driver);
    }

    public P05_CheckOutPage ContinueButton() {
        try {
            Utilities.ClickElement(driver, ContinueButton);
            return new P05_CheckOutPage(driver);
        } catch (Exception e) {
            LogUtility.Error("Invalid Data");
            return this.ContinueButton();
        }
    }

    public boolean CheckURL(String URL) {
        return driver.getCurrentUrl().equals(URL);
    }
}

