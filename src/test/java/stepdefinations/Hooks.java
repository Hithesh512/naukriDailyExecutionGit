package stepdefinations;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.messages.types.Attachment;

public class Hooks {
	public static WebDriver driver;
	@Before
    public void setUp() {
		System.out.println("openining the browser");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       
    }

    @After
    public void tearDown() throws IOException, InterruptedException {
    	Thread.sleep(2000);
    	TakesScreenshot ts = (TakesScreenshot)driver;
    	File ScreenShot = ts.getScreenshotAs(OutputType.FILE);
    	File Destination = new File(System.getProperty("user.dir")+"/screenshot/screenshot.jpg");
		Files.copy(ScreenShot, Destination);
    	System.out.println("Closing the browser");
        driver.close();
    }
}
