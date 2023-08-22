package com.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
monochrome = true,
	    features = "src/features/facebook.feature", // Path to your feature files
	    glue = "cucumber",   // Package containing step definitions
	    tags = "@web",          // Tags to filter scenarios
	    plugin = {"pretty", "html:src/test-output/HTMLReports/cucumber-reports"} // Specify reporting formats
	)
public class CucumberRunner{

}
