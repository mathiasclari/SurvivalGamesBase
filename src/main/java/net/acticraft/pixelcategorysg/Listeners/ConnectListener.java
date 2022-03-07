package net.acticraft.pixelcategorysg.Listeners;

import net.acticraft.pixelcategorysg.Arena.Arena;
import net.acticraft.pixelcategorysg.PixelCategorySG;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectListener implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Arena arena = PixelCategorySG.getInstance().arena;
        arena.PlayerJoin(p);



    }

    @EventHandler
    private void onLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        Arena arena = PixelCategorySG.getInstance().arena;
        arena.PlayerLeave(p);
    }
}
