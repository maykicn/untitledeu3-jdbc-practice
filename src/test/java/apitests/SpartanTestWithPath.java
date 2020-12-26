package apitests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithPath {

    @BeforeClass
        public void beforeClass(){

            baseURI="http://3.80.189.73:8000";
        }


         /*
   Given accept type is json
   And path param id is 10
   When user sends a get request to "api/spartans/{id}"
   Then status code is 200
   And content-type is "application/json;charset=UTF-8"
   And response payload values match the following:
           "id": 9,
            "name": "Florrie",
            "gender": "Female",
            "phone": 1702025787
    */

        @Test
        public void getOneSpartan_path(){
            Response response = given().accept(ContentType.JSON)
                    .and().pathParam("id", 9)
                    .when().get("/api/spartans/{id}");

            assertEquals(response.statusCode(),200);
            assertEquals(response.contentType(),"application/json;charset=UTF-8");

            //print each key value in the json body/payload
            System.out.println(response.path("id").toString());
            System.out.println(response.path("name").toString());
            System.out.println(response.path("gender").toString());
            System.out.println(response.path("phone").toString());

            //save json key values

            int id =response.path("id");
            String name=response.path("name");
            String gender=response.path("gender");
            int phone=response.path("phone");

            //assert one by one
            assertEquals(id,9);
            assertEquals(name,"Florrie");
            assertEquals(gender,"Female");
            assertEquals(phone,1702025787);














        }
    
    
    
    
    
    
}





