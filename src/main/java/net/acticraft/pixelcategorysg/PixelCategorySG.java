package net.acticraft.pixelcategorysg;

import net.acticraft.pixelcategorysg.Arena.Arena;
import net.acticraft.pixelcategorysg.Arena.ArenaGenerateRules;
import net.acticraft.pixelcategorysg.Commands.StartCommand;
import net.acticraft.pixelcategorysg.Commands.StopCommand;
import net.acticraft.pixelcategorysg.Event.ChestManager;
import net.acticraft.pixelcategorysg.Event.JoinGameEvent;
import net.acticraft.pixelcategorysg.Event.OnDeathEvent;
import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.GameManager.GameState;
import net.acticraft.pixelcategorysg.Listeners.BlockBreakListener;
import net.acticraft.pixelcategorysg.Listeners.ConnectListener;
import net.acticraft.pixelcategorysg.Listeners.PregameListener;
//import net.acticraft.pixelcategorysg.MySql.MySQL;
import net.acticraft.pixelcategorysg.ScoreBoard.ScoreBoard;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;


public final class PixelCategorySG extends JavaPlugin {

    private static PixelCategorySG instance;
    public Arena arena;
    private GameManager gameManager;
    private ChestManager chestManager;

    private final YamlConfiguration conf = new YamlConfiguration();

     //   public MySQL SQL;


    @Override
    public void onEnable() {
        saveDefaultConfig();
        chestManager = new ChestManager(getConfig());
        instance = this;
        this.gameManager = new GameManager(this);
        getServer().getPluginManager().registerEvents(new ArenaGenerateRules(this),this);
        getServer().getPluginManager().registerEvents(new ConnectListener(),this);
        getServer().getPluginManager().registerEvents(new JoinGameEvent(),this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(gameManager),this);
        getServer().getPluginManager().registerEvents(new ChestManager(this.getConfig()),this);
        getServer().getPluginManager().registerEvents(new PregameListener(gameManager),this);
        getServer().getPluginManager().registerEvents(chestManager, this);
        getServer().getPluginManager().registerEvents(new OnDeathEvent(), this);
        getServer().getPluginManager().registerEvents(new ScoreBoard(),this);
        getCommand("gstart").setExecutor(new StartCommand(gameManager));
        getCommand("gstop").setExecutor(new StopCommand(gameManager));


        this.saveDefaultConfig();
        arena = new Arena(gameManager, this.getConfig());
        arena.checkReq();


        if (getServer().getPluginManager().getPlugin("Parties") != null) {
            if (getServer().getPluginManager().getPlugin("Parties").isEnabled()) {

                chestManager.resetChests();
                // Parties is enabled

    /*    this.SQL = new MySQL();

        try {
            SQL.connect();
        }catch (ClassNotFoundException | SQLException e){
            //e.printStackTrace();
            Bukkit.getLogger().info("Database is not connected!");

        }

        if(SQL.isConnected()){
            Bukkit.getLogger().info("Database is connected!");
        }


*/



        getLogger().info("onEnable has been invoked!");

        //Config
        File co = new File(getDataFolder(), "config.yml");
        if(!co.exists()) saveResource("config.yml", false);





        //MiniGame Files


        //ScoreBoard Listener

        //

        //ScoreBoard
            //LobbyScoreBoard


        gameManager.setGameState(GameState.LOBBY);

    }
        }
    }
    @Override
    public void onDisable() {

//        SQL.disconnect();

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
