package Tests;

import DriverFactor.DriverFActory;
import Listeners.IInvokedmethod;
import Listeners.Itest;
import Pages.P01_LoginPage;
import Pages.P02_ProductsPage;
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
public class T02_ProductsTC {

    private final String usrename = DataUtil.GetStingjasondata("ValidLogin", "username");
    private final String password = DataUtil.GetStingjasondata("ValidLogin", "password");

    public T02_ProductsTC() throws FileNotFoundException {
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
    public void CheckAllProductsAdd() throws IOException {
        new P01_LoginPage(getdriver())
                .enter_username(usrename)
                .enter_password(password)
                .ClickLogin()
                .ClickAddAllToCart().BasketClickButton();

        Assert.assertTrue(new P02_ProductsPage(getdriver()).CompareBetweenSelectedAndResult());
    }

    @Test
    public void AddRandomProducts() throws IOException {
        new P01_LoginPage(getdriver())
                .enter_username(usrename)
                .enter_password(password)
                .ClickLogin().
                selectRandomProduct(3);

        Assert.assertTrue(new P02_ProductsPage(getdriver()).CompareBetweenSelectedAndResult());
    }

    @AfterMethod
    public void quit() {
        Quit();
    }


}

