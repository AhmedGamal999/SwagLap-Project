package Tests;

import DriverFactor.DriverFActory;
import Listeners.IInvokedmethod;
import Listeners.Itest;
import Pages.P01_LoginPage;
import Pages.P03_CartPage;
import Pages.P05_CheckOutPage;
import Utility.DataUtil;
import Utility.LogUtility;
import Utility.Utilities;
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
public class T05_CheckoutTC {

    private final String usrename = DataUtil.GetStingjasondata("ValidLogin", "username");
    private final String password = DataUtil.GetStingjasondata("ValidLogin", "password");
    private final String Fname = DataUtil.GetStingjasondata("InformationData", "FirstName") + "-" + Utilities.GetTimeStamp();
    private final String Lname = DataUtil.GetStingjasondata("InformationData", "LastName") + "-" + Utilities.GetTimeStamp();
    private final String ZipCode = DataUtil.GetStingjasondata("InformationData", "ZipCode");

    public T05_CheckoutTC() throws FileNotFoundException {
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
    public void CheckQuantityBeforeFinish() throws IOException {
        new P01_LoginPage(getdriver())
                .enter_username(usrename)
                .enter_password(password)
                .ClickLogin()
                .selectRandomProduct(3)
                .BasketClickButton()
                .CheckoutButton()
                .FillFirstName(Fname)
                .FillLastName(Lname)
                .FillZipCode(ZipCode)
                .ContinueButton()
                .CheckProductsQuantity();
        Assert.assertTrue(new P05_CheckOutPage(getdriver()).CheckQuantity(3));


    }

    @Test
    public void CheckTheSumOfPricesSameAtCarAndChecoutAndTheSum() throws IOException {
        String Sum_Cart_Prices =
                new P01_LoginPage(getdriver())
                        .enter_username(usrename)
                        .enter_password(password)
                        .ClickLogin()
                        .selectRandomProduct(3)
                        .BasketClickButton()
                        .CartPagePrices();
        String Sum_Checkout_Prices =
                new P03_CartPage(getdriver())
                        .CheckoutButton()
                        .FillFirstName(Fname)
                        .FillLastName(Lname)
                        .FillZipCode(ZipCode)
                        .ContinueButton()
                        .CheckProductsPrices();
        new P05_CheckOutPage(getdriver())
                .ItemPrices();
        Assert.assertTrue(new P05_CheckOutPage(getdriver()).CheckPrices(Sum_Cart_Prices, Sum_Checkout_Prices));


    }

    @Test
    public void CheckTheTotalPrice() {
        new P01_LoginPage(getdriver())
                .enter_username(usrename)
                .enter_password(password)
                .ClickLogin()
                .selectRandomProduct(3)
                .BasketClickButton()
                .CheckoutButton()
                .FillFirstName(Fname)
                .FillLastName(Lname)
                .FillZipCode(ZipCode)
                .ContinueButton()
                .GetTotal();
        Assert.assertTrue(new P05_CheckOutPage(getdriver())
                .CheckTotalWithTaxes());
    }

    @Test
    public void CheckFinishButtonRedirection() throws IOException {
        new P01_LoginPage(getdriver())
                .enter_username(usrename)
                .enter_password(password)
                .ClickLogin()
                .selectRandomProduct(3)
                .BasketClickButton()
                .CheckoutButton()
                .FillFirstName(Fname)
                .FillLastName(Lname)
                .FillZipCode(ZipCode)
                .ContinueButton()
                .FinishBoutton();
        Assert.assertTrue(new P05_CheckOutPage(getdriver()).
                CheckURL(DataUtil.GetPropData("environment", "FinishPage_URL")));

    }

    @Test
    public void CheckCancelButtonRedirection() throws IOException {
        new P01_LoginPage(getdriver())
                .enter_username(usrename)
                .enter_password(password)
                .ClickLogin()
                .selectRandomProduct(3)
                .BasketClickButton()
                .CheckoutButton()
                .FillFirstName(Fname)
                .FillLastName(Lname)
                .FillZipCode(ZipCode)
                .ContinueButton()
                .CancelButton();
        Assert.assertTrue(new P05_CheckOutPage(getdriver()).
                CheckURL(DataUtil.GetPropData("environment", "Home_URL")));

    }


    @AfterMethod
    public void quit() {
        Quit();
    }
}