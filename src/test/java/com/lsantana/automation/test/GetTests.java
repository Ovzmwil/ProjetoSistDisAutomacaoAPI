package com.lsantana.automation.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.lsantana.automation.report.GenerateReport;
import com.lsantana.automation.route.Routes;
import com.lsantana.automation.util.ConfigFileReader;
import com.lsantana.automation.util.RetryAnalyzer;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetTests extends GenerateReport {
	
	private static String apiKey;
	Response response;
	ConfigFileReader reader;
	
	Map<String, Object> paramsMap = new HashMap<String, Object>();
	
	@BeforeClass
	public void setUp() {
		reader = new ConfigFileReader("configs/config.properties");
		RestAssured.baseURI = reader.getPropertyByKey("base.uri");
		apiKey = reader.getPropertyByKey("api.key");
	}
	
	@AfterMethod
	public void finish() {
		if(response != null)
			logTimeout(response.getTime());
		paramsMap.clear();
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void championRotationTest() {
		paramsMap.put("api_key", apiKey);
		
		response = given().formParams(paramsMap).when().get(Routes.championRotation());
		
		assertThat(response.getStatusCode(), equalTo(200));
	}
}
