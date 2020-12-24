package com.athena.world.content.combat;

import com.athena.world.content.combat.restrictions.KillCTRestrictor;
import com.athena.world.entity.impl.Character;
import com.athena.world.entity.impl.npc.NPC;
import com.athena.world.entity.impl.player.Player;

/**
 * Created by Tyler on 10/31/2017.
 */
public class CombatController {

    public static boolean canAttack(Character attacker, Character target) {
        if(attacker instanceof Player) {
            if(target instanceof NPC) {
                if(!canAttack((Player) attacker, (NPC) target))
                    return false;
            }
        }
        return true;
    }

    //Note that because of the checks above this can be considered as a PVM strict method.
    private static boolean canAttack(Player player, NPC npc) {
        return KillCTRestrictor.meetsRequirement(player, npc.getId());
    }

}
