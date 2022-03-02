package common;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    @BeforeSuite(alwaysRun = true)
    public static void setupBaseUrl() {
        RestAssured.baseURI = "http://localhost:3004";
    }


}




