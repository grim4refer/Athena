package com.athena.model.input.impl;

import com.athena.model.container.impl.Bank.BankSearchAttributes;
import com.athena.model.input.Input;
import com.athena.world.entity.impl.player.Player;

public class EnterSyntaxToBankSearchFor extends Input {

	@Override
	public void handleSyntax(Player player, String syntax) {
		boolean searchingBank = player.isBanking() && player.getBankSearchingAttribtues().isSearchingBank();
		if(searchingBank)
			BankSearchAttributes.beginSearch(player, syntax);
	}
}
