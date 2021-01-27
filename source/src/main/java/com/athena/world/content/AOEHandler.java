package com.athena.world.content;

import com.athena.model.CombatIcon;
import com.athena.model.Hit;
import com.athena.model.Hitmask;
import com.athena.model.Locations;
import com.athena.util.RandomUtility;
import com.athena.world.entity.impl.Character;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;

import java.util.Iterator;

/**
 * 861 100 3000 15 Range

861 = Item ID
100 = Minimum damage
3000 = maximum damage
15 = radius
Range = Weapon combat icon that it'll show.
 * @author HP Laptop
 *
 */
public class AOEHandler {

	public static void handleAttack(Character attacker, Character victim, int minimumDamage, int maximumDamage,
									int radius, CombatIcon combatIcon) {

		// if no radius, loc isn't multi, stops.
		if (radius == 0 || !Locations.Location.inMulti(victim)) {
			System.out.println("Radius 0");
			return;
		}

		// We passed the checks, so now we do multiple target stuff.
		Iterator<? extends Character> it = null;
		if (attacker.isPlayer() && victim.isPlayer()) {
			it = ((Player) attacker).getLocalPlayers().iterator();
		} else if (attacker.isPlayer() && victim.isNpc()) {
			it = ((Player) attacker).getLocalNpcs().iterator();

			for (Iterator<? extends Character> $it = it; $it.hasNext();) {
				Character next = $it.next();

				if (next == null) {
					continue;
				}

				if (next.isNpc()) {
					NPC n = (NPC) next;
					if (!n.getDefinition().isAttackable() || n.isSummoningNpc()) {
						continue;
					}
				} else {
					Player p = (Player) next;
					if (p.getLocation() != Locations.Location.WILDERNESS || !Locations.Location.inMulti(p)) {
						continue;
					}
				}

				if (next.getPosition().isWithinDistance(victim.getPosition(), radius) && !next.equals(attacker)
						&& !next.equals(victim) && next.getConstitution() > 0 && next.getConstitution() > 0) {
					int calc = RandomUtility.inclusiveRandom(minimumDamage, maximumDamage);
					next.dealDamage(new Hit(calc, Hitmask.RED, combatIcon));
					next.getCombatBuilder().addDamage(attacker, calc);
				}
			}
		}

	}
}
