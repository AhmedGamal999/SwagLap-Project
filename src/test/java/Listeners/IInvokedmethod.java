package Listeners;

import Utility.Utilities;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import static DriverFactor.DriverFActory.getdriver;

public class IInvokedmethod implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test case" + testResult.getName() + " Failure");
            Utilities.TakeScreenShot(getdriver(), testResult.getName());

        }
    }
}
