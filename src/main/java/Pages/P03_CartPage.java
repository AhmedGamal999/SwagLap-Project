package Pages;

import Utility.LogUtility;
import Utility.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_CartPage {
    private static List<WebElement> QuantityElments;
    private final By QuantityApperancLocator = By.xpath("//div[text()=1]");
    private final By CartPricesLocarors = By.xpath("//div[@class='inventory_item_price']");
    private final By CheckoutButton = By.xpath("//a[@class='btn_action checkout_button']");
    private final By ContinueShoppingButton = By.xpath("//a[@class='btn_secondary']");
    private final WebDriver driver;
    private float total_Prices;

    public P03_CartPage(WebDriver driver) {
        this.driver = driver;

    }

    public boolean CheckPage(String URL) {
        LogUtility.Info(driver.getCurrentUrl());
        return driver.getCurrentUrl().equals(URL);
    }

    public boolean checkTheQuantity(int Numbersofrandom) {
        QuantityElments = driver.findElements(QuantityApperancLocator);
        LogUtility.Info("the Quantity is :" + QuantityElments.size());
        return QuantityElments.size() == Numbersofrandom;
    }

    public String CartPagePrices() {
        try {


            List<WebElement> SelectedProductPrices = driver.findElements(CartPricesLocarors);
            for (int i = 1; i <= SelectedProductPrices.size(); i++) {
                By CartPricesLocarors = By.xpath("(//div[@class='inventory_item_price'])[" + i + "]");
                String Prices = Utilities.GetData(driver, CartPricesLocarors).replace("$", "");
                total_Prices += Float.parseFloat(Prices);


            }
            LogUtility.Info(String.valueOf(total_Prices));
            return String.valueOf(total_Prices);
        } catch (Exception e) {
            return "0";
        }


    }

    public boolean CompareProductAndPagesTotalPrices(String ProductPagePrices) {
        return CartPagePrices().equals(ProductPagePrices);
    }

    public P02_ProductsPage ContinueShopping() {
        Utilities.ClickElement(driver, ContinueShoppingButton);
        return new P02_ProductsPage(driver);
    }

    public P04_InformationPage CheckoutButton() {
        Utilities.ClickElement(driver, CheckoutButton);
        return new P04_InformationPage(driver);
    }

}