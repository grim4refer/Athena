package com.arlania.services.discord.server.user;

/**
 * 
 * @author Hennessy
 *
 * Jul 25, 2019 - 12:45:11 PM
 */
public class Information {
	
	private String username;
	
	private String hash;
	
	private long userId;
	
	private String discordName;
	
	public String getUsername() {
		return username;
	}
	
	public String getHash() {
		return hash;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public String getDiscordName() {
		return discordName;
	}
	
	public Information(String username, String hash, long userId) {
		this.username = username;
		this.hash = hash;
		this.userId = userId;
		this.discordName = username.toLowerCase()+"#"+hash;
	}
}