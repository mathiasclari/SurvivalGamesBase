package net.acticraft.pixelcategorysg.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;

public class FireSpreadListener implements Listener {

    @EventHandler
    public void onFireSpread(BlockBurnEvent e){
        e.setCancelled(false);
    }
}
