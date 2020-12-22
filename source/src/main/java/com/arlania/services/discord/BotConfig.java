package com.arlania.services.discord;

import java.util.List;

//import sx.blah.discord.handle.obj.*;

/**
 * @author Teek
 *
 * Creation Date: Aug 23, 2018 - 4:38:24 PM
 */
public abstract class BotConfig {
	
	public abstract void onLaunch();
	
	public abstract void onExit();
	
	public abstract long messageCoolDownDuration();
	
	//public abstract IGuild getDiscordSettings();
	
	//public abstract List<IRole> getPermissions();
	
	public enum Permission {
		
		OWNER(15),
		MANAGER(14),
		ADMINISTRATOR(13),
		MODERATOR(12),
		SERVER_SUPPORT(11),
		VETERAN(10),
		BUG_TESTER(9),
		EVERYONE(0);
		
		public int permissionId;
		
		Permission(int permissionId) {
			this.permissionId = permissionId;
		}
	}

}
