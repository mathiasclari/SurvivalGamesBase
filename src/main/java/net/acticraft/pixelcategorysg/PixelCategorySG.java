package net.acticraft.pixelcategorysg;

import net.acticraft.pixelcategorysg.Arena.Arena;
import net.acticraft.pixelcategorysg.Arena.ArenaGenerateRules;
import net.acticraft.pixelcategorysg.Commands.StartCommand;
import net.acticraft.pixelcategorysg.Commands.StopCommand;
import net.acticraft.pixelcategorysg.Event.ChestManager;
import net.acticraft.pixelcategorysg.Event.GameEvent;
import net.acticraft.pixelcategorysg.Event.CombatEvents;
import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.GameManager.GameState;
import net.acticraft.pixelcategorysg.Listeners.BlockBreakListener;
import net.acticraft.pixelcategorysg.Listeners.ConnectListener;
import net.acticraft.pixelcategorysg.Listeners.PregameListener;
import net.acticraft.pixelcategorysg.MySql.MySQL;
import net.acticraft.pixelcategorysg.ScoreBoard.ScoreBoard;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public final class PixelCategorySG extends JavaPlugin {

    private static PixelCategorySG instance;
    public Arena arena;
    public GameManager gameManager;
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
        getServer().getPluginManager().registerEvents(new GameEvent(),this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(gameManager),this);
        getServer().getPluginManager().registerEvents(new ChestManager(this.getConfig()),this);
        getServer().getPluginManager().registerEvents(new PregameListener(gameManager),this);
        getServer().getPluginManager().registerEvents(chestManager, this);
        getServer().getPluginManager().registerEvents(new CombatEvents(), this);
        getServer().getPluginManager().registerEvents(new ScoreBoard(),this);
        getCommand("gstart").setExecutor(new StartCommand(gameManager));
        getCommand("gstop").setExecutor(new StopCommand(gameManager));


        this.saveDefaultConfig();
        arena = new Arena(gameManager, this.getConfig());


        if (getServer().getPluginManager().getPlugin("Parties") != null) {
            if (getServer().getPluginManager().getPlugin("Parties").isEnabled()) {
                chestManager.resetChests();
            }
        }

        //DataBase
        String host =  getConfig().getString("host");
        int port = getConfig().getInt("port");
        String database = getConfig().getString("database");
        String user = getConfig().getString("user");
        String password = getConfig().getString("password");


        MySQL.Login(host,database,user,password,port);
        MySQL.CreateTables();
    }


    @Override
    public void onDisable() {

//        SQL.disconnect();

        gameManager.cleanup();
        // Plugin shutdown logic
    }




    public static PixelCategorySG getInstance() {
        return instance;
    }



}
