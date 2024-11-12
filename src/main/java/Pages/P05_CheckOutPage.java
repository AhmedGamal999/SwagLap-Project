package Pages;

import Utility.LogUtility;
import Utility.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P05_CheckOutPage {

    private final WebDriver driver;
    private final By ProductsPrices = By.xpath("//div[@class='inventory_item_price']");
    private final By itemPrices = By.xpath("//div[@class='summary_subtotal_label']");
    private final By Taxes = By.xpath("//div[@class='summary_tax_label']");
    private final By total = By.xpath("//div[@class='summary_total_label']");
    private final By FininshButton = By.xpath("//a[@class='btn_action cart_button']");
    private final By CancelButton = By.xpath("//a[@class='cart_cancel_link btn_secondary']");

    private float total_Prices;

    public P05_CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }

    public int CheckProductsQuantity() {
        List<WebElement> Products = driver.findElements(ProductsPrices);
        LogUtility.Info("Product Quantity is : " + Products.size());
        return Products.size();

    }

    public boolean CheckQuantity(int products) {
        return CheckProductsQuantity() == products;
    }

    public String CheckProductsPrices() {
        List<WebElement> Products = driver.findElements(ProductsPrices);
        for (int i = 1; i <= Products.size(); i++) {
            By ProductsPrices = By.xpath("(//div[@class='inventory_item_price'])[" + i + "]");
            String Prices = Utilities.GetData(driver, ProductsPrices).replace("$", "");
            total_Prices += Float.parseFloat(Prices);
        }
        LogUtility.Info(String.valueOf(total_Prices));
        return String.valueOf(total_Prices);

    }

    public String ItemPrices() {
        LogUtility.Info(Utilities.GetData(driver, itemPrices).replace("Item total: $", ""));
        return Utilities.GetData(driver, itemPrices).replace("Item total: $", "");

    }

    public boolean CheckPrices(String CartPricesSums, String CheckOutPricesSums) {
        return ItemPrices().equals(CartPricesSums) && CartPricesSums.equals(CheckOutPricesSums);
    }

    public String GetTaxes() {
        return Utilities.GetData(driver, Taxes).replace("Tax: $", "");
    }

    public String GetTotal() {
        return Utilities.GetData(driver, total).replace("Total: $", "");
    }

    public boolean CheckTotalWithTaxes() {
        float taxes = Float.parseFloat(GetTaxes());
        float item = Float.parseFloat(ItemPrices());
        LogUtility.Info(String.valueOf(taxes + item));
        LogUtility.Info(GetTotal());
        return GetTotal().equals(String.valueOf(taxes + item));
    }

    public P06_FinishPage FinishBoutton() {
        Utilities.ClickElement(driver, FininshButton);
        return new P06_FinishPage(driver);
    }

    public P02_ProductsPage CancelButton() {
        Utilities.ClickElement(driver, CancelButton);
        return new P02_ProductsPage(driver);
    }

    public boolean CheckURL(String URL) {
        return driver.getCurrentUrl().equals(URL);
    }


}
