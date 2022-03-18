package net.acticraft.pixelcategorysg.Tasks;

import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.GameManager.GameState;
import net.acticraft.pixelcategorysg.PixelCategorySG;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStartCountdownTask extends BukkitRunnable {

    private GameManager gameManager;
    private Player p;

    public GameStartCountdownTask(GameManager gameManager){
        this.gameManager = gameManager;
    }

    private int timeLeft = 20;


    @Override
    public void run() {
        timeLeft--;
        if (timeLeft <= 0) {
            cancel();
            gameManager.setGameState(GameState.ACTIVE);
            PixelCategorySG.getInstance().arena.StartGame();
            return;


        }

        if(timeLeft == 15 ||timeLeft == 10 || timeLeft <= 5) {
            Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(ChatColor.of("#0F7AD9") + String.valueOf(timeLeft), ChatColor.GRAY + "seconds until game starts!", 10, 20, 10));
            Bukkit.broadcastMessage(ChatColor.of("#0F7AD9")+""+timeLeft + " until game starts");
            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 500.0f, 1.0f));

        }
    }

}
