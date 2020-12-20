package com.athena.util;

import com.athena.world.entity.impl.player.Player;

/**
 * @author Stan van der Bend
 */
public interface Command {

    void execute(final Player player, String command);

}
