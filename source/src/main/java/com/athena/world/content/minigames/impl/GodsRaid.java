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
        setName("Supreme Raid");
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
        int[] rare = {15668,21082,18931,19933,20820,15667, 15662, 15664};
        int[] ultraRare = {11724, 11726, 11728}; // 1:1000 chance - should put pets here
        int abba[][] = {
                {3947, 1}, {3948, 1}, {3949, 1}, {20012, 1}, {20010, 1}, {20020, 1}, {20019, 1}, {20607, 1}, {21063, 1}, {21066, 1}, {21065, 1}, {14008, 1}, {14009, 1}, {14010, 1}
        };

        for (int i = 0; i < playerIndex; ++i) {

                int random = Misc.random(1000);
                Item reward = new Item(17849, 1);
                int randomCommon = RandomUtility.getRandom(abba.length - 1);
                reward = new Item(abba[randomCommon][0]);
                int amount = abba[randomCommon][1];

                if (random == 1) {
                    amount = 1;
                    int randomUltra = Misc.random(ultraRare.length - 1);
                    reward = new Item(ultraRare[randomUltra]);
                    String itemName = reward.getDefinition().getName();
                    String announcement = "@yel@[Supreme Raids]@red@[Ultra Rare] " + players[i].getUsername() + " @or2@has got " + itemName;
                    World.sendMessage(announcement);
                } else if (random >= 1 && random <= 3) {
                    amount = 1;
                    int randomRare = Misc.random(rare.length - 1);
                    reward = new Item(rare[randomRare]);
                    String itemName = reward.getDefinition().getName();
                    String announcement = "@blu@[Raids]@whi@[Rare]" + players[i].getUsername() + " @gre@has got " + itemName;
                    World.sendMessage(announcement);
                }
                reward.setAmount(amount);
                players[i].sendMessage("@mag@You got " + amount + "@red@x " + reward.getDefinition().getName() + " as a reward");
                if (reward.getDefinition().isStackable() || players[i].getInventory().getFreeSlots() > amount)
                    players[i].getInventory().add(reward);
                else {
                    players[i].sendMessage("Reward was added to your bank because you have no inventory spaces");
                    players[i].getBank(players[i].getCurrentBankTab()).add(reward);
                }
        }
    }

    @Override
    protected void initStages() {
        Stage[] stages = new Stage[4];
        NPC[] stage0npcs = new NPC[11];
        stage0npcs[0] = new NPC(6278, new Position(1818, 5160, 2));
        stage0npcs[1] = new NPC(6278, new Position(1822, 5156, 2));
        stage0npcs[2] = new NPC(6278, new Position(1830, 5159, 2));
        stage0npcs[3] = new NPC(6278, new Position(1834, 5153, 2));
        stage0npcs[4] = new NPC(6278, new Position(1838, 5148, 2));
        stage0npcs[5] = new NPC(6278, new Position(1832, 5144, 2));
        stage0npcs[6] = new NPC(6278, new Position(1827, 5147, 2));
        stage0npcs[7] = new NPC(6278, new Position(1821, 5150, 2));
        stage0npcs[8] = new NPC(6278, new Position(1815, 5149, 2));
        stage0npcs[9] = new NPC(6278, new Position(1818, 5143, 2));
        stage0npcs[10] = new NPC(6278, new Position(1826, 5141, 2));
        stages[0] = new Stage(stage0npcs);


        NPC[] stage1npcs = new NPC[11];
        stage1npcs[0] = new NPC(6276, new Position(1818, 5160, 2));
        stage1npcs[1] = new NPC(6276, new Position(1822, 5156, 2));
        stage1npcs[2] = new NPC(6276, new Position(1830, 5159, 2));
        stage1npcs[3] = new NPC(6276, new Position(1834, 5153, 2));
        stage1npcs[4] = new NPC(6276, new Position(1838, 5148, 2));
        stage1npcs[5] = new NPC(6276, new Position(1832, 5144, 2));
        stage1npcs[6] = new NPC(6276, new Position(1827, 5147, 2));
        stage1npcs[7] = new NPC(6276, new Position(1821, 5150, 2));
        stage1npcs[8] = new NPC(6276, new Position(1815, 5149, 2));
        stage1npcs[9] = new NPC(6276, new Position(1818, 5143, 2));
        stage1npcs[10] = new NPC(6276, new Position(1826, 5141, 2));
        stages[1] = new Stage(stage1npcs);


        NPC[] stage2npcs = new NPC[11];
        stage2npcs[0] = new NPC(6277, new Position(1818, 5160, 2));
        stage2npcs[1] = new NPC(6277, new Position(1822, 5156, 2));
        stage2npcs[2] = new NPC(6277, new Position(1830, 5159, 2));
        stage2npcs[3] = new NPC(6277, new Position(1834, 5153, 2));
        stage2npcs[4] = new NPC(6277, new Position(1838, 5148, 2));
        stage2npcs[5] = new NPC(6277, new Position(1832, 5144, 2));
        stage2npcs[6] = new NPC(6277, new Position(1827, 5147, 2));
        stage2npcs[7] = new NPC(6277, new Position(1821, 5150, 2));
        stage2npcs[8] = new NPC(6277, new Position(1815, 5149, 2));
        stage2npcs[9] = new NPC(6277, new Position(1818, 5143, 2));
        stage2npcs[10] = new NPC(6277, new Position(1826, 5141, 2));
        stages[2] = new Stage(stage2npcs);

        stages[3] = new Stage(new NPC[] {
                new NPC(6260, new Position(1821, 5150, 2))
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
