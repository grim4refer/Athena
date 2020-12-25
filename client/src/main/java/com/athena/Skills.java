package com.athena;
public final class Skills {


    public static String[] SKILL_NAMES = {
        "Attack", "Constitution", "Mining", "Strength", "Agility",
        "Smithing", "Defence", "Herblore", "Fishing", "Ranged", "Thieving",
        "Cooking", "Prayer", "Crafting", "Firemaking", "Magic", "Fletching", "Woodcutting",
        "Runecrafting", "Slayer", "Farming", "Construction", "Hunter", "Summoning",
        "Dungeoneering", "Invention"
    };

    public static final int SKILL_COUNT = SKILL_NAMES.length;

    public static int[] hoverIds = { 4040, 4076, 4112, 4046, 4082, 4118, 4052, 4088, 4124, 4058, 4094, 4130, 4064, 4100, 4136, 4070, 4106, 4142, 4160, 2832, 13917, 28173, 28174, 28175, 28176, 28182 };

    public static int SKILL_ID(int ids) {
		for (int hover = 0; hover < hoverIds.length; hover++) {
			if (hoverIds[hover] == ids) {
				ids = hover;
				return hoverIds[ids];
			}
		}
		return 0;
	}
    
    public static final boolean[] SKILLS_ENABLED = {
        true, true, true, true, true, true, true, true, true, true,
        true, true, true, true, true, true, true, true, true, true,
        true, true, true, true, true
    };
    
    public static final int[][] goalData = new int[SKILL_COUNT][3];
    public static String goalType = "Target Level: ";
    public static int selectedSkillId = -1;

    static {
        for (int i = 0; i < goalData.length; i++) {
            goalData[i][0] = -1;
            goalData[i][1] = -1;
            goalData[i][2] = -1;
        }
    }
    public static int[] skillID = {0, 3, 14, 2, 16, 13, 1, 15, 10, 4, 17, 7, 5, 12, 11, 6, 9, 8, 20, 18, 19, 21, 22, 23, 24, 25, 26};

    public static int SKILL_ID_NAME(String name) {
        name = name.toLowerCase().trim();
        String[] names = {
            "Attack", "Constitution", "Mining", "Strength", "Agility",
            "Smithing", "Defence", "Herblore", "Fishing", "Ranged", "Thieving",
            "Cooking", "Prayer", "Crafting", "Firemaking", "Magic", "Fletching", "Woodcutting",
            "Runecrafting", "Slayer", "Farming", "Construction", "Hunter", "Summoning",
            "Dungeoneering", "Invention"
        };
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(name)) {
                return skillID[i];
            }
        }
        return 0;
    }
}