package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.TestBase;

public class ProfilePage extends TestBase {
	
	@FindBy(id="name")
	@CacheLookup
	WebElement txt_name;
	
	@FindBy(id="login")
	@CacheLookup
	WebElement txt_login;
	
	@FindBy(id="password1")
	@CacheLookup
	WebElement txt_password;
	
	@FindBy(id="password2")
	@CacheLookup
	WebElement txt_confirm_password;

	@FindBy(id="btn_save")
	@CacheLookup
	WebElement btn_save;
	
	@FindBy(xpath="//div[@class='page-title'][contains(text(), 'Profile')]")
	WebElement txt_profile_header;
	
	public ProfilePage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyProfilePageDisplayed() {
		return txt_profile_header.isDisplayed();
	}
	
	public HomePage ChangePassword() {
		
		String password = prop.getProperty("newUserPassword");		
		System.out.println("New Password: " + password );

		txt_password.sendKeys(password);
		txt_confirm_password.sendKeys(password);
		btn_save.click();
		return new HomePage();
	}
	
	public HomePage UpdateLogin(String timestamp) {
		
		String newLoginDetails = prop.getProperty("newUserNamePrefix") + "-" + timestamp;		
		System.out.println("New login: " + newLoginDetails );

		txt_login.clear();
		txt_login.sendKeys(newLoginDetails);
		btn_save.click();
		return new HomePage();
	}
	
	public HomePage UpdateName(String timestamp) {
		
		String newLoginDetails = prop.getProperty("newUserNamePrefix") + "-" + timestamp;
		System.out.println("New name: " + newLoginDetails );

		txt_name.clear();
		txt_name.sendKeys(newLoginDetails);
		btn_save.click();
		return new HomePage();
	}
}
