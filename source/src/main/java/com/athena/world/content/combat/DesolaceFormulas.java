package com.athena.world.content.combat;

import com.athena.GameLoader;
import com.athena.GameSettings;
import com.athena.model.Graphic;
import com.athena.model.Skill;
import com.athena.model.Locations.Location;
import com.athena.model.container.impl.Equipment;
import com.athena.model.definitions.ItemDefinition;
import com.athena.util.Misc;
import com.athena.world.content.combat.effect.EquipmentBonus;
import com.athena.world.content.combat.magic.CombatSpell;
import com.athena.world.content.combat.prayer.CurseHandler;
import com.athena.world.content.combat.prayer.PrayerHandler;
import com.athena.world.content.combat.range.CombatRangedAmmo.RangedWeaponData;
import com.athena.world.content.combat.weapon.FightType;
import com.athena.world.content.skill.SkillManager;
import com.athena.world.entity.impl.Character;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;

public class DesolaceFormulas {

	/*
	 * =============================================================================
	 * =
	 */
	/*
	 * ===================================MELEE=====================================
	 */

	public static int calculateMaxMeleeHit(Character entity, Character victim) {
		double maxHit = 0;
		if (entity.isNpc()) {
			NPC npc = (NPC) entity;
			maxHit = npc.getDefinition().getMaxHit();
			if (npc.getStrengthWeakened()[0]) {
				maxHit -= (int) ((0.10) * (maxHit));
			} else if (npc.getStrengthWeakened()[1]) {
				maxHit -= (int) ((0.20) * (maxHit));
			} else if (npc.getStrengthWeakened()[2]) {
				maxHit -= (int) ((0.30) * (maxHit));
			}

			/** CUSTOM NPCS **/
			if (npc.getId() == 2026) { // Dharok the wretched
				maxHit += (int) ((int) (npc.getDefaultConstitution() - npc.getConstitution()) * 0.2);
			}
		} else {
			Player plr = (Player) entity;

			double base = 0;
			double effective = getEffectiveStr(plr);
			double specialBonus = 1;
			if (plr.isSpecialActivated()) {
				specialBonus = plr.getCombatSpecial().getStrengthBonus();
			}
			double strengthBonus = plr.getBonusManager().getOtherBonus()[0];
			base = (13 + effective + (strengthBonus / 8) + ((effective * strengthBonus) / 65)) / 11;
			if (plr.getEquipment().getItems()[3].getId() == 4718 && plr.getEquipment().getItems()[0].getId() == 4716
					&& plr.getEquipment().getItems()[4].getId() == 4720
					&& plr.getEquipment().getItems()[7].getId() == 4722)
				base += ((plr.getSkillManager().getMaxLevel(Skill.CONSTITUTION) - plr.getConstitution()) * .045) + 1;

			if (plr.getEquipment().getItems()[0].getId() == 15667 && plr.getEquipment().getItems()[1].getId() == 15664
					&& plr.getEquipment().getItems()[3].getId() == 15668
					&& plr.getEquipment().getItems()[4].getId() == 15662
					&& plr.getEquipment().getItems()[7].getId() == 15663
					&& plr.getEquipment().getItems()[9].getId() == 15666
					&& plr.getEquipment().getItems()[10].getId() == 15665)
				base += ((plr.getSkillManager().getMaxLevel(Skill.CONSTITUTION) - plr.getConstitution()) * .045) + 1;

			if (specialBonus > 1)
				base = (base * specialBonus);
			if (hasObsidianEffect(plr) || EquipmentBonus.voidMelee(plr))
				base = (base * 1.2);
			if (hasObsidianEffect(plr) || EquipmentBonus.voidEMelee(plr))
				base = (base * 1.6);

			if (EquipmentBonus.voidGMelee(plr))
				base *= 1.9;
			
			if (EquipmentBonus.voidEMelee(plr))
				base *= 0.5;
			
			if (EquipmentBonus.voidMelee(plr))
				base *= 0.4;
			
			if (EquipmentBonus.dragonV(plr))
				base *= 1.9;
			
			if (EquipmentBonus.DarthVader(plr))
				base *= 1.9;
			
			if (EquipmentBonus.SupremeLord(plr))
				base *= 1.9;
			
			if (EquipmentBonus.Ultimatium(plr))
				base *= 1.9;
			
			if (EquipmentBonus.SupremeGraardor(plr))
				base *= 1.9;
			
			if (EquipmentBonus.StormTroop(plr))
				base *= 0.8;
			
			if (EquipmentBonus.Yanille(plr))
				base *= 1.9;
			
			if (EquipmentBonus.Deluxe(plr))
				base *= 1.9;
			
			if(GameSettings.STREAM_BONUS)
				base *= 1.7;
			
			if (victim.isNpc()) {
				NPC npc = (NPC) victim;
				if (npc.getDefenceWeakened()[0]) {
					base += (int) ((0.10) * (base));
				} else if (npc.getDefenceWeakened()[1]) {
					base += (int) ((0.20) * (base));
				} else if (npc.getDefenceWeakened()[2]) {
					base += (int) ((0.30) * (base));
				}

				if (entity.isPlayer()) {

					if (plr.getSummoned() == 2074) {
						base *= 2;
					} else if (plr.getSummoned() == 3063 || plr.getSummoned() == 2071 || plr.getSummoned() == 2072
							|| plr.getSummoned() == 2073 || plr.getSummoned() == 2075) {
						base *= 1.5;
					} else if (plr.getSummoned() > 3053) {
						base *= 1.1;
					} else if (plr.getSummoned() > 3063) {
						base *= 1.2;
					} else if (plr.getSummoned() > 1302) {
						base *= 1.3;
					} else if (plr.getSummoned() > 6301) {
						base *= 1.5;
					} else if (plr.getSummoned() > 9) {
						base *= 1.1;
					} else if (plr.getSummoned() > 6) {
						base *= 1.1;
					} else if (plr.getSummoned() > 5) {
						base *= 1.1;
					} else if (plr.getSummoned() > 4) {
						base *= 1.1;
					} else if (plr.getSummoned() > 0) {
						base *= 1.1;
					} else if (plr.getSummoned() > 1) {
						base *= 1.1;
					}

					if (plr.getTransform() > 1) {
						switch (plr.getTransform()) {
						case 4540:
							base *= 1.10;
							break;
						case 6303:
							base *= 1.15;
							break;
						case 1234:
							base *= 1.20;
							break;
						case 2236:
							base *= 1.25;
							break;
						}
					}
					if (npc.getDefinition().getExamine() != null) {
						String npcElemental = npc.getDefinition().getExamine();
						ItemDefinition wep = ItemDefinition
								.forId(plr.getEquipment().get(Equipment.WEAPON_SLOT).getId());
						int elemental = wep.getElemental();
						switch (elemental) {
						case 1: // fire wep
							if (npcElemental.contains("Grass")) {
								base *= 1.10;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does extra damage against a grass type monster");
								}
							} else if (npcElemental.contains("Dark")) {
								base *= 1.10;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does extra damage against a dark type monster");
								}
							} else if (npcElemental.contains("Mythical")) {
								base *= 1.10;
								plr.getPacketSender()
										.sendMessage("Your weapon does extra damage against a Mythical type monster");
							} else if (npcElemental.contains("Earth")) {
								base *= 0.90;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does less damage against a earth type monster");
								}
							} else if (npcElemental.contains("Water")) {

								base *= 0.90;

								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does less damage against a water type monster");
								}
							}
							break;
						case 2: // water wep
							if (npcElemental.contains("Fire")) {
								base *= 1.10;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does extra damage against a fire type monster");
								}
							} else if (npcElemental.contains("Earth")) {
								base *= 1.10;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does extra damage against a Earth type monster");
								}
							} else if (npcElemental.contains("Grass")) {
								base *= 0.90;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);
									plr.getPacketSender()
											.sendMessage("Your weapon does less damage against a Grass type monster");
								}
							}
							break;
						case 3: // earth wep
							if (npcElemental.contains("Fire")) {
								base *= 1.10;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does extra damage against a fire type monster");
								}
							} else if (npcElemental.contains("Earth")) {
								base *= 1.10;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does extra damage against a Earth type monster");
								}
							} else if (npcElemental.contains("Grass")) {
								base *= 0.90;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does less damage against a grass type monster");
								}
							} else if (npcElemental.contains("Water")) {
								base *= 0.90;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does less damage against a water type monster");
								}
							}
							break;
						case 4: // grass wep
							if (npcElemental.contains("Water")) {
								base *= 1.10;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does extra damage against a Water type monster");
								}
							} else if (npcElemental.contains("Earth")) {
								base *= 1.10;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does extra damage against a Earth type monster");
								}
							} else if (npcElemental.contains("Fire")) {
								base *= 0.90;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does less damage against a Fire type monster");
								}
							} else if (npcElemental.contains("Mytical")) {
								base *= 0.90;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender().sendMessage(
											"Your weapon does less damage against a Mythical type monster");
								}
							}
							break;
						case 5: // light wep
							if (npcElemental.contains("Dark")) {
								base *= 1.10;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does extra damage against a dark type monster");
								}
							} else if (npcElemental.contains("Fire")) {
								base *= 0.90;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does less damage against a Fire type monster");
								}
							}
							break;
						case 6: // dark wep
							if (npcElemental.contains("Light")) {
								base *= 1.10;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does extra damage against a dark type monster");
								}
							} else if (npcElemental.contains("Earth")) {
								base *= 0.90;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does less damage against a Earth type monster");
								}
							}
							break;
						case 7: // Mytical wep
							if (npcElemental.contains("Fire")) {
								base *= 1.10;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does extra damage against a dark type monster");
								}
							} else if (npcElemental.contains("Water")) {
								base *= 1.10;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does extra damage against a dark type monster");
								}
							} else if (npcElemental.contains("Mythical")) {
								base *= 1.10;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender().sendMessage(
											"Your weapon does extra damage against a Mythical type monster");
								}
							} else if (npcElemental.contains("Earth")) {
								base *= 0.90;
								if (plr.sendElementalMessage == true) {
									plr.setSendElementalMessage(false);

									plr.getPacketSender()
											.sendMessage("Your weapon does less damage against a Earth type monster");
								}
							}
							break;
						}
					}
				}

				/** SLAYER HELMET **/
				if (npc.getId() == plr.getSlayer().getSlayerTask().getNpcId()) {
					if (plr.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 13263) {
						base *= 1.12;
					}
					if (plr.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 11546) {
						base *= 1.22;
					}
					if (plr.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 11547) {
						base *= 1.32;
					}
					if (plr.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 11548) {
						base *= 1.42;
					}
					if (plr.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 11549) {
						base *= 1.62;
					}
					if (plr.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 11550) {
						base *= 1.52;
					}
				}

				// NEW ARMOR START
				// MISSING ARTEMIS GLOVE + BOOTS

				// dragon vanquisher
				if (plr.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 20815
						&& plr.getEquipment().get(Equipment.HEAD_SLOT).getId() == 20810
						&& plr.getEquipment().get(Equipment.BODY_SLOT).getId() == 20811
						&& plr.getEquipment().get(Equipment.LEG_SLOT).getId() == 20812
						&& plr.getEquipment().get(Equipment.HANDS_SLOT).getId() == 20813
						&& (plr.getEquipment().get(Equipment.FEET_SLOT).getId() == 20814
								|| plr.getEquipment().get(Equipment.FEET_SLOT).getId() == 20800
								|| plr.getEquipment().get(Equipment.FEET_SLOT).getId() == 20801
								|| plr.getEquipment().get(Equipment.FEET_SLOT).getId() == 20802
								|| plr.getEquipment().get(Equipment.FEET_SLOT).getId() == 20803
								|| plr.getEquipment().get(Equipment.FEET_SLOT).getId() == 20804)) {
					base *= 1.6;
				}
				if (plr.getEquipment().get(Equipment.SHIELD_SLOT).getId() == 20820) {
					base *= 1.2;
				}

			}
			maxHit = (base *= 10);
			if(GameLoader.doubleDamage())
				maxHit *= 2;

			// maxHit = plr.getLocation() == Location.WILDERNESS ? (base *= 10) / 100 :
			// (base *= 10);
		}
		if (victim.isPlayer()) {
			Player p = (Player) victim;
			if (p.hasStaffOfLightEffect()) {
				maxHit = maxHit / 2;
				p.performGraphic(new Graphic(2319));
			}
		}
		return (int) Math.floor(maxHit);
	}

	/**
	 * Calculates a player's Melee attack level (how likely that they're going to
	 * hit through defence)
	 *
	 * @param plr The player's Meelee attack level
	 * @return The player's Melee attack level
	 */
	@SuppressWarnings("incomplete-switch")
	public static int getMeleeAttack(Player plr) {
		int attackLevel = plr.getSkillManager().getCurrentLevel(Skill.ATTACK);
		switch (plr.getFightType().getStyle()) {
		case AGGRESSIVE:
			attackLevel += 3;
			break;
		case CONTROLLED:
			attackLevel += 1;
			break;
		}
		boolean hasVoid = EquipmentBonus.voidMelee(plr);
		boolean hasVoid2 = EquipmentBonus.voidEMelee(plr);
		boolean hasArtemis = EquipmentBonus.wearingArtemis(plr);

		if (PrayerHandler.isActivated(plr, PrayerHandler.CLARITY_OF_THOUGHT)) {
			attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.05;
		} else if (PrayerHandler.isActivated(plr, PrayerHandler.IMPROVED_REFLEXES)) {
			attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.1;
		} else if (PrayerHandler.isActivated(plr, PrayerHandler.INCREDIBLE_REFLEXES)) {
			attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.15;
		} else if (PrayerHandler.isActivated(plr, PrayerHandler.CHIVALRY)) {
			attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.15;
		} else if (PrayerHandler.isActivated(plr, PrayerHandler.PIETY)) {
			attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.2;
		} else if (CurseHandler.isActivated(plr, CurseHandler.LEECH_ATTACK)) {
			attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.05 + plr.getLeechedBonuses()[2];
		} else if (CurseHandler.isActivated(plr, CurseHandler.TURMOIL)) {
			attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.3 + plr.getLeechedBonuses()[2];
		}

		attackLevel *= plr.isSpecialActivated() ? plr.getCombatSpecial().getAccuracyBonus() : 1;
		int i = (int) plr.getBonusManager().getAttackBonus()[bestMeleeAtk(plr)];

		/*
		 * if (hasObsidianEffect(plr) || hasVoid) i *= 1.20; if (hasObsidianEffect(plr)
		 * || hasVoid2) i *= 1.90;
		 */
		return (int) (attackLevel + (attackLevel * 0.15) + (i + i * 0.04));
	}

	/**
	 * Calculates a player's Melee Defence level
	 *
	 * @param plr The player to calculate Melee defence for
	 * @return The player's Melee defence level
	 */
	public static int getMeleeDefence(Player plr) {
		int defenceLevel = plr.getSkillManager().getCurrentLevel(Skill.DEFENCE);
		int i = (int) plr.getBonusManager().getDefenceBonus()[bestMeleeDef(plr)];
		if (plr.getPrayerActive()[PrayerHandler.THICK_SKIN]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.05;
		} else if (plr.getPrayerActive()[PrayerHandler.ROCK_SKIN]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.1;
		} else if (plr.getPrayerActive()[PrayerHandler.STEEL_SKIN]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.15;
		} else if (plr.getPrayerActive()[PrayerHandler.CHIVALRY]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.2;
		} else if (plr.getPrayerActive()[PrayerHandler.PIETY]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
		} else if (plr.getPrayerActive()[PrayerHandler.RIGOUR]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
		} else if (plr.getPrayerActive()[PrayerHandler.AUGURY]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
		} else if (plr.getPrayerActive()[CurseHandler.TURMOIL]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.RANGED) * 0.25;
		} else if (plr.getPrayerActive()[CurseHandler.TURMOIL]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.MAGIC) * 0.25;
		} else if (plr.getCurseActive()[CurseHandler.TURMOIL]) { // turmoil
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.15;
		}
		return (int) (defenceLevel + (defenceLevel * 0.15) + (i + i * 1.0));
	}

	public static int bestMeleeDef(Player p) {
		if (p.getBonusManager().getDefenceBonus()[0] > p.getBonusManager().getDefenceBonus()[1]
				&& p.getBonusManager().getDefenceBonus()[0] > p.getBonusManager().getDefenceBonus()[2]) {
			return 0;
		}
		if (p.getBonusManager().getDefenceBonus()[1] > p.getBonusManager().getDefenceBonus()[0]
				&& p.getBonusManager().getDefenceBonus()[1] > p.getBonusManager().getDefenceBonus()[2]) {
			return 1;
		}
		return p.getBonusManager().getDefenceBonus()[2] <= p.getBonusManager().getDefenceBonus()[0]
				|| p.getBonusManager().getDefenceBonus()[2] <= p.getBonusManager().getDefenceBonus()[1] ? 0 : 2;
	}

	public static int bestMeleeAtk(Player p) {
		if (p.getBonusManager().getAttackBonus()[0] > p.getBonusManager().getAttackBonus()[1]
				&& p.getBonusManager().getAttackBonus()[0] > p.getBonusManager().getAttackBonus()[2]) {
			return 0;
		}
		if (p.getBonusManager().getAttackBonus()[1] > p.getBonusManager().getAttackBonus()[0]
				&& p.getBonusManager().getAttackBonus()[1] > p.getBonusManager().getAttackBonus()[2]) {
			return 1;
		}
		return p.getBonusManager().getAttackBonus()[2] <= p.getBonusManager().getAttackBonus()[1]
				|| p.getBonusManager().getAttackBonus()[2] <= p.getBonusManager().getAttackBonus()[0] ? 0 : 2;
	}

	/**
	 * Obsidian items
	 */

	public static final int[] obsidianWeapons = { 746, 747, 6523, 6525, 6526, 6527, 6528 };

	public static boolean hasObsidianEffect(Player plr) {
		if (plr.getEquipment().getItems()[2].getId() != 11128)
			return false;

		for (int weapon : obsidianWeapons) {
			if (plr.getEquipment().getItems()[3].getId() == weapon)
				return true;
		}
		return false;
	}

	@SuppressWarnings("incomplete-switch")
	public static int getStyleBonus(Player plr) {
		switch (plr.getFightType().getStyle()) {
		case AGGRESSIVE:
		case ACCURATE:
			return 3;
		case CONTROLLED:
			return 1;
		}
		return 0;
	}

	public static double getEffectiveStr(Player plr) {
		return ((plr.getSkillManager().getCurrentLevel(Skill.STRENGTH)) * getPrayerStr(plr)) + getStyleBonus(plr);
	}

	public static double getPrayerStr(Player plr) {
		if (plr.getPrayerActive()[1] || plr.getCurseActive()[CurseHandler.LEECH_STRENGTH])
			return 1.05;
		else if (plr.getPrayerActive()[6])
			return 1.1;
		else if (plr.getPrayerActive()[14])
			return 1.15;
		else if (plr.getPrayerActive()[24])
			return 1.18;
		else if (plr.getPrayerActive()[25])
			return 1.23;
		else if (plr.getCurseActive()[CurseHandler.TURMOIL])
			return 1.24;
		return 1;
	}

	/**
	 * Calculates a player's Ranged attack (level). Credits: Dexter Morgan
	 *
	 * @param plr The player to calculate Ranged attack level for
	 * @return The player's Ranged attack level
	 */
	public static int getRangedAttack(Player plr) {
		int rangeLevel = plr.getSkillManager().getCurrentLevel(Skill.RANGED);
		boolean hasVoid = EquipmentBonus.voidRange(plr);
		boolean hasVoid2 = EquipmentBonus.voidERange(plr);
		double accuracy = plr.isSpecialActivated() ? plr.getCombatSpecial().getAccuracyBonus() : 1;
		rangeLevel *= accuracy;
		if (hasVoid) {
			rangeLevel += SkillManager.getLevelForExperience(plr.getSkillManager().getExperience(Skill.RANGED)) * 1.5;
		}
		if (hasVoid2) {
			rangeLevel += SkillManager.getLevelForExperience(plr.getSkillManager().getExperience(Skill.RANGED)) * 2.5;
		}
		if (plr.getCurseActive()[PrayerHandler.SHARP_EYE] || plr.getCurseActive()[CurseHandler.SAP_RANGER]) {
			rangeLevel *= 1.05;
		}
		if (plr.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 15492) {
			rangeLevel *= 1.2;
		} else if (plr.getPrayerActive()[PrayerHandler.HAWK_EYE]) {
			rangeLevel *= 1.10;
		} else if (plr.getPrayerActive()[PrayerHandler.EAGLE_EYE]) {
			rangeLevel *= 1.15;
		} else if (plr.getPrayerActive()[PrayerHandler.RIGOUR]) {
			rangeLevel *= 1.22;
		} else if (plr.getCurseActive()[CurseHandler.LEECH_RANGED]) {
			rangeLevel *= 1.10;
		}
		if (hasVoid && accuracy > 1.15)
			rangeLevel *= 1.4;
		if (hasVoid2 && accuracy > 1.15)
			rangeLevel *= 2.0;
		return (int) (rangeLevel + (plr.getBonusManager().getAttackBonus()[4] * 2));
	}

	/**
	 * Calculates a player's Ranged defence level.
	 *
	 * @param plr The player to calculate the Ranged defence level for
	 * @return The player's Ranged defence level
	 */
	public static int getRangedDefence(Player plr) {
		int defenceLevel = plr.getSkillManager().getCurrentLevel(Skill.DEFENCE);
		if (plr.getPrayerActive()[PrayerHandler.THICK_SKIN]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.05;
		} else if (plr.getPrayerActive()[PrayerHandler.ROCK_SKIN]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.1;
		} else if (plr.getPrayerActive()[PrayerHandler.STEEL_SKIN]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.15;
		} else if (plr.getPrayerActive()[PrayerHandler.CHIVALRY]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.2;
		} else if (plr.getPrayerActive()[PrayerHandler.PIETY]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
		} else if (plr.getPrayerActive()[PrayerHandler.RIGOUR]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
		} else if (plr.getPrayerActive()[PrayerHandler.AUGURY]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
		} else if (plr.getCurseActive()[CurseHandler.TURMOIL]) { // turmoil
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.20 + plr.getLeechedBonuses()[0];
		}
		return (int) (defenceLevel + plr.getBonusManager().getDefenceBonus()[4]
				+ (plr.getBonusManager().getDefenceBonus()[4] / 2));
	}

	public static int getMagicAttack(Player plr) {
		boolean voidEquipment = EquipmentBonus.voidMage(plr);
		boolean voidEquipment2 = EquipmentBonus.voidEMage(plr);
		int attackLevel = plr.getSkillManager().getCurrentLevel(Skill.MAGIC);

		if (plr.getPrayerActive()[PrayerHandler.MYSTIC_WILL] || plr.getCurseActive()[CurseHandler.SAP_MAGE]) {
			attackLevel *= 1.05;
		} else if (plr.getPrayerActive()[PrayerHandler.MYSTIC_LORE]) {
			attackLevel *= 1.10;
		} else if (plr.getPrayerActive()[PrayerHandler.MYSTIC_MIGHT]) {
			attackLevel *= 1.15;
		} else if (plr.getPrayerActive()[PrayerHandler.AUGURY]) {
			attackLevel *= 1.22;
		} else if (plr.getCurseActive()[CurseHandler.LEECH_MAGIC]) {
			attackLevel *= 1.18;
		}
		attackLevel *= plr.isSpecialActivated() ? plr.getCombatSpecial().getAccuracyBonus() : 1;

		return (int) (attackLevel + (plr.getBonusManager().getAttackBonus()[3]));
	}

	/**
	 * Calculates a player's magic defence level
	 *
	 * @param plr The player to calculate magic defence level for
	 * @return The player's magic defence level
	 */
	public static int getMagicDefence(Player plr) {

		int defenceLevel = plr.getSkillManager().getCurrentLevel(Skill.DEFENCE) / 2
				+ plr.getSkillManager().getCurrentLevel(Skill.MAGIC) / 2;

		if (plr.getPrayerActive()[PrayerHandler.THICK_SKIN]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.05;
		} else if (plr.getPrayerActive()[PrayerHandler.ROCK_SKIN]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.1;
		} else if (plr.getPrayerActive()[PrayerHandler.STEEL_SKIN]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.15;
		} else if (plr.getPrayerActive()[PrayerHandler.CHIVALRY]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.2;
		} else if (plr.getPrayerActive()[PrayerHandler.PIETY]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
		} else if (plr.getPrayerActive()[PrayerHandler.RIGOUR]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
		} else if (plr.getPrayerActive()[PrayerHandler.AUGURY]) {
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
		} else if (plr.getCurseActive()[CurseHandler.TURMOIL]) { // turmoil
			defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.20 + plr.getLeechedBonuses()[0];
		}

		return (int) (defenceLevel + plr.getBonusManager().getDefenceBonus()[3]
				+ (plr.getBonusManager().getDefenceBonus()[3] / 3));
	}

	/**
	 * Calculates a player's magic max hit
	 *
	 * @param c The player to calculate magic max hit for
	 * @return The player's magic max hit damage
	 */
	public static int getMagicMaxhit(Character c, Character victim) {
		int damage = 0;
		CombatSpell spell = c.getCurrentlyCasting();
		if (spell != null) {
			if (spell.maximumHit() > 0)
				damage += spell.maximumHit();

			else {
				if (c.isNpc()) {
					damage = ((NPC) c).getDefinition().getMaxHit();
				} else {
					damage = 1;
				}
			}
		}

		if (c.isNpc()) {
			if (spell == null) {
				damage = Misc.getRandom(((NPC) c).getDefinition().getMaxHit());
			}
			return damage;
		}

		Player p = (Player) c;
		double damageMultiplier = 1;
		boolean voidEquipment = EquipmentBonus.voidMage(p);
		boolean voidEquipment2 = EquipmentBonus.voidEMage(p);

		if (voidEquipment) {
			damageMultiplier += 1.2;
		} else if (voidEquipment2) {
			damageMultiplier += 1.6;
		} else if (EquipmentBonus.voidGMage(p)) {
			damageMultiplier += 1.8;
		}
		
		if(GameSettings.STREAM_BONUS)
			damageMultiplier += 1.7;

		if (p.getSummoned() == 2074) {
			damageMultiplier += 2;
		} else if (p.getSummoned() == 3063 || p.getSummoned() == 2071 || p.getSummoned() == 2072
				|| p.getSummoned() == 2073 || p.getSummoned() == 2075) {
			damageMultiplier += 1.5;
		} else if (p.getSummoned() > 3053) {
			damageMultiplier += 1.1;
		} else if (p.getSummoned() > 3063) {
			damageMultiplier += 1.2;
		} else if (p.getSummoned() > 1302) {
			damageMultiplier += 1.3;
		} else if (p.getSummoned() > 6301) {
			damageMultiplier += 1.5;
		} else if (p.getSummoned() > 9) {
			damageMultiplier += 1.1;
		} else if (p.getSummoned() > 6) {
			damageMultiplier += 1.1;
		} else if (p.getSummoned() > 5) {
			damageMultiplier += 1.1;
		} else if (p.getSummoned() > 4) {
			damageMultiplier += 1.1;
		} else if (p.getSummoned() > 0) {
			damageMultiplier += 1.1;
		} else if (p.getSummoned() > 1) {
			damageMultiplier += 1.1;
		}
		if (p.getTransform() > 1) {
			switch (p.getTransform()) {
			case 4540:
				damageMultiplier += 1.1;
				break;
			case 6303:
				damageMultiplier += 1.15;
				break;
			case 1234:
				damageMultiplier += 1.20;
				break;
			case 2236:
				damageMultiplier += 1.25;
				break;
			}
		}

		boolean specialAttack = p.isSpecialActivated();

		int maxHit = -1;

		if (specialAttack) {
			switch (p.getEquipment().getItems()[Equipment.WEAPON_SLOT].getId()) {
			case 19780:
				damage = maxHit = 750;
				break;
			case 11730:
				damage = maxHit = 610;
				break;
			case 19028:
				damage = maxHit = 510;
				break;
			}
		} else {
			damageMultiplier += 0.25;
		}

		if (p.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 18335) {
			damageMultiplier += .10;
		}

		damage *= damageMultiplier;

		if (maxHit > 0) {
			if (damage > maxHit) {
				damage = maxHit;
			}
		}

		if (c.isPlayer()) {
			NPC npc = (NPC) victim;
			Player plr = (Player) c;

			if (npc.getDefinition().getExamine() != null) {
				String npcElemental = npc.getDefinition().getExamine();
				ItemDefinition wep = ItemDefinition.forId(plr.getEquipment().get(Equipment.WEAPON_SLOT).getId());
				int elemental = wep.getElemental();
				switch (elemental) {
				case 1: // fire wep
					if (npcElemental.contains("Grass")) {
						damage *= 1.10;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does extra damage against a grass type monster");
						}
					} else if (npcElemental.contains("Dark")) {
						damage *= 1.10;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does extra damage against a dark type monster");
						}
					} else if (npcElemental.contains("Mythical")) {
						damage *= 1.10;
						plr.getPacketSender()
								.sendMessage("Your weapon does extra damage against a Mythical type monster");
					} else if (npcElemental.contains("Earth")) {
						damage *= 0.90;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does less damage against a earth type monster");
						}
					} else if (npcElemental.contains("Water")) {

						damage *= 0.90;

						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does less damage against a water type monster");
						}
					}
					break;
				case 2: // water wep
					if (npcElemental.contains("Fire")) {
						damage *= 1.10;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does extra damage against a fire type monster");
						}
					} else if (npcElemental.contains("Earth")) {
						damage *= 1.10;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does extra damage against a Earth type monster");
						}
					} else if (npcElemental.contains("Grass")) {
						damage *= 0.90;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);
							plr.getPacketSender()
									.sendMessage("Your weapon does less damage against a Grass type monster");
						}
					}
					break;
				case 3: // earth wep
					if (npcElemental.contains("Fire")) {
						damage *= 1.10;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does extra damage against a fire type monster");
						}
					} else if (npcElemental.contains("Earth")) {
						damage *= 1.10;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does extra damage against a Earth type monster");
						}
					} else if (npcElemental.contains("Grass")) {
						damage *= 0.90;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does less damage against a grass type monster");
						}
					} else if (npcElemental.contains("Water")) {
						damage *= 0.90;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does less damage against a water type monster");
						}
					}
					break;
				case 4: // grass wep
					if (npcElemental.contains("Water")) {
						damage *= 1.10;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does extra damage against a Water type monster");
						}
					} else if (npcElemental.contains("Earth")) {
						damage *= 1.10;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does extra damage against a Earth type monster");
						}
					} else if (npcElemental.contains("Fire")) {
						damage *= 0.90;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does less damage against a Fire type monster");
						}
					} else if (npcElemental.contains("Mytical")) {
						damage *= 0.90;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does less damage against a Mythical type monster");
						}
					}
					break;
				case 5: // light wep
					if (npcElemental.contains("Dark")) {
						damage *= 1.10;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does extra damage against a dark type monster");
						}
					} else if (npcElemental.contains("Fire")) {
						damage *= 0.90;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does less damage against a Fire type monster");
						}
					}
					break;
				case 6: // dark wep
					if (npcElemental.contains("Light")) {
						damage *= 1.10;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does extra damage against a dark type monster");
						}
					} else if (npcElemental.contains("Earth")) {
						damage *= 0.90;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does less damage against a Earth type monster");
						}
					}
					break;
				case 7: // Mytical wep
					if (npcElemental.contains("Fire")) {
						damage *= 1.10;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does extra damage against a dark type monster");
						}
					} else if (npcElemental.contains("Water")) {
						damage *= 1.10;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does extra damage against a dark type monster");
						}
					} else if (npcElemental.contains("Mythical")) {
						damage *= 1.10;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does extra damage against a Mythical type monster");
						}
					} else if (npcElemental.contains("Earth")) {
						damage *= 0.90;
						if (plr.sendElementalMessage == true) {
							plr.setSendElementalMessage(false);

							plr.getPacketSender()
									.sendMessage("Your weapon does less damage against a Earth type monster");
						}
					}
					break;
				}
			}
		}
		Player plr = (Player) c;
		int magicalAttack = getMagicAttack(plr);

		for (int weapon : GameSettings.ENABLED_WILDERNESS_EQUIPMENT) {
			damage = plr.getEquipment().getItems()[Equipment.WEAPON_SLOT].getId() == weapon
					? (int) ((magicalAttack / 8) * damageMultiplier)
					: plr.getLocation() == Location.WILDERNESS ? (int) ((magicalAttack / 8) * damageMultiplier) / 500
							: (int) (int) ((magicalAttack / 8) * damageMultiplier);
		}

		// damage = plr.getLocation() == Location.WILDERNESS ? (int)((magicalAttack /
		// 8)* damageMultiplier) / 100: (int)((magicalAttack / 8)* damageMultiplier);
		if(GameLoader.doubleDamage())
			damage *= 2;
		return (int) damage;
	}

	public static int getAttackDelay(Player plr) {
		int id = plr.getEquipment().getItems()[Equipment.WEAPON_SLOT].getId();
		String s = ItemDefinition.forId(id).getName().toLowerCase();
		if (id == -1)
			return 4;// unarmed
		if (id == 18357 || id == 14684 || id == 13051)
			return 3;
		RangedWeaponData rangedData = plr.getRangedWeaponData();
		if (rangedData != null) {
			int speed = rangedData.getType().getAttackDelay();
			if (plr.getFightType() == FightType.SHORTBOW_RAPID || plr.getFightType() == FightType.DART_RAPID
					|| plr.getFightType() == FightType.KNIFE_RAPID || plr.getFightType() == FightType.THROWNAXE_RAPID
					|| plr.getFightType() == FightType.JAVELIN_RAPID) {
				speed--;
			}
			return speed;
		}
		if (id == 18365)
			return 3;
		else if (id == 18349) // CCbow and rapier
			return 4;
		if (id == 18353) // cmaul
			return 7;// chaotic maul
		if (id == 3954) // Bandos maul
			return 6;// Bandos Maul

		if (id == 20000)
			return 4;// gs
		if (id == 20001)
			return 4;// gs
		if (id == 20002)
			return 4;// gs
		if (id == 20003)
			return 4;// gs
		if (id == 18349)
			return 5;// chaotic rapier
		if (id == 18353) // cmaul
			return 7;// chaotic maul
		if (id == 3954) // Bandos maul
			return 6;// Bandos Maul
		if (id == 16877)
			return 4;// dung 16877 shortbow
		if (id == 19143)
			return 3;// sara shortbow
		if (id == 19146)
			return 4;// guthix shortbow
		if (id == 19149)
			return 3;// zammy shortbow

		switch (id) {
		case 11235:
		case 13405: // dbow
		case 15701: // dark bow
		case 15702: // dark bow
		case 15703: // dark bow
		case 15704: // dark bow
		case 19146: // guthix bow
			return 9;
		case 13879:
			return 8;
		case 15241: // hand cannon
			return 8;
		case 11730:
			return 4;
		case 14484:
			return 5;
		case 19023:
			return 5;
		case 13883:
			return 6;
		case 10887:
		case 6528:
		case 15039:
			return 7;
		case 4450:
			return 5;
		case 13905:
			return 5;
		case 13907:
			return 5;
		case 18353:
			return 7;
		case 3954:
			return 6;
		case 18349:
			return 4;
		case 20000:
		case 20001:
		case 20002:
		case 20003:
			return 4;
		case 4706:
		case 18971:
		case 4212:
			return 4;

		case 16403: // long primal
			return 5;
		}

		if (s.endsWith("greataxe"))
			return 7;
		else if (s.equals("torags hammers"))
			return 5;
		else if (s.equals("guthans warspear"))
			return 5;
		else if (s.equals("veracs flail"))
			return 5;
		else if (s.equals("ahrims staff"))
			return 6;
		else if (s.equals("chaotic crossbow"))
			return 4;
		else if (s.contains("staff")) {
			if (s.contains("zamarok") || s.contains("guthix") || s.contains("saradomian") || s.contains("slayer")
					|| s.contains("ancient"))
				return 4;
			else
				return 5;
		} else if (s.contains("aril")) {
			if (s.contains("composite") || s.equals("seercull"))
				return 5;
			else if (s.contains("Ogre"))
				return 8;
			else if (s.contains("short") || s.contains("hunt") || s.contains("sword"))
				return 4;
			else if (s.contains("long") || s.contains("crystal"))
				return 6;
			else if (s.contains("'bow"))
				return 7;

			return 5;
		} else if (s.contains("dagger"))
			return 4;
		else if (s.contains("godsword") || s.contains("2h"))
			return 6;
		else if (s.contains("longsword"))
			return 5;
		else if (s.contains("sword"))
			return 4;
		else if (s.contains("scimitar") || s.contains("katana"))
			return 4;
		else if (s.contains("mace"))
			return 5;
		else if (s.contains("battleaxe"))
			return 6;
		else if (s.contains("pickaxe"))
			return 5;
		else if (s.contains("thrownaxe"))
			return 5;
		else if (s.contains("axe"))
			return 5;
		else if (s.contains("warhammer"))
			return 6;
		else if (s.contains("2h"))
			return 7;
		else if (s.contains("spear"))
			return 5;
		else if (s.contains("claw"))
			return 4;
		else if (s.contains("halberd"))
			return 7;

		// sara sword, 2400ms
		else if (s.equals("granite maul"))
			return 7;
		else if (s.equals("toktz-xil-ak"))// sword
			return 4;
		else if (s.equals("tzhaar-ket-em"))// mace
			return 5;
		else if (s.equals("tzhaar-ket-om"))// maul
			return 7;
		else if (s.equals("chaotic maul"))// maul
			return 7;
		else if (s.equals("toktz-xil-ek"))// knife
			return 4;
		else if (s.equals("toktz-xil-ul"))// rings
			return 4;
		else if (s.equals("toktz-mej-tal"))// staff
			return 6;
		else if (s.contains("whip"))
			return 4;
		else if (s.contains("dart"))
			return 3;
		else if (s.contains("knife"))
			return 3;
		else if (s.contains("javelin"))
			return 6;
		return 5;
	}
}