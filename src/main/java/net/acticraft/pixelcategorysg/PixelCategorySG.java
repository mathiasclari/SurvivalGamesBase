package net.acticraft.pixelcategorysg;

import fr.mrmicky.fastboard.FastBoard;
import net.acticraft.pixelcategorysg.Commands.StartCommand;
import net.acticraft.pixelcategorysg.Commands.StopCommand;
import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.Listeners.BlockBreakListener;
import net.acticraft.pixelcategorysg.Listeners.FireSpreadListener;
import net.acticraft.pixelcategorysg.Listeners.MobSpawnListener;
import net.acticraft.pixelcategorysg.ScoreBoard.LobbySB;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;


public final class PixelCategorySG extends JavaPlugin {

    private static PixelCategorySG instance;
    private GameManager gameManager;
    private String host;
    private String database;
    private String user;
    private String password;


    private final YamlConfiguration conf = new YamlConfiguration();


    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("onEnable has been invoked!");

        //Config
        File co = new File(getDataFolder(), "config.yml");
        if(!co.exists()) saveResource("config.yml", false);





        //MiniGame Files
        this.gameManager = new GameManager(this);

        getServer().getPluginManager().registerEvents(new BlockBreakListener(gameManager),this);
        //ScoreBoard Listener
        getServer().getPluginManager().registerEvents(new LobbySB(),this);
        //
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

    public YamlConfiguration getConf() {

    host = conf.getString("host");
    database = conf.getString("database");
    user = conf.getString("user");
    password = conf.getString("password");
        return this.conf; }


    public static PixelCategorySG getInstance() {
        return instance;
    }
}
