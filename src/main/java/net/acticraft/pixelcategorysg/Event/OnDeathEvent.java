package net.acticraft.pixelcategorysg.Event;

import net.acticraft.pixelcategorysg.PixelCategorySG;
import org.bukkit.*;
import org.bukkit.entity.Cat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.UUID;

public class OnDeathEvent implements Listener {
    private String res;


    @EventHandler
    private void RespawnEvent(PlayerRespawnEvent e){
        Player p = e.getPlayer();

        p.setGameMode(GameMode.ADVENTURE);

        p.setAllowFlight(true);
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999, 2, true, false));
        p.dropItem(false);
        p.setCanPickupItems(false);
        p.playSound(p.getLocation(), Sound.ENTITY_CAT_DEATH, 500.0f, 1.0f);
        p.setCollidable(true);
        p.setHealth(1);
        p.setHealthScale(1);
        p.sendTitle(ChatColor.DARK_PURPLE + "YOU HAVE BEEN MURDERED", ChatColor.GRAY + "good luck next time!", 10, 20, 10);



    }
}
