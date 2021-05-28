package common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.github.javafaker.Faker;

import io.cucumber.java.Scenario;

import org.apache.commons.io.FileUtils;

public class Utils extends TestBase{

	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 30;
	public static long TIMEOUT_IN_SECONDS = 30;
	
	public static String getDateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");		
		return formatter.format(Calendar.getInstance().getTime()) + (int)(Math.random()*(999999-9+1)+9);		
	}
	
	public static void takeScreenshotAtEndOfTest(Scenario scenario, WebDriver driver) throws IOException {
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", scenario.getName());
	}
	
	public static String takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		String fileName = currentDir + "/target/screenshots/" + System.currentTimeMillis() + ".png";
		FileUtils.copyFile(scrFile, new File(fileName));
		return fileName;
	}

	public static String getRandomName() {
		Faker faker = new Faker();				
		return faker.name().firstName() + getDateTime();
	}
}
