package com.qa.Amadeus.Test;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Amadeus.Base.BaseTest;
import com.qa.Amadeus.Pojo.UserPOJO;
import com.qa.Amadeus.Utils.ExcelUtils;
import com.qa.Amadeus.Utils.StringUtils;
import com.qa.Amadeus.client.RestClient;

public class createUser extends BaseTest {

    @DataProvider
    public Object[][] getUserData() {
        // Excel Sheet1 must have exactly 3 columns: name | gender | status
        Object[][] obj = ExcelUtils.getTestDataFromSheet("Sheet1");
        return obj;
    }

    @Test(dataProvider = "getUserData")
    public void createUserTest(String name, String gender, String status) {

        // 1 - Create User
    	
        UserPOJO user = new UserPOJO(name, StringUtils.generateEmailId(), gender, status);

        Integer createdUserId = rc.postRequest(REST_ENDPOINT, "json", user, true, true)
                .then().log().all()
                .assertThat().statusCode(201)
                .and()
                .body("name", equalTo(name))
                .and()
                .extract().path("id");

        System.out.println("Created User ID is: " + createdUserId);

        // 2 - Fetch and verify the created user
        
        rc.getRequest(REST_ENDPOINT + createdUserId, true, true)
                .then().log().all()
                .assertThat().statusCode(201)
                .and()
                .body("id", equalTo(createdUserId));
    }
}