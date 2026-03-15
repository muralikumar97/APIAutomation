package com.qa.Amadeus.Test;

import java.util.HashMap;


import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.Amadeus.Base.BaseTest;
import com.qa.Amadeus.Constants.APIHTTPStatusCodes;
import com.qa.Amadeus.client.RestClient;

import static org.hamcrest.Matchers.*;


public class FlightsAPITest extends BaseTest {

	public String token;

	@Parameters({ "grantType", "clientId", "clientSecret" })  
	@BeforeMethod
	public void flightsAPISetup(String grantType, String clientId, String clientSecret) {
		
		rc = new RestClient(prop, baseUri);
		
		this.token = rc.getOAuth2Token(AMADEUS_OAUTH2_TOKEN_ENDPOINT,grantType,clientId,clientSecret
		);
	}
	
	@Test
	public void getFlightsTest() {
		
		RestClient rcflights = new RestClient(prop, baseUri);
		
		Map<String, String> headersMap = new HashMap<>();
		headersMap.put("Authorization", "Bearer " + token);
		
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("origin", "PAR");
		queryMap.put("maxPrice", "200");
		
		rcflights.getRequest(
				AMADEUS_FLIGHTDETAILS_ENDPOINT,
				headersMap,
				queryMap,
				true,
				false
		)
		.then().log().all()
		.assertThat().statusCode(APIHTTPStatusCodes.APIHttpStatusCodes.OK_200.getCode())
		.and().body("data[1].price.total", equalTo("150.00"));

	}
}


