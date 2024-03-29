package com.athena.world.content.combat;

import com.athena.engine.task.Task;
import com.athena.engine.task.TaskManager;
import com.athena.model.container.impl.Equipment;
import com.athena.world.content.Sounds;
import com.athena.world.content.combat.prayer.CurseHandler;
import com.athena.world.content.combat.range.CombatRangedAmmo.RangedWeaponData;
import com.athena.world.content.combat.strategy.impl.DefaultRangedCombatStrategy;
import com.athena.world.content.combat.weapon.CombatSpecial;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;

/**
 * A {@link Task} implementation that handles every combat 'hook' or 'turn'
 * during a combat session.
 * 
 * @author lare96
 */
public class CombatHookTask extends Task {

	/** The builder assigned to this task. */
	private CombatBuilder builder;

	/**
	 * Create a new {@link CombatHookTask}.
	 * 
	 * @param builder
	 *            the builder assigned to this task.
	 */
	public CombatHookTask(CombatBuilder builder) {
		super(1, builder, false);
		this.builder = builder;
	}

	@Override
	public void execute() {

		if (builder.isCooldown()) {
			builder.cooldown--;
			builder.attackTimer--;

			if (builder.cooldown == 0) {
				builder.reset(true);
			}
			return;
		}

		if (!CombatFactory.checkHook(builder.getCharacter(), builder.getVictim())) {
			return;
		}

		// If the entity is an player we redetermine the combat strategy before
		// attacking.
		if (builder.getCharacter().isPlayer()) {
			builder.determineStrategy();
			if(CurseHandler.isActivated((Player)builder.getCharacter(), CurseHandler.SOUL_SPLIT) && builder.getVictim().isNpc() && ((NPC)builder.getVictim()).getId() == 2070){
				Player p = (Player)builder.getCharacter();
				CurseHandler.deactivateCurse(p, CurseHandler.SOUL_SPLIT);
				p.sendMessage("You cannot use soulsplit on this monster." );
				return;
			}
		}
		if (builder.getCharacter().isPlayer()) {
			builder.determineStrategy();
			if(CurseHandler.isActivated((Player)builder.getCharacter(), CurseHandler.SOUL_SPLIT) && builder.getVictim().isNpc() && ((NPC)builder.getVictim()).getId() == 4331){
				Player p = (Player)builder.getCharacter();
				CurseHandler.deactivateCurse(p, CurseHandler.SOUL_SPLIT);
				p.sendMessage("You cannot use soulsplit on this Storm Troop." );
				return;
			}
		}
		if (builder.getCharacter().isPlayer()) {
			builder.determineStrategy();
			if(CurseHandler.isActivated((Player)builder.getCharacter(), CurseHandler.SOUL_SPLIT) && builder.getVictim().isNpc() && ((NPC)builder.getVictim()).getId() == 4332){
				Player p = (Player)builder.getCharacter();
				CurseHandler.deactivateCurse(p, CurseHandler.SOUL_SPLIT);
				p.sendMessage("You cannot use soulsplit on this Storm Troop." );
				return;
			}
		}
		if (builder.getCharacter().isPlayer()) {
			builder.determineStrategy();
			if(CurseHandler.isActivated((Player)builder.getCharacter(), CurseHandler.SOUL_SPLIT) && builder.getVictim().isNpc() && ((NPC)builder.getVictim()).getId() == 4333){
				Player p = (Player)builder.getCharacter();
				CurseHandler.deactivateCurse(p, CurseHandler.SOUL_SPLIT);
				p.sendMessage("You cannot use soulsplit on this Storm Troop." );
				return;
			}
		}
		if (builder.getCharacter().isPlayer()) {
			builder.determineStrategy();
			if(CurseHandler.isActivated((Player)builder.getCharacter(), CurseHandler.SOUL_SPLIT) && builder.getVictim().isNpc() && ((NPC)builder.getVictim()).getId() == 4334){
				Player p = (Player)builder.getCharacter();
				CurseHandler.deactivateCurse(p, CurseHandler.SOUL_SPLIT);
				p.sendMessage("You cannot use soulsplit on this Storm Troop." );
				return;
			}
		}
		if (builder.getCharacter().isPlayer()) {
			builder.determineStrategy();
			if(CurseHandler.isActivated((Player)builder.getCharacter(), CurseHandler.SOUL_SPLIT) && builder.getVictim().isNpc() && ((NPC)builder.getVictim()).getId() == 4335){
				Player p = (Player)builder.getCharacter();
				CurseHandler.deactivateCurse(p, CurseHandler.SOUL_SPLIT);
				p.sendMessage("You cannot use soulsplit on this Storm Troop." );
				return;
			}
		}
		if (builder.getCharacter().isPlayer()) {
			builder.determineStrategy();
			if(CurseHandler.isActivated((Player)builder.getCharacter(), CurseHandler.SOUL_SPLIT) && builder.getVictim().isNpc() && ((NPC)builder.getVictim()).getId() == 4336){
				Player p = (Player)builder.getCharacter();
				CurseHandler.deactivateCurse(p, CurseHandler.SOUL_SPLIT);
				p.sendMessage("You cannot use soulsplit on this Storm Troop." );
				return;
			}
		}
		if (builder.getCharacter().isPlayer()) {
			builder.determineStrategy();
			if(CurseHandler.isActivated((Player)builder.getCharacter(), CurseHandler.SOUL_SPLIT) && builder.getVictim().isNpc() && ((NPC)builder.getVictim()).getId() == 4330){
				Player p = (Player)builder.getCharacter();
				CurseHandler.deactivateCurse(p, CurseHandler.SOUL_SPLIT);
				p.sendMessage("You cannot use soulsplit on this Storm Troop Swordsman." );
				return;
			}
		}
		if (builder.getCharacter().isPlayer()) {
			builder.determineStrategy();
			if(CurseHandler.isActivated((Player)builder.getCharacter(), CurseHandler.SOUL_SPLIT) && builder.getVictim().isNpc() && ((NPC)builder.getVictim()).getId() == 4329){
				Player p = (Player)builder.getCharacter();
				CurseHandler.deactivateCurse(p, CurseHandler.SOUL_SPLIT);
				p.sendMessage("You cannot use soulsplit on this Darth Vader." );
				return;
			}
		}



		// Decrement the attack timer.
		builder.attackTimer--;

		// The attack timer is below 1, we can attack.
		if (builder.attackTimer < 1) {
			// Check if the attacker is close enough to attack.
			if (!CombatFactory.checkAttackDistance(builder)) {
				if (builder.getCharacter().isNpc() && builder.getVictim().isPlayer()) {
					if (builder.getLastAttack().elapsed(4500)) {
						((NPC) builder.getCharacter()).setFindNewTarget(true);
					}
				}
				return;
			}

			// Check if the attack can be made on this hook
			if (!builder.getStrategy().canAttack(builder.getCharacter(), builder.getVictim())) {
				builder.getCharacter().getCombatBuilder().reset(builder.getCharacter().isNpc() ? true : false);
				return;
			}

			// Do all combat calculations here, we reincarnate the combat containers
			// using the attacking entity's combat strategy.

			boolean customContainer = builder.getStrategy().customContainerAttack(builder.getCharacter(),
					builder.getVictim());
			CombatContainer container = builder.getContainer();

			builder.getCharacter().setEntityInteraction(builder.getVictim());

			if (builder.getCharacter().isPlayer()) {
				Player player = (Player) builder.getCharacter();
				player.getPacketSender().sendInterfaceRemoval();

				if (player.isSpecialActivated() && player.getCastSpell() == null) {
					container = player.getCombatSpecial().container(player, builder.getVictim());
					boolean magicShortbowSpec = player.getCombatSpecial() != null
							&& player.getCombatSpecial() == CombatSpecial.MAGIC_SHORTBOW;
					CombatSpecial.drain(player, player.getCombatSpecial().getDrainAmount());

					Sounds.sendSound(player,
							Sounds.specialSounds(player.getEquipment().get(Equipment.WEAPON_SLOT).getId()));
					
								
					if (player.getCombatSpecial().getCombatType() == CombatType.RANGED) {
						if (player.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 12926) {
							if (player.getBlowpipeLoading().getContents().isEmpty()) {
								
							}
								if (player.getCombatSpecial().getCombatType() == CombatType.RANGED) {
									if (player.getEquipment().get(Equipment.WEAPON_SLOT).getId() == 12926) {
										if (player.getBlowpipeLoading().getContents().isEmpty()) {
											
											return;
										}
									}
								
								return;
							}
						}
						DefaultRangedCombatStrategy.decrementAmmo(player, builder.getVictim().getPosition());
						if (CombatFactory.darkBow(player)
								|| player.getRangedWeaponData() == RangedWeaponData.MAGIC_SHORTBOW
										&& magicShortbowSpec) {
							DefaultRangedCombatStrategy.decrementAmmo(player, builder.getVictim().getPosition());
						}
					}
				}
			}
			

			// If there is no hit type the combat turn is ignored.
			if (container != null && container.getCombatType() != null) {
				// If we have hit splats to deal, we filter them through combat
				// prayer effects now. If not then we still send the hit tasks
				// next to handle any effects.

				// An attack is going to be made for sure, set the last attacker
				// for this victim.
				builder.getVictim().getCombatBuilder().setLastAttacker(builder.getCharacter());
				builder.getVictim().getLastCombat().reset();

				// Start cooldown if we're using magic and not autocasting.
				if (container.getCombatType() == CombatType.MAGIC && builder.getCharacter().isPlayer()) {
					Player player = (Player) builder.getCharacter();

					if (!player.isAutocast()) {
						if (!player.isSpecialActivated())
							player.getCombatBuilder().cooldown = 10;
						player.setCastSpell(null);
						player.getMovementQueue().setFollowCharacter(null);
						builder.determineStrategy();
					}
				}
				if (builder.getCharacter().isPlayer()) {
					Player player = (Player) builder.getCharacter();

					if (player.getEquipment().get(3) != null) {
						switch (player.getEquipment().get(3).getId()) {
							/*
							 * Hit two times
							 */
							case 21003:
							case 21032:
							case 21033:
							case 21013:
							case 20079:
							case 20862:	
							case 20102:
							case 20607:
							case 21060:
							case 21063:
							case 20603:
							case 21080:
							case 20605:
							case 21079:
							case 4777:
							case 894:
							case 21044:
							case 700:
							case 701:
							case 895:
							case 2867:
							case 896:
							case 17847:
							case 21061:
							case 18989:
							case 17849:
							case 17848:
							case 18355:
							case 21082:
							case 21062:
							case 20510:
							case 6196:
							case 3666:
							case 20601:
							case 20089:
							case 19057:
							case 21077:
							case 21038:
							case 21039:
							case 21040:
							case 21041:
							case 21042:
							case 21043:
							case 3302:
							case 18957:
							case 20504:
							case 18965:
							case 4784:
							case 3961:
							case 18931:
							case 3650:	
							case 18889:
							case 20604:
							case 20115:
							case 20752:
							case 20818:
								container.setHitAmount(2);
								break;
							case 19098:
							case 5168:
							case 20853:
							case 20854:
							case 19005:
								container.setHitAmount(2);
								break;
							case 20602:
							case 20805:
							case 20815:
							case 18932:
							case 20751:
							case 19004:
								container.setHitAmount(3);
								break;
							case 21001:
							case 3717:
							case 3065:
							case 20988:
							case 20998:
							case 3661:
							case 13215:
							case 18891:
							case 20838:
							case 20932:
								container.setHitAmount(4);
								break;
						}
					}

				}
				if (container.getHitDelay() == 0) { // An instant attack
					new CombatHitTask(builder, container).handleAttack();
				} else {

					TaskManager.submit(new CombatHitTask(builder, container, container.getHitDelay(), false));
				}

				builder.setContainer(null); // Fetch a brand new container on
											// next attack
			}

			// Reset the attacking entity.
			builder.attackTimer = builder.getStrategy() != null
					? builder.getStrategy().attackDelay(builder.getCharacter())
					: builder.getCharacter().getAttackSpeed();
			builder.getLastAttack().reset();
			builder.getCharacter().setEntityInteraction(builder.getVictim());
		}
	}
}
