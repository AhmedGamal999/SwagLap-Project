package Pages;

import Utility.LogUtility;
import Utility.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class P02_ProductsPage {
    private static final By AllProductClickAddToCartButton = By.xpath("//button[@class]");
    private static final By NumberOfSelectedProductsLocator = By.xpath("//span[contains(@class,\"shopping_cart_badge\")]");
    private static final By ProductSelectedNumberLocator = By.xpath("//button[text()='REMOVE']");
    private static final By BasketButton = By.xpath("//a[contains(@class,'shopping_cart')]");
    private static final By ProductPrices = By.xpath("//button[text()='REMOVE']//preceding-sibling::div[@class='inventory_item_price']");
    private static final By CheckoutButton = By.xpath("//a[@class='btn_action checkout_button']");
    private static List<WebElement> ProductElmentLocators;
    private static List<WebElement> ProductSelectedNumber;
    private static Set RandomProducts;
    private final WebDriver driver;
    private float total_Prices;


    public P02_ProductsPage(WebDriver driver) {
        this.driver = driver;
    }


    public P02_ProductsPage ClickAddAllToCart() {
        ProductElmentLocators = driver.findElements(AllProductClickAddToCartButton);
        LogUtility.Info("number of products is :" + ProductElmentLocators.size());
        for (int i = 1; i <= ProductElmentLocators.size(); i++) {
            By AllProductClickAddToCartButton = By.xpath("(//button[@class])[" + i + "]");
            Utilities.ClickElement(driver, AllProductClickAddToCartButton);
        }
        return this;
    }

    public String NumberOfSelectedProduct() {
        try {
            return Utilities.GetData(driver, NumberOfSelectedProductsLocator);
        } catch (Exception e) {
            LogUtility.Error("No selected Product founds");
            return "0";
        }
    }


    public boolean CompareBetweenSelectedAndResult() {
        ProductSelectedNumber = driver.findElements(ProductSelectedNumberLocator);
        LogUtility.Info("number is 6");
        return NumberOfSelectedProduct().equals(String.valueOf(ProductSelectedNumber.size()));

    }

    public P02_ProductsPage selectRandomProduct(int Numbers_of_products) {
        ProductElmentLocators = driver.findElements(AllProductClickAddToCartButton);
        RandomProducts = Utilities.grnerateUniqueRandomNumbers(Numbers_of_products, ProductElmentLocators.size());
        LogUtility.Info("the numbers is " + RandomProducts);

        for (Object i : RandomProducts) {
            By AllProductClickAddToCartButton = By.xpath("(//button[@class])[" + i + "]");
            Utilities.ClickElement(driver, AllProductClickAddToCartButton);

        }
        return this;

    }

    public P03_CartPage BasketClickButton() {
        Utilities.ClickElement(driver, BasketButton);
        return new P03_CartPage(driver);
    }

    public String ProductPagePrices() {
        try {


            List<WebElement> SelectedProductPrices = driver.findElements(ProductPrices);
            for (int i = 1; i <= SelectedProductPrices.size(); i++) {
                By ProductPrices = By.xpath("(//button[text()='REMOVE']//preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                String Prices = Utilities.GetData(driver, ProductPrices).replace("$", "");
                total_Prices += Float.parseFloat(Prices);


            }
            LogUtility.Info(String.valueOf("Total Prices" + total_Prices));
            return String.valueOf(total_Prices);
        } catch (Exception e) {
            return "0";
        }

    }


}
