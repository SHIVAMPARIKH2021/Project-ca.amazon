package ca.amazon.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ca.amazon.basepackage.BaseTest;

public class FailTestUtils extends BaseTest implements ITestListener{

	// How to take ScreenShot of Failed Test Case?

	ExtentReports extent;
	ExtentTest test;

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {

		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = "S://" + date + screenshotName + ".png"; // because of 'date' variable every time screenshot will have new name
		FileUtils.copyFile(srcfile, new File(path));
		return path;
	}

	// Overrieded method from 'ITestListner'
public void onTestFailure(ITestResult result) {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/DefectExtent.html", true);
		extent.addSystemInfo("Environment", "Testing Environment");
		extent.addSystemInfo("Screenshot Storage", "S: drive");

		test = extent.startTest(result.getMethod().getMethodName());

		if (result.getStatus() == ITestResult.FAILURE) {

			test.log(LogStatus.FAIL, "Test Case " + result.getName() + " is Failed"); // to add 'name of test case'which is failed in extent report

			test.log(LogStatus.FAIL, "Test Case Failure Reason is :" + result.getThrowable()); // to add error as well as Exceptions in extent report

			String screenshotpathforfail = null;
			try {
				screenshotpathforfail = getScreenshot(driver, result.getName() + "_failed");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 'getScreenshot()'mehtod is created above

			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotpathforfail)); // Capturing the screenshot of
																					// failed test Case

		}

		else if (result.getStatus() == ITestResult.SKIP)
		{

			test.log(LogStatus.SKIP, "Test Case "+result.getName()+" is Skipped");
			test.log(LogStatus.SKIP, "Test Case Skipped Reason is :" + result.getThrowable());
			/*
			 * String screenshotpathforskip =
			 * getScreenshot(driver,result.getName()+"_skip"); test.log(LogStatus.SKIP,
			 * test.addScreenCapture(screenshotpathforskip));
			 */
		}
		
		else if (result.getStatus()==ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "Test Case "+result.getName()+" is Passed");
		}
		extent.endTest(test); // To end the Test and prepare html report
		extent.flush();
		extent.close();

	}

}
