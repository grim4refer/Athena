package com.athena.services.discord.listeners;

import java.util.List;

import com.athena.services.discord.BotConfig;
import com.athena.services.discord.DiscordBot;
import com.athena.services.discord.server.Channels.ChannelType;

import sx.blah.discord.handle.obj.*;

/**
 * 
 * @author Hennessy
 *
 * Jul 25, 2019 - 12:14:45 PM
 */
public class BotConfigListner extends BotConfig {

	private DiscordBot bot;
	
	private List<IRole> permissions;

	public BotConfigListner(DiscordBot bot) {
		this.bot = bot;
	}

	@Override
	public void onLaunch() {
		bot.sendChannelNotification(ChannelType.GENERAL, "World: 1 is now ONLINE!");
		permissions = bot.getClient().getRoles();
	}

	@Override
	public void onExit() {
		bot.sendChannelNotification(ChannelType.GENERAL, "World: 1 is now OFFLINE!");
	}
	
	@Override
	public long messageCoolDownDuration() {
		return System.currentTimeMillis() + 3000;
	}

	public List<IRole> getPermissions() {
		return permissions;
	}

	public IGuild getDiscordSettings() {
		return bot.getClient().getGuilds().get(0);
	}

}
