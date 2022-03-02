package net.acticraft.pixelcategorysg;

import net.acticraft.pixelcategorysg.Commands.StartCommand;
import net.acticraft.pixelcategorysg.Commands.StopCommand;
import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.Listeners.BlockBreakListener;
import net.acticraft.pixelcategorysg.Listeners.FireSpreadListener;
import net.acticraft.pixelcategorysg.Listeners.MobSpawnListener;
import net.acticraft.pixelcategorysg.ScoreBoard.LobbySB;
import org.bukkit.plugin.java.JavaPlugin;


public final class PixelCategorySG extends JavaPlugin {
    private GameManager gameManager;

    @Override
    public void onEnable() {
        super.onEnable();

        this.gameManager = new GameManager(this);

        getServer().getPluginManager().registerEvents(new BlockBreakListener(gameManager),this);
        getServer().getPluginManager().registerEvents(new LobbySB(),this);
        getServer().getPluginManager().registerEvents(new FireSpreadListener(),this);
        getServer().getPluginManager().registerEvents(new MobSpawnListener(),this);

        getCommand("start").setExecutor(new StartCommand(gameManager));
        getCommand("stop").setExecutor(new StopCommand(gameManager));

        //ScoreBoard
            //LobbyScoreBoard


    }

    @Override
    public void onDisable() {

        gameManager.cleanup();
        // Plugin shutdown logic
    }
}
