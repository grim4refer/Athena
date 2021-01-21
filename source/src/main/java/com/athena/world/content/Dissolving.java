package com.athena.world.content;

import com.athena.model.Animation;
import com.athena.model.Item;
import com.athena.model.Skill;
import com.athena.world.entity.impl.player.Player;

import java.util.ArrayList;

public class Dissolving {

	private Player player;

	public Dissolving(Player player) {
		this.player = player;
	}

	static int anim = 11904;
	static int orb = 12855;

	enum DissolvingData {
		
		athenstarterbody(20615, new Item[] { new Item(orb, 4000) }, 17500, anim),

		athenastarterhelm(20616, new Item[] { new Item(orb, 500) }, 1250, anim),
		galvek2(19158, new Item[] { new Item(orb, 100) }, 1250, anim),
		galvek3(19159, new Item[] { new Item(orb, 100) }, 1250, anim),
		galvek(19160, new Item[] { new Item(orb, 100) }, 1250, anim),

		Flamebrustdef(17273, new Item[] { new Item(orb, 100) }, 250, anim),
		PERFECTCELL(7543, new Item[] { new Item(orb, 50000) }, 15000, anim),
		gokuwatch(2575, new Item[] { new Item(orb, 10000) }, 15000, anim),
		PERFECTCELL2(7544, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL3(7545, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL4(9481, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL5(9482, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL6(9483, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL7(16249, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL8(15832, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL99(16265, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL9(9478, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL999(9479, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL11(9480, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL121(17702, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL131(11763, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL141(11764, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL151(11765, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL161(11766, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL171(11767, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELL181(13323, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCELLS181(13323, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCSDELL181(13324, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCASELAL181(13325, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTCSELL181(13326, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTDACELL181(13327, new Item[] { new Item(orb, 50000) }, 15000, anim),
		PERFECTSASCELL181(5068, new Item[] { new Item(orb, 25000) }, 10000, anim),
		APERFECTSASCELL181(5069, new Item[] { new Item(orb, 25000) }, 10000, anim),
		SPERFECTSASCELL181(5070, new Item[] { new Item(orb, 25000) }, 10000, anim),
		P3ERFECTSASCELL181(5071, new Item[] { new Item(orb, 25000) }, 10000, anim),
		P4ERFECTSASCELL181(5072, new Item[] { new Item(orb, 25000) }, 10000, anim),
		P5ERFECTSASCELL181(5073, new Item[] { new Item(orb, 25000) }, 10000, anim),
		CRYSTAL(14060, new Item[] { new Item(orb, 25000) }, 10000, anim),
		CRYSTAL2(14061, new Item[] { new Item(orb, 25000) }, 10000, anim),
		CRYSTAL3(14062, new Item[] { new Item(orb, 25000) }, 10000, anim),
		CRYSTAL4(14063, new Item[] { new Item(orb, 25000) }, 10000, anim),
		CRYSTAL5(14064, new Item[] { new Item(orb, 25000) }, 10000, anim),
		CRYSTAL6(14065, new Item[] { new Item(orb, 25000) }, 10000, anim),
		CRYSTAL7(14066, new Item[] { new Item(orb, 25000) }, 10000, anim),
		cash(10835, new Item[] { new Item(orb, 1000) }, 2000, anim),

		KBDDD(15920, new Item[] { new Item(orb, 100) }, 999, anim),
		ETERNAL00(19909, new Item[] { new Item(orb, 200) }, 2500, anim),
		ETERNAL(19901, new Item[] { new Item(orb, 200) }, 2500, anim),
		ETERNAL2(19902, new Item[] { new Item(orb, 200) }, 999, anim),
		ETERNAL3(19903, new Item[] { new Item(orb, 200) }, 999, anim),
		ETERNAL4(19904, new Item[] { new Item(orb, 200) }, 999, anim),
		ETERNAL5(19905, new Item[] { new Item(orb, 200) }, 999, anim),
		NEXGLAIVE(20557, new Item[] { new Item(orb, 25000) }, 50000, anim),
		SCARLET(3737, new Item[] { new Item(orb, 25000) }, 50000, anim),
		SCARLET2(3720, new Item[] { new Item(orb, 25000) }, 50000, anim),
		SCARLET3(3721, new Item[] { new Item(orb, 25000) }, 50000, anim),
		SCARLET4(3722, new Item[] { new Item(orb, 25000) }, 50000, anim),
		artcape(4367, new Item[] { new Item(orb, 20000) }, 35000, anim),

		
		HERBAL(3738, new Item[] { new Item(orb, 25000) }, 50000, anim),
		HERBAL2(3723, new Item[] { new Item(orb, 25000) }, 50000, anim),
		HERBAL3(3724, new Item[] { new Item(orb, 25000) }, 50000, anim),
		HERBAL4(3725, new Item[] { new Item(orb, 25000) }, 50000, anim),
		AZURE(3739, new Item[] { new Item(orb, 25000) }, 50000, anim),
		AZURE2(3726, new Item[] { new Item(orb, 25000) }, 50000, anim),
		AZURE3(3728, new Item[] { new Item(orb, 25000) }, 50000, anim),
		AZURE4(3730, new Item[] { new Item(orb, 25000) }, 50000, anim),
		prestige1(17606, new Item[] { new Item(orb, 500) }, 4101, anim),
		prestige(17614, new Item[] { new Item(orb, 500) }, 4101, anim),
		prestige2(17616, new Item[] { new Item(orb, 500) }, 4101, anim),
		prestige3(17618, new Item[] { new Item(orb, 500) }, 4101, anim),
		prestige4(17620, new Item[] { new Item(orb, 500) }, 4101, anim),
		prestige5(17622, new Item[] { new Item(orb, 500) }, 4101, anim),
		prestige6(17624, new Item[] { new Item(orb, 500) }, 4101, anim),
		prestige7(17626, new Item[] { new Item(orb, 500) }, 4101, anim),
		prestige8(17628, new Item[] { new Item(orb, 500) }, 4101, anim),
		TIER_4_GEAR_A(22084, new Item[] { new Item(orb, 20000) }, 75000, anim),
		TIER_4_GEAR_B(22083, new Item[] { new Item(orb, 20000) }, 75000, anim),
		TIER_4_GEAR_C(22092, new Item[] { new Item(orb, 20000) }, 75000, anim),
		TIER_4_GEAR_C1(8323, new Item[] { new Item(orb, 20000) }, 75000, anim),
		TIER_4_GEAR_C12(8324, new Item[] { new Item(orb, 20000) }, 75000, anim),
		TIER_4_GEAR_C13(8325, new Item[] { new Item(orb, 20000) }, 75000, anim),
		TIER_4_GEAR_C14(8326, new Item[] { new Item(orb, 20000) }, 75000, anim),
		TIER_4_GEAR_C15(8327, new Item[] { new Item(orb, 20000) }, 75000, anim),
		TIER_4_GEAR_C16(8328, new Item[] { new Item(orb, 20000) }, 75000, anim),
		TIER_4_GEAR_C117(8329, new Item[] { new Item(orb, 20000) }, 75000, anim),
		TIER_4_GEAR_C11sda7(11140, new Item[] { new Item(orb, 10000) }, 75000, anim),

		ARTTTSAD(16136, new Item[] { new Item(orb, 20000) }, 75000, anim),
		WRATHHS(17700, new Item[] { new Item(orb, 70000) }, 125000, anim),
		WRATHH2(17698, new Item[] { new Item(orb, 70000) }, 125000, anim),

		TIER_4_GEAR_C17(8330, new Item[] { new Item(orb, 20000) }, 75000, anim),
		TIER_4_GEAR_C177(8331, new Item[] { new Item(orb, 20000) }, 75000, anim),
		TIER_4_GEAR_C1778(8332, new Item[] { new Item(orb, 20000) }, 75000, anim),
		T5_GEAR(18750, new Item[] { new Item(orb, 75000) }, 125000, anim),
		T5_GEAR1(18751, new Item[] { new Item(orb, 75000) }, 125000, anim),
		T5_GEAR2(18752, new Item[] { new Item(orb, 75000) }, 125000, anim),
		T5_GEAR3(18753, new Item[] { new Item(orb, 75000) }, 125000, anim),
		T5_GEAR31(18749, new Item[] { new Item(orb, 75000) }, 125000, anim),
		T5_GEAR32(18748, new Item[] { new Item(orb, 75000) }, 125000, anim),
		T5_GEAR33(18638, new Item[] { new Item(orb, 75000) }, 125000, anim),
		T5_GEAR312(18636, new Item[] { new Item(orb, 75000) }, 125000, anim),
		T5_GEAR3122(18629, new Item[] { new Item(orb, 75000) }, 125000, anim),
		T5_GEARSASA312(18631, new Item[] { new Item(orb, 75000) }, 125000, anim),
		T5_GEAAR312(18637, new Item[] { new Item(orb, 75000) }, 125000, anim),
		T5_GEGGAR312(18623, new Item[] { new Item(orb, 75000) }, 125000, anim),
		T5_GDEAAR312(19886, new Item[] { new Item(orb, 75000) }, 125000, anim),
		T5_GESDAR312(4446, new Item[] { new Item(orb, 75000) }, 125000, anim),
		T5_GESDAR3212(18823, new Item[] { new Item(orb, 250000) }, 175000, anim),
		T5_GESDAR3A12(19888, new Item[] { new Item(orb, 400000) }, 175000, anim),
		T6_GESDAR312(8087, new Item[] { new Item(orb, 125000) }, 175000, anim),
		T6_GESDAR32(8088, new Item[] { new Item(orb, 125000) }, 175000, anim),
		T6_GESDAR12(8089, new Item[] { new Item(orb, 125000) }, 175000, anim),
		T6_GESDAR12S(11320, new Item[] { new Item(orb, 125000) }, 175000, anim),
		T6_GESDAR12A(11321, new Item[] { new Item(orb, 125000) }, 175000, anim),
		T6_GESDAR12B(11322, new Item[] { new Item(orb, 125000) }, 175000, anim),
		T6_GESDAR12C(11340, new Item[] { new Item(orb, 125000) }, 175000, anim),
		T6_GESDAR12D(11341, new Item[] { new Item(orb, 125000) }, 175000, anim),
		T6_GESDAR12SD(11342, new Item[] { new Item(orb, 125000) }, 175000, anim),
		T6_GESASAFASDAR12SD(11321, new Item[] { new Item(orb, 125000) }, 175000, anim),
		T6_GEASASDASDAR12SD(11322, new Item[] { new Item(orb, 125000) }, 175000, anim),
		T6_GESFASADASSDAR12SD(11323, new Item[] { new Item(orb, 125000) }, 175000, anim),

		SKOLLSTAFF(5095, new Item[] { new Item(orb, 80) }, 180, anim),
		SABER(21057, new Item[] { new Item(orb, 400) }, 1500, anim),
		SABER2(21058, new Item[] { new Item(orb, 800) }, 2500, anim),

		SUMMONCHARMBOX(20481, new Item[] { new Item(orb, 200) }, 1200, anim),
		HADESSHIELD(21035, new Item[] { new Item(orb, 6000) }, 16000, anim),
		VIGGORCHAIN(20554, new Item[] { new Item(orb, 5000) }, 15000, anim),
		FLAMETHRO(17712, new Item[] { new Item(orb, 2854) }, 12854, anim),
		HADESHAMMER(21034, new Item[] { new Item(orb, 6000) }, 16000, anim),
		HADESLEGS(21033, new Item[] { new Item(orb, 6000) }, 16000, anim),
		HADESBODY(21032, new Item[] { new Item(orb, 6000) }, 16000, anim),
		HADESHELM(21031, new Item[] { new Item(orb, 6000) }, 16000, anim),
		ELECTRICBOOTS(15236, new Item[] { new Item(orb, 6000) }, 11900, anim),
		ELECTRICGLOVES(15235, new Item[] { new Item(orb, 6000) }, 11900, anim),
		GRO_BOOTS(3107, new Item[] { new Item(orb, 16000) }, 18493, anim),
		GRO_STAFF(13640, new Item[] { new Item(orb, 16000) }, 18493, anim),
		GRO_SHIELD(13964, new Item[] { new Item(orb, 16000) }, 18493, anim),
		GRO_POWER(15448, new Item[] { new Item(orb, 16000) }, 18493, anim),
		GRO_HELM(21934, new Item[] { new Item(orb, 16000) }, 18493, anim),
		GRO_BODY(19918, new Item[] { new Item(orb, 16000) }, 18493, anim),
		GRO_LEG(19913, new Item[] { new Item(orb, 16000) }, 18493, anim),
		shikruu1(8800, new Item[] { new Item(orb, 8000) }, 18493, anim),
		shikruu2(8801, new Item[] { new Item(orb, 8000) }, 18493, anim),
		shikruu3(8802, new Item[] { new Item(orb, 8000) }, 18493, anim),
		FLOREX(22074, new Item[] { new Item(orb, 310) }, 1310, anim),
		JAV(13956, new Item[] { new Item(orb, 50) }, 100, anim),
		DRAGSLAYER(19472, new Item[] { new Item(orb, 50) }, 100, anim),
		DRAGSLAYER2(19473, new Item[] { new Item(orb, 200) }, 1000, anim),
		DRAGSLAYER3(19811, new Item[] { new Item(orb, 200) }, 1000, anim),
		PREDATOR(8860, new Item[] { new Item(orb, 400) }, 2000, anim),
		PREDATOR1(8834, new Item[] { new Item(orb, 400) }, 2000, anim),
		PREDATOR2(8835, new Item[] { new Item(orb, 400) }, 2000, anim),
		PREDATOR3(8861, new Item[] { new Item(orb, 400) }, 2000, anim),
		PREDATOR4(8862, new Item[] { new Item(orb, 400) }, 2000, anim),
		PREDATOR5(15830, new Item[] { new Item(orb, 400) }, 2000, anim),
		
		GZONEGS33(18795, new Item[] { new Item(orb, 310) }, 1310, anim),
		wingsss(12634, new Item[] { new Item(orb, 500) }, 1310, anim),
		Frozenrapier(16045, new Item[] { new Item(orb, 1000) }, 1240, anim),
		FrozenSHORTBOW(15785, new Item[] { new Item(orb, 1000) }, 1240, anim),
		Frozenstaff(19331, new Item[] { new Item(orb, 1000) }, 1240, anim),
		empgloves(8812, new Item[] { new Item(orb, 3000) }, 1240, anim),
		barrowglovesI(3318, new Item[] { new Item(orb, 500) }, 1240, anim),
		evilstaff(5096, new Item[] { new Item(orb, 750) }, 1240, anim),
		BRING(6737, new Item[] { new Item(orb, 50) }, 1240, anim),
		BRING_z(3909, new Item[] { new Item(orb, 500) }, 1240, anim),
		rowii(3324, new Item[] { new Item(orb, 1000) }, 1240, anim),
		bossgloves(3905, new Item[] { new Item(orb, 1000) }, 1240, anim),
		icyfury(15418, new Item[] { new Item(orb, 1000) }, 1240, anim),
		LAVARING(15401, new Item[] { new Item(orb, 2500) }, 1240, anim),
		SHADOWAMULET(11195, new Item[] { new Item(orb, 2500) }, 1240, anim),
		WINGSHIELD(19749, new Item[] { new Item(orb, 2500) }, 1240, anim),
		ARTRING(8335, new Item[] { new Item(orb, 4000) }, 1240, anim),

		ZEUSCAPEFLAG(17662, new Item[] { new Item(orb, 7493) }, 17493, anim),
		ZEUSSEIHLD(15234, new Item[] { new Item(orb, 7493) }, 17493, anim),
		ZEUSHAMMER(15233, new Item[] { new Item(orb, 7493) }, 17493, anim),
		ZEUSLEGS(15232, new Item[] { new Item(orb, 7493) }, 17493, anim),
		ZEUSBODY(15231, new Item[] { new Item(orb, 7493) }, 17493, anim),
		ZEUSHELM(15230, new Item[] { new Item(orb, 7493) }, 17493, anim),
		
		shukay2wd(14924, new Item[] { new Item(orb, 500) }, 2000, anim),
		shukays2wd(19135, new Item[] { new Item(orb, 100) }, 1000, anim),

		shukay(8813, new Item[] { new Item(orb, 3000) }, 11493, anim),
		shukay2(8814, new Item[] { new Item(orb, 3000) }, 11493, anim),
		shukay3(8815, new Item[] { new Item(orb, 3000) }, 11493, anim),
		swoodo2(4405, new Item[] { new Item(orb, 10000) }, 27493, anim),
		swoodo222(16077, new Item[] { new Item(orb, 10000) }, 27493, anim),
		swoodo3(12930, new Item[] { new Item(orb, 10000) }, 27493, anim),
		swoodo(16011, new Item[] { new Item(orb, 10000) }, 27493, anim),
		swoodosads(16066, new Item[] { new Item(orb, 10000) }, 27493, anim),

		swoodo4(16055, new Item[] { new Item(orb, 10000) }, 27493, anim),
		swoodo5(16056, new Item[] { new Item(orb, 10000) }, 27493, anim),
		swoodo6(16057, new Item[] { new Item(orb, 10000) }, 27493, anim),
		swoodo7(16114, new Item[] { new Item(orb, 10000) }, 27493, anim),
		swoodo8(19149, new Item[] { new Item(orb, 10000) }, 27493, anim),

		
		CREEPERHELM(9054, new Item[] { new Item(orb, 14500) }, 17493, anim),
		CREEPERHELM2(9055, new Item[] { new Item(orb, 14500) }, 17493, anim),
		CREEPERHELM3(9056, new Item[] { new Item(orb, 14500) }, 17493, anim),
		CREEPERHELM4(9057, new Item[] { new Item(orb, 14500) }, 17493, anim),
		CREEPERHELM5(9058, new Item[] { new Item(orb, 14500) }, 17493, anim),

		SATANRING(18817, new Item[] { new Item(orb, 5535) }, 15535, anim),
		SATANSWORD(20542, new Item[] { new Item(orb, 5535) }, 15535, anim),
		SATANCAPE(18683, new Item[] { new Item(orb, 5535) }, 15535, anim),
		SATANSHIELD(13306, new Item[] { new Item(orb, 5535) }, 15535, anim),
		SATANAMULET(15511, new Item[] { new Item(orb, 5535) }, 15535, anim),
		SATANBOOTS(13305, new Item[] { new Item(orb, 5535) }, 15535, anim),
		PVPVESTAPLATE(13887, new Item[] { new Item(orb, 320) }, 1320, anim),
		SATANLEGS(13304, new Item[] { new Item(orb, 5535) }, 15535, anim),
		PVPVESTAPVP(13905, new Item[] { new Item(orb, 320) }, 1320, anim),
		PVPZURIELSTAFF(13867, new Item[] { new Item(orb, 320) }, 1320, anim),
		DEMONAGGER(15877, new Item[] { new Item(orb, 620) }, 1620, anim),
		KINGHELM(15922, new Item[] { new Item(orb, 1999) }, 11999, anim),
		KINGLEGS(15933, new Item[] { new Item(orb, 1999) }, 11999, anim),
		KINGOBDY(16021, new Item[] { new Item(orb, 1999) }, 11999, anim),
		BULWARKHELM(8816, new Item[] { new Item(orb, 1999) }, 11999, anim),
		BULWARKBODY(8817, new Item[] { new Item(orb, 1999) }, 11999, anim),
		BULWARKLEGS(8818, new Item[] { new Item(orb, 1999) }, 11999, anim),
		CHAOSSTAFF(18356, new Item[] { new Item(orb, 300) }, 1300, anim),
		SERPENTINEHELM(12931, new Item[] { new Item(orb, 1999) }, 11999, anim),

		SATNAGLOVES(13302, new Item[] { new Item(orb, 5535) }, 15535, anim),
		SATANBODY(13301, new Item[] { new Item(orb, 5535) }, 15535, anim),
		SATANHELMET(13300, new Item[] { new Item(orb, 5535) }, 15535, anim),
		SHIKRUUKATANA(20549, new Item[] { new Item(orb, 8500) }, 14000, anim),
		SORROWBOW(20173, new Item[] { new Item(orb, 8500) }, 14000, anim),
		SORROWHELMET(8803, new Item[] { new Item(orb, 8500) }, 14000, anim),
		SORROWBODY(8804, new Item[] { new Item(orb, 8500) }, 14000, anim),
		SORROWCHAPS(8805, new Item[] { new Item(orb, 8500) }, 14000, anim),
		GANOSTAFF(8809, new Item[] { new Item(orb, 8500) }, 14000, anim),
		GANOHLMET(8806, new Item[] { new Item(orb, 8500) }, 14000, anim),
		GANOBODY(8807, new Item[] { new Item(orb, 8500) }, 14000, anim),
		GANOLEGS(8808, new Item[] { new Item(orb, 8500) }, 14000, anim),
		TITANSTAFF(17600, new Item[] { new Item(orb, 2300) }, 12300, anim),
		eternalwhip(20534, new Item[] { new Item(orb, 2300) }, 12300, anim),
		brutalwhip(20535, new Item[] { new Item(orb, 2300) }, 12300, anim),
		eternalshield(20438, new Item[] { new Item(orb, 2300) }, 12300, anim),
		TITANCAPE(19944, new Item[] { new Item(orb, 2300) }, 12300, anim),
		TITANHELMET(703, new Item[] { new Item(orb, 2300) }, 12300, anim),
		TITANLEGS(705, new Item[] { new Item(orb, 2300) }, 12300, anim),
		TITANBODY(704, new Item[] { new Item(orb, 2300) }, 12300, anim),
		TITANBOOTS(19945, new Item[] { new Item(orb, 2300) }, 12300, anim),
		TITANGLOVES(19946, new Item[] { new Item(orb, 2300) }, 12300, anim),
		AGNELICHELMET(17638, new Item[] { new Item(orb, 2999) }, 12999, anim),
		ANGELBODY(17640, new Item[] { new Item(orb, 2999) }, 12999, anim),
		ANGELOEGS(15593, new Item[] { new Item(orb, 2999) }, 12999, anim),
		ANGELICAMULET(16140, new Item[] { new Item(orb, 2999) }, 12999, anim),
		ANGELICGLOVES(12860, new Item[] { new Item(orb, 2999) }, 12999, anim),
		ANGELICWINGS(2021, new Item[] { new Item(orb, 2999) }, 12999, anim),
		ANGLICBOOTS(12565, new Item[] { new Item(orb, 2999) }, 12999, anim),
		LUCIENAXE(17714, new Item[] { new Item(orb, 4392) }, 14392, anim),
		LUCIENWINGS(17686, new Item[] { new Item(orb, 4392) }, 14392, anim),
		LUCIENHELMET(15924, new Item[] { new Item(orb, 4392) }, 14392, anim),
		LUCIENBODY(16023, new Item[] { new Item(orb, 4392) }, 14392, anim),
		LUCIENLEGS(15935, new Item[] { new Item(orb, 4392) }, 14392, anim),
		LUUCIENWHIP(15888, new Item[] { new Item(orb, 4392) }, 14392, anim),
		LUCIENDEFENDER(15818, new Item[] { new Item(orb, 4392) }, 14392, anim),
		LUCIENGLOVES(12994, new Item[] { new Item(orb, 4392) }, 14392, anim),
		HERCHELMET(15005, new Item[] { new Item(orb, 4865) }, 14392, anim),
		LUCIENBOOTS(16272, new Item[] { new Item(orb, 4392) }, 14392, anim),
		HERCULESSCYTHE(12284, new Item[] { new Item(orb, 4865) }, 14865, anim),
		HERCULESCAPE(15100, new Item[] { new Item(orb, 4865) }, 14865, anim),
		HERCULESBODY(15006, new Item[] { new Item(orb, 4865) }, 14865, anim),
		HERCULESSHIELD(15008, new Item[] { new Item(orb, 4865) }, 14865, anim),
		HERCULESLEGS(15007, new Item[] { new Item(orb, 4865) }, 14865, anim),
		CURSEDSCIM(20098, new Item[] { new Item(orb, 90000) }, 44865, anim),
		CURSEDARM(20086, new Item[] { new Item(orb, 75000) }, 25865, anim),
		CURSEDARM2(20087, new Item[] { new Item(orb, 75000) }, 25865, anim),
		CURSEDARM3(20088, new Item[] { new Item(orb, 75000) }, 25865, anim),
		CURSEDARM4(20089, new Item[] { new Item(orb, 75000) }, 25865, anim),
		CURSEDARM5(20090, new Item[] { new Item(orb, 75000) }, 25865, anim),
		CURSEDARM6(20091, new Item[] { new Item(orb, 75000) }, 25865, anim),
		CURSEDARM7(20099, new Item[] { new Item(orb, 75000) }, 25865, anim),
		CREEPERCAPE(1486, new Item[] { new Item(orb, 75000) }, 54865, anim),
		VINE(21371, new Item[] { new Item(orb, 100) }, 1000, anim),
		VINE32(21372, new Item[] { new Item(orb, 100) }, 1000, anim),
		VINE2(21373, new Item[] { new Item(orb, 100) }, 1000, anim),
		VINE3(21374, new Item[] { new Item(orb, 100) }, 1000, anim),

		VIGGORA(12285, new Item[] { new Item(orb, 6000) }, 8500, anim),
		SCYTHE(20555, new Item[] { new Item(orb, 6000) }, 8500, anim),
		BRUTAL(22077, new Item[] { new Item(orb, 1250) }, 8500, anim),
		BRUTA2L(19136, new Item[] { new Item(orb, 1250) }, 8500, anim),
		BRUTA222L(22080, new Item[] { new Item(orb, 5250) }, 8500, anim),

		BRUTAL3(6936, new Item[] { new Item(orb, 1250) }, 8500, anim),

		ASTRO(17704, new Item[] { new Item(orb, 500) }, 8500, anim),
		TORSO(3319, new Item[] { new Item(orb, 500) }, 8500, anim),
		TORSO2(3320, new Item[] { new Item(orb, 500) }, 8500, anim),
		TORSO3(3321, new Item[] { new Item(orb, 500) }, 8500, anim),
		TORSO4(3322, new Item[] { new Item(orb, 500) }, 8500, anim),
		katanaaa(14018, new Item[] { new Item(orb, 700) }, 10500, anim),
		bulwark(8816, new Item[] { new Item(orb, 700) }, 10500, anim),
		bulwark2(8817, new Item[] { new Item(orb, 700) }, 10500, anim),
		bulwark3(8818, new Item[] { new Item(orb, 700) }, 10500, anim),
		bulwark4(20554, new Item[] { new Item(orb, 6000) }, 40500, anim),
		bulwark45(8810, new Item[] { new Item(orb, 7000) }, 40500, anim),
		bulwark425(8811, new Item[] { new Item(orb, 7000) }, 40500, anim),


		TORVA(14008, new Item[] { new Item(orb, 100) }, 1500, anim),
		TORVA2(14009, new Item[] { new Item(orb, 100) }, 1500, anim),
		TORVA3(14010, new Item[] { new Item(orb, 100) }, 1500, anim),
		TORVA4(14011, new Item[] { new Item(orb, 100) }, 1500, anim),
		TORVA5(14012, new Item[] { new Item(orb, 100) }, 1500, anim),
		TORVA6(14013, new Item[] { new Item(orb, 100) }, 1500, anim),
		TORVA7(14014, new Item[] { new Item(orb, 100) }, 1500, anim),
		TORVA8(14015, new Item[] { new Item(orb, 100) }, 1500, anim),
		TORVA9(14016, new Item[] { new Item(orb, 100) }, 1500, anim),

		ARCHIEGUN2(7640, new Item[] { new Item(orb, 120000) }, 34865, anim),
		ARCHIEGUN3(19843, new Item[] { new Item(orb, 120000) }, 44865, anim),
		ARCHIEGUN(4178, new Item[] { new Item(orb, 120000) }, 44865, anim),

		HERCULESGLOVES(15200, new Item[] { new Item(orb, 4865) }, 4101, anim),
		HERCULESBOOTS(15201, new Item[] { new Item(orb, 4865) }, 4101, anim),
		NEXTORVAHLMET(14008, new Item[] { new Item(orb, 400) }, 1400, anim),
		NEXTORVABODY(14009, new Item[] { new Item(orb, 400) }, 1400, anim),
		NEXTORVALEGS(14010, new Item[] { new Item(orb, 400) }, 1400, anim),
		NEXPERNIXCOWL(14011, new Item[] { new Item(orb, 400) }, 1400, anim),
		NEXPERNIXBODY(14012, new Item[] { new Item(orb, 400) }, 1400, anim),
		NEXPERNIXLEGS(14013, new Item[] { new Item(orb, 400) }, 1400, anim),
		NEXVIRTUSMASK(14014, new Item[] { new Item(orb, 400) }, 1400, anim),
		NEXVIRTUSBODY(14015, new Item[] { new Item(orb, 400) }, 1400, anim),
		NEXVIRTUSLEGS(14016, new Item[] { new Item(orb, 400) }, 1400, anim),
		TTTORVAHELMET(6927, new Item[] { new Item(orb, 2000) }, 4101, anim),
		TTTORVALEGS(6929, new Item[] { new Item(orb, 2000) }, 12000, anim),
		TTROVABODY(6928, new Item[] { new Item(orb, 2000) }, 12000, anim),
		TTPERNIXHLMET(6930, new Item[] { new Item(orb, 2000) }, 12000, anim),
		TTPERNIXHLMET2(6937, new Item[] { new Item(orb, 1200) }, 12000, anim),

		TTPERNIXBODY(6931, new Item[] { new Item(orb, 2000) }, 12000, anim),
		TTPERNIXLEGS(6932, new Item[] { new Item(orb, 2000) }, 12000, anim),
		TTVIRTUSMASK(6933, new Item[] { new Item(orb, 2000) }, 12000, anim),
		TTVIRTUSBODY(6934, new Item[] { new Item(orb, 2000) }, 12000, anim),
		TTVIRTUSLEGS(6935, new Item[] { new Item(orb, 2000) }, 12000, anim),
		GWDBANDOBODY(11724, new Item[] { new Item(orb, 240) }, 1240, anim),
		asdasdsd(17291, new Item[] { new Item(orb, 1250) }, 4240, anim),

		GWDBANDOBOOTS(11728, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWDBANDOTASSETS(11726, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWDSHARD_1(11710, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWD_SHARD2(11712, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWD_HILT1(11704, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWD_SHARD3(11714, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWDZAMORAKHASTSA(11716, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWD_HILT2(11708, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWDAMRABODY(11720, new Item[] { new Item(orb, 240) }, 1240, anim),
		HWDARMAHELMET(11718, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWDSARASWORD(11730, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWDARMABODY(11722, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWD_HILT3(11702, new Item[] { new Item(orb, 240) }, 1240, anim),
		FIREYBOW(16754, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWDSARADOMINROBS(17235, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWDSARAROBE2(15846, new Item[] { new Item(orb, 240) }, 1240, anim),

		GWDSARAROBBOTOM(15806, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWDSRARROBE2(16863, new Item[] { new Item(orb, 240) }, 1240, anim),
		GWDARAMACROSSBOW(22034, new Item[] { new Item(orb, 240) }, 1240, anim),
		ABBYWHIP(4151, new Item[] { new Item(orb, 120) }, 1240, anim),
		ABBYWHIP2(15441, new Item[] { new Item(orb, 120) }, 1240, anim),
		ABBYWHIP3(15442, new Item[] { new Item(orb, 120) }, 1240, anim),
		ABBYWHIP4(15443, new Item[] { new Item(orb, 120) }, 1240, anim),
		ABBYWHIP5(15444, new Item[] { new Item(orb, 120) }, 1240, anim),

		FURY(6585, new Item[] { new Item(orb, 141) }, 1141, anim),
		STAFFOFLIGHT(15486, new Item[] { new Item(orb, 141) }, 14101, anim),
		PVPMORHELMT(13876, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPSTAT(13896, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPSTAT2(13884, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPSTAT3(13890, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPSTATHAMMER(13902, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPVESTLEGS(13893, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPVETSALONG(13899, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPZURLEHELM(13864, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPZURBODY(13858, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPZURLEGS(13861, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPMORHELM(13878, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPMOREBODY(13870, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPMORELEGS(13873, new Item[] { new Item(orb, 169) }, 1169, anim),
		GWDSARAHOOD(16753, new Item[] { new Item(orb, 169) }, 1169, anim),
		REVFRSOTHELM(13922, new Item[] { new Item(orb, 169) }, 1169, anim),
		REVFROSTHAMMER(13928, new Item[] { new Item(orb, 169) }, 1169, anim),
		REVFROSTLEG(13916, new Item[] { new Item(orb, 169) }, 1169, anim),
		REVFRSOTBODY(13910, new Item[] { new Item(orb, 169) }, 1169, anim),
		REVMORRIHELM(13952, new Item[] { new Item(orb, 169) }, 1169, anim),
		REVMOREBOYD(13946, new Item[] { new Item(orb, 169) }, 1169, anim),
		demsword(14915, new Item[] { new Item(orb, 300) }, 2000, anim),

		tormclas(18685, new Item[] { new Item(orb, 350) }, 1169, anim),

		morrig1(13953, new Item[] { new Item(orb, 50) }, 500, anim),
		morrig2(13954, new Item[] { new Item(orb, 50) }, 500, anim),
		morrig3(13955, new Item[] { new Item(orb, 50) }, 500, anim),
		morrig4(13956, new Item[] { new Item(orb, 50) }, 500, anim),
		morrig5(13957, new Item[] { new Item(orb, 50) }, 500, anim),
		morrig6(13958, new Item[] { new Item(orb, 50) }, 500, anim),

		REVMORELEGS(13949, new Item[] { new Item(orb, 169) }, 1169, anim),
		REVPINKHOOD(13940, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPPINKSTAFF(13943, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPPINKBOYD(13934, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPPINKLEGS(13937, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPGOLDHOODY(13996, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPGILDSPEAR(13931, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPGILDBODY(13913, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPGILDBODY2(19123, new Item[] { new Item(orb, 169) }, 1169, anim),

		PVPGILDBODY3(11617, new Item[] { new Item(orb, 169) }, 1169, anim),

		PVPGILDLONG(13925, new Item[] { new Item(orb, 169) }, 1169, anim),
		PVPGILDLEGS(13919, new Item[] { new Item(orb, 169) }, 1169, anim),
		GZONEGS(18835, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONEHELMET(18834, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONEBODY(18801, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONELEGS(18800, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONECBOW(18799, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONEGS2(18797, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONEGS3(18798, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONEGS4(18796, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONEBANDOBOOTS(19794, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONESARASWORD(18792, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONEZILYAHELM(15900, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONEZILBODY(15845, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONEBILLEGS(15805, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONETUTSPEAR(18790, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONEGS5(18789, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONETUTHALO(18791, new Item[] { new Item(orb, 310) }, 1310, anim),
		GZONETUTBOOTS(18787, new Item[] { new Item(orb, 310) }, 1310, anim),
		BARROW1(4708, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW2(4712, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW3(4714, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW4(4710, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW1set(11846, new Item[] { new Item(orb, 200) }, 600, anim),
		BARROW2set(11848, new Item[] { new Item(orb, 200) }, 600, anim),
		BARROW3set(11850, new Item[] { new Item(orb, 200) }, 600, anim),
		BARROW4set(11852, new Item[] { new Item(orb, 200) }, 600, anim),
		BARROW5set(11854, new Item[] { new Item(orb, 200) }, 600, anim),
		BARROW6set(11856, new Item[] { new Item(orb, 200) }, 600, anim),

		BARRO5(4716, new Item[] { new Item(orb, 50) }, 150, anim),
		BARRO6(4720, new Item[] { new Item(orb, 50) }, 150, anim),
		BARRO7(4722, new Item[] { new Item(orb, 50) }, 150, anim),
		BARRO8(4718, new Item[] { new Item(orb, 50) }, 150, anim),
		BARRO9(4753, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW10(4751, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW11(4757, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW12(4759, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW14(4755, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW13(4724, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW16(4728, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW18(4730, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW19(4726, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW20(4745, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW21(4749, new Item[] { new Item(orb, 50) }, 150, anim),

		BARRO22(4747, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW23(4732, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW34(4734, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW35(4736, new Item[] { new Item(orb, 50) }, 150, anim),
		BARROW37(4738, new Item[] { new Item(orb, 50) }, 150, anim),
		C_LONG(18351, new Item[] { new Item(orb, 50) }, 150, anim),
		C_RAP(18349, new Item[] { new Item(orb, 50) }, 150, anim),
		C_MAUL(18353, new Item[] { new Item(orb, 50) }, 4101, anim),
		C_STAFF(18355, new Item[] { new Item(orb, 50) }, 4101, anim),
		C_CBOW(18357, new Item[] { new Item(orb, 50) }, 4101, anim),
		WARRIOEHELM(4882, new Item[] { new Item(orb, 100) }, 1000, anim),
		WARRIORAXE(4888, new Item[] { new Item(orb, 100) }, 1000, anim),
		WARRIORLEGS(4894, new Item[] { new Item(orb, 100) }, 1000, anim),
		WARRIORBODY(4900, new Item[] { new Item(orb, 100) }, 1000, anim),
		WARRIORSHIELD(18747, new Item[] { new Item(orb, 100) }, 1000, anim),
		TORMENTEDDEFENDER(18684, new Item[] { new Item(orb, 100) }, 1000, anim),
		TORMENTEDWHIP(18686, new Item[] { new Item(orb, 100) }, 1000, anim),
		WARRIORGLOVES(20460, new Item[] { new Item(orb, 100) }, 1000, anim),
		WARRIORBOOTS(20456, new Item[] { new Item(orb, 100) }, 1000, anim),
		TOKHAARCAPE(19111, new Item[] { new Item(orb, 100) }, 1000, anim),
		MAGEBEASTHELM(19140, new Item[] { new Item(orb, 200) }, 1000, anim),
		MAGEBEASTBODY(19139, new Item[] { new Item(orb, 200) }, 1000, anim),
		MEABEATSLEGS(19138, new Item[] { new Item(orb, 200) }, 1000, anim),
		DEMONMAUL(22078, new Item[] { new Item(orb, 645) }, 1645, anim),
		DEMONMAUL2(22079, new Item[] { new Item(orb, 1250) }, 1645, anim),

		IMPWINGS(4411, new Item[] { new Item(orb, 100) }, 1000, anim),
		IMPBOW(16337, new Item[] { new Item(orb, 100) }, 1000, anim),
		IMPCANNON(671, new Item[] { new Item(orb, 100) }, 1000, anim),
		IMPCOIF(14415, new Item[] { new Item(orb, 100) }, 1000, anim),
		IMPBODY(14395, new Item[] { new Item(orb, 100) }, 1000, anim),
		IMPLEGS(14405, new Item[] { new Item(orb, 100) }, 1000, anim),
		IMPGLOVES(672, new Item[] { new Item(orb, 100) }, 1000, anim),
		IMPBOOTS(673, new Item[] { new Item(orb, 100) }, 1000, anim),
		LORGRING(681, new Item[] { new Item(orb, 200) }, 1200, anim),
		LORDSWORD(676, new Item[] { new Item(orb, 200) }, 1200, anim),
		LORDSHIELD(18363, new Item[] { new Item(orb, 200) }, 1200, anim),
		LORDHELM(677, new Item[] { new Item(orb, 200) }, 1200, anim),
		LORGSBODY(678, new Item[] { new Item(orb, 200) }, 1200, anim),
		LORDLEGS(679, new Item[] { new Item(orb, 200) }, 1200, anim),
		DEMONBIGMAUL(734, new Item[] { new Item(orb, 530) }, 1530, anim),
		DEMONTOP(15424, new Item[] { new Item(orb, 530) }, 1530, anim),
		DEMONHOOD(666, new Item[] { new Item(orb, 530) }, 1530, anim),
		DEMONSKIRT(674, new Item[] { new Item(orb, 530) }, 1530, anim),
		JOYXHELMET(675, new Item[] { new Item(orb, 930) }, 4101, anim),
		JOYXBODY(702, new Item[] { new Item(orb, 930) }, 1930, anim),
		JOYXLEGS(700, new Item[] { new Item(orb, 930) }, 1930, anim),
		JOYXKITESHIELD(701, new Item[] { new Item(orb, 930) }, 1930, anim),
		JOYXSWORD(17708, new Item[] { new Item(orb, 930) }, 1930, anim),
		JOYXCAPE(17602, new Item[] { new Item(orb, 930) }, 1930, anim),
		HELLFIRE_SIGIL(18783, new Item[] { new Item(orb, 1250) }, 2500, anim),
		CUSTOM_SIGIL(18784, new Item[] { new Item(orb, 1250) }, 2500, anim),
		IMPNECK(19887, new Item[] { new Item(orb, 100) }, 1000, anim),
		LORDCAPE(4393, new Item[] { new Item(orb, 200) }, 1000, anim),
		LORDSWORD2(22075, new Item[] { new Item(orb, 200) }, 1000, anim),
		REGLORDHELM(19471, new Item[] { new Item(orb, 200) }, 1000, anim),
		TENT(22008, new Item[] { new Item(orb, 200) }, 1000, anim),

		REGLORDBODY(19470, new Item[] { new Item(orb, 200) }, 1000, anim),
		RELORGLEGS(19469, new Item[] { new Item(orb, 200) }, 1000, anim),
		DEMONWINNGS(4369, new Item[] { new Item(orb, 350) }, 1000, anim),
		DEMONBOOTS(16269, new Item[] { new Item(orb, 350) }, 1000, anim),
		DEMONGLOVES(15943, new Item[] { new Item(orb, 350) }, 1000, anim),
		RAGNERBOW(18332, new Item[] { new Item(orb, 100) }, 1000, anim),
		RANGERGLOVES(15026, new Item[] { new Item(orb, 100) }, 1000, anim),
		MAGEICSTAFF(14377, new Item[] { new Item(orb, 100) }, 1000, anim),
		MAGICBOOTS(10865, new Item[] { new Item(orb, 100) }, 1000, anim),
		MAGICGLOVES(12864, new Item[] { new Item(orb, 100) }, 1000, anim),
		RANGERBOOTS(10696, new Item[] { new Item(orb, 100) }, 1000, anim),
		REGJOYXHELM(19153, new Item[] { new Item(orb, 800) }, 1800, anim),
		REGJOYXBODY(19142, new Item[] { new Item(orb, 800) }, 1800, anim),
		REGJOYXLEGS(19141, new Item[] { new Item(orb, 800) }, 1800, anim),
		CHAOSRAP(18350, new Item[] { new Item(orb, 200) }, 1000, anim),
		CHOAKITE(18360, new Item[] { new Item(orb, 200) }, 1000, anim),
		CHAORSWORD(18352, new Item[] { new Item(orb, 200) }, 1000, anim),
		CHOASCBOW(18358, new Item[] { new Item(orb, 200) }, 1000, anim),
		CHAOSMAUL1(18354, new Item[] { new Item(orb, 200) }, 1000, anim),
		RANGERHELMET(17043, new Item[] { new Item(orb, 100) }, 1000, anim),
		RANGEBOYD(17175, new Item[] { new Item(orb, 100) }, 4101, anim),
		RANGERLEGS(17321, new Item[] { new Item(orb, 100) }, 4101, anim),
		ROW1(2572, new Item[] { new Item(orb, 100) }, 4101, anim),
		CYANTORVAHELMET(19931, new Item[] { new Item(orb, 600) }, 2100, anim),
		CYANTORVABODY(19933, new Item[] { new Item(orb, 600) }, 2100, anim),
		CYANTORVALEGS(19934, new Item[] { new Item(orb, 600) }, 2100, anim),
		ARCRAPIER(19919, new Item[] { new Item(orb, 600) }, 2100, anim),
		ARCMACE(19917, new Item[] { new Item(orb, 600) }, 2100, anim),
		CYANRING(7927, new Item[] { new Item(orb, 600) }, 2100, anim),
		MAGICGHAT(14733, new Item[] { new Item(orb, 100) }, 1000, anim),
		MAGICBODY(14732, new Item[] { new Item(orb, 100) }, 1000, anim),
		MAGICLEGS(14734, new Item[] { new Item(orb, 100) }, 1000, anim),
		SILIG1(13746, new Item[] { new Item(orb, 1200) }, 11200, anim),
		SIGIL2(13748, new Item[] { new Item(orb, 1200) }, 11200, anim),
		SIGIL3(13750, new Item[] { new Item(orb, 1200) }, 11200, anim),
		SIGIL4(13752, new Item[] { new Item(orb, 1200) }, 11200, anim),
		DRAGON1(1149, new Item[] { new Item(orb, 15) }, 150, anim),
		DRAGON2(1249, new Item[] { new Item(orb, 15) }, 150, anim),
		DRAGON3(3204, new Item[] { new Item(orb, 15) }, 150, anim),
		DRAGON4(1305, new Item[] { new Item(orb, 15) }, 150, anim),
		DRAGON5(1215, new Item[] { new Item(orb, 15) }, 150, anim),
		DRAGON6(1377, new Item[] { new Item(orb, 15) }, 150, anim),
		DRAGON7(1434, new Item[] { new Item(orb, 15) }, 150, anim),
		DRAGON8(7158, new Item[] { new Item(orb, 15) }, 150, anim),
		DEFAULT(1, new Item[] { new Item(orb, 41) }, 4101, anim);

		DissolvingData(int id, Item[] rewards, int experience, int animation) {
			this.id = id;
			this.rewards = rewards;
			this.experience = experience;
			this.animation = animation;
			// this.progressions = progressions;
		}

		private int id, experience, animation;
		private Item[] rewards;
		// private int[][] progressions;

		public int getId() {
			return id;
		}

		public int getExperience() {
			return experience;
		}

		public int getAnimation() {
			return animation;
		}

		public Item[] getRewards() {
			return rewards;
		}
		// public int[][] getProgressions() {
		// return progressions;
		// }

	}

	public ArrayList<Item> getDissolvableItemsInInv() {
		ArrayList<Item> dissolvableItems = new ArrayList<Item>();
		for (Item item : player.getInventory().getItems()) {
			if (isDissolvable(item.getId()))
				dissolvableItems.add(item);
		}
		return dissolvableItems;
	}

	public int getTotalOrbAmount(ArrayList<Item> allItems) {
		int totalOrbAmount = 0;
		for (Item item : allItems) {
			for (DissolvingData data : DissolvingData.values()) {
				if (data.getId() == item.getId())
					totalOrbAmount += (data.getRewards()[0].getAmount()*item.getAmount());
			}
		}
		return totalOrbAmount;
	}

	private boolean isDissolvable(int id) {
		for (DissolvingData data : DissolvingData.values()) {
			if (data.getId() == id)
				return true;
		}
		return false;
	}

	public void handle(int id, int amount) {

		for (DissolvingData data : DissolvingData.values()) {
			if (data.getId() == id) {
				player.getInventory().delete(id, amount);
				player.getInventory().add(data.getRewards()[0].getId(), (data.getRewards()[0].getAmount() * amount));
				player.getSkillManager().addExperience(Skill.INVENTION, (data.getExperience() * amount));
				player.performAnimation(new Animation(data.getAnimation()));
				//player.getProgressionManager().getProgressionTask(22).incrementProgress(0, 1);
				//player.getProgressionManager().getProgressionTask(18).incrementProgress(0, 1);
				//player.getProgressionManager().getProgressionTask(20).incrementProgress(2, 1);
				//player.getProgressionManager().getProgressionTask(23).incrementProgress(0, 1);
				//player.getProgressionManager().getProgressionTask(25).incrementProgress(2, 1);
				//player.getProgressionManager().getProgressionTask(27).incrementProgress(0, 1);
				//player.getProgressionManager().getProgressionTask(28).incrementProgress(0, 1);
				//player.getProgressionManager().getProgressionTask(29).incrementProgress(2, 1);
				// handleProgressions(data.getProgressions());
				break;
			}
		}

	}
}

/*
 * private void handleProgressions(int[][] progressions) { for(int[] progression
 * : progressions) {
 * player.getProgressionManager().getProgressionTask(progression[0]).
 * incrementProgress(progression[1], progression[2]); } }
 * 
 * }
 */
