package net.acticraft.pixelcategorysg.Event;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnDeathEvent implements Listener {

    @EventHandler
    private void RespawnEvent(PlayerRespawnEvent e){
        Player p = e.getPlayer();

        p.setGameMode(GameMode.SPECTATOR);


    }
}
