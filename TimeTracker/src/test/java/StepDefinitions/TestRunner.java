package StepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features",
				glue = {"StepDefinitions"},
				monochrome = true,
				plugin={ "pretty", "html:target/HtmlReports",						
						"junit:target/JUnitReports/cucumber.xml",
						"json:target/cucumber.json"
						}
				)
public class TestRunner {

}
