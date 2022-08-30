package ca.amazon.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import ca.amazon.basepackage.BaseTest;

public class BaseUtilsMethods extends BaseTest implements IRetryAnalyzer{
	int count = 0;
	int maxcount = 1;
	
	public boolean retry(ITestResult result){
		if(count<maxcount) {
			count++;
			return true;
		}
		else {
			return false;
		}
	}

	
}
