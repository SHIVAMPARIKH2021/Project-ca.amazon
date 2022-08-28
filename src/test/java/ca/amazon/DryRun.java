package ca.amazon;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import ca.amazon.basepackage.BaseTest;

public class DryRun extends BaseTest {
	Logger log = Logger.getLogger(DryRun.class);

@Test
public void primarytest() {
	initiate();
	
	  String a = "Shivam"; String b = "Shiva"; if(a!=b){
	  System.out.println("Dry Run is Passed"); } else {
	  System.out.println("Dry Run is Failed");
	}	 
	  quitbrowser();
}

}
