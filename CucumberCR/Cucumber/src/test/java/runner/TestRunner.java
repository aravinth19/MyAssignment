package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "./src/test/java/features", glue = "steps", tags = "@CRs")
public class TestRunner extends AbstractTestNGCucumberTests {
}
