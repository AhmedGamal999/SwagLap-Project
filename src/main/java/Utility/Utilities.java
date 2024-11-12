package Utility;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utilities {
    private static final String Screen_path = "test-outputs/Screenshots/";

    public static void ClickElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();

    }

    public static void SendData(WebDriver driver, By locator, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    public static String GetData(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public static WebDriverWait GeneralWait(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public static void Scroll(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).
                executeScript("arguments[0].scrollIntoView();", BytoWebElement(driver, locator));
    }

    public static WebElement BytoWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static void TakeScreenShot(WebDriver driver, String name) {
        try {


            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dist = new File(Screen_path + name + "-" + GetTimeStamp() + ".png");
            FileUtils.copyFile(src, dist);
            Allure.addAttachment(name, Files.newInputStream(Path.of(dist.getPath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String GetTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }

    public static void SelectDeopDown(WebDriver driver, By locator, String options) {
        new Select(BytoWebElement(driver, locator)).deselectByVisibleText(options);

    }

    public static int GenerateRandomnumber(int num) {
        return new Random().nextInt(num) + 1;

    }

    public static Set grnerateUniqueRandomNumbers(int NumbersNeeded, int Range) {
        Set<Integer> RandomValue = new HashSet<>();
        while (RandomValue.size() < NumbersNeeded) {
            int random = GenerateRandomnumber(Range);
            RandomValue.add(random);
        }
        return RandomValue;
    }

}
