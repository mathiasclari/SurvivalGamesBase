package net.acticraft.pixelcategorysg.GameManager;

import net.acticraft.pixelcategorysg.PixelCategorySG;
import net.acticraft.pixelcategorysg.Tasks.GameStartCountdownTask;
import org.bukkit.Bukkit;

public class GameManager {
    private final PixelCategorySG plugin;
    private GameState gameState = GameState.LOBBY;

    private final BlockManager blockManager;
    private final PlayerManager playerManager;


    private GameStartCountdownTask gameStartCountdownTask;



    public GameManager(PixelCategorySG plugin){
        this.plugin = plugin;

        this.blockManager = new BlockManager(this);
        this.playerManager = new PlayerManager(this);


    }

    public  void  setGameState(GameState gameState){
        if(this.gameState == GameState.ACTIVE && gameState == GameState.STARTING) return;
        if(this.gameState == gameState) return;


        this.gameState = gameState;


        switch (gameState){
            case ACTIVE:
                if(this.gameStartCountdownTask != null) this.gameStartCountdownTask.cancel();

                Bukkit.broadcastMessage("Active!");
                getPlayerManager().giveKits();
                break;
            case STARTING:
                Bukkit.broadcastMessage("Starting!");
                // start countdown task
                // teleport players
                // clear inventories
                // etc
                this.gameStartCountdownTask = new GameStartCountdownTask(this);
                this.gameStartCountdownTask.runTaskTimer(plugin, 0,20);
                break;
        }
    }

    public void cleanup(){

    }
        public BlockManager getBlockManager(){return blockManager;}
        public PlayerManager getPlayerManager(){
        return playerManager;
        }


    public GameState getGameState() {
        return gameState;
    }
}
