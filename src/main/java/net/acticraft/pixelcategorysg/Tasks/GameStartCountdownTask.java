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

    private int timeLeft = 10;

    @Override
    public void run() {
        timeLeft--;
        if (timeLeft <= 0) {
            cancel();
            gameManager.setGameState(GameState.ACTIVE);
            return;


        }
        if (timeLeft <= 5) {
            Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(ChatColor.RED + String.valueOf(timeLeft), ChatColor.GRAY + "seconds until game starts!", 10, 20, 10));
        }

        Bukkit.broadcastMessage(timeLeft + " until game starts");


    }

}
