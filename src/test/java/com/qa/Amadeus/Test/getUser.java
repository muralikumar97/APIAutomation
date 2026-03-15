package com.qa.Amadeus.Test;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qa.Amadeus.Base.BaseTest;
import com.qa.Amadeus.Utils.XMLPathVal;
import com.qa.Amadeus.client.RestClient;
import com.qa.Amadeus.Constants.APIHTTPStatusCodes;
import io.restassured.response.Response; 

public class getUser extends BaseTest {   

    @BeforeMethod                      
    public void getUserTmethod() {
        rc = new RestClient(prop, baseUri);
    }

    @Test(priority = 3)
    public void getUserTest() {
        rc.getRequest(REST_ENDPOINT, true, true)
          .then().log().all()
          .assertThat().statusCode(200);
    }

    @Test(priority = 2)
    public void singleUserTest() {
        rc.getRequest(REST_ENDPOINT+ 8291348, true, true)
          .then().log().all()
          .assertThat().statusCode(200)
          .and().body("id", equalTo(8291348))
          .and().body("name", equalTo("Aparna"));
    }

    @Test(priority = 1)
    public void getUserwithQueryParamTest() {
        Map<String, String> map = new HashMap<>();
        map.put("status", "inactive");
        map.put("gender", "male");

        rc.getRequest("/public/v2/users/", map, true, true)
          .then().log().all()
          .assertThat().statusCode(200);
    }
    
  
    
    @Test(priority = 4)
    public void getAllUsersTest() {

        Response res = rc.getRequest(REST_ENDPOINT, true, true);

        Assert.assertEquals(res.getStatusCode(), 200);

        List<String> nameList = res.jsonPath().getList("name");

        System.out.println("The JSON Name List is " + nameList);

        Assert.assertTrue(nameList.contains("Aparna"));
    }

    	
	}
 