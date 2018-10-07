package Selenium.First;

import java.io.File;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Selenium.Utils.SeleniumUtils;

public class Dp extends SeleniumUtils {
	
	int i =0;
	@BeforeSuite
	public void presetup(){
		extent = new ExtentReports (System.getProperty("user.dir") +"/Extent-Report/Report.html", true);
		extent
        .addSystemInfo("Host Name", "SoftwareTestingMaterial")
        .addSystemInfo("Environment", "Win10 Chrome")
        .addSystemInfo("Client", "LearnNShine");
		extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
	}
	@DataProvider
	public Object[][] input(){
		return new Object[][]{{"vinothkumar","vinoth","Invalid User Id or Password !!!"
			},
			{"arun","arun","Invalid User Id or Password !!!"
		},{"arun","","Please enter Password"
			},{"","arun","Please enter User Id"
			},{"","","Please enter User Id"
			}};
	}

	
	@Test(dataProvider="input")
	public void testNegative(String username, String password, String message){
		logger = extent.startTest("Verify error " + message); 
		i++;
		launchBrowser("chrome", "http://login.globalglaze.in/");
		enterTextBox("xpath", "//input[@placeholder='USER ID/NEW ID']", username);
		enterTextBox("xpath", "//input[@placeholder='PASSWORD']",password);
		takeScreenshot("screen1"+ i + ".png");
		clickElement("xpath","//input[@value='LOGIN']");
		verifyAlertText(message);
		
		logger.log(LogStatus.PASS, "Verified error " + message , logger.addScreenCapture(System.getProperty("user.dir") +"/Screenshot/screen1" +i +".png"));
		closeBrowser();
		extent.endTest(logger);
		
	}
	
	@AfterSuite
	public void cleanup(){
		extent.flush();
		extent.close();
	}
}
