package net.acticraft.pixelcategorysg.Arena;

import net.acticraft.pixelcategorysg.PixelCategorySG;
import org.bukkit.GameRule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

public class ArenaGenerateRules implements Listener {

    private PixelCategorySG plugin;

    public ArenaGenerateRules(PixelCategorySG plugin) {
        this.plugin= plugin;
    }

    @EventHandler
    public void WorldLoad(WorldLoadEvent e){
        e.getWorld().setGameRule(GameRule.DO_FIRE_TICK, false );
        e.getWorld().setGameRule(GameRule.DO_MOB_SPAWNING, false);
        e.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        e.getWorld().setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        e.getWorld().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        e.getWorld().setGameRule(GameRule.DO_PATROL_SPAWNING, false);
        e.getWorld().setGameRule(GameRule.MOB_GRIEFING, false);
        e.getWorld().setGameRule(GameRule.RANDOM_TICK_SPEED, 0);
        e.getWorld().setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        e.getWorld().setTime(6000);
        e.getWorld().setStorm(false);
        if(e.getWorld().getName().equals(plugin.arena.worldname)){
            plugin.arena.setWorld(e.getWorld(),plugin.getConfig());
        }
    }
}
