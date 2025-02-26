package stepdefinations;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.*;
import pagefactory.UpdateResumeAndLocationPage;

public class UpdateResumeAndLocationSteps {
	
	WebDriver driver = Hooks.driver;
	UpdateResumeAndLocationPage u = new UpdateResumeAndLocationPage(driver);
	WebDriverWait wait = new WebDriverWait(driver,30);
	
	@Given("user navigate to the Naukri login page")
	public void user_navigate_to_the_naukri_login_page() {
		
		driver.get("https://www.naukri.com/");
	  
	}

	@When("user Click on Login button")
	public void user_click_on_login_button() 
	{
	   u.clickLoginMain();
	}

	@When("user enter valid username {string} and password {string}")
	public void user_enter_valid_username_and_password(String username, String password) 
	{
	   u.setUsername(username);
	   u.setPassword(password);
	}

	@When("user click on the login button")
	public void user_click_on_the_login_button() {
	    u.clickLogin();
	}

	@Then("I should be redirected to the homepage")
	public void i_should_be_redirected_to_the_homepage() {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.titleContains("Home"));
		String expected = "Home";
		String actual = driver.getTitle();
		Assertions.assertTrue(actual.contains(expected));
		
	}

	@When("user see Naukri Home page")
	public void user_see_Naukri_Home_page() {
		String expected = "Home";
		String actual = driver.getTitle();
		Assertions.assertTrue(actual.contains(expected));
		
	}

	@When("user click on the profile drawer Icon on Home page")
	public void user_click_on_the_profile_drawer_icon_on_home_page() {
	    u.clickProfileDrawer();
	}

	@When("user click on view and update profile Button on Home Page")
	public void user_click_on_view_and_update_profile_button_on_home_page() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(.,'Update Profile')])[1]")));
		u.clickUpdateProfile();
	}

	@Then("Page should display Profile Page")
	public void page_should_display_profile_page() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.titleContains("Profile"));
		String expected = "Profile";
		String actual = driver.getTitle();
		Assertions.assertTrue(actual.contains(expected));
	}

	@Then("user upload resume on Profile Page")
	public void user_upload_resume_on_profile_page() throws AWTException, InterruptedException, EncryptedDocumentException, IOException {
	   WebDriverWait wait = new WebDriverWait(driver,30);
	   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@value,'resume')]")));
	  
	   u.uploadResume();
	 
	}

	@Then("Page should display Success message on profile Page")
	public void page_should_display_success_message_on_profile_page() {
	   u.isSuccessMessageDisplayed();
	}

	@Then("user click on edit profile button on Profile Page")
	public void user_click_on_edit_profile_button_on_profile_page() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//em[contains(@class,'icon edit')]")));
	    u.clickEdit(driver);
	}

	@Then("User clears the current location on Basic details Form")
	public void user_clears_the_current_location_on_basic_details_form() {
	   u.clearLocationText();
	}

	@Then("User Enters the new location on Basic details Form")
	public void user_enters_the_new_location_on_basic_details_form() throws InterruptedException {
	    u.setLocationText();
	    Thread.sleep(1000);//give some time to set location and select 1st location bcz when we enter new location page gets reloaded and save new element address and selenium need some time save those address
		u.selectFirstLocationOption();
	}

	@Then("User click on the save button on Basic details Form")
	public void user_click_on_the_save_button_on_basic_details_form() {
		u.clickSave();
	}

	@Then("User verify the newly added address on profile Page")
	public void user_verify_the_newly_added_address_on_profile_page() {
	   u.isLocationModified();
	}
}
