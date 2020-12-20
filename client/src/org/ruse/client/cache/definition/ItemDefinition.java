package org.ruse.client.cache.definition;

import java.io.File;
import java.io.FileOutputStream;

import org.ruse.client.Client;
import org.ruse.client.List;
import org.ruse.client.cache.Archive;
import org.ruse.client.graphics.DrawingArea;
import org.ruse.client.graphics.Sprite;
import org.ruse.client.io.ByteBuffer;
import org.ruse.client.world.Model;
import org.ruse.client.world.Rasterizer;

public final class ItemDefinition {

	private static final int[] BLACK_FIX = { 13101, 13672, 13675, 6568, 10636,
			12158, 12159, 12160, 12161, 12162, 12163, 12164, 12165, 12166,
			12167, 12168, 12527, 18017, 18018, 18019, 18020, 3140, 13481,
			14479, 14481, 19337, 19342 };
	private static ByteBuffer buffer;
	private static ItemDefinition[] cache;
	private static int cacheIndex;
	public static boolean isMembers = true;
	public static List mruNodes1 = new List(100);
	public static List mruNodes2 = new List(50);
	private static int[] streamIndices;
	public static int totalItems;

	public static void dumpItemModelsForId(int i) {
		try {
			ItemDefinition d = get(i);

			if (d != null) {
				int[] models = { d.maleWearId, d.femaleWearId, d.modelID, };

				for (int ids : models) {// 13655
					if (ids > 0) {
						try {
							System.out.println("Dumping item model: " + ids);
							byte abyte[] = Client.instance.decompressors[1].decompress(ids);
							File map = new File("./models/" + ids + ".gz");
							FileOutputStream fos = new FileOutputStream(map);
							fos.write(abyte);
							fos.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ItemDefinition get(int id) {
		for (int i = 0; i < 10; i++) {
			if (cache[i].id == id) {
				return cache[i];
			}
		}

		cacheIndex = (cacheIndex + 1) % 10;
		ItemDefinition itemDef = cache[cacheIndex];
		buffer.position = streamIndices[id];
		itemDef.id = id;
		itemDef.setDefaults();
		/*	if (Hardcode.readOSRSItem(itemDef)) {
			if (!itemDef.name.contains("hat") && !itemDef.name.contains("boot") && !itemDef.name.contains("cape")) {
				itemDef.maleWieldY += 8;
				itemDef.femaleWieldY += 8;
			}

			if (itemDef.name.contains("hat")) {
				itemDef.maleWieldZ = 5;
				itemDef.femaleWieldZ = 5;
			}

		} else {
			itemDef.readValues(buffer);
		}*/
		itemDef.readValues(buffer);
		if (itemDef.modifiedModelColors != null) {
			for (int i2 = 0; i2 < itemDef.modifiedModelColors.length; i2++) {
				if (itemDef.originalModelColors[i2] == 0) {
					itemDef.originalModelColors[i2] = 1;
				}
			}
		}

		for (int a : BLACK_FIX) {
			if (itemDef.id == a) {
				itemDef.modifiedModelColors = new int[1];
				itemDef.originalModelColors = new int[1];
				itemDef.modifiedModelColors[0] = 0;
				itemDef.originalModelColors[0] = 1;
			}
		}

		int customId = itemDef.id;

		if (customId >= 13700 && customId <= 13709) {
			/*
			 * final ItemDefinition stat = get(14876); definition.name = "Tier "
			 * + (1 + (customId - 13700)) + " Emblem"; definition.actions =
			 * stat.actions.clone(); //definition.modifiedModelColors =
			 * stat.modifiedModelColors.clone();
			 * //definition.originalModelColors =
			 * stat.originalModelColors.clone(); definition.modelID =
			 * stat.modelID; definition.modelOffset1 = stat.modelOffset1;
			 * definition.modelOffset2 = stat.modelOffset2;
			 * definition.modelRotation1 = stat.modelRotation1;
			 * definition.modelRotation2 = stat.modelRotation2;
			 * definition.groundActions = stat.groundActions; definition.value =
			 * stat.value; definition.modelZoom = stat.modelZoom;
			 * definition.certID = -1; definition.certTemplateID = -1;
			 * definition.stackable = false;
			 */
			itemDef.certID = -1;
			itemDef.certTemplateID = -1;
			itemDef.stackable = false;
		}

		switch (customId) {
		case 12601:
			itemDef.name = "Ring of the gods";
			itemDef.modelZoom = 900;
			itemDef.modelRotation1 = 393;
			itemDef.modelRotation2 = 1589;
			itemDef.modelOffset1 = -9;
			itemDef.modelOffsetY = -12;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.modelID = 33009;
			break;

		case 12603:
			itemDef.name = "Tyrannical ring";
			itemDef.modelZoom = 592;
			itemDef.modelRotation1 = 285;
			itemDef.modelRotation2 = 1163;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.modelID = 28823;
			break;

		case 12605:
			itemDef.name = "Treasonous ring";
			itemDef.modelZoom = 750;
			itemDef.modelRotation1 = 342;
			itemDef.modelRotation2 = 252;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffsetY = -12;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.modelID = 33010;
			break;
		case 12927:
			itemDef.name = "Serpentine visage";
			itemDef.modelZoom = 816;
			itemDef.modelRotation1 = 498;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffsetY = 7;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.modelID = 19218;
			break;
		case 20555:
			itemDef.name = "Dragon warhammer";
			itemDef.modelID = 4041;
			itemDef.modelRotation1 = 1510;
			itemDef.modelRotation2 = 1785;
			itemDef.modelZoom = 1600;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.maleWearId = 4037;
			itemDef.femaleWearId = 4038;
			break;
		case 12922:
			itemDef.name = "Tanzanite fang";
			itemDef.modelZoom = 1411;
			itemDef.modelRotation1 = 202;
			itemDef.modelRotation2 = 1939;
			itemDef.modelOffset1 = -4;
			itemDef.modelOffsetY = -8;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.modelID = 19228;
			break;

		case 12929:
			itemDef.name = "Serpentine helm (uncharged)";
			itemDef.modelZoom = 700;
			itemDef.modelRotation1 = 215;
			itemDef.modelRotation2 = 1724;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffsetY = -1;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.modelID = 19222;
			itemDef.maleWearId = 14396;
			itemDef.femaleWearId = 14397;
			break;

		case 12931:
			itemDef.name = "Serpentine helm";
			itemDef.modelZoom = 700;
			itemDef.modelRotation1 = 215;
			itemDef.modelRotation2 = 1724;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffsetY = 9;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", "Check", null, "Uncharge" };
			itemDef.modelID = 19220;
			itemDef.maleWearId = 14395;
			itemDef.femaleWearId = 14398;
			break;

		case 12932:
			itemDef.name = "Magic fang";
			itemDef.modelZoom = 1095;
			itemDef.modelRotation1 = 579;
			itemDef.modelRotation2 = 1832;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffsetY = 4;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.modelID = 19227;
			break;

		case 12934:
			itemDef.name = "Zulrah's scales";
			itemDef.modelZoom = 1370;
			itemDef.modelRotation1 = 212;
			itemDef.modelRotation2 = 148;
			itemDef.modelOffset1 = 7;
			itemDef.stackable = true;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.modelID = 19212;
			break;
		case 19908:
			itemDef.name = "Trident of the seas (full)";
			itemDef.modelZoom = 2350;
			itemDef.modelRotation1 = 1549;
			itemDef.modelRotation2 = 1818;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", "Check", "Uncharge", "Drop" };
			itemDef.modelID = 28232;
			itemDef.maleWearId = 28230;
			itemDef.femaleWearId = 28230;
			break;

		case 19906:
			itemDef.name = "Trident of the seas";
			itemDef.modelZoom = 2755;
			itemDef.modelRotation1 = 420;
			itemDef.modelRotation2 = 1130;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", "Check", "Uncharge", "Drop" };
			itemDef.modelID = 28232;
			itemDef.maleWearId = 28230;
			itemDef.femaleWearId = 28230;
			break;
		case 12899:
			itemDef.name = "Trident of the swamp";
			itemDef.modelZoom = 2421;
			itemDef.modelRotation1 = 1818;
			itemDef.modelRotation2 = 1549;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 9;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", "Check", "Uncharge", "Drop" };
			itemDef.modelID = 19223;
			itemDef.maleWearId = 19221;
			itemDef.femaleWearId = 19221;
			break;
		case 22007:
			itemDef.name = "Kraken tentacle";
			itemDef.modelZoom = 1095;
			itemDef.modelRotation1 = 593;
			itemDef.modelRotation2 = 741;
			itemDef.modelOffset1 = 4;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.modelID = 28437;
			break;
		case 2970:
			itemDef.name = "Mort myre fungus";
			itemDef.modelZoom = 790;
			itemDef.modelRotation1 = 60;
			itemDef.modelRotation2 = 232;
			itemDef.modelOffset1 = 3;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, null, null, "Drop" };
			itemDef.modelID = 3563;
			break;
		case 13045:
			itemDef.name = "Abyssal bludgeon";
			itemDef.modelZoom = 1400;
			itemDef.modelRotation2 = 1549;
			itemDef.modelRotation1 = 1818;
			itemDef.modelOffsetY = 9;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", "Check", "Uncharge", "Drop" };
			itemDef.modelID = 29597;
			itemDef.maleWearId = 29424;
			itemDef.femaleWearId = 29424;
		break;
		case 13047:
			itemDef.name = "Abyssal dagger";
			itemDef.modelZoom = 1275;
			itemDef.modelRotation2 = 1549;
			itemDef.modelRotation1 = 1818;
			itemDef.modelOffsetY = 9;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", "Check", "Uncharge", "Drop" };
			itemDef.modelID = 29598;
			itemDef.maleWearId = 29425;
			itemDef.femaleWearId = 29425;
		break;
		case 13049:
			itemDef.name = "Abyssal dagger (p)";
			itemDef.modelZoom = 1275;
			itemDef.modelRotation2 = 1549;
			itemDef.modelRotation1 = 1818;
			itemDef.modelOffsetY = 9;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", "Check", "Uncharge", "Drop" };
			itemDef.modelID = 29598;
			itemDef.maleWearId = 29426;
			itemDef.femaleWearId = 29426;
		break;
		case 13051:
			itemDef.name = "Overseer's book";
			itemDef.modelZoom = 1450;
			itemDef.modelRotation2 = 498;
			itemDef.modelRotation1 = 256;
			itemDef.modelOffsetX = 8;
			itemDef.modelOffset1 = 8;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.modelID = 29599;
		break;
		case 22008:
			itemDef.name = "Abyssal tentacle";
			itemDef.modelZoom = 840;
			itemDef.modelRotation1 = 280;
			itemDef.modelRotation2 = 121;
			itemDef.modelOffset1 = 56;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, "Check", "Dissolve" };
			itemDef.modelID = 28439;
			itemDef.maleWearId = 28446;
			itemDef.femaleWearId = 28446;
			break;
		case 12900:
			itemDef.name = "Uncharged toxic trident";
			itemDef.modelZoom = 2421;
			itemDef.modelRotation1 = 1818;
			itemDef.modelRotation2 = 1549;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 9;
			itemDef.stackable = false;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", "Check", "Dismantle", "Drop" };
			itemDef.modelID = 19226;
			itemDef.maleWearId = 14401;
			itemDef.femaleWearId = 14401;
			break;
		case 12902:
			itemDef.name = "Toxic staff (uncharged)";
			itemDef.modelID = 19224;
			itemDef.maleWearId = 14404;
			itemDef.femaleWearId = 14404;
			itemDef.modelZoom = 2150;
			itemDef.modelRotation2 = 512;
			itemDef.modelRotation1 = 1010;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.stackable = false;
			// itemDef.rotationZ = 229;
			itemDef.actions[1] = "Wield";
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.modifiedModelColors = new int [1];
			itemDef.originalModelColors = new int [1];
			break;
		case 12904:
			itemDef.name = "Toxic staff of the dead";
			itemDef.modelID = 19224;
			itemDef.maleWearId = 14402;
			itemDef.femaleWearId = 14402;
			itemDef.modelZoom = 2150;
			itemDef.modelRotation2 = 512;
			itemDef.modelRotation1 = 1010;
			// itemDef.rotationZ = 229;
			itemDef.actions[1] = "Wield";
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			break;
		case 22047:
			itemDef.actions = new String[5];
			itemDef.modelID = 19219;
			itemDef.name = "Toxic blowpipe";
			itemDef.modelZoom = 1158;
			itemDef.modelRotation2 = 768;
			itemDef.modelRotation1 = 189;
			itemDef.modelOffset1 = -7;
			itemDef.modelOffsetY = 4;
			itemDef.value = 200000000;
			itemDef.maleWearId = 14403;
			itemDef.femaleWearId = 14403;
			itemDef.actions[1] = "Wield";
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			break;
		case 12924:
			itemDef.actions = new String[5];
			itemDef.modelID = 19221;
			itemDef.name = "Toxic blowpipe (empty)";
			itemDef.modelZoom = 1158;
			itemDef.modelRotation2 = 768;
			itemDef.modelRotation1 = 189;
			itemDef.modelOffset1 = -7;
			itemDef.modelOffsetY = 4;
			itemDef.value = 200000000;
			itemDef.maleWearId = 14405;
			itemDef.femaleWearId = 14405;
			itemDef.actions[1] = "Wield";
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			break;
			
		case 22012:
			itemDef.name = "Pegasian boots";
			itemDef.modelID = 29396;
			itemDef.modelZoom = 900;
			itemDef.modelRotation2 = 165;
			itemDef.modelRotation1 = 99;
			itemDef.modelOffsetX = 3;
			itemDef.modelOffsetY =-7;
			itemDef.maleWearId = 29252;
			itemDef.femaleWearId = 29253;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			break;

		case 22011:
			itemDef.name = "Primordial Boots";
			itemDef.modelID = 29397;
			itemDef.modelZoom = 900;
			itemDef.modelRotation2 = 165;
			itemDef.modelRotation1 = 99;
			itemDef.modelOffsetX = 3;
			itemDef.modelOffsetY =-7;
			itemDef.maleWearId = 29250;
			itemDef.femaleWearId = 29255;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			break;

		case 22010:
			itemDef.name = "Eternal boots";
			itemDef.modelID = 29394;
			itemDef.modelZoom = 900;
			itemDef.modelRotation2 = 165;
			itemDef.modelRotation1 = 99;
			itemDef.modelOffsetX = 3;
			itemDef.modelOffsetY =-7;
			itemDef.maleWearId = 29249;
			itemDef.femaleWearId = 29254;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			break;
		case 14023:
			itemDef.modelID = 64994;//64994;
			itemDef.name = "Drygore long-sword";
			itemDef.description = "A powerful sword made from the chitlin of the Kalphite King.".getBytes();		
			itemDef.modelZoom = 1493;
			itemDef.modelRotation1 = 618;
			itemDef.modelRotation2 = 1086;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -5;
			itemDef.maleWearId = 64995;
			itemDef.femaleWearId = 64996;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.actions[4] = "Drop";
			break;	
		case 14024:
			itemDef.modelID = 64997;
			itemDef.name = "Drygore rapier";
			itemDef.description = "A powerful rapier made from the chitlin of the Kalphite King.".getBytes();		
			itemDef.modelZoom = 1493;
			itemDef.modelRotation1 = 618;
			itemDef.modelRotation2 = 996;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -5;
			itemDef.maleWearId = 64998;
			itemDef.femaleWearId = 64999;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.actions[4] = "Drop";
			break;
		case 18352:
		case 18354:
		case 18350:
		case 18358:
		case 18356:
			itemDef.name = itemDef.name.replace("(broken)", "(deg)");
			itemDef.actions = new String[] { null, "Wear", "Check-charges", null, "Drop" };
			break;
		case 18351:
		case 18353:
		case 18349:
		case 18357:
		case 18355:
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 19670:
			itemDef.name = "Vote scroll";
			itemDef.actions = new String[5];
			itemDef.actions[4] = "Drop";
			itemDef.actions[0] = "Claim";
			itemDef.actions[2] = "Claim-All";
			break;
		case 10034:
		case 10033:
			itemDef.actions = new String[] { null, null, null, null, "Drop"};
			break;
		case 13727:
			itemDef.actions = new String[] { null, null, null, null, "Drop"};
			break;
		case 6500:
			itemDef.modelID = 9123;
			itemDef.name = "Charming imp";
			itemDef.stackable = false;
			itemDef.actions = new String[] { null, null, "Check", "Config", "Drop"};
			break;
		case 15332:
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 61;
			itemDef.originalModelColors[0] = 0;
			itemDef.modelID = 2789;
			itemDef.modelZoom = 550;
			itemDef.modelRotation1 = 84;
			itemDef.modelRotation2 = 1996;
			break;
		case 11995:
			itemDef.name = "Pet Chaos elemental";
			ItemDefinition itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11996:
			itemDef.name = "Pet King black dragon";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11997:
			itemDef.name = "Pet General graardor";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11978:
			itemDef.name = "Pet TzTok-Jad";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 12001:
			itemDef.name = "Pet Corporeal beast";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 12002:
			itemDef.name = "Pet Kree'arra";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 12003:
			itemDef.name = "Pet K'ril tsutsaroth";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 12004:
			itemDef.name = "Pet Commander zilyana";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 12005:
			itemDef.name = "Pet Dagannoth supreme";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 12006:
			itemDef.name = "Pet Dagannoth prime";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11990:
			itemDef.name = "Pet Dagannoth rex";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11991:
			itemDef.name = "Pet Frost dragon";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11992:
			itemDef.name = "Pet Tormented demon";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11993:
			itemDef.name = "Pet Kalphite queen";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11994:
			itemDef.name = "Pet Slash bash";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11989:
			itemDef.name = "Pet Phoenix";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11988:
			itemDef.name = "Pet Bandos avatar";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11987:
			itemDef.name = "Pet Nex";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11986:
			itemDef.name = "Pet Jungle strykewyrm";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11985:
			itemDef.name = "Pet Desert strykewyrm";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11984:
			itemDef.name = "Pet Ice strykewyrm";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11983:
			itemDef.name = "Pet Green dragon";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11982:
			itemDef.name = "Pet Baby blue dragon";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11981:
			itemDef.name = "Pet Blue dragon";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 11979:
			itemDef.name = "Pet Black dragon";
			itemDef2 = ItemDefinition.get(12458);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, null, "Summon", null, "Drop" };
			break;
		case 14667:
			itemDef.name = "Zombie fragment";
			itemDef.modelID = ItemDefinition.get(14639).modelID;
			break;
		case 15182:
			itemDef.actions[0] = "Bury";
			break;
		case 15084:
			itemDef.actions[0] = "Roll";
			itemDef.name = "Dice (up to 100)";
			itemDef2 = ItemDefinition.get(15098);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			break;
		case 2996:
			itemDef.name = "Agility ticket";
			break;
		case 5510:
		case 5512:
		case 5509:
			itemDef.actions = new String[]{"Fill", null, "Empty", "Check", null, null};
			break;
		case 11998:
			itemDef.name = "Scimitar";
			itemDef.actions = new String[]{null, null, null, null, null, null};
			break;
		case 11999:
			itemDef.name = "Scimitar";
			itemDef.actions = new String[]{null, null, null, null, null, null};
			itemDef.modelZoom = 700;
			itemDef.modelRotation1 = 0;
			itemDef.modelRotation2 = 350;
			itemDef.modelID = 2429;
			itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
			itemDef.stackable = true;
			itemDef.certID = 11998;
			itemDef.certTemplateID = 799;
			break;
		case 1389:
			itemDef.name = "Staff";
			itemDef.actions = new String[]{null, null, null, null, null, null};
			break;
		case 1390:
			itemDef.name = "Staff";
			itemDef.actions = new String[]{null, null, null, null, null, null};
			break;
		case 17401:
			itemDef.name = "Damaged Hammer";
			itemDef.actions = new String[]{null, null, null, null, null, null};
			break;
		case 17402:
			itemDef.name = "Damaged Hammer";
			itemDef.actions = new String[]{null, null, null, null, null, null};
			itemDef.modelZoom = 760;
			itemDef.modelRotation1 = 28;
			itemDef.modelRotation2 = 552;
			itemDef.modelID = 2429;
			itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
			itemDef.stackable = true;
			itemDef.certID = 17401;
			itemDef.certTemplateID = 799;
			break;
		case 15009:
			itemDef.name = "Gold Ring";
			itemDef.actions = new String[]{null, null, null, null, null, null};
			break;
		case 15010:
			itemDef.modelID = 2429;
			itemDef.name = "Gold Ring";
			itemDef.actions = new String[]{null, null, null, null, null, null};
			itemDef.modelZoom = 760;
			itemDef.modelRotation1 = 28;
			itemDef.modelRotation2 = 552;
			itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
			itemDef.stackable = true;
			itemDef.certID = 15009;
			itemDef.certTemplateID = 799;
			break;
		case 11884:
			itemDef.actions = new String[]{"Open", null, null, null, null, null};
			break;
		case 15262:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open";
			itemDef.actions[2] = "Open-All";
			break;
		case 6570:
			itemDef.actions[2] = "Upgrade";
			break;
		case 4155:
			itemDef.name = "Slayer gem";
			itemDef.actions = new String[] {"Activate", null, "Social-Slayer", null, "Destroy"};
			break;
		case 13663:
			itemDef.name = "Stat reset cert.";
			itemDef.actions = new String[5];
			itemDef.actions[4] = "Drop";
			itemDef.actions[0] = "Open";
			break;
		case 13653:
			itemDef.name = "Energy fragment";
			break;
		case 292:
			itemDef.name = "Ingredients book";
			break;
		case 15707:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[0] = "Create Party";
			break;
		case 14501:
			itemDef.modelID = 44574;
			itemDef.maleWearId = 43693;
			itemDef.femaleWearId= 43693;
			break;
		case 19111:
			itemDef.name = "TokHaar-Kal";
			itemDef.maleWearId = 62575;
			itemDef.femaleWearId = 62582;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.modelOffset1 = -4;
			itemDef.modelID = 62592;
			itemDef.description = "A cape made of ancient, enchanted rocks.".getBytes();
			itemDef.modelZoom = 1616;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.modelOffsetX = 0;
			itemDef.modelRotation1 = 339;
			itemDef.modelRotation2 = 192;
			break;
		case 10942:
			itemDef.name = "$10 Scroll";
			itemDef.actions = new String[5];
			itemDef.actions[4] = "Drop";
			itemDef.actions[0] = "Claim";
			itemDef2 = ItemDefinition.get(761);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			break;
		case 10934:
			itemDef.name = "$25 Scroll";
			itemDef.actions = new String[5];
			itemDef.actions[4] = "Drop";
			itemDef.actions[0] = "Claim";
			itemDef2 = ItemDefinition.get(761);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			break;
		case 10935:
			itemDef.name = "$50 Scroll";
			itemDef.actions = new String[5];
			itemDef.actions[4] = "Drop";
			itemDef.actions[0] = "Claim";
			itemDef2 = ItemDefinition.get(761);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			break;
		case 10943:
			itemDef.name = "$100 Scroll";
			itemDef.actions = new String[5];
			itemDef.actions[4] = "Drop";
			itemDef.actions[0] = "Claim";
			itemDef2 = ItemDefinition.get(761);
			itemDef.modelID = itemDef2.modelID;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelZoom = itemDef2.modelZoom;
			break;
		case 995:
			itemDef.name = "Coins";
			itemDef.actions = new String[5];
			itemDef.actions[4] = "Drop";
			itemDef.actions[3] = "Add-to-pouch";
			break;
		case 17291:
			itemDef.name = "Blood necklace";
			itemDef.actions = new String[] {null, "Wear", null, null, null, null};
			break;
		case 20084:
			itemDef.name = "Golden Maul";
			break;
		case 6199:
			itemDef.name = "Mystery Box";
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open";
			break;
		case 15501:
			itemDef.name = "Legendary Mystery Box";
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open";
			break;
		case 6568: // To replace Transparent black with opaque black.
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 0;
			itemDef.originalModelColors[0] = 2059;
			break;
		case 13262:
			itemDef2 = ItemDefinition.get(20072);
			itemDef.modelID = itemDef2.modelID;
			itemDef.maleWearId = itemDef2.maleWearId;
			itemDef.femaleWearId = itemDef2.femaleWearId;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelRotation1 = itemDef2.modelRotation1;
			itemDef.modelRotation2 = itemDef2.modelRotation2;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.name = itemDef2.name;
			itemDef.actions = itemDef2.actions;
			break;
		case 996:
		case 997:
		case 998:
		case 999:
		case 1000:
		case 1001:
		case 1002:
		case 1003:
		case 1004:
			itemDef.name = "Coins";
			break;
		case 14017:
			itemDef.name = "Brackish blade";
			itemDef.modelZoom = 1488;
			itemDef.modelRotation2 = 276;
			itemDef.modelRotation1 = 1580;
			itemDef.modelRotation2 = 1;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.modelID = 64593;
			itemDef.maleWearId = 64704;
			itemDef.femaleWearId = 64704;
			break;
		case 15220:
			itemDef.name = "Berserker ring (i)";
			itemDef.modelZoom = 600;
			itemDef.modelRotation1 = 324;
			itemDef.modelRotation2 = 1916;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -15;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.modelID = 7735;
			itemDef.maleWearId = -1;
			itemDef.femaleWearId = -1;
			break;
		case 14019:
			itemDef.modelID = 65262;
			itemDef.name = "Max Cape";
			itemDef.description = "A cape worn by those who've achieved 99 in all skills.".getBytes();
			itemDef.modelZoom = 1385;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 24;
			itemDef.modelRotation1 = 279;
			itemDef.modelRotation2 = 948;
			itemDef.maleWearId = 65300;
			itemDef.femaleWearId = 65322;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			break;
		case 14020:
			itemDef.name = "Veteran hood";
			itemDef.description = "A hood worn by Chivalry's veterans.".getBytes();
			itemDef.modelZoom = 760;
			itemDef.modelRotation1 = 11;
			itemDef.modelRotation2 = 81;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffsetY = -3;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.modelID = 65271;
			itemDef.maleWearId = 65289;
			itemDef.femaleWearId = 65314;
			break;
		case 14021:
			itemDef.modelID = 65261;
			itemDef.name = "Veteran Cape";
			itemDef.description = "A cape worn by Chivalry's veterans.".getBytes();
			itemDef.modelZoom = 760;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 24;
			itemDef.modelRotation1 = 279;
			itemDef.modelRotation2 = 948;
			itemDef.maleWearId = 65305;
			itemDef.femaleWearId = 65318;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			break;
		case 14022:
			itemDef.modelID = 65270;
			itemDef.name = "Completionist Cape";
			itemDef.description = "We'd pat you on the back, but this cape would get in the way.".getBytes();
			itemDef.modelZoom = 1385;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 24;
			itemDef.modelRotation1 = 279;
			itemDef.modelRotation2 = 948;
			itemDef.modifiedModelColors = new int[]{65214, 65200, 65186, 62995};
			itemDef.originalModelColors = new int[]{44988, 44988, 32463, 44988};
			itemDef.maleWearId = 65297;
			itemDef.femaleWearId = 65297;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			break;
		case 14004:
			itemDef.name = "Staff of light";
			itemDef.modelID = 51845;
			itemDef.modifiedModelColors = new int [11];
			itemDef.originalModelColors = new int [11];
			itemDef.modifiedModelColors[0] = 7860;
			itemDef.originalModelColors[0] = 38310;
			itemDef.modifiedModelColors[1] = 7876;
			itemDef.originalModelColors[1] = 38310;
			itemDef.modifiedModelColors[2] = 7892;
			itemDef.originalModelColors[2] = 38310;
			itemDef.modifiedModelColors[3] = 7884;
			itemDef.originalModelColors[3] = 38310;
			itemDef.modifiedModelColors[4] = 7868;
			itemDef.originalModelColors[4] = 38310;
			itemDef.modifiedModelColors[5] = 7864;
			itemDef.originalModelColors[5] = 38310;
			itemDef.modifiedModelColors[6] = 7880;
			itemDef.originalModelColors[6] = 38310;
			itemDef.modifiedModelColors[7] = 7848;
			itemDef.originalModelColors[7] = 38310;
			itemDef.modifiedModelColors[8] = 7888;
			itemDef.originalModelColors[8] = 38310;
			itemDef.modifiedModelColors[9] = 7872;
			itemDef.originalModelColors[9] = 38310;
			itemDef.modifiedModelColors[10] = 7856;
			itemDef.originalModelColors[10] = 38310;
			itemDef.modelZoom = 2256;
			itemDef.modelRotation2 = 456;
			itemDef.modelRotation1 = 513;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.maleWearId = 51795;
			itemDef.femaleWearId = 51795;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;

		case 14005:
			itemDef.name = "Staff of light";
			itemDef.modelID = 51845;
			itemDef.modifiedModelColors = new int [11];
			itemDef.originalModelColors = new int [11];
			itemDef.modifiedModelColors[0] = 7860;
			itemDef.originalModelColors[0] = 432;
			itemDef.modifiedModelColors[1] = 7876;
			itemDef.originalModelColors[1] = 432;
			itemDef.modifiedModelColors[2] = 7892;
			itemDef.originalModelColors[2] = 432;
			itemDef.modifiedModelColors[3] = 7884;
			itemDef.originalModelColors[3] = 432;
			itemDef.modifiedModelColors[4] = 7868;
			itemDef.originalModelColors[4] = 432;
			itemDef.modifiedModelColors[5] = 7864;
			itemDef.originalModelColors[5] = 432;
			itemDef.modifiedModelColors[6] = 7880;
			itemDef.originalModelColors[6] = 432;
			itemDef.modifiedModelColors[7] = 7848;
			itemDef.originalModelColors[7] = 432;
			itemDef.modifiedModelColors[8] = 7888;
			itemDef.originalModelColors[8] = 432;
			itemDef.modifiedModelColors[9] = 7872;
			itemDef.originalModelColors[9] = 432;
			itemDef.modifiedModelColors[10] = 7856;
			itemDef.originalModelColors[10] = 432;
			itemDef.modelZoom = 2256;
			itemDef.modelRotation2 = 456;
			itemDef.modelRotation1 = 513;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.maleWearId = 51795;
			itemDef.femaleWearId = 51795;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;

		case 14006:
			itemDef.name = "Staff of light";
			itemDef.modelID = 51845;
			itemDef.modifiedModelColors = new int [11];
			itemDef.originalModelColors = new int [11];
			itemDef.modifiedModelColors[0] = 7860;
			itemDef.originalModelColors[0] = 24006;
			itemDef.modifiedModelColors[1] = 7876;
			itemDef.originalModelColors[1] = 24006;
			itemDef.modifiedModelColors[2] = 7892;
			itemDef.originalModelColors[2] = 24006;
			itemDef.modifiedModelColors[3] = 7884;
			itemDef.originalModelColors[3] = 24006;
			itemDef.modifiedModelColors[4] = 7868;
			itemDef.originalModelColors[4] = 24006;
			itemDef.modifiedModelColors[5] = 7864;
			itemDef.originalModelColors[5] = 24006;
			itemDef.modifiedModelColors[6] = 7880;
			itemDef.originalModelColors[6] = 24006;
			itemDef.modifiedModelColors[7] = 7848;
			itemDef.originalModelColors[7] = 24006;
			itemDef.modifiedModelColors[8] = 7888;
			itemDef.originalModelColors[8] = 24006;
			itemDef.modifiedModelColors[9] = 7872;
			itemDef.originalModelColors[9] = 24006;
			itemDef.modifiedModelColors[10] = 7856;
			itemDef.originalModelColors[10] = 24006;
			itemDef.modelZoom = 2256;
			itemDef.modelRotation2 = 456;
			itemDef.modelRotation1 = 513;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.maleWearId = 51795;
			itemDef.femaleWearId = 51795;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14007:
			itemDef.name = "Staff of light";
			itemDef.modelID = 51845;
			itemDef.modifiedModelColors = new int [11];
			itemDef.originalModelColors = new int [11];
			itemDef.modifiedModelColors[0] = 7860;
			itemDef.originalModelColors[0] = 14285;
			itemDef.modifiedModelColors[1] = 7876;
			itemDef.originalModelColors[1] = 14285;
			itemDef.modifiedModelColors[2] = 7892;
			itemDef.originalModelColors[2] = 14285;
			itemDef.modifiedModelColors[3] = 7884;
			itemDef.originalModelColors[3] = 14285;
			itemDef.modifiedModelColors[4] = 7868;
			itemDef.originalModelColors[4] = 14285;
			itemDef.modifiedModelColors[5] = 7864;
			itemDef.originalModelColors[5] = 14285;
			itemDef.modifiedModelColors[6] = 7880;
			itemDef.originalModelColors[6] = 14285;
			itemDef.modifiedModelColors[7] = 7848;
			itemDef.originalModelColors[7] = 14285;
			itemDef.modifiedModelColors[8] = 7888;
			itemDef.originalModelColors[8] = 14285;
			itemDef.modifiedModelColors[9] = 7872;
			itemDef.originalModelColors[9] = 14285;
			itemDef.modifiedModelColors[10] = 7856;
			itemDef.originalModelColors[10] = 14285;
			itemDef.modelZoom = 2256;
			itemDef.modelRotation2 = 456;
			itemDef.modelRotation1 = 513;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.maleWearId = 51795;
			itemDef.femaleWearId = 51795;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;
		case 14003:
			itemDef.name = "Robin hood hat";
			itemDef.modelID = 3021;
			itemDef.modifiedModelColors = new int [3];
			itemDef.originalModelColors = new int [3];
			itemDef.modifiedModelColors[0] = 15009;
			itemDef.originalModelColors[0] = 30847;
			itemDef.modifiedModelColors[1] = 17294;
			itemDef.originalModelColors[1] = 32895;
			itemDef.modifiedModelColors[2] = 15252;
			itemDef.originalModelColors[2] = 30847;
			itemDef.modelZoom = 650;
			itemDef.modelRotation1 = 2044;
			itemDef.modelRotation2 = 256;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffsetY = -5;
			itemDef.maleWearId = 3378;
			itemDef.femaleWearId = 3382;
			itemDef.maleDialogue = 3378;
			itemDef.femaleDialogue = 3382;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;

		case 14001:
			itemDef.name = "Robin hood hat";
			itemDef.modelID = 3021;
			itemDef.modifiedModelColors = new int [3];
			itemDef.originalModelColors = new int [3];
			itemDef.modifiedModelColors[0] = 15009;
			itemDef.originalModelColors[0] = 10015;
			itemDef.modifiedModelColors[1] = 17294;
			itemDef.originalModelColors[1] = 7730;
			itemDef.modifiedModelColors[2] = 15252;
			itemDef.originalModelColors[2] = 7973;
			itemDef.modelZoom = 650;
			itemDef.modelRotation1 = 2044;
			itemDef.modelRotation2 = 256;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffsetY = -5;
			itemDef.maleWearId = 3378;
			itemDef.femaleWearId = 3382;
			itemDef.maleDialogue = 3378;
			itemDef.femaleDialogue = 3382;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;

		case 14002:
			itemDef.name = "Robin hood hat";
			itemDef.modelID = 3021;
			itemDef.modifiedModelColors = new int [3];
			itemDef.originalModelColors = new int [3];
			itemDef.modifiedModelColors[0] = 15009;
			itemDef.originalModelColors[0] = 35489;
			itemDef.modifiedModelColors[1] = 17294;
			itemDef.originalModelColors[1] = 37774;
			itemDef.modifiedModelColors[2] = 15252;
			itemDef.originalModelColors[2] = 35732;
			itemDef.modelZoom = 650;
			itemDef.modelRotation1 = 2044;
			itemDef.modelRotation2 = 256;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffsetY = -5;
			itemDef.maleWearId = 3378;
			itemDef.femaleWearId = 3382;
			itemDef.maleDialogue = 3378;
			itemDef.femaleDialogue = 3382;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			break;

		case 14000:
			itemDef.name = "Robin hood hat";
			itemDef.modelID = 3021;
			itemDef.modifiedModelColors = new int [3];
			itemDef.originalModelColors = new int [3];
			itemDef.modifiedModelColors[0] = 15009;
			itemDef.originalModelColors[0] = 3745;
			itemDef.modifiedModelColors[1] = 17294;
			itemDef.originalModelColors[1] = 3982;
			itemDef.modifiedModelColors[2] = 15252;
			itemDef.originalModelColors[2] = 3988;
			itemDef.modelZoom = 650;
			itemDef.modelRotation1 = 2044;
			itemDef.modelRotation2 = 256;
			itemDef.modelOffsetX = 1;
			itemDef.modelOffsetY = -5;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.maleWearId = 3378;
			itemDef.femaleWearId = 3382;
			itemDef.maleDialogue = 3378;
			itemDef.femaleDialogue = 3382;
			break;
		case 20000:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.modelID = 53835;
			itemDef.name = "Steadfast boots";
			itemDef.modelZoom = 900;
			itemDef.modelRotation1 = 165;
			itemDef.modelRotation2 = 99;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -7;
			itemDef.maleWearId = 53327;
			itemDef.femaleWearId = 53643;
			itemDef.description = "A pair of Steadfast boots.".getBytes();
			break;

		case 20001:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.modelID = 53828;
			itemDef.name = "Glaiven boots";
			itemDef.modelZoom = 900;
			itemDef.modelRotation1 = 165;
			itemDef.modelRotation2 = 99;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -7;
			itemDef.femaleWearId = 53309;
			itemDef.maleWearId = 53309;
			itemDef.description = "A pair of Glaiven boots.".getBytes();
			break;

		case 20002:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.description = "A pair of Ragefire boots.".getBytes();
			itemDef.modelID = 53897;
			itemDef.name = "Ragefire boots";
			itemDef.modelZoom = 900;
			itemDef.modelRotation1 = 165;
			itemDef.modelRotation2 = 99;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffsetY = -7;
			itemDef.maleWearId = 53330;
			itemDef.femaleWearId = 53651;
			break;
		case 14018:
			itemDef.modelID = 6277;
			itemDef.name = "Ornate katana";
			itemDef.modelZoom = 2025;
			itemDef.modelRotation1 = 593;
			itemDef.modelRotation2 = 2040;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffsetY = 1;
			itemDef.value = 50000;
			itemDef.membersObject = true;
			itemDef.maleWearId = 5324;
			itemDef.femaleWearId = 5324;
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.actions[4] = "Destroy";
			break;
		case 14008:
			itemDef.modelID = 62714;
			itemDef.name = "Torva full helm";
			itemDef.modelZoom = 672;
			itemDef.modelRotation1 = 85;
			itemDef.modelRotation2 = 1867;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -3;
			itemDef.maleWearId = 62738;
			itemDef.femaleWearId = 62754;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			//itemDef.actions[2] = "Check-charges";
			itemDef.actions[4] = "Drop";
			itemDef.maleDialogue = 62729;
			itemDef.femaleDialogue = 62729;
			break;
		case 14009:
			itemDef.modelID = 62699;
			itemDef.name = "Torva platebody";
			itemDef.modelZoom = 1506;
			itemDef.modelRotation1 = 473;
			itemDef.modelRotation2 = 2042;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = 0;
			itemDef.maleWearId = 62746;
			itemDef.femaleWearId = 62762;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			//itemDef.actions[2] = "Check-charges";
			itemDef.actions[4] = "Drop";
			break;

		case 14010:
			itemDef.modelID = 62701;
			itemDef.name = "Torva platelegs";
			itemDef.modelZoom = 1740;
			itemDef.modelRotation1 = 474;
			itemDef.modelRotation2 = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -5;
			itemDef.maleWearId = 62743;
			itemDef.femaleWearId = 62760;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			//itemDef.actions[2] = "Check-charges";
			itemDef.actions[4] = "Drop";
			break;

		case 14011:
			itemDef.modelID = 62693;
			itemDef.name = "Pernix cowl";
			itemDef.modelZoom = 800;
			itemDef.modelRotation1 = 532;
			itemDef.modelRotation2 = 14;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffsetY = 1;
			itemDef.maleWearId = 62739;
			itemDef.femaleWearId = 62756;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			//itemDef.actions[2] = "Check-charges";
			itemDef.actions[4] = "Drop";
			itemDef.maleDialogue = 62731;
			itemDef.femaleDialogue = 62727;
			itemDef.modifiedModelColors = new int[2];
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 4550;
			itemDef.originalModelColors[0] = 0;
			itemDef.modifiedModelColors[1] = 4540;
			itemDef.originalModelColors[1] = 0;
			break;

		case 14012:
			itemDef.modelID = 62709;
			itemDef.name = "Pernix body";
			itemDef.modelZoom = 1378;
			itemDef.modelRotation1 = 485;
			itemDef.modelRotation2 = 2042;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffsetY = 7;
			itemDef.maleWearId = 62744;
			itemDef.femaleWearId = 62765;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			//itemDef.actions[2] = "Check-charges";
			itemDef.actions[4] = "Drop";
			break;

		case 14013:
			itemDef.modelID = 62695;
			itemDef.name = "Pernix chaps";
			itemDef.modelZoom = 1740;
			itemDef.modelRotation1 = 504;
			itemDef.modelRotation2 = 0;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffsetY = 3;
			itemDef.maleWearId = 62741;
			itemDef.femaleWearId = 62757;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			//itemDef.actions[2] = "Check-charges";
			itemDef.actions[4] = "Drop";
			break;
		case 14014:
			itemDef.modelID = 62710;
			itemDef.name = "Virtus mask";
			itemDef.modelZoom = 928;
			itemDef.modelRotation1 = 406;
			itemDef.modelRotation2 = 2041;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffsetY = -5;
			itemDef.maleWearId = 62736;
			itemDef.femaleWearId = 62755;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			//itemDef.actions[2] = "Check-charges";
			itemDef.actions[4] = "Drop";
			itemDef.maleDialogue = 62728;
			itemDef.femaleDialogue = 62728;
			break;

		case 14015:
			itemDef.modelID = 62704;
			itemDef.name = "Virtus robe top";
			itemDef.modelZoom = 1122;
			itemDef.modelRotation1 = 488;
			itemDef.modelRotation2 = 3;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffsetY = 0;
			itemDef.maleWearId = 62748;
			itemDef.femaleWearId = 62764;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			//itemDef.actions[2] = "Check-charges";
			itemDef.actions[4] = "Drop";
			break;

		case 14016:
			itemDef.modelID = 62700;
			itemDef.name = "Virtus robe legs";
			itemDef.modelZoom = 1740;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 2045;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffsetY = 4;
			itemDef.maleWearId = 62742;
			itemDef.femaleWearId = 62758;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			//itemDef.actions[2] = "Check-charges";
			itemDef.actions[4] = "Drop";
			break;
		case 9666:
		case 11814:
		case 11816:
		case 11818:
		case 11820:
		case 11822:
		case 11824:
		case 11826:
		case 11828:
		case 11830:
		case 11832:
		case 11834:
		case 11836:
		case 11838:
		case 11840:
		case 11842:
		case 11844:
		case 11846:
		case 11848:
		case 11850:
		case 11852:
		case 11854:
		case 11856:
		case 11858:
		case 11860:
		case 11862:
		case 11864:
		case 11866:
		case 11868:
		case 11870:
		case 11874:
		case 11876:
		case 11878:
		case 11882:
		case 11886:
		case 11890:
		case 11894:
		case 11898:
		case 11902:
		case 11904:
		case 11906:
		case 11926:
		case 11928:
		case 11930:
		case 11938:
		case 11942:
		case 11944:
		case 11946:
		case 14525:
		case 14527:
		case 14529:
		case 14531:
		case 19588:
		case 19592:
		case 19596:
		case 11908:
		case 11910:
		case 11912:
		case 11914:
		case 11916:
		case 11618:
		case 11920:
		case 11922:
		case 11924:
		case 11960:
		case 11962:
		case 11967:
		case 19586:
		case 19584:
		case 19590:
		case 19594:
		case 19598:
			itemDef.actions = new String[5];
			itemDef.actions[0] = "Open";
			break;
		case 7980:
			itemDef.name = "Prince Black Dragon";
			itemDef.description = "A miniature King Black Dragon!".getBytes();
			itemDef.actions = new String[5];
			break;
		case 8141:
			itemDef.name = "Baby Dagannoth Prime";
			itemDef.description = "A miniature Dagannoth Prime.".getBytes();
			break;
		case 8142:
			itemDef.name = "Baby Dagannoth Rex";
			itemDef.description = "A miniature Dagannoth Rex.".getBytes();

			ItemDefinition def2 = ItemDefinition.get(8141);
			itemDef.modelID = def2.modelID;
			itemDef.modelZoom = def2.modelZoom;
			itemDef.modelRotation1 = def2.modelRotation1;
			itemDef.modelRotation2 = def2.modelRotation2;
			itemDef.modelOffset1 = def2.modelOffset1;
			itemDef.modelOffsetY = def2.modelOffsetY;
			break;
		case 8143:
			itemDef.name = "Baby Dagannoth Supreme";
			itemDef.description = "A miniature Dagannoth Supreme.".getBytes();

			ItemDefinition def3 = ItemDefinition.get(8141);
			itemDef.modelID = def3.modelID;
			itemDef.modelZoom = def3.modelZoom;
			itemDef.modelRotation1 = def3.modelRotation1;
			itemDef.modelRotation2 = def3.modelRotation2;
			itemDef.modelOffset1 = def3.modelOffset1;
			itemDef.modelOffsetY = def3.modelOffsetY;
			break;
		case 8144:
			// 11216
			itemDef.name = "Baby Chaos Elemental";
			itemDef.description = "A miniature Chaos Elemental.".getBytes();

			ItemDefinition def4 = ItemDefinition.get(8141);
			itemDef.modelID = 11216;
			itemDef.modelZoom = def4.modelZoom;
			itemDef.modelRotation1 = def4.modelRotation1;
			itemDef.modelRotation2 = def4.modelRotation2;
			itemDef.modelOffset1 = def4.modelOffset1;
			itemDef.modelOffsetY = def4.modelOffsetY;
			break;
			// 28057
		case 8145:
			// 11216
			itemDef.name = "Baby Commander Zilyana";
			itemDef.description = "A miniature Commander Zilyana.".getBytes();

			ItemDefinition def5 = ItemDefinition.get(8141);
			itemDef.modelID = 28078;
			itemDef.modelZoom = 2000;
			//System.out.println("" + def5.modelZoom);
			itemDef.modelRotation1 = def5.modelRotation1;
			itemDef.modelRotation2 = def5.modelRotation2;
			itemDef.modelOffset1 = 7;
			itemDef.modelOffsetY = 7;
			break;
		case 8146:
			// 11216
			itemDef.name = "Baby Penance Queen";
			itemDef.description = "A miniature Penance Queen.".getBytes();

			ItemDefinition def6 = ItemDefinition.get(8141);
			itemDef.modelID = 20715;
			itemDef.modelZoom = def6.modelZoom;
			itemDef.modelRotation1 = def6.modelRotation1;
			itemDef.modelRotation2 = def6.modelRotation2;
			itemDef.modelOffset1 = def6.modelOffset1;
			itemDef.modelOffsetY = def6.modelOffsetY;
			break;
		case 8147:
			// 11216
			itemDef.name = "Baby General Graardor";
			itemDef.description = "A miniature General Graardor.".getBytes();

			ItemDefinition def7 = ItemDefinition.get(8141);
			itemDef.modelID = 27785;
			itemDef.modelZoom = def7.modelZoom;
			itemDef.modelRotation1 = def7.modelRotation1;
			itemDef.modelRotation2 = def7.modelRotation2;
			itemDef.modelOffset1 = def7.modelOffset1;
			itemDef.modelOffsetY = def7.modelOffsetY;
			break;
		case 8148:
			// 11216
			itemDef.name = "Baby Kree'arra";
			itemDef.description = "A miniature Kree'arra.".getBytes();

			ItemDefinition def8 = ItemDefinition.get(8141);
			itemDef.modelID = 28004;
			itemDef.modelZoom = def8.modelZoom;
			itemDef.modelRotation1 = def8.modelRotation1;
			itemDef.modelRotation2 = def8.modelRotation2;
			itemDef.modelOffset1 = def8.modelOffset1;
			itemDef.modelOffsetY = def8.modelOffsetY;
			break;
		case 8149:
			// 11216
			itemDef.name = "Baby Giant Mole";
			itemDef.description = "A miniature Giant Mole.".getBytes();

			ItemDefinition def9 = ItemDefinition.get(8141);
			itemDef.modelID = 12076;
			itemDef.modelZoom = 3500;
			itemDef.modelRotation1 = def9.modelRotation1;
			itemDef.modelRotation2 = def9.modelRotation2;
			itemDef.modelOffset1 = def9.modelOffset1;
			itemDef.modelOffsetY = def9.modelOffsetY;
			break;

		case 10284:
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 933;
			itemDef.originalModelColors[0] = 6;
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.modelRotation1 = 72;
			itemDef.modelRotation2 = 136;
			itemDef.modelOffsetX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffsetY = -3;
			itemDef.maleWearId = 189;
			itemDef.anInt188 = -1;
			itemDef.femaleWearId = 366;
			itemDef.anInt164 = -1;
			itemDef.maleDialogue = 69;
			itemDef.femaleDialogue = 127;
			itemDef.stackable = false;
			itemDef.name = "Black santa hat";
			itemDef.description = "Black santa hat.".getBytes();
			break;
		}
		/*if (customId == 13653) {
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.modelID = 3288;
			itemDef.modelZoom = 2000;
			itemDef.modelRotation1 = 500;
			itemDef.modelRotation2 = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = 1;
			itemDef.modelOffsetX = 14;
			itemDef.maleWearId = 3287;
			itemDef.femaleWearId = 3287;
			itemDef.maleDialogue = -1;
			itemDef.femaleDialogue = -1;
			itemDef.stackable = false;
			// definition.anInt199 = -1; TODO, unrename this!
			itemDef.name = "Death cape";
			itemDef.description = "Death cape.".getBytes();
		}*/
		if (customId == 13655) {
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wield";
			itemDef.name = "Dragon kiteshield";
			itemDef.description = "A rare, protective kiteshield.".getBytes();
			itemDef.modelID = 13701;
			itemDef.modelZoom = 1560;
			itemDef.modelRotation1 = 344;
			itemDef.modelRotation2 = 1104;
			itemDef.modelOffsetX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffsetY = -14;
			itemDef.maleWearId = 13700;
			itemDef.femaleWearId = 13700;
			itemDef.anInt188 = -1;
			itemDef.anInt164 = -1;
			itemDef.maleDialogue = -1;
			itemDef.femaleDialogue = -1;
		}

		if (itemDef.certTemplateID != -1) {
			itemDef.toNote();
		}

		if (itemDef.lendTemplateID != -1) {
			itemDef.toLend();
		}

		if (!isMembers && itemDef.membersObject) {
			itemDef.name = "Members Object";
			itemDef.description = "Login to a members' server to use this object.".getBytes();
			itemDef.groundActions = null;
			itemDef.actions = null;
			itemDef.team = 0;
		}

		switch (itemDef.id) {

		case 20147:
			itemDef.modifiedModelColors = new int[2];
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 4550;
			itemDef.originalModelColors[0] = 1;
			itemDef.modifiedModelColors[1] = 4540;
			itemDef.originalModelColors[1] = 1;
			break;
		}

		return itemDef;
	}

	public static Sprite getSprite(int i, int j, int k) {
		if (k == 0) {
			Sprite sprite = (Sprite) mruNodes1.insertFromCache(i);

			if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
				sprite.unlink();
				sprite = null;
			}

			if (sprite != null) {
				return sprite;
			}
		}

		if(i > ItemDefinition.totalItems) {
			return null;
		}
		ItemDefinition definition = get(i);

		if (definition.stackIDs == null) {
			j = -1;
		}

		if (j > 1) {
			int i1 = -1;

			for (int j1 = 0; j1 < 10; j1++) {
				if (j >= definition.stackAmounts[j1] && definition.stackAmounts[j1] != 0) {
					i1 = definition.stackIDs[j1];
				}
			}

			if (i1 != -1) {
				definition = get(i1);
			}
		}

		Model model = definition.getInventoryModel(1);

		if (model == null) {
			return null;
		}

		Sprite sprite = null;

		if (definition.certTemplateID != -1) {
			sprite = getSprite(definition.certID, 10, -1);

			if (sprite == null) {
				return null;
			}
		}

		if (definition.lendTemplateID != -1) {
			sprite = getSprite(definition.lendID, 50, 0);

			if (sprite == null) {
				return null;
			}
		}

		Sprite sprite2 = new Sprite(32, 32);
		int k1 = Rasterizer.centerX;
		int l1 = Rasterizer.centerY;
		int ai[] = Rasterizer.lineOffsets;
		int ai1[] = DrawingArea.pixels;
		int i2 = DrawingArea.width;
		int j2 = DrawingArea.height;
		int k2 = DrawingArea.topX;
		int l2 = DrawingArea.bottomX;
		int i3 = DrawingArea.topY;
		int j3 = DrawingArea.bottomY;
		Rasterizer.notTextured = false;
		DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
		DrawingArea.drawPixels(32, 0, 0, 0, 32);
		Rasterizer.method364();
		int k3 = definition.modelZoom;

		if (k == -1) {
			k3 = (int) (k3 * 1.5D);
		}

		if (k > 0) {
			k3 = (int) (k3 * 1.04D);
		}

		int l3 = Rasterizer.SINE[definition.modelRotation1] * k3 >> 16;
		int i4 = Rasterizer.COSINE[definition.modelRotation1] * k3 >> 16;
		model.renderSingle(definition.modelRotation2, definition.modelOffsetX, definition.modelRotation1, definition.modelOffset1, l3 + model.modelHeight / 2 + definition.modelOffsetY, i4 + definition.modelOffsetY);

		for (int i5 = 31; i5 >= 0; i5--) {
			for (int j4 = 31; j4 >= 0; j4--) {
				if (sprite2.myPixels[i5 + j4 * 32] == 0) {
					if (i5 > 0 && sprite2.myPixels[i5 - 1 + j4 * 32] > 1) {
						sprite2.myPixels[i5 + j4 * 32] = 1;
					} else if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1) {
						sprite2.myPixels[i5 + j4 * 32] = 1;
					} else if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1) {
						sprite2.myPixels[i5 + j4 * 32] = 1;
					} else if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1) {
						sprite2.myPixels[i5 + j4 * 32] = 1;
					}
				}
			}
		}

		if (k > 0) {
			for (int j5 = 31; j5 >= 0; j5--) {
				for (int k4 = 31; k4 >= 0; k4--) {
					if (sprite2.myPixels[j5 + k4 * 32] == 0) {
						if (j5 > 0 && sprite2.myPixels[j5 - 1 + k4 * 32] == 1) {
							sprite2.myPixels[j5 + k4 * 32] = k;
						} else if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1) {
							sprite2.myPixels[j5 + k4 * 32] = k;
						} else if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1) {
							sprite2.myPixels[j5 + k4 * 32] = k;
						} else if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1) {
							sprite2.myPixels[j5 + k4 * 32] = k;
						}
					}
				}
			}
		} else if (k == 0) {
			for (int k5 = 31; k5 >= 0; k5--) {
				for (int l4 = 31; l4 >= 0; l4--) {
					if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && sprite2.myPixels[k5 - 1 + (l4 - 1) * 32] > 0) {
						sprite2.myPixels[k5 + l4 * 32] = 0x302020;
					}
				}
			}
		}

		if (definition.certTemplateID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}

		if (definition.lendTemplateID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}

		if (k == 0) {
			mruNodes1.removeFromCache(sprite2, i);
		}

		DrawingArea.initDrawingArea(j2, i2, ai1);
		DrawingArea.setBounds(k2, i3, l2, j3);
		Rasterizer.centerX = k1;
		Rasterizer.centerY = l1;
		Rasterizer.lineOffsets = ai;
		Rasterizer.notTextured = true;

		if (definition.stackable) {
			sprite2.maxWidth = 33;
		} else {
			sprite2.maxWidth = 32;
		}

		sprite2.maxHeight = j;
		return sprite2;
	}

	public static Sprite getSprite(int i, int j, int k, int zoom) {
		if (k == 0 && zoom != -1) {
			Sprite sprite = (Sprite) mruNodes1.insertFromCache(i);

			if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
				sprite.unlink();
				sprite = null;
			}

			if (sprite != null) {
				return sprite;
			}
		}

		ItemDefinition definition = get(i);

		if (definition.stackIDs == null) {
			j = -1;
		}

		if (j > 1) {
			int i1 = -1;

			for (int j1 = 0; j1 < 10; j1++) {
				if (j >= definition.stackAmounts[j1] && definition.stackAmounts[j1] != 0) {
					i1 = definition.stackIDs[j1];
				}
			}

			if (i1 != -1) {
				definition = get(i1);
			}
		}

		Model model = definition.getInventoryModel(1);

		if (model == null) {
			return null;
		}

		Sprite sprite = null;

		if (definition.certTemplateID != -1) {
			sprite = getSprite(definition.certID, 10, -1);

			if (sprite == null) {
				return null;
			}
		}

		if (definition.lendTemplateID != -1) {
			sprite = getSprite(definition.lendID, 50, 0);

			if (sprite == null) {
				return null;
			}
		}

		Sprite sprite2 = new Sprite(32, 32);
		int k1 = Rasterizer.centerX;
		int l1 = Rasterizer.centerY;
		int ai[] = Rasterizer.lineOffsets;
		int ai1[] = DrawingArea.pixels;
		int i2 = DrawingArea.width;
		int j2 = DrawingArea.height;
		int k2 = DrawingArea.topX;
		int l2 = DrawingArea.bottomX;
		int i3 = DrawingArea.topY;
		int j3 = DrawingArea.bottomY;
		Rasterizer.notTextured = false;
		DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
		DrawingArea.drawPixels(32, 0, 0, 0, 32);
		Rasterizer.method364();
		int k3 = definition.modelZoom;
		if (zoom != -1 && zoom != 0)
			k3 = (definition.modelZoom * 100) / zoom;
		if (k == -1) {
			k3 = (int) (k3 * 1.5D);
		}

		if (k > 0) {
			k3 = (int) (k3 * 1.04D);
		}

		int l3 = Rasterizer.SINE[definition.modelRotation1] * k3 >> 16;
		int i4 = Rasterizer.COSINE[definition.modelRotation1] * k3 >> 16;
		model.renderSingle(definition.modelRotation2, definition.modelOffsetX, definition.modelRotation1, definition.modelOffset1, l3 + model.modelHeight / 2 + definition.modelOffsetY, i4 + definition.modelOffsetY);

		for (int i5 = 31; i5 >= 0; i5--) {
			for (int j4 = 31; j4 >= 0; j4--) {
				if (sprite2.myPixels[i5 + j4 * 32] == 0) {
					if (i5 > 0 && sprite2.myPixels[i5 - 1 + j4 * 32] > 1) {
						sprite2.myPixels[i5 + j4 * 32] = 1;
					} else if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1) {
						sprite2.myPixels[i5 + j4 * 32] = 1;
					} else if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1) {
						sprite2.myPixels[i5 + j4 * 32] = 1;
					} else if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1) {
						sprite2.myPixels[i5 + j4 * 32] = 1;
					}
				}
			}
		}

		if (k > 0) {
			for (int j5 = 31; j5 >= 0; j5--) {
				for (int k4 = 31; k4 >= 0; k4--) {
					if (sprite2.myPixels[j5 + k4 * 32] == 0) {
						if (j5 > 0 && sprite2.myPixels[j5 - 1 + k4 * 32] == 1) {
							sprite2.myPixels[j5 + k4 * 32] = k;
						} else if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1) {
							sprite2.myPixels[j5 + k4 * 32] = k;
						} else if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1) {
							sprite2.myPixels[j5 + k4 * 32] = k;
						} else if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1) {
							sprite2.myPixels[j5 + k4 * 32] = k;
						}
					}
				}
			}
		} else if (k == 0) {
			for (int k5 = 31; k5 >= 0; k5--) {
				for (int l4 = 31; l4 >= 0; l4--) {
					if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && sprite2.myPixels[k5 - 1 + (l4 - 1) * 32] > 0) {
						sprite2.myPixels[k5 + l4 * 32] = 0x302020;
					}
				}
			}
		}

		if (definition.certTemplateID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}

		if (definition.lendTemplateID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}

		if (k == 0) {
			mruNodes1.removeFromCache(sprite2, i);
		}

		DrawingArea.initDrawingArea(j2, i2, ai1);
		DrawingArea.setBounds(k2, i3, l2, j3);
		Rasterizer.centerX = k1;
		Rasterizer.centerY = l1;
		Rasterizer.lineOffsets = ai;
		Rasterizer.notTextured = true;

		if (definition.stackable) {
			sprite2.maxWidth = 33;
		} else {
			sprite2.maxWidth = 32;
		}

		sprite2.maxHeight = j;
		return sprite2;
	}

	public static void nullify() {
		mruNodes2 = null;
		mruNodes1 = null;
		streamIndices = null;
		cache = null;
		buffer = null;
	}

	public static void unpackConfig(Archive streamLoader) {
		buffer = new ByteBuffer(streamLoader.get("obj.dat"));
		ByteBuffer stream = new ByteBuffer(streamLoader.get("obj.idx"));
		totalItems = stream.getUnsignedShort();
		streamIndices = new int[totalItems];
		int i = 2;

		for (int j = 0; j < totalItems; j++) {
			streamIndices[j] = i;
			i += stream.getUnsignedShort();
		}

		cache = new ItemDefinition[10];

		for (int k = 0; k < 10; k++) {
			cache[k] = new ItemDefinition();
		}
	}

	public String[] actions;
	private int anInt162;
	int anInt164;
	public int maleWearId;
	private int anInt166;
	private int anInt167;
	private int anInt173;
	private int maleDialogue;
	private int anInt184;
	private int anInt185;
	int anInt188;
	private int anInt191;
	private int anInt192;
	private int anInt196;
	private int femaleDialogue;
	public int femaleWearId;
	private int modelOffsetX;
	public int certID;
	public int certTemplateID;
	public byte[] description;
	public byte femaleWieldX;
	public byte femaleWieldY;
	public byte femaleWieldZ;
	public String[] groundActions;
	public int id;
	public int lendID;
	private int lendTemplateID;
	public byte maleWieldX;
	public byte maleWieldY;
	public byte maleWieldZ;
	public boolean membersObject;
	public int modelID;
	int modelOffset1;
	int modelOffsetY;
	public int modelRotation1;
	public int modelRotation2;
	public int modelZoom;
	int[] modifiedModelColors;
	public String name;
	int[] originalModelColors;
	public boolean stackable;
	private int[] stackAmounts;
	private int[] stackIDs;
	public int team;
	public int value;

	public ItemDefinition() {
		id = -1;
	}

	public Model getInventoryModel(int amount) {
		if (stackIDs != null && amount > 1) {
			int id = -1;

			for (int i = 0; i < 10; i++) {
				if (amount >= stackAmounts[i] && stackAmounts[i] != 0) {
					id = stackIDs[i];
				}
			}

			if (id != -1) {
				return get(id).getInventoryModel(1);
			}
		}

		Model model = (Model) mruNodes2.insertFromCache(id);

		if (model != null) {
			return model;
		}

		model = Model.fetchModel(modelID);

		if (model == null) {
			return null;
		}

		if (anInt167 != 128 || anInt192 != 128 || anInt191 != 128) {
			model.scaleT(anInt167, anInt191, anInt192);
		}

		if (modifiedModelColors != null) {
			for (int l = 0; l < modifiedModelColors.length; l++) {
				model.method476(modifiedModelColors[l], originalModelColors[l]);
			}
		}

		model.light(64 + anInt196, 768 + anInt184, -50, -10, -50, true);
		model.aBoolean1659 = true;
		mruNodes2.removeFromCache(model, id);
		return model;
	}

	public boolean dialogueModelFetched(int j) {
		int k = maleDialogue;
		int l = anInt166;

		if (j == 1) {
			k = femaleDialogue;
			l = anInt173;
		}

		if (k == -1) {
			return true;
		}

		boolean flag = true;

		if (!Model.method463(k)) {
			flag = false;
		}

		if (l != -1 && !Model.method463(l)) {
			flag = false;
		}

		return flag;
	}

	public Model method194(int j) {
		int k = maleDialogue;
		int l = anInt166;

		if (j == 1) {
			k = femaleDialogue;
			l = anInt173;
		}

		if (k == -1) {
			return null;
		}

		Model model = Model.fetchModel(k);

		if (l != -1) {
			Model model_1 = Model.fetchModel(l);
			Model models[] = { model, model_1 };
			model = new Model(2, models);
		}

		if (modifiedModelColors != null) {
			for (int i1 = 0; i1 < modifiedModelColors.length; i1++) {
				model.method476(modifiedModelColors[i1], originalModelColors[i1]);
			}
		}

		return model;
	}

	public boolean method195(int j) {
		int k = maleWearId;
		int l = anInt188;
		int i1 = anInt185;

		if (j == 1) {
			k = femaleWearId;
			l = anInt164;
			i1 = anInt162;
		}

		if (k == -1) {
			return true;
		}

		boolean flag = true;

		if (!Model.method463(k)) {
			flag = false;
		}

		if (l != -1 && !Model.method463(l)) {
			flag = false;
		}

		if (i1 != -1 && !Model.method463(i1)) {
			flag = false;
		}

		return flag;
	}

	public Model method196(int i) {
		int j = maleWearId;
		int k = anInt188;
		int l = anInt185;

		if (i == 1) {
			j = femaleWearId;
			k = anInt164;
			l = anInt162;
		}

		if (j == -1) {
			return null;
		}

		Model model = Model.fetchModel(j);

		if (k != -1) {
			if (l != -1) {
				Model model_1 = Model.fetchModel(k);
				Model model_3 = Model.fetchModel(l);
				Model model_1s[] = { model, model_1, model_3 };
				model = new Model(3, model_1s);
			} else {
				Model model_2 = Model.fetchModel(k);
				Model models[] = { model, model_2 };
				model = new Model(2, models);
			}
		}

		if (i == 0 && (maleWieldX != 0 || maleWieldY != 0 || maleWieldZ != 0)) {
			model.translate(maleWieldX, maleWieldY, maleWieldZ);
		}

		if (i == 1 && (femaleWieldX != 0 || femaleWieldY != 0 || femaleWieldZ != 0)) {
			model.translate(femaleWieldX, femaleWieldY, femaleWieldZ);
		}

		if (modifiedModelColors != null) {
			for (int i1 = 0; i1 < modifiedModelColors.length; i1++) {
				model.method476(modifiedModelColors[i1], originalModelColors[i1]);
			}
		}

		return model;
	}

	public Model method202(int i) {
		if (stackIDs != null && i > 1) {
			int j = -1;

			for (int k = 0; k < 10; k++) {
				if (i >= stackAmounts[k] && stackAmounts[k] != 0) {
					j = stackIDs[k];
				}
			}

			if (j != -1) {
				return get(j).method202(1);
			}
		}

		Model model = Model.fetchModel(modelID);

		if (model == null) {
			return null;
		}

		if (modifiedModelColors != null) {
			for (int l = 0; l < modifiedModelColors.length; l++) {
				model.method476(modifiedModelColors[l], originalModelColors[l]);
			}
		}

		return model;
	}

	private void readValues(ByteBuffer buffer) {
		do {
			int opcode = buffer.getUnsignedByte();

			if (opcode == 0) {
				return;
			} else if (opcode == 1) {
				modelID = buffer.getUnsignedShort();
			} else if (opcode == 2) {
				name = buffer.getString();
			} else if (opcode == 3) {
				description = buffer.getBytes();
			} else if (opcode == 4) {
				modelZoom = buffer.getUnsignedShort();
			} else if (opcode == 5) {
				modelRotation1 = buffer.getUnsignedShort();
			} else if (opcode == 6) {
				modelRotation2 = buffer.getUnsignedShort();
			} else if (opcode == 7) {
				modelOffset1 = buffer.getUnsignedShort();

				if (modelOffset1 > 32767) {
					modelOffset1 -= 0x10000;
				}
			} else if (opcode == 8) {
				modelOffsetY = buffer.getUnsignedShort();

				if (modelOffsetY > 32767) {
					modelOffsetY -= 0x10000;
				}
			} else if (opcode == 10) {
				buffer.getUnsignedShort();
			} else if (opcode == 11) {
				stackable = true;
			} else if (opcode == 12) {
				value = buffer.getIntLittleEndian();
			} else if (opcode == 16) {
				membersObject = true;
			} else if (opcode == 23) {
				maleWearId = buffer.getUnsignedShort();
				maleWieldY = buffer.getSignedByte();
			} else if (opcode == 24) {
				anInt188 = buffer.getUnsignedShort();
			} else if (opcode == 25) {
				femaleWearId = buffer.getUnsignedShort();
				femaleWieldY = buffer.getSignedByte();
			} else if (opcode == 26) {
				anInt164 = buffer.getUnsignedShort();
			} else if (opcode >= 30 && opcode < 35) {
				if (groundActions == null) {
					groundActions = new String[5];
				}

				groundActions[opcode - 30] = buffer.getString();

				if (groundActions[opcode - 30].equalsIgnoreCase("hidden")) {
					groundActions[opcode - 30] = null;
				}
			} else if (opcode >= 35 && opcode < 40) {
				if (actions == null) {
					actions = new String[5];
				}

				actions[opcode - 35] = buffer.getString();
			} else if (opcode == 40) {
				int size = buffer.getUnsignedByte();
				modifiedModelColors = new int[size];
				originalModelColors = new int[size];

				for (int k = 0; k < size; k++) {
					modifiedModelColors[k] = buffer.getUnsignedShort();
					originalModelColors[k] = buffer.getUnsignedShort();
				}
			} else if (opcode == 78) {
				anInt185 = buffer.getUnsignedShort();
			} else if (opcode == 79) {
				anInt162 = buffer.getUnsignedShort();
			} else if (opcode == 90) {
				maleDialogue = buffer.getUnsignedShort();
			} else if (opcode == 91) {
				femaleDialogue = buffer.getUnsignedShort();
			} else if (opcode == 92) {
				anInt166 = buffer.getUnsignedShort();
			} else if (opcode == 93) {
				anInt173 = buffer.getUnsignedShort();
			} else if (opcode == 95) {
				modelOffsetX = buffer.getUnsignedShort();
			} else if (opcode == 97) {
				certID = buffer.getUnsignedShort();
			} else if (opcode == 98) {
				certTemplateID = buffer.getUnsignedShort();
			} else if (opcode >= 100 && opcode < 110) {
				if (stackIDs == null) {
					stackIDs = new int[10];
					stackAmounts = new int[10];
				}

				stackIDs[opcode - 100] = buffer.getUnsignedShort();
				stackAmounts[opcode - 100] = buffer.getUnsignedShort();
			} else if (opcode == 110) {
				anInt167 = buffer.getUnsignedShort();
			} else if (opcode == 111) {
				anInt192 = buffer.getUnsignedShort();
			} else if (opcode == 112) {
				anInt191 = buffer.getUnsignedShort();
			} else if (opcode == 113) {
				anInt196 = buffer.getSignedByte();
			} else if (opcode == 114) {
				anInt184 = buffer.getSignedByte() * 5;
			} else if (opcode == 115) {
				team = buffer.getUnsignedByte();
			} else if (opcode == 121) {
				lendID = buffer.getUnsignedShort();
			} else if (opcode == 122) {
				lendTemplateID = buffer.getUnsignedShort();
			}
		} while (true);
	}

	private void setDefaults() {
		modelID = 0;
		name = null;
		description = null;
		originalModelColors = null;
		modifiedModelColors = null;
		modelZoom = 2000;
		modelRotation1 = 0;
		modelRotation2 = 0;
		modelOffsetX = 0;
		modelOffset1 = 0;
		modelOffsetY = 0;
		stackable = false;
		value = 1;
		membersObject = false;
		groundActions = null;
		actions = null;
		lendID = -1;
		lendTemplateID = -1;
		maleWearId = -1;
		anInt188 = -1;
		femaleWearId = -1;
		anInt164 = -1;
		anInt185 = -1;
		anInt162 = -1;
		maleDialogue = -1;
		anInt166 = -1;
		femaleDialogue = -1;
		anInt173 = -1;
		stackIDs = null;
		stackAmounts = null;
		certID = -1;
		certTemplateID = -1;
		anInt167 = 128;
		anInt192 = 128;
		anInt191 = 128;
		anInt196 = 0;
		anInt184 = 0;
		team = 0;
		femaleWieldY = 0;
		femaleWieldX = 0;
		femaleWieldZ = 0;
		maleWieldX = 0;
		maleWieldZ = 0;
		maleWieldY = 0;
	}

	private void toLend() {
		ItemDefinition itemDef = get(lendTemplateID);
		actions = new String[5];
		modelID = itemDef.modelID;
		modelOffset1 = itemDef.modelOffset1;
		modelRotation2 = itemDef.modelRotation2;
		modelOffsetY = itemDef.modelOffsetY;
		modelZoom = itemDef.modelZoom;
		modelRotation1 = itemDef.modelRotation1;
		modelOffsetX = itemDef.modelOffsetX;
		value = 0;
		ItemDefinition definition = get(lendID);
		anInt166 = definition.anInt166;
		originalModelColors = definition.originalModelColors;
		anInt185 = definition.anInt185;
		femaleWearId = definition.femaleWearId;
		anInt173 = definition.anInt173;
		maleDialogue = definition.maleDialogue;
		groundActions = definition.groundActions;
		maleWearId = definition.maleWearId;
		name = definition.name;
		anInt188 = definition.anInt188;
		membersObject = definition.membersObject;
		femaleDialogue = definition.femaleDialogue;
		anInt164 = definition.anInt164;
		anInt162 = definition.anInt162;
		modifiedModelColors = definition.modifiedModelColors;
		team = definition.team;

		if (definition.actions != null) {
			for (int i = 0; i < 4; i++) {
				actions[i] = definition.actions[i];
			}
		}

		actions[4] = "Discard";
	}

	private void toNote() {
		ItemDefinition definition = get(certTemplateID);
		modelID = definition.modelID;
		modelZoom = definition.modelZoom;
		modelRotation1 = definition.modelRotation1;
		modelRotation2 = definition.modelRotation2;
		modelOffsetX = definition.modelOffsetX;
		modelOffset1 = definition.modelOffset1;
		modelOffsetY = definition.modelOffsetY;
		modifiedModelColors = definition.modifiedModelColors;
		originalModelColors = definition.originalModelColors;
		definition = get(certID);
		name = definition.name;
		membersObject = definition.membersObject;
		value = definition.value;
		String s = "a";
		char c = definition.name.charAt(0);

		if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
			s = "an";
		}

		description = ("Swap this note at any bank for " + s + " " + definition.name + ".").getBytes();
		stackable = true;
	}

}