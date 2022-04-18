package net.acticraft.pixelcategorysg.Event;

import net.acticraft.pixelcategorysg.Arena.PlayerData;
import net.acticraft.pixelcategorysg.PixelCategorySG;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class CombatEvents implements Listener {

    private String res;

    @EventHandler
    public void OnDamageEntity(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player && PixelCategorySG.getInstance().arena.spectators.contains(e.getDamager().getUniqueId())){
            e.setCancelled(true);
            return;
        }
        if(e.getEntity() instanceof Player) {

            if(!PixelCategorySG.getInstance().arena.CanDamage()){
                e.setCancelled(true);
            }

            if(e.getDamager() instanceof Player) {
                PlayerData playerData = PixelCategorySG.getInstance().arena.playersInGame.get(e.getEntity().getUniqueId());
                playerData.setDamager((Player) e.getDamager());
            } else if(e.getDamager() instanceof Projectile) {
                Projectile proj = (Projectile) e.getDamager();
                if(proj.getShooter() != null && proj.getShooter() instanceof Player) {
                    PlayerData playerData = PixelCategorySG.getInstance().arena.playersInGame.get(e.getEntity().getUniqueId());
                    playerData.setDamager((Player) proj.getShooter());
                }
            }
        }
    }

    @EventHandler
    public void OnDeath(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if(player.getHealth() - e.getDamage() <= 0) {
                player.setHealth(20.0);
                e.setCancelled(true);
                PixelCategorySG.getInstance().arena.handleDeath(player, e.getCause());
            }
        }
    }



}
