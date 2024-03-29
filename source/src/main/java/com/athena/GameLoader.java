package com.athena;

import com.athena.database.Database;
import com.athena.engine.GameEngine;
import com.athena.engine.task.TaskManager;
import com.athena.engine.task.impl.ServerTimeUpdateTask;
import com.athena.model.container.impl.Shop.ShopManager;
import com.athena.model.definitions.ItemDefinition;
import com.athena.model.definitions.NPCDrops;
import com.athena.model.definitions.NpcDefinition;
import com.athena.model.definitions.WeaponInterfaces;
import com.athena.net.PipelineFactory;
import com.athena.net.security.ConnectionHandler;
import com.athena.world.SlotMachineRewards;
import com.athena.world.World;
import com.athena.world.content.AOESystem;
import com.athena.world.clip.region.RegionClipping;
import com.athena.world.content.*;
import com.athena.world.content.clan.ClanChatManager;
import com.athena.world.content.combat.effect.CombatPoisonEffect.CombatPoisonData;
import com.athena.world.content.combat.strategy.CombatStrategies;
import com.athena.world.content.dialogue.DialogueManager;
import com.athena.world.content.global.GlobalBossHandler;
import com.athena.world.content.grandexchange.GrandExchangeOffers;
import com.athena.world.content.minigames.MinigameHandler2;
import com.athena.world.content.pos.PlayerOwnedShopManager;
import com.athena.world.content.skill.impl.invention.InventionHandler;
import com.athena.world.content.upgrade.Upgrade;
import com.athena.world.entity.impl.npc.NPC;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.util.HashedWheelTimer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Credit: lare96, Gabbe
 */
public final class GameLoader {

	public static final int VIALSMASH =1;
	/*
	 * Daily events
	 * Handles the checking of the day to represent
	 * which event will be active on such day
	 */
	public static final int SUNDAY = 1;
	public static final int MONDAY = 2;
	public static final int TUESDAY = 3;
	public static final int WEDNESDAY = 4;
	public static final int THURSDAY = 5;
	public static final int FRIDAY = 6;
	public static final int SATURDAY = 7;
	//public static Object getSpecialDay;
		//Double EXP days
	public static int getDoubleEXPWeekend() {
		return (getDay() == FRIDAY) ? 2 : 1;
	}
		//Finds the day of the week
	public static int getDay() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static String day() {
		return switch (getDay()) {
			case MONDAY -> "Monday";
			case TUESDAY -> "Tuesday";
			case WEDNESDAY -> "Wednesday";
			case THURSDAY -> "Thursday";
			case FRIDAY -> "Friday";
			case SATURDAY -> "Saturday";
			case SUNDAY -> "Sunday";
			default -> "";
		};
	}
			//events for each day
	public static String getSpecialDay() {
		return switch (getDay()) {
			case MONDAY -> "X2 Vote Points";
			case TUESDAY -> "X2 Boss Points";
			case WEDNESDAY -> "X2 Slayer Points";
			case THURSDAY -> "X2 PC Points";
			case FRIDAY -> "X2 Exp. & Lottery";
			case SATURDAY -> "X2 Trivia Points";
			case SUNDAY -> "x2 Damage Points";
			default -> "X2 Exp. & Lottery";
		};
	}
	public static boolean doubleBossPoints() {
		return new GregorianCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY;
	}
	public static boolean doubleTriviaPoints() {
		return new GregorianCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
	}
	public static boolean doubleDamage() {
		return new GregorianCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}

	private final ExecutorService serviceLoader = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("GameLoadingThread").build());
	private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().setNameFormat("GameThread").build());
	private final GameEngine engine;
	private final int port;

	protected GameLoader(int port) {
		this.port = port;
		this.engine = new GameEngine();
	}

	public void init() {
		Preconditions.checkState(!serviceLoader.isShutdown(), "The bootstrap has been bound already!");
		executeServiceLoad();
		serviceLoader.shutdown();
	}

	public void finish() throws IOException, InterruptedException {
		if (!serviceLoader.awaitTermination(15, TimeUnit.MINUTES))
			throw new IllegalStateException("The background service load took too long!");
		ExecutorService networkExecutor = Executors.newCachedThreadPool();
		ServerBootstrap serverBootstrap = new ServerBootstrap (new NioServerSocketChannelFactory(networkExecutor, networkExecutor));
        serverBootstrap.setPipelineFactory(new PipelineFactory(new HashedWheelTimer()));
        serverBootstrap.setOption("child.keepAlive", true);
        serverBootstrap.setOption("child.tcpNoDelay", true);
		serverBootstrap.setOption("child.TcpAckFrequency", true);
        serverBootstrap.bind(new InetSocketAddress(port));
		executor.scheduleAtFixedRate(engine, 0, GameSettings.ENGINE_PROCESSING_CYCLE_RATE, TimeUnit.MILLISECONDS);
		TaskManager.submit(new ServerTimeUpdateTask());
		//TaskManager.submit(new WeaponGame());
		
		World.minigameHandler = MinigameHandler2.defaultHandler();
		World.minigameHandler.loadMinigames();
	}
        
	private void executeServiceLoad() {
		if (GameSettings.MYSQL_ENABLED) {
			serviceLoader.execute(Database::init);
//			serviceLoader.execute(MySQLController::init);
		}
                
		serviceLoader.execute(ConnectionHandler::init);
		serviceLoader.execute(PlayerPunishment::init);
		serviceLoader.execute(RegionClipping::init);
		serviceLoader.execute(CustomObjects::init);
		serviceLoader.execute(ItemDefinition::init);
		serviceLoader.execute(Lottery::init);
		serviceLoader.execute(VotingContest::init);
		serviceLoader.execute(GrandExchangeOffers::init);
		serviceLoader.execute(Scoreboards::init);
		serviceLoader.execute(WellOfGoodwill::init);
		serviceLoader.execute(ClanChatManager::init);
		serviceLoader.execute(CombatPoisonData::init);
		serviceLoader.execute(() -> NpcDefinition.parseNpcs().load());
		serviceLoader.execute(() -> NPCDrops.parseDrops().load());
		serviceLoader.execute(() -> WeaponInterfaces.parseInterfaces().load());
		serviceLoader.execute(() -> ShopManager.parseShops().load());
		serviceLoader.execute(() -> DialogueManager.parseDialogues().load());
		serviceLoader.execute(CombatStrategies::init);
		serviceLoader.execute(NPC::init);
		serviceLoader.execute(InventionHandler::init);
		serviceLoader.execute(Upgrade::init);
		serviceLoader.execute(SlotMachineRewards::init);
		serviceLoader.execute(ProfileViewing::init);
		serviceLoader.execute(PlayerOwnedShopManager::loadShops);
		serviceLoader.execute(MonsterDrops::initialize);
		serviceLoader.execute(GlobalBossHandler::init);
		serviceLoader.execute(AOESystem.getSingleton()::parseData);

	}

	public GameEngine getEngine() {
		return engine;
	}
}