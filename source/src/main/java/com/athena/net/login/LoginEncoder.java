package com.athena.net.login;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.athena.net.packet.Packet;

/**
 * Handles login encoding requests
 * @author Gabriel Hannason
 */
public class LoginEncoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext context, Channel channel, Object message) {
		return ((Packet)message).getBuffer();
	}
}
