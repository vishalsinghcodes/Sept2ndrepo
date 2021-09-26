package AbcPackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.spi.FileTypeDetector;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Passtest {
	ExtentReports extent;
	
	@BeforeTest
	public void Extentconfig() {
		String path = System.getProperty("user.dir")+"\\Extent reports";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("InGithubtest");
		reporter.config().setDocumentTitle("For Git test results");
		
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Vishal Singh");
		
		
	}
	
	@Test
	public void Test1() throws IOException, InterruptedException {
		ExtentTest test = extent.createTest("Google page open test");
		System.setProperty("webdriver.chrome.driver","F:\\Udemy Selenium\\WebDrivers\\Chrome\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.get("http://www.google.com");
		
		String projectpath = System.getProperty("user.dir");
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(src, new File(projectpath+"\\ScreenShots"));
		
		
		Thread.sleep(2000L);
		test.pass("Its a Pass");
		extent.flush();
		
		Thread.sleep(5000L);
		driver.close();	
	}

}
