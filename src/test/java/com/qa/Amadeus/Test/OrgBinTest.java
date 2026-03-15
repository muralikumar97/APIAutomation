package com.qa.Amadeus.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Amadeus.Base.BaseTest;
import com.qa.Amadeus.Constants.APIHTTPStatusCodes;
import com.qa.Amadeus.client.RestClient;

public class OrgBinTest extends BaseTest {

    RestClient binRc;

    @BeforeMethod
    public void BinSetup() {
        
        binRc = new RestClient(prop, prop.getProperty("httpBinBaseUrl"));
    }

    @Test
    public void getBinOrgData() {
    	
        binRc.getRequest(BINTEST_ENDPOINT, true, false)
                .then().log().all()
                .assertThat().statusCode(APIHTTPStatusCodes.APIHttpStatusCodes.OK_200.getCode());
    }
}

