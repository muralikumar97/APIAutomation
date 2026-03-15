package com.qa.Amadeus.configuration;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.FrameWorkExceptions.APIFrameWorkExceptions;

public class ConfigManger {
	
	private Properties prop;
	
	private FileInputStream fp;

	public Properties initProperties() {
		prop = new Properties();
		
		String EnvName = System.getProperty("env");
		
		System.out.println("Test cases are running on " + EnvName + " environment");
		
		
		try {
			
	//		fp= new FileInputStream("src/main/resources/ConfigProps/config.properties");
			
			if(EnvName==null) {
				
				System.out.println("No environment is passed, hence running it on default environment- stage");
				
				fp= new FileInputStream("src/main/resources/config/config.properties");
			}
			else {
				switch (EnvName.toLowerCase().trim()) {
				
				case "qa":
					
					System.out.println("Running test cases on" + EnvName + " "+ "environment");
					
					fp= new FileInputStream("src/main/resources/config/config-qa.properties");
					
					break;
					
				case "dev":
					
					System.out.println("Running test cases on" + EnvName +" "+ "environment");
					
					fp= new FileInputStream("src/main/resources/config/config-dev.properties");
					break;
					
				case "stage":
					
					System.out.println("Running test cases on" + EnvName +" "+ "environment");
					
					fp= new FileInputStream("src/main/resources/config/config-stage.properties");
					break;
					
				case "uat":
					
					System.out.println("Running test cases" + EnvName +" "+ "environment");
					
					fp= new FileInputStream("src/main/resources/config/config-uat.properties");
					break;
					
				default:
					
					throw new APIFrameWorkExceptions("Invalid Environemt is passed " + EnvName);
				}
			}
			
			prop.load(fp);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return prop;
}
	
}
