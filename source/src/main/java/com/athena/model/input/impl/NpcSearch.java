package com.athena.model.input.impl;


import com.athena.model.input.Input;
import com.athena.world.content.DropLookup;
import com.athena.world.entity.impl.player.Player;

public class NpcSearch extends Input {

    @Override
    public void handleSyntax(Player player, final String syntax) {
        if(syntax.length() < 3) {
            player.sendMessage("You must type atleast 3 letters");
            return;
        }
        if(!player.getClickDelay().elapsed(600)) {
            return;
        }
        player.getClickDelay().reset();
        DropLookup.search(player, syntax, DropLookup.SearchPolicy.NPC);
    }
}
