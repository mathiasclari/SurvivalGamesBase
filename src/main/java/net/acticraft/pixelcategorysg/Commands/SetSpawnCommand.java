package net.acticraft.pixelcategorysg.Commands;

import net.acticraft.pixelcategorysg.PixelCategorySG;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        /*
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.isOp()) {
                if(args.length == 2) {
                    if(args[0].equals("add")) {
                        if(args[1].equals("spawn")) {
                            int i = 0;
                            for(String a : PixelCategorySG.plugin.getConfig().getConfigurationSection("goldDrop").getKeys(false)) {
                                i++;
                            }
                            i++;
                            PixelCategorySG.plugin.getConfig().set("Spawns."+"spawn"+i+".world", player.getWorld().getName());
                            PixelCategorySG.plugin.getConfig().set("Spawns."+"spawn"+i+".x", player.getLocation().getX());
                            PixelCategorySG.plugin.getConfig().set("Spawns."+"spawn"+i+".y", player.getLocation().getY());
                            PixelCategorySG.plugin.getConfig().set("Spawns."+"spawn"+i+".z", player.getLocation().getZ());
                            PixelCategorySG.plugin.saveConfig();
                            PixelCategorySG.PlayerMessager(1, player, "Added spawn named: §c"+i);
                        }
                    }
                }
            }
        }
       */ return true;
    }
}
