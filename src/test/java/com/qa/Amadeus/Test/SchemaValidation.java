package com.qa.Amadeus.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

import com.qa.Amadeus.Base.BaseTest;
import com.qa.Amadeus.Pojo.UserPOJO;
import com.qa.Amadeus.Utils.StringUtils;

public class SchemaValidation extends BaseTest {

    @Test
    public void checkSchemaValidation() {

   
        UserPOJO up = new UserPOJO("Murali", StringUtils.generateEmailId(), "male", "active");

       
        rc.postRequest(REST_ENDPOINT, "json", up, true, true)
                .then().log().all()
                .assertThat().statusCode(201)
                .and()
                .body(matchesJsonSchemaInClasspath("schemas/createUserSchema.json"));
    }
}