package ca.amazon;

import org.testng.Assert;
import org.testng.annotations.Test;

import ca.amazon.basepackage.BaseTest;

public class DryRun extends BaseTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println("This is the Dry Running Class of the project");
	}
@Test
public void primarytest() {
	
	  String a = "Shivam"; String b = "Shiva"; if(a!=b){ Assert.assertEquals(a,b);
	  System.out.println("Dry Run is Passed"); } else {
	  System.out.println("Dry Run is Failed"); }
	 
}
}
