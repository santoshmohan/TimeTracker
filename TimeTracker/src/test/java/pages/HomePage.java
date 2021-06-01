package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(css=".top-menu-table a[href='logout.php']")
	WebElement link_logout;	
	
	@FindBy(css=".top-menu-table a[href='profile_edit.php']")
	WebElement link_profile;	
	
	@FindBy(id= "user")
	WebElement user_drop_down;
	
	@FindBy(xpath="//div[@class='user-details']")
	WebElement userDetails;
	
	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	public boolean userDropdownDisplayed() {
		return user_drop_down.isDisplayed();
	}
	
	public String getLoggedInUserName(){
		System.out.println("�ser details : " + userDetails.getText());
		return userDetails.getText();
	}
	
	public ProfilePage clickOnProfileLink(){
		link_profile.click();
		return new ProfilePage();
	}
	
	
	public void clickLogout() {
		link_logout.click();
	}
	
	public boolean verifyLogoutLink() {
		return link_logout.isDisplayed();
	}
}
