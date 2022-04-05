package net.acticraft.pixelcategorysg.Event;

import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.PixelCategorySG;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class GameEvent implements Listener {
    private GameManager gameManager;


    @EventHandler
    private void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.setInvulnerable(false);
        p.setHealth(20);
        p.setExp(0);
        p.setLevel(0);
        p.setFoodLevel(20);
        p.setHealthScale(20);
        p.getInventory().clear();
        p.setCanPickupItems(true);

    }

    @EventHandler
    private void NObreaky(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction().equals(Action.PHYSICAL)){
            e.setCancelled(true);
        }
        if(PixelCategorySG.getInstance().arena.spectators.contains(p.getUniqueId())){
            e.setCancelled(true);
        }

    }

}
