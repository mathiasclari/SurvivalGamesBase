package net.acticraft.pixelcategorysg;

import dev.jcsoftware.jscoreboards.JGlobalScoreboard;
import net.acticraft.pixelcategorysg.Commands.StartCommand;
import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.Listeners.BlockBreakListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.util.Arrays;


public final class PixelCategorySG extends JavaPlugin {
    private GameManager gameManager;
    private JGlobalScoreboard scoreboard;

    @Override
    public void onEnable() {
        JGlobalScoreboard scoreboard = new JGlobalScoreboard(
                () -> "&6&lHungerGames", // The title supplier
                () -> { // The lines supplier
                    return Arrays.asList(
                            "Lobby 1",
                            "test"
                    );
                }
        );
        Bukkit.getOnlinePlayers().forEach(this::addToScoreboard);




        super.onEnable();

        this.gameManager = new GameManager(this);

        getServer().getPluginManager().registerEvents(new BlockBreakListener(gameManager),this);

        getCommand("start").setExecutor(new StartCommand(gameManager));
    }

    private void addToScoreboard(Player player){
        scoreboard.addPlayer(player);
        scoreboard.updateScoreboard();
    }

    @Override
    public void onDisable() {

        gameManager.cleanup();
        // Plugin shutdown logic
    }
}
