package br.com.alelo.integration;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {  
                 "br.com.alelo.integration.loan", 
                },
        features = { 
                "src/test/resources/features/Loan.feature", 
                },
        tags = {"@tests_loan"},
        plugin = { 
                "pretty", "json:target/pretty-cucumber/TEST-CucumberReportIT.json", 
                "junit:target/pretty-cucumber/TEST-CucumberReportIT.xml", 
                "de.monochromata.cucumber.report.PrettyReports:target/pretty-cucumber/" },
        stepNotifications = true,
        monochrome = true,
        strict = true
        )
public class RunCucumberIT {

}
