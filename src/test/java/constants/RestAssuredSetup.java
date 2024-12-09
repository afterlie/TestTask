package constants;

import io.restassured.*;

public class RestAssuredSetup {
    public static void config() {
        RestAssured.baseURI = Constants.BASE_URL;
    }
}
