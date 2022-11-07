package ca.amazon.basepackage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class RemoteDriverFactory {
	
	private RemoteDriverFactory() {
	}
	
	private static DesiredCapabilities dc;
	private static WebDriver remotedriver;
	private static URL url;
	private static Logger log = Logger.getLogger(RemoteDriverFactory.class);
	
	private static WebDriver setRemoteWebDriver(String setBrowser) throws MalformedURLException {

		dc = new DesiredCapabilities();
		
		switch(setBrowser) {
		case "chrome":
			dc.setBrowserName("chrome");
				break;
		case "edge":
			dc.setBrowserName("edge");
				break;
		case "firefox":
			dc.setBrowserName("firefox");
		}
		Scanner scaner = new Scanner(System.in);
		System.out.print("Please Enter your Dynamic URL for RemoteWebDriver-->"+":");
		String dynamicURL = scaner.next();
		url = new URL(dynamicURL);
		remotedriver = new RemoteWebDriver(url,dc);
		return remotedriver;
	}
	
	public static WebDriver getRemoteWebDriver(String getBrowser) throws MalformedURLException {
		log.info("Connecting with Chrome Browser through RemoteWebDriver");
		return setRemoteWebDriver(getBrowser);
	}

}
