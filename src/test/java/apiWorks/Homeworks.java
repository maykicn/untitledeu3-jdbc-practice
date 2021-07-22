package apiWorks;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ExcelUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;



public class Homeworks {

    @BeforeClass
    public void beforeclass(){

        baseURI="http://52.86.179.144:8000";

        }


    @DataProvider
    public Object [][] userData(){

        ExcelUtil spartanMacro = new ExcelUtil("src/test/resources/MOCK_DATA.xlsx","data");

        String [][] dataArray =spartanMacro.getDataArrayWithoutFirstRow();

        return dataArray;
    }

   //(dataProvider = "userData")
   //String name,String gender, String phone
    @Test
    public void homework1(){

        ExcelUtil spartanMacro = new ExcelUtil("src/test/resources/MOCK_DATA.xlsx","data");

        System.out.println("spartanMacro.getCellData(1,2) = " + spartanMacro.getCellData(1, 2));


        /*System.out.println("phone = " + phone);
        //long phoneLong=Long.parseLong(phone);
        //System.out.println("phoneLong = " + phoneLong);
        Map<String,Object> spartan= new HashMap<>();
        spartan.put("name",name);
        spartan.put("gender",gender);*/
        //spartan.put("phone",phoneLong);


        /*Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartan)
                .when().post("/api/spartans");


        assertEquals(response.statusCode(),201);*/



    }








}
