package com.athena;

/**
 * Created by Stan van der Bend on 23/12/2017.
 * project: immortal client
 * package: com.arlania
 */
public class ItemDef_Jarrod_1 {

	public static ItemDef forDef(ItemDef itemDef, int ID) {

		ItemDef itemDef2;
		switch (ID){




			case 4784:
			itemDef.name = "@cya@Mana Minigun"; // name of the item
			itemDef.modelID = 65241; // Model id of the inv (Inv models, drop models etcetcetc)
			itemDef.maleEquip1 = 65242; // Equipment model for the item (Male)
			itemDef.femaleEquip1 = 65242; // same as maleEquip but for female
			itemDef.actions = new String[5]; // String array of 5 actions
			itemDef.actions[1] = "Wear"; // Adding `Wear` option
			break;
			case 20932:
				itemDef.name = "@mag@Doflamingo @red@Minigun @gre@24dr"; // name of the item
				itemDef.modelID = 90674; // Model id of the inv (Inv models, drop models etcetcetc)
				itemDef.maleEquip1 = 90675; // Equipment model for the item (Male)
				itemDef.femaleEquip1 = 90675; // same as maleEquip but for female
				itemDef.actions = new String[5]; // String array of 5 actions
				itemDef.actions[1] = "Wear"; // Adding `Wear` option
				itemDef.modelZoom = 1500;
				itemDef.rotationY = 339;
				itemDef.rotationX = 192;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				break;
			case 3717:
				itemDef.modelID = 96113;
				itemDef.name = "@bla@Supreme @gre@ Lord Blade";
				itemDef.maleEquip1 = 96114;
				itemDef.femaleEquip1 = 96114;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {60};
				itemDef.newModelColor = new int[]    {52};
				break;
			case 1667:
				itemDef.modelID = 94424;
				itemDef.name = "@bla@Supreme @gre@Lord full helm";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 672;
				itemDef.rotationY = 85;
				itemDef.rotationX = 1867;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -3;
				itemDef.maleEquip1 = 94425;
				itemDef.femaleEquip1 = 94425;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.maleDialogue = 62729;
				itemDef.femaleDialogue = 62729;
				itemDef.editedModelColor = new int[] {60};
				itemDef.newModelColor = new int[]    {52};
				break;
			case 1554:
				itemDef.modelID = 94426;
				itemDef.name = "@bla@Supreme @gre@Lord platebody";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 1506;
				itemDef.rotationY = 473;
				itemDef.rotationX = 2042;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 94427;
				itemDef.femaleEquip1 = 94427;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {60};
				itemDef.newModelColor = new int[]    {52};
				break;
			case 1647:
				itemDef.modelID = 94428;
				itemDef.name = "@bla@Supreme @gre@Lord platelegs";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 1740;
				itemDef.rotationY = 474;
				itemDef.rotationX = 2045;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 94429;
				itemDef.femaleEquip1 = 94429;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {60};
				itemDef.newModelColor = new int[]    {52};
				break;
			case 1648:
				itemDef.modelID = 94431;
				itemDef.name = "@bla@Supreme @gre@Lord gloves";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 648;
				itemDef.rotationY = 618;
				itemDef.rotationX = 1143;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 94432;
				itemDef.femaleEquip1 = 94432;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {60};
				itemDef.newModelColor = new int[]    {52};
				break;
			case 1666:
				itemDef.modelID = 94430;
				itemDef.name = "@bla@Supreme @gre@Lord boots";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 825;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetY = 0;
				itemDef.rotationY = 400;
				itemDef.maleEquip1 = 94430;
				itemDef.femaleEquip1 = 94430;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {60};
				itemDef.newModelColor = new int[]    {52};
				break;
			case 20750:
				itemDef.name = "@red@Owner Cape @gre@25dr"; // name of the item
				itemDef.modelID = 90403; // Model id of the inv (Inv models, drop models etcetcetc)
				itemDef.maleEquip1 = 90404; // Equipment model for the item (Male)
				itemDef.femaleEquip1 = 90404; // same as maleEquip but for female
				itemDef.actions = new String[5]; // String array of 5 actions
				itemDef.actions[1] = "Wear"; // Adding `Wear` option

				itemDef.rotationY = 307;
				itemDef.rotationX = 978;
				break;
			case 20751:
				itemDef.name = "@red@Yanille glaive @gre@15dr"; // name of the item
				itemDef.modelID = 91693; // Model id of the inv (Inv models, drop models etcetcetc)
				itemDef.maleEquip1 = 91694; // Equipment model for the item (Male)
				itemDef.femaleEquip1 = 91694; // same as maleEquip but for female
				itemDef.actions = new String[5]; // String array of 5 actions
				itemDef.actions[1] = "Wear"; // Adding `Wear` option
				break;

			case 20752:
				itemDef.name = "@mag@Sponsor glaive"; // name of the item
				itemDef.modelID = 90405; // Model id of the inv (Inv models, drop models etcetcetc)
				itemDef.maleEquip1 = 90406; // Equipment model for the item (Male)
				itemDef.femaleEquip1 = 90406; // same as maleEquip but for female
				itemDef.actions = new String[5]; // String array of 5 actions
				itemDef.actions[1] = "Wear"; // Adding `Wear` option
				break;

			case 3999:
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 51;
				itemDef.modelID = 91610;
				itemDef.name = "@blu@Blue Torva Body";
				itemDef.description = "Simply onfire";
				itemDef.modelZoom = 1579;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetX = 0;
				itemDef.rotationX = 2030;
				itemDef.rotationY = 333;
				itemDef.maleEquip1 = 91611;
				itemDef.femaleEquip1 = 91611;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;

			case 4000:
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 51;
				itemDef.modelID = 91606;
				itemDef.name = "@blu@Blue Torva Legs";
				itemDef.description = "Simply onfire";
				itemDef.modelZoom = 1579;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetX = 0;
				itemDef.rotationX = 2030;
				itemDef.rotationY = 333;
				itemDef.maleEquip1 = 91607;
				itemDef.femaleEquip1 = 91607;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 4001:
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 51;
				itemDef.modelID = 91608;
				itemDef.name = "@blu@Blue Torva Helm";
				itemDef.description = "Simply onfire";
				itemDef.modelZoom = 1100;
				itemDef.modelOffset1 = 16;
				itemDef.modelOffsetY = 0;
				itemDef.rotationX = 533;
				itemDef.rotationY = 333;
				itemDef.maleEquip1 = 91609;
				itemDef.femaleEquip1 = 91609;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;




			case 4644:
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 56;
				itemDef.modelID = 91610;
				itemDef.name = "@yel@Yanille Body";
				itemDef.description = "Simply onfire";
				itemDef.modelZoom = 1579;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetX = 0;
				itemDef.rotationX = 2030;
				itemDef.rotationY = 333;
				itemDef.maleEquip1 = 91611;
				itemDef.femaleEquip1 = 91611;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 11552:
				itemDef2 = ItemDef.forID(7462);
				itemDef.modelID = itemDef2.modelID;
				itemDef.maleEquip1 = itemDef2.maleEquip1;
				itemDef.femaleEquip1 = itemDef2.femaleEquip1;
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.name = "@yel@Yanille Gloves";
				itemDef.colourRedefine2 = 326;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20989:
				itemDef2 = ItemDef.forID(19031);
				itemDef.modelID = itemDef2.modelID;
				itemDef.maleEquip1 = itemDef2.maleEquip1;
				itemDef.femaleEquip1 = itemDef2.femaleEquip1;
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.name = "@blu@Angelic Mask";
				itemDef.colourRedefine2 = 92000;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20990:
				itemDef2 = ItemDef.forID(19029);
				itemDef.modelID = itemDef2.modelID;
				itemDef.maleEquip1 = itemDef2.maleEquip1;
				itemDef.femaleEquip1 = itemDef2.femaleEquip1;
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.name = "@blu@Angelic Body";
				itemDef.colourRedefine2 = 92000;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20991:
				itemDef2 = ItemDef.forID(19030);
				itemDef.modelID = itemDef2.modelID;
				itemDef.maleEquip1 = itemDef2.maleEquip1;
				itemDef.femaleEquip1 = itemDef2.femaleEquip1;
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.name = "@blu@Angelic Legs";
				itemDef.colourRedefine2 = 92000;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20992:
				itemDef2 = ItemDef.forID(19032);
				itemDef.modelID = itemDef2.modelID;
				itemDef.maleEquip1 = itemDef2.maleEquip1;
				itemDef.femaleEquip1 = itemDef2.femaleEquip1;
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.name = "@blu@Angelic Boots";
				itemDef.colourRedefine2 = 92000;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20993:
				itemDef2 = ItemDef.forID(19033);
				itemDef.modelID = itemDef2.modelID;
				itemDef.maleEquip1 = itemDef2.maleEquip1;
				itemDef.femaleEquip1 = itemDef2.femaleEquip1;
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.name = "@blu@Angelic Gloves";
				itemDef.colourRedefine2 = 92000;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;



			case 4645:
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 56;
				itemDef.modelID = 91606;
				itemDef.name = "@yel@Yanille Legs";
				itemDef.description = "Simply onfire";
				itemDef.modelZoom = 1579;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetX = 0;
				itemDef.rotationX = 2030;
				itemDef.rotationY = 333;
				itemDef.maleEquip1 = 91607;
				itemDef.femaleEquip1 = 91607;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 4646:
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 56;
				itemDef.modelID = 91608;
				itemDef.name = "@yel@Yanille Helm";
				itemDef.description = "Simply onfire";
				itemDef.modelZoom = 1100;
				itemDef.modelOffset1 = 16;
				itemDef.modelOffsetY = 0;
				itemDef.rotationX = 533;
				itemDef.rotationY = 333;
				itemDef.maleEquip1 = 91609;
				itemDef.femaleEquip1 = 91609;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;

			case 4799:
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 59;
				itemDef.modelID = 91608;
				itemDef.name = "@red@Red Textured Helm";
				itemDef.description = "Simply onfire";
				itemDef.modelZoom = 1100;
				itemDef.modelOffset1 = 16;
				itemDef.modelOffsetY = 0;
				itemDef.rotationX = 533;
				itemDef.rotationY = 333;
				itemDef.maleEquip1 = 91609;
				itemDef.femaleEquip1 = 91609;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 4800:
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 59;
				itemDef.modelID = 91610;
				itemDef.name = "@red@Red Textured Body";
				itemDef.description = "Simply onfire";
				itemDef.modelZoom = 1579;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetX = 0;
				itemDef.rotationX = 2030;
				itemDef.rotationY = 333;
				itemDef.maleEquip1 = 91611;
				itemDef.femaleEquip1 = 91611;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 4801:
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 59;
				itemDef.modelID = 91606;
				itemDef.name = "@red@Red Textured Legs";
				itemDef.description = "Simply onfire";
				itemDef.modelZoom = 1579;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetX = 0;
				itemDef.rotationX = 2030;
				itemDef.rotationY = 333;
				itemDef.maleEquip1 = 91607;
				itemDef.femaleEquip1 = 91607;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;

			case 4647:
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 58;
				itemDef.modelID = 91610;
				itemDef.name = "@gre@Green Textured Body";
				itemDef.description = "Simply onfire";
				itemDef.modelZoom = 1579;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetX = 0;
				itemDef.rotationX = 2030;
				itemDef.rotationY = 333;
				itemDef.maleEquip1 = 91611;
				itemDef.femaleEquip1 = 91611;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 4648:
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 58;
				itemDef.modelID = 91606;
				itemDef.name = "@gre@Green Textured Legs";
				itemDef.description = "Simply onfire";
				itemDef.modelZoom = 1579;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetX = 0;
				itemDef.rotationX = 2030;
				itemDef.rotationY = 333;
				itemDef.maleEquip1 = 91607;
				itemDef.femaleEquip1 = 91607;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;

			case 3983:
				itemDef.name = "@or2@Redfire Textured Cape";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 59;

				itemDef.modelID = 9631;
				itemDef.modelZoom = 2040;
				itemDef.rotationY = 545;
				itemDef.rotationX = 2;
				itemDef.modelOffsetY = 5;
				itemDef.modelOffset1 = 4;

				itemDef.maleEquip1 = 9638;
				itemDef.femaleEquip1 = 9640;
				itemDef.description = "A rare cape of Blue Lava.";
				break;

			case 3985:
				itemDef.name = "@gre@Green Textured Cape";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 58;

				itemDef.modelID = 9631;
				itemDef.modelZoom = 2040;
				itemDef.rotationY = 545;
				itemDef.rotationX = 2;
				itemDef.modelOffsetY = 5;
				itemDef.modelOffset1 = 4;

				itemDef.maleEquip1 = 9638;
				itemDef.femaleEquip1 = 9640;
				itemDef.description = "A rare cape of Blue Lava.";
				break;
			case 3982:
				itemDef.name = "@yel@Brightlava Texture Cape";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 56;
				itemDef.modelID = 9631;
				itemDef.modelZoom = 2040;
				itemDef.rotationY = 545;
				itemDef.rotationX = 2;
				itemDef.modelOffsetY = 5;
				itemDef.modelOffset1 = 4;
				itemDef.maleEquip1 = 9638;
				itemDef.femaleEquip1 = 9640;
				itemDef.description = "A rare cape of Blue Lava.";
				itemDef.stackable = false;
				break;

			case 4649:
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 58;
				itemDef.modelID = 91608;
				itemDef.name = "@gre@Green Textured Helm";
				itemDef.description = "Simply onfire";
				itemDef.modelZoom = 1100;
				itemDef.modelOffset1 = 16;
				itemDef.modelOffsetY = 0;
				itemDef.rotationX = 533;
				itemDef.rotationY = 333;
				itemDef.maleEquip1 = 91609;
				itemDef.femaleEquip1 = 91609;
				itemDef.groundActions = new String[5];
				itemDef.actions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;

			case 3981:
				itemDef.name = "@mag@Yanille Cape";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 64;

				itemDef.modelID = 9631;
				itemDef.modelZoom = 2040;
				itemDef.rotationY = 545;
				itemDef.rotationX = 2;
				itemDef.modelOffsetY = 5;
				itemDef.modelOffset1 = 4;

				itemDef.maleEquip1 = 9638;
				itemDef.femaleEquip1 = 9640;
				itemDef.description = "A rare cape of Blue Lava.";
				break;

			case 19467:
				itemDef.name = "Armadylian Machine";
				itemDef.description = "A strange Armadyl contraption.";
				itemDef.modelZoom = 1284;
				itemDef.rotationY = 189;
				itemDef.rotationX = 148;
				itemDef.modelOffset1 = 8;
				itemDef.modelOffsetY = -18;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.modelID = 95021;
				itemDef.maleEquip1 = 95022;
				itemDef.femaleEquip1 = 95022;
				itemDef.stackable = true;
				break;
			case 13263:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[2] = "Upgrade";
				itemDef.actions[4] = "Drop";
				break;

			case 7453:
				itemDef.name = "@cya@Culinar Gloves Y1";
				itemDef.colourRedefine3 = 250000;
				break;
			case 7454:
				itemDef.name = "@red@Culinar Gloves Y2";
				itemDef.colourRedefine3 = 330000;
				break;
			case 7455:
				itemDef.name = "@gre@Culinar Gloves Y3";
				itemDef.colourRedefine3 = 270000;
				break;
			case 7456:
				itemDef.name = "@whi@Culinar Gloves Y4";
				itemDef.colourRedefine3 = 280000;
				break;
			case 7457:
				itemDef.name = "@yel@Culinar Gloves Y5";
				itemDef.colourRedefine3 = 290000;
				break;
			case 7458:
				itemDef.name = "@bla@Culinar Gloves Y6";
				itemDef.colourRedefine3 = 300000;
				break;
			case 7459:
				itemDef.name = "@dre@Culinar Gloves Y7";
				itemDef.colourRedefine3 = 260000;
				break;
			case 7460:
				itemDef.name = "@blu@Culinar Gloves Y8";
				itemDef.colourRedefine3 = 320000;
				break;
			case 7461:
				itemDef.name = "@mag@Culinar Gloves Y9";
				itemDef.colourRedefine3 = 310000;
				break;

			case 1153:
				itemDef.name = "Beginner Dragon fullhelm";
				itemDef.modelZoom = 672;
				itemDef.rotationY = 85;
				itemDef.rotationX = 1867;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -3;
				itemDef.maleEquip1 = 91321;
				itemDef.colourRedefine3 = 120000;

				itemDef.femaleEquip1 = 91321;
				itemDef.modelID = 91320;
				break;
			case 12852:
				itemDef.name = "@red@Raids @yel@Token";
				itemDef.colourRedefine3 = 142157;
				break;

			case 11724:
				itemDef.name = "@red@Supreme Bandos @yel@chestplate";
				itemDef.colourRedefine2 = 1042157;
				break;

			case 11726:
				itemDef.name = "@red@Supreme Bandos @yel@tassets";
				itemDef.colourRedefine2 = 1042157;
				break;

			case 11728:
				itemDef.name = "@red@Supreme Bandos @yel@boots";
				itemDef.colourRedefine2 = 1042157;
				break;

			case 19031:
				itemDef.colourRedefine3 = 180000;
				break;

			case 19029:
				itemDef.colourRedefine3 = 180000;
				break;

			case 19030:
				itemDef.colourRedefine3 = 180000;
				break;

			case 19032:
				itemDef.colourRedefine3 = 180000;
				break;

			case 19033:
				itemDef.colourRedefine3 = 180000;
				break;

			case 1115:
				itemDef.name = "Beginner Dragon platebody";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 1506;
				itemDef.rotationY = 473;
				itemDef.rotationX = 2042;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 91316;
				itemDef.colourRedefine3 = 120000;

				itemDef.modelID = 91315;
				itemDef.femaleEquip1 = 91316;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;

			case 1067:
				itemDef.modelID = 91323;
				itemDef.name = "Beginner Dragon platelegs";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 1740;
				itemDef.rotationY = 474;
				itemDef.rotationX = 2045;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 91322;
				itemDef.modelID = 91323;
				itemDef.colourRedefine3 = 120000;
				itemDef.femaleEquip1 = 91322;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;

			case 21050:
				itemDef.name = "Light Darklord fullhelm";
				itemDef.modelZoom = 672;
				itemDef.rotationY = 85;
				itemDef.rotationX = 1867;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -3;
				itemDef.colourRedefine2 = 68475;
				itemDef.maleEquip1 = 91321;
				itemDef.femaleEquip1 = 91321;
				itemDef.modelID = 91320;
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;

			case 21051:
				itemDef.name = "Light Darklord platebody";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 1506;
				itemDef.rotationY = 473;
				itemDef.rotationX = 2042;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.colourRedefine2 = 68475;

				itemDef.modelID = 91315;
				itemDef.maleEquip1 = 91316;
				itemDef.femaleEquip1 = 91316;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;

			case 21052:
				itemDef.modelID = 91323;
				itemDef.name = "Light Darklord platelegs";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 1740;
				itemDef.rotationY = 474;
				itemDef.rotationX = 2045;
				itemDef.modelID = 91323;
				itemDef.colourRedefine2 = 68475;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 91322;
				itemDef.femaleEquip1 = 91322;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 21053:
				itemDef.modelID = 91318;
				itemDef.name = "Light Darklord gloves";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 648;
				itemDef.rotationY = 618;
				itemDef.rotationX = 1143;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 91319;
				itemDef.femaleEquip1 = 91319;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.colourRedefine2 = 68475;
				break;

			case 21045:
				itemDef.name = "@mag@Crash @cya@box";
				itemDef.modelID = 91355;
				/* 21314 */       itemDef.modelZoom = 1100;
				itemDef.rotationX = ItemDef.forID(7956).rotationX;
				itemDef.rotationY = ItemDef.forID(7956).rotationY;
				itemDef.modelOffsetX = ItemDef.forID(7956).modelOffsetX;
				itemDef.modelOffsetY = ItemDef.forID(7956).modelOffsetY;
				itemDef.actions = new String[5];
				itemDef.actions[0] = "Open";
				itemDef.actions[4] = "Drop";
				break;
			case 21054:
				itemDef.modelID = 91317;
				itemDef.name = "Light Darklord boots";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 825;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetY = 0;
				itemDef.rotationY = 400;
				itemDef.maleEquip1 = 91317;
				itemDef.femaleEquip1 = 91317;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				itemDef.colourRedefine2 = 68475;
				break;

			case 21011:
				itemDef.modelID = 91318;
				itemDef.name = "Beginner Dragon gloves";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 648;
				itemDef.rotationY = 618;
				itemDef.rotationX = 1143;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 91319;
				itemDef.colourRedefine3 = 120000;

				itemDef.femaleEquip1 = 91319;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;

			case 21012:
				itemDef.modelID = 91317;
				itemDef.name = "Beginner Dragon boots";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 825;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetY = 0;
				itemDef.rotationY = 400;
				itemDef.maleEquip1 = 91317;
				itemDef.colourRedefine3 = 120000;
				itemDef.femaleEquip1 = 91317;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				break;

			case 21056:
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.name = "Elite void melee helm";
				ItemDef melee = ItemDef.forID(11665);
				itemDef.modelID = melee.modelID;
				itemDef.maleEquip1 = melee.maleEquip1;
				itemDef.femaleEquip1 = melee.femaleEquip1;
				itemDef.rotationX = melee.rotationX;
				itemDef.modelOffsetX = melee.modelOffsetX;
				itemDef.modelOffset1 = melee.modelOffset1;
				itemDef.modelOffsetY = melee.modelOffsetY;
				itemDef.rotationY = melee.rotationY;
				itemDef.modelZoom = melee.modelZoom;
				break;
			case 21057:
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.name = "Elite void ranger helm";
				melee = ItemDef.forID(11664);
				itemDef.modelID = melee.modelID;
				itemDef.maleEquip1 = melee.maleEquip1;
				itemDef.maleEquip2 = melee.maleEquip2;
				itemDef.maleEquip3 = melee.maleEquip3;
				itemDef.femaleEquip1 = melee.femaleEquip1;
				itemDef.femaleEquip2 = melee.femaleEquip2;
				itemDef.femaleEquip3 = melee.femaleEquip3;
				itemDef.rotationX = melee.rotationX;
				itemDef.modelOffsetX = melee.modelOffsetX;
				itemDef.modelOffset1 = melee.modelOffset1;
				itemDef.modelOffsetY = melee.modelOffsetY;
				itemDef.rotationY = melee.rotationY;
				itemDef.modelZoom = melee.modelZoom;
				break;
			case 21058:
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.name = "Elite void mage helm";
				melee = ItemDef.forID(11663);
				itemDef.modelID = melee.modelID;
				itemDef.maleEquip1 = melee.maleEquip1;
				itemDef.maleEquip2 = melee.maleEquip2;
				itemDef.maleEquip3 = melee.maleEquip3;
				itemDef.femaleEquip1 = melee.femaleEquip1;
				itemDef.femaleEquip2 = melee.femaleEquip2;
				itemDef.femaleEquip3 = melee.femaleEquip3;
				itemDef.rotationX = melee.rotationX;
				itemDef.modelOffsetX = melee.modelOffsetX;
				itemDef.modelOffset1 = melee.modelOffset1;
				itemDef.modelOffsetY = melee.modelOffsetY;
				itemDef.rotationY = melee.rotationY;
				itemDef.modelZoom = melee.modelZoom;
				break;
			case 21059:
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.name = "Elite void gloves";
				melee = ItemDef.forID(8842);
				itemDef.modelID = melee.modelID;
				itemDef.maleEquip1 = melee.maleEquip1;
				itemDef.femaleEquip1 = melee.femaleEquip1;
				itemDef.rotationX = melee.rotationX;
				itemDef.modelOffsetX = melee.modelOffsetX;
				itemDef.modelOffset1 = melee.modelOffset1;
				itemDef.modelOffsetY = melee.modelOffsetY;
				itemDef.rotationY = melee.rotationY;
				itemDef.modelZoom = melee.modelZoom;
				break;

			case 21020:
				itemDef.modelID = 91318;
				itemDef.name = "Dark Predator gloves";
				itemDef.colourRedefine = 96000;
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 648;
				itemDef.rotationY = 618;
				itemDef.rotationX = 1143;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 90138;
				itemDef.femaleEquip1 = 90138;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;

			case 21021:
				itemDef.modelID = 91317;
				itemDef.colourRedefine = 96000;
				itemDef.name = "Dark Predator boots";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 825;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetY = 0;
				itemDef.rotationY = 400;
				itemDef.maleEquip1 = 90085;
				itemDef.femaleEquip1 = 90085;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				break;
			case 20411:
				itemDef.modelID = 83331;
				itemDef.name = "Dark Predator boots";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 825;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetY = 0;
				itemDef.rotationY = 400;
				itemDef.maleEquip1 = 83330;
				itemDef.femaleEquip1 = 83330;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				break;

			case 20500:
				itemDef.modelID = 130000;
				itemDef.name = "infernal boots";
				itemDef.maleEquip1 = 130000;
				itemDef.femaleEquip1 = 130000;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				break;
			case 20501:
				itemDef.modelID = 130001;
				itemDef.name = "infernal full helm";
				itemDef.maleEquip1 = 130002;
				itemDef.femaleEquip1 = 130003;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				break;
			case 20502:
				itemDef.modelID = 130004;
				itemDef.name = "infernal plate body";
				itemDef.maleEquip1 = 130005;
				itemDef.femaleEquip1 = 130005;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				break;
			case 20600:
				itemDef.modelID = 96127;
				itemDef.name = "infernal plate body";
				itemDef.maleEquip1 = 96128;
				itemDef.femaleEquip1 = 96128;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				break;
			case 20601:
				itemDef.modelID = 96113;
				itemDef.name = "Infernal Blade";
				itemDef.maleEquip1 = 96114;
				itemDef.femaleEquip1 = 96114;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				break;
			case 20602:
				itemDef.modelID = 96054;
				itemDef.name = "staff of Infernal";
				itemDef.maleEquip1 = 96055;
				itemDef.femaleEquip1 = 96055;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
				itemDef.newModelColor = new int[]    {60,60,60,60,60,60,60,60,60,60,60,60,60,60};
				break;
			case 20603:
				itemDef.modelID = 96113;
				itemDef.name = "Water Blade";
				itemDef.maleEquip1 = 96114;
				itemDef.femaleEquip1 = 96114;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
				itemDef.newModelColor = new int[]    {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
				break;
			case 20607:
				itemDef.modelID = 96113;
				itemDef.name = "Shadow Dragonslayer Blade";
				itemDef.maleEquip1 = 96114;
				itemDef.femaleEquip1 = 96114;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
				itemDef.newModelColor = new int[]    {57,57,57,57,57,57,57,57,57,57,57,57,57,57};
				break;
			case 20510:
				itemDef.modelID = 96113;
				itemDef.name = "@mag@Magma Blade";
				itemDef.maleEquip1 = 96114;
				itemDef.femaleEquip1 = 96114;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
				itemDef.newModelColor = new int[]    {55,55,55,55,55,55,55,55,55,55,55,55,55,55};
				break;
			case 20604:
				itemDef.modelID = 96054;
				itemDef.name = "staff of Water";
				itemDef.maleEquip1 = 96055;
				itemDef.femaleEquip1 = 96055;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
				itemDef.newModelColor = new int[]    {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
				break;
			case 20605:
				itemDef.modelID = 95141;
				itemDef.name = "Water bow";
				itemDef.description = "A pernix shortbow.";
				itemDef.modelZoom = 1447;
				itemDef.rotationY = 296;
				itemDef.rotationX = 377;
				itemDef.modelOffset1 = 5;
				itemDef.modelOffsetY = -33;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 95142;
				itemDef.femaleEquip1 = 95142;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {40,31,62,63,64,65,66,67,68,69,70,71,72,73};
				itemDef.newModelColor = new int[]    {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
				break;
			case 20503:
				itemDef.modelID = 130006;
				itemDef.name = "infernal plate legs";
				itemDef.maleEquip1 = 130007;
				itemDef.femaleEquip1 = 130007;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				break;

			case 20504:
				itemDef.modelID = 130008;
				itemDef.name = "infernal spear";
				itemDef.maleEquip1 = 130009;
				itemDef.femaleEquip1 = 130009;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				break;

			case 21013:
				itemDef.name = "@bla@ Dark Predator's Scythe";
				itemDef.modelID = 94458;
				itemDef.maleEquip1 = 94459;
				itemDef.femaleEquip1 = 94459;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				break;


			case 21008:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.name = "@whi@Mythical sword tier1";
				itemDef.modelID = 91015;
				itemDef.maleEquip1 = 54355;
				itemDef.femaleEquip1 = 54355;
				break;

			case 21004:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.name = "@bla@Dark laser sword";
				itemDef.modelID = 94038;
				itemDef.maleEquip1 = 94039;
				itemDef.femaleEquip1 = 94039;
				break;

			case 21060:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.name = "@gre@ Nature sword";
				itemDef.modelID = 120001;
				itemDef.maleEquip1 = 120000;
				itemDef.femaleEquip1 = 120000;
				break;
			case 21064:
				itemDef.name = "Forest defender";
				itemDef2 = ItemDef.forID(20072);
				itemDef.modelID = itemDef2.modelID;
				itemDef.maleEquip1 = itemDef2.maleEquip1;
				itemDef.femaleEquip1 = itemDef2.femaleEquip1;
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.actions = itemDef2.actions;
				break;
			case 21065:
				itemDef.name = "Forest boots";
				itemDef2 = ItemDef.forID(11732);
				itemDef.modelID = itemDef2.modelID;
				itemDef.maleEquip1 = itemDef2.maleEquip1;
				itemDef.femaleEquip1 = itemDef2.femaleEquip1;
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.actions = itemDef2.actions;
				break;
			case 21066:
				itemDef.name = "Forest gloves";
				itemDef2 = ItemDef.forID(7462);
				itemDef.modelID = itemDef2.modelID;
				itemDef.maleEquip1 = itemDef2.maleEquip1;
				itemDef.femaleEquip1 = itemDef2.femaleEquip1;
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.actions = itemDef2.actions;
				break;
			case 21063:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.name = "@gre@ Forest sword";
				itemDef.modelID = 120001;
				itemDef.maleEquip1 = 120000;
				itemDef.femaleEquip1 = 120000;
				break;
			case 21061:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.name = "@gre@ Oblivion scythe";
				itemDef.modelID = 91674;
				itemDef.maleEquip1 = 91675;
				itemDef.femaleEquip1 = 91675;
				break;
			case 21062:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.name = "@gre@ Lava minigun";
				itemDef.modelID = 90674;
				itemDef.maleEquip1 = 90675;
				itemDef.femaleEquip1 = 90675;
				break;

			case 21003:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.name = "@red@Fire duo laser sword";
				itemDef.modelID = 94036;
				itemDef.maleEquip1 = 94037;
				itemDef.femaleEquip1 = 94037;
				break;

			case 21030:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.name = "@gre@Grass laser sword";
				itemDef.colourRedefine2 = 93823;
				itemDef.modelID = 94034;
				itemDef.maleEquip1 = 94035;
				itemDef.femaleEquip1 = 94035;
				break;

			case 21031:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.name = "@cya@Water laser sword";
				itemDef.colourRedefine2 = 34343;
				itemDef.modelID = 94034;
				itemDef.maleEquip1 = 94035;
				itemDef.femaleEquip1 = 94035;
				break;
			case 21032:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.name = "@gre@Grass Duo laser sword";
				itemDef.colourRedefine2 = 93823;
				itemDef.modelID = 94036;
				itemDef.maleEquip1 = 94037;
				itemDef.femaleEquip1 = 94037;
				break;

			case 21033:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.name = "@cya@Water Duo laser sword";
				itemDef.colourRedefine2 = 34343;
				itemDef.modelID = 94036;
				itemDef.maleEquip1 = 94037;
				itemDef.femaleEquip1 = 94037;
				break;

			case 21002:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.name = "@red@Red laser sword";
				itemDef.modelID = 94034;
				itemDef.maleEquip1 = 94035;
				itemDef.femaleEquip1 = 94035;
				break;

			case 21007:
				ItemDef shard = ItemDef.forID(13727);
				itemDef.name = "@whi@Mythical edge shard";
				itemDef.modelID = shard.modelID;
				itemDef.colourRedefine = 96000;
				itemDef.actions = new String[5];
				itemDef.stackable = true;
				itemDef.actions[2] = "Combine";
				itemDef.actions[4] = "Drop";
				break;
			case 21006:
				ItemDef shard2 = ItemDef.forID(13727);
				itemDef.name = "@whi@Mythical hilt shard";
				itemDef.modelID = shard2.modelID;
				itemDef.colourRedefine = 902;
				itemDef.actions = new String[5];
				itemDef.actions[2] = "Combine";
				itemDef.stackable = true;
				itemDef.actions[4] = "Drop";
				break;


			case 21009:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.name = "@whi@Mythical sword tier2";
				itemDef.modelID = 91015;
				itemDef.maleEquip1 = 100001;
				itemDef.femaleEquip1 = 100001;
				break;
			case 21010:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.name = "@whi@Mythical sword tier3";
				itemDef.modelID = 91015;
				itemDef.maleEquip1 = 100002;
				itemDef.femaleEquip1 = 100002;
				break;


			case 11546:
				itemDef.name = "Lime Slayer helmet[3]";
				itemDef.modelID = 56474;
				itemDef.description = "Yanille full helm";
				itemDef.maleEquip1 = 56473;
				itemDef.femaleEquip1 = 56473;
				itemDef2 = ItemDef.forID(13263);
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.actions = itemDef2.actions;
				itemDef.editedModelColor = new int[] {60};
				itemDef.newModelColor = new int[]    {66};
				break;
			case 11547:
				itemDef.name = "Aqua Slayer helmet[4]";
				itemDef.modelID = 56474;
				itemDef.description = "Yanille full helm";
				itemDef.maleEquip1 = 56473;
				itemDef.femaleEquip1 = 56473;
				itemDef2 = ItemDef.forID(13263);
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.actions = itemDef2.actions;
				itemDef.editedModelColor = new int[] {60};
				itemDef.newModelColor = new int[]    {70};
				break;


            /*  case 20120:
                  itemDef.name = "Invision potion (3)";
                  itemDef.colourRedefine2 = 123123;
                  itemDef.modelID = 2697;
                  itemDef.modelZoom = 550;
                  itemDef.rotationY = 84;
                  itemDef.rotationX = 1996;
                  itemDef.modelOffset1 = 0;
                  itemDef.modelOffsetY = -1;
                  itemDef.stackable = false;
                  itemDef.membersObject = true;
                  itemDef.actions = new String[5];
                  itemDef.actions[0] = "Drink";
                  itemDef.actions[3] = "Empty";
                     break;
              case 20119:
                  itemDef.name = "Invision potion (3)";
                  itemDef.colourRedefine2 = 123123;
                  itemDef.modelID = 2697;
                  itemDef.modelZoom = 550;
                  itemDef.rotationY = 84;
                  itemDef.rotationX = 1996;
                  itemDef.modelOffset1 = 0;
                  itemDef.modelOffsetY = -1;
                  itemDef.stackable = false;
                  itemDef.membersObject = true;
                  itemDef.actions = new String[5];
                  itemDef.actions[0] = "Drink";
                  itemDef.actions[3] = "Empty";
                  itemDef.certTemplateID = 799;
                  itemDef.certID = 20120;
                     break; */

			case 11548:
				itemDef.name = "Corrupt Slayer helmet[5]";
				itemDef.modelID = 56474;
				itemDef.description = "Yanille full helm";
				itemDef.maleEquip1 = 56473;
				itemDef.femaleEquip1 = 56473;
				itemDef2 = ItemDef.forID(13263);
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.actions = itemDef2.actions;
				itemDef.editedModelColor = new int[] {60};
				itemDef.newModelColor = new int[]    {71};
				break;
			case 11550:
				itemDef.name = "Lava Slayer helmet[1]";
				itemDef.modelID = 56474;
				itemDef.description = "Yanille full helm";
				itemDef.maleEquip1 = 56473;
				itemDef.femaleEquip1 = 56473;
				itemDef2 = ItemDef.forID(13263);
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.actions = itemDef2.actions;
				itemDef.editedModelColor = new int[] {60};
				itemDef.newModelColor = new int[]    {40};
				break;
			case 11549:
				itemDef.name = "Golden Slayer helmet[2]";
				itemDef.modelID = 56474;
				itemDef.description = "Yanille full helm";
				itemDef.maleEquip1 = 56473;
				itemDef.femaleEquip1 = 56473;
				itemDef2 = ItemDef.forID(13263);
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.actions = itemDef2.actions;
				itemDef.editedModelColor = new int[] {60};
				itemDef.newModelColor = new int[]    {59};
				break;

			case 11553:
				itemDef.name = "Gloves of Death";
				itemDef.colourRedefine2 = 32326;
				itemDef2 = ItemDef.forID(7462);
				itemDef.modelID = itemDef2.modelID;
				itemDef.maleEquip1 = itemDef2.maleEquip1;
				itemDef.femaleEquip1 = itemDef2.femaleEquip1;
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 11555:
				itemDef.name = "Ring of Chaos @red@10dr";
				itemDef.colourRedefine2 = 32326;
				itemDef2 = ItemDef.forID(6737);
				itemDef.modelID = itemDef2.modelID;
				itemDef.maleEquip1 = itemDef2.maleEquip1;
				itemDef.femaleEquip1 = itemDef2.femaleEquip1;
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.actions[2] = "Upgrade";
				break;
			case 11556:
				itemDef.name = "Ring of Death";
				itemDef.colourRedefine2 = 213131;
				itemDef2 = ItemDef.forID(6737);
				itemDef.modelID = itemDef2.modelID;
				itemDef.maleEquip1 = itemDef2.maleEquip1;
				itemDef.femaleEquip1 = itemDef2.femaleEquip1;
				itemDef.modelOffset1 = itemDef2.modelOffset1;
				itemDef.modelOffsetX = itemDef2.modelOffsetX;
				itemDef.modelOffsetY = itemDef2.modelOffsetY;
				itemDef.rotationY = itemDef2.rotationY;
				itemDef.rotationX = itemDef2.rotationX;
				itemDef.modelZoom = itemDef2.modelZoom;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 6737://BERSERKER RING
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.actions[2] = "Upgrade";
				break;
			case 7462://BARROWS GLOVES
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.actions[2] = "Upgrade";
				break;
			case 620:
				itemDef.name = "10 Boss points";
				itemDef.actions = new String[5];
				itemDef.actions[4] = "Drop";
				itemDef.actions[0] = "Claim";
				break;
			case 17847:
				itemDef.modelID = 90587;
				itemDef.name = "Blood godsword";
				itemDef.description = "The lava flows freely through the blade";
				itemDef.modelZoom = 1500;
				itemDef.rotationY = 228;
				itemDef.rotationX = 1985;
				itemDef.modelOffset1 = 5;
				itemDef.modelOffsetY = -55;
				itemDef.stackable = false;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 90588;
				itemDef.femaleEquip1 = 90588;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.editedModelColor = new int[4];
				itemDef.newModelColor = new int[4];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 52;
				itemDef.editedModelColor[1] = 31;
				itemDef.newModelColor[1] = 52;
				itemDef.editedModelColor[2] = 1;
				itemDef.newModelColor[2] = 57;
				itemDef.editedModelColor[3] = 24;
				itemDef.newModelColor[3] = 57;
				break;
			case 19938:
				itemDef.modelID = 95027;
				itemDef.name = "AK47";
				itemDef.description = "This weapon shouldnt be in the game :o";
				itemDef.modelZoom = 1500;
				itemDef.rotationY = 228;
				itemDef.rotationX = 1985;
				itemDef.modelOffset1 = 5;
				itemDef.modelOffsetY = -55;
				itemDef.stackable = false;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 95028;
				itemDef.femaleEquip1 = 95028;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 20091:
				itemDef.modelID = 95065;
				itemDef.name = "AK47 @red@(Lava)";
				itemDef.description = "This weapon shouldnt be in the game :o";
				itemDef.modelZoom = 1500;
				itemDef.rotationY = 228;
				itemDef.rotationX = 1985;
				itemDef.modelOffset1 = 5;
				itemDef.modelOffsetY = -55;
				itemDef.stackable = false;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 95066;
				itemDef.femaleEquip1 = 95066;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 19939:
				itemDef.modelID = 95029;
				itemDef.name = "Flamethrower";
				itemDef.description = "This weapon shouldnt be in the game :o";
				itemDef.modelZoom = 1500;
				itemDef.rotationY = 228;
				itemDef.rotationX = 1985;
				itemDef.modelOffset1 = 5;
				itemDef.modelOffsetY = -55;
				itemDef.stackable = false;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 95030;
				itemDef.femaleEquip1 = 95030;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;

			case 569:
				itemDef.name = "Fuel";
				itemDef.description = "Fuel for the flamethrower";
				itemDef.stackable = true;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;

			case 17848:
				itemDef.modelID = 90589;
				itemDef.name = "Flaming whip";
				itemDef.description = "Ouch!";
				itemDef.modelZoom = 1000;
				itemDef.rotationY = 601;
				itemDef.rotationX = 1000;
				itemDef.modelOffset1 = 4;
				itemDef.modelOffsetY = 8;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 90590;
				itemDef.femaleEquip1 = 90590;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;

			case 17849:
				itemDef.modelID = 90591;
				itemDef.name = "Razor whip";
				itemDef.description = "Ouch!";
				itemDef.modelZoom = 1000;
				itemDef.rotationY = 601;
				itemDef.rotationX = 1000;
				itemDef.modelOffset1 = 4;
				itemDef.modelOffsetY = 8;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 90592;
				itemDef.femaleEquip1 = 90592;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;

			case 3947:
				itemDef.modelID = 93273;
				itemDef.name = "Baphomet Torva full helm";
				itemDef.description = "Torva full helm imbued with lava";
				itemDef.modelZoom = 672;
				itemDef.rotationY = 85;
				itemDef.rotationX = 1867;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -3;
				itemDef.maleEquip1 = 93274;
				itemDef.femaleEquip1 = 93274;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[2] = "Check-charges";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {40,1,24};
				itemDef.newModelColor = new int[]    {70,55,55};
				break;
			case 3948:
				itemDef.modelID = 93278;
				itemDef.name = "Baphomet Torva platebody";
				itemDef.description = "Torva platebody imbued with lava";
				itemDef.modelZoom = 1506;
				itemDef.rotationY = 473;
				itemDef.rotationX = 2042;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 93277;
				itemDef.femaleEquip1 = 93277;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[2] = "Check-charges";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {40,1,24};
				itemDef.newModelColor = new int[]    {70,55,55};
				break;
			case 3949:
				itemDef.modelID = 93275;
				itemDef.name = "Baphomet Torva platelegs ";
				itemDef.description = "Torva platelegs imbued with lava";
				itemDef.modelZoom = 1740;
				itemDef.rotationY = 474;
				itemDef.rotationX = 2045;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 93276;
				itemDef.femaleEquip1 = 93276;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[2] = "Check-charges";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {40,1,24};
				itemDef.newModelColor = new int[]    {70,55,55};
				break;
			case 3950:
				itemDef.modelID = 95079;
				itemDef.name = "Drygore Boots";
				itemDef.description = "It's a pair of drygore boots.";
				itemDef.modelZoom = 855;
				itemDef.rotationY = 215;
				itemDef.rotationX = 94;
				itemDef.modelOffset1 = 4;
				itemDef.modelOffsetY = -32;
				itemDef.maleEquip1 = 95080;
				itemDef.femaleEquip1 = 95080;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 3951:
				itemDef.modelID = 95081;
				itemDef.name = "Drygore Gauntlets";
				itemDef.description = "It's a pair of Drygore Gauntlets.";
				itemDef.modelZoom = 592;
				itemDef.rotationY = 323;
				itemDef.rotationX = 1710;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 5;
				itemDef.maleEquip1 = 95082;
				itemDef.femaleEquip1 = 95082;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 21081:
				itemDef.name = "Voldemort's top";
				itemDef.modelID = 95035;
				itemDef.femaleEquip1 = 95036;
				itemDef.maleEquip1 = 95036;
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 21082:
				itemDef.name = "Infernal mg";
				itemDef.modelID = 95031;
				itemDef.femaleEquip1 = 95032;
				itemDef.maleEquip1 = 95032;
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;

			case 21083:
				itemDef.name = "Purple mg";
				itemDef.modelID = 95033;
				itemDef.femaleEquip1 = 95034;
				itemDef.maleEquip1 = 95034;
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 21080:
				itemDef.name = "@red@Blood Duel Beretta";
				itemDef.modelID = 94059;
				itemDef.femaleEquip1 = 94060;
				itemDef.maleEquip1 = 94060;
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				itemDef.editedModelColor = new int[4];
				itemDef.newModelColor = new int[4];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 52;
				itemDef.editedModelColor[1] = 31;
				itemDef.newModelColor[1] = 52;
				itemDef.editedModelColor[2] = 1;
				itemDef.newModelColor[2] = 57;
				itemDef.editedModelColor[3] = 24;
				itemDef.newModelColor[3] = 57;
				break;
			case 21075:
				itemDef.name = "@red@Blood phat";
				itemDef.modelID = 94332;
				itemDef.femaleEquip1 = 94333;
				itemDef.maleEquip1 = 94333;
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 51;
				itemDef.newModelColor[0] = 52;

				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;

			case 12421:
				itemDef.name = "@red@Donator Scroll";
				itemDef.actions = new String[] { null, null, "Claim", null, "Drop" };
				break;

			case 12422:
				itemDef.name = "@gre@Super Donator";
				itemDef.actions = new String[] { null, null, "Claim", null, "Drop" };
				itemDef.modelID = ItemDef.forID(12421).modelID;
				break;

			case 20950:
				itemDef.modelID = ItemDef.forID(9018).modelID;
				itemDef.name = "gun powder";
				itemDef.stackable = true;
				itemDef.colourRedefine = 25;
				itemDef.modelZoom = ItemDef.forID(9018).modelZoom;
				itemDef.actions = new String[] { null, null, null, null, "Drop" };
				break;

			case 20951:
				itemDef.modelID = ItemDef.forID(9018).modelID;
				itemDef.name = "sulfur";
				itemDef.stackable = true;
				itemDef.colourRedefine = 8128;
				itemDef.modelZoom = ItemDef.forID(9018).modelZoom;
				itemDef.actions = new String[] { null, null, null, null, "Drop" };
				break;

			case 20952:
				itemDef.modelID = ItemDef.forID(9018).modelID;
				itemDef.name = "Charcoal";
				itemDef.stackable = true;
				itemDef.colourRedefine = 96000;
				itemDef.modelZoom = ItemDef.forID(9018).modelZoom;
				itemDef.actions = new String[] { null, null, null, null, "Drop" };
				break;

			case 12423:
				itemDef.name = "@mag@Extreme Donator";
				itemDef.actions = new String[] { null, null, "Claim", null, "Drop" };
				itemDef.modelID = ItemDef.forID(12421).modelID;
				break;
			case 12424:
				itemDef.name = "@yel@Legendary Donator";
				itemDef.actions = new String[] { null, null, "Claim", null, "Drop" };
				itemDef.modelID = ItemDef.forID(12421).modelID;
				break;
			case 12425:
				itemDef.name = "@cya@Uber Donator";
				itemDef.actions = new String[] { null, null, "Claim", null, "Drop" };
				itemDef.modelID = ItemDef.forID(12421).modelID;
				break;
			case 20985:
				itemDef.name = "@blu@Deluxe @mag@Donator";
				itemDef.actions = new String[] { null, null, "Claim", null, "Drop" };
				itemDef.modelID = ItemDef.forID(12421).modelID;
				itemDef.modelZoom = 945;
				itemDef.rotationY = 345;
				itemDef.rotationX = 0;
				break;

			case 21077:
				itemDef.name = "Infernal M16 mh";
				itemDef.modelID = 94411;
				itemDef.femaleEquip1 = 94412;
				itemDef.maleEquip1 = 94412;
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 21076:
				itemDef.name = "Nude M16 mh";
				itemDef.modelID = 94411;
				itemDef.femaleEquip1 = 94412;
				itemDef.maleEquip1 = 94412;
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 21078:
				itemDef.name = "Infernal M16 oh";
				itemDef.modelID = 94411;
				itemDef.femaleEquip1 = 94413;
				itemDef.maleEquip1 = 94413;
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 21079:
				itemDef.name = "@red@Blood sniper";
				itemDef.modelID = 91811;
				itemDef.femaleEquip1 = 91812;
				itemDef.editedModelColor = new int[4];
				itemDef.newModelColor = new int[4];
				itemDef.editedModelColor[0] = 40;
				itemDef.newModelColor[0] = 52;
				itemDef.editedModelColor[1] = 50;
				itemDef.newModelColor[1] = 52;
				itemDef.editedModelColor[2] = 51;
				itemDef.newModelColor[2] = 52;
				itemDef.editedModelColor[3] = 24;
				itemDef.newModelColor[3] = 52;
				itemDef.maleEquip1 = 91812;
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;

			case 21069:
				itemDef.name = "Seers boots";
				itemDef.modelID = 94293;
				itemDef.femaleEquip1 = 94293;
				itemDef.maleEquip1 = 94293;
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 21068:
				itemDef.name = "Archers boots";
				itemDef.modelID = 94294;
				itemDef.femaleEquip1 = 94294;
				itemDef.maleEquip1 = 94294;
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 21067:
				itemDef.name = "Berserker boots";
				itemDef.modelID = 94292;
				itemDef.femaleEquip1 = 94292;
				itemDef.maleEquip1 = 94292;
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;

			case 3952:
				itemDef.modelID = 95083;
				itemDef.name = "Drygore Wings";
				itemDef.description = "It's a pair of drygore wings.";
				itemDef.modelZoom = 1519;
				itemDef.rotationY = 595;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 95084;
				itemDef.femaleEquip1 = 95084;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 3953:
				itemDef.modelID = 95085;
				itemDef.name = "Drygore Godsword";
				itemDef.description = "It's a drygore godsword.";
				itemDef.modelZoom = 1711;
				itemDef.rotationX = 1513;
				itemDef.rotationY = 354;
				itemDef.maleEquip1 = 95086;
				itemDef.femaleEquip1 = 95086;
				itemDef.actions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
			case 20873:
				itemDef.modelID = 75030;
				itemDef.name = "@mag@Dual Gem Sword @gre@10dr";
				itemDef.description = "It's a Dual Gem Sword!";
				itemDef.modelZoom = 1711;
				itemDef.rotationX = 1513;
				itemDef.rotationY = 354;
				itemDef.maleEquip1 = 75031;
				itemDef.femaleEquip1 = 75031;
				itemDef.actions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;
			case 20095:
				itemDef.colourRedefine2 = 51;
				itemDef.modelID = 95099;
				itemDef.name = "Trio full helmet";
				itemDef.description = "It's a purgatory full helmet.";
				itemDef.modelZoom = 1000;
				itemDef.rotationY = 595;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 95100;
				itemDef.femaleEquip1 = 95100;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 20096:
				itemDef.colourRedefine2 = 51;
				itemDef.modelID = 95101;
				itemDef.name = "Trio platebody";
				itemDef.description = "It's a purgatory platebody.";
				itemDef.modelZoom = 1400;
				itemDef.rotationY = 595;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 95102;
				itemDef.femaleEquip1 = 95102;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 20097:
				itemDef.colourRedefine2 = 51;
				itemDef.modelID = 95103;
				itemDef.name = "Trio platelegs";
				itemDef.description = "It's a pair of purgatory platelegs.";
				itemDef.modelZoom = 1500;
				itemDef.rotationY = 595;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 95104;
				itemDef.femaleEquip1 = 95104;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 20098:
				itemDef.colourRedefine2 = 51;
				itemDef.modelID = 95105;
				itemDef.name = "Trio boots";
				itemDef.description = "It's a pair of purgatory boots.";
				itemDef.modelZoom = 855;
				itemDef.rotationY = 215;
				itemDef.rotationX = 94;
				itemDef.modelOffset1 = 4;
				itemDef.modelOffsetY = -32;
				itemDef.maleEquip1 = 95105;
				itemDef.femaleEquip1 = 95105;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 20099:
				itemDef.colourRedefine2 = 51;
				itemDef.modelID = 95106;
				itemDef.name = "Trio gauntlets";
				itemDef.description = "It's a pair of purgatory gauntlets.";
				itemDef.modelZoom = 592;
				itemDef.rotationY = 323;
				itemDef.rotationX = 1710;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 5;
				itemDef.maleEquip1 = 95107;
				itemDef.femaleEquip1 = 95107;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 20100:
				itemDef.colourRedefine2 = 51;
				itemDef.modelID = 95108;
				itemDef.name = "Trio wings";
				itemDef.description = "It's a pair of purgatory wings.";
				itemDef.modelZoom = 1519;
				itemDef.rotationY = 595;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 95109;
				itemDef.femaleEquip1 = 95109;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 20101:
				itemDef.colourRedefine2 = 51;
				itemDef.modelID = 95110;
				itemDef.name = "Purgatory longsword";
				itemDef.description = "It's a purgatory longsword.";
				itemDef.modelZoom = 1519;
				itemDef.rotationY = 595;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 95111;
				itemDef.femaleEquip1 = 95111;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				break;

			case 20102:
				itemDef.modelID = 94528;
				itemDef.name = "@or3@Deadly Dragonslayer blade";
				itemDef.description = "It's a Camouflage whip.";
				itemDef.modelZoom = 900;
				itemDef.rotationY = 324;
				itemDef.rotationX = 1808;
				itemDef.modelOffset1 = -2;
				itemDef.modelOffsetY = 3;
				itemDef.maleEquip1 = 94529;
				itemDef.femaleEquip1 = 94529;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20103:
				itemDef.modelID = 80344;
				itemDef.name = "Deadly Dragonslayer platelegs";
				itemDef.description = "It's a set of camouflage torva platelegs.";
				itemDef.modelZoom = 1740;
				itemDef.rotationY = 474;
				itemDef.rotationX = 2045;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 80345;
				itemDef.femaleEquip1 = 80345;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20104:
				itemDef.modelID = 80340;
				itemDef.name = "Deadly Dragonslayer fullhelm";
				itemDef.description = "It's a camouflage torva fullhelm.";
				itemDef.modelZoom = 676;
				itemDef.rotationY = 0;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = -14;
				itemDef.maleEquip1 = 80341;
				itemDef.femaleEquip1 = 80341;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20105:
				itemDef.modelID = 80342;
				itemDef.name = "Deadly Dragonslayer platebody";
				itemDef.description = "It's a camouflage torva platebody.";
				itemDef.modelOffset1 = 1;
				itemDef.modelOffsetY = -8;
				itemDef.modelZoom = 1513;
				itemDef.rotationY = 566;
				itemDef.rotationX = 0;
				itemDef.maleEquip1 = 80343;
				itemDef.femaleEquip1 = 80343;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;

			case 20106:
				itemDef.name = "Deadly Dragonslayer gloves";
				itemDef.description = "It's a pair of camouflage gloves.";
				itemDef.modelZoom = 592;
				itemDef.rotationY = 323;
				itemDef.rotationX = 1710;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 5;
				itemDef.modelID = 49;
				itemDef.maleEquip1 = 50;
				itemDef.femaleEquip1 = 50;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20107:
				itemDef.name = "Deadly Dragonslayer boots";
				itemDef.description = "It's a pair of camouflage boots.";
				itemDef.modelID = 51;
				itemDef.modelZoom = 855;
				itemDef.rotationY = 215;
				itemDef.rotationX = 94;
				itemDef.modelOffset1 = 4;
				itemDef.modelOffsetY = -32;
				itemDef.maleEquip1 = 52;
				itemDef.femaleEquip1 = 52;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.description = "Some boots.";
				break;


			case 19906:
				itemDef.modelID = 95098;
				itemDef.name = "Bullets";
				itemDef.description = "Bullets";
				itemDef.modelZoom = 1500;
				itemDef.modelOffsetY = 5;
				itemDef.modelOffset1 = 3;
				itemDef.rotationX = 117;
				itemDef.rotationY = 477;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				itemDef.stackable = true;
				break;

			case 19904:
				itemDef.modelID = 95098;
				itemDef.name = "Infernal Bullets";
				itemDef.description = "Bullets";
				itemDef.modelZoom = 1500;
				itemDef.modelOffsetY = 5;
				itemDef.modelOffset1 = 3;
				itemDef.rotationX = 117;
				itemDef.rotationY = 477;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				itemDef.stackable = true;
				break;
			case 19903:
				itemDef.modelID = 95098;
				itemDef.name = "Ice Bullets";
				itemDef.description = "Bullets";
				itemDef.modelZoom = 1500;
				itemDef.modelOffsetY = 5;
				itemDef.modelOffset1 = 3;
				itemDef.rotationX = 117;
				itemDef.rotationY = 477;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				itemDef.stackable = true;
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
				itemDef.newModelColor = new int[] { 54194 };
				itemDef.editedModelColor = new int[] { 8128 };
				break;
			case 2381:
				itemDef.name = "Equilibrium Essence";
				itemDef.description = "A strange essence from the wilderness";
				itemDef.stackable = true;
				break;
			// Tkaar armor
			case 15662:
				itemDef.modelID = 91105;
				itemDef.name = "@mag@Mario @yel@Mask @gre@2dr";
				itemDef.description = "This is Mario's mask!";
				itemDef.modelZoom = 1579;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetX = 0;
				itemDef.rotationX = 2030;
				itemDef.rotationY = 333;
				itemDef.maleEquip1 = 91105;
				itemDef.femaleEquip1 = 91105;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 15663:
				itemDef.modelID = 90320;
				itemDef.name = "@mag@Body of human";
				itemDef.description = "This is body of a human";
				itemDef.modelZoom = 1828;
				itemDef.rotationY = 539;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = -1;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 90320;
				itemDef.femaleEquip1 = 90320;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20999:
				itemDef.modelID = 94431;
				itemDef.name = "@mag@Infernal @cya@Virtus @red@Gloves @gre@10dr";
				itemDef.description = "Best ancient mage's Gloves.";
				itemDef.modelZoom = 648;
				itemDef.rotationY = 618;
				itemDef.rotationX = 1143;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 94432;
				itemDef.femaleEquip1 = 94432;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {51,60,40};
				itemDef.newModelColor = new int[]    {60,60,60};
				break;
			case 15664:
				itemDef.modelID = 90061;
				itemDef.name = "@mag@Raids @yel@Cape @gre@12dr";
				itemDef.description = "This is a Raids Cape!";
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 1;
				itemDef.stackable = false;
				itemDef.modelZoom = 2000;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.rotationX = 0;
				itemDef.rotationY = 0;
				itemDef.maleEquip1 = 90061;
				itemDef.femaleEquip1 = 90061;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 15665:
				itemDef.modelID = 90310;
				itemDef.name = "@mag@Raids @yel@Claw @gre@2dr";
				itemDef.description = "This is a Vorago Claw!";
				itemDef.modelZoom = 1645;
				itemDef.rotationX = 377;
				itemDef.rotationY = 444;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 90310;
				itemDef.femaleEquip1 = 90310;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;

			case 15666:
				itemDef.modelID = 82998;
				itemDef.name = "@red@Shield";
				itemDef.description = "This is a Raids Shield!";
				itemDef.modelZoom = 1645;
				itemDef.rotationX = 377;
				itemDef.rotationY = 444;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 82999;
				itemDef.femaleEquip1 = 82999;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "@mag@Raids";
				itemDef.actions[4] = "Drop";
				break;

			case 15667:
				itemDef.modelID = 91101;
				itemDef.name = "@mag@Luigi @yel@Mask @gre@4dr";
				itemDef.description = "This is Luigi's mask!";
				itemDef.modelZoom = 1579;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetX = 0;
				itemDef.rotationX = 2030;
				itemDef.rotationY = 333;
				itemDef.maleEquip1 = 91101;
				itemDef.femaleEquip1 = 91101;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 15668:
				itemDef.modelID = 16805;
				itemDef.name = "@mag@Raids @yel@Hammer";
				itemDef.description = "Carved from the exo-skeleton of Jad itself";
				itemDef.modelZoom = 2500;
				itemDef.rotationY = 40;
				itemDef.rotationX = 933;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -3;
				itemDef.maleEquip1 = 16805;
				itemDef.femaleEquip1 = 16805;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "@mag@Huge";
				itemDef.actions[4] = "Drop";
				break;
			//End tkarr armor

			case 3642:
				itemDef.modelID = 54253;
				itemDef.name = "Draconian kite Shield";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 2434;
				itemDef.rotationY = 458;
				itemDef.rotationX = 0;
				itemDef.colourRedefine2 = 256782;
				itemDef.modelOffset1 = -3;
				itemDef.modelOffsetY = 9;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55991;
				itemDef.femaleEquip1 = 55991;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 3643:
				itemDef.modelID = 54126;
				itemDef.name = "Draconian platebody";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1447;
				itemDef.rotationY = 485;
				itemDef.rotationX = 0;
				itemDef.colourRedefine2 = 256782;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 13;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55851;
				itemDef.femaleEquip1 = 55851;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 3644:
				itemDef.modelID = 54175;
				itemDef.name = "Draconian platelegs";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1730;
				itemDef.rotationY = 525;
				itemDef.rotationX = 0;
				itemDef.colourRedefine2 = 256782;
				itemDef.modelOffset1 = 7;
				itemDef.modelOffsetY = 3;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55815;
				itemDef.femaleEquip1 = 56478;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 919:
				itemDef.modelID = 95050;
				itemDef.name = "Ironman platebody";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1730;
				itemDef.rotationY = 525;
				itemDef.rotationX = 0;
				//	itemDef.colourRedefine2 = 256782;
				itemDef.modelOffset1 = 7;
				itemDef.modelOffsetY = 3;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 95050;
				itemDef.femaleEquip1 = 95050;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 920:
				itemDef.modelID = 95048;
				itemDef.name = "Ironman platelegs";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1447;
				itemDef.rotationY = 485;
				itemDef.rotationX = 0;
				//itemDef.colourRedefine2 = 256782;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 13;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 95048;
				itemDef.femaleEquip1 = 95048;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 3645:
				itemDef.modelID = 54164;
				itemDef.name = "Draconian full helm";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 921;
				itemDef.rotationY = 121;
				itemDef.rotationX = 0;
				itemDef.colourRedefine2 = 256782;
				//cool primal color = 277782, 286782
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -4;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55770;
				itemDef.femaleEquip1 = 56434;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 3646:
				itemDef.modelID = 54055;
				itemDef.name = "Draconian chainbody";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1447;
				itemDef.rotationY = 485;
				itemDef.rotationX = 0;
				itemDef.colourRedefine2 = 256782;
				//cool primal color = 277782, 286782
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 8;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55830;
				itemDef.femaleEquip1 = 56518;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 3647:
				itemDef.modelID = 54062;
				itemDef.name = "Draconian boots";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 789;
				itemDef.rotationY = 164;
				itemDef.rotationX = 156;
				itemDef.colourRedefine2 = 256782;
				//cool primal color = 277782, 286782
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -8;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55673;
				itemDef.femaleEquip1 = 56353;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 3648:
				itemDef.modelID = 54037;
				itemDef.name = "Draconian gloves";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 930;
				itemDef.rotationY = 420;
				itemDef.rotationX = 828;
				itemDef.colourRedefine2 = 256782;
				//cool primal color = 277782, 286782
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = -7;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55728;
				itemDef.femaleEquip1 = 56417;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 3649:
				itemDef.modelID = 54437;
				itemDef.name = "Draconian longsword";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1700;
				itemDef.rotationY = 498;
				itemDef.rotationX = 1300;
				itemDef.modelOffset1 = -1;
				itemDef.modelOffsetY = -1;
				itemDef.colourRedefine2 = 261782;
				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 56100;
				itemDef.femaleEquip1 = 56163;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;


			case 3650:
				itemDef.modelID = 54425;
				itemDef.name = "Draconian shortbow";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1447;
				itemDef.rotationY = 296;
				itemDef.rotationX = 377;
				itemDef.modelOffset1 = 5;
				itemDef.modelOffsetY = -33;
				itemDef.colourRedefine2 = 277782;
				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 56287;
				itemDef.femaleEquip1 = 56287;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 3651:
				itemDef.modelID = 94424;
				itemDef.name = "@mag@Shadowlord full helm";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 672;
				itemDef.rotationY = 85;
				itemDef.rotationX = 1867;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -3;
				itemDef.maleEquip1 = 94425;
				itemDef.femaleEquip1 = 94425;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.maleDialogue = 62729;
				itemDef.femaleDialogue = 62729;
				itemDef.editedModelColor = new int[] {51,60};
				itemDef.newModelColor = new int[]    {55,55};
				break;
			case 3652:
				itemDef.modelID = 94426;
				itemDef.name = "@mag@Shadowlord platebody";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 1506;
				itemDef.rotationY = 473;
				itemDef.rotationX = 2042;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 94427;
				itemDef.femaleEquip1 = 94427;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {51,60};
				itemDef.newModelColor = new int[]    {55,55};
				break;

			case 3653:
				itemDef.modelID = 94428;
				itemDef.name = "@mag@Shadowlord platelegs";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 1740;
				itemDef.rotationY = 474;
				itemDef.rotationX = 2045;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 94429;
				itemDef.femaleEquip1 = 94429;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {51,60};
				itemDef.newModelColor = new int[]    {55,55};
				break;

			case 20511:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.name = "@mag@Magma protector";
				itemDef.description = "An item imbued with infernal essence.";
				itemDef.modelID = 95042;
				itemDef.modelZoom = 1560;
				itemDef.rotationY = 344;
				itemDef.rotationX = 1104;
				itemDef.modelOffsetX = 0;
				itemDef.modelOffset1 = -6;
				itemDef.modelOffsetY = -14;
				itemDef.maleEquip1 = 95043;
				itemDef.femaleEquip1 = 95043;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.editedModelColor = new int[] {51,60,40,31};
				itemDef.newModelColor = new int[]    {55,55,55,55};
				break;
			case 3654:
				itemDef.modelID = 94430;
				itemDef.name = "@mag@Shadowlord boots";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 825;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetY = 0;
				itemDef.rotationY = 400;
				itemDef.maleEquip1 = 94430;
				itemDef.femaleEquip1 = 944303;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {51,60};
				itemDef.newModelColor = new int[]    {55,55};
				break;

			case 3655:
				itemDef.modelID = 94431;
				itemDef.name = "@mag@Shadowlord gloves";
				itemDef.description = "I can almost feel the Dark lord's soul within";
				itemDef.modelZoom = 648;
				itemDef.rotationY = 618;
				itemDef.rotationX = 1143;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 94432;
				itemDef.femaleEquip1 = 94432;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {51,60};
				itemDef.newModelColor = new int[]    {55,55};
				break;
			case 20983:
				itemDef.modelID = 94430;
				itemDef.name = "@mag@Doflamingo @cya@Torva @red@Boots @gre@10dr";
				itemDef.description = "Torva platebody imbued with Doflamingo";
				itemDef.modelZoom = 825;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetY = 0;
				itemDef.rotationY = 400;
				itemDef.maleEquip1 = 94430;
				itemDef.femaleEquip1 = 944303;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {51,60,40};
				itemDef.newModelColor = new int[]    {69,69,69};
				break;
			case 20984:
				itemDef.modelID = 94431;
				itemDef.name = "@mag@Doflamingo @cya@Torva @red@Gloves @gre@10dr";
				itemDef.description = "Torva platebody imbued with Doflamingo";
				itemDef.modelZoom = 648;
				itemDef.rotationY = 618;
				itemDef.rotationX = 1143;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 94432;
				itemDef.femaleEquip1 = 94432;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {51,60,40};
				itemDef.newModelColor = new int[]    {69,69,69};
				break;

			case 3656:
				itemDef.modelID = 54093;
				itemDef.name = "Draconian hood";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1025;
				itemDef.rotationY = 215;
				itemDef.rotationX = 162;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = -43;
				itemDef.colourRedefine2 = 298782;
				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55746;
				itemDef.femaleEquip1 = 56453;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 3657:
				itemDef.modelID = 54097;
				itemDef.name = "Draconian robe top";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1447;
				itemDef.rotationY = 485;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 13;
				itemDef.colourRedefine2 = 298782;
				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55659;
				itemDef.femaleEquip1 = 56327;
				itemDef.femaleEquip2 = 56530;
				itemDef.maleEquip2 = 55893;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 3658:
				itemDef.modelID = 54009;
				itemDef.name = "Draconian robe bottom";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1974;
				itemDef.rotationY = 377;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 16;
				itemDef.colourRedefine2 = 298782;
				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55785;
				itemDef.femaleEquip1 = 56466;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 3659:
				itemDef.modelID = 54177;
				itemDef.name = "Draconian shoes";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 770;
				itemDef.rotationY = 189;
				itemDef.rotationX = 1988;
				itemDef.modelOffset1 = -1;
				itemDef.modelOffsetY = -22;
				itemDef.colourRedefine2 = 298782;
				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55681;
				itemDef.femaleEquip1 = 56367;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 3660:
				itemDef.modelID = 54121;
				itemDef.name = "Draconian mage gloves";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 830;
				itemDef.rotationY = 536;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 3;
				itemDef.colourRedefine2 = 298782;
				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55740;
				itemDef.femaleEquip1 = 56408;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 3661:
				itemDef.modelID = 54391;
				itemDef.name = "Grim Staff";
				itemDef.description = "It's a Grim Staff.";
				itemDef.modelZoom = 1650;
				itemDef.rotationY = 391;
				itemDef.rotationX = 391;
				itemDef.modelOffset1 = 1;
				itemDef.modelOffsetY = -5;
				itemDef.colourRedefine3 = 180000;
				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55909;
				itemDef.femaleEquip1 = 55909;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 20988:
				itemDef.modelID = 54391;
				itemDef.name = "@blu@Angelic staff";
				itemDef.description = "It's a Angelic staff.";
				itemDef.modelZoom = 1650;
				itemDef.rotationY = 391;
				itemDef.rotationX = 391;
				itemDef.modelOffset1 = 1;
				itemDef.modelOffsetY = -5;
				itemDef.colourRedefine2 = 920000;
				//rapier : sky : 62111,
				//itemDef.aByte154 = -14;
				//cool primal color = 277782, 286782
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 55909;
				itemDef.femaleEquip1 = 55909;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 3625:
				itemDef.modelID = 54425;
				itemDef.name = "Kryptic bow";
				itemDef.description = "A bow from the deepest dungeon";
				itemDef.modelZoom = 1447;
				itemDef.rotationY = 296;
				itemDef.rotationX = 377;
				itemDef.colourRedefine2 = 158211;
				itemDef.modelOffset1 = 5;
				itemDef.modelOffsetY = -33;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 56287;
				itemDef.femaleEquip1 = 56287;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 3626:
				itemDef.modelID = 57653;
				itemDef.name = "Prestigious full helm";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 640;
				itemDef.rotationY = 108;
				itemDef.rotationX = 156;
				itemDef.colourRedefine2 = 298782;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -1;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 4425;
				itemDef.femaleEquip1 = 28930;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 3627:
				itemDef.modelID = 57622;
				itemDef.name = "Prestigious platebody";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1378;
				itemDef.rotationY = 527;
				itemDef.rotationX = 0;
				itemDef.colourRedefine2 = 298782;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -1;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 4435;
				itemDef.femaleEquip1 = 38208;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 3628:
				itemDef.modelID = 57633;
				itemDef.name = "Prestigious platelegs";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1740;
				itemDef.rotationY = 516;
				itemDef.rotationX = 0;
				itemDef.colourRedefine2 = 298782;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 4428;
				itemDef.femaleEquip1 = 38192;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 3629:
				itemDef.modelID = 57625;
				itemDef.name = "Prestigious kiteshield";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 1432;
				itemDef.rotationY = 281;
				itemDef.rotationX = 1959;
				itemDef.colourRedefine2 = 298782;
				itemDef.modelOffset1 = 1;
				itemDef.modelOffsetY = -1;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 57673;
				itemDef.femaleEquip1 = 57673;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 3631:
				itemDef.modelID = 57638;
				itemDef.name = "Prestigious cape";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 2012;
				itemDef.rotationY = 307;
				itemDef.rotationX = 978;
				itemDef.colourRedefine2 = 298782;
				itemDef.modelOffset1 = -1;
				itemDef.modelOffsetY = 0;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 4437;
				itemDef.femaleEquip1 = 38259;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 3632:
				itemDef.modelID = 57632;
				itemDef.name = "Prestigious cape";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 2012;
				itemDef.rotationY = 307;
				itemDef.rotationX = 978;
				itemDef.colourRedefine2 = 298782;
				itemDef.modelOffset1 = -1;
				itemDef.modelOffsetY = 0;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 4441;
				itemDef.femaleEquip1 = 38256;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 3633:
				itemDef.modelID = 16015;
				itemDef.name = "Prestigious boots";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 784;
				itemDef.rotationY = 147;
				itemDef.rotationX = 1841;
				itemDef.colourRedefine2 = 298782;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -4;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 16010;
				itemDef.femaleEquip1 = 16012;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

			case 3630:
				itemDef.modelID = 33165;
				itemDef.name = "Prestigious gloves";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 930;
				itemDef.rotationY = 420;
				itemDef.rotationX = 828;
				itemDef.colourRedefine2 = 298782;
				itemDef.modelOffset1 = -7;
				itemDef.modelOffsetY = 1;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 33207;
				itemDef.femaleEquip1 = 33259;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;

		/*case 7809:
			itemDef.name = "Prestigious spear";
			itemDef.modifiedModelColors = new int[7];
    		itemDef.originalModelColors = new int[7];
    		itemDef.modifiedModelColors[0] = 920; // Signe
    		itemDef.originalModelColors[0] = 1;
			break;

		case 7808:
			itemDef.name = "Prestigious mace";
			itemDef.modifiedModelColors = new int[7];
    		itemDef.originalModelColors = new int[7];
    		itemDef.modifiedModelColors[0] = 920; // Signe
    		itemDef.originalModelColors[0] = 1;
			break;
		case 7807:
			itemDef.name = "Prestigious battleaxe";
			itemDef.modifiedModelColors = new int[7];
    		itemDef.originalModelColors = new int[7];
    		itemDef.modifiedModelColors[0] = 920; // Signe
    		itemDef.originalModelColors[0] = 1;
			break;
		case 7806:
			itemDef.name = "Prestigious sword";
    		itemDef.modifiedModelColors = new int[7];
    		itemDef.originalModelColors = new int[7];
    		itemDef.modifiedModelColors[0] = 920; // Signe
    		itemDef.originalModelColors[0] = 1;
			break;*/


			case 3634:
				itemDef.modelID = 4934;
				itemDef.name = "Prestigious amulet";
				itemDef.description = "A custom item.";
				itemDef.modelZoom = 440;
				itemDef.rotationY = 172;
				itemDef.rotationX = 72;
				itemDef.colourRedefine2 = 298782;
				itemDef.modelOffset1 = 2;
				itemDef.modelOffsetY = -18;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 20858;
				itemDef.femaleEquip1 = 20864;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 3682:
				itemDef.modelID = 90999;
				itemDef.name = "Lava necklace";
				itemDef.description = "A necklace imbued with lava.";
				itemDef.modelZoom = 440;
				itemDef.rotationY = 172;
				itemDef.rotationX = 72;
				itemDef.modelOffset1 = 2;
				itemDef.modelOffsetY = -18;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 90998;
				itemDef.femaleEquip1 = 90998;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				break;
			case 20251:
				itemDef.modelID = 90999;
				itemDef.name = "Beginner Dragon necklace";
				itemDef.description = "A necklace imbued with lava.";
				itemDef.modelZoom = 440;
				itemDef.rotationY = 172;
				itemDef.rotationX = 72;
				itemDef.modelOffset1 = 2;
				itemDef.modelOffsetY = -18;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 90998;
				itemDef.femaleEquip1 = 90998;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {40,31};
				itemDef.newModelColor = new int[]    {57,57};
				break;
			case 20252:
				itemDef.modelID = 90999;
				itemDef.name = "Predator necklace";
				itemDef.description = "A necklace imbued with lava.";
				itemDef.modelZoom = 440;
				itemDef.rotationY = 172;
				itemDef.rotationX = 72;
				itemDef.modelOffset1 = 2;
				itemDef.modelOffsetY = -18;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 90998;
				itemDef.femaleEquip1 = 90998;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {40,31};
				itemDef.newModelColor = new int[]    {55,55};
				break;
			case 20253:
				itemDef.modelID = 90999;
				itemDef.name = "Dragonslayer necklace";
				itemDef.description = "A necklace imbued with lava.";
				itemDef.modelZoom = 440;
				itemDef.rotationY = 172;
				itemDef.rotationX = 72;
				itemDef.modelOffset1 = 2;
				itemDef.modelOffsetY = -18;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 90998;
				itemDef.femaleEquip1 = 90998;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {40,31};
				itemDef.newModelColor = new int[]    {66,66};
				break;
			case 20254:
				itemDef.modelID = 90999;
				itemDef.name = "Donkey Kong necklace";
				itemDef.description = "A necklace imbued with lava.";
				itemDef.modelZoom = 440;
				itemDef.rotationY = 172;
				itemDef.rotationX = 72;
				itemDef.modelOffset1 = 2;
				itemDef.modelOffsetY = -18;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 90998;
				itemDef.femaleEquip1 = 90998;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {40,31};
				itemDef.newModelColor = new int[]    {66,66};
				break;
			case 20255:
				itemDef.modelID = 90999;
				itemDef.name = "Oblivion necklace";
				itemDef.description = "A necklace imbued with lava.";
				itemDef.modelZoom = 440;
				itemDef.rotationY = 172;
				itemDef.rotationX = 72;
				itemDef.modelOffset1 = 2;
				itemDef.modelOffsetY = -18;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 90998;
				itemDef.femaleEquip1 = 90998;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {40,31};
				itemDef.newModelColor = new int[]    {56,56};
				break;
			case 20256:
				itemDef.modelID = 90999;
				itemDef.name = "Trio necklace";
				itemDef.description = "A necklace imbued with lava.";
				itemDef.modelZoom = 440;
				itemDef.rotationY = 172;
				itemDef.rotationX = 72;
				itemDef.modelOffset1 = 2;
				itemDef.modelOffsetY = -18;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 90998;
				itemDef.femaleEquip1 = 90998;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {40,31};
				itemDef.newModelColor = new int[]    {69,69};
				break;
			case 20257:
				itemDef.modelID = 90999;
				itemDef.name = "Blood necklace";
				itemDef.description = "A necklace imbued with lava.";
				itemDef.modelZoom = 440;
				itemDef.rotationY = 172;
				itemDef.rotationX = 72;
				itemDef.modelOffset1 = 2;
				itemDef.modelOffsetY = -18;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 90998;
				itemDef.femaleEquip1 = 90998;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {40,31};
				itemDef.newModelColor = new int[]    {52,52};
				break;
			case 20258:
				itemDef.modelID = 90999;
				itemDef.name = "Infernal necklace";
				itemDef.description = "A necklace imbued with lava.";
				itemDef.modelZoom = 440;
				itemDef.rotationY = 172;
				itemDef.rotationX = 72;
				itemDef.modelOffset1 = 2;
				itemDef.modelOffsetY = -18;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 90998;
				itemDef.femaleEquip1 = 90998;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.stackable = false;
				itemDef.editedModelColor = new int[] {40,31};
				itemDef.newModelColor = new int[]    {60,60};
				break;
			/** OSRS && NEW ITEMS **/
			case 18880:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.modelID = 90558;
				itemDef.name = "Ancestral hat";
				itemDef.modelZoom = 1236;
				itemDef.rotationY = 118;
				itemDef.rotationX = 10;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -12;
				itemDef.femaleEquip1 = 90559;
				itemDef.maleEquip1 = 90559;
				itemDef.description = "The hat of a powerful sorceress from a bygone era.";
				break;
			case 18881:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.modelID = 90561;
				itemDef.name = "Ancestral robe top";
				itemDef.modelZoom = 1358;
				itemDef.rotationY = 514;
				itemDef.rotationX = 2041;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -3;
				itemDef.femaleEquip1 = 90562;
				itemDef.maleEquip1 = 90563;
				itemDef.maleEquip2 = 90564; // male arms
				itemDef.femaleEquip2 = 90565; // female arms
				itemDef.description = "The robe top of a powerful sorceress from a bygone era.";
				break;
			case 18882:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.modelID = 90566;
				itemDef.name = "Ancestral robe bottom";
				itemDef.modelZoom = 1690;
				itemDef.rotationY = 435;
				itemDef.rotationX = 9;
				itemDef.modelOffset1 = 1;
				itemDef.modelOffsetY = 7;
				itemDef.femaleEquip1 = 90567;
				itemDef.maleEquip1 = 90568;
				itemDef.description = "The robe bottom of a powerful sorceress from a bygone era.";
				break;
			case 20061:
				itemDef.modelID = 10247;
				itemDef.name = "Abyssal vine whip";
				itemDef.description = "Abyssal vine whip";
				itemDef.modelZoom = 848;
				itemDef.rotationY = 324;
				itemDef.rotationX = 1808;
				itemDef.modelOffset1 = 5;
				itemDef.modelOffsetY = 38;
				itemDef.maleEquip1 = 10253;
				itemDef.femaleEquip1 = 10253;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 20010:
				itemDef.modelID = 80342;
				itemDef.name = "Shadow Dragonslayer platebody";
				itemDef.description = "It's a camouflage torva platebody.";
				itemDef.modelOffset1 = 1;
				itemDef.modelOffsetY = -8;
				itemDef.modelZoom = 1513;
				itemDef.rotationY = 566;
				itemDef.rotationX = 0;
				itemDef.maleEquip1 = 80343;
				itemDef.femaleEquip1 = 80343;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20011:
				itemDef.modelID = 80344;
				itemDef.name = "Shadow Dragonslayer platelegs";
				itemDef.description = "It's a set of camouflage torva platelegs.";
				itemDef.modelZoom = 1740;
				itemDef.rotationY = 474;
				itemDef.rotationX = 2045;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 80345;
				itemDef.femaleEquip1 = 80345;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20012:
				itemDef.modelID = 80340;
				itemDef.name = "Shadow Dragonslayer fullhelm";
				itemDef.description = "It's a camouflage torva fullhelm.";
				itemDef.modelZoom = 676;
				itemDef.rotationY = 0;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = -14;
				itemDef.maleEquip1 = 80341;
				itemDef.femaleEquip1 = 80341;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20013:
				itemDef.modelID = 44633;
				itemDef.name = "Vanguard helm";
				itemDef.modelZoom = 855;
				itemDef.rotationY = 1966;
				itemDef.rotationX = 5;
				itemDef.modelOffsetY = 4;
				itemDef.modelOffset1 = -1;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.maleEquip1 = 44769;
				itemDef.femaleEquip1 = 44769;
				break;
			case 20014:
				itemDef.modelID = 44627;
				itemDef.name = "Vanguard body";
				itemDef.modelZoom = 1513;
				itemDef.rotationX = 2041;
				itemDef.rotationY = 593;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = -11;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.maleEquip1 = 44812;
				itemDef.femaleEquip1 = 44812;
				break;
			case 14062:
				itemDef.modelID = 50011;
				itemDef.name = "Vanguard legs";
				itemDef.modelZoom = 1711;
				itemDef.rotationX = 0;
				itemDef.rotationY = 360;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = -11;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.maleEquip1 = 50010;
				itemDef.femaleEquip1 = 44771;
				break;
			case 20016:
				itemDef.modelID = 44704;
				itemDef.name = "Battle-mage helm";
				itemDef.modelZoom = 658;
				itemDef.rotationX = 1898;
				itemDef.rotationY = 2;
				itemDef.modelOffset1 = 12;
				itemDef.modelOffsetY = 3;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.maleEquip1 = 44767;
				itemDef.femaleEquip1 = 44767;
				break;
			case 20017:
				itemDef.modelID = 44631;
				itemDef.name = "Battle-mage robe";
				itemDef.modelZoom = 1382;
				itemDef.rotationX = 3;
				itemDef.rotationY = 488;
				itemDef.modelOffset1 = 1;
				itemDef.modelOffsetY = 0;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.maleEquip1 = 44818;
				itemDef.femaleEquip1 = 44818;
				break;
			case 6307:
				ItemDef itemDef23 = ItemDef.forID(299);
				itemDef.modelID = itemDef23.modelID;
				itemDef.name = "Rg Seeds";
				itemDef.modelZoom = itemDef23.modelZoom;
				itemDef.rotationX = itemDef23.rotationX;
				itemDef.rotationY = itemDef23.rotationY;
				itemDef.modelOffset1 = itemDef23.modelOffset1;
				itemDef.modelOffsetY = itemDef23.modelOffsetY;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[5];
				itemDef.actions[0] = "Plant";
				break;
			case 20018:
				itemDef.modelID = 44672;
				itemDef.name = "Battle-mage robe legs";
				itemDef.modelZoom = 1842;
				itemDef.rotationX = 1024;
				itemDef.rotationY = 498;
				itemDef.modelOffset1 = 4;
				itemDef.modelOffsetY = -1;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.maleEquip1 = 44775;
				itemDef.femaleEquip1 = 44775;
				break;
			// case 1685:
			//  itemDef.modelID = 83126;
			//  itemDef.name = "Dragon boots [1]";
			//  itemDef.modelZoom = 848;
			//   itemDef.rotationY = 141;
			//    itemDef.rotationX = 141;
			//     itemDef.modelOffset1 = -9;
			//     itemDef.modelOffsetY = 0;
			//      itemDef.groundActions = new String[] { null, null, "Take", null, null };
			//     itemDef.actions = new String[5];
			//      itemDef.actions[1] = "Wear";
			//      itemDef.maleEquip1 = 83126;
			//      itemDef.femaleEquip1 = 83126;
			//      break;
			case 12861:
				itemDef.name = "Dire Wolf gloves";
				break;
			case 20073:
				itemDef.modelID = 72078;
				itemDef.name = "Zamorakian scimitar";
				itemDef.description = "Imbued with the Demon himself";
				itemDef.modelZoom = 1488;
				itemDef.rotationY = 525;
				itemDef.rotationX = 1075;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 72077;
				itemDef.femaleEquip1 = 70077;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 20019:
				itemDef.name = "Shadow Dragonslayer boots";
				itemDef.description = "It's a pair of camouflage boots.";
				itemDef.modelID = 51;
				itemDef.modelZoom = 855;
				itemDef.rotationY = 215;
				itemDef.rotationX = 94;
				itemDef.modelOffset1 = 4;
				itemDef.modelOffsetY = -32;
				itemDef.maleEquip1 = 52;
				itemDef.femaleEquip1 = 52;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.description = "Some boots.";
				break;
			case 20020:
				itemDef.name = "Shadow Dragonslayer gloves";
				itemDef.description = "It's a pair of camouflage gloves.";
				itemDef.modelZoom = 592;
				itemDef.rotationY = 323;
				itemDef.rotationX = 1710;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 5;
				itemDef.modelID = 49;
				itemDef.maleEquip1 = 50;
				itemDef.femaleEquip1 = 50;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20021:
				itemDef.modelID = 44662;
				itemDef.name = "Battle-mage boots";
				itemDef.modelZoom = 987;
				itemDef.rotationX= 1988;
				itemDef.rotationY = 188;
				itemDef.modelOffset1 = -8;
				itemDef.modelOffsetY = 5;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.maleEquip1 = 44755;
				itemDef.femaleEquip1 = 44755;
				break;
			case 20022:
				itemDef.modelID = 44573;
				itemDef.name = "Battle-mage gloves";
				itemDef.modelZoom = 1053;
				itemDef.rotationX = 0;
				itemDef.rotationY = 536;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 0;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.maleEquip1 = 44762;
				itemDef.femaleEquip1 = 44762;
				break;
			case 11554:
				itemDef.name = "Abyssal tentacle";
				itemDef.modelZoom = 840;
				itemDef.rotationY = 280;
				itemDef.rotationX = 121;
				itemDef.modelOffsetY = 56;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.modelID = 28439;
				itemDef.maleEquip1 = 45006;
				itemDef.femaleEquip1 = 43500;
				break;
			case 11926:
				itemDef.name = "Odium ward";
				itemDef.modelZoom = 1200;
				itemDef.rotationY = 568;
				itemDef.rotationX = 1836;
				itemDef.modelOffsetX = 2;
				itemDef.modelOffsetY = 3;
				itemDef.newModelColor = new int[] { 15252 };
				itemDef.editedModelColor = new int[] { 908 };
				itemDef.modelID = 9354;
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.maleEquip1 = 9347;
				itemDef.femaleEquip1 = 9347;
				break;
			case 20690:
				ItemDef dfs = ItemDef.forID(11283);
				itemDef.name = "Water DragonFire Shield";
				itemDef.modelZoom = dfs.modelZoom;
				itemDef.rotationY = dfs.rotationY;
				itemDef.rotationX = dfs.rotationX;
				itemDef.modelOffsetX = dfs.modelOffsetX;
				itemDef.modelOffsetY = dfs.modelOffsetY;
				itemDef.modelID = dfs.modelID;
				itemDef.maleEquip1 = dfs.maleEquip1;
				itemDef.femaleEquip1 = dfs.femaleEquip1;
				itemDef.colourRedefine2 = 34543;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				break;
			case 20691:
				dfs = ItemDef.forID(11283);
				itemDef.name = "Grass DragonFire Shield";
				itemDef.modelZoom = dfs.modelZoom;
				itemDef.rotationY = dfs.rotationY;
				itemDef.rotationX = dfs.rotationX;
				itemDef.modelOffsetX = dfs.modelOffsetX;
				itemDef.modelOffsetY = dfs.modelOffsetY;
				itemDef.modelID = dfs.modelID;
				itemDef.maleEquip1 = dfs.maleEquip1;
				itemDef.femaleEquip1 = dfs.femaleEquip1;
				itemDef.colourRedefine2 = 14500;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				break;
			case 20692:
				dfs = ItemDef.forID(11283);
				itemDef.name = "Fire DragonFire Shield";
				itemDef.modelZoom = dfs.modelZoom;
				itemDef.rotationY = dfs.rotationY;
				itemDef.rotationX = dfs.rotationX;
				itemDef.modelOffsetX = dfs.modelOffsetX;
				itemDef.modelOffsetY = dfs.modelOffsetY;
				itemDef.modelID = dfs.modelID;
				itemDef.maleEquip1 = dfs.maleEquip1;
				itemDef.femaleEquip1 = dfs.femaleEquip1;
				itemDef.colourRedefine2 = 850;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				break;
			case 20693:
				dfs = ItemDef.forID(11283);
				itemDef.name = "Dark DragonFire Shield";
				itemDef.modelZoom = dfs.modelZoom;
				itemDef.rotationY = dfs.rotationY;
				itemDef.rotationX = dfs.rotationX;
				itemDef.modelOffsetX = dfs.modelOffsetX;
				itemDef.modelOffsetY = dfs.modelOffsetY;
				itemDef.modelID = dfs.modelID;
				itemDef.maleEquip1 = dfs.maleEquip1;
				itemDef.femaleEquip1 = dfs.femaleEquip1;
				itemDef.colourRedefine2 = 907;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				break;

			case 20694:
				dfs = ItemDef.forID(11283);
				itemDef.name = "Earth DragonFire Shield";
				itemDef.modelZoom = dfs.modelZoom;
				itemDef.rotationY = dfs.rotationY;
				itemDef.rotationX = dfs.rotationX;
				itemDef.modelOffsetX = dfs.modelOffsetX;
				itemDef.modelOffsetY = dfs.modelOffsetY;
				itemDef.modelID = dfs.modelID;
				itemDef.maleEquip1 = dfs.maleEquip1;
				itemDef.femaleEquip1 = dfs.femaleEquip1;
				itemDef.colourRedefine2 = 74800;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				break;

			case 20675:
				dfs = ItemDef.forID(11283);
				itemDef.name = "Light DragonFire Shield";
				itemDef.modelZoom = dfs.modelZoom;
				itemDef.rotationY = dfs.rotationY;
				itemDef.rotationX = dfs.rotationX;
				itemDef.modelOffsetX = dfs.modelOffsetX;
				itemDef.modelOffsetY = dfs.modelOffsetY;
				itemDef.modelID = dfs.modelID;
				itemDef.maleEquip1 = dfs.maleEquip1;
				itemDef.femaleEquip1 = dfs.femaleEquip1;
				itemDef.colourRedefine2 = 74300;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				break;
			case 20696:
				dfs = ItemDef.forID(11283);
				itemDef.name = "Mythical DragonFire Shield";
				itemDef.modelZoom = dfs.modelZoom;
				itemDef.rotationY = dfs.rotationY;
				itemDef.rotationX = dfs.rotationX;
				itemDef.modelOffsetX = dfs.modelOffsetX;
				itemDef.modelOffsetY = dfs.modelOffsetY;
				itemDef.modelID = dfs.modelID;
				itemDef.maleEquip1 = dfs.maleEquip1;
				itemDef.femaleEquip1 = dfs.femaleEquip1;
				itemDef.colourRedefine2 = 4200;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				break;

			case 20390:
				dfs = ItemDef.forID(1540);
				itemDef.name = "Water Dragon-anti Shield";
				itemDef.modelZoom = dfs.modelZoom;
				itemDef.rotationY = dfs.rotationY;
				itemDef.rotationX = dfs.rotationX;
				itemDef.modelOffsetX = dfs.modelOffsetX;
				itemDef.modelOffsetY = dfs.modelOffsetY;
				itemDef.modelID = dfs.modelID;
				itemDef.maleEquip1 = dfs.maleEquip1;
				itemDef.femaleEquip1 = dfs.femaleEquip1;
				itemDef.colourRedefine2 = 34543;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				break;
			case 20391:
				dfs = ItemDef.forID(1540);
				itemDef.name = "Grass Dragon-anti Shield";
				itemDef.modelZoom = dfs.modelZoom;
				itemDef.rotationY = dfs.rotationY;
				itemDef.rotationX = dfs.rotationX;
				itemDef.modelOffsetX = dfs.modelOffsetX;
				itemDef.modelOffsetY = dfs.modelOffsetY;
				itemDef.modelID = dfs.modelID;
				itemDef.maleEquip1 = dfs.maleEquip1;
				itemDef.femaleEquip1 = dfs.femaleEquip1;
				itemDef.colourRedefine3 = 120000;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				break;
			case 20392:
				dfs = ItemDef.forID(1540);
				itemDef.name = "Fire Dragon-anti Shield";
				itemDef.modelZoom = dfs.modelZoom;
				itemDef.rotationY = dfs.rotationY;
				itemDef.rotationX = dfs.rotationX;
				itemDef.modelOffsetX = dfs.modelOffsetX;
				itemDef.modelOffsetY = dfs.modelOffsetY;
				itemDef.modelID = dfs.modelID;
				itemDef.maleEquip1 = dfs.maleEquip1;
				itemDef.femaleEquip1 = dfs.femaleEquip1;
				itemDef.colourRedefine2 = 850;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				break;
			case 20393:
				dfs = ItemDef.forID(1540);
				itemDef.name = "Dark Dragon-anti Shield";
				itemDef.modelZoom = dfs.modelZoom;
				itemDef.rotationY = dfs.rotationY;
				itemDef.rotationX = dfs.rotationX;
				itemDef.modelOffsetX = dfs.modelOffsetX;
				itemDef.modelOffsetY = dfs.modelOffsetY;
				itemDef.modelID = dfs.modelID;
				itemDef.maleEquip1 = dfs.maleEquip1;
				itemDef.femaleEquip1 = dfs.femaleEquip1;
				itemDef.colourRedefine2 = 915;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				break;

			case 20394:
				dfs = ItemDef.forID(1540);
				itemDef.name = "Earth Dragon-anti Shield";
				itemDef.modelZoom = dfs.modelZoom;
				itemDef.rotationY = dfs.rotationY;
				itemDef.rotationX = dfs.rotationX;
				itemDef.modelOffsetX = dfs.modelOffsetX;
				itemDef.modelOffsetY = dfs.modelOffsetY;
				itemDef.modelID = dfs.modelID;
				itemDef.maleEquip1 = dfs.maleEquip1;
				itemDef.femaleEquip1 = dfs.femaleEquip1;
				itemDef.colourRedefine2 = 4400;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				break;

			case 20395:
				dfs = ItemDef.forID(1540);
				itemDef.name = "Light Dragon-anti Shield";
				itemDef.modelZoom = dfs.modelZoom;
				itemDef.rotationY = dfs.rotationY;
				itemDef.rotationX = dfs.rotationX;
				itemDef.modelOffsetX = dfs.modelOffsetX;
				itemDef.modelOffsetY = dfs.modelOffsetY;
				itemDef.modelID = dfs.modelID;
				itemDef.maleEquip1 = dfs.maleEquip1;
				itemDef.femaleEquip1 = dfs.femaleEquip1;
				itemDef.colourRedefine2 = 74200;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				break;

			case 20396:
				dfs = ItemDef.forID(1540);
				itemDef.name = "Mythical Dragon-anti Shield";
				itemDef.modelZoom = dfs.modelZoom;
				itemDef.rotationY = dfs.rotationY;
				itemDef.rotationX = dfs.rotationX;
				itemDef.modelOffsetX = dfs.modelOffsetX;
				itemDef.modelOffsetY = dfs.modelOffsetY;
				itemDef.modelID = dfs.modelID;
				itemDef.maleEquip1 = dfs.maleEquip1;
				itemDef.femaleEquip1 = dfs.femaleEquip1;
				itemDef.colourRedefine2 = 4200;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				break;

			case 11288:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 926;
				itemDef.newModelColor[0] = 196608;
				itemDef.modelID = 2438;
				itemDef.modelZoom = 730;
				itemDef.rotationY = 516;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -10;
				itemDef.maleEquip1 = 3188;
				itemDef.femaleEquip1 = 3192;
				itemDef.name = "Black h'ween Mask";
				itemDef.description = "Aaaarrrghhh... I'm a monster.";
				break;
			case 11924:
				itemDef.name = "Malediction ward";
				itemDef.modelZoom = 1200;
				itemDef.rotationY = 568;
				itemDef.rotationX = 1836;
				itemDef.modelOffsetX = 2;
				itemDef.modelOffsetY = 3;
				itemDef.newModelColor = new int[] { -21608 };
				itemDef.editedModelColor = new int[] { 908 };
				itemDef.modelID = 9354;
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.maleEquip1 = 9347;
				itemDef.femaleEquip1 = 9347;
				break;
			case 12282:
				itemDef.name = "Serpentine helm";
				itemDef.modelID = 19220;
				itemDef.modelZoom = 700;
				itemDef.rotationX = 1724;
				itemDef.rotationY = 215;
				itemDef.modelOffsetX = 17;
				itemDef.femaleEquip1 = 14398;
				itemDef.maleEquip1 = 14395;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 12279:
				itemDef.name = "Magma helm";
				itemDef.modelID = 29205;
				itemDef.modelZoom = 700;
				itemDef.rotationX = 1724;
				itemDef.rotationY = 215;
				itemDef.modelOffsetX = 17;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.femaleEquip1 = 14426;
				itemDef.maleEquip1 = 14424;
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 917:
				itemDef.name = "Santa hat & Specs";
				itemDef.modelID = 90559;
				itemDef.modelZoom = 700;
				itemDef.rotationX = 1724;
				itemDef.rotationY = 215;
				itemDef.modelOffsetX = 17;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.femaleEquip1 = 90558;
				itemDef.maleEquip1 = 90558;
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 918:
				itemDef.name = "Ironman helmet";
				itemDef.modelID = 95047;
				//	itemDef.modelID = 2438;
				itemDef.modelZoom = 730;
				itemDef.rotationY = 516;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -10;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.femaleEquip1 = 95047;
				itemDef.maleEquip1 = 95047;
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20074:
				itemDef.name = "infernal mg";
				itemDef.modelID = 95031;
				itemDef.modelZoom = 700;
				itemDef.rotationX = 1724;
				itemDef.rotationY = 215;
				itemDef.modelOffsetX = 17;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.femaleEquip1 = 95032;
				itemDef.maleEquip1 = 95032;
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 12278:
				itemDef.name = "Tanzanite helm";
				itemDef.modelID = 29213;
				itemDef.modelZoom = 700;
				itemDef.rotationX = 1724;
				itemDef.rotationY = 215;
				itemDef.modelOffsetX = 17;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.femaleEquip1 = 23994;
				itemDef.maleEquip1 = 14421;
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 13239:
				itemDef.name = "Artemis boots";
				itemDef.modelID = 29397;
				itemDef.modelZoom = 976;
				itemDef.rotationY = 147;
				itemDef.rotationX = 279;
				itemDef.modelOffsetX = 5;
				itemDef.modelOffsetY = 5;
				itemDef.maleEquip1 = 29250;
				itemDef.femaleEquip1 = 29255;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 12708:
				itemDef.name = "Pegasian boots";
				itemDef.modelID = 29396;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.groundActions = new String[5];
				itemDef.groundActions[1] = "Take";
				itemDef.modelZoom = 900;
				itemDef.rotationY = 165;
				itemDef.rotationX = 279;
				itemDef.modelOffsetX = 3;
				itemDef.modelOffsetY =-7;
				itemDef.maleEquip1 = 29252;
				itemDef.femaleEquip1 = 29253;
				break;
			case 16137:
				itemDef.name = "Thok's Sword";
				break;
			case 20922:
				itemDef.name = "@bla@Electric @whi@katana";
				itemDef.description = "It's a Electric katana.";
				itemDef.modelID = 91218;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.groundActions[2] = "Take";
				itemDef.modelZoom = 1645;
				itemDef.rotationX = 377;
				itemDef.rotationY = 444;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 91219;
				itemDef.femaleEquip1 = 91219;
				itemDef.colourRedefine3 = 100000;
				break;
			case 20923:
				itemDef.name = "@bla@Electric @whi@full helmet";
				itemDef.description = "It's a Electric full helmet.";
				itemDef.modelID = 91172;
				itemDef.modelZoom = 700;
				itemDef.rotationX = 1724;
				itemDef.rotationY = 215;
				itemDef.modelOffsetX = 17;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.femaleEquip1 = 91173;
				itemDef.maleEquip1 = 91173;
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.colourRedefine3 = 100000;
				break;
			case 20924:
				itemDef.name = "@bla@Electric @whi@platebody";
				itemDef.description = "It's a Electric playebody.";
				itemDef.modelZoom = 1506;
				itemDef.rotationY = 473;
				itemDef.rotationX = 2042;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 91227;
				itemDef.colourRedefine3 = 100000;

				itemDef.modelID = 91226;
				itemDef.femaleEquip1 = 91227;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20925:
				itemDef.modelID = 91224;
				itemDef.name = "@bla@Electric @whi@platelegs";
				itemDef.description = "It's a Electric playelegs.";
				itemDef.modelZoom = 1740;
				itemDef.rotationY = 474;
				itemDef.rotationX = 2045;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 91225;
				itemDef.colourRedefine3 = 100000;
				itemDef.femaleEquip1 = 91225;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 20927:
				itemDef.modelID = 91166;
				itemDef.name = "@bla@Electric @whi@gauntlets";
				itemDef.description = "It's a pair of Electric gauntlets.";
				itemDef.modelZoom = 592;
				itemDef.rotationY = 323;
				itemDef.rotationX = 1710;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 5;
				itemDef.maleEquip1 = 91167;
				itemDef.femaleEquip1 = 91167;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				itemDef.colourRedefine3 = 100000;
				break;
			case 20926:
				itemDef.name = "@bla@Electric @whi@boots";
				itemDef.description = "It's a pair of Electric boots.";
				itemDef.modelID = 91228;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.modelZoom = 900;
				itemDef.rotationY = 165;
				itemDef.rotationX = 279;
				itemDef.modelOffsetX = 3;
				itemDef.modelOffsetY =-7;
				itemDef.maleEquip1 = 91228;
				itemDef.femaleEquip1 = 91228;
				itemDef.colourRedefine3 = 100000;
				break;
			case 20928:
				itemDef.modelID = 91058;
				itemDef.name = "@bla@Electric @whi@cape";
				itemDef.description = "It's a pair of Electric wings.";
				itemDef.modelZoom = 1519;
				itemDef.rotationY = 307;
				itemDef.rotationX = 978;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 91059;
				itemDef.femaleEquip1 = 91059;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				itemDef.colourRedefine3 = 100000;
				break;
			case 1543:
				itemDef.name = "Boss Key";
				break;
			case 6821:
				itemDef.name = "Money Orb";
				break;
			case 15356:
				itemDef.name = "$5 Bond";
				itemDef.modelID = 140103;
				itemDef.actions = new String[5];
				itemDef.actions[4] = "Drop";
				itemDef.actions[0] = "Claim";
				itemDef.modelZoom = 2155;
				itemDef.rotationY = 396;
				itemDef.rotationX = 1050;
				itemDef.modelOffsetY = -3;
				itemDef.modelOffset1 = 4;
				break;
			case 15355:
				itemDef.name = "$10 Bond";
				itemDef.modelID = 140103;
				itemDef.actions = new String[5];
				itemDef.actions[4] = "Drop";
				itemDef.actions[0] = "Claim";
				itemDef.modelZoom = 2155;
				itemDef.rotationY = 396;
				itemDef.rotationX = 1050;
				itemDef.modelOffsetY = -3;
				itemDef.modelOffset1 = 4;
				break;
			case 15359:
				itemDef.name = "$25 Bond";
				itemDef.modelID = 140103;
				itemDef.actions = new String[5];
				itemDef.actions[4] = "Drop";
				itemDef.actions[0] = "Claim";
				itemDef.modelZoom = 2155;
				itemDef.rotationY = 396;
				itemDef.rotationX = 1050;
				itemDef.modelOffsetY = -3;
				itemDef.modelOffset1 = 4;
				break;
			case 15358:
				itemDef.name = "$50 Bond";
				itemDef.modelID = 140103;
				itemDef.actions = new String[5];
				itemDef.actions[4] = "Drop";
				itemDef.actions[0] = "Claim";
				itemDef.modelZoom = 2155;
				itemDef.rotationY = 396;
				itemDef.rotationX = 1050;
				itemDef.modelOffsetY = -3;
				itemDef.modelOffset1 = 4;
				break;

			case 13235:
				itemDef.name = "Eternal boots";
				itemDef.modelID = 29394;
				itemDef.modelZoom = 976;
				itemDef.rotationY = 147;
				itemDef.rotationX = 279;
				itemDef.modelOffsetX = 5;
				itemDef.modelOffsetY = 5;
				itemDef.maleEquip1 = 29249;
				itemDef.femaleEquip1 = 29254;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
				break;
			case 7614:
				itemDef.modelID = 95152;
				itemDef.name = "Zilyana shield";
				itemDef.description = "Worn by the mighty Commander of Saradomin.";
				itemDef.modelZoom = 1616;
				itemDef.rotationY = 396;
				itemDef.rotationX = 1050;
				itemDef.modelOffsetY = -3;
				itemDef.modelOffset1 = 4;
				itemDef.maleEquip1 = 95153;
				itemDef.femaleEquip1 = 95153;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[2] = "Check-charges";
				itemDef.actions[4] = "Drop";
				break;
			case 20059:
				itemDef.name = "Drygore rapier";
				itemDef.modelZoom = 1145;
				itemDef.rotationX = 2047;
				itemDef.rotationY = 254;
				itemDef.modelOffset1 = -3;
				itemDef.modelOffsetY = -45;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", "Check-charges", null, "Drop" };
				itemDef.modelID = 14000;
				itemDef.maleEquip1 = 14001;
				itemDef.femaleEquip1 = 14001;
				break;
			case 20057:
				itemDef.name = "Drygore longsword";
				itemDef.modelZoom = 1645;
				itemDef.rotationX = 377;
				itemDef.rotationY = 444;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 0;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", "Check-charges", null, "Drop" };
				itemDef.modelID = 14022;
				itemDef.maleEquip1 = 14023;
				itemDef.femaleEquip1 = 14023;
				break;
			case 20058:
				itemDef.name = "Drygore mace";
				itemDef.modelZoom = 1118;
				itemDef.rotationX = 28;
				itemDef.rotationY = 235;
				itemDef.modelOffset1 = -1;
				itemDef.modelOffsetY = -47;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", "Check-charges", null, "Drop" };
				itemDef.modelID = 14024;
				itemDef.maleEquip1 = 14025;
				itemDef.femaleEquip1 = 14025;
				break;
			/**END OF OSRS ITEMS**/
			case 1686:
				itemDef.name = "Drygore longsword";
				itemDef.modelZoom = 1645;
				itemDef.rotationX = 377;
				itemDef.rotationY = 444;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 0;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", "Check-charges", null, "Drop" };
				itemDef.modelID = 94465;
				itemDef.maleEquip1 = 14150;
				itemDef.femaleEquip1 = 14150;
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
				itemDef.actions = new String[]{null, null, null, null, "Drop"};
				break;
			case 13727:
				itemDef.actions = new String[]{null, null, null, null, "Drop"};
				break;
			case 6500:
				itemDef.modelID = 9123;
				itemDef.name = "Charming imp";
				//	itemDef.modelZoom = 672;
				//	itemDef.rotationY = 85;
				//	itemDef.rotationX = 1867;
				itemDef.actions = new String[]{null, null, "Check", "Config", "Drop"};
				break;
			case 13045:
				itemDef.name = "Abyssal bludgeon";
				itemDef.modelZoom = 2611;
				itemDef.rotationY = 552;
				itemDef.rotationX = 1508;
				itemDef.modelOffsetY = 3;
				itemDef.modelOffset1 = -17;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", "Check", "Uncharge", "Drop" };
				itemDef.modelID = 29597;
				itemDef.maleEquip1 = 29424;
				itemDef.femaleEquip1 = 29424;
				break;
			case 13047:
				itemDef.name = "Abyssal dagger";
				itemDef.modelZoom = 1347;
				itemDef.rotationY = 1589;
				itemDef.rotationX = 781;
				itemDef.modelOffsetY = 3;
				itemDef.modelOffset1 = -5;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", "Check", "Uncharge", "Drop" };
				itemDef.modelID = 29598;
				itemDef.maleEquip1 = 29425;
				itemDef.femaleEquip1 = 29425;
				break;
			case 500:
				itemDef.modelID = 2635;
				itemDef.name = "Black partyhat";
				itemDef.description = "Black partyhat";
				itemDef.modelZoom = 440;
				itemDef.rotationX = 1845;
				itemDef.rotationY = 121;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 1;
				itemDef.stackable = false;
				itemDef.value = 1;
				itemDef.membersObject = true;
				itemDef.maleEquip1 = 94498;
				itemDef.femaleEquip1 = 94498;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor = new int[] { 926 };
				break;
			case 13655:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.name = "Baphomet Drygore Offhand";
				itemDef.description = "A rare, protective kiteshield.";
				itemDef.modelID = 83379;
				itemDef.modelZoom = 1645;
				itemDef.rotationX = 377;
				itemDef.rotationY = 444;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 83380;
				itemDef.femaleEquip1 = 83380;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				//itemDef.anInt188 = -1;
				//itemDef.anInt164 = -1;
				itemDef.maleDialogue = -1;
				itemDef.femaleDialogue = -1;
				itemDef.editedModelColor = new int[] {16,11,4};
				itemDef.newModelColor = new int[]    {97000,97000,97000};
				break;

			case 7108:
				itemDef.stackable = true;
				itemDef.name = "Ammunition"; // Item Name
				itemDef.description = "Ammunition for various firearms."; // Item
				break;
			case 9706:
				itemDef.stackable = true;
				itemDef.name = "Armadylian arrow"; // Item Name
				itemDef.description = "Ammunition for the Armadylian machine."; // Item
				break;
			case 12284:
				itemDef.name = "Toxic staff of the dead";
				itemDef.modelID = 19224;
				itemDef.modelZoom = 2150;
				itemDef.rotationX = 1010;
				itemDef.rotationY = 512;
				itemDef.femaleEquip1 = 14402;
				itemDef.maleEquip1 = 49001;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[2] = "Check";
				itemDef.actions[4] = "Uncharge";
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.editedModelColor = new int[1];
				itemDef.editedModelColor[0] = 17467;
				itemDef.newModelColor = new int[1];
				itemDef.newModelColor[0] = 21947;
				break;
			case 12605:
				itemDef.name = "Treasonous ring";
				itemDef.modelZoom = 750;
				itemDef.rotationY = 342;
				itemDef.rotationX = 252;
				itemDef.modelOffset1 = -3;
				itemDef.modelOffsetY = -12;
				itemDef.groundActions = new String[]{null, null, "Take", null, null};
				itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
				itemDef.modelID = 43900;
				break;

			/** clue scrolls **/
			case 2714:
				itemDef.name = "Casket";
				break;
			case 6183:
				itemDef.name = "@cya@Starter @mag@Box";
				break;
			case 20986:
				itemDef.actions = new String[5];
				itemDef.actions[0] = "Claim Prize";
				itemDef.name = "@blu@Doflamingo @mag@Set";
				itemDef.description = "@blu@It's a Doflamingo @mag@Set @red@Box!";
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 22464;
				itemDef.newModelColor[0] = 22410;
				itemDef.modelID = 2426;
				itemDef.modelZoom = 1050;
				itemDef.rotationY = 160;
				itemDef.rotationX = 172;
				itemDef.modelOffsetY = 0;
				itemDef.modelOffset1 = 0;
				break;
			case 2677:
			case 2678:
			case 2679:
			case 2680:
			case 2681:
			case 2682:
			case 2683:
			case 2684:
			case 2685:
				itemDef.name = "Clue Scroll";
				break;



			case 13051:
				itemDef.name = "Armadyl crossbow";
				itemDef.modelZoom = 1325;
				itemDef.rotationY = 240;
				itemDef.rotationX = 110;
				itemDef.modelOffset1 = -6;
				itemDef.modelOffsetY = -40;
				itemDef.newModelColor = new int[]{115, 107, 10167, 10171};
				itemDef.editedModelColor = new int[]{5409, 5404, 6449, 7390};
				itemDef.groundActions = new String[]{null, null, "Take", null, null};
				itemDef.actions = new String[]{null, "Wield", null, null, "Drop"};
				itemDef.modelID = 19967;
				itemDef.maleEquip1 = 19839;
				itemDef.femaleEquip1 = 19839;
				break;
			case 20339:
				itemDef.name = "Imbued heart";
				itemDef.rotationY = 500;
				itemDef.rotationX = 1800;
				itemDef.actions = new String[] { "Draw power", null, null, null, "Drop" };
				itemDef.modelID = 32298;
				itemDef.modelOffset1 = 4;
				itemDef.modelOffsetY = -4;
				break;
			case 4454:
				itemDef.name = "Kodai wand";
				itemDef.modelZoom = 1417;
				itemDef.rotationY = 552;
				itemDef.rotationX = 1006;
				itemDef.modelOffsetY = 1;
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				itemDef.modelID = 32789;
				itemDef.maleEquip1 = 32669;
				itemDef.femaleEquip1 = 32669;
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 0;
				itemDef.newModelColor[0] = 4;
				break;
			case 4453:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.modelID = 32797; // Drop/Inv Model
				itemDef.maleEquip1 = 32668; // Male Wield Model
				itemDef.femaleEquip1 = 32668; // Female Wield
				itemDef.modelZoom = 1230;
				itemDef.rotationY = 236;
				itemDef.rotationX = 236;
				itemDef.modelOffset1 = -5;
				itemDef.modelOffsetY = -36;
				itemDef.stackable = false;
				itemDef.name = "Dragon hunter crossbow"; // Item Name
				itemDef.description = "A crossbow specialising in hunting dragons."; // Item
				break;
			case 4452:
				itemDef.name = "Twisted buckler";
				itemDef.modelZoom = 920;
				itemDef.rotationY = 291;
				itemDef.rotationX = 2031;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -3;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.modelID = 32793;
				itemDef.maleEquip1 = 32667;
				itemDef.femaleEquip1 = 32667;
				break;
			case 4448:
				itemDef.name = "Dinh's bulwark";
				itemDef.modelZoom = 1620;
				itemDef.rotationY = 291;
				itemDef.rotationX = 1224;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -3;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.modelID = 32801;
				itemDef.maleEquip1 = 32671;
				itemDef.femaleEquip1 = 32671;
				break;
			case 15369:
				itemDef.actions = new String[5];
				itemDef.actions[0] = "Claim Prize";
				itemDef.name = "@gre@Common Package"; //Name
				itemDef.description = "A Party @gre@Common"; //Description
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 22464;
				itemDef.newModelColor[0] = 22410;
				itemDef.modelID = 2426;
				itemDef.modelZoom = 1180;
				itemDef.rotationY = 160;
				itemDef.rotationX = 172;
				itemDef.modelOffsetY = 0;
				itemDef.modelOffset1 = 0;
				break;
			case 15370:
				itemDef.actions = new String[5];
				itemDef.actions[0] = "Claim Prize";
				itemDef.name = "@blu@Uncommon Package"; //Name
				itemDef.description = "A @blu@Uncommon package"; //Description
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 1010;
				itemDef.newModelColor[0] = 22410;
				itemDef.modelID = 2426;
				itemDef.modelZoom = 1180;
				itemDef.rotationY = 160;
				itemDef.rotationX = 172;
				itemDef.modelOffsetY = 0;
				itemDef.modelOffset1 = 0;
				break;
			case 15371:
				itemDef.actions = new String[5];
				itemDef.actions[0] = "Claim Prize";
				itemDef.name = "@ora@Rare Package"; //Name
				itemDef.description = "A @ora@Rare Package"; //Description
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 23009;
				itemDef.newModelColor[0] = 22410;
				itemDef.modelID = 2426;
				itemDef.modelZoom = 1180;
				itemDef.rotationY = 160;
				itemDef.rotationX = 172;
				itemDef.modelOffsetY = 0;
				itemDef.modelOffset1 = 0;
				break;
			case 15372:
				itemDef.actions = new String[5];
				itemDef.actions[0] = "Claim Prize";
				itemDef.name = "@red@Extreme Package"; //Name
				itemDef.description = "A Extreme Package"; //Description
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 8464;
				itemDef.newModelColor[0] = 22410;
				itemDef.modelID = 2426;
				itemDef.modelZoom = 1180;
				itemDef.rotationY = 160;
				itemDef.rotationX = 172;
				itemDef.modelOffsetY = 0;
				itemDef.modelOffset1 = 0;
				break;
			case 15373:
				itemDef.actions = new String[5];
				itemDef.actions[0] = "Claim Prize";
				itemDef.name = "@bla@Legendary Package"; //Name
				itemDef.description = "A @bla@Legendary Package"; //Description
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 0;
				itemDef.newModelColor[0] = 22410;
				itemDef.modelID = 2426;
				itemDef.modelZoom = 1180;
				itemDef.rotationY = 160;
				itemDef.rotationX = 172;
				itemDef.modelOffsetY = 0;
				itemDef.modelOffset1 = 0;
				break;
     /*       case 4449:
            	itemDef.name = "Dragon sword";
            	itemDef.modelZoom = 1200;
            	itemDef.rotationY = 255;
            	itemDef.rotationX = 337;
            	itemDef.modelOffset1 = 7;
            	itemDef.actions = new String[] { null, "Wield", "null", null, "Destroy" };
            	itemDef.modelID = 32791;
            	itemDef.maleEquip1 = 32676;
            	itemDef.femaleEquip1 = 32676;
            	break; */
			case 4450:
				itemDef.name = "Elder maul";
				itemDef.modelZoom = 1800;
				itemDef.rotationY = 308;
				itemDef.rotationX = 36;
				itemDef.modelOffset1 = 7;
				itemDef.actions = new String[] { null, "Wield", "null", null, "Destroy" };
				itemDef.modelID = 32792;
				itemDef.maleEquip1 = 32678;
				itemDef.femaleEquip1 = 32678;
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 0;
				itemDef.newModelColor[0] = 4;
				break;
			case 3954:
				itemDef.name = "Bandos maul";
				itemDef.description = "One of General Graardors many weapons.";
				itemDef.modelZoom = 2300;
				itemDef.rotationY = 308;
				itemDef.rotationX = 36;
				itemDef.modelOffset1 = 7;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.modelID = 95001;
				itemDef.maleEquip1 = 95000;
				itemDef.femaleEquip1 = 95000;
				break;
			case 3964:
				itemDef.name = "Kruza's WarMachine";
				itemDef.description = "One of Commander Kruza's many weapons.";
				itemDef.modelZoom = 2300;
				itemDef.rotationY = 308;
				itemDef.rotationX = 36;
				itemDef.modelOffset1 = 7;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.modelID = 95019;
				itemDef.maleEquip1 = 95020;
				itemDef.femaleEquip1 = 95020;
				break;
			case 4451:
				itemDef.name = "Dragon thrownaxe";
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.modelID = 32788;
				itemDef.modelZoom = 980;
				itemDef.rotationY = 415;
				itemDef.rotationX = 112;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 2;
				itemDef.femaleEquip1 = 32672;
				itemDef.maleEquip1 = 32672; // male equip
				break;
			case 12927:
				itemDef.name = "Magma blowpipe";
				itemDef.modelZoom = 1158;
				itemDef.rotationY = 768;
				itemDef.rotationX = 189;
				itemDef.modelOffset1 = -7;
				itemDef.modelOffsetY = 4;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", "Check", "Unload", "Uncharge" };
				itemDef.newModelColor = new int[] { 8134, 5058, 926, 957, 3008, 1321, 86, 41, 49, 7110, 3008, 1317 };
				itemDef.editedModelColor = new int[] { 48045, 49069, 48055, 49083, 50114, 33668, 29656, 29603, 33674, 33690, 33680, 33692 };
				itemDef.modelID = 19219;
				itemDef.maleEquip1 = 14403;
				itemDef.femaleEquip1 = 14403;
				break;
			case 12926:
				itemDef.modelID = 25000;
				itemDef.name = "Toxic blowpipe";
				itemDef.description = "It's a Toxic blowpipe";
				itemDef.modelZoom = 1158;
				itemDef.rotationY = 768;
				itemDef.rotationX = 189;
				itemDef.modelOffset1 = -7;
				itemDef.modelOffsetY = 4;
				itemDef.maleEquip1 = 14403;
				itemDef.femaleEquip1 = 14403;
				itemDef.actions = new String[]{null, "Wield", "Check", "Unload", "Drop"};
				itemDef.groundActions = new String[]{null, null, "Take", null, null};
				break;
			case 3962:
				itemDef.modelID = 95016;
				itemDef.name = "Dragonrage blowpipe";
				itemDef.description = "It's a Dragonrage blowpipe";
				itemDef.modelZoom = 1158;
				itemDef.rotationY = 768;
				itemDef.rotationX = 189;
				itemDef.modelOffset1 = -7;
				itemDef.modelOffsetY = 4;
				itemDef.maleEquip1 = 95017;
				itemDef.femaleEquip1 = 95017;
				itemDef.actions = new String[]{null, "Wield", "Check", "Unload", "Drop"};
				itemDef.groundActions = new String[]{null, null, "Take", null, null};
				break;
			case 13058:
				itemDef.name = "Trident of the seas";
				itemDef.description = "A weapon from the deep.";
				itemDef.femaleEquip1 = 1052;
				itemDef.maleEquip1 = 1052;
				itemDef.modelID = 1051;
				itemDef.rotationY = 420;
				itemDef.rotationX = 1130;
				itemDef.modelZoom = 2755;
				itemDef.modelOffsetY = 0;
				itemDef.modelOffset1 = 0;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[3] = "Check";
				break;
			case 12601:
				itemDef.name = "Ring of the gods";
				itemDef.modelZoom = 900;
				itemDef.rotationY = 393;
				itemDef.rotationX = 1589;
				itemDef.modelOffset1 = -9;
				itemDef.modelOffsetY = -12;
				itemDef.groundActions = new String[]{null, null, "Take", null, null};
				itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
				itemDef.modelID = 94354;
				break;
			case 12603:
				itemDef.name = "Tyrannical ring";
				itemDef.modelZoom = 592;
				itemDef.rotationY = 285;
				itemDef.rotationX = 1163;
				itemDef.groundActions = new String[]{null, null, "Take", null, null};
				itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
				itemDef.modelID = 28823;
				break;
			case 20555:
				itemDef.name = "Dragon warhammer";
				itemDef.modelID = 4041;
				itemDef.rotationY = 1450;
				itemDef.rotationX = 1900;
				itemDef.modelZoom = 1605;
				itemDef.groundActions = new String[] { null, null, "Take", null, null };
				itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
				itemDef.maleEquip1 = 4037;
				itemDef.femaleEquip1 = 4038;
				break;
			case 11613:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.modelID = 13701;
				itemDef.modelZoom = 1560;
				itemDef.rotationY = 344;
				itemDef.rotationX = 1104;
				itemDef.modelOffsetY = -14;
				itemDef.modelOffsetX = 0;
				itemDef.maleEquip1 = 13700;
				itemDef.femaleEquip1 = 13700;
				itemDef.name = "Dragon Kiteshield";
				itemDef.description = "A Dragon Kiteshield!";
				break;
			case 11614:
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.modelID = 3288;
				itemDef.modelZoom = 2000;
				itemDef.rotationY = 500;
				itemDef.rotationX = 0;
				itemDef.modelOffsetY = -6;
				itemDef.modelOffsetX = 1;
				itemDef.maleEquip1 = 3287;
				itemDef.femaleEquip1 = 3287;
				itemDef.name = "Death Cape";
				break;

			case 20870:
				itemDef.actions = new String[5];
				itemDef.actions[0] = "Claim Prize";
				itemDef.name = "@yel@Yanille @yel@Mystery @yel@Box";
				itemDef.description = "@yel@It's a Yanille Mystery Box!";
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 22464;
				itemDef.newModelColor[0] = 22410;
				itemDef.modelID = 2426;
				itemDef.modelZoom = 1050;
				itemDef.rotationY = 160;
				itemDef.rotationX = 172;
				itemDef.modelOffsetY = 0;
				itemDef.modelOffset1 = 0;
				break;
			case 20931:
				itemDef.actions = new String[5];
				itemDef.actions[0] = "Claim Prize";
				itemDef.name = "@mag@Ultra @cya@Mystery @yel@Box";
				itemDef.description = "@yel@It's a Ultra Mystery Box!";
				itemDef.editedModelColor = new int[1];
				itemDef.newModelColor = new int[1];
				itemDef.editedModelColor[0] = 22464;
				itemDef.newModelColor[0] = 22410;
				itemDef.modelID = 2426;
				itemDef.modelZoom = 1050;
				itemDef.rotationY = 160;
				itemDef.rotationX = 172;
				itemDef.modelOffsetY = 0;
				itemDef.modelOffset1 = 0;
				break;
			case 20996:
				itemDef.modelID = 140041;
				itemDef.name = "@mag@Infernal @cya@Virtus @red@Mask @gre@10dr";
				itemDef.description = "Best ancient mage's Mask.";
				itemDef.modelZoom = 928;
				itemDef.rotationY = 406;
				itemDef.rotationX = 2041;
				itemDef.modelOffset1 = 1;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 140042;
				itemDef.femaleEquip1 = 140042;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {51,60,40};
				itemDef.newModelColor = new int[]    {60,60,60};
				break;
			case 20994:
				itemDef.modelID = 140043;
				itemDef.name = "@mag@Infernal @cya@Virtus @red@Top @gre@10dr";
				itemDef.description = "Best ancient mage's Top.";
				itemDef.modelZoom = 1122;
				itemDef.rotationY = 488;
				itemDef.rotationX = 3;
				itemDef.modelOffset1 = 1;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140044;
				itemDef.femaleEquip1 = 140044;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {51,60,40};
				itemDef.newModelColor = new int[]    {60,60,60};
				break;
			case 20997:
				itemDef.modelID = 140045;
				itemDef.name = "@mag@Infernal @cya@Virtus @red@Robe @blu@Legs @gre@10dr";
				itemDef.description = "Best ancient mage's Legs.";
				itemDef.modelZoom = 1740;
				itemDef.rotationY = 498;
				itemDef.rotationX = 2045;
				itemDef.modelOffset1 = -1;
				itemDef.modelOffsetY = 4;
				itemDef.maleEquip1 = 140046;
				itemDef.femaleEquip1 = 140046;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {51,60,40};
				itemDef.newModelColor = new int[]    {60,60,60};
				break;
			case 20998:
				itemDef.modelID = 140047;
				itemDef.name = "@mag@Infernal @cya@Battle @red@Staff";
				itemDef.description = "Best ancient mage's Staff.";
				itemDef.modelZoom = 2650;
				itemDef.rotationY = 691;
				itemDef.rotationX = 1591;
				itemDef.modelOffset1 = 1;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 140048;
				itemDef.femaleEquip1 = 140048;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wield";
				itemDef.actions[4] = "Drop";
				itemDef.editedModelColor = new int[] {51,60,40};
				itemDef.newModelColor = new int[]    {60,60,60};
				break;
			case 20995:
				itemDef.modelID = 140040;
				itemDef.name = "@mag@Infernal @cya@Virtus @red@Boots @gre@10dr";
				itemDef.description = "Best ancient mage's Boots.";
				itemDef.modelZoom = 789;
				itemDef.rotationY = 164;
				itemDef.rotationX = 156;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140040;
				itemDef.femaleEquip1 = 140040;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 21016:
				itemDef.modelID = 140051;
				itemDef.name = "@cya@Deluxe @red@Full Helm @gre@10dr";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 650;
				itemDef.rotationY = 491;
				itemDef.rotationX = 1991;
				itemDef.modelOffset1 = 1;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 140051;
				itemDef.femaleEquip1 = 140051;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[2] = "Check-charges";
				itemDef.actions[4] = "Drop";
				break;
			case 21014:
				itemDef.modelID = 140052;
				itemDef.name = "@cya@Deluxe @red@Platebody @gre@10dr";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 1122;
				itemDef.rotationY = 488;
				itemDef.rotationX = 3;
				itemDef.modelOffset1 = 1;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140052;
				itemDef.femaleEquip1 = 140052;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[2] = "Check-charges";
				itemDef.actions[4] = "Drop";
				break;
			case 21015:
				itemDef.modelID = 94431;
				itemDef.name = "@cya@Deluxe @red@Gloves @gre@10dr";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 648;
				itemDef.rotationY = 618;
				itemDef.rotationX = 1143;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 94432;
				itemDef.femaleEquip1 = 94432;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 21005:
				itemDef.modelID = 140053;
				itemDef.name = "@cya@Deluxe @red@Boots @gre@10dr";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 1730;
				itemDef.rotationY = 225;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140053;
				itemDef.femaleEquip1 = 140053;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[2] = "Check-charges";
				itemDef.actions[4] = "Drop";
				break;
			case 21000:
				itemDef.modelID = 59093;
				itemDef.name = "@cya@Deluxe @red@Boots @gre@10dr";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 789;
				itemDef.rotationY = 164;
				itemDef.rotationX = 156;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 59093;
				itemDef.femaleEquip1 = 59093;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 21001:
				itemDef.modelID = 6701;
				itemDef.name = "@cya@Delux @red@Whip @gre@35dr";
				itemDef.description = "Simple onfire.";
				itemDef.modelZoom = 700;
				itemDef.rotationY = 324;
				itemDef.rotationX = 1808;
				itemDef.modelOffsetX = 1;
				itemDef.modelOffsetY = 3;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.maleEquip1 = 95130;
				itemDef.femaleEquip1 = 95130;
				break;
			case 21017:
				itemDef.modelID = 90061;
				itemDef.name = "@cya@Deluxe @red@Cape @gre@25dr";
				itemDef.description = "Simply onfire.";
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 1;
				itemDef.stackable = false;
				itemDef.modelZoom = 2000;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.rotationX = 0;
				itemDef.rotationY = 0;
				itemDef.maleEquip1 = 90061;
				itemDef.femaleEquip1 = 90061;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 21018:
				itemDef.modelID = 140056;
				itemDef.name = "@whi@Storm @red@Troop @blu@Body";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 1447;
				itemDef.rotationY = 485;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140055;
				itemDef.femaleEquip1 = 140055;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 21019:
				itemDef.modelID = 140058;
				itemDef.name = "@whi@Storm @red@Troop @blu@Legs";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 1730;
				itemDef.rotationY = 525;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140057;
				itemDef.femaleEquip1 = 140057;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 21035:
				itemDef.modelID = 140060;
				itemDef.name = "@whi@Storm @red@Troop @blu@Mask";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 921;
				itemDef.rotationY = 121;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140059;
				itemDef.femaleEquip1 = 140059;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 21036:
				itemDef.modelID = 140062;
				itemDef.name = "@whi@Storm @red@Troop @blu@Gloves";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 930;
				itemDef.rotationY = 420;
				itemDef.rotationX = 828;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140061;
				itemDef.femaleEquip1 = 140061;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 21037:
				itemDef.modelID = 140063;
				itemDef.name = "@whi@Storm @red@Troop @blu@Boots";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 789;
				itemDef.rotationY = 164;
				itemDef.rotationX = 156;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140063;
				itemDef.femaleEquip1 = 140063;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 21038:
				itemDef.modelID = 140079;
				itemDef.name = "@whi@Storm @red@Troop @yel@Yellow @blu@Gun";
				itemDef.description = "Simple onfire.";
				itemDef.modelZoom = 1200;
				itemDef.rotationY = 324;
				itemDef.rotationX = 2008;
				itemDef.modelOffsetX = 1;
				itemDef.modelOffsetY = 3;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.maleEquip1 = 140078;
				itemDef.femaleEquip1 = 140078;
				break;
			case 21039:
				itemDef.modelID = 140081;
				itemDef.name = "@whi@Storm @red@Troop @yel@Purple @blu@Gun";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 1200;
				itemDef.rotationY = 324;
				itemDef.rotationX = 2008;
				itemDef.modelOffsetX = 1;
				itemDef.modelOffsetY = 3;
				itemDef.maleEquip1 = 140080;
				itemDef.femaleEquip1 = 140080;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 21040:
				itemDef.modelID = 140083;
				itemDef.name = "@whi@Storm @red@Troop @yel@Orange @blu@Gun";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 1200;
				itemDef.rotationY = 324;
				itemDef.rotationX = 2008;
				itemDef.modelOffsetX = 1;
				itemDef.modelOffsetY = 3;
				itemDef.maleEquip1 = 140082;
				itemDef.femaleEquip1 = 140082;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 21041:
				itemDef.modelID = 140085;
				itemDef.name = "@whi@Storm @red@Troop @yel@Blue @blu@Gun";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 1200;
				itemDef.rotationY = 324;
				itemDef.rotationX = 2008;
				itemDef.modelOffsetX = 1;
				itemDef.modelOffsetY = 3;
				itemDef.maleEquip1 = 140084;
				itemDef.femaleEquip1 = 140084;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 21042:
				itemDef.modelID = 140087;
				itemDef.name = "@whi@Storm @red@Troop @yel@Mixer @blu@Gun";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 1150;
				itemDef.rotationY = 324;
				itemDef.rotationX = 1008;
				itemDef.modelOffsetX = 1;
				itemDef.modelOffsetY = 3;
				itemDef.maleEquip1 = 140086;
				itemDef.femaleEquip1 = 140086;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 21043:
				itemDef.modelID = 140089;
				itemDef.name = "@whi@Storm @red@Troop @yel@Pink @blu@Gun";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 1150;
				itemDef.rotationY = 324;
				itemDef.rotationX = 1008;
				itemDef.modelOffsetX = 1;
				itemDef.modelOffsetY = 3;
				itemDef.maleEquip1 = 140088;
				itemDef.femaleEquip1 = 140088;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 21046:
				itemDef.modelID = 140065;
				itemDef.name = "@red@Darth @yel@Vader @blu@Body";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 1447;
				itemDef.rotationY = 485;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140064;
				itemDef.femaleEquip1 = 140064;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 21047:
				itemDef.modelID = 140066;
				itemDef.name = "@red@Darth @yel@Vader @blu@Legs";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 1516;
				itemDef.rotationY = 339;
				itemDef.rotationX = 1992;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140066;
				itemDef.femaleEquip1 = 140066;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 21048:
				itemDef.modelID = 140068;
				itemDef.name = "@red@Darth @yel@Vader @blu@Mask";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 921;
				itemDef.rotationY = 121;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140067;
				itemDef.femaleEquip1 = 140067;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 17488:
				itemDef.modelID = 140090;
				itemDef.name = "@red@Ultimatium @blu@Mask";
				itemDef.description = "It's a Ultimatium Mask.";
				itemDef.modelZoom = 921;
				itemDef.rotationY = 121;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140091;
				itemDef.femaleEquip1 = 140091;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 799:
				itemDef.modelID = 140092;
				itemDef.name = "@red@Ultimatium @blu@Body";
				itemDef.description = "It's a Ultimatium Body.";
				itemDef.modelZoom = 1447;
				itemDef.rotationY = 485;
				itemDef.rotationX = 0;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140093;
				itemDef.femaleEquip1 = 140093;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 3062:
				itemDef.modelID = 140094;
				itemDef.name = "@red@Ultimatium @blu@Legs";
				itemDef.description = "It's a Ultimatium Legs.";
				itemDef.modelZoom = 1516;
				itemDef.rotationY = 339;
				itemDef.rotationX = 1992;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140095;
				itemDef.femaleEquip1 = 140095;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 3063:
				itemDef.modelID = 140096;
				itemDef.name = "@red@Ultimatium @blu@Gloves";
				itemDef.description = "It's a Ultimatium Gloves.";
				itemDef.modelZoom = 1330;
				itemDef.rotationY = 420;
				itemDef.rotationX = 828;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140097;
				itemDef.femaleEquip1 = 140097;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 3064:
				itemDef.modelID = 140098;
				itemDef.name = "@red@Ultimatium @blu@Boots";
				itemDef.description = "It's a Ultimatium Boots.";
				itemDef.modelZoom = 825;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetY = 0;
				itemDef.rotationY = 400;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.maleEquip1 = 140098;
				itemDef.femaleEquip1 = 140098;
				break;
			case 3065:
				itemDef.modelID = 140099;
				itemDef.name = "@red@Ultimatium @mag@Battle-Axe";
				itemDef.description = "It's a Ultimatium Battle-Axe.";
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 1;
				itemDef.modelZoom = 2500;
				itemDef.rotationY = 339;
				itemDef.rotationX = 192;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140100;
				itemDef.femaleEquip1 = 140100;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 3066:
				itemDef.modelID = 140101;
				itemDef.name = "@red@Ultimatium @blu@shield";
				itemDef.description = "Its a Ultimatium shield.";
				itemDef.modelZoom = 1645;
				itemDef.rotationX = 777;
				itemDef.rotationY = 444;
				itemDef.modelOffset1 = 3;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140102;
				itemDef.femaleEquip1 = 140102;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 21049:
				itemDef.modelID = 140070;
				itemDef.name = "@red@Darth @yel@Vader @blu@Gloves";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 648;
				itemDef.rotationY = 618;
				itemDef.rotationX = 1143;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = -5;
				itemDef.maleEquip1 = 140069;
				itemDef.femaleEquip1 = 140069;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 21070:
				itemDef.modelID = 140072;
				itemDef.name = "@red@Darth @yel@Vader @blu@Cape";
				itemDef.description = "Simply onfire.";
				itemDef.modelZoom = 2016;
				itemDef.rotationY = 339;
				itemDef.rotationX = 1292;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.maleEquip1 = 140071;
				itemDef.femaleEquip1 = 140071;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.actions[4] = "Drop";
				break;
			case 21071:
				itemDef.modelID = 140073;
				itemDef.name = "@red@Darth @yel@Vader @blu@Boots";
				itemDef.description = "Simple onfire.";
				itemDef.modelZoom = 825;
				itemDef.modelOffset1 = -4;
				itemDef.modelOffsetY = 0;
				itemDef.rotationY = 400;
				itemDef.groundActions = new String[5];
				itemDef.groundActions[2] = "Take";
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				itemDef.maleEquip1 = 140073;
				itemDef.femaleEquip1 = 140073;
				break;
			case 21072:
				itemDef.modelID = 140075;
				itemDef.name = "@red@Darth @yel@Vader @blu@Laser @mag@Sword";
				itemDef.description = "Simply onfire.";
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 1;
				itemDef.stackable = false;
				itemDef.modelZoom = 2000;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.rotationX = 0;
				itemDef.rotationY = 0;
				itemDef.maleEquip1 = 140074;
				itemDef.femaleEquip1 = 140074;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
			case 21073:
				itemDef.modelID = 140077;
				itemDef.name = "@whi@Storm @red@Troop @yel@Laser @blu@Sword";
				itemDef.description = "Simply onfire.";
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 1;
				itemDef.stackable = false;
				itemDef.modelZoom = 2000;
				itemDef.modelOffset1 = 0;
				itemDef.modelOffsetY = 0;
				itemDef.rotationX = 0;
				itemDef.rotationY = 0;
				itemDef.maleEquip1 = 140076;
				itemDef.femaleEquip1 = 140076;
				itemDef.actions = new String[5];
				itemDef.actions[1] = "Wear";
				break;
		}

		return ItemDef_Lewis_1.forDef(itemDef, ID);
	}

}