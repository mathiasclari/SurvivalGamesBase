package net.acticraft.pixelcategorysg.Tasks;

import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.GameManager.GameState;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStartCountdownTask extends BukkitRunnable {

    private GameManager gameManager;

    public GameStartCountdownTask(GameManager gameManager){
        this.gameManager = gameManager;
    }

    private int timeleft = 30;

    @Override
    public void run(){
        timeleft--;
        if(timeleft <= 0){
            cancel();
            gameManager.setGameState(GameState.ACTIVE);
            return;
        }

        Bukkit.broadcastMessage(timeleft + " until game starts");
    }

}
