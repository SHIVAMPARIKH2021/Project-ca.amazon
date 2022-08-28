package ca.amazon.utilities;


import java.io.File;
import java.lang.Class;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IAnnotationTransformer;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ca.amazon.basepackage.BaseTest;

public class TestUtils extends BaseTest implements IReporter,IAnnotationTransformer{
	private ExtentReports extent;
	  
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        extent = new ExtentReports(outputDirectory + File.separator + "Extent.html", true);
 
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
    
 //Using a method "transform()" by implementing "IAnnotationTransformer" interface to create a listner in pom.xml 
 //so that all the @Test don't have to use @Test(retryAnalyzer=ca.amazon.utilities.BaseUtilsMethods.class)-- i.e packagename.classname.class for IRetryAnalyzer

    public void transform(ITestAnnotation annotation,Class testClass,Constructor testConstructor,Method testMethod) {
    	annotation.setRetryAnalyzer(BaseUtilsMethods.class);
    }

}
