package com.arlania.world.content.pos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A single thread executor that executes pos-related behavior on a separate thread.
 *
 * @author Andys1814.
 */
public final class PlayerOwnedShopThreadExecutor {

    private final PlayerOwnedShopManager manager;

    public PlayerOwnedShopThreadExecutor(PlayerOwnedShopManager manager) {
        this.manager = manager;
    }

    private static final ExecutorService threadExecutor = Executors.newSingleThreadExecutor();

    public void execute(Runnable runnable) {
        threadExecutor.execute(runnable);
    }

    public void openEditor() {
        execute(manager::openEditor);
    }

    public void openShop() {
        execute(manager::open);
    }

}
