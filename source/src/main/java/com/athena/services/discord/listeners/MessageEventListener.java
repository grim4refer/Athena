package com.athena.services.discord.listeners;

import java.util.*;

import com.athena.services.discord.DiscordBot;
import com.athena.services.discord.server.Channels;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

/**
 * 
 * @author Hennessy
 *
 * Jul 25, 2019 - 1:24:03 PM
 */
public class MessageEventListener {

	private DiscordBot bot;

	public Map<String, Long> coolDowns;

	public MessageEventListener(DiscordBot bot) {
		this.bot = bot;
		this.coolDowns = new HashMap<String, Long>();
	}

	@EventSubscriber
	public void onMessageReceivedEvent(MessageReceivedEvent event) {
		
		if(bot.getCommandListener() == null)
			return;
		
		String discordName = event.getClient().getApplicationOwner().getName()+event.getAuthor().getDiscriminator();
		
		String prefix = bot.getCommandListener().getCommandPrefix();
		
		if (coolDowns.containsKey(discordName) && coolDowns.get(discordName) > System.currentTimeMillis()) 
			return;
		
			String command = event.getMessage().getContent().replaceFirst(prefix, "").toLowerCase();
			String[] cmd = command.split(" ");
			bot.getCommandListener().handleCommand(event.getMessage(), command, cmd, Channels.getChannel(event.getChannel().getStringID()));
			coolDowns.put(discordName, bot.getSettings().messageCoolDownDuration());
		
	}

}
