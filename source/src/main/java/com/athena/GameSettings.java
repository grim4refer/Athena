package com.athena;

import java.math.BigInteger;

import com.athena.model.Position;
import com.athena.net.security.ConnectionHandler;

public class GameSettings {


    /**
     * Dzone activation
     */
    public static boolean DZONEON = false;

    /**
     * The game port
     */
    public static final int GAME_PORT = 43595;

    /**
     * The game version
     */
    public static final int GAME_VERSION = 5;

    /**
     * The maximum amount of players that can be logged in on a single game
     * sequence.
     */
    public static final int LOGIN_THRESHOLD = 25;

    /**
     * The maximum amount of players that can be logged in on a single game
     * sequence.
     */
    public static final int LOGOUT_THRESHOLD = 50;

    /**
     * The maximum amount of players who can receive rewards on a single game
     * sequence.
     */
    public static final int VOTE_REWARDING_THRESHOLD = 15;

    /**
     * The maximum amount of connections that can be active at a time, or in
     * other words how many clients can be logged in at once per connection.
     * (0 is counted too)
     */
    public static final int CONNECTION_AMOUNT = 25;

    /**
     * The throttle interval for incoming connections accepted by the
     * {@link ConnectionHandler}.
     */
    public static final long CONNECTION_INTERVAL = 1000;

    /**
     * The number of seconds before a connection becomes idle.
     */
    public static final int IDLE_TIME = 15;

    /**
     * The keys used for encryption on login
     */
    public static final BigInteger RSA_MODULUS = new BigInteger("141038977654242498796653256463581947707085475448374831324884224283104317501838296020488428503639086635001378639378416098546218003298341019473053164624088381038791532123008519201622098961063764779454144079550558844578144888226959180389428577531353862575582264379889305154355721898818709924743716570464556076517");
    public static final BigInteger RSA_EXPONENT = new BigInteger("73062137286746919055592688968652930781933135350600813639315492232042839604916461691801305334369089083392538639347196645339946918717345585106278208324882123479616835538558685007295922636282107847991405620139317939255760783182439157718323265977678194963487269741116519721120044892805050386167677836394617891073");

    /**
     * The maximum amount of messages that can be decoded in one sequence.
     */
    public static final int DECODE_LIMIT = 30;

    /** GAME **/

    /**
     * Processing the engine
     */
    public static final int ENGINE_PROCESSING_CYCLE_RATE = 600;
    public static final int GAME_PROCESSING_CYCLE_RATE = 600;

    /**
     * Are the MYSQL services enabled?
     */
    public static boolean MYSQL_ENABLED = false;
    /**
     * Is it currently bonus xp?
     */
    public static boolean BONUS_EXP = false;//Misc.isWeekend();
    /**
     * Is shadow lord teleport spawned?
     */
    public static boolean SHADOW_TELEPORT = true;
    /**
     * Did joker activate bonus?
     */
    public static boolean STREAM_BONUS = false;
    /*
     * Can players attack each other in the wilderness?
     */
    public static boolean WILDERNESS_ATTACK_ENABLED = false;
    /**
     * The default position
     */
    public static final Position DEFAULT_POSITION = new Position(2529, 2527, 1);
    
    public static final Position CONSTRUCTION_POSITION = new Position(1917, 5117);


    public static final int MAX_STARTERS_PER_IP = 11;

    /**
     * Untradeable items
     * Items which cannot be traded or staked
     */
    public static final int[] UNTRADEABLE_ITEMS =
            { 19804, 19803, 19790, 19789,  19786, 19785, 12422, 12423, 12424, 12425,20985,
            		 20800, 20801, 20802, 20803, 20804, 20816, 20817};
    
    /**
     * Items that can attack player's in wilderness
     */
	public static final int[] ENABLED_WILDERNESS_EQUIPMENT = {
		13899, 3755, 662, 6107, 6108, 6568, 882, 884, 886, 888, 890, 892, 11212, 9142, 9143, 9144, 9242, 9244, 9245, 867, 868, 810, 811, 11230, 10499, 841, 853, 861, 9185, 1129, 6326, 6322, 6324, 6328, 3749, 2499, 2493, 2487, 2501, 2495, 2489, 2503, 2497, 2491, 1323, 1331, 1333, 4587, 1249, 3204, 1305, 1215, 5680, 1377, 1434, 7158, 6528, 4153, 1191, 5574, 5575, 5576, 1163, 1127, 1079, 1201, 6524, 1187, 13734, 3105, 4131, 1725, 1704, 11126, 10828, 3751, 9747, 9753, 9750, 9768, 9756, 9759, 9762, 9801, 9807, 9783, 9789, 9804, 9780, 9795, 9792, 9774, 9771, 9777, 9786, 9810, 9765, 9789, 9948, 12169, 18508, 9748, 9754, 9751, 9769, 9757, 9760, 9763, 9802, 9808, 9784, 9799, 9805, 9781, 9796, 9793, 9775, 9772, 9778, 9787, 9811, 9766, 9790, 9949, 12170, 18509, 9749, 9755, 9752, 9770, 9785, 9761, 9764, 9803, 9809, 9785, 9800, 9806, 9782, 9797, 9794, 9776, 9773, 9779, 9788, 9812, 9767, 9791, 9950, 12171, 18510, 1381, 1383, 1385, 1387, 4675, 4089, 4091, 4093, 4095, 4097, 4099, 4101, 4103, 4105, 4107, 4109, 4111, 4113, 4115, 4117, 14499, 14497, 14501, 2412, 2413, 2414, 9075, 560, 557, 565, 555, 554, 556, 558, 559, 561, 562, 563, 566, 19111, 13879, 13883, 15332, 11235, 6570, 20072, 15241, 14484, 13887, 13893, 13896, 13884, 13890, 10550, 10548, 10551, 11283, 20000, 20001, 20002, 12926, 4151, 2581, 2577, 15486, 11696, 11698, 11700, 11694, 11730, 15018, 15019, 15020, 15220, 11118, 2550
	};

    /**
     * Unsellable items
     * Items which cannot be sold to shops
     */
    public static int UNSELLABLE_ITEMS[] = new int[]
    		{  19804, 19803, 19790, 19789,  19786, 19785, 12422, 12423, 12424, 12425,20985,5022,5023,18016,995 };

    public static final String[] INVALID_NAMES = {"mod", "boby", "moderator", "admin", "administrator", "owner", "developer",
            "supporter", "dev", "developer", "nigga", "0wn3r", "4dm1n", "m0d", "adm1n", "a d m i n", "m o d",
            "o w n e r"};

    public static final int
            ATTACK_TAB = 0,
            SKILLS_TAB = 1,
            QUESTS_TAB = 2,
            ACHIEVEMENT_TAB = 14,
            INVENTORY_TAB = 3,
            EQUIPMENT_TAB = 4,
            PRAYER_TAB = 5,
            MAGIC_TAB = 6,

    SUMMONING_TAB = 13,
            FRIEND_TAB = 8,
            IGNORE_TAB = 9,
            CLAN_CHAT_TAB = 7,
            LOGOUT = 10,
            OPTIONS_TAB = 11,
            EMOTES_TAB = 12;
}
