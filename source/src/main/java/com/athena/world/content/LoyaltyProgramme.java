package com.athena.world.content;

import com.athena.model.Flag;
import com.athena.model.Skill;
import com.athena.util.Misc;
import com.athena.world.content.Achievements.AchievementData;
import com.athena.world.entity.impl.player.Player;

public class LoyaltyProgramme {

	public enum LoyaltyTitles {

		NONE(0, 0, 0) {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				return true;
			}
		},		
		REAPER(-1, 43011, -22528, "@lre@") {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				if(!p.getUnlockedLoyaltyTitles()[1]) {
						if(!p.getInventory().contains(7118)) {
							if(sendMessage)
								p.getPacketSender().sendMessage("To unlock this title, you must have a Donator Mystery Box in your inventory.");
							return false;
						}
					}
				unlock(p, this);
				return true;
			}
		},
		ASSASSIN(-1, 43015, -22524, "@lre@") {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				if(!p.getUnlockedLoyaltyTitles()[2]) {
					if(sendMessage)
						p.getPacketSender().sendMessage("To unlock this title, you must kill Vorago.");
					return false;
				}
				return true;
			}
		},
		CORRUPT(-1, 43019, -22520, "@lre@") {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				if(!p.getUnlockedLoyaltyTitles()[3]) {
					if(!p.getInventory().contains(619)) {
						if(sendMessage)
							p.getPacketSender().sendMessage("To unlock this title, you must have a Donation Point in your inventory.");
						return false;
					}
				}
			unlock(p, this);
			return true;
		}
	},
		WARCHIEF(-1, 43023, -22516, "@blu@") {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				if(!p.getUnlockedLoyaltyTitles()[4]) {
					if(!p.getInventory().contains(5023)) {
						if(sendMessage)
							p.getPacketSender().sendMessage("To unlock this title, you must have 1Q in your inventory.");
						return false;
					}
				}
			unlock(p, this);
			return true;
		}
	},
		SKILLER(-1, 43027, -22512, "@gre@") {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				if(!p.getUnlockedLoyaltyTitles()[5]) {
					for(int i = 7; i < Skill.VALUES.size(); i++) {
						if(i == 21 || i == 24 || i == 23 || i == 18)
							continue;
						if(p.getSkillManager().getMaxLevel(i) < 99) {
							if(sendMessage)
								p.getPacketSender().sendMessage("You must be at least level 99 in every non-combat skill for this title.");
							return false;
						}
					}
				}
				unlock(p, this);
				return true;
			}
		},
		SUPREME(-1, 43031, -22508, "@dre@") {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				if(!p.getUnlockedLoyaltyTitles()[6]) {
					for(int i = 0; i <= 6; i++) {
						if(p.getSkillManager().getMaxLevel(i) < (i == 3 || i == 5 ? 990 : 99)) {
							if(sendMessage)
								p.getPacketSender().sendMessage("You must be at least level 99 in every combat skill for this title.");
							return false;
						}
					}
				}
				unlock(p, this);
				return true;
			}
		},
		MAXED(-1, 43035, -22504, "@red@") {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				if(!p.getUnlockedLoyaltyTitles()[7]) {
					for(int i = 0; i < Skill.VALUES.size(); i++) {
//						if(i == 21)
//							continue;
						if(p.getSkillManager().getMaxLevel(i) < (i == 3 || i == 5 ? 990 : 99)) {
							if(sendMessage)
								p.getPacketSender().sendMessage("You must be at least level 99 in every skill for this title.");
							return false;
						}
					}
				}
				unlock(p, this);
				return true;
			}
		},
		GODSLAYER(-1, 43039, -22500, "@whi@") {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				if(!p.getUnlockedLoyaltyTitles()[8]) {
                    for(int i = 18; i <= 18; i++) {
                        if(p.getSkillManager().getMaxLevel(i) < (i == 18 ? 99 : 99)) {
                            if(sendMessage)
                                p.getPacketSender().sendMessage("You must be at least level 99 in every combat skill for this title.");
							return false;
						}
					}
				}
				unlock(p, this);
				return true;
			}
		},
		ADMIRAL(-1, 43043, -22496, "@yel@") {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				if(!p.getUnlockedLoyaltyTitles()[9]) {
					if(p.getPointsHandler().getLoyaltyPoints() < 100000) {
						if(sendMessage)
							p.getPacketSender().sendMessage("To unlock this title, you must gain 100,000 Loyalty Points simultaneously.");
						return false;
					}
				}
				unlock(p, this);
				return true;
			}
		},
		DIVINE(-1, 43047, -22492, "@mag@") {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				if(!p.getUnlockedLoyaltyTitles()[10]) {
					if(p.getAchievementAttributes().getTotalLoyaltyPointsEarned() < 500000) {
						if(sendMessage)
							p.getPacketSender().sendMessage("To unlock this title, you must have earned 500,000 Loyalty Points in total.");
						return false;
					}
				}
				unlock(p, this);
				return true;
			}
		},
		DICER(-1, 43051, -22488, "@cya@") {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				if(!p.getUnlockedLoyaltyTitles()[11]) {
					if(!p.getInventory().contains(15084)) {
						if(sendMessage)
							p.getPacketSender().sendMessage("To unlock this title, you must have a Dice in your inventory.");
						return false;
					}
				}
				unlock(p, this);
				return true;
			}
		},


		KING(25000, 43055, -22484) {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				return true;
			}
		},
		QUEEN(25000,  43059, -22480) {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				return true;
			}
		},
		PRINCE(20000,  43063, -22476) {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				return true;
			}
		},
		PRINCESS(15000, 43067, -22472) {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				return true;
			}
		},
		LORD(15000,  43071, -22468) {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				return true;
			}
		},
		BARON(10000, 43075, -22464) {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				return true;
			}
		},
		DUKE(10000, 43079, -22460) {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				return true;
			}
		},
		SIR(8000, 43083, -22456) {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				return true;
			}
		},
		LADY(8000, 43087, -22452) {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				return true;
			}
		},
		EVIL(5000, 43091, -22448) {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				return true;
			}
		},
		GOOD(5000, 43095, -22444) {
			@Override
			boolean canBuy(Player p, boolean sendMessage) {
				return true;
			}
		};

		private LoyaltyTitles(int cost, int frame, int button) {
			this(cost, frame, button, "@or2@");
		}
		

		private LoyaltyTitles(int cost, int frame, int button, String color) {
			this.cost = cost;
			this.frame = frame;
			this.button = button;
			this.color = color;
		}

		private final int cost;
		private final int frame;
		private final int button;
		private final String color;
		abstract boolean canBuy(Player p, boolean sendMessage);

		public static LoyaltyTitles getTitle(int button) {
			for(LoyaltyTitles t : LoyaltyTitles.values()) {
				if(t.button == button)
					return t;
			}
			return null;
		}
	}

	public static void unlock(Player player, LoyaltyTitles title) {
		if(player.getUnlockedLoyaltyTitles()[title.ordinal()])
			return;
		Achievements.doProgress(player, AchievementData.UNLOCK_ALL_LOYALTY_TITLES);
		player.setUnlockedLoyaltyTitle(title.ordinal());
		player.getPacketSender().sendMessage("You've unlocked the "+Misc.formatText(title.name().toLowerCase())+" loyalty title!");
	}

	public static boolean handleButton(Player player, int button) {
		LoyaltyTitles title = LoyaltyTitles.getTitle(button);
		if(title != null) {
			if(title.canBuy(player, true)) {
			
				if(player.getPointsHandler().getLoyaltyPoints() >= title.cost) {
				
					player.setTitle(title.color + title);
				
					player.getPointsHandler().setLoyaltyPoints(-title.cost, true);
					player.getPointsHandler().refreshPanel();
					player.getPacketSender().sendMessage("You've changed your title.");
					player.getUpdateFlag().flag(Flag.APPEARANCE);
					open(player);
				} else {
					player.getPacketSender().sendMessage("You need at least "+title.cost+" Loyalty Points to buy this title.");
				}
			}
			return true;
		}
		return false;
	}

	public static void open(Player player) {
		for(LoyaltyTitles title : LoyaltyTitles.values()) {
			if(title.cost > 0) {
				player.getPacketSender().sendString(title.frame, title.cost + " LP");
			} else {
				if(title.canBuy(player, false)) {
					player.getPacketSender().sendString(title.frame, "@gre@Unlocked");
				} else {
					player.getPacketSender().sendString(title.frame, "  @red@Locked");
				}
			}
		}
		player.getPacketSender().sendString(43120, "Your Loyalty Points: "+player.getPointsHandler().getLoyaltyPoints()).sendInterface(43000);
	}

	public static void reset(Player player) {
		player.setLoyaltyTitle(LoyaltyTitles.NONE);
		player.getUpdateFlag().flag(Flag.APPEARANCE);
	}

	public static void incrementPoints(Player player) {
		double pts = player.getRights().getLoyaltyPointsGainModifier();
		if(WellOfGoodwill.bonusLoyaltyPoints(player))
			pts *= 1.5;
		player.getPointsHandler().incrementLoyaltyPoints(pts);
		player.getAchievementAttributes().incrementTotalLoyaltyPointsEarned(pts);
		
		int totalPoints = (int)player.getPointsHandler().getLoyaltyPoints();
		if(totalPoints >= 100000) {
			unlock(player, LoyaltyTitles.ADMIRAL);
		}
		if(totalPoints >= 50000) {
			Achievements.finishAchievement(player, AchievementData.REACH_50K_LOYALTY_POINTS);
		}
		
		if(player.getInterfaceId() == 43000) {
			player.getPacketSender().sendString(43120, "Your Loyalty Points: "+totalPoints);
		}
 		player.getPacketSender().sendString(39178, "@or2@Loyalty Points: @yel@"+totalPoints);

		if(player.getAchievementAttributes().getTotalLoyaltyPointsEarned() >= 500000) {
			unlock(player, LoyaltyTitles.DIVINE);
		}
	}
}