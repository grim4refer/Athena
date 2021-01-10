package com.athena;

import java.io.File;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.athena.model.container.impl.Bank;
import com.athena.util.ShutdownHook;
import com.athena.world.entity.impl.player.Player;
import com.athena.world.entity.impl.player.PlayerLoading;
/**
 * The starting point of Athena.
 * @author Gabriel
 * @author Samy
 */
public class GameServer {

	private static final GameLoader loader = new GameLoader(GameSettings.GAME_PORT);
	private static final Logger logger = Logger.getLogger("Athena");
	private static boolean updating;

	static long totalEco = 0;

	public static void checkServersEco(Player player) {
		totalEco = 0;

		CompletableFuture.runAsync(() -> {

			if (player == null)
				System.out.println("Check total $ in eco..");
			else
				player.sendMessage("Check total $ in eco..");

			for (File file : Objects.requireNonNull(new File("data/saves/characters/").listFiles())) {
				Player p = new Player(null);

				p.setUsername(file.getName().substring(0, file.getName().length() - 5));

				PlayerLoading.getResult(p, true);

				/*
				 * Reset Bank
				 */

				long netWorth = 0;
				for (Bank bank : p.getBanks()) {
					if (bank == null) {
						return;
					}
					for (int i = 0; i < bank.getItems().length; i++)
						netWorth += bank.getItems()[i].getDefinition().getValue();
				}

				for (int inv = 0; inv < p.getInventory().getItems().length; inv++) {
					if (p.getInventory().getItems()[inv] != null) {
						netWorth += p.getInventory().getItems()[inv].getDefinition().getValue();
					}
				}

				netWorth += p.getMoneyInPouch();

				totalEco += netWorth;
				/*
				 * Save File
				 */
				//PlayerSaving.save(p);
				if (player == null)
					System.out.println(p.getUsername() + " Net worth $" + NumberFormat.getInstance().format(netWorth));
				else
					player.sendMessage(p.getUsername() + " Net worth $" + NumberFormat.getInstance().format(netWorth));

			}
			if (player == null)
				System.out.println("Total Eco $" + NumberFormat.getInstance().format(totalEco));
			else
				player.sendMessage("Total Eco $" + NumberFormat.getInstance().format(totalEco));
		});
	}

	public static void main(String[] params) {
		Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		try {

			logger.info("Initializing the loader...");
			loader.init();
			loader.finish();
			logger.info("The loader has finished loading utility tasks.");
			logger.info("Athena is now online on port "+ com.athena.GameSettings.GAME_PORT+"!");
			//checkServersEco(null);
		//	JavaCord.init();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Could not start Athena! Program terminated.", ex);
			System.exit(1);
		}
	}



		public static GameLoader getLoader () {
			return loader;
		}

		public static Logger getLogger () {
			return logger;
		}

		public static void setUpdating ( boolean updating){
			GameServer.updating = updating;
		}

		public static boolean isUpdating () {
			return GameServer.updating;
		}
	}