package com.qa.Amadeus.Utils;

import java.util.List;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XMLPathVal {

    private static String getXMLResAsString(Response res) {
    	
        return res.getBody().asString();
    }

    public <T> T readXML(Response res, String path) {

        String response = getXMLResAsString(res);
        
        XmlPath xmlPath = new XmlPath(response);

        return xmlPath.get(path);
    }

    public <T> List<T> readXMLList(Response res, String path) {

        String response = getXMLResAsString(res);
        
        XmlPath xmlPath = new XmlPath(response);

        return xmlPath.getList(path);
    }

}