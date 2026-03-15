package com.qa.Amadeus.Test;

import java.util.List;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Amadeus.Base.BaseTest;
import com.qa.Amadeus.Utils.JSONPathValUtil;

import com.qa.Amadeus.Constants.APIConstants;
import com.qa.Amadeus.Constants.APIHTTPStatusCodes;
import com.qa.Amadeus.client.RestClient;

import io.restassured.response.Response;

public class GetProdTest extends BaseTest {
	
	@BeforeMethod
	public void getProdDtails() {
		
        rc = new RestClient(prop, baseUri);
    }
	
	@Test
	public void getProdDetailsTest() {
		
		Response response = rc.getRequest(FAKESTORE_PRODUCTS_ENDPOINT, true, false);
		
         int statuscode = response.getStatusCode();
         
        // Assert.assertEquals(statuscode, 200);
         
         Assert.assertEquals(statuscode, APIHTTPStatusCodes.APIHttpStatusCodes.OK_200.getCode());
         
         JSONPathValUtil jsonutil = new JSONPathValUtil();  
       
         List<Double> listofrates = jsonutil.readJsonList(response, "$..[?(@.rate>3)].rate");
         
         Assert.assertTrue(listofrates.size() > 0);
         
         
         Assert.assertEquals(listofrates.get(1), 4.1d);
         
         System.out.println("Rate List is : " + listofrates);
 		
    
 		Assert.assertTrue(listofrates.contains(4.1d));
	}
}
