package com.athena.services.discord.server.commands;

import com.athena.services.discord.DiscordBot;
import com.athena.services.discord.listeners.CommandListener;
import com.athena.services.discord.server.Channels.ChannelType;
import com.athena.world.World;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author Hennessy
 *
 *         Jul 25, 2019 - 12:59:40 PM
 */
public class CommandsManager extends CommandListener {

	public CommandsManager(DiscordBot bot, String commandPrefix) {
		super(bot, commandPrefix);
	}

	@Override
	public void handleCommand(IMessage message, String command, String[] cmd, ChannelType type) {

		String channelId = type.channelId;

		switch (cmd[0]) {

		case "website":
		case "forums": {
			bot.sendChannelMessage(channelId, "***Website***"+ " http://www.yanille.net");
			break;
		}

		case "store":
		case "donate": {
			bot.sendChannelMessage(channelId, "***Store***: http://www.yanille.net/store/");
			break;
		}

		case "vote": {
			bot.sendChannelMessage(channelId, "***Voting***: http://www.yanille.net/vote/");
			break;
		}

		case "players": {
			bot.sendChannelMessage(channelId,
					"There are currently " + (int) (World.getPlayers().size() * 2) + " players online!");
			return;
		}
	}
	}
}