package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import common.TestBase;

public class LoginPage extends TestBase {
	
	@FindBy(id="login")
	@CacheLookup
	WebElement txt_username;

	@FindBy(id="password")
	@CacheLookup
	WebElement txt_password;
	
	@FindBy(id="btn_login")
	@CacheLookup
	WebElement btn_login;

	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String userName, String password) {
		System.out.println("Attempting Login creds >> UserName: " + userName + " Password: " + password );
		txt_username.clear();
		txt_username.sendKeys(userName);
		txt_password.sendKeys(password);
		btn_login.click();
		return new HomePage();
	}		
}
