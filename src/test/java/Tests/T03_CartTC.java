package Tests;

import DriverFactor.DriverFActory;
import Listeners.IInvokedmethod;
import Listeners.Itest;
import Pages.P01_LoginPage;
import Pages.P02_ProductsPage;
import Pages.P03_CartPage;
import Utility.DataUtil;
import Utility.LogUtility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactor.DriverFActory.Quit;
import static DriverFactor.DriverFActory.getdriver;

@Listeners({IInvokedmethod.class, Itest.class})
public class T03_CartTC {

    private final String usrename = DataUtil.GetStingjasondata("ValidLogin", "username");
    private final String password = DataUtil.GetStingjasondata("ValidLogin", "password");

    public T03_CartTC() throws FileNotFoundException {
    }

    @BeforeMethod
    public void setup() throws IOException {
        String Browser = System.getProperty("Browser") != null ? System.getProperty("Browser") : DataUtil.GetPropData("environment", "Browser");
        LogUtility.Info(System.getProperty("Browser"));
        DriverFActory.SetupDriver(Browser);
        LogUtility.Info("Chrome is open");
        getdriver().get(DataUtil.GetPropData("environment", "Base_URL"));
        getdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test
    public void CheckRedirectTocart() throws IOException {
        new P01_LoginPage(getdriver())
                .enter_username(usrename)
                .enter_password(password)
                .ClickLogin().
                BasketClickButton();
        Assert.assertTrue(new P03_CartPage(getdriver()).CheckPage(DataUtil.GetPropData("environment", "CartPage_URL")));

    }

    @Test
    public void CheckTheQuantity() throws FileNotFoundException {
        new P01_LoginPage(getdriver())
                .enter_username(usrename)
                .enter_password(password).
                ClickLogin().
                selectRandomProduct(3)
                .BasketClickButton();
        Assert.assertTrue(new P03_CartPage(getdriver()).checkTheQuantity(3));
    }

    @Test
    public void CheckThePriceSmeInCart() throws FileNotFoundException {
        String ProductTotalPrices = new P01_LoginPage(getdriver())
                .enter_username(usrename).
                enter_password(password)
                .ClickLogin()
                .selectRandomProduct(3)
                .ProductPagePrices();

        new P02_ProductsPage(getdriver())
                .BasketClickButton()
                .CartPagePrices();


        Assert.assertTrue(new P03_CartPage(getdriver()).
                CompareProductAndPagesTotalPrices(ProductTotalPrices));

    }

    @Test
    public void CheckContinueshopping() throws IOException {
        new P01_LoginPage(getdriver())
                .enter_username(usrename).
                enter_password(password)
                .ClickLogin()
                .BasketClickButton()
                .ContinueShopping();

        Assert.assertTrue(new P03_CartPage(getdriver()).
                CheckPage(DataUtil.GetPropData("environment", "Home_URL")));
    }

    @Test
    public void CheckRedirectCheckout() throws IOException {
        new P01_LoginPage(getdriver())
                .enter_username(usrename).
                enter_password(password)
                .ClickLogin()
                .BasketClickButton().
                CheckoutButton();
        Assert.assertTrue(new P03_CartPage(getdriver()).
                CheckPage(DataUtil.GetPropData("environment", "InformationPage_URL")));

    }


    @AfterMethod
    public void quit() {
        Quit();
    }
}


