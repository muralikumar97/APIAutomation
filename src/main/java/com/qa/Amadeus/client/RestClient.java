package com.qa.Amadeus.client;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Properties;

import com.qa.Amadeus.Constants.APIConstants;

import com.qa.FrameWorkExceptions.APIFrameWorkExceptions;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	
//    private static final String BASE_URI="https://gorest.co.in";
//	
//	private static final String BEARER_TOKEN="Bearer d49f9f7c6cc32784ecd348925d68a8c2b23c1b26f96a8e0417799ae21b694abe";
	
	private RequestSpecBuilder specBuilder;
	
	private Properties prop;
	private String baseURI;	
	private boolean isAuthorizationAdded=false;

	
//	static {
//	
//		specBuilder=new RequestSpecBuilder();
//	}
	
	public RestClient(Properties prop, String baseURI) {
		
		specBuilder=new RequestSpecBuilder();
		
		this.baseURI=baseURI;
		this.prop=prop;
		
	}
	
	// ********************************************************* Common Reusable Methods *************************************************************************
	
//	public void addAuthorization() {
//		
//		if(!isAuthorizationAdded) {
//			
//			specBuilder.addHeader("Authorization", prop.getProperty("tokenId"));
//			
//			isAuthorizationAdded=true;
//		}
//	}
	
	public void addAuthorization() {
	    specBuilder.addHeader("Authorization", prop.getProperty("tokenId"));
	}
	
	
	public void setRequestContentType(String contentType) {
		
		
		System.out.println("The content type used is "+contentType);
		
		switch(contentType.toLowerCase().trim()) {
		
		case "json":
			
			specBuilder.setContentType(ContentType.JSON);
			
			break;
			
			
			
			case "xml":
				
				specBuilder.setContentType(ContentType.XML);
				
				break;
				
				
				
			case "html":
				
				specBuilder.setContentType(ContentType.HTML);
					
				break;
				
				
				
				case "text":
					
					specBuilder.setContentType(ContentType.TEXT);
					
					break;
					
				case "multipart":
					
					specBuilder.setContentType(ContentType.MULTIPART);
					
					break;
					
					
					default:
						
						System.out.println("Please provide a valid content type");
						
						throw new APIFrameWorkExceptions("An unsupported content type was provided "+contentType);
				
		}
		
		
	}
	

		// ************************************************ Request Specifications **********************************************************
	
	
	public RequestSpecification requestSpec(boolean includeAuth) {
		
		specBuilder = new RequestSpecBuilder();

		specBuilder.setBaseUri(baseURI);
		
		if(includeAuth) {
		
		addAuthorization();
	}
		return specBuilder.build();
}
	
	
	
	
/**
 * Method to add headers dynamically
 * @param headersMap
 * @return
 */
	
	public RequestSpecification requestSpec(Map<String, String> headersMap, boolean includeAuth) {
		
		specBuilder = new RequestSpecBuilder();

		specBuilder.setBaseUri(baseURI);
		
		if(includeAuth) {
		
		addAuthorization();
		
		}
		
		if(headersMap!=null) {
			
		specBuilder.addHeaders(headersMap);
		
	}
		return specBuilder.build();
}
	
	
	
	
	/**
	 * Method to add headers and query params dynamically
	 * @param headersMap
	 * @param queryParams
	 * @return
	 */
	
	public RequestSpecification requestSpec(Map<String, String> headersMap, Map<String, String> queryParams, boolean includeAuth) {

		specBuilder = new RequestSpecBuilder();
		specBuilder.setBaseUri(baseURI);
		
		if(includeAuth) {
			
			addAuthorization();
		
		}
	
		if(headersMap!=null) {
			
		specBuilder.addHeaders(headersMap);
		
		if(queryParams!=null) {
			
			specBuilder.addQueryParams(queryParams);
		}
		
	}
		return specBuilder.build();
}
	
	
	// *****************************************  Post Request Specifications ***********************************************
	
	/**
	 * Method to add request body
	 * @param requestBody
	 * @param contentType
	 * @return
	 */
	
	public RequestSpecification requestSpec(Object requestBody, String contentType, boolean includeAuth) {

		
		specBuilder = new RequestSpecBuilder();
		specBuilder.setBaseUri(baseURI);
		
		if(includeAuth) {
		
		addAuthorization();
		}
		setRequestContentType(contentType);
		
		if(requestBody!=null) {
		
		specBuilder.setBody(requestBody);
		
		}
		
		return specBuilder.build();
}
	
	
	/**
	 * For adding request body and headers dynamically
	 * @param requestBody
	 * @param contentType
	 * @param headersMap
	 * @return
	 */
	
	
	public RequestSpecification requestSpec(Object requestBody, String contentType, Map<String, String> headersMap, boolean includeAuth) {

		
		specBuilder = new RequestSpecBuilder();
		specBuilder.setBaseUri(baseURI);
		
		if(includeAuth) {
		
		addAuthorization();
		
		}
		
		setRequestContentType(contentType);
		
		if(requestBody!=null) {
		
		specBuilder.setBody(requestBody);
		
		}
		
		if(headersMap!=null) {
			
			specBuilder.addHeaders(headersMap);
			
			}
		return specBuilder.build();
}
	
	
	/**
	 * For adding request body, headers and query params dynamically
	 * @param requestBody
	 * @param contentType
	 * @param headersMap
	 * @param queryParams
	 * @return
	 */
	
	public RequestSpecification requestSpec(Object requestBody, String contentType, Map<String, String> headersMap, Map<String, String> queryParams, boolean includeAuth) {

		
		specBuilder = new RequestSpecBuilder();
		specBuilder.setBaseUri(baseURI);
		
		if(includeAuth) {
		
		addAuthorization();
		}
		
		setRequestContentType(contentType);
		
		if(requestBody!=null) {
		
		specBuilder.setBody(requestBody);
		
		}
		
		if(headersMap!=null) {
			
			specBuilder.addHeaders(headersMap);
			
			}
		
		if(queryParams!=null) {
			
			specBuilder.addQueryParams(queryParams);
		}
		
		return specBuilder.build();
}
	
	
	
	
	// *****************************************  Common GET HTTP methods called in test cases ***********************************************
	
	/**
	 * GET Request Method with Service URL
	 * @param serviceURL
	 * @param log
	 * @return
	 */
	
 public Response getRequest(String serviceURL, boolean log, boolean includeAuth) {

	 if(log) {
		 
	 Response res=RestAssured.given().log().all()
			 
	                      .spec(requestSpec(includeAuth))
	                       .when()
	                       .get(serviceURL);
	 return res; 
	 }
	 else {
		 
		 Response res=RestAssured.given()
				 .spec(requestSpec(includeAuth))
				  .when()
				  .get(serviceURL);
		 
		 return res; 
	 } 
 }
 
 /**
	 * GET Request Method with Headers
	 * @param serviceURL
	 * @param headersMap
	 * @param log
	 * @return
	 */
 
 public Response getRequest(String serviceURL, Map<String, String> headersMap, boolean log, boolean includeAuth) {

	    if (log) {
	        Response res = RestAssured.given().log().all()
	                .spec(requestSpec(headersMap, includeAuth))
	                .when()
	                .get(serviceURL);
	        return res;
	    } else {
	        Response res = RestAssured.given()
	                .spec(requestSpec(headersMap,includeAuth))
	                .when()
	                .get(serviceURL);
	        return res;
	    }
	}

 /**
	 * GET Request Method with Headers and Query Params
	 * @param serviceURL
	 * @param headersMap
	 * @param queryParams
	 * @param log
	 * @return
	 */
 
	 
 public Response getRequest(String serviceURL, Map<String, String> headersMap, Map<String, String> queryParams, boolean log, boolean includeAuth) {

	    if (log) {
	        Response res = RestAssured.given().log().all()
	                .spec(requestSpec(headersMap, queryParams, includeAuth))
	                .when()
	                .get(serviceURL);
	        return res;
	    } else {
	        Response res = RestAssured.given()
	                .spec(requestSpec(headersMap, queryParams, includeAuth))
	                .when()
	                .get(serviceURL);
	        return res;
	    }
	}
 
 
//*****************************************  Common POST HTTP methods called in test cases ***********************************************

 
 /**
	 * POST Request Method
	 * @param serviceURL
	 * @param contentType
	 * @param requestBody
	 * @param log
	 * @return
	 */
 
 public Response postRequest(String serviceURL, String contentType, Object requestBody, boolean log, boolean includeAuth) {

	 if(log) {
	 Response res=RestAssured.given().log().all()
	                     .spec(requestSpec(requestBody, contentType,includeAuth))
	                       .when()
	                       .post(serviceURL);
	 return res; 
	 }
	 else {
		 
		 Response res=RestAssured.given()
				 .spec(requestSpec(requestBody, contentType, includeAuth))
				  .when()
				  .post(serviceURL);
		 
		 return res; 
	 } 
 }
 
 
 
 
 /**
	 * POST Request Method with Headers
	 * @param serviceURL
	 * @param contentType
	 * @param requestBody
	 * @param headersMap
	 * @param log
	 * @return
	 */
 
 
 public Response postRequest(String serviceURL, String contentType, Object requestBody,Map<String, String> headersMap, boolean log, boolean includeAuth) {

	 if(log) {
	 Response res=RestAssured.given().log().all()
	                     .spec(requestSpec(requestBody, contentType,headersMap, includeAuth))
	                       .when()
	                       .post(serviceURL);
	 return res; 
	 }
	 else {
		 
		 Response res=RestAssured.given()
				 .spec(requestSpec(requestBody, contentType,headersMap, includeAuth))
				  .when()
				  .post(serviceURL);
		 
		 return res; 
	 } 
 }
 
 
 
 /**
	 * POST Request Method with Headers and Query Params
	 * @param serviceURL
	 * @param contentType
	 * @param requestBody
	 * @param headersMap
	 * @param queryParams
	 * @param log
	 * @return
	 */
 
 public Response postRequest(String serviceURL, String contentType, Object requestBody,Map<String, String> headersMap,Map<String, String> queryParams, boolean log, boolean includeAuth) {

	 if(log) {
	 Response res=RestAssured.given().log().all()
	                     .spec(requestSpec(requestBody, contentType,headersMap,queryParams, includeAuth))
	                       .when()
	                       .post(serviceURL);
	 return res; 
	 }
	 else {
		 
		 Response res=RestAssured.given()
				 .spec(requestSpec(requestBody, contentType,headersMap,queryParams, includeAuth))
				  .when()
				  .post(serviceURL);
		 
		 return res; 
	 } 
 }
 
 
 
//*****************************************  Common PUT HTTP methods called in test cases ***********************************************
	
	
 /**
	 * PUT Request Method
	 * @param serviceURL
	 * @param contentType
	 * @param requestBody
	 * @param log
	 * @return
	 */
 
 
 public Response putRequest(String serviceURL, String contentType, Object requestBody, boolean log, boolean includeAuth) {

	 if(log) {
	 Response res=RestAssured.given().log().all()
	                     .spec(requestSpec(requestBody, contentType, includeAuth))
	                       .when()
	                       .put(serviceURL);
	 return res; 
	 }
	 else {
		 
		 Response res=RestAssured.given()
				 .spec(requestSpec(requestBody, contentType, includeAuth))
				  .when()
				  .put(serviceURL);
		 
		 return res; 
	 } 
 }
 
 
 /**
	 * PUT Request Method with Headers
	 * @param serviceURL
	 * @param contentType
	 * @param requestBody
	 * @param headersMap
	 * @param log
	 * @return
	 */
 
 
 public Response putRequest(String serviceURL, String contentType, Object requestBody,Map<String, String> headersMap, boolean log, boolean includeAuth) {

	 if(log) {
	 Response res=RestAssured.given().log().all()
	                     .spec(requestSpec(requestBody, contentType,headersMap, includeAuth))
	                       .when()
	                       .put(serviceURL);
	 return res; 
	 }
	 else {
		 
		 Response res=RestAssured.given()
				 .spec(requestSpec(requestBody, contentType,headersMap, includeAuth))
				  .when()
				  .put(serviceURL);
		 
		 return res; 
	 } 
 }
 
 
 /**
	 * PUT Request Method with Headers and Query Params
	 * @param serviceURL
	 * @param contentType
	 * @param requestBody
	 * @param headersMap
	 * @param queryParams
	 * @param log
	 * @return
	 */
 
 public Response putRequest(String serviceURL, String contentType, Object requestBody,Map<String, String> headersMap,Map<String, String> queryParams, boolean log, boolean includeAuth) {

	 if(log) {
	 Response res=RestAssured.given().log().all()
	                     .spec(requestSpec(requestBody, contentType,headersMap, queryParams, includeAuth))
	                       .when()
	                       .put(serviceURL);
	 return res; 
	 }
	 else {
		 
		 Response res=RestAssured.given()
				 .spec(requestSpec(requestBody, contentType,headersMap, queryParams, includeAuth))
				  .when()
				  .put(serviceURL);
		 
		 return res; 
	 } 
 }
 
 
 
 
//*****************************************  Common PATCH HTTP methods called in test cases ***********************************************
 
 
 /**
	 * PATCH Request Method
	 * @param serviceURL
	 * @param contentType
	 * @param requestBody
	 * @param log
	 * @return
	 */
 
 public Response patchRequest(String serviceURL, String contentType, Object requestBody, boolean log, boolean includeAuth) {

	 if(log) {
	 Response res=RestAssured.given().log().all()
	                     .spec(requestSpec(requestBody, contentType, includeAuth))
	                       .when()
	                       .patch(serviceURL);
	 return res; 
	 }
	 else {
		 
		 Response res=RestAssured.given()
				 .spec(requestSpec(requestBody, contentType, includeAuth))
				  .when()
				  .patch(serviceURL);
		 
		 return res; 
	 } 
 }
 
 /**
	 * PATCH Request Method with Headers
	 * @param serviceURL
	 * @param contentType
	 * @param requestBody
	 * @param headersMap
	 * @param log
	 * @return
	 */
 
 
 public Response patchRequest(String serviceURL, String contentType, Object requestBody,Map<String, String> headersMap, boolean log, boolean includeAuth) {

	 if(log) {
	 Response res=RestAssured.given().log().all()
	                     .spec(requestSpec(requestBody, contentType,headersMap, includeAuth))
	                       .when()
	                       .patch(serviceURL);
	 return res; 
	 }
	 else {
		 
		 Response res=RestAssured.given()
				 .spec(requestSpec(requestBody, contentType,headersMap, includeAuth))
				  .when()
				  .patch(serviceURL);
		 
		 return res; 
	 } 
 }
 
 
 /**
	 * PATCH Request Method with Headers and Query Params
	 * @param serviceURL
	 * @param contentType
	 * @param requestBody
	 * @param headersMap
	 * @param queryParams
	 * @param log
	 * @return
	 */
 
 public Response patchRequest(String serviceURL, String contentType, Object requestBody,Map<String, String> headersMap,Map<String, String> queryParams, boolean log, boolean includeAuth) {

	 if(log) {
	 Response res=RestAssured.given().log().all()
	                     .spec(requestSpec(requestBody, contentType,headersMap, queryParams, includeAuth))
	                       .when()
	                       .patch(serviceURL);
	 return res; 
	 }
	 else {
		 
		 Response res=RestAssured.given()
				 .spec(requestSpec(requestBody, contentType,headersMap, queryParams, includeAuth))
				  .when()
				  .patch(serviceURL);
		 
		 return res; 
	 } 
 }
 
//*****************************************  Common DELETE HTTP methods called in test cases ***********************************************
 
 /**
	 * DELETE Request Method
	 * @param serviceURL
	 * @param log
	 * @return
	 */
 
 public Response deleteRequest(String serviceURL, boolean log, boolean includeAuth) {

	 if(log) {
	 Response res=RestAssured.given().log().all()
	                     .spec(requestSpec(includeAuth))
	                       .when()
	                       .delete(serviceURL);
	 return res; 
	 }
	 else {
		 
		 Response res=RestAssured.given()
				 .spec(requestSpec(includeAuth))
				  .when()
				  .delete(serviceURL);
		 
		 return res; 
	 } 
 }
 
 
 
 // **********************************************OAuth 2.0 Token Generation Method *******************************************************
 
 
 
 
 public String getOAuth2Token(String serviceUrl, String grantType, String clientId, String clientSecret)
 {
     RestAssured.baseURI = "https://test.api.amadeus.com";
     
     String authToken = given().log().all()
         .contentType(ContentType.URLENC)
         .formParam("grant_type", grantType)
         .formParam("client_id", clientId)
         .formParam("client_secret", clientSecret)
         .when()
         .post(serviceUrl)
         .then().log().all()
         .assertThat().statusCode(200)  
         .extract().path("access_token");

     System.out.println("OAuth 2.0 Token is : " + authToken);	
     return authToken;
 }



 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
}