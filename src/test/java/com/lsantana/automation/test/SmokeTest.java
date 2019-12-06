package com.lsantana.automation.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.not;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lsantana.automation.data.SummonerDTO;
import com.lsantana.automation.report.GenerateReport;
import com.lsantana.automation.route.Routes;
import com.lsantana.automation.util.ConfigFileReader;
import com.lsantana.automation.util.RetryAnalyzer;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SmokeTest extends GenerateReport {

	private static String apiKey;
	Response response;
	ConfigFileReader reader;
	SummonerDTO summoner = new SummonerDTO();

	Map<String, Object> paramsMap = new HashMap<String, Object>();

	@BeforeClass
	public void setUp() {
		reader = new ConfigFileReader("configs/config.properties");
		RestAssured.baseURI = reader.getPropertyByKey("base.uri");
		apiKey = reader.getPropertyByKey("api.key");
		paramsMap.put("api_key", apiKey);
	}

	@AfterMethod
	public void finish() {
		if (response != null)
			afterAPITest(null, response);
	}

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void getSummonerByName() {
		response = given().formParams(paramsMap).when().get(Routes.getSummoner().concat("/by-name/Leandro Santana"));
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_OK));
		assertThat(response.jsonPath().getString("name"), equalTo(summoner.getName()));
		assertThat(response.jsonPath().getInt("profileIconId"), equalTo(summoner.getProfileIconId()));
		assertThat(response.jsonPath().getString("puuid"), equalTo(summoner.getPuuid()));
		assertThat(response.jsonPath().getInt("summonerLevel"), equalTo(summoner.getSummonerLevel()));
		assertThat(response.jsonPath().getString("accountId"), equalTo(summoner.getAccountId()));
		assertThat(response.jsonPath().getString("id"), equalTo(summoner.getId()));

		
	}

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void getSummonerByAccount() {
		response = given().formParams(paramsMap).when().get(Routes.getSummoner().concat("/by-account/").concat(summoner.getAccountId()));
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_OK));
		assertThat(response.jsonPath().getString("name"), equalTo(summoner.getName()));
		assertThat(response.jsonPath().getInt("profileIconId"), equalTo(summoner.getProfileIconId()));
		assertThat(response.jsonPath().getString("puuid"), equalTo(summoner.getPuuid()));
		assertThat(response.jsonPath().getInt("summonerLevel"), equalTo(summoner.getSummonerLevel()));
		assertThat(response.jsonPath().getString("accountId"), equalTo(summoner.getAccountId()));
		assertThat(response.jsonPath().getString("id"), equalTo(summoner.getId()));
	}

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void getSummonerByPUUID() {
		response = given().formParams(paramsMap).when().get(Routes.getSummoner().concat("/by-puuid/").concat(summoner.getPuuid()));
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_OK));
		assertThat(response.jsonPath().getString("name"), equalTo(summoner.getName()));
		assertThat(response.jsonPath().getInt("profileIconId"), equalTo(summoner.getProfileIconId()));
		assertThat(response.jsonPath().getString("puuid"), equalTo(summoner.getPuuid()));
		assertThat(response.jsonPath().getInt("summonerLevel"), equalTo(summoner.getSummonerLevel()));
		assertThat(response.jsonPath().getString("accountId"), equalTo(summoner.getAccountId()));
		assertThat(response.jsonPath().getString("id"), equalTo(summoner.getId()));
	}

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void getSummonerById() {
		response = given().formParams(paramsMap).when().get(Routes.getSummoner().concat("/").concat(summoner.getId()));
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_OK));
		assertThat(response.jsonPath().getString("name"), equalTo(summoner.getName()));
		assertThat(response.jsonPath().getInt("profileIconId"), equalTo(summoner.getProfileIconId()));
		assertThat(response.jsonPath().getString("puuid"), equalTo(summoner.getPuuid()));
		assertThat(response.jsonPath().getInt("summonerLevel"), equalTo(summoner.getSummonerLevel()));
		assertThat(response.jsonPath().getString("accountId"), equalTo(summoner.getAccountId()));
		assertThat(response.jsonPath().getString("id"), equalTo(summoner.getId()));
	}

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void getSummonerInvalidAccount() {
		response = given().formParams(paramsMap).when().get(Routes.getSummoner().concat("/by-account/1234"));
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));
	}

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void getSummonerInvalidName() {
		response = given().formParams(paramsMap).when().get(Routes.getSummoner().concat("/by-name/''"));
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
	}

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void getSummonerInvalidPUUID() {
		response = given().formParams(paramsMap).when().get(Routes.getSummoner().concat("/by-puuid/0"));
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));
	}

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void getSummonerInvalidID() {
		response = given().formParams(paramsMap).when().get(Routes.getSummoner().concat("/1234"));
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void getSummonerMasteryById() {
		response = given().formParams(paramsMap).when().get(Routes.getChampionMastery().concat("/").concat(summoner.getId()));
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_OK));
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void getSummonerMasteryInvalidId() {
		response = given().formParams(paramsMap).when().get(Routes.getChampionMastery().concat("/12"));
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void getChampionRotation() {
		response = given().formParams(paramsMap).when().get(Routes.championRotation());
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_OK));
		assertThat(response.jsonPath().get("freeChampionIds"), not(equalTo(null)));
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void getStatus() {
		response = given().formParams(paramsMap).when().get(Routes.getStatus());
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_OK));
		assertThat(response.jsonPath().get("services"), not(equalTo(null)));
		assertThat(response.jsonPath().getString("name"), equalTo("Brazil"));
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void accessWithoutKey() {
		response = given().when().get(Routes.getStatus());
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_UNAUTHORIZED));
	}
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void accessInvalidRoute() {
		response = given().formParams(paramsMap).when().get("/champions");
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.SC_FORBIDDEN));
	}
	
}
