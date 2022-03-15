package net.acticraft.pixelcategorysg.Tasks;

import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.GameManager.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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
            Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(ChatColor.DARK_PURPLE + String.valueOf(timeLeft), ChatColor.GRAY + "seconds until game starts!", 10, 20, 10));
        }
        if (timeLeft <= 5){
            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 500.0f, 1.0f));
        }
        if (timeLeft <= 4){
            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 500.0f, 1.0f));
        }
        if (timeLeft <= 3){
            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 500.0f, 1.0f));
        }
        if (timeLeft <= 2){
            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 500.0f, 1.0f));
        }
        if (timeLeft <= 1){
            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 500.0f, 1.0f));
        }
        if (timeLeft <= 0){
            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 500.0f, 1.0f));
        }

        Bukkit.broadcastMessage(timeLeft + " until game starts");


    }

}
