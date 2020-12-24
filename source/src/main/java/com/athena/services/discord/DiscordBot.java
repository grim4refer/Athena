package com.athena.services.discord;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import com.athena.services.discord.BotConfig.Permission;
import com.athena.services.discord.listeners.BotConfigListner;
import com.athena.services.discord.listeners.CommandListener;
import com.athena.services.discord.listeners.MessageEventListener;
import com.athena.services.discord.server.Channels.ChannelType;
import com.athena.services.discord.server.commands.CommandsManager;
import com.athena.services.discord.server.user.Information;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.ActivityType;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.StatusType;
import sx.blah.discord.util.*;

/**
 * 
 * @author Hennessy
 *
 *         Jul 25, 2019 - 1:47:07 PM
 */

public class DiscordBot {

private IDiscordClient client;

	private CommandListener commandListener;

	private BotConfig botSettings;

	private Map<Integer, Information> discordUsers;

	private static long lastDiscordNameSave;
	
	public int savedUserCount;

	public BotConfig getConfig() {
		return botSettings;
	}

	public DiscordBot(String clientToken) {
		this.botSettings = new BotConfigListner(this);
		this.discordUsers = new HashMap<Integer, Information>();
		run(clientToken);
	}

	public BotConfig getSettings() {
		return botSettings;
	}

	public void run(String clientToken) {
		if (clientToken == null)
			return;
		ClientBuilder builder = new ClientBuilder();
		builder.withToken(clientToken);

		try {
			
			builder.setMaxReconnectAttempts(1);
			builder.setMaxMessageCacheCount(0);
			builder.setPresence(StatusType.ONLINE, ActivityType.PLAYING, "Test");
			client = builder.login();
			client.getDispatcher().registerListener(new MessageEventListener(this));
			registerCommandListener(new CommandsManager(this, "::"));
			updateDiscordList();
		} catch (DiscordException e) {
			System.out.println("hi");
			//e.printStackTrace();
		}
	}

	public boolean hasPermission(MessageReceivedEvent event, Permission permissions) {
		return event.getAuthor().hasRole(getConfig().getPermissions().get(permissions.permissionId));
	}

	public void process() {

		if (lastDiscordNameSave < System.currentTimeMillis()) {
			updateDiscordList();

		}
	}

	private void updateDiscordList() {
		CompletableFuture.runAsync(() -> {
		if (client == null || client.getUsers() == null)
			return;

		int currentUsers = client.getUsers().size();

		if (currentUsers == savedUserCount)
			return;

		savedUserCount = currentUsers;

		System.out.println("Updating discord stored userlist.. total members: " + currentUsers);

		discordUsers = new HashMap<Integer, Information>();

		Information info = null;

		for (int i = 0; i < client.getUsers().size(); i++) {

			info = new Information(client.getUsers().get(i).getName(), client.getUsers().get(i).getDiscriminator(),
					client.getUsers().get(i).getLongID());

			if (client.getUsers().get(i) != null) {
				discordUsers.put(i, info);
			}
		}
		lastDiscordNameSave = System.currentTimeMillis() + (60_000 * 10);
		});
	}

	public boolean userInDiscord(String name) {
		for (int i = 0; i < discordUsers.size(); i++) {
			if (discordUsers.get(i).getDiscordName().toLowerCase().equalsIgnoreCase(name.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public void privateMessage(String name, String message) {
		CompletableFuture.runAsync(() -> {
		try {

			Information info = null;

			for (int i = 0; i < discordUsers.size(); i++) {
				if (name.toLowerCase().equalsIgnoreCase(discordUsers.get(i).getDiscordName()))
					info = discordUsers.get(i);
			}

			if (info != null)
				client.getOrCreatePMChannel(client.getUserByID(info.getUserId())).sendMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		});
	}
	
	public void deleteMessage(IMessage message) {
		try {
			message.delete();
		} catch (MissingPermissionsException | RateLimitException | DiscordException e) {
			e.printStackTrace();
		}
	}

	public void sendChannelMessage(String channelId, String message) {
		CompletableFuture.runAsync(() -> {
		try {
			client.getChannelByID(Long.parseLong(channelId)).sendMessage(message);
		} catch (MissingPermissionsException | DiscordException e) {
			e.printStackTrace();
		}
		});
	}

	public void sendChannelNotification(ChannelType type, String message) {
		CompletableFuture.runAsync(() -> {
		if (message == null)
			return;
		try {
			client.getChannelByID(Long.parseLong(type.channelId)).sendMessage("```" + message + "```");
		} catch (MissingPermissionsException | DiscordException e) {
			e.printStackTrace();
		}
		});
	}

	public void sendUnFormattedChannelNotification(ChannelType type, String message) {
		CompletableFuture.runAsync(() -> {
		if (message == null)
			return;

		try {
			client.getChannelByID(Long.parseLong(type.channelId)).sendMessage(message);
		} catch (MissingPermissionsException | RateLimitException | DiscordException e) {
			e.printStackTrace();
		}
		});
	}

	public IDiscordClient getClient() {
		return client;
	}

	public CommandListener getCommandListener() {
		return commandListener;
	}

	public void registerCommandListener(CommandListener listener) {
		this.commandListener = listener;
	}

}