package com.arlania.world.content.minigames.impl;

import com.arlania.util.Rand;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

public class RaidsChest {


    private static final int[] VERY_RARE_REWARDS = {21048,21046,21047,21071,21049,21072,21070,15668,21035,21018,21019,21037,21036,21040,21043,21039,21042,21038,21041};
    private static final int[] RARE_REWARDS = {20838,18889,20821,20822,20823,20824,20825,20833,20832,20834,20831,20836,20829,20830};
    private static final int[] COMMON_REWARDS = {21082,20089,3666};
    private static final int[] ALWAYS_REWARDS = {1153,1115,1067,21012,21011,21031,21002,21030,20086,20087,20088,21021,21020,21013,20095,20096,20097,20104,20105,20103,20106,20107,20102,13196,13197,13198,13207,13206,21060,13199,3683,20255,3684,3685,3686,21061};
	
	

    public static void handleRaidsChestOpen(Player player) {
        if(player.getInventory().contains(21074)) {
            player.getInventory().delete(21074, 1);
            int rewardId;
            if (Rand.hit(1000)) {
                rewardId = Rand.ranElement(VERY_RARE_REWARDS);
                if (rewardId == 21048)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Darth Vader Mask!");
                if (rewardId == 21046)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Darth Vader Body!");
                if (rewardId == 21047)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Darth Vader Legs!");
                if (rewardId == 21071)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Darth Vader Boots!");
                if (rewardId == 21049)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Darth Vader Gloves!");
                if (rewardId == 21072)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Darth Vader Laser Sword!");
                if (rewardId == 21070)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Darth Vader Cape!");
                if (rewardId == 15668)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Huge Raids Hammer!");
                if (rewardId == 21035)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Storm Troop Mask!");
                if (rewardId == 21018)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Storm Troop Body!");
                if (rewardId == 21019)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Storm Troop Legs!");
                if (rewardId == 21037)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Storm Troop Boots!");
                if (rewardId == 21036)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Storm Troop Gloves!");
                if (rewardId == 21040)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Storm Troop Orange Gun!");
                if (rewardId == 21043)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Storm Troop Pink Gun!");
                if (rewardId == 21039)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Storm Troop Purple Gun!");
                if (rewardId == 21042)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Storm Troop Mixer Gun!");
                if (rewardId == 21038)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Storm Troop Yellow Gun!");
                if (rewardId == 21041)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@or2@ has just received @red@VERY RARE @yel@Storm Troop Blue Gun!");
            } else if (Rand.hit(400)) {
                rewardId = Rand.ranElement(RARE_REWARDS);
                if (rewardId == 18889)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@gre@ has just received @or2@RARE @red@Elite crossbow!");
                if (rewardId == 20821)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@gre@ has just received @or2@RARE @red@Hell fiend helm!");
                if (rewardId == 20822)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@gre@ has just received @or2@RARE @red@Hell fiend chestplate!");
                if (rewardId == 20823)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@gre@ has just received @or2@RARE @red@Hell fiend chainskirt!");
                if (rewardId == 20824)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@gre@ has just received @or2@RARE @red@Hell fiend gloves!");
                if (rewardId == 20825)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@gre@ has just received @or2@RARE @red@Hell fiend boots!");
                if (rewardId == 20833)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@gre@ has just received @or2@RARE @red@Infernal undead hat!");
                if (rewardId == 20832)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@gre@ has just received @or2@RARE @red@Infernal undead top!");
                if (rewardId == 20834)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@gre@ has just received @or2@RARE @red@Infernal undead bottom!");
                if (rewardId == 20831)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@gre@ has just received @or2@RARE @red@Infernal undead boots!");
                if (rewardId == 20836)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@gre@ has just received @or2@RARE @red@Infernal undead gloves!");
                if (rewardId == 20829)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@gre@ has just received @or2@RARE @red@Infernal undead wings!");
                if (rewardId == 20830)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@gre@ has just received @or2@RARE @red@Infernal undead kiteshield!");
                if (rewardId == 20838)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@gre@ has just received @or2@RARE @red@Infernal Staff of the Undead!");
            } else if (Rand.hit(50)) {
                rewardId = Rand.ranElement(COMMON_REWARDS);
                if (rewardId == 3666)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@whi@ has just received @or2@COMMON @red@Lava scythe.");
                if (rewardId == 21082)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@whi@ has just received @or2@COMMON @red@Infernal Minigun.");
                if (rewardId == 20089)
            		World.sendMessage("@mag@[Raids Chest] @blu@"+player.getUsername()+"@whi@ has just received @or2@COMMON @red@Brutal whip.");
            } else {
                rewardId = Rand.ranElement(ALWAYS_REWARDS);
            	if (rewardId == 1153)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Beginner Dragon fullhelm.");
            	if (rewardId == 1115)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Beginner Dragon platebody.");
            	if (rewardId == 1067)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Beginner Dragon platelegs.");
            	if (rewardId == 21012)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Beginner Dragon boots.");
            	if (rewardId == 21011)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Beginner Dragon gloves.");
            	if (rewardId == 21031)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Water laser sword.");
            	if (rewardId == 21002)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Red laser sword.");
            	if (rewardId == 21030)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Grass laser sword.");
            	if (rewardId == 20086)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Dark Predator full helm.");
            	if (rewardId == 20087)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Dark Predator platebody.");
            	if (rewardId == 20088)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Dark Predator platelegs.");
            	if (rewardId == 21021)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Dark Predator boots.");
            	if (rewardId == 21020)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Dark Predator gloves.");
            	if (rewardId == 21013)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Dark Predator's Scythe.");
            	if (rewardId == 20095)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @cya@Trio full helmet.");
            	if (rewardId == 20096)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @cya@Trio platebody.");
            	if (rewardId == 20097)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @cya@Trio platelegs.");
            	if (rewardId == 20104)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Deadly Dragonslayer fullhelm.");
            	if (rewardId == 20105)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Deadly Dragonslayer platebody.");
            	if (rewardId == 20103)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Deadly Dragonslayer platelegs.");
            	if (rewardId == 20106)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Deadly Dragonslayer gloves.");
            	if (rewardId == 20107)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Deadly Dragonslayer boots.");
            	if (rewardId == 20102)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Deadly Dragonslayer blade.");
            	if (rewardId == 13196)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Nature Torva full helm.");
            	if (rewardId == 13197)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Nature Torva platebody.");
            	if (rewardId == 13198)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Nature Torva platelegs.");
            	if (rewardId == 13207)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Nature gloves.");
            	if (rewardId == 13206)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Nature boots.");
            	if (rewardId == 21060)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Nature sword.");
            	if (rewardId == 13199)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Nature defender.");
            	if (rewardId == 3683)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Oblivion mask.");
            	if (rewardId == 20255)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Oblivion necklace.");
            	if (rewardId == 3684)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Oblivion platebody.");
            	if (rewardId == 3685)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Oblivion platelegs.");
            	if (rewardId == 3686)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Oblivion wings.");
            	if (rewardId == 21061)
            		World.sendMessage("@mag@[Raids Chest] @red@"+player.getUsername()+"@whi@ has just received @gre@ALWAYS @blu@Oblivion scythe.");
            }
            player.getInventory().add(rewardId, 1);
        } else {
            player.sendMessage("You need a Raids Key to open this chest.");
        }
    }
}