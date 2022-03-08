package net.acticraft.pixelcategorysg.Listeners;

import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.GameManager.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PregameListener implements Listener {


    private final GameManager gameManager;

    public PregameListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (gameManager.getGameState().equals(GameState.LOBBY)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMoveStarting(PlayerMoveEvent e) {
        if (gameManager.getGameState().equals(GameState.STARTING)) {
            e.setCancelled(true);
        }
    }
}