package com.lsantana.automation.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

//import static io.restassured.RestAssured.given;
//import static org.junit.Assert.assertThat;
//import static org.hamcrest.Matchers.equalTo;

import com.lsantana.automation.report.GenerateReport;
import com.lsantana.automation.util.ConfigFileReader;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetTests extends GenerateReport {
	
	private static String apiKey;
	Response response;
	ConfigFileReader reader;
	
	@BeforeClass
	public void setUp() {
		reader = new ConfigFileReader("configs/config.properties");
		RestAssured.baseURI = reader.getPropertyByKey("base.uri");
		apiKey = reader.getPropertyByKey("api.key");
	}
	
	@AfterMethod
	public void finish() {
		logTimeout(response.getTime());
	}

}
