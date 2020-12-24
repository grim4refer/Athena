package com.athena.services.discord.server;

/**
 * 
 * @author Hennessy
 *
 * Jul 25, 2019 - 1:11:47 PM
 */
public class Channels {

	public enum ChannelType { 
		
		GENERAL("563456418586296345"),


		
		;
		
		public String channelId;
		
		ChannelType(String channelId) {
			this.channelId = channelId;
		}
	}
	
	public static ChannelType getChannel(String id) {
		for (Channels.ChannelType c : Channels.ChannelType.values()) {
			if (c.channelId.equalsIgnoreCase(id))
				return c;
		}
		return null;
	}

}
