package com.athena.model.container.impl;

import java.util.HashMap;
import java.util.Map;

import com.athena.engine.task.TaskManager;
import com.athena.engine.task.impl.ShopRestockTask;
import com.athena.model.GameMode;
import com.athena.model.Item;
import com.athena.model.Locations.Location;
import com.athena.model.PlayerRights;
import com.athena.model.Skill;
import com.athena.model.container.ItemContainer;
import com.athena.model.container.StackType;
import com.athena.model.definitions.ItemDefinition;
import com.athena.model.input.impl.EnterAmountToBuyFromShop;
import com.athena.model.input.impl.EnterAmountToSellToShop;
import com.athena.util.JsonLoader;
import com.athena.util.Misc;
import com.athena.world.World;
import com.athena.world.content.minigames.impl.RecipeForDisaster;
import com.athena.world.content.skill.impl.dungeoneering.UltimateIronmanHandler;
import com.athena.world.entity.impl.player.Player;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Messy but perfect Shop System
 *
 * @author Gabriel Hannason
 */

public class Shop extends ItemContainer {

	/*
	 * The shop constructor
	 */
	public Shop(Player player, int id, String name, Item currency, Item[] stockItems) {
		super(player);
		if (stockItems.length > 42)
			throw new ArrayIndexOutOfBoundsException(
					"Stock cannot have more than 40 items; check shop[" + id + "]: stockLength: " + stockItems.length);
		this.id = id;
		this.name = name.length() > 0 ? name : "General Store";
		this.currency = currency;
		this.originalStock = new Item[stockItems.length];
		for (int i = 0; i < stockItems.length; i++) {
			Item item = new Item(stockItems[i].getId(), stockItems[i].getAmount());
			add(item, false);
			this.originalStock[i] = item;
		}
	}

	private final int id;

	private String name;

	private Item currency;

	private Item[] originalStock;

	public Item[] getOriginalStock() {
		return this.originalStock;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public Shop setName(String name) {
		this.name = name;
		return this;
	}

	public Item getCurrency() {
		return currency;
	}

	public Shop setCurrency(Item currency) {
		this.currency = currency;
		return this;
	}

	private boolean restockingItems;

	public boolean isRestockingItems() {
		return restockingItems;
	}

	public void setRestockingItems(boolean restockingItems) {
		this.restockingItems = restockingItems;
	}

	/**
	 * Opens a shop for a player
	 *
	 * @param player
	 *            The player to open the shop for
	 * @return The shop instance
	 */
	public Shop open(Player player) {
		setPlayer(player);

		//sendPrices(player);

		getPlayer().getPacketSender().sendInterfaceRemoval().sendClientRightClickRemoval();
		getPlayer().setShop(ShopManager.getShops().get(id)).setInterfaceId(INTERFACE_ID).setShopping(true);
		refreshItems();
		if (Misc.getMinutesPlayed(getPlayer()) <= 190)
			getPlayer().getPacketSender()
					.sendMessage("Note: When selling an item to a store, it loses 50% of its original value.");
		return this;
	}

	private void sendPrices(Player player) {
		shopPrices = new int[getOriginalStock().length];
		Shop shop = ShopManager.getShops().get(id);
		for (int i = 0; i < getOriginalStock().length; i++) {
			Item item = getItems()[i];
			if (shop.getCurrency().getId() == 995) {
				shopPrices[i] = item.getDefinition().getValue();
			} else {
				Object[] data = ShopManager.getCustomShopData(id, item.getId());
				if (data != null)
					shopPrices[i] = (int) data[0];
			}
		}
		player.getPacketSender().sendShopPrice(shopPrices);
	}

	/**
	 * Refreshes a shop for every player who's viewing it
	 */
	public void publicRefresh() {
		Shop publicShop = ShopManager.getShops().get(id);
		if (publicShop == null)
			return;
		publicShop.setItems(getItems());
		for (Player player : World.getPlayers()) {
			if (player == null)
				continue;
			if (player.getShop() != null && player.getShop().id == id && player.isShopping())
				player.getShop().setItems(publicShop.getItems());
		}
	}

	/**
	 * Checks a value of an item in a shop
	 *
	 * @param player
	 *            The player who's checking the item's value
	 * @param slot
	 *            The shop item's slot (in the shop!)
	 * @param sellingItem
	 *            Is the player selling the item?
	 */
	public void checkValue(Player player, int slot, boolean sellingItem) {
		this.setPlayer(player);
		if (UltimateIronmanHandler.hasItemsStored(player) && player.getLocation() != Location.DUNGEONEERING) {
			player.getPacketSender().sendMessage("<shad=0>@red@You cannot use the shop until you claim your stored Dungeoneering items.");
			return;
		}
		Item shopItem = new Item(getItems()[slot].getId());
		if (!player.isShopping()) {
			player.getPacketSender().sendInterfaceRemoval();
			return;
		}
		Item item = sellingItem ? player.getInventory().getItems()[slot] : getItems()[slot];
		if (item.getId() == 995)
			return;
		if (sellingItem) {
			if (!shopBuysItem(id, item)) {
				player.getPacketSender().sendMessage("You cannot sell this item to this store.");
				return;
			}
		}
		int finalValue = 0;
		String finalString = sellingItem ? "" + ItemDefinition.forId(item.getId()).getName() + ": shop will buy for "
				: "" + ItemDefinition.forId(shopItem.getId()).getName() + " currently costs ";
		if (getCurrency().getId() != -1) {
			finalValue = ItemDefinition.forId(item.getId()).getValue();
			String s = currency.getDefinition().getName().toLowerCase().endsWith("s")
					? currency.getDefinition().getName().toLowerCase()
					: currency.getDefinition().getName().toLowerCase() + "s";
			/** CUSTOM CURRENCY, CUSTOM SHOP VALUES **/
			if (id == RACNOR_SHOP || id == PIKKUPSTIX || id == PIKKUPSTIX2 || id == EXPLORER_SHOP || id == TOKKUL_EXCHANGE_STORE || id == RAIDS_STORE || id == ENERGY_FRAGMENT_STORE || id == STARDUST_STORE|| id == AGILITY_TICKET_STORE
					|| id == GRAVEYARD_STORE || id == HOLY_WATER_STORE) {
				Object[] obj = ShopManager.getCustomShopData(id, item.getId());
				if (obj == null)
					return;
				finalValue = (int) obj[0];
				s = (String) obj[1];
			}
			if (sellingItem) {
				if (finalValue != 1) {
					finalValue = (int) (finalValue * 0.50);
				}
			}
			finalString += "" + (int) finalValue + " " + s + "" + shopPriceEx((int) finalValue) + ".";
		} else {
			Object[] obj = ShopManager.getCustomShopData(id, item.getId());
			if (obj == null)
				return;
			finalValue = (int) obj[0];
			if (sellingItem) {
				if (finalValue != 1) {
					finalValue = (int) (finalValue * 0.50);
				}
			}
			finalString += "" + finalValue + " " + (String) obj[1] + ".";
		}
		if (player != null && finalValue > 0) {
			player.getPacketSender().sendMessage(finalString);
			return;
		}
	}

	public void sellItem(Player player, int slot, int amountToSell) {
		this.setPlayer(player);
		if (!player.isShopping() || player.isBanking()) {
			player.getPacketSender().sendInterfaceRemoval();
			return;
		}

		if (!player.isShopping() || player.isBanking()) {
			player.getPacketSender().sendInterfaceRemoval();
			return;
		}
		if (UltimateIronmanHandler.hasItemsStored(player) && player.getLocation() != Location.DUNGEONEERING) {
			player.getPacketSender().sendMessage("<shad=0>@red@You cannot use the shop until you claim your stored Dungeoneering items.");
			return;
		}
		Item itemToSell = player.getInventory().getItems()[slot];
		if (!itemToSell.sellable()) {
			player.getPacketSender().sendMessage("This item cannot be sold.");
			return;
		}
		if (!shopBuysItem(id, itemToSell)) {
			player.getPacketSender().sendMessage("You cannot sell this item to this store.");
			return;
		}
		if (!player.getInventory().contains(itemToSell.getId()) || itemToSell.getId() == 995)
			return;
		if (this.full(itemToSell.getId()))
			return;
		if (player.getInventory().getAmount(itemToSell.getId()) < amountToSell)
			amountToSell = player.getInventory().getAmount(itemToSell.getId());
		if (amountToSell == 0)
			return;
		/*
		 * if(amountToSell > 300) { String s =
		 * ItemDefinition.forId(itemToSell.getId()).getName().endsWith("s") ?
		 * ItemDefinition.forId(itemToSell.getId()).getName() :
		 * ItemDefinition.forId(itemToSell.getId()).getName() + "s";
		 * player.getPacketSender().sendMessage("You can only sell 300 "+s+
		 * " at a time."); return; }
		 */
		int itemId = itemToSell.getId();
		boolean customShop = this.getCurrency().getId() == -1;
		boolean inventorySpace = customShop ? true : false;
		if (!customShop) {
			if (!itemToSell.getDefinition().isStackable()) {
				if (!player.getInventory().contains(this.getCurrency().getId()))
					inventorySpace = true;
			}
			if (player.getInventory().getFreeSlots() <= 0
					&& player.getInventory().getAmount(this.getCurrency().getId()) > 0)
				inventorySpace = true;
			if (player.getInventory().getFreeSlots() > 0
					|| player.getInventory().getAmount(this.getCurrency().getId()) > 0)
				inventorySpace = true;
		}
		int itemValue = 0;
		if (getCurrency().getId() > 0) {
			itemValue = ItemDefinition.forId(itemToSell.getId()).getValue();
		} else {
			Object[] obj = ShopManager.getCustomShopData(id, itemToSell.getId());
			if (obj == null)
				return;
			itemValue = (int) obj[0];
		}
		if (itemValue <= 0)
			return;
		itemValue = (int) (itemValue * 0.50);
		if (itemValue <= 0) {
			itemValue = 1;
		}
		for (int i = amountToSell; i > 0; i--) {
			itemToSell = new Item(itemId);
			if (this.full(itemToSell.getId()) || !player.getInventory().contains(itemToSell.getId())
					|| !player.isShopping())
				break;
			if (!itemToSell.getDefinition().isStackable()) {
				if (inventorySpace) {
					super.switchItem(player.getInventory(), this, itemToSell.getId(), -1);
					if (!customShop) {
						player.getInventory().add(new Item(getCurrency().getId(), itemValue), false);
					} else {
						// Return points here
					}
				} else {
					player.getPacketSender().sendMessage("Please free some inventory space before doing that.");
					break;
				}
			} else {
				if (inventorySpace) {
					super.switchItem(player.getInventory(), this, itemToSell.getId(), amountToSell);
					if (!customShop) {
						player.getInventory().add(new Item(getCurrency().getId(), itemValue * amountToSell), false);
					} else {
						// Return points here
					}
					break;
				} else {
					player.getPacketSender().sendMessage("Please free some inventory space before doing that.");
					break;
				}
			}
			amountToSell--;
		}
		if (customShop) {
			player.getPointsHandler().refreshPanel();
		}
		player.getInventory().refreshItems();
		fireRestockTask();
		refreshItems();
		publicRefresh();
	}

	/**
	 * Buying an item from a shop
	 */
	@Override
	public Shop switchItem(ItemContainer to, Item item, int slot, boolean sort, boolean refresh) {
		final Player player = getPlayer();
		if (player == null)
			return this;
		if (!player.isShopping() || player.isBanking()) {
			player.getPacketSender().sendInterfaceRemoval();
			return this;
		}
		if (this.id == GENERAL_STORE) {
			if (player.getGameMode() == GameMode.IRONMAN) {
				player.getPacketSender()
						.sendMessage("Ironman-players are not allowed to buy items from the general-store.");
				return this;
			}
			if (player.getRights() == PlayerRights.YOUTUBER) {
				player.getPacketSender()
						.sendMessage("Youtubers are not allowed to buy items from the general-store.");
				return this;
			}
			if (player.getGameMode() == GameMode.HARDCORE_IRONMAN) {
				player.getPacketSender()
						.sendMessage("Hardcore-ironman-players are not allowed to buy items from the general-store.");
				return this;
			}
		}
		if (!shopSellsItem(item))
			return this;

		if (getItems()[slot].getAmount() <= 1 && id != GENERAL_STORE) {

			player.getPacketSender()
					.sendMessage("The shop can't be 1 items and needs to regenerate some items first..");

		}

		if (item.getAmount() > getItems()[slot].getAmount())
			item.setAmount(getItems()[slot].getAmount());
		int amountBuying = item.getAmount();
		if (id == 21) { //farming cheapfix
			if (getItems()[slot].getAmount() - amountBuying <= 1) {
				amountBuying = getItems()[slot].getAmount() - 1;
				while(getItems()[slot].getAmount() - amountBuying <= 1) {
					if (getItems()[slot].getAmount() - amountBuying == 1) break;
					amountBuying--;
				}
			}
		}
		if (getItems()[slot].getAmount() < amountBuying) {
			amountBuying = getItems()[slot].getAmount() - 101;
		}
		if (amountBuying <= 0)
			return this;

		if (amountBuying > 5000) {
			player.getPacketSender().sendMessage(
					"You can only buy 5000 " + ItemDefinition.forId(item.getId()).getName() + "s at a time.");
			return this;
		}
		if (UltimateIronmanHandler.hasItemsStored(player) && player.getLocation() != Location.DUNGEONEERING) {
			player.getPacketSender().sendMessage("You must claim your stored items at Dungeoneering first.");
			return this;
		}
		if (UltimateIronmanHandler.hasItemsStored(player) && id == DUNGEONEERING_STORE) {
			player.getPacketSender().sendMessage("You must claim your stored items at Dungeoneering first.");
			return this;
		}
		
		boolean customShop = getCurrency().getId() == -1;
		boolean usePouch = false;
		int playerCurrencyAmount = 0;
		int value = ItemDefinition.forId(item.getId()).getValue();
		String currencyName = "";
		if (getCurrency().getId() != -1) {
			playerCurrencyAmount = player.getInventory().getAmount(currency.getId());
			currencyName = ItemDefinition.forId(currency.getId()).getName().toLowerCase();
			if (currency.getId() == 995) {
				if (player.getMoneyInPouch() >= value) {
					playerCurrencyAmount = player.getMoneyInPouchAsInt();
					if (!(player.getInventory().getFreeSlots() == 0
							&& player.getInventory().getAmount(currency.getId()) == value)) {
						usePouch = true;
					}
				}
			} else {
				/** CUSTOM CURRENCY, CUSTOM SHOP VALUES **/
				if (id == RACNOR_SHOP || id == PIKKUPSTIX || id == PIKKUPSTIX2 || id == EXPLORER_SHOP|| id == TOKKUL_EXCHANGE_STORE || id == RAIDS_STORE || id == ENERGY_FRAGMENT_STORE || id == STARDUST_STORE || id == AGILITY_TICKET_STORE
						|| id == GRAVEYARD_STORE || id == HOLY_WATER_STORE) {
					value = (int) ShopManager.getCustomShopData(id, item.getId())[0];
				}
			}
		} else {
			Object[] obj = ShopManager.getCustomShopData(id, item.getId());
			if (obj == null)
				return this;
			value = (int) obj[0];
			currencyName = (String) obj[1];
			if (id == PKING_REWARDS_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getPkPoints();
			} else if (id == VOTING_REWARDS_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getVotingPoints();
			} else if (id == DUNGEONEERING_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getDungeoneeringTokens();
			} else if (id == DONATOR_STORE_1) {
				playerCurrencyAmount = player.getPointsHandler().getDonationPoints();
			} else if (id == TRIVIA_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getTriviaPoints();
			} else if (id == LOYALTY_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getLoyaltyPoints();
			} else if (id == BOSS_POINT_STORE) {
				playerCurrencyAmount = player.getBossPoints();
			} else if (id == PEST_CONTROL_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getCommendations();
			} else if (id == DONATOR_STORE_2) {
				playerCurrencyAmount = player.getPointsHandler().getDonationPoints();
			} else if (id == DONATOR_STORE_3) {
				playerCurrencyAmount = player.getPointsHandler().getDonationPoints();
			} else if (id == DONATOR_STORE_4) {
				playerCurrencyAmount = player.getPointsHandler().getDonationPoints();
			} else if (id == PRESTIGE_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getPrestigePoints();
			} else if (id == SLAYER_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getSlayerPoints();
			}
		}
		if (value <= 0) {
			return this;
		}
		if (!hasInventorySpace(player, item, getCurrency().getId(), value)) {
			player.getPacketSender().sendMessage("You do not have any free inventory slots.");
			return this;
		}
		if (playerCurrencyAmount <= 0 || playerCurrencyAmount < value) {
			player.getPacketSender()
					.sendMessage("You do not have enough "
							+ ((currencyName.endsWith("s") ? (currencyName) : (currencyName + "s")))
							+ " to purchase this item.");
			return this;
		}
		if (id == SKILLCAPE_STORE_1 || id == SKILLCAPE_STORE_2 || id == SKILLCAPE_STORE_3) {
			for (int i = 0; i < item.getDefinition().getRequirement().length; i++) {
				int req = item.getDefinition().getRequirement()[i];
				if ((i == 3 || i == 5) && req == 99)
					req *= 10;
				if (req > player.getSkillManager().getMaxLevel(i)) {
					player.getPacketSender().sendMessage("You need to have at least level 99 in "
							+ Misc.formatText(Skill.forId(i).toString().toLowerCase()) + " to buy this item.");
					return this;
				}
			}
		} else if (id == GAMBLING_STORE) {
			if (item.getId() == 15084 || item.getId() == 299) {
				if (player.getRights() == PlayerRights.PLAYER) {
					player.getPacketSender().sendMessage("You need to be a member to use these items.");
					return this;
				}
			}
		}

		for (int i = amountBuying; i > 0; i--) {
			if (!shopSellsItem(item)) {
				break;
			}
			if (getItems()[slot].getAmount() < amountBuying) {
				amountBuying = getItems()[slot].getAmount() - 101;

			}

			if (getItems()[slot].getAmount() <= 1 && id != GENERAL_STORE) {

				player.getPacketSender()
						.sendMessage("The shop can't be below 1 items and needs to regenerate some items first...");
				break;
			}
			if (!item.getDefinition().isStackable()) {
				if (playerCurrencyAmount >= value && hasInventorySpace(player, item, getCurrency().getId(), value)) {

					if (!customShop) {
						if (usePouch) {
							player.setMoneyInPouch((player.getMoneyInPouch() - value));
						} else {
							player.getInventory().delete(currency.getId(), value, false);
						}
					} else {
						if (id == PKING_REWARDS_STORE) {
							player.getPointsHandler().setPkPoints(-value, true);
						} else if (id == VOTING_REWARDS_STORE) {
							player.getPointsHandler().setVotingPoints(-value, true);
						} else if (id == DUNGEONEERING_STORE) {
							player.getPointsHandler().setDungeoneeringTokens(-value, true);
						} else if (id == DONATOR_STORE_1) {
							player.getPointsHandler().setDonationPoints(-value, true);
						} else if (id == BOSS_POINT_STORE) {
							player.setBossPoints(player.getBossPoints() - value);
						} else if (id == PEST_CONTROL_STORE) {
							player.getPointsHandler().setCommendations(player.getPointsHandler().getCommendations() - value);
						} else if (id == TRIVIA_STORE) {
							player.getPointsHandler().setTriviaPoints(-value, true);
						} else if (id == LOYALTY_STORE) {
							player.getPointsHandler().setLoyaltyPoints(-value, true);
						} else if (id == DONATOR_STORE_2) {
							player.getPointsHandler().setDonationPoints(-value, true);
						} else if (id == DONATOR_STORE_3) {
							player.getPointsHandler().setDonationPoints(-value, true);
						} else if (id == DONATOR_STORE_4) {
							player.getPointsHandler().setDonationPoints(-value, true);
						} else if (id == PRESTIGE_STORE) {
							player.getPointsHandler().setPrestigePoints(-value, true);
						} else if (id == SLAYER_STORE) {
							player.getPointsHandler().setSlayerPoints(-value, true);
						}
					}

					super.switchItem(to, new Item(item.getId(), 1), slot, false, false);

					playerCurrencyAmount -= value;
				} else {
					break;
				}
			} else {
				if (playerCurrencyAmount >= value && hasInventorySpace(player, item, getCurrency().getId(), value)) {

					int canBeBought = playerCurrencyAmount / (value);
					if (canBeBought >= amountBuying) {
						canBeBought = amountBuying;
					}
					if (canBeBought == 0)
						break;

					if (!customShop) {
						if (usePouch) {
							player.setMoneyInPouch((player.getMoneyInPouch() - (value * canBeBought)));
						} else {
							player.getInventory().delete(currency.getId(), value * canBeBought, false);
						}
					} else {
						if (id == PKING_REWARDS_STORE) {
							player.getPointsHandler().setPkPoints(-value * canBeBought, true);
						} else if (id == VOTING_REWARDS_STORE) {
							player.getPointsHandler().setVotingPoints(-value * canBeBought, true);
						} else if (id == DUNGEONEERING_STORE) {
							player.getPointsHandler().setDungeoneeringTokens(-value * canBeBought, true);
						} else if (id == DONATOR_STORE_1) {
							player.getPointsHandler().setDonationPoints(-value * canBeBought, true);
						} else if (id == TRIVIA_STORE) {
							player.getPointsHandler().setTriviaPoints(-value * canBeBought, true);
						} else if (id == LOYALTY_STORE) {
							player.getPointsHandler().setLoyaltyPoints(-value * canBeBought, true);
						} else if (id == BOSS_POINT_STORE) {
							player.setBossPoints(player.getBossPoints() - (value * canBeBought));
						} else if (id == PEST_CONTROL_STORE) {
							player.getPointsHandler().setCommendations(player.getPointsHandler().getCommendations() - (value * canBeBought));
						} else if (id == DONATOR_STORE_2) {
							player.getPointsHandler().setDonationPoints(-value * canBeBought, true);
						} else if (id == DONATOR_STORE_3) {
							player.getPointsHandler().setDonationPoints(-value * canBeBought, true);
						} else if (id == DONATOR_STORE_4) {
							player.getPointsHandler().setDonationPoints(-value * canBeBought, true);
						} else if (id == PRESTIGE_STORE) {
							player.getPointsHandler().setPrestigePoints(-value * canBeBought, true);
						} else if (id == SLAYER_STORE) {
							player.getPointsHandler().setSlayerPoints(-value * canBeBought, true);
						}
					}
					super.switchItem(to, new Item(item.getId(), canBeBought), slot, false, false);
					playerCurrencyAmount -= value;
					break;
				} else {
					break;
				}
			}
			amountBuying--;
		}
		if (!customShop) {
			if (usePouch) {
				player.getPacketSender().sendString(8135, "" + player.getMoneyInPouch()); // Update
				// the
				// money
				// pouch
			}
		} else {
			player.getPointsHandler().refreshPanel();
		}
		player.getInventory().refreshItems();
		fireRestockTask();
		refreshItems();
		publicRefresh();
		return this;
	}

	/**
	 * Checks if a player has enough inventory space to buy an item
	 *
	 * @param item
	 *            The item which the player is buying
	 * @return true or false if the player has enough space to buy the item
	 */
	public static boolean hasInventorySpace(Player player, Item item, int currency, int pricePerItem) {
		if (player.getInventory().getFreeSlots() >= 1) {
			return true;
		}
		if (item.getDefinition().isStackable()) {
			if (player.getInventory().contains(item.getId())) {
				return true;
			}
		}
		if (currency != -1) {
			if (player.getInventory().getFreeSlots() == 0
					&& player.getInventory().getAmount(currency) == pricePerItem) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Shop add(Item item, boolean refresh) {
		super.add(item, false);
		if (id != RECIPE_FOR_DISASTER_STORE)
			publicRefresh();
		return this;
	}

	@Override
	public int capacity() {
		return 42;
	}

	@Override
	public StackType stackType() {
		return StackType.STACKS;
	}

	@Override
	public Shop refreshItems() {
		if (id == RECIPE_FOR_DISASTER_STORE) {
			RecipeForDisaster.openRFDShop(getPlayer());
			return this;
		}
		for (Player player : World.getPlayers()) {
			if (player == null || !player.isShopping() || player.getShop() == null || player.getShop().id != id)
				continue;
			player.getPacketSender().sendItemContainer(player.getInventory(), INVENTORY_INTERFACE_ID);
			player.getPacketSender().sendItemContainer(ShopManager.getShops().get(id), ITEM_CHILD_ID);
			player.getPacketSender().sendString(NAME_INTERFACE_CHILD_ID, name);
			if (player.getInputHandling() == null || !(player.getInputHandling() instanceof EnterAmountToSellToShop
					|| player.getInputHandling() instanceof EnterAmountToBuyFromShop))
				player.getPacketSender().sendInterfaceSet(INTERFACE_ID, INVENTORY_INTERFACE_ID - 1);
		}
		return this;
	}

	@Override
	public Shop full() {
		getPlayer().getPacketSender().sendMessage("The shop is currently full. Please come back later.");
		return this;
	}

	public String shopPriceEx(int shopPrice) {
		String ShopAdd = "";
		if (shopPrice >= 1000 && shopPrice < 1000000) {
			ShopAdd = " (" + (shopPrice / 1000) + "K)";
		} else if (shopPrice >= 1000000) {
			ShopAdd = " (" + (shopPrice / 1000000) + " million)";
		}
		return ShopAdd;
	}

	private boolean shopSellsItem(Item item) {
		return contains(item.getId());
	}

	public void fireRestockTask() {
		if (isRestockingItems() || fullyRestocked())
			return;
		setRestockingItems(true);
		TaskManager.submit(new ShopRestockTask(this));
	}

	public void restockShop() {
		for (int shopItemIndex = 0; shopItemIndex < getOriginalStock().length; shopItemIndex++) {
			int currentStockAmount = getItems()[shopItemIndex].getAmount();
			add(getItems()[shopItemIndex].getId(), getOriginalStock()[shopItemIndex].getAmount() - currentStockAmount);
//			publicRefresh();
			refreshItems();
		}

	}

	public boolean fullyRestocked() {
		if (id == GENERAL_STORE) {
			return getValidItems().size() == 0;
		} else if (id == RECIPE_FOR_DISASTER_STORE) {
			return true;
		}
		if (getOriginalStock() != null) {
			for (int shopItemIndex = 0; shopItemIndex < getOriginalStock().length; shopItemIndex++) {
				if (getItems()[shopItemIndex].getAmount() != getOriginalStock()[shopItemIndex].getAmount())
					return false;
			}
		}
		return true;
	}

	public static boolean shopBuysItem(int shopId, Item item) {
		if (shopId == GENERAL_STORE)
			return true;
		if (shopId == BULLET_STORE || shopId == RACNOR_SHOP || shopId == SUPERSAYIANS || shopId == GRIM || shopId == PIKKUPSTIX || shopId == PIKKUPSTIX2 || shopId == DUNGEONEERING_STORE || shopId == BOSS_POINT_STORE|| shopId == PEST_CONTROL_STORE || shopId == TRIVIA_STORE || shopId == LOYALTY_STORE
				|| shopId == DONATOR_STORE_1 || shopId == DONATOR_STORE_2 || shopId == DONATOR_STORE_3 || shopId == DONATOR_STORE_4 || shopId == PKING_REWARDS_STORE
				|| shopId == VOTING_REWARDS_STORE || shopId == RECIPE_FOR_DISASTER_STORE || shopId == ENERGY_FRAGMENT_STORE || shopId == AGILITY_TICKET_STORE || shopId == GRAVEYARD_STORE
				|| shopId == TOKKUL_EXCHANGE_STORE || shopId == RAIDS_STORE || shopId == PRESTIGE_STORE || shopId ==  HOLY_WATER_STORE || shopId == STARDUST_STORE || shopId == SLAYER_STORE)
			return false;
		Shop shop = ShopManager.getShops().get(shopId);
		if (shop != null && shop.getOriginalStock() != null) {
			for (Item it : shop.getOriginalStock()) {
				if (it != null && it.getId() == item.getId())
					return true;
			}
		}
		return false;
	}

	public static class ShopManager {

		private static Map<Integer, Shop> shops = new HashMap<Integer, Shop>();

		public static Map<Integer, Shop> getShops() {
			return shops;
		}

		public static JsonLoader parseShops() {
			return new JsonLoader() {
				@Override
				public void load(JsonObject reader, Gson builder) {
					int id = reader.get("id").getAsInt();
					String name = reader.get("name").getAsString();
					Item[] items = builder.fromJson(reader.get("items").getAsJsonArray(), Item[].class);
					Item currency = new Item(reader.get("currency").getAsInt());
					shops.put(id, new Shop(null, id, name, currency, items));
				}

				@Override
				public String filePath() {
					return "./data/def/json/world_shops.json";
				}
			};
		}

		public static Object[] getCustomShopData(int shop, int item) {
			if (shop == VOTING_REWARDS_STORE) {
				switch (item) {
					case 619:
						return new Object[] { 40, "Voting Points" };
					case 20922:
						return new Object[] { 8, "Voting Points" };
					case 7612:
					case 7610:
					case 7611:
					case 7613:
						return new Object[] { 25, "Voting Points" };
					case 7118:
						return new Object[] { 4, "Voting Points" };
					case 3230:
						return new Object[] { 60, "Voting Points" };
				}
				return new Object[] { 25, "Voting Points" };
			} else if (shop == PKING_REWARDS_STORE) {
				switch (item) {
					case 13879:
					case 13883:
					case 15332:
						return new Object[] { 1, "PK Points" };
					case 2581:
					case 2577:
					case 6570:
					case 11235:
					case 4151:
					case 20072:
						return new Object[] { 10, "PK Points" };
					case 10550:
					case 10548:
					case 10551:
					case 11283:
					case 15241:
						return new Object[] { 25, "PK Points" };
					case 13887:
					case 13893:
					case 13896:
					case 13884:
					case 13890:
						return new Object[] { 50, "PK Points" };
					case 20000:
					case 20001:
					case 20002:
						return new Object[] { 60, "PK Points" };
					case 4706:
					case 11696:
					case 11698:
					case 11700:
						return new Object[] { 45, "PK Points" };
					case 11730:
					case 19111:
						return new Object[] { 30, "PK Points" };
					case 15486:
					case 15018:
					case 15020:
					case 15019:
					case 15220:
						return new Object[] { 20, "PK Points" };
					case 15471:
						return new Object[] { 1000, "PK Points" };
					case 15472:
						return new Object[] { 2000, "PK Points" };
					case 15473:	
						return new Object[] { 3000, "PK Points" };
					case 12926:
						return new Object[] { 100, "PK Points" };
					case 11694:
					case 14484:
						return new Object[] { 65, "PK Points" };
				}
			} else if (shop == STARDUST_STORE) {
				switch (item) {
					case 13632:
					case 13633:
					case 13634:
					case 13635:
					case 13636:
					case 13637:
					case 13638:
					case 13639:
					case 13640:
						return new Object[] { 250, "Stardust" };
					case 13641:
						return new Object[] { 500, "Stardust" };
					case 13642:
						return new Object[] { 750, "Stardust" };
				}
				return new Object[] { 100, "Stardust Points" };
			} else if (shop == ENERGY_FRAGMENT_STORE) {
				switch (item) {
					case 5509:
						return new Object[] { 400, "energy fragments" };
					case 5510:
						return new Object[] { 750, "energy fragments" };
					case 5512:
						return new Object[] { 1100, "energy fragments" };
				}
			} else if (shop == PEST_CONTROL_STORE) {
				switch (item) {
					case 11663:
					case 11665:
					case 11664:
						return new Object[] { 750, "Commendation Points" };
					case 8842:
						return new Object[] { 500, "Commendation Points" };
					case 8839:
					case 8840:
						return new Object[] { 1000, "CommendationCommendation Points" };

				}
			} else if (shop == BOSS_POINT_STORE) {
				switch (item) {
					case 18349:
					case 18351:
					case 18353:
					case 18355:
					case 18357:
						return new Object[] { 750, "Boss Points" };
					case 11730:
					case 11716:
					case 15486:
					case 10550:
					case 10551:
					case 10548:
					case 18337:
						return new Object[] { 120, "Boss Points" };
					case 989:
					case 2572:
						return new Object[] { 10, "Boss Points" };
					case 20838:
						return new Object[] { 2000, "Boss Points" };
					//case 18782:
					//return new Object[] { 65, "Boss Points" };
					case 20012:
					case 20010:
					case 20011:
					case 20020:
					case 20019:
					case 20016:
					case 20017:
					case 20018:
					case 20022:
					case 20021:
					case 18903:
					case 11425:
						return new Object[] { 500, "Boss Points" };
					case 11720:
					case 11718:
					case 11722:
					case 11724:
					case 11726:
						return new Object[] {350, "Boss Points" };
					case 19933:
						return new Object[] {250, "Boss Points" };
					case 3645:
					case 3643:
					case 3644:
					case 3647:
					case 3648:
					case 3642:
					case 3650:
						return new Object[] {500, "Boss Points" };
					case 20821:
					case 20822:
					case 20823:
					case 20824:
					case 20825:
						return new Object[] {750, "Boss Points" };
					case 20819:
						return new Object[] {4000, "Boss Points" };
					case 20820:
						return new Object[] {1000, "Boss Points" };
					case 20833:
					case 20832:
					case 20834:
					case 20831:
					case 20836:
					case 20829:
					case 20830:
						return new Object[] {1000, "Boss Points" };
					case 13216:
					case 13217:
					case 13218:
					case 13219:
					case 13220:
					case 13215:
						return new Object[] {400, "Boss Points" };
					case 3689:
					case 3687:
					case 3688:
					case 3691:
					case 3690:
					case 3692:
						return new Object[] {500, "Boss Points" };
					case 18931:
						return new Object[] {500, "Boss Points" };
					case 20806:
						return new Object[] {1000, "Boss Points" };
				}
				return new Object[] { 100, "Boss Points" };
			} else if (shop == DONATOR_STORE_1) { //donation store
				switch (item) {
					case 20818:
						return new Object[] { 75, "Donation Points" };
					case 20751:
						return new Object[] { 150, "Donation Points" };
					case 4644:
					case 4646:
					case 4645:
					case 20090:
					case 11552:
						return new Object[] { 25, "Donation Points" };
					case 20819:
						return new Object[] { 35, "Donation Points" };
					case 20820:
						return new Object[] { 10, "Donation Points" };
					case 20838:
						return new Object[] { 20, "Donation Points" };
					case 20833:
					case 20832:
					case 20834:
					case 20831:
					case 20836:
					case 20829:
					case 20830:
						return new Object[] { 10, "Donation Points" };
					case 20828:
					case 20827:
						return new Object[] { 35, "Donation Points" };
					case 20750:
						return new Object[] { 100, "Donation Points" };
					case 18989: //Doom blade
						return new Object[] { 30, "Donation Points" };
					case 20847:
					case 20850:
					case 20851:
					case 20849:
					case 20848:
						return new Object[] { 15, "Donation Points" };
					case 20807:
					case 20808:
					case 20809:
					case 13239:
					case 20080:
					case 20806:
						return new Object[] { 10, "Donation Points" };
					case 20805:
						return new Object[] { 12, "Donation Points" };
					case 13216:
					case 13217:
					case 13218:
					case 13219:
					case 13220:
					case 13215:
						return new Object[] { 4, "Donation Points" };
					case 20853:
					case 20854:
						return new Object[] { 40, "Donation Points" };
				}
				return new Object[] { 100, "Donation Points" };
			} else if (shop == DONATOR_STORE_2) {
				switch (item) {
					case 20871:
					case 20872:
						return new Object[] { 35, "Donation Points" };
					case 20870:
						return new Object[] { 10, "Donation Points" };
					case 18958:
						return new Object[] { 10, "Donation Points" };
					case 3230:
						return new Object[] { 10, "Donation Points" };
					case 19934:
						return new Object[] { 3, "Donation Points" };
					case 7118:
					case 15471:
						return new Object[] { 1, "Donation Points" };
					case 15472:
						return new Object[] { 2, "Donation Points" };
					case 15473:
						return new Object[] { 3, "Donation Points" };
					case 20985:
						return new Object[] { 250, "Donation Points" };
					case 12423:
						return new Object[] { 20, "Donation Points" };
					case 12424:
						return new Object[] { 50, "Donation Points" };
					case 12425:
						return new Object[] { 100, "Donation Points" };
					case 15668:
						return new Object[] { 60, "Donation Points" };
					case 15667:
						return new Object[] { 35, "Donation Points" };
					case 15662:
						return new Object[] { 15, "Donation Points" };
					case 15664:
						return new Object[] { 25, "Donation Points" };
					case 20873:
						return new Object[] { 100, "Donation Points" };
					case 20931:
						return new Object[] { 15, "Donation Points" };
					case 20810:
					case 20811:
					case 20812:
					case 20813:
					case 20814:
						return new Object[] { 20, "Donation Points" };
					case 20821:
					case 20822:
					case 20823:
					case 20824:
					case 20825:
						return new Object[] { 5, "Donation Points" };
					case 20842:
					case 20845:
					case 20844:
					case 20846:
					case 20841:
					case 20843:
						return new Object[] { 8, "Donation Points" };
					case 20510:
						return new Object[] { 5, "Donation Points" };
					case 11423:
					case 11555:
					case 11533:
						return new Object[] { 20, "Donation Points" };
					}
				} else if (shop == DONATOR_STORE_3) {
					switch (item) {
						case 20942:
						case 20936:
						case 20934:
						case 20933:
						case 20935:
						case 20940:
						case 20941:
						case 20939:
						case 20937:
						case 20938:
							return new Object[] { 20, "Donation Points" };
						case 19004:
							return new Object[] { 25, "Donation Points" };
						case 20967:
						case 20968:
						case 20969:
						case 20970:
						case 2762:
						case 2760:
						case 2098:
						case 2759:
						case 20975:
						case 20977:
							return new Object[] { 15, "Donation Points" };
						case 21016:
						case 21014:
						case 21005:
						case 21000:
						case 21015:
							return new Object[] { 50, "Donation Points" };
						case 21017:
							return new Object[] { 100, "Donation Points" };
						case 21001:
							return new Object[] { 400, "Donation Points" };
						case 20996:
						case 20994:
						case 20997:
						case 20995:
						case 20999:
							return new Object[] { 25, "Donation Points" };
						case 20998:
							return new Object[] { 300, "Donation Points" };
						case 20980:
						case 20981:
						case 20982:
						case 20983:
						case 20984:
							return new Object[] { 25, "Donation Points" };
						case 20932:
							return new Object[] { 250, "Donation Points" };
				}
				return new Object[] { 100, "Donation Points" };
				} else if (shop == DONATOR_STORE_4) {
					switch (item) {
						case 21038:
							return new Object[] { 20, "Donation Points" };
						case 21039:
							return new Object[] { 25, "Donation Points" };
						case 21040:
							return new Object[] { 30, "Donation Points" };
						case 21041:
							return new Object[] { 35, "Donation Points" };
						case 21042:
							return new Object[] { 40, "Donation Points" };
						case 21043:
							return new Object[] { 45, "Donation Points" };
						case 11724:
							return new Object[] { 23, "Donation Points" };
						case 11726:
							return new Object[] { 23, "Donation Points" };
						case 11728:
							return new Object[] { 23, "Donation Points" };
						case 1667:
							return new Object[] { 30, "Donation Points" };
						case 1554:
							return new Object[] { 30, "Donation Points" };
						case 1647:
							return new Object[] { 30, "Donation Points" };
						case 1666:
							return new Object[] { 30, "Donation Points" };
						case 17488:
							return new Object[] { 21, "Donation Points" };
						case 799:
							return new Object[] { 21, "Donation Points" };
						case 3062:
							return new Object[] { 21, "Donation Points" };
						case 3064:
							return new Object[] { 21, "Donation Points" };
						case 3063:
							return new Object[] { 21, "Donation Points" };
						case 3066:
							return new Object[] { 21, "Donation Points" };
						case 3065:
							return new Object[] { 35, "Donation Points" };
						case 1648:
							return new Object[] { 30, "Donation Points" };
						case 3717:
							return new Object[] { 175, "Donation Points" };
						case 21072:
							return new Object[] { 100, "Donation Points" };
						case 21073:
							return new Object[] { 50, "Donation Points" };
						case 21048:
							return new Object[] { 22, "Donation Points" };
						case 21046:
							return new Object[] { 22, "Donation Points" };
						case 21047:
							return new Object[] { 22, "Donation Points" };
						case 21071:
							return new Object[] { 22, "Donation Points" };
						case 21049:
							return new Object[] { 22, "Donation Points" };
						case 21070:
							return new Object[] { 50, "Donation Points" };
						case 21035:
							return new Object[] { 12, "Donation Points" };
						case 21018:
							return new Object[] { 12, "Donation Points" };
						case 21019:
							return new Object[] { 12, "Donation Points" };
						case 21037:
							return new Object[] { 12, "Donation Points" };
						case 21036:
							return new Object[] { 12, "Donation Points" };
					}
				return new Object[] { 100, "Donation Points" };
			} else if (shop == PIKKUPSTIX) {
				switch (item) {
			case 12155:
				return new Object[] { 20000, "20k Coins" };
			case 18016:
				return new Object[] { 20000, "20k Coins" };
			case 15262:
				return new Object[] { 20000, "20k Coins" };
			case 2859:
				return new Object[] { 20000, "20k Coins" };
			case 6291:
				return new Object[] { 20000, "20k Coins" };
			case 3363:
				return new Object[] { 20000, "20k Coins" };
			case 6319:
				return new Object[] { 20000, "20k Coins" };
			case 1783:
				return new Object[] { 20000, "20k Coins" };
			case 3095:	
				return new Object[] { 20000, "20k Coins" };
			case 12168:
				return new Object[] { 20000, "20k Coins" };
			case 2143:
				return new Object[] { 20000, "20k Coins" };
			case 3138:
				return new Object[] { 20000, "20k Coins" };
			case 6032:
				return new Object[] { 20000, "20k Coins" };
			case 9976:
				return new Object[] { 20000, "20k Coins" };
			case 3325:
				return new Object[] { 20000, "20k Coins" };
			case 12156:
				return new Object[] { 20000, "20k Coins" };
			case 12164:
				return new Object[] { 20000, "20k Coins" };
			case 12166:
				return new Object[] { 20000, "20k Coins" };
			case 12167:
				return new Object[] { 20000, "20k Coins" };
			case 12165:
				return new Object[] { 20000, "20k Coins" };
			case 6010:
				return new Object[] { 20000, "20k Coins" };
			case 12153:
				return new Object[] { 20000, "20k Coins" };
			case 8431:
				return new Object[] { 20000, "20k Coins" };
			case 2150:
				return new Object[] { 20000, "20k Coins" };
			case 7939:
				return new Object[] { 20000, "20k Coins" };
				}
			} else if (shop == PIKKUPSTIX2) {
				switch (item) {
			case 1963:
				return new Object[] { 20000, "20k Coins" };
			case 1933:
				return new Object[] { 20000, "20k Coins" };
			case 10117:
				return new Object[] { 20000, "20k Coins" };
			case 14616:
				return new Object[] { 20000, "20k Coins" };
			case 6979:
				return new Object[] { 20000, "20k Coins" };
			case 2460:
				return new Object[] { 20000, "20k Coins" };
			case 10020:
				return new Object[] { 20000, "20k Coins" };
			case 12162:
				return new Object[] { 20000, "20k Coins" };
			case 5933:	
				return new Object[] { 20000, "20k Coins" };
			case 1442:
				return new Object[] { 20000, "20k Coins" };
			case 1440:
				return new Object[] { 20000, "20k Coins" };
			case 1438:
				return new Object[] { 20000, "20k Coins" };
			case 1444:
				return new Object[] { 20000, "20k Coins" };
			case 571:
				return new Object[] { 20000, "20k Coins" };
			case 6155:
				return new Object[] { 20000, "20k Coins" };
			case 10149:
				return new Object[] { 20000, "20k Coins" };
			case 237:
				return new Object[] { 20000, "20k Coins" };
			case 3226:
				return new Object[] { 20000, "20k Coins" };
			case 1119:
				return new Object[] { 20000, "20k Coins" };
			case 10818:
				return new Object[] { 20000, "20k Coins" };
			case 12111:
				return new Object[] { 20000, "20k Coins" };
			case 12113:
				return new Object[] { 20000, "20k Coins" };
			case 12115:
				return new Object[] { 20000, "20k Coins" };
			case 12117:
				return new Object[] { 20000, "20k Coins" };
			case 12119:
				return new Object[] { 20000, "20k Coins" };
			case 12121:
				return new Object[] { 20000, "20k Coins" };
			case 590:
				return new Object[] { 20000, "20k Coins" };
			case 1635:
				return new Object[] { 20000, "20k Coins" };
			case 2132:
				return new Object[] { 20000, "20k Coins" };
			case 9978:
				return new Object[] { 20000, "20k Coins" };
			case 1937:
				return new Object[] { 20000, "20k Coins" };
				}
			} else if (shop == RACNOR_SHOP) {
				switch (item) {
					case 1153:
					case 1115:
					case 1067:
					case 21012:
					case 21011:
					return new Object[] { 200, "1B Tickets" };
					case 21031:
					case 21002:
					case 21030:
						return new Object[] { 150, "1B Tickets" };
					case 21082:
						return new Object[] { 330000, "1B Tickets" };
					case 20089:
						return new Object[] { 500000, "1B Tickets" };
					case 20086:
					case 20087:
					case 20088:
					case 21021:
					case 21020:
						return new Object[] { 500, "1B Tickets" };
					case 21013:
						return new Object[] { 2500, "1B Tickets" };
					case 20095:
					case 20096:
					case 20097:
						return new Object[] { 20000, "1B Tickets" };
					case 20104:
					case 20105:
					case 20103:
					case 20106:
					case 20107:
						return new Object[] { 1500, "1B Tickets" };
					case 20102:
						return new Object[] { 2000, "1B Tickets" };
					case 13196:
					case 13197:
					case 13198:
					case 13207:
					case 13206:
					case 13199:
						return new Object[] { 3000, "1B Tickets" };
					case 21060:
						return new Object[] { 4000, "1B Tickets" };
					case 20255:
					case 3683:
					case 3684:
					case 3685:
					case 3686:
						return new Object[] { 2000, "1B Tickets" };
					case 21061:
						return new Object[] { 3000, "1B Tickets" };
					case 18889:
						return new Object[] { 1000000, "1B Tickets" };
					case 15668:
						return new Object[] { 120000000, "1B Tickets" };
						
				}
			} else if (shop == AGILITY_TICKET_STORE) {
				switch (item) {
					case 14936:
					case 14938:
						return new Object[] { 60, "agility tickets" };
					case 10941:
					case 10939:
					case 10940:
					case 10933:
						return new Object[] { 20, "agility tickets" };
					case 13661:
						return new Object[] { 100, "agility tickets" };
				}
			} else if (shop == GRAVEYARD_STORE) {
				switch (item) {
					case 20010:
					case 20011:
					case 20012:
					case 20009:
					case 20020:
						return new Object[] { 500, "zombie fragments" };
					case 10551:
						return new Object[] { 450, "zombie fragments" };
					case 10548:
					case 10550:
						return new Object[] { 100, "zombie fragments" };
					case 11846:
					case 11848:
					case 11850:
					case 11852:
					case 11854:
					case 11856:
						return new Object[] { 25, "zombie fragments" };
					case 18890:
						return new Object[] { 1000, "zombie fragments" };
					case 19005:
						return new Object[] { 500, "zombie fragments" };
					case 18891:
						return new Object[] { 3000, "zombie fragments" };
					case 15241:
						return new Object[] { 250, "zombie fragments" };
					case 1:
						return new Object[] { 2, "zombie fragments" };
					case 15243:
						return new Object[] { 1, "zombie fragments" };
				}
				return new Object[] { 10000, "zombie fragments" };
			} else if (shop == TOKKUL_EXCHANGE_STORE) {
				switch (item) {
				case 15668:
					return new Object[] { 1000, "@mag@7 Dragon Balls" };
				case 15665:
					return new Object[] { 200, "@mag@7 Dragon Balls" };
				case 15667:
					return new Object[] { 750, "@mag@7 Dragon Balls" };
				case 15662:
					return new Object[] { 375, "@mag@7 Dragon Balls" };
				case 15664:
					return new Object[] { 500, "@mag@7 Dragon Balls" };
				case 15663:
					return new Object[] { 300, "@mag@7 Dragon Balls" };
				case 15666:
					return new Object[] { 100, "@mag@7 Dragon Balls" };
				}
			} else if (shop == RAIDS_STORE) {
				switch (item) {
				case 21048:
					return new Object[] { 72000, "@mag@Raids @blu@Token" };
				case 21046:
					return new Object[] { 72000, "@mag@Raids @blu@Token" };
				case 21047:
					return new Object[] { 72000, "@mag@Raids @blu@Token" };
				case 21071:
					return new Object[] { 72000, "@mag@Raids @blu@Token" };
				case 21049:
					return new Object[] { 72000, "@mag@Raids @blu@Token" };
				case 21070:
					return new Object[] { 150000, "@mag@Raids @blu@Token" };
				case 21072:
					return new Object[] { 300000, "@mag@Raids @blu@Token" };
				case 21035:
					return new Object[] { 36000, "@mag@Raids @blu@Token" };
				case 21018:
					return new Object[] { 36000, "@mag@Raids @blu@Token" };
				case 21019:
					return new Object[] { 36000, "@mag@Raids @blu@Token" };
				case 21037:
					return new Object[] { 36000, "@mag@Raids @blu@Token" };
				case 21036:
					return new Object[] { 36000, "@mag@Raids @blu@Token" };
				case 21073:
					return new Object[] { 150000, "@mag@Raids @blu@Token" };
				case 21038:
					return new Object[] { 60000, "@mag@Raids @blu@Token" };
				case 21039:
					return new Object[] { 75000, "@mag@Raids @blu@Token" };
				case 21040:
					return new Object[] { 90000, "@mag@Raids @blu@Token" };
				case 21041:
					return new Object[] { 105000, "@mag@Raids @blu@Token" };
				case 21042:
					return new Object[] { 120000, "@mag@Raids @blu@Token" };
				case 21043:
					return new Object[] { 135000, "@mag@Raids @blu@Token" };
				}
			} else if (shop == DUNGEONEERING_STORE) {
				switch (item) {
					case 18351:
					case 18349:
					case 18353:
					case 18357:
					case 18355:
					case 18359:
					case 18361:
					case 18363:
						return new Object[] { 40000, "Dungeoneering tokens" };
					case 16955:
					case 16425:
					case 16403:
						return new Object[] { 250000, "Dungeoneering tokens" };
					case 13201:
					case 13202:
					case 13203:
					case 13204:
					case 13205:
					case 13210:
					case 13211:
					case 13212:
					case 13213:
					case 13214:
						return new Object[] { 400000, "Dungeoneering tokens" };
					case 13208:
						return new Object[] { 868500, "Dungeoneering tokens" };
					case 3643:
					case 3644:
					case 3645:
					case 3646:
					case 3647:
					case 3648:
					case 3649:
					case 3650:
					case 3652:
					case 3653:
					case 3654:
					case 3655:
					case 3656:
					case 3657:
					case 3658:
					case 3659:
					case 3651:
					case 3660:

						return new Object[] { 500000, "Dungeoneering tokens" };
					case 13209:
						return new Object[] { 6000, "Dungeoneering tokens" };
					case 18335:
						return new Object[] { 75000, "Dungeoneering tokens" };
				}
			} else if (shop == LOYALTY_STORE) {
				switch (item) {
					case 20453:
						return new Object[] { 2000, "Loyalty Points" };
					case 20452:
						return new Object[] { 1750, "Loyalty Points" };
					case 20451:
						return new Object[] { 1500, "Loyalty Points" };
					case 20450:
						return new Object[] { 1250, "Loyalty Points" };
				}
				return new Object[] { 100, "Loyalty Points" };

			} else if (shop == TRIVIA_STORE) {
				switch (item) {
				case 15662:
					return new Object[] { 450, "Trivia Points" };
					case 20870:
						return new Object[] { 600, "Trivia Points" };
					case 15664:
						return new Object[] { 750, "Trivia Points" };
					case 15667:
						return new Object[] { 1050, "Trivia Points" };
				}
				return new Object[] { 100, "Trivia Points" };
			} else if (shop == PRESTIGE_STORE) {
				switch (item) {
					case 20871:
						return new Object[] { 2500, "Prestige Points" };
					case 20872:
						return new Object[] { 2500, "Prestige Points" };
				}
			} else if (shop == SLAYER_STORE) {
				switch (item) {
					case 13263:
						return new Object[] { 250, "Slayer points" };
					case 13281:
						return new Object[] { 5, "Slayer points" };
					case 15403:
					case 11730:
					case 10887:
					case 15241:
					case 11716:
					case 20690:
						return new Object[] { 150, "Slayer points" };
					case 2717:
					case 20900:
						return new Object[] { 25, "Slayer points" };
						
					case 3947:
						return new Object[] { 250, "Slayer points" };
					case 3948:
					case 3949:
						return new Object[] { 350, "Slayer points" };
					case 20257:
						return new Object[] { 1500, "Slayer points" };
					case 21080:
						return new Object[] { 1200, "Slayer points" };
					case 17847:
						return new Object[] { 1000, "Slayer points" };
					case 21082:
						return new Object[] { 2500, "Slayer points" };
					case 21077:
						return new Object[] { 250, "Slayer points" };
					case 21078:
						return new Object[] { 250, "Slayer points" };
					case 20601:
						return new Object[] { 500, "Slayer points" };
					case 11425:
						return new Object[] { 50, "Slayer points" };
					case 11555:
						return new Object[] { 15000, "Slayer points" };
					case 11423:
						return new Object[] { 3000, "Slayer points" };
					case 20054:
						return new Object[] { 500, "Slayer points" };
					case 20818:
						return new Object[] { 5000, "Slayer points" };
					case 3689:
					case 3687:
					case 3688:
					case 3691:
					case 3690:
					case 3692:
						return new Object[] {1000, "Slayer points" };
					case 18:
						return new Object[] { 1000, "Slayer points" };
					case 2101:
						return new Object[] { 1500, "Slayer points" };
					case 15220:
					case 15020:
					case 15019:
					case 15018:
					case 11546:
						return new Object[] { 300, "Slayer points" };
					case 11550:
						return new Object[] { 200, "Slayer points" };
					case 11531:
						return new Object[] { 300, "Slayer points" };
					case 11235:
					case 4151:
					case 15486:
					case 18337:
					case 11549:
						return new Object[] { 250, "Slayer points" };
					case 15243:
						return new Object[] { 3, "Slayer points" };
					case 10551:
						return new Object[] { 200, "Slayer points" };
					case 7118:
						return new Object[] { 150, "Slayer points" };
					case 3666:
						return new Object[] { 2500, "Slayer points" };

					case 2581:
					case 2577:
					case 6585:
					case 11732:
						return new Object[] { 100, "Slayer points" };
					case 11547:
						return new Object[] {600, "Slayer points"};
					case 11548:
					case 17848:
						return new Object[] {900, "Slayer points"};
					case 2572:
						return new Object[] { 250, "Slayer points" };
				}
			}else if (shop == EXPLORER_SHOP) {
				switch (item) {

					case 15471:
						return new Object[] { 1000, "Yanille Ticket" };
					case 15472:
						return new Object[] { 2500, "Yanille Ticket" };
					case 15473:
						return new Object[] { 5000, "Yanille Ticket" };
				}
			}
			return null;
		}
	}

	/**
	 * The shop interface id.
	 */
	public static final int INTERFACE_ID = 3824;

	/**
	 * The starting interface child id of items.
	 */
	public static final int ITEM_CHILD_ID = 3900;

	/**
	 * The interface child id of the shop's name.
	 */
	public static final int NAME_INTERFACE_CHILD_ID = 3901;

	/**
	 * The inventory interface id, used to set the items right click values to
	 * 'sell'.
	 */
	public static final int INVENTORY_INTERFACE_ID = 3823;



	private int[] shopPrices;

	/*
	 * Declared shops
	 */

	public static final int DONATOR_STORE_1 = 48;
	public static final int DONATOR_STORE_2 = 49;
	public static final int DONATOR_STORE_3 = 20;
	public static final int DONATOR_STORE_4 = 5;

	public static final int TRIVIA_STORE = 50;
	public static final int LOYALTY_STORE = 72;
	private static final int RAIDS_STORE = 4;

	public static final int GENERAL_STORE = 12;
	public static final int RECIPE_FOR_DISASTER_STORE = 36;

	private static final int VOTING_REWARDS_STORE = 27;
	private static final int PKING_REWARDS_STORE = 26;
	private static final int ENERGY_FRAGMENT_STORE = 33;
	private static final int AGILITY_TICKET_STORE = 39;
	private static final int GRAVEYARD_STORE = 42;
	private static final int TOKKUL_EXCHANGE_STORE = 43;
	private static final int HOLY_WATER_STORE = 51;
	private static final int SKILLCAPE_STORE_1 = 8;
	private static final int SKILLCAPE_STORE_2 = 9;
	private static final int SKILLCAPE_STORE_3 = 10;
	private static final int GAMBLING_STORE = 41;
	private static final int DUNGEONEERING_STORE = 44;
	private static final int PRESTIGE_STORE = 46;
	public static final int BOSS_POINT_STORE = 92;
	public static final int PEST_CONTROL_STORE = 99;
	private static final int SLAYER_STORE = 47;
	public static final int STARDUST_STORE = 55;
	public static final int EXPLORER_SHOP = 28;
	public static final int RACNOR_SHOP = 1;
	public static final int BULLET_STORE = 3;
	public static final int PIKKUPSTIX = 22;
	public static final int PIKKUPSTIX2 = 23;
	public static final int SUPERSAYIANS = 2;
	public static final int GRIM = 25;
}
