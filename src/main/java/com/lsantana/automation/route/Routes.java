package com.lsantana.automation.route;

public class Routes {

	private static String championRotation = "/lol/platform/v3/champion-rotations";
	private static String championMastery = "/lol/champion-mastery/v4/champion-masteries/by-summoner";
	private static String status = "/lol/status/v3/shard-data";
	private static String summoner = "/lol/summoner/v4/summoners";

	public static String championRotation() {
		return championRotation;
	}

	public static String getChampionRotation() {
		return championRotation;
	}

	public static void setChampionRotation(String championRotation) {
		Routes.championRotation = championRotation;
	}

	public static String getChampionMastery() {
		return championMastery;
	}

	public static void setChampionMastery(String championMastery) {
		Routes.championMastery = championMastery;
	}

	public static String getStatus() {
		return status;
	}

	public static void setStatus(String status) {
		Routes.status = status;
	}

	public static String getSummoner() {
		return summoner;
	}

	public static void setSummoner(String summoner) {
		Routes.summoner = summoner;
	}

}
