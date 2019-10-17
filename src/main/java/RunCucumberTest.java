import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import java.io.File;

import static com.cucumber.listener.Reporter.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"stepdefs"},
       // tags = {"@testing"},
        features = {"src/test/resources/features"},
        plugin={"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"})

public class RunCucumberTest {

    @AfterClass
    public static void writeExtentReport() {
        loadXMLConfig(new File("resources//extent-config.xml"));
    }

}

