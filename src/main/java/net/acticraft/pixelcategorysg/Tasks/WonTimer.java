package net.acticraft.pixelcategorysg.Tasks;

import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.GameManager.GameState;
import net.acticraft.pixelcategorysg.PixelCategorySG;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WonTimer extends BukkitRunnable {

    private GameManager gameManager;
    private Player p;

    public WonTimer(GameManager gameManager){
        this.gameManager = gameManager;
    }

    private int timer = 10;


    @Override
    public void run() {
        timer--;
        if (timer <= 0) {
            cancel();
            gameManager.setGameState(GameState.RESTARTING);
            return;


        }

        if(timer == 0) {
            Bukkit.getServer().shutdown();;
        }
    }



}