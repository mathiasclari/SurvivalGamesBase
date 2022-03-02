package net.acticraft.pixelcategorysg.Tasks;

import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.GameManager.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class GameStartCountdownTask extends BukkitRunnable {

    private GameManager gameManager;
    private Player p;

    public GameStartCountdownTask(GameManager gameManager){
        this.gameManager = gameManager;
    }

    private int timeleft = 10;

    @Override
    public void run() {
        timeleft--;
        if (timeleft <= 0) {
            cancel();
            gameManager.setGameState(GameState.ACTIVE);
            return;
        }

        Bukkit.broadcastMessage(timeleft + " until game starts");
    }

}
