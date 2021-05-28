package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.TestBase;

public class RegisterPage extends TestBase {
	
	@FindBy(id="group_name")
	WebElement txt_group_name;
	
	@FindBy(id="manager_name")
	WebElement txt_manager_name;
	
	@FindBy(id="manager_login")
	WebElement txt_manager_login;
	
	@FindBy(id="password1")
	WebElement txt_password;
	
	@FindBy(id="password2")
	WebElement txt_confirm_password;
	
	@FindBy(id="manager_email")
	WebElement txt_manager_email;
	
	@FindBy(id="btn_submit")
	WebElement btn_submit;
	
	@FindBy(css="a[href='register.php']")
	WebElement link_register;	
	
	@FindBy(className ="page-errors")
	WebElement page_errors;
	
	WebDriverWait wait;
	//Initializing the Page Objects:
	public RegisterPage() {
		PageFactory.initElements(driver, this);
		wait= new WebDriverWait(driver, 20);
	}
	
	public String verifyRegisterPageTitle(){
		return driver.getTitle();
	}
	
	public HomePage RegisterNewUser(String timestamp) throws InterruptedException {
		Thread.sleep(2000);		
		link_register.click();
		String userName = prop.getProperty("userNamePrefix")+ "-" + timestamp;
		String password = prop.getProperty("userPassword");		
		
		System.out.println("Login creds >> UserName: " + userName + " Password: " + password );
		txt_group_name.sendKeys(userName );
		txt_manager_name.sendKeys(userName);
		txt_manager_login.sendKeys(userName);
		txt_password.sendKeys(password);
		txt_confirm_password.sendKeys(password);
		txt_manager_email.sendKeys(userName +"@mail.com");	
		btn_submit.click();
		Thread.sleep(2000);
		return new HomePage();
	}
}
