package org.yanille.teleport.hierarchy;


import org.yanille.Client;


public class TeleportChatbox extends HierarchyChatboxDrawing {

	public static void open(int index) {
		if(Client.openInterfaceID != -1) {
			Client.chatboxDrawing = null;
			return;
		}
	}

	@Override
	public HierarchyOption[] getOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	}
