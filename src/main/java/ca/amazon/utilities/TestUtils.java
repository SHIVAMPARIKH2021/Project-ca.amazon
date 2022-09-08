package ca.amazon.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ca.amazon.basepackage.BaseTest;

public class TestUtils extends BaseTest implements IReporter, IAnnotationTransformer, ITestListener {
	private ExtentReports extent;
	private static ExtentTest test;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		extent = new ExtentReports(outputDirectory + File.separator + "Extent.html", true);
		extent.addSystemInfo("Environment", "Testing Environment");
		extent.addSystemInfo("Screenshot Storage", "S: Drive");
		
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}

		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {

		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());

//Using the Mehtods "setStrartedTime() and setEndTime()"

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));
		
				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				String message = "Test " + status.toString().toLowerCase() + "ed";

				if (result.getThrowable() != null)
					message = result.getThrowable().getMessage();

				test.log(status, message);

				extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	// Using a method "transform()" by implementing "IAnnotationTransformer"
	// interface to create a listner in pom.xml
	// so that all the @Test don't have to use
	// @Test(retryAnalyzer=ca.amazon.utilities.BaseUtilsMethods.class)-- i.e
	// packagename.classname.class for IRetryAnalyzer

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(BaseUtilsMethods.class);
	}

	// How to take ScreenShot of Failed Test Case?

		public void setExtentReport() {
			extent = new ExtentReports(System.getProperty("user.dir") + "test-output/Extent.html", true);
			extent.addSystemInfo("Environment", "Testing Environment");
			extent.addSystemInfo("Screenshot Storage", "S: Drive");
		}

	
		public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {

			String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = "S://" + date + screenshotName + ".png"; // because of 'date' variable every time screenshot will
																	// have new name
			FileUtils.copyFile(srcfile, new File(path));
			return path;
		}
		
 //Overrieded method from 'ITestListner'
		
		public void onTestFailure(ITestResult result) {

			extent = new ExtentReports(System.getProperty("user.dir") + "test-output/Extent.html", true);
			test = extent.startTest(result.getMethod().getMethodName());
			if (result.getStatus() == ITestResult.FAILURE) {

				test.log(LogStatus.FAIL, "Test Case " + result.getName() + " is Failed"); // to add 'name of test case'which is failed in extent report
																								
																								
				test.log(LogStatus.FAIL, "Test Case Failure Reason is :" + "/n" + result.getThrowable()); // to add error as well as Exceptions in extent report
																												
				String screenshotpathforfail = null;
				try {
					screenshotpathforfail = getScreenshot(driver, result.getName() + "_failed");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 'getScreenshot()'mehtod is created above

				test.log(LogStatus.FAIL, test.addScreenCapture(screenshotpathforfail)); // Capturing the screenshot of failed test Case
																						
			}

			else if (result.getStatus() == ITestResult.SKIP) {

				test.log(LogStatus.SKIP, "Test Case " + result.getName() + "is Skipped");
				test.log(LogStatus.SKIP, "Test Case Skipped Reason is :" + "/n" + result.getThrowable());
				/*
				 * String screenshotpathforskip =
				 * getScreenshot(driver,result.getName()+"_skip"); test.log(LogStatus.SKIP,
				 * test.addScreenCapture(screenshotpathforskip));
				 */
			}

			extent.endTest(test); // To end the Test and prepare html report
			extent.flush();
			extent.close();

		}

}
