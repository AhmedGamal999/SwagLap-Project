package Listeners;

import Utility.LogUtility;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Itest implements ITestListener {
    public void onTestSuccess(ITestResult result) {

        LogUtility.Info("TestCase" + result.getName() + "Passed");
    }

    public void onTestSkipped(ITestResult result) {
        LogUtility.Info("TestCase " + result.getName() + "Skipped");
    }

    public void onTestStart(ITestResult result) {
        LogUtility.Info("TestCase " + result.getName() + "Started");
    }

}
