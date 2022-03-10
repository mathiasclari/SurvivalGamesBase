package net.acticraft.pixelcategorysg.Event;

import net.acticraft.pixelcategorysg.Arena.PlayerData;
import net.acticraft.pixelcategorysg.PixelCategorySG;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnDeathEvent implements Listener {
    private String res;


    @EventHandler
    private void RespawnEvent(PlayerRespawnEvent e){
        Player p = e.getPlayer();

    }
}
