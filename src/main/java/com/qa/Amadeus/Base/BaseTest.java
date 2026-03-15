package com.qa.Amadeus.Base;

import java.util.Properties;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;
import io.qameta.allure.restassured.AllureRestAssured;
import com.qa.Amadeus.client.RestClient;
import com.qa.Amadeus.configuration.ConfigManger;
import io.restassured.RestAssured;

public class BaseTest {

   
    public final String REST_ENDPOINT= "/public/v2/users/";
    
    public final String GOREST_XMLDATA_ENDPOINT= "/public/v2/users.xml";

    
    public final String BINTEST_ENDPOINT= "/get";

  
    public final String AMADEUS_OAUTH2_TOKEN_ENDPOINT= "/v1/security/oauth2/token";
    
    public final String AMADEUS_FLIGHTDETAILS_ENDPOINT= "/v1/shopping/flight-destinations";

    
    public final String FAKESTORE_PRODUCTS_ENDPOINT = "/products";

    public Properties prop;
    public ConfigManger configMg;
    public RestClient rc;
    public String baseUri;

    @Parameters("baseURI")
    @BeforeTest
    public void setUp(@Optional String baseUri) {

        RestAssured.filters(new AllureRestAssured());

        configMg = new ConfigManger();
        prop = configMg.initProperties();

       
        if (baseUri == null || baseUri.trim().isEmpty()) {
        	
            System.out.println("No environment is passed, hence running on default environment - stage");
            
            baseUri = prop.getProperty("baseUrl");
            
        } else {
        	
            System.out.println("Test cases are running on: " + baseUri);
        }

        rc = new RestClient(prop, baseUri);
        this.baseUri = baseUri;
    }
}