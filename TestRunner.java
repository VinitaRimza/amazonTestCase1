package com.amazon.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.amazon.steps", 
    		dryRun = false,
    		plugin = {
    				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" }
   // plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}