package Tests;

import DriverFactor.DriverFActory;
import Listeners.IInvokedmethod;
import Listeners.Itest;
import Pages.P01_LoginPage;
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
public class T01_LoginTest {

    private final String usrename = DataUtil.GetStingjasondata("ValidLogin", "username");
    private final String password = DataUtil.GetStingjasondata("ValidLogin", "password");

    public T01_LoginTest() throws FileNotFoundException {
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
    public void valid_Login() throws IOException {
        new P01_LoginPage(getdriver())
                .enter_username(usrename)
                .enter_password(password)
                .ClickLogin();
        Assert.assertTrue(new P01_LoginPage(getdriver()).assertlogin(DataUtil.GetPropData("environment", "Home_URL")));

    }

    @AfterMethod
    public void quit() {
        Quit();
    }
}
