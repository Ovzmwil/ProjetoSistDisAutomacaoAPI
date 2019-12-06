package com.lsantana.automation.data;

public class SummonerDTO {

	private String name = "Leandro Santana";
	private Integer profileIconId = 4279;
	private String puuid = "BLE7GWSVWUmnOaXNitNMe9QK31qetpg8QY1eFJrGhMZVuNSch6exgzkAw1cav4pUTVbuIMYHVE20pg";
	private Integer summonerLevel = 226;
	private String accountId = "3NNOdolSNjdZ6jEwfacehosjqUaCFBX8nPn7CnkjuppnwnQ";
	private String id = "-zMLNtsvNjIEeYA_4arvvL9tw93mGB8bhnyImmut3RjxoA";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProfileIconId() {
		return profileIconId;
	}

	public void setProfileIconId(Integer profileIconId) {
		this.profileIconId = profileIconId;
	}

	public String getPuuid() {
		return puuid;
	}

	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}

	public Integer getSummonerLevel() {
		return summonerLevel;
	}

	public void setSummonerLevel(Integer summonerLevel) {
		this.summonerLevel = summonerLevel;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
