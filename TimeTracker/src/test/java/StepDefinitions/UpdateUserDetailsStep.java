package StepDefinitions;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import common.TestBase;
import common.Utils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
import pages.RegisterPage;

public class UpdateUserDetailsStep extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	RegisterPage registerPage;
	ProfilePage profilePage;
	private static boolean createNewUser = false;

	private static String timestamp = null;
	private static String currentUserName = "empty";
	private static String currentPassword = "empty";

	public UpdateUserDetailsStep() {
		super();
	}

	@Before(order = 0)
	public void browserSetup() {
		initialization();
		registerPage = new RegisterPage();
	}

	@After
	public void tearDown(Scenario scenario) throws Exception {
		if (scenario.isFailed()) {
			System.out.println("Screenshot location : " + Utils.takeScreenshotAtEndOfTest());
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		driver.close();
		driver.quit();
	}
	
	@After
	public void updateLastSuccessfulUserInfo() {
		prop.setProperty("lastSuccessfulUserName", currentUserName);
		prop.setProperty("lastSuccessfulPassword", currentPassword);
	}

	@Given("I create a new user")
	public void i_create_a_new_user() throws InterruptedException {

		if (!createNewUser) {
			timestamp = Utils.getRandomName();
			System.out.println(timestamp);
			currentUserName = prop.getProperty("userNamePrefix") + "-" + timestamp;
			currentPassword = prop.getProperty("userPassword");
			Thread.sleep(2000);
			homePage = registerPage.RegisterNewUser(timestamp);
			homePage.clickLogout();
			createNewUser = true;
		}
	}

	@Given("I login to the timetracker as an existing user")
	public void i_login_to_the_timetracker_as_an_existing_user() throws InterruptedException {

		loginPage = new LoginPage();
		// The website prevents creating users back to back - hence use last successful user info
		if (currentUserName.contains("empty"))
			currentUserName = prop.getProperty("lastSuccessfulUserName");
		if (currentPassword.contains("empty"))
			currentPassword = prop.getProperty("lastSuccessfulPassword");
		homePage = loginPage.login(currentUserName, currentPassword);
	}

	@And("I update the Password of the user")
	public void i_update_the_password_of_the_user() throws InterruptedException {

		profilePage = homePage.clickOnProfileLink();
		currentPassword = prop.getProperty("newUserPassword");
		homePage = profilePage.ChangePassword();
	}

	@And("I update the Login of the user")
	public void i_update_the_login_of_the_user() {

		profilePage = homePage.clickOnProfileLink();
		currentUserName = prop.getProperty("newUserNamePrefix") + "-" + timestamp;
		homePage = profilePage.UpdateLogin(timestamp);
	}

	@And("I logout from the timetracker")
	public void i_logout_from_the_timetracker() throws InterruptedException {
		homePage.clickLogout();
	}

	@When("I login with the updated timetracker user details")
	public void i_login_with_the_updated_timetracker_user_details() {
		loginPage = new LoginPage();
		homePage = loginPage.login(currentUserName, currentPassword);
	}

	@Then("I am able to login successfully")
	public void i_am_able_to_login_successfully() throws InterruptedException {
		assertTrue(homePage.userDropdownDisplayed());		
	}

}
