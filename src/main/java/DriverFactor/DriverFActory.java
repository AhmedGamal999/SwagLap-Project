package DriverFactor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFActory {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void SetupDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "edge":
                driverThreadLocal.set(new EdgeDriver());
                break;
            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driverThreadLocal.set(new ChromeDriver(options));
        }


    }

    public static WebDriver getdriver() {

        return driverThreadLocal.get();
    }

    public static void Quit() {
        getdriver().quit();
        driverThreadLocal.remove();
    }
}
