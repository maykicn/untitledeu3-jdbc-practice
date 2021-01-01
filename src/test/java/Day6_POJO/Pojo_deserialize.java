package Day6_POJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class Pojo_deserialize {


    @Test
    public void oneSpartanPojo(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("http://3.80.189.73:8000/api/spartans/{id}");
        assertEquals(response.statusCode(),200);


        //JSON to POJO --> de serialize  to java custom  class
        //JSON to Our Spartan class object

        Spartan spartan11 = response.body().as(Spartan.class);
        System.out.println(spartan11);

        System.out.println("spartan11.getName() = " + spartan11.getName());
        System.out.println("spartan11.getPhone() = " + spartan11.getPhone());

        //assertion
        assertEquals(spartan11.getId(),11);
        assertEquals(spartan11.getName(),"Lionel Pepsi");


    }


    @Test
    public void regionToPojo(){

        Response response = when().get("http://3.80.189.73:1000/ords/hr/regions");

        assertEquals(response.statusCode(),200);

        //JSON to POJO(regions class)

        Regions regions=response.body().as(Regions.class);

        System.out.println(regions.getHasMore());

        System.out.println("regions.getItems().get(0).getRegionName() = " + regions.getItems().get(0).getRegionName());

        List<Item> items = regions.getItems();
        System.out.println("items.get(1).getRegionId() = " + items.get(1).getRegionId());




    }






















}
