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
            Bukkit.getServer().shutdown();
            if(timer == 10 ||timer == 7 || timer <= 5) {
                Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(ChatColor.of("#0F7AD9") + String.valueOf(timer), ChatColor.GRAY + "seconds until restart!", 10, 20, 10));
                Bukkit.broadcastMessage(ChatColor.of("#0F7AD9")+""+timer + " until restart");
                System.out.println("Restarting in " + timer + " seconds");
        }
    }

}
}