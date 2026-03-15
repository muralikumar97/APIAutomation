package com.qa.Amadeus.Utils;

import java.util.List;


import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JSONPathValUtil {
	
	private static String getJsonResAsString(Response res)
	{
		String response=res.getBody().asString();
	     return response;
	}

	public <T> T readJson(Response res, String path){
		
		String response=getJsonResAsString(res);
		return  JsonPath.read(response, path);
		
	}
	
	
	public <T> List<T> readJsonList(Response res, String path) {
		
	String response=getJsonResAsString(res);
		return  JsonPath.read(response, path);
		
	}
	
}
