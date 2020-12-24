/**
 * 
 */
package com.athena.world.content.raids.immortal;

import com.athena.model.input.Input;
import com.athena.world.entity.impl.player.Player;

/**
 * @author Hennessy
 *
 * Jul 29, 2019 - 7:12:36 PM
 */
public class ImmortalRaidsPassword extends Input {
	
	
	@SuppressWarnings("unused")
	@Override
	public void handleSyntax(Player player, String password) {
		if (password.length() < 3 || password.length() > 12) {
			player.sendMessage("The desired password is too "+(password.length() < 3 ? "short" : "long")+" pick another!");
			return;
		}
		
		boolean creation = player.getAttribute("raidsCreation", true);
		
		boolean join = player.getAttribute("raidsJoin", true);
		
		if (creation) 
			ImmortalRaidsManager.createSession(player, password);
		else 
			ImmortalRaidsManager.joinSession(player, password);
	}
}