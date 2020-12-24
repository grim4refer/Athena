package com.athena.services.discord.listeners;

import com.athena.services.discord.DiscordBot;
import com.athena.services.discord.server.Channels.ChannelType;

import sx.blah.discord.handle.obj.IMessage;

public abstract class CommandListener {
	
	protected DiscordBot bot;
	
	private String commandPrefix;
	
	public CommandListener(DiscordBot bot, String commandPrefix) {
		this.bot = bot;
		this.commandPrefix = commandPrefix;
	}
	
	public abstract void handleCommand(IMessage message, String command, String[] cmd, ChannelType type);
	
	public String getCommandPrefix() {
		return commandPrefix;
	}
	

}
