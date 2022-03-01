package net.acticraft.pixelcategorysg;

import net.acticraft.pixelcategorysg.Commands.StartCommand;
import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.Listeners.BlockBreakListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PixelCategorySG extends JavaPlugin {
    private GameManager gameManager;

    @Override
    public void onEnable() {
        super.onEnable();

        this.gameManager = new GameManager(this);

        getServer().getPluginManager().registerEvents(new BlockBreakListener(gameManager),this);

        getCommand("start").setExecutor(new StartCommand(gameManager));
    }

    @Override
    public void onDisable() {

        gameManager.cleanup();
        // Plugin shutdown logic
    }
}
