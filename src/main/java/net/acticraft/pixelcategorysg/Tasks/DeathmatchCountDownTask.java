package net.acticraft.pixelcategorysg.Tasks;

import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.GameManager.GameState;
import net.acticraft.pixelcategorysg.PixelCategorySG;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathmatchCountDownTask extends BukkitRunnable {

    private GameManager gameManager;
    private Player p;

    public DeathmatchCountDownTask(GameManager gameManager){
        this.gameManager = gameManager;
    }

    private int timer = 30;


    @Override
    public void run() {
        timer--;
        if (timer <= 0) {
            cancel();
            gameManager.setGameState(GameState.PREDEATHMATCH);
            PixelCategorySG.getInstance().arena.StartGame();
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c&lDeathmatch has started!"));
            Bukkit.getWorld("world").getWorldBorder().setSize(65,120);
            return;


        }

        if(timer % 5 == 0 || timer < 5){
            Bukkit.broadcastMessage(ChatColor.of("#0F7AD9")+""+timer + " seconds until Deathmatch");
            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 500.0f, 1.0f));

        }
    }



}
