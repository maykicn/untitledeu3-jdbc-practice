package apitests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithPath {

    @BeforeClass
        public void beforeClass(){

            baseURI="http://52.86.179.144:8000";
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
            assertEquals(response.contentType(),"application/json");

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
    
    @Test

    public void getAllApartanWithPath(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        assertEquals(response.statusCode(),200);
        assertEquals(response.getHeader("Content-Type"),"application/json;charset=UTF-8");

        int firstId=response.path("id[0]");
        System.out.println("firstId = " + firstId);

        String firstName=response.path("name[0]");
        System.out.println("firstName = " + firstName);

        String lastFirstName=response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);

        int lastId=response.path("id[-1]");
        System.out.println("lastId = " + lastId);

        //print name of all spartans

        List<String> names = response.path("name");
        System.out.println("names = " + names);
        System.out.println("names.size() = " + names.size());

        List<String> phones = response.path("phone");
        for (String phone : phones) {
            System.out.println(phone);
        }

    }
    
    
    
    
}





