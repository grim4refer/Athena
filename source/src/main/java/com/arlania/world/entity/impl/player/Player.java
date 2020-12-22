package com.arlania.world.entity.impl.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import com.arlania.model.*;
import com.arlania.model.movement.MovementQueue;
import com.arlania.net.packet.*;
import com.arlania.world.content.combat.magic.CustomMagicStaff;
import com.arlania.world.content.minigames.impl.weapon.WeaponGame;
import com.arlania.world.content.mysteryboxes.MysteryBox;
import com.arlania.world.entity.Entity;
import org.jboss.netty.channel.Channel;
import com.arlania.GameSettings;
import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.engine.task.impl.PlayerDeathTask;
import com.arlania.engine.task.impl.WalkToTask;
import com.arlania.model.container.impl.Bank;
import com.arlania.model.container.impl.Equipment;
import com.arlania.model.container.impl.Inventory;
import com.arlania.model.container.impl.PriceChecker;
import com.arlania.model.container.impl.Shop;
import com.arlania.model.container.impl.Bank.BankSearchAttributes;
import com.arlania.model.container.impl.DungeoneeringIronEquipment;
import com.arlania.model.container.impl.DungeoneeringIronInventory;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.model.definitions.WeaponAnimations;
import com.arlania.model.definitions.WeaponInterfaces;
import com.arlania.model.definitions.WeaponInterfaces.WeaponInterface;
import com.arlania.model.input.Input;
import com.arlania.net.PlayerSession;
import com.arlania.net.SessionState;
import com.arlania.util.FrameUpdater;
import com.arlania.util.Misc;
import com.arlania.util.Stopwatch;
import com.arlania.world.World;
import com.arlania.world.content.*;
import com.arlania.world.content.Achievements.AchievementAttributes;
import com.arlania.world.content.BankPin.BankPinAttributes;
import com.arlania.world.content.DropLog.DropLogEntry;
import com.arlania.world.content.KillsTracker.KillsEntry;
import com.arlania.world.content.LoyaltyProgramme.LoyaltyTitles;
import com.arlania.world.content.StartScreen.GameModes;
import com.arlania.world.content.StarterTasks.StarterTaskAttributes;
//import com.arlania.world.content.auras.AuraManager;
import com.arlania.world.content.clan.ClanChat;
import com.arlania.world.content.combat.CombatFactory;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.effect.CombatPoisonEffect.CombatPoisonData;
import com.arlania.world.content.combat.magic.CombatSpell;
import com.arlania.world.content.combat.magic.CombatSpells;
import com.arlania.world.content.combat.prayer.CurseHandler;
import com.arlania.world.content.combat.prayer.PrayerHandler;
import com.arlania.world.content.combat.pvp.PlayerKillingAttributes;
import com.arlania.world.content.combat.range.CombatRangedAmmo.RangedWeaponData;
import com.arlania.world.content.combat.strategy.CombatStrategies;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.content.combat.weapon.CombatSpecial;
import com.arlania.world.content.combat.weapon.FightType;
import com.arlania.world.content.dialogue.Dialogue;
import com.arlania.world.content.dialogue.DialogueExpression;
import com.arlania.world.content.dialogue.DialogueType;
import com.arlania.world.content.grandexchange.GrandExchangeSlot;
import com.arlania.world.content.itemloading.BlowpipeLoading;
import com.arlania.world.content.itemloading.DragonRageLoading;
import com.arlania.world.content.itemloading.CorruptBandagesLoading;
import com.arlania.world.content.itemloading.MinigunLoading;
import com.arlania.world.content.minigames.Minigame;
import com.arlania.world.content.minigames.MinigameAttributes;
import com.arlania.world.content.minigames.impl.Dueling;
import com.arlania.world.content.pos.PlayerOwnedShopManager;
import com.arlania.world.content.raids.immortal.ImmortalRaid;
import com.arlania.world.content.raids.immortal.ImmortalRaidsManager;
import com.arlania.world.content.skill.SkillManager;
import com.arlania.world.content.skill.impl.construction.HouseFurniture;
import com.arlania.world.content.skill.impl.construction.Portal;
import com.arlania.world.content.skill.impl.construction.Room;
import com.arlania.world.content.skill.impl.construction.ConstructionData.HouseLocation;
import com.arlania.world.content.skill.impl.construction.ConstructionData.HouseTheme;
import com.arlania.world.content.skill.impl.farming.Farming;
import com.arlania.world.content.skill.impl.slayer.Slayer;
import com.arlania.world.content.skill.impl.summoning.Pouch;
import com.arlania.world.content.skill.impl.summoning.Summoning;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.model.container.impl.GambleOfferItemContainer;
import com.arlania.world.content.gambling.GamblingManager;

import javax.security.auth.callback.Callback;

public class Player extends Character {
	
	public boolean inRaid;
	
	private com.arlania.world.content.TeleportInterface teleportInterface = new com.arlania.world.content.TeleportInterface(this);
	
	public boolean isVoting = false;
	
	public TeleportInterface getTeleportInterface() {
		return this.teleportInterface;
	}

	private int[] maxCapeColors = { 65214, 65200, 65186, 62995 };
	private PVMRanking pvmRanking = new PVMRanking(this);

	public int[] getMaxCapeColors() {
		return maxCapeColors;
	}

	public void setMaxCapeColors(int[] maxCapeColors) {
		this.maxCapeColors = maxCapeColors;
	}

	private String title = "";
	private boolean infinityHealth = false;

	public ArrayList<String> slowEvents = new ArrayList<String>();

	/**
	 * Kc system
	 */
	
	public Map<Integer, Integer> getNpcKillCount() {
		return npcKillCountMap;
	}

	public void setNpcKillCount(Map<Integer, Integer> dataMap) {
		this.npcKillCountMap = dataMap;
	}

	public void addNpcKillCount(int npcId) {
		npcKillCountMap.merge(npcId, 1, Integer::sum);
	}

	private Map<Integer, Integer> npcKillCountMap = new HashMap<>();
	
	public int getNpcKillCount(int npcId) {
		return npcKillCountMap.get(npcId) == null ? 0 : npcKillCountMap.get(npcId);
	}
	private int winstreak;
	// public boolean toggledPets;

	private boolean placeholders = true; // lmao that should work i think

	public boolean isPlaceholders() {
		return placeholders;
	}

	public void setPlaceholders(boolean placeholders) {
		this.placeholders = placeholders;
	}

	private int transform;

	public boolean getInfinityHealth() {
		return infinityHealth;
	}

	public void setInfinityHealth(Boolean x) {
		infinityHealth = x;
	}

	public int getTransform() {
		return transform;
	}

	public void setTransform(int npcId) {
		this.transform = npcId;
	}

	public int getWinStreak() {
		return winstreak;
	}

	public void setWinStreak(int npcId) {
		this.winstreak = npcId;
	}

	private boolean instance = false;
	private final PlayerOwnedShopManager playerOwnedShopManager = new PlayerOwnedShopManager(this);

	private boolean active;

	private boolean Invited = false;
	private int[] InvitedCoords = new int[] { 0, 0, 0 };
	private boolean shopUpdated;

	private boolean indung;

	public PlayerOwnedShopManager getPlayerOwnedShopManager() {
		return playerOwnedShopManager;
	}

	public boolean getInvited() {
		return Invited;
	}

	public int[] getInvitedCoords() {
		return InvitedCoords;
	}

	public boolean getInstance() {
		return instance;
	}

	public void setInstance(boolean instance2) {
		this.instance = instance2;
	}

	public void setSpinning(boolean Spinning) {
		this.Spinning = Spinning;

	}

	public static boolean Spinning = false;
	private MysteryBox mysteryBox = new MysteryBox(this);

	public MysteryBox getMysteryBox() {
		return mysteryBox;
	}

	public boolean doubleDrops = false;

	private int mbox;

	public int getmbox() {
		return mbox;
	}

	public Player setmbox(int mbox) {
		this.mbox = mbox;
		return this;
	}

	public static int[] guns = { 21062, 20695, 18931, 3650, 18889, 21082, 20695, 4784, 20932, 21077, 21079, 894, 21044, 700, 701,
			895, 896, 2867, 21080, 20805, 19098, 20853, 20854, 21038, 21039, 21040, 21041, 21042, 21043 };

	public static int[] getGuns() {
		return guns;
	}

	public void setInvited(boolean i) {
		this.Invited = i;
	}

	public void setInvitedCoords(int[] i) {
		this.InvitedCoords = i;
	}

	public int box = 0;
	public int currentTabs;
	public int clickindex;

	public void setBox(int box1) {
		this.box = box1;
	}

	public int getBox() {
		return box;
	}

	public Player(PlayerSession playerIO) {
		super(GameSettings.DEFAULT_POSITION.copy());
		this.session = playerIO;
	}

	/**
	 * The gambling manager
	 */
	private final GamblingManager gamblingManager = new GamblingManager();
	/**
	 * The gambling offer container
	 */
	private final GambleOfferItemContainer gambleOffer = new GambleOfferItemContainer(this);

	private Map<String, Object> attributes = new HashMap<>();

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key) {
		return (T) attributes.get(key);
	}

	private Minigame minigame = null;

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key, T fail) {
		Object object = attributes.get(key);
		return object == null ? fail : (T) object;
	}

	public boolean hasAttribute(String key) {
		return attributes.containsKey(key);
	}

	public void removeAttribute(String key) {
		attributes.remove(key);
	}

	private int hardwareNumber;

	public int getHardwareNumber() {
		return hardwareNumber;
	}

	public Player setHardwareNumber(int hardwareNumber) {
		this.hardwareNumber = hardwareNumber;
		return this;
	}

	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	@Override
	public void appendDeath() {
		if (!isDying) {
			if (infinityHealth) {
				heal(999);
				return;
			}
			isDying = true;
			TaskManager.submit(new PlayerDeathTask(this));
		}
	}

	private int ArcticPSPoints;

	public int getArcticPSPoints() {
		return ArcticPSPoints;
	}

	public void setArcticPSPoints(int ArcticPSPoints) {
		this.ArcticPSPoints = ArcticPSPoints;
	}

	public void incrementArcticPSPoints(double amount) {
		this.ArcticPSPoints -= amount;
	}

	private boolean betaTester;

	private int bossPoints;
	private int weaponGamePoints;

	public int getBossPoints() {
		return bossPoints;
	}

	public void setBossPoints(int bossPoints) {
		this.bossPoints = bossPoints;
	}


	public boolean getStarterClaimed() {
		return starterClaimed;
	}

	public void setStarterClaimed(boolean starterClaimed) {
		this.starterClaimed = starterClaimed;
	}

	public boolean starterTaskCompleted;

	public boolean isStarterTaskCompleted() {
		return starterTaskCompleted;
	}

	public void setStarterTaskCompleted() {
		starterTaskCompleted = true;
	}

	private int npcKills;

	public int getNpcKills() {
		return npcKills;
	}

	private int npcTransformationId;

	public Character setNpcTransformationId(int npcTransformationId) {
		this.npcTransformationId = npcTransformationId;
		return this;
	}

	public int getNpcTransformationId() {
		return npcTransformationId;
	}

	private final Appearance appearance = new Appearance(this);

	public void setBetaTester(boolean beta) {
		this.betaTester = beta;
	}

	public boolean getBetaTester() {
		return betaTester;
	}

	public void setNpcKills(int npcKills) {
		this.npcKills = npcKills;
	}

	/*
	 * Variables for DropTable & Player Profiling
	 * 
	 * @author Levi Patton
	 * 
	 * @www.rune-server.org/members/auguryps
	 */
	public Player dropLogPlayer;
	public boolean dropLogOrder;
	private PlayerDropLog playerDropLog = new PlayerDropLog();
	private ProfileViewing profile = new ProfileViewing();
	
	//public AuraManager auraManager = new AuraManager(this);

	/*
	 * Variables for the DropLog
	 * 
	 * @author Levi Patton
	 */
	public PacketSender getPA() {
		return getPacketSender();
	}

	public PlayerDropLog getPlayerDropLog() {
		return playerDropLog;
	}

	public ProfileViewing getProfile() {
		return profile;
	}

	public void setProfile(ProfileViewing profile) {
		this.profile = profile;
	}

	public void setPlayerDropLog(PlayerDropLog playerDropLog) {
		this.playerDropLog = playerDropLog;
	}

	@Override
	public int getConstitution() {
		return getSkillManager().getCurrentLevel(Skill.CONSTITUTION);
	}

	@Override
	public Character setConstitution(int constitution) {
		if (isDying) {
			return this;
		}
		skillManager.setCurrentLevel(Skill.CONSTITUTION, constitution);
		packetSender.sendSkill(Skill.CONSTITUTION);
		if (getConstitution() <= 0 && !isDying) {
			appendDeath();
		}
		return this;
	}

	@Override
	public void heal(int amount) {
		int level = skillManager.getMaxLevel(Skill.CONSTITUTION) + immortalRaidsCompleted;
		if ((skillManager.getCurrentLevel(Skill.CONSTITUTION) + amount) >= level) {
			setConstitution(level);
		} else {
			setConstitution(skillManager.getCurrentLevel(Skill.CONSTITUTION) + amount);
		}
	}
	
	public void raidsHeal(int amount) {
	   setConstitution(skillManager.getCurrentLevel(Skill.CONSTITUTION) + amount);
	}

	@Override
	public int getBaseAttack(CombatType type) {
		if (type == CombatType.RANGED) {
			return skillManager.getCurrentLevel(Skill.RANGED);
		} else if (type == CombatType.MAGIC) {
			return skillManager.getCurrentLevel(Skill.MAGIC);
		}
		return skillManager.getCurrentLevel(Skill.ATTACK);
	}

	@Override
	public int getBaseDefence(CombatType type) {
		if (type == CombatType.MAGIC) {
			return skillManager.getCurrentLevel(Skill.MAGIC);
		}
		return skillManager.getCurrentLevel(Skill.DEFENCE);
	}

	/**
	 * The max amount of players that can be added per cycle.
	 */
	private static final int MAX_NEW_PLAYERS_PER_CYCLE = 25;

	/**
	 * Loops through the associated player's {@code localPlayer} list and updates
	 * them.
	 * 
	 * @return The PlayerUpdating instance.
	 */
	// public boolean hasPet() {
	// return this.getSummoning() != null && this.getSummoning().getFamiliar() !=
	// null && this.getSummoning().getFamiliar().isPet();
	// }
	public static void update(final Player player) {
		PacketBuilder update = new PacketBuilder();
		PacketBuilder packet = new PacketBuilder(81, Packet.PacketType.SHORT);
		packet.initializeAccess(PacketBuilder.AccessType.BIT);
		updateMovement(player, packet);
		appendUpdates(player, update, player, false, true);
		packet.putBits(8, player.getLocalPlayers().size());
		for (Iterator<Player> playerIterator = player.getLocalPlayers().iterator(); playerIterator.hasNext();) {
			Player otherPlayer = playerIterator.next();
			if (World.getPlayers().get(otherPlayer.getIndex()) != null && otherPlayer.isVisible()
					&& otherPlayer.getPosition().isWithinDistance(player.getPosition())
					&& !otherPlayer.isNeedsPlacement()) {
				updateOtherPlayerMovement(packet, otherPlayer);
				if (otherPlayer.getUpdateFlag().isUpdateRequired()) {
					appendUpdates(player, update, otherPlayer, false, false);
				}
			} else {
				playerIterator.remove();
				packet.putBits(1, 1);
				packet.putBits(2, 3);
			}
		}
		int playersAdded = 0;

		for (Player otherPlayer : World.getPlayers()) {
			if (player.getLocalPlayers().size() >= 79 || playersAdded > MAX_NEW_PLAYERS_PER_CYCLE)
				break;
			if (otherPlayer == null || otherPlayer == player || !player.isVisible()
					|| player.getLocalPlayers().contains(otherPlayer)
					|| !otherPlayer.getPosition().isWithinDistance(player.getPosition()))
				continue;
			player.getLocalPlayers().add(otherPlayer);
			addPlayer(player, otherPlayer, packet);
			appendUpdates(player, update, otherPlayer, true, false);
			playersAdded++;
		}

		if (update.buffer().writerIndex() > 0) {
			packet.putBits(11, 2047);
			packet.initializeAccess(PacketBuilder.AccessType.BYTE);
			packet.putBytes(update.buffer());
		} else {
			packet.initializeAccess(PacketBuilder.AccessType.BYTE);
		}
		player.getSession().queueMessage(packet);
	}

	/**
	 * Adds a new player to the associated player's client.
	 * 
	 * @param target
	 *            The player to add to the other player's client.
	 * @param builder
	 *            The packet builder to write information on.
	 * @return The PlayerUpdating instance.
	 */
	private static void addPlayer(Player player, Player target, PacketBuilder builder) {
		builder.putBits(11, target.getIndex());
		builder.putBits(1, 1);
		builder.putBits(1, 1);
		int yDiff = target.getPosition().getY() - player.getPosition().getY();
		int xDiff = target.getPosition().getX() - player.getPosition().getX();
		builder.putBits(5, yDiff);
		builder.putBits(5, xDiff);
	}

	/**
	 * Updates the associated player's movement queue.
	 * 
	 * @param builder
	 *            The packet builder to write information on.
	 * @return The PlayerUpdating instance.
	 */
	private static void updateMovement(Player player, PacketBuilder builder) {
		/*
		 * Check if the player is teleporting.
		 */
		if (player.isNeedsPlacement() || player.isChangingRegion()) {
			/*
			 * They are, so an update is required.
			 */
			builder.putBits(1, 1);

			/*
			 * This value indicates the player teleported.
			 */
			builder.putBits(2, 3);

			/*
			 * This is the new player height.
			 */
			builder.putBits(2, player.getPosition().getZ());

			/*
			 * This indicates that the client should discard the walking queue.
			 */
			builder.putBits(1, player.isResetMovementQueue() ? 1 : 0);

			/*
			 * This flag indicates if an update block is appended.
			 */
			builder.putBits(1, player.getUpdateFlag().isUpdateRequired() ? 1 : 0);

			/*
			 * These are the positions.
			 */
			builder.putBits(7, player.getPosition().getLocalY(player.getLastKnownRegion()));
			builder.putBits(7, player.getPosition().getLocalX(player.getLastKnownRegion()));
		} else /*
				 * Otherwise, check if the player moved.
				 */
		if (player.getPrimaryDirection().toInteger() == -1) {
			/*
			 * The player didn't move. Check if an update is required.
			 */
			if (player.getUpdateFlag().isUpdateRequired()) {
				/*
				 * Signifies an update is required.
				 */
				builder.putBits(1, 1);

				/*
				 * But signifies that we didn't move.
				 */
				builder.putBits(2, 0);
			} else
				/*
				 * Signifies that nothing changed.
				 */
				builder.putBits(1, 0);
		} else /*
				 * Check if the player was running.
				 */
		if (player.getSecondaryDirection().toInteger() == -1) {

			/*
			 * The player walked, an update is required.
			 */
			builder.putBits(1, 1);

			/*
			 * This indicates the player only walked.
			 */
			builder.putBits(2, 1);

			/*
			 * This is the player's walking direction.
			 */

			builder.putBits(3, player.getPrimaryDirection().toInteger());

			/*
			 * This flag indicates an update block is appended.
			 */
			builder.putBits(1, player.getUpdateFlag().isUpdateRequired() ? 1 : 0);
		} else {

			/*
			 * The player ran, so an update is required.
			 */
			builder.putBits(1, 1);

			/*
			 * This indicates the player ran.
			 */
			builder.putBits(2, 2);

			/*
			 * This is the walking direction.
			 */
			builder.putBits(3, player.getPrimaryDirection().toInteger());

			/*
			 * And this is the running direction.
			 */
			builder.putBits(3, player.getSecondaryDirection().toInteger());

			/*
			 * And this flag indicates an update block is appended.
			 */
			builder.putBits(1, player.getUpdateFlag().isUpdateRequired() ? 1 : 0);
		}
	}

	/**
	 * Updates another player's movement queue.
	 * 
	 * @param builder
	 *            The packet builder to write information on.
	 * @param target
	 *            The player to update movement for.
	 * @return The PlayerUpdating instance.
	 */
	private static void updateOtherPlayerMovement(PacketBuilder builder, Player target) {
		/*
		 * Check which type of movement took place.
		 */
		if (target.getPrimaryDirection().toInteger() == -1) {
			/*
			 * If no movement did, check if an update is required.
			 */
			if (target.getUpdateFlag().isUpdateRequired()) {
				/*
				 * Signify that an update happened.
				 */
				builder.putBits(1, 1);

				/*
				 * Signify that there was no movement.
				 */
				builder.putBits(2, 0);
			} else
				/*
				 * Signify that nothing changed.
				 */
				builder.putBits(1, 0);
		} else if (target.getSecondaryDirection().toInteger() == -1) {
			/*
			 * The player moved but didn't run. Signify that an update is required.
			 */
			builder.putBits(1, 1);

			/*
			 * Signify we moved one tile.
			 */
			builder.putBits(2, 1);

			/*
			 * Write the primary sprite (i.e. walk direction).
			 */
			builder.putBits(3, target.getPrimaryDirection().toInteger());

			/*
			 * Write a flag indicating if a block update happened.
			 */
			builder.putBits(1, target.getUpdateFlag().isUpdateRequired() ? 1 : 0);
		} else {
			/*
			 * The player ran. Signify that an update happened.
			 */
			builder.putBits(1, 1);

			/*
			 * Signify that we moved two tiles.
			 */
			builder.putBits(2, 2);

			/*
			 * Write the primary sprite (i.e. walk direction).
			 */
			builder.putBits(3, target.getPrimaryDirection().toInteger());

			/*
			 * Write the secondary sprite (i.e. run direction).
			 */
			builder.putBits(3, target.getSecondaryDirection().toInteger());

			/*
			 * Write a flag indicating if a block update happened.
			 */
			builder.putBits(1, target.getUpdateFlag().isUpdateRequired() ? 1 : 0);
		}
	}

	/**
	 * Appends a player's update mask blocks.
	 * 
	 * @param builder
	 *            The packet builder to write information on.
	 * @param target
	 *            The player to update masks for.
	 * @param updateAppearance
	 *            Update the player's appearance without the flag being set?
	 * @param noChat
	 *            Do not allow player to chat?
	 * @return The PlayerUpdating instance.
	 */
	private static void appendUpdates(Player player, PacketBuilder builder, Player target, boolean updateAppearance,
			boolean noChat) {
		if (!target.getUpdateFlag().isUpdateRequired() && !updateAppearance)
			return;
		/*
		 * if (player.getCachedUpdateBlock() != null && !player.equals(target) &&
		 * !updateAppearance && !noChat) {
		 * builder.putBytes(player.getCachedUpdateBlock()); return; }
		 */
		// synchronized (target) {
		final UpdateFlag flag = target.getUpdateFlag();
		int mask = 0;
		if (flag.flagged(Flag.GRAPHIC) && target.getGraphic() != null) {
			mask |= 0x100;
		}
		if (flag.flagged(Flag.ANIMATION) && target.getAnimation() != null) {
			mask |= 0x8;
		}
		if (flag.flagged(Flag.FORCED_CHAT) && target.getForcedChat().length() > 0) {
			mask |= 0x4;
		}
		if (flag.flagged(Flag.CHAT) && !noChat
				&& !player.getRelations().getIgnoreList().contains(target.getLongUsername())) {
			mask |= 0x80;
		}
		if (flag.flagged(Flag.ENTITY_INTERACTION)) {
			mask |= 0x1;
		}
		if (flag.flagged(Flag.APPEARANCE) || updateAppearance) {
			mask |= 0x10;
		}
		if (flag.flagged(Flag.FACE_POSITION)) {
			mask |= 0x2;
		}
		if (flag.flagged(Flag.SINGLE_HIT)) {
			mask |= 0x20;
		}
		if (flag.flagged(Flag.DOUBLE_HIT)) {
			mask |= 0x200;
		}
		if (flag.flagged(Flag.FORCED_MOVEMENT) && target.getForceMovement() != null) {
			mask |= 0x400;
		}
		if (mask >= 0x100) {
			mask |= 0x40;
			builder.put((mask & 0xFF));
			builder.put((mask >> 8));
		} else {
			builder.put(mask);
		}
		if (flag.flagged(Flag.FORCED_MOVEMENT) && target.getForceMovement() != null) {
			updateForcedMovement(player, builder, target);
		}
		if (flag.flagged(Flag.GRAPHIC) && target.getGraphic() != null) {
			updateGraphics(builder, target);
		}
		if (flag.flagged(Flag.ANIMATION) && target.getAnimation() != null) {
			updateAnimation(builder, target);
		}
		if (flag.flagged(Flag.FORCED_CHAT) && target.getForcedChat().length() > 0) {
			updateForcedChat(builder, target);
		}
		if (flag.flagged(Flag.CHAT) && !noChat
				&& !player.getRelations().getIgnoreList().contains(target.getLongUsername())) {
			updateChat(builder, target);
		}
		if (flag.flagged(Flag.ENTITY_INTERACTION)) {
			updateEntityInteraction(builder, target);
		}
		if (flag.flagged(Flag.APPEARANCE) || updateAppearance) {
			Player.updateAppearance(player, builder, target);
		}
		if (flag.flagged(Flag.FACE_POSITION)) {
			updateFacingPosition(builder, target);
		}
		if (flag.flagged(Flag.SINGLE_HIT)) {
			updateSingleHit(builder, target);
		}
		if (flag.flagged(Flag.DOUBLE_HIT)) {
			updateDoubleHit(builder, target);
		}
		/*
		 * if (!player.equals(target) && !updateAppearance && !noChat) {
		 * player.setCachedUpdateBlock(cachedBuffer.buffer()); }
		 */
		// }
	}

	/**
	 * This update block is used to update player chat.
	 * 
	 * @param builder
	 *            The packet builder to write information on.
	 * @param target
	 *            The player to update chat for.
	 * @return The PlayerUpdating instance.
	 */
	private static void updateChat(PacketBuilder builder, Player target) {
		ChatMessage.Message message = target.getChatMessages().get();
		byte[] bytes = message.getText();
		builder.putShort(((message.getColour() & 0xff) << 8) | (message.getEffects() & 0xff), ByteOrder.LITTLE);
		builder.put(target.getRights().ordinal());
		builder.put(target.getGameMode().ordinal());
		builder.put(bytes.length, ValueType.C);
		builder.putBytesReverse(bytes);
	}

	/**
	 * This update block is used to update forced player chat.
	 * 
	 * @param builder
	 *            The packet builder to write information on.
	 * @param target
	 *            The player to update forced chat for.
	 * @return The PlayerUpdating instance.
	 */
	private static void updateForcedChat(PacketBuilder builder, Player target) {
		builder.putString(target.getForcedChat());
	}

	/**
	 * This update block is used to update forced player movement.
	 * 
	 * @param builder
	 *            The packet builder to write information on.
	 * @param target
	 *            The player to update forced movement for.
	 * @return The PlayerUpdating instance.
	 */
	private static void updateForcedMovement(Player player, PacketBuilder builder, Player target) {
		Position position = target.getPosition();
		Position myPosition = player.getLastKnownRegion();
		builder.put((position.getLocalX(myPosition) + target.getForceMovement()[MovementQueue.FIRST_MOVEMENT_X]),
				ValueType.S);
		builder.put((position.getLocalY(myPosition) + target.getForceMovement()[MovementQueue.FIRST_MOVEMENT_Y]),
				ValueType.S);
		builder.put((position.getLocalX(myPosition) + target.getForceMovement()[MovementQueue.SECOND_MOVEMENT_X]),
				ValueType.S);
		builder.put((position.getLocalY(myPosition) + target.getForceMovement()[MovementQueue.SECOND_MOVEMENT_Y]),
				ValueType.S);
		builder.putShort(target.getForceMovement()[MovementQueue.MOVEMENT_SPEED], ValueType.A, ByteOrder.LITTLE);
		builder.putShort(target.getForceMovement()[MovementQueue.MOVEMENT_REVERSE_SPEED], ValueType.A);
		builder.put(target.getForceMovement()[MovementQueue.MOVEMENT_DIRECTION], ValueType.S);
	}

	/**
	 * This update block is used to update a player's animation.
	 * 
	 * @param builder
	 *            The packet builder to write information on.
	 * @param target
	 *            The player to update animations for.
	 * @return The PlayerUpdating instance.
	 */
	private static void updateAnimation(PacketBuilder builder, Player target) {
		builder.putShort(target.getAnimation().getId(), ByteOrder.LITTLE);
		builder.put(target.getAnimation().getDelay(), ValueType.C);
	}

	/**
	 * This update block is used to update a player's graphics.
	 * 
	 * @param builder
	 *            The packet builder to write information on.
	 * @param target
	 *            The player to update graphics for.
	 * @return The PlayerUpdating instance.
	 */
	private static void updateGraphics(PacketBuilder builder, Player target) {
		builder.putShort(target.getGraphic().getId(), ByteOrder.LITTLE);
		builder.putInt(
				((target.getGraphic().getHeight().ordinal() * 50) << 16) + (target.getGraphic().getDelay() & 0xffff));
	}

	/**
	 * This update block is used to update a player's single hit.
	 * 
	 * @param builder
	 *            The packet builder used to write information on.
	 * @param target
	 *            The player to update the single hit for.
	 * @return The PlayerUpdating instance.
	 */
	private static void updateSingleHit(PacketBuilder builder, Player target) {
		builder.putShort(target.getPrimaryHit().getDamage(), ValueType.A);
		builder.put(target.getPrimaryHit().getHitmask().ordinal());
		builder.put(target.getPrimaryHit().getCombatIcon().ordinal() - 1);
		builder.putShort(target.getPrimaryHit().getAbsorb(), ValueType.A);
		builder.putShort(target.getConstitution(), ValueType.A);
		builder.putShort(target.getSkillManager().getMaxLevel(Skill.CONSTITUTION), ValueType.A);
	}

	/**
	 * This update block is used to update a player's double hit.
	 * 
	 * @param builder
	 *            The packet builder used to write information on.
	 * @param target
	 *            The player to update the double hit for.
	 * @return The PlayerUpdating instance.
	 */
	private static void updateDoubleHit(PacketBuilder builder, Player target) {
		builder.putShort(target.getSecondaryHit().getDamage(), ValueType.A);
		builder.put(target.getSecondaryHit().getHitmask().ordinal());
		builder.put(target.getSecondaryHit().getCombatIcon().ordinal() - 1);
		builder.putShort(target.getPrimaryHit().getAbsorb(), ValueType.A);
		builder.putShort(target.getConstitution(), ValueType.A);
		builder.putShort(target.getSkillManager().getMaxLevel(Skill.CONSTITUTION), ValueType.A);
	}

	/**
	 * This update block is used to update a player's face position.
	 * 
	 * @param builder
	 *            The packet builder to write information on.
	 * @param target
	 *            The player to update face position for.
	 * @return The PlayerUpdating instance.
	 */
	private static void updateFacingPosition(PacketBuilder builder, Player target) {
		final Position position = target.getPositionToFace();
		int x = position == null ? 0 : position.getX();
		int y = position == null ? 0 : position.getY();
		builder.putShort(x * 2 + 1, ValueType.A, ByteOrder.LITTLE);
		builder.putShort(y * 2 + 1, ByteOrder.LITTLE);
	}

	/**
	 * This update block is used to update a player's entity interaction.
	 * 
	 * @param builder
	 *            The packet builder to write information on.
	 * @param target
	 *            The player to update entity interaction for.
	 * @return The PlayerUpdating instance.
	 */
	private static void updateEntityInteraction(PacketBuilder builder, Player target) {
		Entity entity = target.getInteractingEntity();
		if (entity != null) {
			int index = entity.getIndex();
			if (entity instanceof Player)
				index += +32768;
			builder.putShort(index, ByteOrder.LITTLE);
		} else {
			builder.putShort(-1, ByteOrder.LITTLE);
		}
	}

	/**
	 * Resets the associated player's flags that will need to be removed upon
	 * completion of a single update.
	 * 
	 * @return The PlayerUpdating instance.
	 */
	public static void resetFlags(Player player) {
		player.getUpdateFlag().reset();
		player.setRegionChange(false);
		player.setTeleporting(false).setForcedChat("");
		player.setResetMovementQueue(false);
		player.setNeedsPlacement(false);
		player.setPrimaryDirection(Direction.NONE);
		player.setSecondaryDirection(Direction.NONE);
	}

	/**
	 * This update block is used to update a player's appearance, this includes
	 * their equipment, clothing, combat level, gender, head icons, user name and
	 * animations.
	 * 
	 * @param target
	 *            The player to update appearance for.
	 * @return The PlayerUpdating instance.
	 */
	public static void updateAppearance(Player player, PacketBuilder out, Player target) {
		for (Player p : World.getPlayers()) {

		}
		Appearance appearance = target.getAppearance();
		Equipment equipment = target.getEquipment();
		PacketBuilder properties = new PacketBuilder();
		properties.put(appearance.getGender().ordinal());
		properties.put(appearance.getHeadHint());
		properties.put(target.getLocation() == Locations.Location.WILDERNESS ? appearance.getBountyHunterSkull() : -1);
		properties.putShort(target.getSkullIcon());

		if (target.getNpcTransformationId() > 1) {
			properties.putShort(-1);
			properties.putShort(target.getNpcTransformationId());
		} else {
			int[] equip = new int[equipment.capacity()];
			for (int i = 0; i < equipment.capacity(); i++) {
				equip[i] = equipment.getItems()[i].getId();
			}
			if (equip[Equipment.HEAD_SLOT] > -1) {
				properties.putShort(0x200 + equip[Equipment.HEAD_SLOT]);
			} else {
				properties.put(0);
			}
			if (equip[Equipment.CAPE_SLOT] > -1) {
				properties.putShort(0x200 + equip[Equipment.CAPE_SLOT]);
				if (equip[Equipment.CAPE_SLOT] == 14019) {
					/*
					 * int[] modelColors = new int[] { 65214, 65200, 65186, 62995 };
					 * if(target.getUsername().equalsIgnoreCase("apache ah64")) { modelColors[0] =
					 * 926;//cape modelColors[1] = 350770;//cape modelColors[2] = 928;//outline
					 * modelColors[3] = 130770;//cape } else
					 * if(target.getUsername().equalsIgnoreCase("apache ah66")) { modelColors[0] =
					 * 926;//cape modelColors[1] = 302770;//cape modelColors[2] = 928;//outline
					 * modelColors[3] = 302770;//cape }
					 */
					int[] modelColors = target.getMaxCapeColors();
					// System.out.println("Updating: "+Arrays.toString(modelColors));
					if (modelColors != null) {
						properties.put(modelColors.length);
						for (int i = 0; i < modelColors.length; i++) {
							properties.putInt(modelColors[i]);
						}
					} else {
						properties.put(0);
					}
				} else {
					properties.put(0);
				}
			} else {
				properties.put(0);
			}
			if (equip[Equipment.AMULET_SLOT] > -1) {
				properties.putShort(0x200 + equip[Equipment.AMULET_SLOT]);
			} else {
				properties.put(0);
			}
			if (equip[Equipment.WEAPON_SLOT] > -1) {
				properties.putShort(0x200 + equip[Equipment.WEAPON_SLOT]);
			} else {
				properties.put(0);
			}
			if (equip[Equipment.BODY_SLOT] > -1) {
				properties.putShort(0x200 + equip[Equipment.BODY_SLOT]);
			} else {
				properties.putShort(0x100 + appearance.getLook()[Appearance.CHEST]);
			}
			if (equip[Equipment.SHIELD_SLOT] > -1) {
				properties.putShort(0x200 + equip[Equipment.SHIELD_SLOT]);
			} else {
				properties.put(0);
			}

			if (ItemDefinition.forId(equip[Equipment.BODY_SLOT]).isFullBody()) {
				properties.put(0);
			} else {
				properties.putShort(0x100 + appearance.getLook()[Appearance.ARMS]);
			}

			if (equip[Equipment.LEG_SLOT] > -1) {
				properties.putShort(0x200 + equip[Equipment.LEG_SLOT]);
			} else {
				properties.putShort(0x100 + appearance.getLook()[Appearance.LEGS]);
			}

			if (ItemDefinition.forId(equip[Equipment.HEAD_SLOT]).isFullHelm()) {
				properties.put(0);
			} else {
				properties.putShort(0x100 + appearance.getLook()[Appearance.HEAD]);
			}
			if (equip[Equipment.HANDS_SLOT] > -1) {
				properties.putShort(0x200 + equip[Equipment.HANDS_SLOT]);
			} else {
				properties.putShort(0x100 + appearance.getLook()[Appearance.HANDS]);
			}
			if (equip[Equipment.FEET_SLOT] > -1) {
				properties.putShort(0x200 + equip[Equipment.FEET_SLOT]);
			} else {
				properties.putShort(0x100 + appearance.getLook()[Appearance.FEET]);
			}
			if (appearance.getLook()[Appearance.BEARD] <= 0 || appearance.getGender().equals(Gender.FEMALE)) {
				properties.put(0);
			} else {
				properties.putShort(0x100 + appearance.getLook()[Appearance.BEARD]);
			}
		}
		properties.put(appearance.getLook()[Appearance.HAIR_COLOUR]);
		properties.put(appearance.getLook()[Appearance.TORSO_COLOUR]);
		properties.put(appearance.getLook()[Appearance.LEG_COLOUR]);
		properties.put(appearance.getLook()[Appearance.FEET_COLOUR]);
		properties.put(appearance.getLook()[Appearance.SKIN_COLOUR]);

		int skillAnim = target.getSkillAnimation();
		if (skillAnim > 0) {
			for (int i = 0; i < 7; i++)
				properties.putShort(skillAnim);
		} else {
			properties.putShort(target.getCharacterAnimations().getStandingAnimation());
			properties.putShort(0x337);
			properties.putShort(target.getCharacterAnimations().getWalkingAnimation());
			properties.putShort(0x334);
			properties.putShort(0x335);
			properties.putShort(0x336);
			properties.putShort(target.getCharacterAnimations().getRunningAnimation());
		}

		properties.putLong(target.getLongUsername());
		properties.put(target.getSkillManager().getCombatLevel());
		properties.putShort(target.getRights().ordinal());
		properties.putString(target.getTitle());
		// System.out.println("test - "+target.getTitle());
		// properties.putShort(target.getLoyaltyTitle().ordinal());

		out.put(properties.buffer().writerIndex(), ValueType.C);
		out.putBytes(properties.buffer());
	}

	@Override
	public int getAttackSpeed() {
		int speed = weapon.getSpeed();
		String weapon = equipment.get(Equipment.WEAPON_SLOT).getDefinition().getName();
		if (getCurrentlyCasting() != null) {
			if (getCurrentlyCasting() == CombatSpells.BLOOD_BLITZ.getSpell()
					|| getCurrentlyCasting() == CombatSpells.SHADOW_BLITZ.getSpell()
					|| getCurrentlyCasting() == CombatSpells.SMOKE_BLITZ.getSpell()
					|| getCurrentlyCasting() == CombatSpells.ICE_BLITZ.getSpell()) {
				return 5;
			} else {
				return 6;
			}
		}
		int weaponId = equipment.get(Equipment.WEAPON_SLOT).getId();
		int shieldId = equipment.get(Equipment.SHIELD_SLOT).getId();
		if (weaponId == 1419) {
			speed -= 2;
		}
		if (weaponId == 19938) {
			speed = 3;
		}
		if (weaponId == 19939) {
			speed = 3;
		}
		if (weaponId == 894 || weaponId == 21044 || weaponId == 700 || weaponId == 701 || weaponId == 895
				|| weaponId == 2867 || weaponId == 18957) {
			speed = 3;
		}
		if (weaponId == 21080 || weaponId == 20805 || weaponId == 20605 || weaponId == 19098 || weaponId == 20853 || shieldId == 20854) {
			speed = 1;
		}
		if (weaponId == 17847) {
			speed = 2;
		}
		if (weaponId == 20504) {
			speed = 1;
		}
		if (weaponId == 21079 || weaponId == 20089) {
			speed = 2;
		}
		if (weaponId == 20695) {
			speed = 1;
		}
		if (weaponId == 21062) {
			speed = 1;
		}
		if (weaponId == 18932 || weaponId == 20838 || weaponId == 20998 || weaponId == 21082 || weaponId == 20818 ||  weaponId == 19004 || weaponId == 20819
				|| weaponId == 20815 || weaponId == 20820 || weaponId == 18931 || weaponId == 18911 || weaponId == 19057
				|| weaponId == 18965 || weaponId == 3666 || weaponId == 20695 || weaponId == 20751 || weaponId == 18898 || weaponId == 20752
				|| weaponId == 21062 || weaponId == 20115 || weaponId == 20601 || weaponId == 20510 || weaponId == 3717 || weaponId == 3065 || weaponId == 6196
				|| weaponId == 21077 || weaponId == 4784 || weaponId == 20862) {
			speed = 1;
		}
		if (weaponId == 18932) {
			speed = 0;
		}
		if (weaponId == 20932) {
			speed = 0;
		}
		if (weaponId == 21001) {
			speed = 0;
		}
		if (weaponId == 3717) {
			speed = 0;
		}
		if (weaponId == 3065) {
			speed = 0;
		}
		if (weaponId == 15665) {
			speed = 0;
		}
		if (weaponId == 20838) {
			speed = 0;
		}
		if (weaponId == 20873) {
			speed = 0;
		}
		if (weaponId == 21072) {
			speed = 0;
		}
		if (weaponId == 21073) {
			speed = 0;
		}
		if (weaponId == 15668) {
			speed = 0;
		}
		if (weaponId == 20922) {
			speed = 0;
		}

		if (weaponId == 21002 || weaponId == 18889 || weaponId == 19005 || weaponId == 3650 || weaponId == 12927 || weaponId == 11539 || weaponId == 19004
				|| weaponId == 20603 || weaponId == 21060 || weaponId == 21063 || weaponId == 21013 || weaponId == 20079
				|| weaponId == 20102 || weaponId == 20607 || weaponId == 21003 || weaponId == 21030 || weaponId == 21031
				|| weaponId == 21032 || weaponId == 21033 || weaponId == 18989 || weaponId == 21004) {
			speed = 2;
		}
		if (fightType == FightType.CROSSBOW_RAPID || fightType == FightType.LONGBOW_RAPID
				|| weaponId == 6522 && fightType == FightType.KNIFE_RAPID || weapon.contains("rapier")) {
			if (weaponId != 11235) {
				speed--;
			}

		} else if (weaponId != 6522 && weaponId != 15241
				&& (fightType == FightType.SHORTBOW_RAPID || fightType == FightType.DART_RAPID
						|| fightType == FightType.KNIFE_RAPID || fightType == FightType.THROWNAXE_RAPID
						|| fightType == FightType.JAVELIN_RAPID)
				|| weaponId == 11730) {
			speed -= 2;
		}
		if (weaponId == 896) {
			return 3;
		}
		return speed;
		// return DesolaceFormulas.getAttackDelay(this);
	}

	public boolean sendElementalMessage = true;
	public int clue1Amount;
	public int clue2Amount;
	public int clue3Amount;
	public int clueLevel;
	public double doubleDropRate = 0;
	public double droprate = 0;
	public Item[] puzzleStoredItems;
	public int sextantGlobalPiece;
	public double sextantBarDegree;
	public int rotationFactor;
	public int sextantLandScapeCoords;
	public int sextantSunCoords;

	// private Channel channel;

	// public Player write(Packet packet) {
	// if (channel.isConnected()) {
	// channel.write(packet);
	// }
	// return this;
	// }

	/// public Channel getChannel() {
	// return channel;
	// }

	private Bank bank = new Bank(this);

	public Bank getBank() {
		return bank;
	}

	public int summoned = -1;

	public int getSummoned() {
		return summoned;
	}

	public void setSummoned(int sum) {
		this.summoned = sum;
	}

	public boolean isSendElementalMessage() {
		return sendElementalMessage;
	}

	public void setSendElementalMessage(boolean elemental) {
		this.sendElementalMessage = elemental;
	}

	@Override
	public boolean isPlayer() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Player)) {
			return false;
		}

		Player p = (Player) o;
		return p.getIndex() == getIndex() || p.getUsername().equals(username);
	}

	@Override
	public int getSize() {
		return 1;
	}

	@Override
	public void poisonVictim(Character victim, CombatType type) {
		if (type == CombatType.MELEE || weapon == WeaponInterface.DART || weapon == WeaponInterface.KNIFE
				|| weapon == WeaponInterface.THROWNAXE || weapon == WeaponInterface.JAVELIN) {
			CombatFactory.poisonEntity(victim, CombatPoisonData.getPoisonType(equipment.get(Equipment.WEAPON_SLOT)));
		} else if (type == CombatType.RANGED) {
			CombatFactory.poisonEntity(victim,
					CombatPoisonData.getPoisonType(equipment.get(Equipment.AMMUNITION_SLOT)));
		}
	}

	@Override
	public CombatStrategy determineStrategy() {
		if (specialActivated && castSpell == null) {

			if (combatSpecial.getCombatType() == CombatType.MELEE) {
				return CombatStrategies.getDefaultMeleeStrategy();
			} else if (combatSpecial.getCombatType() == CombatType.RANGED) {
				setRangedWeaponData(RangedWeaponData.getData(this));
				return CombatStrategies.getDefaultRangedStrategy();
			} else if (combatSpecial.getCombatType() == CombatType.MAGIC) {
				return CombatStrategies.getDefaultMagicStrategy();
			}
		}

		if (CustomMagicStaff.checkCustomStaff(this)) {
			CustomMagicStaff.handleCustomStaff(this);
			this.setCastSpell(CustomMagicStaff.CustomStaff
					.getSpellForWeapon(this.getEquipment().getItems()[Equipment.WEAPON_SLOT].getId()));
			return CombatStrategies.getDefaultMagicStrategy();
		}

		if (castSpell != null || autocastSpell != null) {
			return CombatStrategies.getDefaultMagicStrategy();
		}

		RangedWeaponData data = RangedWeaponData.getData(this);
		if (data != null) {
			setRangedWeaponData(data);
			return CombatStrategies.getDefaultRangedStrategy();
		}

		return CombatStrategies.getDefaultMeleeStrategy();
	}

	public void process() {
		process.sequence();
	}

	public void dispose() {
		save();
		packetSender.sendLogout();
	}

	public void save() {
		if (session.getState() != SessionState.LOGGED_IN && session.getState() != SessionState.LOGGING_OUT) {
			return;
		}
		PlayerSaving.save(this);
	}

	public boolean logout() {
		boolean debugMessage = false;
		int[] playerXP = new int[Skill.VALUES.size()];
		for (int i = 0; i < Skill.VALUES.size(); i++) {
			playerXP[i] = this.getSkillManager().getExperience(Skill.forId(i));
		}
		if (getCombatBuilder().isBeingAttacked()) {
			getPacketSender().sendMessage("You must wait a few seconds after being out of combat before doing this.");
			return false;
		}
		if (getConstitution() <= 0 || isDying || settingUpCannon || crossingObstacle) {
			getPacketSender().sendMessage("You cannot log out at the moment.");
			return false;
		}
		return true;
	}

	public void restart() {
		setFreezeDelay(0);
		setOverloadPotionTimer(0);
		setPrayerRenewalPotionTimer(0);
		setSpecialPercentage(100);
		setSpecialActivated(false);
		CombatSpecial.updateBar(this);
		setHasVengeance(false);
		setSkullTimer(0);
		setSkullIcon(0);
		setTeleblockTimer(0);
		setPoisonDamage(0);
		setStaffOfLightEffect(0);
		performAnimation(new Animation(65535));
		WeaponInterfaces.assign(this, getEquipment().get(Equipment.WEAPON_SLOT));
		WeaponAnimations.assign(this, getEquipment().get(Equipment.WEAPON_SLOT));
		PrayerHandler.deactivateAll(this);
		CurseHandler.deactivateAll(this);
		getEquipment().refreshItems();
		getInventory().refreshItems();
		for (Skill skill : Skill.VALUES) {
			getSkillManager().setCurrentLevel(skill, getSkillManager().getMaxLevel(skill));
		}
		setRunEnergy(100);
		setDying(false);
		getMovementQueue().setLockMovement(false).reset();
		getUpdateFlag().flag(Flag.APPEARANCE);
	}

	/**
	 * @author Stan van der Bend
	 *
	 *         This method will ensure this {@link Player} will receive the item(s)
	 *         defined by the params. 1): first try inventory 2): then try bank 3):
	 *         then try refund box 4): otherwise send error message
	 *
	 * @param itemId
	 * @param itemAmount
	 */
	public void giveItem(int itemId, int itemAmount) {

		final ItemDefinition definition = ItemDefinition.forId(itemId);

		if (definition == null) {
			sendMessage("@red@[Error]: Could not find definition (" + itemId + "-" + itemAmount + ")");
			sendMessage("@red@Please take a screenshot and post it on the forums.");
			return;
		}

		final int occupiedSlots = definition.isNoted() || definition.isStackable() ? 1 : itemAmount;

		if (inventory.getFreeSlots() >= occupiedSlots) {
			inventory.add(itemId, itemAmount).refreshItems();
		} else if (bank.getFreeSlots() >= occupiedSlots) {
			boolean added = false;
			for (Bank bank : getBanks()) {
				if (!added && !Bank.isEmpty(bank)) {
					bank.add(itemId, itemAmount).refreshItems();
					added = true;
				}
			}
		} else {
			sendMessage("@red@[Error]: Could not give (" + itemId + "-" + itemAmount + ")");
			sendMessage("@red@Please take a screenshot and post it on the forums.");
			World.sendStaffMessage("@red@[Error]: Could not give (" + itemId + "-" + itemAmount + ") to " + username);
		}
	}

	public boolean busy() {
		return interfaceId > 0 || isBanking || shopping || trading.inTrade() || dueling.inDuelScreen || isResting;
	}

	/*
	 * Fields
	 */
	/**
	 * * STRINGS **
	 */
	private String username;
	private String password;
	private String serial_number;
	private String emailAddress;
	private String hostAddress;
	private String clanChatName;

	private HouseLocation houseLocation;
	private String claimedat = Misc.println_debug("");
	private String method = "Paypal";
	private HouseTheme houseTheme;

	/**
	 * * LONGS *
	 */
	private Long longUsername;
	private long moneyInPouch;
	private long totalPlayTime;
	// Timers (Stopwatches)
	private final Stopwatch sqlTimer = new Stopwatch();
	private final Stopwatch foodTimer = new Stopwatch();
	private final Stopwatch potionTimer = new Stopwatch();
	private final Stopwatch lastRunRecovery = new Stopwatch();
	private final Stopwatch clickDelay = new Stopwatch();
	private final Stopwatch lastItemPickup = new Stopwatch();
	private final Stopwatch lastYell = new Stopwatch();
	private final Stopwatch lastZulrah = new Stopwatch();
	private final Stopwatch lastSql = new Stopwatch();

	private final Stopwatch lastVengeance = new Stopwatch();
	private final Stopwatch emoteDelay = new Stopwatch();
	private final Stopwatch specialRestoreTimer = new Stopwatch();
	private final Stopwatch dropTimer = new Stopwatch();
	private final Stopwatch lastSummon = new Stopwatch();
	private final Stopwatch recordedLogin = new Stopwatch();
	private final Stopwatch creationDate = new Stopwatch();
	private final Stopwatch tolerance = new Stopwatch();
	private final Stopwatch lougoutTimer = new Stopwatch();

	/**
	 * * INSTANCES **
	 */
	private final CopyOnWriteArrayList<KillsEntry> killsTracker = new CopyOnWriteArrayList<KillsEntry>();
	private final CopyOnWriteArrayList<DropLogEntry> dropLog = new CopyOnWriteArrayList<DropLogEntry>();
	private ArrayList<HouseFurniture> houseFurniture = new ArrayList<HouseFurniture>();
	private ArrayList<Portal> housePortals = new ArrayList<>();
	private final List<Player> localPlayers = new LinkedList<Player>();
	private final List<NPC> localNpcs = new LinkedList<NPC>();

	private PlayerSession session;
	private final PlayerProcess process = new PlayerProcess(this);
	private final PlayerKillingAttributes playerKillingAttributes = new PlayerKillingAttributes(this);
	private final MinigameAttributes minigameAttributes = new MinigameAttributes();
	private final BankPinAttributes bankPinAttributes = new BankPinAttributes();
	private final BankSearchAttributes bankSearchAttributes = new BankSearchAttributes();
	private final AchievementAttributes achievementAttributes = new AchievementAttributes();
	private final StarterTaskAttributes starterTaskAttributes = new StarterTaskAttributes();
	private CharacterAnimations characterAnimations = new CharacterAnimations();
	private final BonusManager bonusManager = new BonusManager();
	private final PointsHandler pointsHandler = new PointsHandler(this);
	private DungeoneeringIronInventory dungeoneeringIronInventory = new DungeoneeringIronInventory(this);
	private DungeoneeringIronEquipment dungeoneeringIronEquipment = new DungeoneeringIronEquipment(this);

	private final PacketSender packetSender = new PacketSender(this);
	private final FrameUpdater frameUpdater = new FrameUpdater();
	private PlayerRights rights = PlayerRights.PLAYER;
	private SkillManager skillManager = new SkillManager(this);
	private PlayerRelations relations = new PlayerRelations(this);
	private ChatMessage chatMessages = new ChatMessage();
	private Inventory inventory = new Inventory(this);
	private Equipment equipment = new Equipment(this);
	private PriceChecker priceChecker = new PriceChecker(this);
	private Trading trading = new Trading(this);
	private Dueling dueling = new Dueling(this);
	private Slayer slayer = new Slayer(this);

	private Farming farming = new Farming(this);
	private Summoning summoning = new Summoning(this);
	private Bank[] bankTabs = new Bank[9];
	private Room[][][] houseRooms = new Room[5][13][13];
	private PlayerInteractingOption playerInteractingOption = PlayerInteractingOption.NONE;
	private GameMode gameMode = GameMode.NORMAL;
	private CombatType lastCombatType = CombatType.MELEE;
	private FightType fightType = FightType.UNARMED_PUNCH;
	private Prayerbook prayerbook = Prayerbook.NORMAL;
	private MagicSpellbook spellbook = MagicSpellbook.NORMAL;
	private LoyaltyTitles loyaltyTitle = LoyaltyTitles.NONE;

	private ClanChat currentClanChat;
	private Input inputHandling;
	private WalkToTask walkToTask;
	private Shop shop;
	private GameObject interactingObject;
	private Item interactingItem;
	private Dialogue dialogue;
	private DwarfCannon cannon;
	private CombatSpell autocastSpell, castSpell, previousCastSpell;
	private RangedWeaponData rangedWeaponData;
	private CombatSpecial combatSpecial;
	private WeaponInterface weapon;
	private Item untradeableDropItem;
	private Object[] usableObject;
	private GrandExchangeSlot[] grandExchangeSlots = new GrandExchangeSlot[6];
	private Task currentTask;
	private Position resetPosition;
	private Pouch selectedPouch;
	private BlowpipeLoading blowpipeLoading = new BlowpipeLoading(this);
	private DragonRageLoading dragonrageLoading = new DragonRageLoading(this);
	private CorruptBandagesLoading CorruptBandagesLoading = new CorruptBandagesLoading(this);
	private MinigunLoading MinigunLoading = new MinigunLoading(this);

	private int timer;

	public int getTimer() {
		return timer;
	}

	public void setTimer(int time) {
		this.timer = time;
	}

	/**
	 * * INTS **
	 */
	public int destination = 0;
	public int lastClickedTab = 0;

	private int[] brawlerCharges = new int[9];
	private int[] forceMovement = new int[7];
	private int[] leechedBonuses = new int[7];
	private int[] ores = new int[2];
	private int[] constructionCoords;
	private int recoilCharges;
	private int runEnergy = 100;
	private int currentBankTab;
	private int interfaceId, walkableInterfaceId, multiIcon;
	private int dialogueActionId;
	private int overloadPotionTimer, prayerRenewalPotionTimer;
	private int furiousPotionTimer;
	private int fireImmunity, fireDamageModifier;
	private int amountDonated;
	private int wildernessLevel;
	private int fireAmmo;
	private int specialPercentage = 100;
	private int skullIcon = -1, skullTimer;
	private int teleblockTimer;
	private int dragonFireImmunity;
	private int poisonImmunity;
	private int shadowState;
	private int effigy;
	private int dfsCharges;
	private String XmasEvent;
	private int playerViewingIndex;
	private int staffOfLightEffect;
	private int minutesBonusExp = -1;
	private int selectedGeSlot = -1;
	private int selectedGeItem = -1;
	private int geQuantity;
	private int gePricePerItem;
	private int selectedSkillingItem;
	private int currentBookPage;
	private int currentBookPage1;
	private int storedRuneEssence, storedPureEssence;
	private int trapsLaid;
	private int skillAnimation;
	private int houseServant;
	private int houseServantCharges;
	private int servantItemFetch;
	private int portalSelected;
	private int constructionInterface;
	private int buildFurnitureId;
	private int buildFurnitureX;
	private int buildFurnitureY;
	private int combatRingType;

	/**
	 * * BOOLEANS **
	 */
	private boolean unlockedLoyaltyTitles[] = new boolean[12];
	private boolean[] crossedObstacles = new boolean[7];
	private boolean processFarming;
	private boolean crossingObstacle;
	private boolean targeted;
	private boolean isBanking, noteWithdrawal, swapMode;
	private boolean regionChange, allowRegionChangePacket;
	private boolean isDying;
	private boolean isRunning = true, isResting;
	private boolean experienceLocked;
	private boolean clientExitTaskActive;
	private boolean drainingPrayer;
	private boolean shopping;
	private boolean settingUpCannon;
	private boolean hasVengeance;
	private boolean killsTrackerOpen;
	private boolean acceptingAid;
	private boolean autoRetaliate;
	private boolean autocast;
	private boolean specialActivated;
	private boolean isCoughing;
	private boolean playerLocked;
	private boolean recoveringSpecialAttack;
	private boolean soundsActive, musicActive;
	private boolean newPlayer;
	private boolean openBank;
	private boolean inActive;
	public int timeOnline;
	private boolean inConstructionDungeon;
	private boolean isBuildingMode;
	private boolean voteMessageSent;
	private boolean receivedStarter;
	public boolean starterClaimed;

	/*
	 * Getters & Setters
	 */
	public PlayerSession getSession() {
		return session;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public DungeoneeringIronInventory getDungeoneeringIronInventory() {
		return dungeoneeringIronInventory;
	}

	public void setDungeoneeringStorage(DungeoneeringIronInventory dungeoneeringIronInventory) {
		this.dungeoneeringIronInventory = dungeoneeringIronInventory;
	}

	public DungeoneeringIronEquipment getDungeoneeringIronEquipment() {
		return dungeoneeringIronEquipment;
	}

	public void setDungeoneeringIronEquipment(DungeoneeringIronEquipment dungeoneeringIronEquipment) {
		this.dungeoneeringIronEquipment = dungeoneeringIronEquipment;
	}

	public boolean isInDung() {
		return indung;
	}

	public void setInDung(boolean indung) {
		this.indung = indung;
	}

	public PriceChecker getPriceChecker() {
		return priceChecker;
	}

	/*
	 * Getters and setters
	 */
	public String getUsername() {
		return username;
	}

	public Player setUsername(String username) {
		this.username = username;
		return this;
	}

	public Long getLongUsername() {
		return longUsername;
	}

	public Player setLongUsername(Long longUsername) {
		this.longUsername = longUsername;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String address) {
		this.emailAddress = address;
	}

	public Player setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getHostAddress() {
		return hostAddress;
	}
	
	public String getClaimedAt() {
		return claimedat;
	}
	public String getMethod() {
		return method;
	}

	public Player setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
		return this;
	}

	public String getSerialNumber() {
		return serial_number;
	}

	public Player setSerialNumber(String serial_number) {
		this.serial_number = serial_number;
		return this;
	}

	public FrameUpdater getFrameUpdater() {
		return this.frameUpdater;
	}

	public PlayerRights getRights() {
		return rights;
	}

	public Player setRights(PlayerRights rights) {
		this.rights = rights;
		return this;
	}

	public ChatMessage getChatMessages() {
		return chatMessages;
	}

	public PacketSender getPacketSender() {
		return packetSender;
	}

	public void delay(int tick, Callback callback) {
		TaskManager.submit(new Task(tick) {
			@Override
			protected void execute() {
			}
		});
	}

	public SkillManager getSkillManager() {
		return skillManager;
	}

	public Appearance getAppearance() {
		return appearance;
	}

	public PlayerRelations getRelations() {
		return relations;
	}

	public PlayerKillingAttributes getPlayerKillingAttributes() {
		return playerKillingAttributes;
	}

	public PointsHandler getPointsHandler() {
		return pointsHandler;
	}

	public boolean isImmuneToDragonFire() {
		return dragonFireImmunity > 0;
	}

	public int getDragonFireImmunity() {
		return dragonFireImmunity;
	}

	public void setDragonFireImmunity(int dragonFireImmunity) {
		this.dragonFireImmunity = dragonFireImmunity;
	}

	public void setDroprate(double droprate) {
		this.droprate = droprate;
	}

	public void setDoubleDropRate(double doubleDropRate) {
		this.doubleDropRate = doubleDropRate;
	}

	public double getDropRate() {
		return droprate;
	}

	public double getDoubleDropRate() {
		return doubleDropRate;
	}

	public void incrementDragonFireImmunity(int amount) {
		dragonFireImmunity += amount;
	}

	public void decrementDragonFireImmunity(int amount) {
		dragonFireImmunity -= amount;
	}

	public int getPoisonImmunity() {
		return poisonImmunity;
	}

	public void setPoisonImmunity(int poisonImmunity) {
		this.poisonImmunity = poisonImmunity;
	}

	public void incrementPoisonImmunity(int amount) {
		poisonImmunity += amount;
	}

	public void decrementPoisonImmunity(int amount) {
		poisonImmunity -= amount;
	}

	public boolean isAutoRetaliate() {
		return autoRetaliate;
	}

	public void setAutoRetaliate(boolean autoRetaliate) {
		this.autoRetaliate = autoRetaliate;
	}

	/**
	 * @return the castSpell
	 */
	public CombatSpell getCastSpell() {
		return castSpell;
	}

	/**
	 * @param castSpell
	 *            the castSpell to set
	 */
	public void setCastSpell(CombatSpell castSpell) {
		this.castSpell = castSpell;
	}

	public CombatSpell getPreviousCastSpell() {
		return previousCastSpell;
	}

	public void setPreviousCastSpell(CombatSpell previousCastSpell) {
		this.previousCastSpell = previousCastSpell;
	}

	/**
	 * @return the autocast
	 */
	public boolean isAutocast() {
		return autocast;
	}

	/**
	 * @param autocast
	 *            the autocast to set
	 */
	public void setAutocast(boolean autocast) {
		this.autocast = autocast;
	}

	public boolean checkItem(int slot, int id) {
		if (this.getEquipment().getItems()[slot].getId() == id) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the skullTimer
	 */
	public int getSkullTimer() {
		return skullTimer;
	}

	/**
	 * @param skullTimer
	 *            the skullTimer to set
	 */
	public void setSkullTimer(int skullTimer) {
		this.skullTimer = skullTimer;
	}

	public void decrementSkullTimer() {
		skullTimer -= 50;
	}

	/**
	 * @return the skullIcon
	 */
	public int getSkullIcon() {
		return skullIcon;
	}

	/**
	 * @param skullIcon
	 *            the skullIcon to set
	 */
	public void setSkullIcon(int skullIcon) {
		this.skullIcon = skullIcon;
	}

	/**
	 * @return the teleblockTimer
	 */
	public int getTeleblockTimer() {
		return teleblockTimer;
	}

	/**
	 * @param teleblockTimer
	 *            the teleblockTimer to set
	 */
	public void setTeleblockTimer(int teleblockTimer) {
		this.teleblockTimer = teleblockTimer;
	}

	public void decrementTeleblockTimer() {
		teleblockTimer--;
	}

	/**
	 * @return the autocastSpell
	 */
	public CombatSpell getAutocastSpell() {
		return autocastSpell;
	}

	/**
	 * @param autocastSpell
	 *            the autocastSpell to set
	 */
	public void setAutocastSpell(CombatSpell autocastSpell) {
		this.autocastSpell = autocastSpell;
	}

	/**
	 * @return the specialPercentage
	 */
	public int getSpecialPercentage() {
		return specialPercentage;
	}

	/**
	 * @param specialPercentage
	 *            the specialPercentage to set
	 */
	public void setSpecialPercentage(int specialPercentage) {
		this.specialPercentage = specialPercentage;
	}

	/**
	 * @return the fireAmmo
	 */
	public int getFireAmmo() {
		return fireAmmo;
	}

	/**
	 * @param fireAmmo
	 *            the fireAmmo to set
	 */
	public void setFireAmmo(int fireAmmo) {
		this.fireAmmo = fireAmmo;
	}

	public int getWildernessLevel() {
		return wildernessLevel;
	}

	public void setWildernessLevel(int wildernessLevel) {
		this.wildernessLevel = wildernessLevel;
	}

	/**
	 * @return the combatSpecial
	 */
	public CombatSpecial getCombatSpecial() {
		return combatSpecial;
	}

	/**
	 * @param combatSpecial
	 *            the combatSpecial to set
	 */
	public void setCombatSpecial(CombatSpecial combatSpecial) {
		this.combatSpecial = combatSpecial;
	}

	/**
	 * @return the specialActivated
	 */
	public boolean isSpecialActivated() {
		return specialActivated;
	}

	/**
	 * @param specialActivated
	 *            the specialActivated to set
	 */
	public void setSpecialActivated(boolean specialActivated) {
		this.specialActivated = specialActivated;
	}

	public void decrementSpecialPercentage(int drainAmount) {
		this.specialPercentage -= drainAmount;

		if (specialPercentage < 0) {
			specialPercentage = 0;
		}
	}

	public void incrementSpecialPercentage(int gainAmount) {
		this.specialPercentage += gainAmount;

		if (specialPercentage > 100) {
			specialPercentage = 100;
		}
	}

	/**
	 * @return the rangedAmmo
	 */
	public RangedWeaponData getRangedWeaponData() {
		return rangedWeaponData;
	}

	/**
	 */
	public void setRangedWeaponData(RangedWeaponData rangedWeaponData) {
		this.rangedWeaponData = rangedWeaponData;
	}

	/**
	 * @return the weapon.
	 */
	public WeaponInterface getWeapon() {
		return weapon;
	}

	public int raidDmg;

	public int getRaidDmg() {
		return raidDmg;
	}

	public void setRaidDmg(int i) {
		this.raidDmg = i;
	}

	public ArrayList<Integer> walkableInterfaceList = new ArrayList<>();
	public long lastHelpRequest;
	public long lastAuthClaimed;
	public GameModes selectedGameMode;
	private boolean areCloudsSpawned;

	public boolean inFFA;
	public boolean inFFALobby;
	public int[] oldSkillLevels = new int[SkillManager.MAX_SKILLS];
	public int[] oldSkillXP = new int[SkillManager.MAX_SKILLS];
	public int[] oldSkillMaxLevels = new int[SkillManager.MAX_SKILLS];
	public long freeDTD, freeDediBoss, dmgPotionTime;

	public void resetInterfaces() {
		walkableInterfaceList.stream().filter((i) -> !(i == 41005 || i == 41000)).forEach((i) -> {
			getPacketSender().sendWalkableInterface(i, false);
		});

		walkableInterfaceList.clear();
	}

	public void sendParallellInterfaceVisibility(int interfaceId, boolean visible) {
		if (this != null && this.getPacketSender() != null) {
			if (visible) {
				if (walkableInterfaceList.contains(interfaceId)) {
					return;
				} else {
					walkableInterfaceList.add(interfaceId);
				}
			} else {
				if (!walkableInterfaceList.contains(interfaceId)) {
					return;
				} else {
					walkableInterfaceList.remove((Object) interfaceId);
				}
			}

			getPacketSender().sendWalkableInterface(interfaceId, visible);
		}
	}

	/**
	 * @param weapon
	 *            the weapon to set.
	 */
	public void setWeapon(WeaponInterface weapon) {
		this.weapon = weapon;
	}

	/**
	 * @return the fightType
	 */
	public FightType getFightType() {
		return fightType;
	}

	/**
	 * @param fightType
	 *            the fightType to set
	 */
	public void setFightType(FightType fightType) {
		this.fightType = fightType;
	}

	public Bank[] getBanks() {
		return bankTabs;
	}

	public Bank getBank(int index) {
		return bankTabs[index];
	}

	public Player setBank(int index, Bank bank) {
		this.bankTabs[index] = bank;
		return this;
	}

	public boolean isAcceptAid() {
		return acceptingAid;
	}

	public void setAcceptAid(boolean acceptingAid) {
		this.acceptingAid = acceptingAid;
	}

	public Trading getTrading() {
		return trading;
	}

	public Dueling getDueling() {
		return dueling;
	}

	public CopyOnWriteArrayList<KillsEntry> getKillsTracker() {
		return killsTracker;
	}

	public CopyOnWriteArrayList<DropLogEntry> getDropLog() {
		return dropLog;
	}

	public void setWalkToTask(WalkToTask walkToTask) {
		this.walkToTask = walkToTask;
	}

	public WalkToTask getWalkToTask() {
		return walkToTask;
	}

	public Player setSpellbook(MagicSpellbook spellbook) {
		this.spellbook = spellbook;
		return this;
	}

	public MagicSpellbook getSpellbook() {
		return spellbook;
	}

	public Player setPrayerbook(Prayerbook prayerbook) {
		this.prayerbook = prayerbook;
		return this;
	}

	public Prayerbook getPrayerbook() {
		return prayerbook;
	}

	/**
	 * The player's local players list.
	 */
	public List<Player> getLocalPlayers() {
		return localPlayers;
	}

	/**
	 * The player's local npcs list getter
	 */
	public List<NPC> getLocalNpcs() {
		return localNpcs;
	}

	public Player setInterfaceId(int interfaceId) {
		this.interfaceId = interfaceId;
		return this;
	}

	public int getInterfaceId() {
		return this.interfaceId;
	}

	public boolean isDying() {
		return isDying;
	}

	public void setDying(boolean isDying) {
		this.isDying = isDying;
	}

	public int[] getForceMovement() {
		return forceMovement;
	}

	public Player setForceMovement(int[] forceMovement) {
		this.forceMovement = forceMovement;
		return this;
	}

	/**
	 * @return the equipmentAnimation
	 */
	public CharacterAnimations getCharacterAnimations() {
		return characterAnimations;
	}

	/**
	 * @return the equipmentAnimation
	 */
	public void setCharacterAnimations(CharacterAnimations equipmentAnimation) {
		this.characterAnimations = equipmentAnimation.clone();
	}

	public LoyaltyTitles getLoyaltyTitle() {
		return loyaltyTitle;
	}

	public void setLoyaltyTitle(LoyaltyTitles loyaltyTitle) {
		this.loyaltyTitle = loyaltyTitle;
	}

	public void setWalkableInterfaceId(int interfaceId2) {
		this.walkableInterfaceId = interfaceId2;
	}

	public PlayerInteractingOption getPlayerInteractingOption() {
		return playerInteractingOption;
	}

	public Player setPlayerInteractingOption(PlayerInteractingOption playerInteractingOption) {
		this.playerInteractingOption = playerInteractingOption;
		return this;
	}

	public int getMultiIcon() {
		return multiIcon;
	}

	public Player setMultiIcon(int multiIcon) {
		this.multiIcon = multiIcon;
		return this;
	}

	public int getWalkableInterfaceId() {
		return walkableInterfaceId;
	}

	public boolean soundsActive() {
		return soundsActive;
	}

	public void setSoundsActive(boolean soundsActive) {
		this.soundsActive = soundsActive;
	}

	public boolean musicActive() {
		return musicActive;
	}

	public void setMusicActive(boolean musicActive) {
		this.musicActive = musicActive;
	}

	public BonusManager getBonusManager() {
		return bonusManager;
	}

	public int getRunEnergy() {
		return runEnergy;
	}

	public Player setRunEnergy(int runEnergy) {
		this.runEnergy = runEnergy;
		return this;
	}

	public Stopwatch getLastRunRecovery() {
		return lastRunRecovery;
	}

	public Player setRunning(boolean isRunning) {
		this.isRunning = isRunning;
		return this;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public Player setResting(boolean isResting) {
		this.isResting = isResting;
		return this;
	}

	public boolean isResting() {
		return isResting;
	}

	public void setMoneyInPouch(long moneyInPouch) {
		this.moneyInPouch = moneyInPouch;
	}

	public long getMoneyInPouch() {
		return moneyInPouch;
	}
	
	public long lastDonationClaim;

	public int getMoneyInPouchAsInt() {
		return moneyInPouch > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) moneyInPouch;
	}

	public boolean experienceLocked() {
		return experienceLocked;
	}

	public void setExperienceLocked(boolean experienceLocked) {
		this.experienceLocked = experienceLocked;
	}

	public void setClientExitTaskActive(boolean clientExitTaskActive) {
		this.clientExitTaskActive = clientExitTaskActive;
	}

	public boolean isClientExitTaskActive() {
		return clientExitTaskActive;
	}

	public Player setCurrentClanChat(ClanChat clanChat) {
		this.currentClanChat = clanChat;
		return this;
	}

	public ClanChat getCurrentClanChat() {
		return currentClanChat;
	}

	public String getClanChatName() {
		return clanChatName;
	}

	public Player setClanChatName(String clanChatName) {
		this.clanChatName = clanChatName;
		return this;
	}

	public void setInputHandling(Input inputHandling) {
		this.inputHandling = inputHandling;
	}

	public Input getInputHandling() {
		return inputHandling;
	}

	public boolean isDrainingPrayer() {
		return drainingPrayer;
	}

	public void setDrainingPrayer(boolean drainingPrayer) {
		this.drainingPrayer = drainingPrayer;
	}

	public Stopwatch getClickDelay() {
		return clickDelay;
	}

	public int[] getLeechedBonuses() {
		return leechedBonuses;
	}

	public Stopwatch getLastItemPickup() {
		return lastItemPickup;
	}

	public Stopwatch getLastSummon() {
		return lastSummon;
	}

	public BankSearchAttributes getBankSearchingAttribtues() {
		return bankSearchAttributes;
	}

	public AchievementAttributes getAchievementAttributes() {
		return achievementAttributes;
	}
	public StarterTaskAttributes getStarterTaskAttributes() {
		return starterTaskAttributes;
	}

	public BankPinAttributes getBankPinAttributes() {
		return bankPinAttributes;
	}

	public int getCurrentBankTab() {
		return currentBankTab;
	}

	public Player setCurrentBankTab(int tab) {
		this.currentBankTab = tab;
		return this;
	}

	public boolean isBanking() {
		return isBanking;
	}

	public Player setBanking(boolean isBanking) {
		this.isBanking = isBanking;
		return this;
	}
	
	public void setNoteWithdrawal(boolean noteWithdrawal) {
		this.noteWithdrawal = noteWithdrawal;
	}

	public boolean withdrawAsNote() {
		return noteWithdrawal;
	}

	public GamblingManager getGambling() {
		return gamblingManager;
	}

	public GambleOfferItemContainer getGambleOffer() {
		return gambleOffer;
	}

	public void setSwapMode(boolean swapMode) {
		this.swapMode = swapMode;
	}

	public boolean swapMode() {
		return swapMode;
	}

	public boolean isShopping() {
		return shopping;
	}

	public void setShopping(boolean shopping) {
		this.shopping = shopping;
	}

	public Shop getShop() {
		return shop;
	}

	public Player setShop(Shop shop) {
		this.shop = shop;
		return this;
	}

	public GameObject getInteractingObject() {
		return interactingObject;
	}

	public Player setInteractingObject(GameObject interactingObject) {
		this.interactingObject = interactingObject;
		return this;
	}

	public Item getInteractingItem() {
		return interactingItem;
	}

	public void setInteractingItem(Item interactingItem) {
		this.interactingItem = interactingItem;
	}

	public Dialogue getDialogue() {
		return this.dialogue;
	}

	public void setDialogue(Dialogue dialogue) {
		this.dialogue = dialogue;
	}

	public int getDialogueActionId() {
		return dialogueActionId;
	}

	public void setDialogueActionId(int dialogueActionId) {
		this.dialogueActionId = dialogueActionId;
	}

	public void setSettingUpCannon(boolean settingUpCannon) {
		this.settingUpCannon = settingUpCannon;
	}

	public boolean isSettingUpCannon() {
		return settingUpCannon;
	}

	public Player setCannon(DwarfCannon cannon) {
		this.cannon = cannon;
		return this;
	}

	public DwarfCannon getCannon() {
		return cannon;
	}

	public int getOverloadPotionTimer() {
		return overloadPotionTimer;
	}

	public void setOverloadPotionTimer(int overloadPotionTimer) {
		this.overloadPotionTimer = overloadPotionTimer;
	}

	public int getFuriousPotionTimer() {
		return furiousPotionTimer;
	}

	public void setFuriousPotionTimer(int furiousPotionTimer) {
		this.furiousPotionTimer = furiousPotionTimer;
	}

	public int getPrayerRenewalPotionTimer() {
		return prayerRenewalPotionTimer;
	}

	public void setPrayerRenewalPotionTimer(int prayerRenewalPotionTimer) {
		this.prayerRenewalPotionTimer = prayerRenewalPotionTimer;
	}

	public Stopwatch getDropTableTimer() {
		return dropTimer;
	}

	public Stopwatch getSpecialRestoreTimer() {
		return specialRestoreTimer;
	}

	public boolean[] getUnlockedLoyaltyTitles() {
		return unlockedLoyaltyTitles;
	}

	public void setUnlockedLoyaltyTitles(boolean[] unlockedLoyaltyTitles) {
		this.unlockedLoyaltyTitles = unlockedLoyaltyTitles;
	}

	public void setUnlockedLoyaltyTitle(int index) {
		unlockedLoyaltyTitles[index] = true;
	}

	public Stopwatch getEmoteDelay() {
		return emoteDelay;
	}

	public MinigameAttributes getMinigameAttributes() {
		return minigameAttributes;
	}

	public Minigame getMinigame() {
		return minigame;
	}

	public void setMinigame(Minigame minigame) {
		this.minigame = minigame;
	}

	public int getFireImmunity() {
		return fireImmunity;
	}

	public Player setFireImmunity(int fireImmunity) {
		this.fireImmunity = fireImmunity;
		return this;
	}

	public int getFireDamageModifier() {
		return fireDamageModifier;
	}

	public Player setFireDamageModifier(int fireDamageModifier) {
		this.fireDamageModifier = fireDamageModifier;
		return this;
	}

	public boolean hasVengeance() {
		return hasVengeance;
	}

	public void setHasVengeance(boolean hasVengeance) {
		this.hasVengeance = hasVengeance;
	}

	public Stopwatch getLastVengeance() {
		return lastVengeance;
	}

	public void setHouseRooms(Room[][][] houseRooms) {
		this.houseRooms = houseRooms;
	}

	public void setHousePortals(ArrayList<Portal> housePortals) {
		this.housePortals = housePortals;
	}

	/*
	 * Construction instancing Arlania
	 */
	public boolean isVisible() {
		if (getLocation() == Locations.Location.CONSTRUCTION) {
			return false;
		}
		return true;
	}

	public void setHouseFurtinture(ArrayList<HouseFurniture> houseFurniture) {
		this.houseFurniture = houseFurniture;
	}

	public Stopwatch getTolerance() {
		return tolerance;
	}

	public boolean isTargeted() {
		return targeted;
	}

	public void setTargeted(boolean targeted) {
		this.targeted = targeted;
	}

	public Stopwatch getLastYell() {
		return lastYell;
	}

	public Stopwatch getLastZulrah() {
		return lastZulrah;
	}

	public Stopwatch getLastSql() {
		return lastSql;
	}

	public int getAmountDonated() {
		return amountDonated;
	}

	public void incrementAmountDonated(int amountDonated) {
		this.amountDonated += amountDonated;
	}

	public long getTotalPlayTime() {
		return totalPlayTime;
	}

	public void setTotalPlayTime(long amount) {
		this.totalPlayTime = amount;
	}

	public Stopwatch getRecordedLogin() {
		return recordedLogin;
	}

	public Player setRegionChange(boolean regionChange) {
		this.regionChange = regionChange;
		return this;
	}

	public boolean isChangingRegion() {
		return this.regionChange;
	}

	public void setAllowRegionChangePacket(boolean allowRegionChangePacket) {
		this.allowRegionChangePacket = allowRegionChangePacket;
	}

	public boolean isAllowRegionChangePacket() {
		return allowRegionChangePacket;
	}

	public boolean isKillsTrackerOpen() {
		return killsTrackerOpen;
	}

	public void setKillsTrackerOpen(boolean killsTrackerOpen) {
		this.killsTrackerOpen = killsTrackerOpen;
	}

	public boolean isCoughing() {
		return isCoughing;
	}

	public void setCoughing(boolean isCoughing) {
		this.isCoughing = isCoughing;
	}

	public int getShadowState() {
		return shadowState;
	}

	public void setShadowState(int shadow) {
		this.shadowState = shadow;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	public boolean isPlayerLocked() {
		return playerLocked;
	}

	public Player setPlayerLocked(boolean playerLocked) {
		this.playerLocked = playerLocked;
		return this;
	}

	public Stopwatch getSqlTimer() {
		return sqlTimer;
	}

	public Stopwatch getFoodTimer() {
		return foodTimer;
	}

	public Stopwatch getPotionTimer() {
		return potionTimer;
	}

	public Item getUntradeableDropItem() {
		return untradeableDropItem;
	}

	public void setUntradeableDropItem(Item untradeableDropItem) {
		this.untradeableDropItem = untradeableDropItem;
	}

	public boolean isRecoveringSpecialAttack() {
		return recoveringSpecialAttack;
	}

	public void setRecoveringSpecialAttack(boolean recoveringSpecialAttack) {
		this.recoveringSpecialAttack = recoveringSpecialAttack;
	}

	public CombatType getLastCombatType() {
		return lastCombatType;
	}

	public void setLastCombatType(CombatType lastCombatType) {
		this.lastCombatType = lastCombatType;
	}

	public int getEffigy() {
		return this.effigy;
	}

	public void setEffigy(int effigy) {
		this.effigy = effigy;
	}

	public int getDfsCharges() {
		return dfsCharges;
	}

	public void incrementDfsCharges(int amount) {
		this.dfsCharges += amount;
	}

	public void setNewPlayer(boolean newPlayer) {
		this.newPlayer = newPlayer;
	}

	public boolean newPlayer() {
		return newPlayer;
	}

	public Stopwatch getLogoutTimer() {
		return lougoutTimer;
	}

	public Player setUsableObject(Object[] usableObject) {
		this.usableObject = usableObject;
		return this;
	}

	public Player setUsableObject(int index, Object usableObject) {
		this.usableObject[index] = usableObject;
		return this;
	}

	public Object[] getUsableObject() {
		return usableObject;
	}

	public int getPlayerViewingIndex() {
		return playerViewingIndex;
	}

	public void setPlayerViewingIndex(int playerViewingIndex) {
		this.playerViewingIndex = playerViewingIndex;
	}

	public boolean hasStaffOfLightEffect() {
		return staffOfLightEffect > 0;
	}

	public int getStaffOfLightEffect() {
		return staffOfLightEffect;
	}

	public void setStaffOfLightEffect(int staffOfLightEffect) {
		this.staffOfLightEffect = staffOfLightEffect;
	}

	public void decrementStaffOfLightEffect() {
		this.staffOfLightEffect--;
	}

	public boolean openBank() {
		return openBank;
	}

	public void setOpenBank(boolean openBank) {
		this.openBank = openBank;
	}

	public int getMinutesBonusExp() {
		return minutesBonusExp;
	}

	public void setMinutesBonusExp(int minutesBonusExp, boolean add) {
		this.minutesBonusExp = (add ? this.minutesBonusExp + minutesBonusExp : minutesBonusExp);
	}

	public void setInactive(boolean inActive) {
		this.inActive = inActive;
	}

	public boolean isInActive() {
		return inActive;
	}

	public int getSelectedGeItem() {
		return selectedGeItem;
	}

	public void setSelectedGeItem(int selectedGeItem) {
		this.selectedGeItem = selectedGeItem;
	}

	public int getGeQuantity() {
		return geQuantity;
	}

	public void setGeQuantity(int geQuantity) {
		this.geQuantity = geQuantity;
	}

	public int getGePricePerItem() {
		return gePricePerItem;
	}

	public void setGePricePerItem(int gePricePerItem) {
		this.gePricePerItem = gePricePerItem;
	}

	public GrandExchangeSlot[] getGrandExchangeSlots() {
		return grandExchangeSlots;
	}

	public void setGrandExchangeSlots(GrandExchangeSlot[] GrandExchangeSlots) {
		this.grandExchangeSlots = GrandExchangeSlots;
	}

	public void setGrandExchangeSlot(int index, GrandExchangeSlot state) {
		this.grandExchangeSlots[index] = state;
	}

	public void setSelectedGeSlot(int slot) {
		this.selectedGeSlot = slot;
	}

	public int getSelectedGeSlot() {
		return selectedGeSlot;
	}

	public Task getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}

	public int getSelectedSkillingItem() {
		return selectedSkillingItem;
	}

	public void setSelectedSkillingItem(int selectedItem) {
		this.selectedSkillingItem = selectedItem;
	}

	public boolean shouldProcessFarming() {
		return processFarming;
	}

	public void setProcessFarming(boolean processFarming) {
		this.processFarming = processFarming;
	}

	public Pouch getSelectedPouch() {
		return selectedPouch;
	}

	public void setSelectedPouch(Pouch selectedPouch) {
		this.selectedPouch = selectedPouch;
	}

	public int getCurrentBookPage() {
		return currentBookPage;
	}

	public void setCurrentBookPage(int currentBookPage) {
		this.currentBookPage = currentBookPage;
	}

	public int getCurrentBookPage1() {
		return currentBookPage1;
	}

	public void setCurrentBookPage1(int currentBookPage1) {
		this.currentBookPage1 = currentBookPage1;
	}

	public int getStoredRuneEssence() {
		return storedRuneEssence;
	}

	public void setStoredRuneEssence(int storedRuneEssence) {
		this.storedRuneEssence = storedRuneEssence;
	}

	public int getStoredPureEssence() {
		return storedPureEssence;
	}

	public void setStoredPureEssence(int storedPureEssence) {
		this.storedPureEssence = storedPureEssence;
	}

	public int getTrapsLaid() {
		return trapsLaid;
	}

	public void setTrapsLaid(int trapsLaid) {
		this.trapsLaid = trapsLaid;
	}

	public boolean isCrossingObstacle() {
		return crossingObstacle;
	}

	public Player setCrossingObstacle(boolean crossingObstacle) {
		this.crossingObstacle = crossingObstacle;
		return this;
	}

	public boolean[] getCrossedObstacles() {
		return crossedObstacles;
	}

	public boolean getCrossedObstacle(int i) {
		return crossedObstacles[i];
	}

	public Player setCrossedObstacle(int i, boolean completed) {
		crossedObstacles[i] = completed;
		return this;
	}

	public void setCrossedObstacles(boolean[] crossedObstacles) {
		this.crossedObstacles = crossedObstacles;
	}

	public int getSkillAnimation() {
		return skillAnimation;
	}

	public Player setSkillAnimation(int animation) {
		this.skillAnimation = animation;
		return this;
	}

	public int[] getOres() {
		return ores;
	}

	public void setOres(int[] ores) {
		this.ores = ores;
	}

	public void setResetPosition(Position resetPosition) {
		this.resetPosition = resetPosition;
	}

	public Position getResetPosition() {
		return resetPosition;
	}

	public Slayer getSlayer() {
		return slayer;
	}

	public Summoning getSummoning() {
		return summoning;
	}

	public Farming getFarming() {
		return farming;
	}

	public boolean inConstructionDungeon() {
		return inConstructionDungeon;
	}

	public void setInConstructionDungeon(boolean inConstructionDungeon) {
		this.inConstructionDungeon = inConstructionDungeon;
	}

	public int getHouseServant() {
		return houseServant;
	}

	public HouseLocation getHouseLocation() {
		return houseLocation;
	}

	public HouseTheme getHouseTheme() {
		return houseTheme;
	}

	public void setHouseTheme(HouseTheme houseTheme) {
		this.houseTheme = houseTheme;
	}

	public void setHouseLocation(HouseLocation houseLocation) {
		this.houseLocation = houseLocation;
	}

	public void setHouseServant(int houseServant) {
		this.houseServant = houseServant;
	}

	public int getHouseServantCharges() {
		return this.houseServantCharges;
	}

	public void setHouseServantCharges(int houseServantCharges) {
		this.houseServantCharges = houseServantCharges;
	}

	public void incrementHouseServantCharges() {
		this.houseServantCharges++;
	}

	public int getServantItemFetch() {
		return servantItemFetch;
	}

	public void setServantItemFetch(int servantItemFetch) {
		this.servantItemFetch = servantItemFetch;
	}

	public int getPortalSelected() {
		return portalSelected;
	}

	public void setPortalSelected(int portalSelected) {
		this.portalSelected = portalSelected;
	}

	public boolean isBuildingMode() {
		return this.isBuildingMode;
	}

	public void setIsBuildingMode(boolean isBuildingMode) {
		this.isBuildingMode = isBuildingMode;
	}

	public int[] getConstructionCoords() {
		return constructionCoords;
	}

	public void setConstructionCoords(int[] constructionCoords) {
		this.constructionCoords = constructionCoords;
	}

	public int getBuildFurnitureId() {
		return this.buildFurnitureId;
	}

	public void setBuildFuritureId(int buildFuritureId) {
		this.buildFurnitureId = buildFuritureId;
	}

	public int getBuildFurnitureX() {
		return this.buildFurnitureX;
	}

	public void setBuildFurnitureX(int buildFurnitureX) {
		this.buildFurnitureX = buildFurnitureX;
	}

	public int getBuildFurnitureY() {
		return this.buildFurnitureY;
	}

	public void setBuildFurnitureY(int buildFurnitureY) {
		this.buildFurnitureY = buildFurnitureY;
	}

	public int getCombatRingType() {
		return this.combatRingType;
	}

	public void setCombatRingType(int combatRingType) {
		this.combatRingType = combatRingType;
	}

	public Room[][][] getHouseRooms() {
		return houseRooms;
	}

	public ArrayList<Portal> getHousePortals() {
		return housePortals;
	}

	public ArrayList<HouseFurniture> getHouseFurniture() {
		return houseFurniture;
	}

	public int getConstructionInterface() {
		return this.constructionInterface;
	}

	public void setConstructionInterface(int constructionInterface) {
		this.constructionInterface = constructionInterface;
	}

	public int[] getBrawlerChargers() {
		return this.brawlerCharges;
	}

	public void setBrawlerCharges(int[] brawlerCharges) {
		this.brawlerCharges = brawlerCharges;
	}

	public int getRecoilCharges() {
		return this.recoilCharges;
	}

	public int setRecoilCharges(int recoilCharges) {
		return this.recoilCharges = recoilCharges;
	}

	public boolean voteMessageSent() {
		return this.voteMessageSent;
	}

	public void setVoteMessageSent(boolean voteMessageSent) {
		this.voteMessageSent = voteMessageSent;
	}

	public boolean didReceiveStarter() {
		return receivedStarter;
	}

	public void sendMessage(String string) {
		packetSender.sendMessage(string);
	}

	public void setReceivedStarter(boolean receivedStarter) {
		this.receivedStarter = receivedStarter;
	}

	public BlowpipeLoading getBlowpipeLoading() {
		return blowpipeLoading;
	}

	public DragonRageLoading getDragonRageLoading() {
		return dragonrageLoading;
	}

	public CorruptBandagesLoading getCorruptBandagesLoading() {
		return CorruptBandagesLoading;
	}

	public MinigunLoading getMinigunLoading() {
		return MinigunLoading;
	}

	public boolean cloudsSpawned() {
		return areCloudsSpawned;
	}

	public void setCloudsSpawned(boolean cloudsSpawned) {
		this.areCloudsSpawned = cloudsSpawned;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isShopUpdated() {
		return shopUpdated;
	}

	public void setShopUpdated(boolean shopUpdated) {
		this.shopUpdated = shopUpdated;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void write(Packet packet) {
		// TODO Auto-generated method stub

	}

	public void datarsps(Player player, String username2) {
		// TODO Auto-generated method stub

	}

	public PVMRanking getPvMRanking() {
		return pvmRanking;
	}

	public int getWeaponGamePoints() {
		return weaponGamePoints;
	}

	/**
	 * Sets the amount of {@link WeaponGame} points a {@link Player} has.
	 *
	 * @param weaponGamePoints
	 *            The amount of points to set.
	 */
	public void setWeaponGamePoints(int weaponGamePoints) {
		this.weaponGamePoints = weaponGamePoints;

	}

	public boolean hasRights(PlayerRights... hasRights) {
		for (int i = 0; i < hasRights.length; i++) {
			if (rights == hasRights[i]) {
				return true;
			}
		}
		return false;
	}

	public void updateMoneyPouch() {
		getPacketSender().sendString(8135, "" + getMoneyInPouch() + "");
	}

	public void rollDedicatedBossExtras() {

		int[][] rewardData = new int[][] { { 80, 200 }, { 19057, 200 }, { 11527, 5 }, { 18989, 200 }, { 11555, 160 },
				{ 19098, 150 }, { 3981, 120 }, { 18908, 120 }, { 11556, 120 }, { 20826, 100 }, { 20827, 100 },
				{ 20828, 100 }, { 12423, 100 }, { 20074, 75 }, { 20510, 60 }, { 20257, 50 }, { 11423, 50 },
				{ 11533, 50 }, { 3230, 30 }, { 11531, 20 }, { 7100, 15 }, { 11529, 15 }, { 12422, 15 }, { 12421, 10 },
				{ 11425, 5 }, };

		TeleportHandler.teleportPlayer(this, new Position(2529, 2527, 1), this.getSpellbook().getTeleportType());

		double chance = Math.random();

		MoneyPouch.addWinnings(this, 5_000_000_000_000_000L);

		if (chance > 0.02) {
			this.sendMessage("Failed to hit 1/50 chance for rare item! ("
					+ DecimalFormat.getInstance().format(chance * 100) + "%) better luck next time!");
			return;
		}

		boolean space = this.getInventory().getFreeSlots() > 0;

		int randomSelect = Misc.random(rewardData.length - 1);

		Item rewardItem = new Item(rewardData[randomSelect][0], 1);

		String location = (space ? "inventory" : "bank");

		if (space)
			this.getInventory().add(rewardItem);
		else
			this.getBank().add(rewardItem);

		this.sendMessage("Congratulations! you've receive a " + rewardItem.getDefinition().getName() + "....");
		this.sendMessage("... from the dedicated boss with a 2% chance! est store value: $"
				+ rewardData[randomSelect][1] + "...");
		this.sendMessage("... your reward has been added to your " + location + ".");

	}

	public byte lastDmgPotionTick;
	
	
	public transient ImmortalRaid currentRaidSession;
	
	public transient String raidsPassword;
	
	public int immortalRaidsCompleted;

	public Position lastTeleportPosition;

	public void applyMultiplierPotion() {

		lastDmgPotionTick--;
		
		if (lastDmgPotionTick == 0) {

			Skill sk = null;
			for (int i = 0; i < 7; i++) {
				if (i == 3 || i == 5)
					continue;
				sk = Skill.forId(i);
				this.getSkillManager().setCurrentLevel(sk, 160);
			}
			lastDmgPotionTick = 50;
		}
	}
	
	public static Dialogue mainRaidOptions(final Player p) {
			return new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.OPTION;
				}

				@Override
				public DialogueExpression animation() {
					return null;
				}

				@Override
				public String[] dialogue() {
					return new String[] { "Create Raid", "Join Active Session", "Never mind" };
				}

				@Override
				public Dialogue nextDialogue() {
					return new Dialogue() {

						@Override
						public DialogueType type() {
							return DialogueType.OPTION;
						}

						@Override
						public DialogueExpression animation() {
							return null;
						}

						@Override
						public String[] dialogue() {
							return new String[] { "Yes", "No" };
						}

						@Override
						public void specialAction() {
							p.setDialogueActionId(788);
						}
					};
				}
			};
		}

	public Object getController() {
		// TODO Auto-generated method stub
		return null;
	}

	
}