package net.acticraft.pixelcategorysg;

import net.acticraft.pixelcategorysg.Commands.StartCommand;
import net.acticraft.pixelcategorysg.Commands.StopCommand;
import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.GameManager.GameState;
import net.acticraft.pixelcategorysg.Listeners.BlockBreakListener;
import net.acticraft.pixelcategorysg.Listeners.FireSpreadListener;
import net.acticraft.pixelcategorysg.Listeners.MobSpawnListener;
import net.acticraft.pixelcategorysg.MySql.MySQL;
import net.acticraft.pixelcategorysg.ScoreBoard.LobbySB;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;


public final class PixelCategorySG extends JavaPlugin {

    private static PixelCategorySG instance;
    private GameManager gameManager;


    private final YamlConfiguration conf = new YamlConfiguration();

        public MySQL SQL;


    @Override
    public void onEnable() {


        if (getServer().getPluginManager().getPlugin("Parties") != null) {
            if (getServer().getPluginManager().getPlugin("Parties").isEnabled()) {
                // Parties is enabled

        this.SQL = new MySQL();

        try {
            SQL.connect();
        }catch (ClassNotFoundException | SQLException e){
            //e.printStackTrace();
            Bukkit.getLogger().info("Database is not connected!");

        }

        if(SQL.isConnected()){
            Bukkit.getLogger().info("Database is connected!");
        }




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


        gameManager.setGameState(GameState.LOBBY);

    }
        }
    }
    @Override
    public void onDisable() {

        SQL.disconnect();

        gameManager.cleanup();
        // Plugin shutdown logic
    }

    /*public YamlConfiguration getConf() {
    host = conf.getString("host");
    database = conf.getString("database");
    user = conf.getString("user");
    password = conf.getString("password");
        return this.conf; }

*/
    public static PixelCategorySG getInstance() {
        return instance;
    }



}
