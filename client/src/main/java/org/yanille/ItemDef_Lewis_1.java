package org.yanille;

/**
 * Created by Stan van der Bend on 23/12/2017.
 * project: immortal client
 * package: com.arlania
 */
public class ItemDef_Lewis_1 {

    public static ItemDef forDef(ItemDef itemDef, int ID) {

        switch (ID){

            case 20978:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.actions[4] = "Drop";
                itemDef.colourRedefine2 = 95000;
                break;

            case 20979:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.actions[4] = "Drop";
                itemDef.colourRedefine2 = 98000;
                break;

            case 3230:
                itemDef.modelID = 91355;
                itemDef.maleEquip1 = 91355;
                itemDef.femaleEquip1 = 91355;
                itemDef.name = "@pu1@Uber Box";
                itemDef.description = "I wonder what could be inside...";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.actions[4] = "Drop";
                itemDef.modelZoom = 1100;
                itemDef.rotationY = 72;
                itemDef.rotationX = 20;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -10;
                break;
            case 11995:
                itemDef.name = "Pet Chaos elemental";
                ItemDef itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11996:
                itemDef.name = "Pet King black dragon";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;

            case 20800:
                itemDef.modelID = 59067;
                itemDef.name = "Support Icon";
                itemDef.description = "Support Icon";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 59068;
                itemDef.femaleEquip1 = 59068;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20801:
                itemDef.modelID = 59069;
                itemDef.name = "Mod Icon";
                itemDef.description = "Mod Icon";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 59070;
                itemDef.femaleEquip1 = 59070;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20802:
                itemDef.modelID = 59071;
                itemDef.name = "Admin Icon";
                itemDef.description = "Admin Icon";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 59072;
                itemDef.femaleEquip1 = 59072;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20803:
                itemDef.modelID = 59073;
                itemDef.name = "Owner Icon";
                itemDef.description = "Owner Icon";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 59074;
                itemDef.femaleEquip1 = 59074;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20804:
                itemDef.modelID = 59075;
                itemDef.name = "Top Donator Icon";
                itemDef.description = "Top Donator Icon";
                itemDef.modelZoom = 2006;
                itemDef.rotationY = 473;
                itemDef.rotationX = 1542;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 59076;
                itemDef.femaleEquip1 = 59076;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20805:
                itemDef.modelID = 59077;
                itemDef.name = "Artemis bow";
                itemDef.description = "Its a Artemis bow.";
                itemDef.modelZoom = 2006;
                itemDef.rotationY = 473;
                itemDef.rotationX = 1542;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 59078;
                itemDef.femaleEquip1 = 59078;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20806:
                itemDef.modelID = 59079;
                itemDef.name = "Bad manners cape";
                itemDef.description = "Its a bad manners cape.";
                itemDef.modelZoom = 1616;
                itemDef.rotationY = 339;
                itemDef.rotationX = 192;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 59080;
                itemDef.femaleEquip1 = 59080;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.rotationY = 307;
                itemDef.rotationX = 978;
                break;

            case 20807:
                itemDef.modelID = 59081;
                itemDef.name = "Artemis helm";
                itemDef.description = "Its a artemis helm.";
                itemDef.modelZoom = 921;
                itemDef.rotationY = 121;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 59082;
                itemDef.femaleEquip1 = 59082;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20808:
                itemDef.modelID = 59083;
                itemDef.name = "Artemis chainbody";
                itemDef.description = "Its a artemis chainbody.";
                itemDef.modelZoom = 1616;
                itemDef.rotationY = 485;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 59084;
                itemDef.femaleEquip1 = 59084;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20809:
                itemDef.modelID = 59085;
                itemDef.name = "Artemis chainskirt";
                itemDef.description = "Its a artemis chainskirt.";
                itemDef.modelZoom = 1616;
                itemDef.rotationY = 485;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 59086;
                itemDef.femaleEquip1 = 59086;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20810:
                itemDef.modelID = 59087;
                itemDef.name = "Dragon vanquisher helm";
                itemDef.description = "Its a dragon vanquisher helm.";
                itemDef.modelZoom = 921;
                itemDef.rotationY = 121;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 59088;
                itemDef.femaleEquip1 = 59088;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20811:
                itemDef.modelID = 59089;
                itemDef.name = "Dragon vanquisher platebody";
                itemDef.description = "Its a dragon vanquisher platebody.";
                itemDef.modelZoom = 1616;
                itemDef.rotationY = 339;
                itemDef.rotationX = 192;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 59090;
                itemDef.femaleEquip1 = 59090;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20812:
                itemDef.modelID = 59091;
                itemDef.name = "Dragon vanquisher platelegs";
                itemDef.description = "Its a dragon vanquisher platelegs.";
                itemDef.modelZoom = 1616;
                itemDef.rotationY = 339;
                itemDef.rotationX = 192;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 59092;
                itemDef.femaleEquip1 = 59092;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20813:
                itemDef.modelID = 59094;
                itemDef.name = "Dragon vanquisher gauntlets";
                itemDef.description = "Its a dragon vanquisher gauntlets.";
                itemDef.modelZoom = 930;
                itemDef.rotationY = 420;
                itemDef.rotationX = 828;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 59095;
                itemDef.femaleEquip1 = 59095;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20814:
                itemDef.modelID = 59093;
                itemDef.name = "Dragon vanquisher heavy boots";
                itemDef.description = "Its a dragon vanquisher heavy boots.";
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



            case 11997:
                itemDef.name = "Pet General graardor";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11978:
                itemDef.name = "@gre@Floating @red@Black @whi@Paper @mag@Pet";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 12001:
                itemDef.name = "Pet Corporeal beast";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 12002:
                itemDef.name = "Pet Kree'arra";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 12003:
                itemDef.name = "Pet K'ril tsutsaroth";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 12004:
                itemDef.name = "Pet Commander zilyana";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 12005:
                itemDef.name = "Pet Dagannoth supreme";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 12006:
                itemDef.name = "Pet Dagannoth prime";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11990:
                itemDef.name = "Pet Dagannoth rex";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11991:
                itemDef.name = "Pet Frost dragon";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11992:
                itemDef.name = "Pet Tormented demon";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11993:
                itemDef.name = "Pet Kalphite queen";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11994:
                itemDef.name = "Pet Slash bash";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11989:
                itemDef.name = "Pet Phoenix";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11988:
                itemDef.name = "Pet Bandos avatar";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11987:
                itemDef.name = "Pet Nex";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11986:
                itemDef.name = "Pet Jungle strykewyrm";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11985:
                itemDef.name = "Pet Desert strykewyrm";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11984:
                itemDef.name = "Pet Ice strykewyrm";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11983:
                itemDef.name = "Pet Green dragon";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11982:
                itemDef.name = "Pet Baby blue dragon";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11981:
                itemDef.name = "Pet Blue dragon";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 11979:
                itemDef.name = "Pet Black dragon";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2090:
                itemDef.name = "Pet Monkey";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2091:
                itemDef.name = "Pet Hell Bat";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2103:
                itemDef.name = "Pet Nutella";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2104:
                itemDef.name = "Pet Abbadon";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2758:
                itemDef.name = "Pet Luigi";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2759:
                itemDef.name = "@mag@Mr. Krabs @yel@Pet @red@15dr";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2760:
                itemDef.name = "@mag@Sonic @yel@Pet @red@15dr";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2761:
                itemDef.name = "Pet Homer";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2762:
                itemDef.name = "@mag@Pikachu @yel@Pet @red@15dr";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 7582:
                itemDef.name = "Hellcat";
                ItemDef ItemDef21 = ItemDef.forID(7582);
                itemDef.modelID = ItemDef21.modelID;
                itemDef.modelOffset1 = ItemDef21.modelOffset1;
                itemDef.modelOffsetX = ItemDef21.modelOffsetX;
                itemDef.modelOffsetY = ItemDef21.modelOffsetY;
                itemDef.modelZoom = ItemDef21.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 7583:
                itemDef.name = "Hell-kitten";
                ItemDef ItemDef211 = ItemDef.forID(7583);
                itemDef.modelID = ItemDef211.modelID;
                itemDef.modelOffset1 = ItemDef211.modelOffset1;
                itemDef.modelOffsetX = ItemDef211.modelOffsetX;
                itemDef.modelOffsetY = ItemDef211.modelOffsetY;
                itemDef.modelZoom = ItemDef211.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2771:
                itemDef.name = "Pet Light creature";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2772:
                itemDef.name = "Pet Rooster";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 3620:
                itemDef.name = "Pet Pig";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 3621:
                itemDef.name = "Pet Dog";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 3622:
                itemDef.name = "Pet Hobo";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 19935:
                itemDef.name = "Pet Morty";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 19936:
                itemDef.name = "Pet Chilli";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 19937:
                itemDef.name = "Pet Mayonnaise";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2105:
                itemDef.name = "Pet Adamant Dragon";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2106:
                itemDef.name = "Pet Runite Dragon";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2107:
                itemDef.name = "Pet Rock Crab";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2108:
                itemDef.name = "Pet Har'laak The Riftsplitter";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2109:
                itemDef.name = "Pet Bal'laak The Pummeler";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2110:
                itemDef.name = "Pet To'Kash The Bloodchiller";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2111:
                itemDef.name = "Pet Iktomi The Trickster";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2112:
                itemDef.name = "Pet Le'fosh The Brutal";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2113:
                itemDef.name = "Pet Zamorakian Warbeast";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2092:
                itemDef.name = "Pet Centaur";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2093:
                itemDef.name = "Pet Dire Wolf";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2094:
                itemDef.name = "Pet Cyrisus";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 5022:
                itemDef.name = "1B Ticket";
                itemDef.stackable = true;
                itemDef.actions = new String[5];
                break;
            case 5023:
                itemDef.name = "1Q Ticket";
                itemDef.stackable = true;
                itemDef.actions = new String[5];
                //itemDef.actions[3] = "Add-to-pouch";
                break;

            case 2095:
                itemDef.name = "Pet Yoda";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2096:
                itemDef.name = "Pet Godzilla";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2097:
                itemDef.name = "Pet Deadly Dragonslayer";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2098:
                itemDef.name = "@mag@Donkey Kong @yel@Pet @red@15dr";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            // case 2095:
            //     itemDef.name = "Pet Callisto";
            //     itemDef2 = ItemDef.forID(12458);
            //    itemDef.modelID = itemDef2.modelID;
            //    itemDef.modelOffset1 = itemDef2.modelOffset1;
            //   itemDef.modelOffsetX = itemDef2.modelOffsetX;
            //  itemDef.modelOffsetY = itemDef2.modelOffsetY;
            //     itemDef.modelZoom = itemDef2.modelZoom;
            //   itemDef.groundActions = new String[]{null, null, "Take", null, null};
            //     itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
            //    break;
            // case 2096:
            // itemDef.name = "Pet Vetion";
            //  itemDef2 = ItemDef.forID(12458);
            //   itemDef.modelID = itemDef2.modelID;
            //    itemDef.modelOffset1 = itemDef2.modelOffset1;
            //    itemDef.modelOffsetX = itemDef2.modelOffsetX;
            //    itemDef.modelOffsetY = itemDef2.modelOffsetY;
            //    itemDef.modelZoom = itemDef2.modelZoom;
            //     itemDef.groundActions = new String[]{null, null, "Take", null, null};
            //     itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
            //     break;
            case 2099:
                itemDef.name = "Pet Thermy";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2100:
                itemDef.name = "Pet Skotizo";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2101:
                itemDef.name = "Pet Cerberus";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 2102:
                itemDef.name = "Pet Abyssal Sire";
                itemDef2 = ItemDef.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;


            case 4417:
                itemDef.name = "Grimy calamint";
                itemDef.description = "I need to clean this herb before I can use it...";
                itemDef.modelID = ItemDef.forID(199).modelID;
                itemDef.modelZoom = 789;
                itemDef.rotationY = 581;
                itemDef.rotationX = 1770;
                itemDef.modelOffset1 = 8;
                itemDef.modelOffsetY = -1;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Clean";
                itemDef.actions[4] = "Drop";
                itemDef.newModelColor = new int[]{955};
                itemDef.editedModelColor = new int[]{22418}; // do not edit
                break;

            case 4419:
                itemDef.name = "Calamint";
                itemDef.description = "A fresh herb";
                itemDef.modelID = ItemDef.forID(199).modelID;
                itemDef.modelZoom = 789;
                itemDef.rotationY = 581;
                itemDef.rotationX = 1770;
                itemDef.modelOffset1 = 8;
                itemDef.modelOffsetY = -1;
                itemDef.actions = new String[]{null, null, null, null, "Drop"};
                itemDef.newModelColor = new int[]{945, 945};
                itemDef.editedModelColor = new int[]{22418, 6806}; // do not edit
                break;

            case 4421:
                itemDef.name = "Void berries";
                itemDef.colourRedefine2 = 12;
                itemDef2 = ItemDef.forID(239);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.rotationY = itemDef2.rotationY;
                itemDef.rotationX = itemDef2.rotationX;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, null, null, "Drop"};
                break;

            case 4456:
                itemDef.name = "Furious flask (6)";
                itemDef.description = "6 doses of furious potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{65083};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 4458:
                itemDef.name = "Furious flask (5)";
                itemDef.description = "5 doses of furious potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{65083};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 4460:
                itemDef.name = "Furious flask (4)";
                itemDef.description = "4 doses of furious potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{65083};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 4462:
                itemDef.name = "Furious flask (3)";
                itemDef.description = "3 doses of furious potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{65083};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 4464:
                itemDef.name = "Furious flask (2)";
                itemDef.description = "2 doses of furious potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{65083};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 4466:
                itemDef.name = "Furious flask (1)";
                itemDef.description = "1 dose of furious potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{65083};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;


            case 14667:
                itemDef.name = "Zombie fragment";
                itemDef.modelID = ItemDef.forID(14639).modelID;
                break;
            case 15182:
                itemDef.actions[0] = "Bury";
                break;
            case 15084:
                itemDef.actions[0] = "Roll";
                itemDef.name = "Dice (up to 100)";
                itemDef2 = ItemDef.forID(15098);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 2996:
                itemDef.name = "Agility ticket";
                break;
            case 6798:
                itemDef.name = "5$ Scroll";
                break;
            case 6799:
                itemDef.name = "10$ Scroll";
                break;
            case 6800:
                itemDef.name = "25$ Scroll";
                break;
            case 6801:
                itemDef.name = "50$ Scroll";
                break;
            case 6802:
                itemDef.name = "100$ Scroll";
                break;
            case 621:
                itemDef.name = "Trivia Point Ticket";
                itemDef.description = "Click this to claim 10 Trivia Points.";
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
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
                itemDef.rotationX = 0;
                itemDef.rotationY = 350;
                itemDef.modelID = 2429;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 11998;
                itemDef.certTemplateID = 799;
                break;

            case 10025:
                itemDef.name = "Charm Box";
                itemDef.actions = new String[] {"Open", null, null, null, null};
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
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
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
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 15009;
                itemDef.certTemplateID = 799;
                break;

            case 11884:
                itemDef.actions = new String[]{"Open", null, null, null, null, null};
                break;
            case 14207:
                itemDef.name = "Potion flask";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.groundActions[2] = "Take";
                itemDef.modelID = 61741;
                itemDef.actions = new String[]{null, null, null, null, null, null};
                break;

            case 4468:
                itemDef.name = "Water flask (6)";
                itemDef.description = "A full water flask.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelID = 61732;
                itemDef.newModelColor = new int[]{37440};
                itemDef.editedModelColor = new int[]{33715};

                break;

            case 14200:
                itemDef.name = "Prayer flask (6)";
                itemDef.description = "6 doses of prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{28488};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14198:
                itemDef.name = "Prayer flask (5)";
                itemDef.description = "5 doses of prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{28488};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14196:
                itemDef.name = "Prayer flask (4)";
                itemDef.description = "4 doses of prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{28488};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14194:
                itemDef.name = "Prayer flask (3)";
                itemDef.description = "3 doses of prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{28488};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14192:
                itemDef.name = "Prayer flask (2)";
                itemDef.description = "2 doses of prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{28488};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 14190:
                itemDef.name = "Prayer flask (1)";
                itemDef.description = "1 dose of prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{28488};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14188:
                itemDef.name = "Super attack flask (6)";
                itemDef.description = "6 doses of super attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14186:
                itemDef.name = "Super attack flask (5)";
                itemDef.description = "5 doses of super attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14184:
                itemDef.name = "Super attack flask (4)";
                itemDef.description = "4 doses of super attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14182:
                itemDef.name = "Super attack flask (3)";
                itemDef.description = "3 doses of super attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14180:
                itemDef.name = "Super attack flask (2)";
                itemDef.description = "2 doses of super attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";

                itemDef.modelID = 61731;
                break;
            case 14178:
                itemDef.name = "Super attack flask (1)";
                itemDef.description = "1 dose of super attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{43848};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14176:
                itemDef.name = "Super strength flask (6)";
                itemDef.description = "6 doses of super strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14174:
                itemDef.name = "Super strength flask (5)";
                itemDef.description = "5 doses of super strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14172:
                itemDef.name = "Super strength flask (4)";
                itemDef.description = "4 doses of super strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14170:
                itemDef.name = "Super strength flask (3)";
                itemDef.description = "3 doses of super strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14168:
                itemDef.name = "Super strength flask (2)";
                itemDef.description = "2 doses of super strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 14166:
                itemDef.name = "Super strength flask (1)";
                itemDef.description = "1 dose of super strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{119};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14164:
                itemDef.name = "Super defence flask (6)";
                itemDef.description = "6 doses of super defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14162:
                itemDef.name = "Super defence flask (5)";
                itemDef.description = "5 doses of super defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14160:
                itemDef.name = "Super defence flask (4)";
                itemDef.description = "4 doses of super defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14158:
                itemDef.name = "Super defence flask (3)";
                itemDef.description = "3 doses of super defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14156:
                itemDef.name = "Super defence flask (2)";
                itemDef.description = "2 doses of super defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 14154:
                itemDef.name = "Super defence flask (1)";
                itemDef.description = "1 dose of super defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{8008};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14152:
                itemDef.name = "Ranging flask (6)";
                itemDef.description = "6 doses of ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14150:
                itemDef.name = "Ranging flask (5)";
                itemDef.description = "5 doses of ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14148:
                itemDef.name = "Ranging flask (4)";
                itemDef.description = "4 doses of ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";

                itemDef.modelID = 61764;
                break;
            case 14146:
                itemDef.name = "Ranging flask (3)";
                itemDef.description = "3 doses of ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14144:
                itemDef.name = "Ranging flask (2)";
                itemDef.description = "2 doses of ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 14142:
                itemDef.name = "Ranging flask (1)";
                itemDef.description = "1 dose of ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{36680};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14140:
                itemDef.name = "Super antipoison flask (6)";
                itemDef.description = "6 doses of super antipoison.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14138:
                itemDef.name = "Super antipoison flask (5)";
                itemDef.description = "5 doses of super antipoison.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14136:
                itemDef.name = "Super antipoison flask (4)";
                itemDef.description = "4 doses of super antipoison.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14134:
                itemDef.name = "Super antipoison flask (3)";
                itemDef.description = "3 doses of super antipoison.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14132:
                itemDef.name = "Super antipoison flask (2)";
                itemDef.description = "2 doses of super antipoison.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 4706:
                itemDef.modelID = 62692;
                itemDef.name = "Zaryte bow";
                itemDef.modelZoom = 1703;
                itemDef.rotationY = 221;
                itemDef.rotationX = 404;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -13;
                itemDef.maleEquip1 = 62750;
                itemDef.femaleEquip1 = 62750;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                break;
            case 4705:
                itemDef.modelID = 6701;
                itemDef.name = "Abyssal vine whip";
                itemDef.description = "A weapon from the Abyss, interlaced with a vicious jade vine.";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 324;
                itemDef.rotationX = 1808;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 3;
                itemDef.maleEquip1 = 6700;
                itemDef.femaleEquip1 = 6700;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Weild";
                break;
            case 14130:
                itemDef.name = "Super antipoison flask (1)";
                itemDef.description = "1 dose of super antipoison.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{62404};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14128:
                itemDef.name = "Saradomin brew flask (6)";
                itemDef.description = "6 doses of saradomin brew.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                /*	itemDef.anInt196 = 40;
			itemDef.anInt184 = 200;*/
                break;
            case 14126:
                itemDef.name = "Saradomin brew flask (5)";
                itemDef.description = "5 doses of saradomin brew.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                /*	itemDef.anInt196 = 40;
			itemDef.anInt184 = 200;*/
                break;
            case 14124:
                itemDef.name = "Saradomin brew flask (4)";
                itemDef.description = "4 doses of saradomin brew.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                /*	itemDef.anInt196 = 40;
			itemDef.anInt184 = 200;*/
                break;
            case 14122:
                itemDef.name = "Saradomin brew flask (3)";
                itemDef.description = "3 doses of saradomin brew.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                /*	itemDef.anInt196 = 40;
			itemDef.anInt184 = 200;*/
                break;
            case 14419:
                itemDef.name = "Saradomin brew flask (2)";
                itemDef.description = "2 doses of saradomin brew.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                /*	itemDef.anInt196 = 40;
			itemDef.anInt184 = 200;*/
                break;
            case 14417:
                itemDef.name = "Saradomin brew flask (1)";
                itemDef.description = "1 dose of saradomin brew.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                /*	itemDef.anInt196 = 40;
			itemDef.anInt184 = 200;*/
                break;
            case 14415:
                itemDef.name = "Super restore flask (6)";
                itemDef.description = "6 doses of super restore potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14413:
                itemDef.name = "Super restore flask (5)";
                itemDef.description = "5 doses of super restore potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14411:
                itemDef.name = "Super restore flask (4)";
                itemDef.description = "4 doses of super restore potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14409:
                itemDef.name = "Super restore flask (3)";
                itemDef.description = "3 doses of super restore potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14407:
                itemDef.name = "Super restore flask (2)";
                itemDef.description = "2 doses of super restore potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 14405:
                itemDef.name = "Super restore flask (1)";
                itemDef.description = "1 dose of super restore potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{62135};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14403:
                itemDef.name = "Magic flask (6)";
                itemDef.description = "6 doses of magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{37440};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14401:
                itemDef.name = "Magic flask (5)";
                itemDef.description = "5 doses of magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{37440};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14399:
                itemDef.name = "Magic flask (4)";
                itemDef.description = "4 doses of magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{37440};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14397:
                itemDef.name = "Magic flask (3)";
                itemDef.description = "3 doses of magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{37440};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14395:
                itemDef.name = "Magic flask (2)";
                itemDef.description = "2 doses of magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{37440};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 14393:
                itemDef.name = "Magic flask (1)";
                itemDef.description = "1 dose of magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{37440};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14385:
                itemDef.name = "Recover special flask (6)";
                itemDef.description = "6 doses of recover special.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14383:
                itemDef.name = "Recover special flask (5)";
                itemDef.description = "5 doses of recover special.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14381:
                itemDef.name = "Recover special flask (4)";
                itemDef.description = "4 doses of recover special.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14379:
                itemDef.name = "Recover special flask (3)";
                itemDef.description = "3 doses of recover special.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14377:
                itemDef.name = "Recover special flask (2)";
                itemDef.description = "2 doses of recover special.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 14375:
                itemDef.name = "Recover special flask (1)";
                itemDef.description = "1 dose of recover special.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{38222};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14373:
                itemDef.name = "Extreme attack flask (6)";
                itemDef.description = "6 doses of extreme attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14371:
                itemDef.name = "Extreme attack flask (5)";
                itemDef.description = "5 doses of extreme attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14369:
                itemDef.name = "Extreme attack flask (4)";
                itemDef.description = "4 doses of extreme attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14367:
                itemDef.name = "Extreme attack flask (3)";
                itemDef.description = "3 doses of extreme attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14365:
                itemDef.name = "Extreme attack flask (2)";
                itemDef.description = "2 doses of extreme attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 14363:
                itemDef.name = "Extreme attack flask (1)";
                itemDef.description = "1 dose of extreme attack potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{33112};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14361:
                itemDef.name = "Extreme strength flask (6)";
                itemDef.description = "6 doses of extreme strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14359:
                itemDef.name = "Extreme strength flask (5)";
                itemDef.description = "5 doses of extreme strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14357:
                itemDef.name = "Extreme strength flask (4)";
                itemDef.description = "4 doses of extreme strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14355:
                itemDef.name = "Extreme strength flask (3)";
                itemDef.description = "3 doses of extreme strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14353:
                itemDef.name = "Extreme strength flask (2)";
                itemDef.description = "2 doses of extreme strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 14351:
                itemDef.name = "Extreme strength flask (1)";
                itemDef.description = "1 dose of extreme strength potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{127};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14349:
                itemDef.name = "Extreme defence flask (6)";
                itemDef.description = "6 doses of extreme defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14347:
                itemDef.name = "Extreme defence flask (5)";
                itemDef.description = "5 doses of extreme defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14345:
                itemDef.name = "Extreme defence flask (4)";
                itemDef.description = "4 doses of extreme defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14343:
                itemDef.name = "Extreme defence flask (3)";
                itemDef.description = "3 doses of extreme defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14341:
                itemDef.name = "Extreme defence flask (2)";
                itemDef.description = "2 doses of extreme defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 14339:
                itemDef.name = "Extreme defence flask (1)";
                itemDef.description = "1 dose of extreme defence potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10198};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14337:
                itemDef.name = "Extreme magic flask (6)";
                itemDef.description = "6 doses of extreme magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14335:
                itemDef.name = "Extreme magic flask (5)";
                itemDef.description = "5 doses of extreme magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14333:
                itemDef.name = "Extreme magic flask (4)";
                itemDef.description = "4 doses of extreme magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14331:
                itemDef.name = "Extreme magic flask (3)";
                itemDef.description = "3 doses of extreme magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14329:
                itemDef.name = "Extreme magic flask (2)";
                itemDef.description = "2 doses of extreme magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 14327:
                itemDef.name = "Extreme magic flask (1)";
                itemDef.description = "1 dose of extreme magic potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{33490};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14325:
                itemDef.name = "Extreme ranging flask (6)";
                itemDef.description = "6 doses of extreme ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14323:
                itemDef.name = "Extreme ranging flask (5)";
                itemDef.description = "5 doses of extreme ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14321:
                itemDef.name = "Extreme ranging flask (4)";
                itemDef.description = "4 doses of extreme ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14319:
                itemDef.name = "Extreme ranging flask (3)";
                itemDef.description = " 3 doses of extreme ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14317:
                itemDef.name = "Extreme ranging flask (2)";
                itemDef.description = "2 doses of extreme ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 14315:
                itemDef.name = "Extreme ranging flask (1)";
                itemDef.description = "1 dose of extreme ranging potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{13111};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14313:
                itemDef.name = "Super prayer flask (6)";
                itemDef.description = "6 doses of super prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14311:
                itemDef.name = "Super prayer flask (5)";
                itemDef.description = "5 doses of super prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14309:
                itemDef.name = "Super prayer flask (4)";
                itemDef.description = "4 doses of super prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14307:
                itemDef.name = "Super prayer flask (3)";
                itemDef.description = "3 doses of super prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14305:
                itemDef.name = "Super prayer flask (2)";
                itemDef.description = "2 doses of super prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 14303:
                itemDef.name = "Super prayer flask (1)";
                itemDef.description = "1 dose of super prayer potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{3016};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
                break;
            case 14301:
                itemDef.name = "Overload flask (6)";
                itemDef.description = "6 doses of overload potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14299:
                itemDef.name = "Overload flask (5)";
                itemDef.description = "5 doses of overload potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 14297:
                itemDef.name = "Overload flask (4)";
                itemDef.description = "4 doses of overload potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 14295:
                itemDef.name = "Overload flask (3)";
                itemDef.description = "3 doses of overload potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 14293:
                itemDef.name = "Overload flask (2)";
                itemDef.description = "2 doses of overload potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 14291:
                itemDef.name = "Overload flask (1)";
                itemDef.description = "1 dose of overload potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{0};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.groundActions[2] = "Take";

                itemDef.modelID = 61812;
                break;
            case 14289:
                itemDef.name = "Prayer renewal flask (6)";
                itemDef.description = "6 doses of prayer renewal.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61732;
                break;
            case 14287:
                itemDef.name = "Prayer renewal flask (5)";
                itemDef.description = "5 doses of prayer renewal.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61729;
                break;
            case 15123:
                itemDef.name = "Prayer renewal flask (4)";
                itemDef.description = "4 doses of prayer renewal potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61764;
                break;
            case 15121:
                itemDef.name = "Prayer renewal flask (3)";
                itemDef.description = "3 doses of prayer renewal potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61727;
                break;
            case 15119:
                itemDef.name = "Prayer renewal flask (2)";
                itemDef.description = "2 doses of prayer renewal potion.";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61731;
                break;
            case 7340:
                itemDef.name = "Prayer renewal flask (1)";
                itemDef.description = "1 dose of prayer renewal potion";
                itemDef.modelZoom = 804;
                itemDef.rotationY = 131;
                itemDef.rotationX = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{926};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, null};
                itemDef.modelID = 61812;
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
                itemDef.actions = new String[]{"Activate", null, "Social-Slayer", null, "Destroy"};
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
        /*    case 14044:
                itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.editedModelColor = new int[1];
    			itemDef.newModelColor = new int[1];
    			itemDef.editedModelColor[0] = 926;
    			itemDef.newModelColor[0] = 0;
    			itemDef.modelID = 2635;
    			itemDef.modelZoom = 440;
    			itemDef.rotationX = 76;
    			itemDef.rotationY = 1850;

    			itemDef.modelOffsetX = 1;
    			itemDef.modelOffsetY = 1;
    			itemDef.maleEquip1 = 187;
    			itemDef.femaleEquip1 = 363;
    			itemDef.anInt175 = 29;
    			itemDef.stackable = false;
    			itemDef.anInt197 = 87;
    			itemDef.name = "Black partyhat";
    			itemDef.description = "A partyhat.";
    			break; */
            case 14044:
                itemDef.name = "Black partyhat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare black partyhat";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 0;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14045:
                itemDef.name = "Lime partyhat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare lime partyhat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 17350;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14046:
                itemDef.name = "Pink partyhat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare pink partyhat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 57300;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14047:
                itemDef.name = "Sky Blue partyhat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare sky blue partyhat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 689484;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14048:
                itemDef.name = "Lava partyhat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare lava partyhat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 6073;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14049:
                itemDef.name = "Pink Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare pink santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 57300;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14050:
                itemDef.name = "Black Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare black santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 0;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14051:
                itemDef.name = "Lime Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare lime santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 17350;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 14052:
                itemDef.name = "Orange Santa hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 6073;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14053:
                itemDef.name = "Lava Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare lava santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 6073;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 15152:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 17350;
                itemDef.modelID = 2635;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 76;
                itemDef.rotationY = 1850;

                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;

                itemDef.femaleEquip1 = 363;

                itemDef.name = "Lime partyhat";
                itemDef.description = "A partyhat.";
            case 14501:
                itemDef.modelID = 44574;
                itemDef.maleEquip1 = 43693;
                itemDef.femaleEquip1 = 43693;
                break;
            case 13262:

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
                itemDef.name = itemDef2.name;
                itemDef.actions = itemDef2.actions;
                break;
            case 10942:
                itemDef.name = "$5 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDef.forID(761);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 10934:
                itemDef.name = "$10 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDef.forID(761);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 10935:
                itemDef.name = "$25 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDef.forID(761);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 10943:
                itemDef.name = "$50 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDef.forID(761);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 17291:
                itemDef.name = "Blood necklace";
                itemDef.actions = new String[]{null, "Wear", null, null, null, null};
                break;
            case 6199:
                itemDef.name = "Mystery Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;

            case 7100:
                itemDef.name = "Pet Mystery Box";
                ItemDef mbox = ItemDef.forID(6199);
                itemDef.modelID = mbox.modelID;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.colourRedefine2 = 37463;
                itemDef.stackable = true;
                break;

            case 7102:
                itemDef.name = "Beginner dragon slayer Box";
                mbox = ItemDef.forID(6199);
                itemDef.modelID = 95152;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.stackable = true;
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {54,54,54,54,54,54,54,54,54,54,54,54,54,54};
                break;

            case 20200:
                itemDef.name = "Car bones";
                mbox = ItemDef.forID(18830);
                itemDef.modelID = 95056;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Bury";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {54,54,54,54,54,54,54,54,54,54,54,54,54,54};
                break;

            case 7104:
                mbox = ItemDef.forID(6199);
                itemDef.name = "Predator Dragon Slayer Box";
                itemDef.modelID = 95152;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.stackable = true;

                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {55,55,55,55,55,55,55,55,55,55,55,55,55,55};
                break;

            case 20201:
                itemDef.name = "Pinguin bones";
                mbox = ItemDef.forID(18830);
                itemDef.modelID = 95056;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Bury";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {67,67,67,67,67,67,67,67,67,67,67,67,67,67};
                break;
            case 7106:
                mbox = ItemDef.forID(6199);
                itemDef.name = "Deadly Dragonslayer Box";
                itemDef.modelID = 95152;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.stackable = true;

                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {57,57,57,57,57,57,57,57,57,57,57,57,57,57};
                break;

            case 20202:
                itemDef.name = "Terror dog bones";
                mbox = ItemDef.forID(18830);
                itemDef.modelID = 95056;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Bury";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {55,55,55,55,55,55,55,55,55,55,55,55,55,55};
                break;

            case 7108:
                mbox = ItemDef.forID(6199);
                itemDef.name = "Donkey Mystery Box";
                itemDef.modelID = 95152;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.stackable = true;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {66,66,66,66,66,66,66,66,66,66,66,66,66,66};
                break;

            case 20203:
                itemDef.name = "Nature Bones";
                mbox = ItemDef.forID(18830);
                itemDef.modelID = 95056;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Bury";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {16,16,16,16,16,16,16,16,16,16,16,16,16,16};
                break;
            case 7110:
                mbox = ItemDef.forID(6199);
                itemDef.name = "Oblivion Mystery Box";
                itemDef.modelID = 95152;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.stackable = true;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {56,56,56,56,56,56,56,56,56,56,56,56,56,56};
                break;

            case 20204:
                itemDef.name = "Aurelia Bones";
                mbox = ItemDef.forID(18830);
                itemDef.modelID = 95056;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Bury";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {56,56,56,56,56,56,56,56,56,56,56,56,56,56};
                break;
            case 7112:
                mbox = ItemDef.forID(6199);
                itemDef.name = "Bandos avatar Mystery Box";
                itemDef.modelID = 95152;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.stackable = true;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {69,69,69,69,69,69,69,69,69,69,69,69,69,69};
                break;

            case 20205:
                itemDef.name = "Livyathann bones";
                mbox = ItemDef.forID(18830);
                itemDef.modelID = 95056;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Bury";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {69,69,69,69,69,69,69,69,69,69,69,69,69,69};
                break;
            case 7114:
                mbox = ItemDef.forID(6199);
                itemDef.name = "Abbadon Mystery Box";
                itemDef.modelID = 95152;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.stackable = true;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {52,52,52,52,52,52,52,52,52,52,52,52,52,52};
                break;

            case 20206:
                itemDef.name = "Abbadon Bone";
                mbox = ItemDef.forID(18830);
                itemDef.modelID = 95056;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Bury";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]     {52,52,52,52,52,52,52,52,52,52,52,52,52,52};
                break;
            case 7116:
                mbox = ItemDef.forID(6199);
                itemDef.name = "Infernal groudon Mystery Box";
                itemDef.modelID = 95152;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.stackable = true;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {60,60,60,60,60,60,60,60,60,60,60,60,60,60};
                break;

            case 20207:
                itemDef.name = "Infernal Groudon Bone";
                mbox = ItemDef.forID(18830);
                itemDef.modelID = 95056;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Bury";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {60,60,60,60,60,60,60,60,60,60,60,60,60,60};
                break;
            case 20210:
                itemDef.name = "Baphomet Bone";
                mbox = ItemDef.forID(18830);
                itemDef.modelID = 95056;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Bury";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
                break;
            case 7118:
                mbox = ItemDef.forID(6199);
                itemDef.name = "Donator Box";
                itemDef.modelID = 95152;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.stackable = true;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {59,59,59,59,59,59,59,59,59,59,59,59,59,597};
                break;
            case 20861:
                mbox = ItemDef.forID(20861);
                itemDef.name = "Christmas Mystery Box";
                itemDef.modelID = 140021;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.stackable = true;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {59,59,59,59,59,59,59,59,59,59,59,59,59,597};
                break;
            case 20862:
                itemDef.modelID = 140025;
                itemDef.name = "@red@Christmas @blu@scythe";
                itemDef.description = "This Scythe looks insane!";
                itemDef.modelZoom = 2500;
                itemDef.rotationY = 228;
                itemDef.rotationX = 1985;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = -55;
                itemDef.stackable = false;
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 140026;
                itemDef.femaleEquip1 = 140026;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 7120:
                mbox = ItemDef.forID(6199);
                itemDef.name = "Belerion Mystery Box";
                itemDef.modelID = 95152;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.stackable = true;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {52,52,52,52,52,52,52,52,52,52,52,52,52,52};
                break;
            case 7122:
                mbox = ItemDef.forID(6199);
                itemDef.name = "Le'fosh Mbox";
                itemDef.modelID = 95152;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.stackable = true;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {71,71,71,71,71,71,71,71,71,71,71,71,71,71};
                break;
            case 7124:
                mbox = ItemDef.forID(6199);
                itemDef.name = "Baphomet Mbox";
                itemDef.modelID = 95152;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.stackable = true;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
                break;

            case 20208:
                itemDef.name = "Belerion Bone";
                mbox = ItemDef.forID(18830);
                itemDef.modelID = 95056;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Bury";
                itemDef.modelZoom = mbox.modelZoom;
                itemDef.modelOffsetY = mbox.modelOffsetY;
                itemDef.modelOffsetX = mbox.modelOffsetX;
                itemDef.modelOffset1 = mbox.modelOffset1;
                itemDef.rotationY = mbox.rotationY;
                itemDef.rotationX = mbox.rotationX;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {52,52,52,52,52,52,52,52,52,52,52,52,52,52};
                break;
            case 15501:
                itemDef.name = "Legendary Mystery Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 6568: // To replace Transparent black with opaque black.
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;

            case 619:
                itemDef.name = "Donation Point";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                break;
            case 19886:
                itemDef.name = "Dire Wolf necklace";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Upgrade";
                itemDef.actions[4] = "Drop";
                break;
            case 4447:
                itemDef.name = "Donator Lamp";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                break;
            case 14017:
                itemDef.name = "Brackish blade";
                itemDef.modelZoom = 1488;
                itemDef.rotationY = 276;
                itemDef.rotationX = 1580;
                itemDef.modelOffsetY = 1;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", null, null, "Drop"};
                itemDef.modelID = 64593;
                itemDef.maleEquip1 = 64704;
                itemDef.femaleEquip2 = 64704;
                break;

            case 15220:
                itemDef.name = "Berserker ring (i)";
                itemDef.modelZoom = 600;
                itemDef.rotationY = 324;
                itemDef.rotationX = 1916;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -15;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 7735; // if it doesn't work try 7735
                itemDef.maleEquip1 = -1;
                // itemDefinition.maleArm = -1;
                itemDef.femaleEquip1 = -1;
                // itemDefinition.femaleArm = -1;
                break;

            case 14019:
                itemDef.modelID = 65262;
                itemDef.name = "Max Cape";
                itemDef.description = "A cape worn by those who've achieved 99 in all skills.";
                itemDef.modelZoom = 1385;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                itemDef.maleEquip1 = 65300;
                itemDef.femaleEquip1 = 65322;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Customize";
                itemDef.editedModelColor = new int[4];
                itemDef.newModelColor = new int[4];
                itemDef.editedModelColor[0] = 65214; //red
                itemDef.editedModelColor[1] = 65200; // darker red
                itemDef.editedModelColor[2] = 65186; //dark red
                itemDef.editedModelColor[3] = 62995; //darker red
                itemDef.newModelColor[0] = 65214;//cape
                itemDef.newModelColor[1] = 65200;//cape
                itemDef.newModelColor[2] = 65186;//outline
                itemDef.newModelColor[3] = 62995;//cape
                break;
            case 14020:
                itemDef.name = "Veteran hood";
                itemDef.description = "A hood worn by Chivalry's veterans.";
                itemDef.modelZoom = 760;
                itemDef.rotationY = 11;
                itemDef.rotationX = 81;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.modelID = 65271;
                itemDef.maleEquip1 = 65289;
                itemDef.femaleEquip1 = 65314;
                break;
            case 14021:
                itemDef.modelID = 65261;
                itemDef.name = "Veteran Cape";
                itemDef.description = "A cape worn by Chivalry's veterans.";
                itemDef.modelZoom = 760;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                itemDef.maleEquip1 = 65305;
                itemDef.femaleEquip1 = 65318;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 14022:
                itemDef.modelID = 65270;
                itemDef.name = "Completionist Cape";
                itemDef.description = "We'd pat you on the back, but this cape would get in the way.";
                itemDef.modelZoom = 1385;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                itemDef.maleEquip1 = 65297;
                itemDef.femaleEquip1 = 65297;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
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

            case 14004:
                itemDef.name = "Staff of light";
                itemDef.modelID = 51845;
                itemDef.editedModelColor = new int[11];
                itemDef.newModelColor = new int[11];
                itemDef.editedModelColor[0] = 7860;
                itemDef.newModelColor[0] = 38310;
                itemDef.editedModelColor[1] = 7876;
                itemDef.newModelColor[1] = 38310;
                itemDef.editedModelColor[2] = 7892;
                itemDef.newModelColor[2] = 38310;
                itemDef.editedModelColor[3] = 7884;
                itemDef.newModelColor[3] = 38310;
                itemDef.editedModelColor[4] = 7868;
                itemDef.newModelColor[4] = 38310;
                itemDef.editedModelColor[5] = 7864;
                itemDef.newModelColor[5] = 38310;
                itemDef.editedModelColor[6] = 7880;
                itemDef.newModelColor[6] = 38310;
                itemDef.editedModelColor[7] = 7848;
                itemDef.newModelColor[7] = 38310;
                itemDef.editedModelColor[8] = 7888;
                itemDef.newModelColor[8] = 38310;
                itemDef.editedModelColor[9] = 7872;
                itemDef.newModelColor[9] = 38310;
                itemDef.editedModelColor[10] = 7856;
                itemDef.newModelColor[10] = 38310;
                itemDef.modelZoom = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset1 = 0;
                itemDef.maleEquip1 = 51795;
                itemDef.femaleEquip1 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 14005:
                itemDef.name = "Staff of light";
                itemDef.modelID = 51845;
                itemDef.editedModelColor = new int[11];
                itemDef.newModelColor = new int[11];
                itemDef.editedModelColor[0] = 7860;
                itemDef.newModelColor[0] = 432;
                itemDef.editedModelColor[1] = 7876;
                itemDef.newModelColor[1] = 432;
                itemDef.editedModelColor[2] = 7892;
                itemDef.newModelColor[2] = 432;
                itemDef.editedModelColor[3] = 7884;
                itemDef.newModelColor[3] = 432;
                itemDef.editedModelColor[4] = 7868;
                itemDef.newModelColor[4] = 432;
                itemDef.editedModelColor[5] = 7864;
                itemDef.newModelColor[5] = 432;
                itemDef.editedModelColor[6] = 7880;
                itemDef.newModelColor[6] = 432;
                itemDef.editedModelColor[7] = 7848;
                itemDef.newModelColor[7] = 432;
                itemDef.editedModelColor[8] = 7888;
                itemDef.newModelColor[8] = 432;
                itemDef.editedModelColor[9] = 7872;
                itemDef.newModelColor[9] = 432;
                itemDef.editedModelColor[10] = 7856;
                itemDef.newModelColor[10] = 432;
                itemDef.modelZoom = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset1 = 0;
                itemDef.maleEquip1 = 51795;
                itemDef.femaleEquip1 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 14006:
                itemDef.name = "Staff of light";
                itemDef.modelID = 51845;
                itemDef.editedModelColor = new int[11];
                itemDef.newModelColor = new int[11];
                itemDef.editedModelColor[0] = 7860;
                itemDef.newModelColor[0] = 24006;
                itemDef.editedModelColor[1] = 7876;
                itemDef.newModelColor[1] = 24006;
                itemDef.editedModelColor[2] = 7892;
                itemDef.newModelColor[2] = 24006;
                itemDef.editedModelColor[3] = 7884;
                itemDef.newModelColor[3] = 24006;
                itemDef.editedModelColor[4] = 7868;
                itemDef.newModelColor[4] = 24006;
                itemDef.editedModelColor[5] = 7864;
                itemDef.newModelColor[5] = 24006;
                itemDef.editedModelColor[6] = 7880;
                itemDef.newModelColor[6] = 24006;
                itemDef.editedModelColor[7] = 7848;
                itemDef.newModelColor[7] = 24006;
                itemDef.editedModelColor[8] = 7888;
                itemDef.newModelColor[8] = 24006;
                itemDef.editedModelColor[9] = 7872;
                itemDef.newModelColor[9] = 24006;
                itemDef.editedModelColor[10] = 7856;
                itemDef.newModelColor[10] = 24006;
                itemDef.modelZoom = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset1 = 0;
                itemDef.maleEquip1 = 51795;
                itemDef.femaleEquip1 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14007:
                itemDef.name = "Staff of light";
                itemDef.modelID = 51845;
                itemDef.editedModelColor = new int[11];
                itemDef.newModelColor = new int[11];
                itemDef.editedModelColor[0] = 7860;
                itemDef.newModelColor[0] = 14285;
                itemDef.editedModelColor[1] = 7876;
                itemDef.newModelColor[1] = 14285;
                itemDef.editedModelColor[2] = 7892;
                itemDef.newModelColor[2] = 14285;
                itemDef.editedModelColor[3] = 7884;
                itemDef.newModelColor[3] = 14285;
                itemDef.editedModelColor[4] = 7868;
                itemDef.newModelColor[4] = 14285;
                itemDef.editedModelColor[5] = 7864;
                itemDef.newModelColor[5] = 14285;
                itemDef.editedModelColor[6] = 7880;
                itemDef.newModelColor[6] = 14285;
                itemDef.editedModelColor[7] = 7848;
                itemDef.newModelColor[7] = 14285;
                itemDef.editedModelColor[8] = 7888;
                itemDef.newModelColor[8] = 14285;
                itemDef.editedModelColor[9] = 7872;
                itemDef.newModelColor[9] = 14285;
                itemDef.editedModelColor[10] = 7856;
                itemDef.newModelColor[10] = 14285;
                itemDef.modelZoom = 2256;
                itemDef.rotationX = 456;
                itemDef.rotationY = 513;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffset1 = 0;
                itemDef.maleEquip1 = 51795;
                itemDef.femaleEquip1 = 51795;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14003:
                itemDef.name = "Robin hood hat";
                itemDef.modelID = 3021;
                itemDef.editedModelColor = new int[3];
                itemDef.newModelColor = new int[3];
                itemDef.editedModelColor[0] = 15009;
                itemDef.newModelColor[0] = 30847;
                itemDef.editedModelColor[1] = 17294;
                itemDef.newModelColor[1] = 32895;
                itemDef.editedModelColor[2] = 15252;
                itemDef.newModelColor[2] = 30847;
                itemDef.modelZoom = 650;
                itemDef.rotationY = 2044;
                itemDef.rotationX = 256;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 3378;
                itemDef.femaleEquip1 = 3382;
                itemDef.maleDialogue = 3378;
                itemDef.femaleDialogue = 3382;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 18901:
                itemDef.modelID = 80010;
                itemDef.name = "Rainbow Spirit Shield";
                itemDef.description = "It's a Rainbow spirit shield";
                itemDef.modelZoom = 1616;
                itemDef.rotationY = 396;
                itemDef.rotationX = 1050;
                itemDef.modelOffsetY = -3;
                itemDef.modelOffsetX = 4;
                itemDef.maleEquip1 = 80011;
                itemDef.femaleEquip1 = 80011;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 20090:
                itemDef.modelID = 95064;
                itemDef.name = "@yel@Yanille Boots";
                itemDef.description = "It is the Yanille boots!";
                itemDef.modelZoom = 1616;
                itemDef.rotationY = 396;
                itemDef.rotationX = 1050;
                itemDef.modelOffsetY = -3;
                itemDef.modelOffsetX = 4;
                itemDef.maleEquip1 = 95064;
                itemDef.femaleEquip1 = 95064;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 6934:
                itemDef.name = "Brutal TokHaar-Kal";
                itemDef.maleEquip1 = 62575;
                itemDef.femaleEquip1 = 62582;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.modelOffset1 = -4;
                itemDef.modelID = 62592;
                itemDef.colourRedefine2 = 2;
                itemDef.stackable = false;
                itemDef.description = "A cape made of ancient, enchanted rocks.";
                itemDef.modelZoom = 1616;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Upgrade";
                itemDef.actions[4] = "Drop";
                itemDef.modelOffset1 = 0;
                itemDef.rotationY = 339;
                itemDef.rotationX = 192;
                break;
            case 6532:
                itemDef.name = "Kryptic TokHaar-Kal";
                itemDef.maleEquip1 = 62575;
                itemDef.femaleEquip1 = 62582;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.modelOffset1 = -4;
                itemDef.modelID = 62592;
                itemDef.colourRedefine2 = 663333;
                itemDef.stackable = false;
                itemDef.description = "A cape made of ancient, enchanted rocks.";
                itemDef.modelZoom = 1616;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Upgrade";
                itemDef.actions[4] = "Drop";
                itemDef.modelOffset1 = 0;
                itemDef.rotationY = 339;
                itemDef.rotationX = 192;
                break;
            case 8681:
                itemDef.name ="@yel@TOKEN";
                itemDef.colourRedefine2 = 336633;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = true;
                break;
            case 6115:
                itemDef.name ="@yel@Blessed Bonecrusher";
                itemDef.colourRedefine2 = 223333;
                itemDef2 = ItemDef.forID(18337);
                itemDef.modelID = itemDef2.modelID;
                itemDef.colourRedefine2 = 336633;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.stackable = false;
                break;
            case 6531:
                itemDef.name ="@red@Corrupt Ring of Wealth";
                itemDef.colourRedefine2 = 223333;
                itemDef2 = ItemDef.forID(2572);
                itemDef.modelID = itemDef2.modelID;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 6190:
                itemDef.name = "Loyalist top";
                itemDef.colourRedefine2 = 663333;
                itemDef2 = ItemDef.forID(15040);
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
            case 6191:
                itemDef.name = "Loyalist trousers";
                itemDef.colourRedefine2 = 663333;
                itemDef2 = ItemDef.forID(15041);
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
            case 6192:
                itemDef.name = "Loyalist skirt";
                itemDef.colourRedefine2 = 663333;
                itemDef2 = ItemDef.forID(15042);
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
            case 6193:
                itemDef.name = "Loyalist cap";
                itemDef.colourRedefine2 = 663333;
                itemDef2 = ItemDef.forID(15039);
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
            case 6194:
                itemDef.name = "Loyalist gloves";
                itemDef.colourRedefine2 = 663333;
                itemDef2 = ItemDef.forID(15044);
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
            case 6195:
                itemDef.name = "Loyalist boots";
                itemDef.colourRedefine2 = 663333;
                itemDef2 = ItemDef.forID(15043);
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
            case 6196:
                itemDef.name = "@gre@ Janeâ€˜s Rapier";
                itemDef.colourRedefine2 = 663333;
                itemDef2 = ItemDef.forID(4068);
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
            case 13196:
                itemDef.modelID = 62714;
                itemDef.name = "Nature Torva full helm";
                itemDef.description = "Yanille full helm";
                itemDef.colourRedefine2 = 663333;
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 62738;
                itemDef.femaleEquip1 = 62754;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 2717:
                itemDef.name = "Slayer Casket";
                itemDef.modelID = 67574;
                itemDef.editedModelColor = new int[] {60};
                itemDef.newModelColor = new int[]    {57};
                break;
            case 3666:
                itemDef.modelID = 91751;
                itemDef.name = "Lava scythe";
                itemDef.description = "The lava looks hot";
                itemDef.modelZoom = 2500;
                itemDef.rotationY = 228;
                itemDef.rotationX = 1985;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = -55;
                itemDef.stackable = false;
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 91752;
                itemDef.femaleEquip1 = 91752;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 20900:
                itemDef.name = "Slayer key";
                itemDef2 = ItemDef.forID(989);
                itemDef.modelID = 67575;
                itemDef.maleEquip1 = itemDef2.maleEquip1;
                itemDef.femaleEquip1 = itemDef2.femaleEquip1;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.rotationY = itemDef2.rotationY;
                itemDef.rotationX = itemDef2.rotationX;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.editedModelColor = new int[] {60};
                itemDef.newModelColor = new int[]    {57};
                break;
            case 20901:
                itemDef.name = "@red@Yanille @yel@key";
                itemDef2 = ItemDef.forID(989);
                itemDef.modelID = 67575;
                itemDef.maleEquip1 = itemDef2.maleEquip1;
                itemDef.femaleEquip1 = itemDef2.femaleEquip1;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.rotationY = itemDef2.rotationY;
                itemDef.rotationX = itemDef2.rotationX;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.editedModelColor = new int[] {60};
                itemDef.newModelColor = new int[]    {55};
                break;
            case 21074:
                itemDef.name = "@red@Raids @yel@key";
                itemDef2 = ItemDef.forID(989);
                itemDef.modelID = 67575;
                itemDef.maleEquip1 = itemDef2.maleEquip1;
                itemDef.femaleEquip1 = itemDef2.femaleEquip1;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.rotationY = itemDef2.rotationY;
                itemDef.rotationX = itemDef2.rotationX;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.editedModelColor = new int[] {60};
                itemDef.newModelColor = new int[]    {52};
                break;
            case 3665:
                itemDef.modelID = 91783;
                itemDef.name = "Pennywise balloon";
                itemDef.description = "I hate clowns";
                itemDef.modelZoom = 1500;
                itemDef.rotationY = 228;
                itemDef.rotationX = 1985;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = -55;
                itemDef.stackable = false;
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 91784;
                itemDef.femaleEquip1 = 91784;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 3664:
                itemDef.modelID = 91773;
                itemDef.name = "Pennywise hat";
                itemDef.description = "I hate clowns";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 91754;
                itemDef.femaleEquip1 = 91754;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 91754;
                itemDef.femaleDialogue = 91754;
                break;
            case 3662:
                itemDef.modelID = 91782;
                itemDef.name = "Pennywise top";
                itemDef.description = "I hate clowns";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 91776;
                itemDef.femaleEquip1 = 91776;
                itemDef.maleEquip2 = 91791;
                itemDef.femaleEquip2 = 91791;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 3663:
                itemDef.modelID = 91781;
                itemDef.name = "Pennywise pants";
                itemDef.description = "I hate clowns";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 91775;
                itemDef.femaleEquip1 = 91775;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 13197:
                itemDef.modelID = 62699;
                itemDef.name = "Nature Torva platebody";
                itemDef.description = "Yanille platebody";
                itemDef.colourRedefine2 = 663333;
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 62746;
                itemDef.femaleEquip1 = 62762;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;

            case 13198:
                itemDef.modelID = 62701;
                itemDef.name = "Nature Torva platelegs";
                itemDef.description = "Yanille platelegs";
                itemDef.colourRedefine2 = 663333;
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 62743;
                itemDef.femaleEquip1 = 62760;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;

                /*case 20075:
	                itemDef.modelID = 95033;
	                itemDef.name = "Wyrm mask";
	                itemDef.description = "A mask from the Wyrm Queen";
	                itemDef.modelZoom = 672;
	                itemDef.rotationY = 85;
	                itemDef.rotationX = 1867;
	                itemDef.modelOffset1 = 0;
	                itemDef.modelOffsetY = -3;
	                itemDef.maleEquip1 = 95034;
	                itemDef.femaleEquip1 = 95034;
	                itemDef.groundActions = new String[5];
	                itemDef.groundActions[2] = "Take";
	                itemDef.actions = new String[5];
	                itemDef.actions[1] = "Wear";
	                itemDef.actions[4] = "Drop";
	                itemDef.maleDialogue = 62729;
	                itemDef.femaleDialogue = 62729;
	                break;
				case 20076:
	                itemDef.modelID = 95035;
	                itemDef.name = "Wyrm body";
	                itemDef.description = "A body from the Wyrm Queen";
	                itemDef.modelZoom = 1506;
	                itemDef.rotationY = 473;
	                itemDef.rotationX = 2042;
	                itemDef.modelOffset1 = 0;
	                itemDef.modelOffsetY = 0;
	                itemDef.maleEquip1 = 95036;
	                itemDef.femaleEquip1 = 95036;
	                itemDef.groundActions = new String[5];
	                itemDef.groundActions[2] = "Take";
	                itemDef.actions = new String[5];
	                itemDef.actions[1] = "Wear";
	                itemDef.actions[4] = "Drop";
	                break;

					case 20078:
	                itemDef.modelID = 95037;
	                itemDef.name = "Wyrm legs";
	                itemDef.description = "Legs from the Wyrm Queen";
	                itemDef.modelZoom = 1740;
	                itemDef.rotationY = 474;
	                itemDef.rotationX = 2045;
	                itemDef.modelOffset1 = 0;
	                itemDef.modelOffsetY = -5;
	                itemDef.maleEquip1 = 95038;
	                itemDef.femaleEquip1 = 95038;
	                itemDef.groundActions = new String[5];
	                itemDef.groundActions[2] = "Take";
	                itemDef.actions = new String[5];
	                itemDef.actions[1] = "Wear";
	                itemDef.actions[4] = "Drop";
	                break;8=*/
        }

        return ItemDef_Tama.forDef(itemDef, ID);
    }

}
