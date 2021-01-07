package com.athena.world.content.combat.strategy;

import java.util.HashMap;

import java.util.Map;

import com.athena.world.content.combat.strategy.impl.*;
import com.athena.world.content.combat.strategy.impl.doflamingo.Doflamingo;
import com.athena.world.content.combat.strategy.impl.gwd2.ArmadylAbyzou;
import com.athena.world.content.combat.strategy.impl.gwd2.ZamorakIktomi;
import com.athena.world.content.combat.strategy.impl.gwd2.ZamorakLefosh;
import com.athena.world.content.combat.strategy.impl.zulrah.BlueZulrah;
import com.athena.world.content.combat.strategy.impl.zulrah.CrimsonZulrah;
import com.athena.world.content.combat.strategy.impl.zulrah.GreenZulrah;


public class CombatStrategies {

	private static final DefaultMeleeCombatStrategy defaultMeleeCombatStrategy = new DefaultMeleeCombatStrategy();
	private static final DefaultMagicCombatStrategy defaultMagicCombatStrategy = new DefaultMagicCombatStrategy();
	private static final DefaultRangedCombatStrategy defaultRangedCombatStrategy = new DefaultRangedCombatStrategy();
	private static final Map<Integer, CombatStrategy> STRATEGIES = new HashMap<Integer, CombatStrategy>();
	
	public static void init() {
		DefaultMagicCombatStrategy defaultMagicStrategy = new DefaultMagicCombatStrategy();
		STRATEGIES.put(13, defaultMagicStrategy);
		STRATEGIES.put(15, defaultMagicStrategy);
		STRATEGIES.put(172, defaultMagicStrategy);
		STRATEGIES.put(174, defaultMagicStrategy);
		STRATEGIES.put(6102, defaultMagicStrategy);
		STRATEGIES.put(2071, defaultMagicStrategy);
		STRATEGIES.put(3915, defaultMagicStrategy);
		STRATEGIES.put(2025, defaultMagicStrategy);
		STRATEGIES.put(3495, defaultMagicStrategy);
		STRATEGIES.put(4419, defaultMagicStrategy);
		STRATEGIES.put(3496, defaultMagicStrategy);
		STRATEGIES.put(3491, defaultMagicStrategy);
		STRATEGIES.put(2882, defaultMagicStrategy);
		STRATEGIES.put(13451, defaultMagicStrategy);
		STRATEGIES.put(13452, defaultMagicStrategy);
		STRATEGIES.put(13453, defaultMagicStrategy);
		STRATEGIES.put(13454, defaultMagicStrategy);
		STRATEGIES.put(1643, defaultMagicStrategy);
		STRATEGIES.put(6254, defaultMagicStrategy);
		STRATEGIES.put(6257, defaultMagicStrategy);
		STRATEGIES.put(6278, defaultMagicStrategy);
		STRATEGIES.put(6221, defaultMagicStrategy);
		STRATEGIES.put(2511, defaultMagicStrategy);
		STRATEGIES.put(2509, defaultMagicStrategy);
		STRATEGIES.put(2505, defaultMagicStrategy);

		DefaultMeleeCombatStrategy defaultMelee = new DefaultMeleeCombatStrategy();
		STRATEGIES.put(2506, defaultMelee);



		
		DefaultRangedCombatStrategy defaultRangedStrategy = new DefaultRangedCombatStrategy();
		STRATEGIES.put(688, defaultRangedStrategy);
		STRATEGIES.put(2028, defaultRangedStrategy);
		STRATEGIES.put(6220, defaultRangedStrategy);
		STRATEGIES.put(6256, defaultRangedStrategy);
		STRATEGIES.put(6276, defaultRangedStrategy);
		STRATEGIES.put(2437, defaultRangedStrategy);
		STRATEGIES.put(6252, defaultRangedStrategy);
		STRATEGIES.put(3915, defaultRangedStrategy);
		STRATEGIES.put(4331, defaultRangedStrategy);
		STRATEGIES.put(4332, defaultRangedStrategy);
		STRATEGIES.put(4333, defaultRangedStrategy);
		STRATEGIES.put(4334, defaultRangedStrategy);
		STRATEGIES.put(4335, defaultRangedStrategy);
		STRATEGIES.put(4336, defaultRangedStrategy);
		STRATEGIES.put(130, defaultRangedStrategy);
		STRATEGIES.put(27, defaultRangedStrategy);
		STRATEGIES.put(3915, new DragonVanquisher());
		STRATEGIES.put(4303, new CustomVoragoCombat());
		STRATEGIES.put(4329, new DarthVader());
		STRATEGIES.put(4331, new Troop1());
		STRATEGIES.put(4332, new Troop2());
		STRATEGIES.put(4333, new Troop3());
		STRATEGIES.put(4334, new Troop4());
		STRATEGIES.put(4335, new Troop5());
		STRATEGIES.put(4336, new Troop6());
		STRATEGIES.put(4330, new Troop7());
		STRATEGIES.put(18, new CustomNpc());
		STRATEGIES.put(19, new Harambe());
		STRATEGIES.put(2088, new Doflamingo());
		STRATEGIES.put(1234, new InfernalGroudon());
		STRATEGIES.put(2236, new Baphomet());
		STRATEGIES.put(2070, new GanodermicNPC());
		STRATEGIES.put(3, new Jad());
		STRATEGIES.put(8528, new Nomad());
		STRATEGIES.put(2501, new DRAX());
		STRATEGIES.put(2518, new ShadowLord());
		STRATEGIES.put(53, new InfernoDragon());
		STRATEGIES.put(8349, new TormentedDemon());
		STRATEGIES.put(433, new Cyrisus());
		STRATEGIES.put(3200, new ChaosElemental());
		STRATEGIES.put(4540, new BandosAvatar());
		STRATEGIES.put(8133, new CorporealBeast());
		STRATEGIES.put(13447, new Nex());
		STRATEGIES.put(6303, new Abbadon());
		STRATEGIES.put(6305, new DraxNew());
		STRATEGIES.put(2896, new Spinolyp());
		STRATEGIES.put(839, new MiniDire());
		STRATEGIES.put(5588, new ChromeBot());
		STRATEGIES.put(509, new Nazastarool());
		STRATEGIES.put(2881, new DagannothSupreme());
		STRATEGIES.put(6260, new Graardor());
		STRATEGIES.put(6263, new Steelwill());
		STRATEGIES.put(6265, new Grimspike());
		STRATEGIES.put(6222, new KreeArra());
		STRATEGIES.put(6223, new WingmanSkree());
		STRATEGIES.put(6225, new Geerin());
		STRATEGIES.put(6203, new Tsutsuroth());
		STRATEGIES.put(6208, new Kreeyath());
		STRATEGIES.put(6206, new Gritch());
		STRATEGIES.put(6247, new Zilyana());
		STRATEGIES.put(6250, new Growler());
		STRATEGIES.put(1382, new Glacor());
		STRATEGIES.put(9939, new PlaneFreezer());
		STRATEGIES.put(2043, new GreenZulrah());
		STRATEGIES.put(2042, new BlueZulrah());
		STRATEGIES.put(2044, new CrimsonZulrah());
		STRATEGIES.put(9357, new Vladimir());
		STRATEGIES.put(9911, new HarLakkRiftsplitter());
		STRATEGIES.put(10141, new BallakPummeler());
		STRATEGIES.put(10039, new ToKashBloodchiller());
		STRATEGIES.put(132, new KingOfCaves());
		STRATEGIES.put(6307, new ZamorakIktomi());
		STRATEGIES.put(6309, new ZamorakLefosh());
		STRATEGIES.put(6313, new ArmadylAbyzou());
		
		Shrek shrekStrat = new Shrek();
		STRATEGIES.put(999, shrekStrat);
		
		Dragon dragonStrategy = new Dragon();
		STRATEGIES.put(50, dragonStrategy);
		STRATEGIES.put(941, dragonStrategy);
		STRATEGIES.put(55, dragonStrategy);
		//STRATEGIES.put(53, dragonStrategy);
		STRATEGIES.put(54, dragonStrategy);
		STRATEGIES.put(51, dragonStrategy);
		STRATEGIES.put(1590, dragonStrategy);
		STRATEGIES.put(1591, dragonStrategy);
		STRATEGIES.put(1592, dragonStrategy);
		STRATEGIES.put(5362, dragonStrategy);
		STRATEGIES.put(5363, dragonStrategy);
		STRATEGIES.put(7000, dragonStrategy);
		STRATEGIES.put(7001, dragonStrategy);
		
		Aviansie aviansieStrategy = new Aviansie();
		STRATEGIES.put(6246, aviansieStrategy);
		STRATEGIES.put(6230, aviansieStrategy);
		STRATEGIES.put(6231, aviansieStrategy);
		
		KalphiteQueen kalphiteQueenStrategy = new KalphiteQueen();
		STRATEGIES.put(1158, kalphiteQueenStrategy);
		STRATEGIES.put(1160, kalphiteQueenStrategy);
		
		Revenant revenantStrategy = new Revenant();
		STRATEGIES.put(6715, revenantStrategy);
		STRATEGIES.put(6716, revenantStrategy);
		STRATEGIES.put(6701, revenantStrategy);
		STRATEGIES.put(6725, revenantStrategy);
		STRATEGIES.put(6691, revenantStrategy);
		
		STRATEGIES.put(4413, new DireWolf());
		//STRATEGIES.put(6305, new Dragonix());
		STRATEGIES.put(2000, new Venenatis());
		STRATEGIES.put(2006, new Vetion());
		STRATEGIES.put(2010, new Callisto());
		STRATEGIES.put(1999, new Cerberus());
		STRATEGIES.put(6766, new LizardMan());
		STRATEGIES.put(499, new Thermonuclear());
		STRATEGIES.put(7286, new Skotizo());
		STRATEGIES.put(5886, new Sire());
		STRATEGIES.put(10126, new UnholyCursebearer());
	}
	
	public static CombatStrategy getStrategy(int npc) {
		if(STRATEGIES.get(npc) != null) {
			return STRATEGIES.get(npc);
		}
		return defaultMeleeCombatStrategy;
	}
	
	public static CombatStrategy getDefaultMeleeStrategy() {
		return defaultMeleeCombatStrategy;
	}

	public static CombatStrategy getDefaultMagicStrategy() {
		return defaultMagicCombatStrategy;
	}


	public static CombatStrategy getDefaultRangedStrategy() {
		return defaultRangedCombatStrategy;
	}
}
