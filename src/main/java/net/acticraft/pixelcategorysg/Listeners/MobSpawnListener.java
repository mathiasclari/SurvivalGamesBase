package net.acticraft.pixelcategorysg.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobSpawnListener implements Listener {

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e){
        if(e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL){
            e.setCancelled(true);
        }
        if(e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.DEFAULT){
            e.setCancelled(true);
        }
        if(e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.ENDER_PEARL){
            e.setCancelled(true);
        }
    }
}
