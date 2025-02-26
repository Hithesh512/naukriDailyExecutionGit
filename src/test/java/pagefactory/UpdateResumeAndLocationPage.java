package pagefactory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdateResumeAndLocationPage {

	@FindBy(xpath = "//a[.='Login']")
	private WebElement LoginMainBtn;
	
	@FindBy(xpath = "(//input)[1]")
	private WebElement UsernameTxt;
	
	@FindBy(xpath = "(//input)[2]")
	private WebElement PasswordTxt;
	
	@FindBy(xpath = "//button[.='Login']")
	private WebElement LoginBtn;
	
	@FindBy(xpath = "//div[contains(@class,'drawer')]//div[contains(@class,'bar1')]")
	private WebElement ProfileDrawerBar;
	
	@FindBy(xpath = "(//a[contains(.,'Update Profile')])[1]")
	private WebElement UpdateProfileLink;
	
	@FindBy(xpath = "//input[contains(@value,'resume')]")
	private WebElement UpdateResumeBtn;
	
	@FindBy(xpath = "//em[contains(@class,'icon edit')]")
	private WebElement EditIcon;

	@FindBy(xpath = "//p[.='Success']")
	private WebElement SuccessLbl;
	
	@FindBy(xpath = "//input[contains(@id,'locationSugg')]")
	private WebElement LocationTxt;
	
	@FindBy(xpath = "//div[@tabindex='-1']")
	private WebElement FirstlocationOption;
	
	@FindBy(xpath = "//button[.='Save']")
	private WebElement SaveButton;
	
	@FindBy(xpath = "//span[@name='Location']")
	private WebElement LocationLbl;

	
	public UpdateResumeAndLocationPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		
	}
	
	public void clickLoginMain()
	{
		LoginMainBtn.click();
	}
	
	public void setUsername(String username)
	{
		UsernameTxt.sendKeys(username);
	}
	
	public void setPassword(String password)
	{
		PasswordTxt.sendKeys(password);
	}
	
	public void clickLogin()
	{
		LoginBtn.click();
	}
	
	public void clickProfileDrawer()
	{
		ProfileDrawerBar.click();
	}
	
	public void clickUpdateProfile()
	{
		UpdateProfileLink.click();
	}
	
	public void uploadResume() throws AWTException, InterruptedException, EncryptedDocumentException, IOException
	{
		
		UpdateResumeBtn.click();
		
	
		
	    Robot robot = new Robot(); 
	    FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\Location.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String path = wb.getSheet("Sheet1").getRow(0).getCell(0).toString();
	    StringSelection filePath = new StringSelection(System.getProperty("user.dir")+path);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, filePath);
	    
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);

	    Thread.sleep(1000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
		
	}
	
	public void isSuccessMessageDisplayed()
	{
		SuccessLbl.isDisplayed();
	}
	
	public void clickEdit(WebDriver driver)
	{	
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditIcon);
	}
	
	public void clearLocationText()
	{
		LocationTxt.clear();
	}
	
	public void setLocationText() throws InterruptedException 
	{
		Thread.sleep(1000);
		LocationTxt.sendKeys("Bengaluru");
	}
	
	public void selectFirstLocationOption()
	{
		FirstlocationOption.click();
	}
	
	public void clickSave()
	{
		SaveButton.click();
	}
	
	public void isLocationModified()
	{
		String ActualTxt = LocationLbl.getText();
		String ExpectedTxt = "Bengaluru";
		Assertions.assertTrue(ActualTxt.contains(ExpectedTxt));
	}
}
