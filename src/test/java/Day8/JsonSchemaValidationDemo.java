package Day8;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class JsonSchemaValidationDemo {
    @BeforeClass
    public void beforeClass(){ baseURI= ConfigurationReader.get("spartan_api_url");}


    @Test
    public void JsonSchemaValidationForSpartan(){

        given().accept(ContentType.JSON)
                .and()
                .pathParam("id",11)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema"));


    }

}
