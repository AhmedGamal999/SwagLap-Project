package Tests;

import DriverFactor.DriverFActory;
import Listeners.IInvokedmethod;
import Listeners.Itest;
import Pages.P01_LoginPage;
import Pages.P04_InformationPage;
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
public class T04_InformationTC {

    private final String usrename = DataUtil.GetStingjasondata("ValidLogin", "username");
    private final String password = DataUtil.GetStingjasondata("ValidLogin", "password");
    private final String Fname = DataUtil.GetStingjasondata("InformationData", "FirstName") + "-" + Utilities.GetTimeStamp();
    private final String Lname = DataUtil.GetStingjasondata("InformationData", "LastName") + "-" + Utilities.GetTimeStamp();
    private final String ZipCode = DataUtil.GetStingjasondata("InformationData", "ZipCode");


    public T04_InformationTC() throws FileNotFoundException {
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
    public void FillInformations() throws IOException {
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
                .ContinueButton();

        Assert.assertTrue(new P04_InformationPage(getdriver()).
                CheckURL(DataUtil.GetPropData("environment", "CheckOut_URL")));

    }

    @Test
    public void CancelButton() throws IOException {
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
                .CancelButton();

        Assert.assertTrue(new P04_InformationPage(getdriver()).
                CheckURL(DataUtil.GetPropData("environment", "CartPage_URL")));

    }


    @AfterMethod
    public void quit() {
        Quit();
    }
}



