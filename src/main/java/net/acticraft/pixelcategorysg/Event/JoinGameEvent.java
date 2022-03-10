package net.acticraft.pixelcategorysg.Event;

import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.GameManager.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinGameEvent implements Listener {
    private GameManager gameManager;


    @EventHandler
    private void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.setInvulnerable(false);
        p.setHealth(20);
        p.setHealthScale(20);
        p.getInventory().clear();
        p.setCanPickupItems(true);

    }
}
