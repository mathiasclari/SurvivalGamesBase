package net.acticraft.pixelcategorysg.ArenaSystem;

import org.bukkit.World;

public interface GameMap {
    boolean lead();
    void unload();
    boolean restoreFromSource();

    boolean isLoaded();
    World getWorld();
}
