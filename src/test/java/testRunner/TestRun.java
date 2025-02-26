package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
		(
		features = "src/test/resources/features",
		glue= {"stepdefinations"},
		monochrome = true,
		plugin= {"pretty", "html:target/HtmlReports/report.html","junit:target/JunitReports/report.xml"}
		)
public class TestRun
{

}