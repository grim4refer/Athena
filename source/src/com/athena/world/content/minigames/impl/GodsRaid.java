package com.athena.world.content.minigames.impl;

import com.athena.model.Item;
import com.athena.model.Locations.Location;
import com.athena.model.Position;
import com.athena.util.Misc;
import com.athena.util.RandomUtility;
import com.athena.world.World;
import com.athena.world.entity.impl.npc.NPC;

public class GodsRaid extends Raid {

    public static boolean started;

    public static boolean getStarted(){
        return started;
    }

    public static void setStarted(boolean started) {
        GodsRaid.started = started;
    }

    public GodsRaid(int maxPlayers) {
        super(maxPlayers, Location.GODS_RAID);
        setName("Marvels Raid");
        init();
    }

    @Override
    protected void spawnCurrentStageNpcs() {
        Stage stage = stages[currentStage];
        int npcCount = stage.countNpcs();
        NPC npc;
        for (int i = 0; i < npcCount; ++i) {
            npc = stage.getNpc(i);
            npc.getDefinition().setMulti(true);
            npc.getDefinition().setRespawnTime(-1);
            World.register(stage.getNpc(i));
        }
    }

    @Override
    protected void giveRewards() {
        int[] rare = {7100,5020,5018,5016,15084,17847}; // 1:333 chance
        int[] ultraRare = {21080,21079,21077,21078,21082,11423,11531,11533,12423}; // 1:1000 chance - should put pets here
        int[][] common = {{19906,75}, {19905,75}, {19904,75}, {19904,75}, {20950,100}, {7112,30}, {7114,30}, {7116,30}, {7124,30}, {7100,1}}; // item and amount

        int abba[][] = {
                {19906,35}, {19905,35}, {19904,35}, {19905,35}, {20950,50}, {7112,5}, {7114,10}, {7116,10}, {7124,30}
        };

        for (int i = 0; i < playerIndex; ++i) {

                int random = Misc.random(999);
                Item reward = new Item(19904, 75);
                int randomCommon = RandomUtility.getRandom(abba.length - 1);
                reward = new Item(abba[randomCommon][0]);
                int amount = abba[randomCommon][1];

                if (random == 0) {
                    amount = 1;
                    int randomUltra = Misc.random(ultraRare.length - 1);
                    reward = new Item(ultraRare[randomUltra]);
                    String itemName = reward.getDefinition().getName();
                    String announcement = "@red@[Marvel Raids][Ultra Rare] @Bla@" + players[i].getUsername() + " has got " + itemName;
                    World.sendMessage(announcement);
                } else if (random >= 1 && random <= 3) {
                    amount = 1;
                    int randomRare = Misc.random(rare.length - 1);
                    reward = new Item(ultraRare[randomRare]);
                    String itemName = reward.getDefinition().getName();
                    String announcement = "@red@[Marvel Raids][Rare] @Bla@" + players[i].getUsername() + " has got " + itemName;
                    World.sendMessage(announcement);
                }
                reward.setAmount(amount);
                players[i].sendMessage("you got " + amount + "x " + reward.getDefinition().getName() + " as a reward");
                if (reward.getDefinition().isStackable() || players[i].getInventory().getFreeSlots() > amount)
                    players[i].getInventory().add(reward);
                else {
                    players[i].sendMessage("reward was added to your bank because you have no inventory spaces");
                    players[i].getBank(players[i].getCurrentBankTab()).add(reward);
                }
        }
    }

    @Override
    protected void initStages() {
        Stage[] stages = new Stage[4];
        NPC[] stage0npcs = new NPC[11];
        stage0npcs[0] = new NPC(2511, new Position(1818, 5160, 2));
        stage0npcs[1] = new NPC(2511, new Position(1822, 5156, 2));
        stage0npcs[2] = new NPC(2511, new Position(1830, 5159, 2));
        stage0npcs[3] = new NPC(2511, new Position(1834, 5153, 2));
        stage0npcs[4] = new NPC(2511, new Position(1838, 5148, 2));
        stage0npcs[5] = new NPC(2511, new Position(1832, 5144, 2));
        stage0npcs[6] = new NPC(2511, new Position(1827, 5147, 2));
        stage0npcs[7] = new NPC(2511, new Position(1821, 5150, 2));
        stage0npcs[8] = new NPC(2511, new Position(1815, 5149, 2));
        stage0npcs[9] = new NPC(2511, new Position(1818, 5143, 2));
        stage0npcs[10] = new NPC(2511, new Position(1826, 5141, 2));
        stages[0] = new Stage(stage0npcs);


        NPC[] stage1npcs = new NPC[11];
        stage1npcs[0] = new NPC(2500, new Position(1818, 5160, 2));
        stage1npcs[1] = new NPC(2500, new Position(1822, 5156, 2));
        stage1npcs[2] = new NPC(2500, new Position(1830, 5159, 2));
        stage1npcs[3] = new NPC(2500, new Position(1834, 5153, 2));
        stage1npcs[4] = new NPC(2500, new Position(1838, 5148, 2));
        stage1npcs[5] = new NPC(2500, new Position(1832, 5144, 2));
        stage1npcs[6] = new NPC(2506, new Position(1827, 5147, 2));
        stage1npcs[7] = new NPC(2506, new Position(1821, 5150, 2));
        stage1npcs[8] = new NPC(2506, new Position(1815, 5149, 2));
        stage1npcs[9] = new NPC(2506, new Position(1818, 5143, 2));
        stage1npcs[10] = new NPC(2506, new Position(1826, 5141, 2));
        stages[1] = new Stage(stage1npcs);


        NPC[] stage2npcs = new NPC[11];
        stage2npcs[0] = new NPC(2515, new Position(1818, 5160, 2));
        stage2npcs[1] = new NPC(2509, new Position(1822, 5156, 2));
        stage2npcs[2] = new NPC(2505, new Position(1830, 5159, 2));
        stage2npcs[3] = new NPC(2515, new Position(1834, 5153, 2));
        stage2npcs[4] = new NPC(2509, new Position(1838, 5148, 2));
        stage2npcs[5] = new NPC(2505, new Position(1832, 5144, 2));
        stage2npcs[6] = new NPC(2505, new Position(1827, 5147, 2));
        stage2npcs[7] = new NPC(2515, new Position(1821, 5150, 2));
        stage2npcs[8] = new NPC(2505, new Position(1815, 5149, 2));
        stage2npcs[9] = new NPC(2515, new Position(1818, 5143, 2));
        stage2npcs[10] = new NPC(2509, new Position(1826, 5141, 2));
        stages[2] = new Stage(stage2npcs);

        stages[3] = new Stage(new NPC[] {
                new NPC(2501, new Position(1821, 5150, 2))
        });
        this.stages = stages;
    }

    @Override
    protected void startNextStage() {
        super.startNextStage();
    }

    @Override
    public void init() {
        super.init();
        initStages();
    }



}
