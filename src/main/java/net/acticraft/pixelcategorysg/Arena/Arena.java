package net.acticraft.pixelcategorysg.Arena;

import net.acticraft.pixelcategorysg.GameManager.GameManager;
import net.acticraft.pixelcategorysg.GameManager.GameState;
import net.acticraft.pixelcategorysg.PixelCategorySG;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class Arena {
    public String worldname;
    public Location center;
    public List<Location> spawnLocations = new ArrayList<>();
    public int maxplayers;
    public int minplayers;
    public double range;
    public List<Boolean> takenPoz = new ArrayList<>();
    public Map<UUID, PlayerData> playersInGame = new HashMap<UUID, PlayerData>();
    public GameState gameState = GameState.LOBBY;
    private GameManager gameManager;
    private int protectionSec = 20;
    private long timeStarted;


    public Arena(GameManager gameManager, FileConfiguration config) {
        this.gameManager = gameManager;
        worldname = config.getString("location.world");
        minplayers = config.getInt("min-players");
        maxplayers = config.getInt("max-players");
        range = config.getDouble("spawnradius");

    }

    public void setWorld(World world, FileConfiguration config) {
        double y = config.getDouble("location.y");
        center = new Location(world, config.getDouble("location.x"), y, config.getDouble("location.z"));
        for (int i = 0; i < maxplayers; i++) {
            double rad = (double) i / maxplayers * Math.PI * 2;
            double z = Math.sin(rad) * range;
            double x = Math.cos(rad) * range;
            float yaw = (float) (rad / (Math.PI * 2) * 360 + 90);
            Location l = new Location(world, x + center.getX(), 0 + center.getY(), z + center.getZ()).getBlock().getLocation().add(0.5, 0, 0.5);
            l.setYaw(yaw);
            spawnLocations.add(l);
            l.getBlock().setType(Material.IRON_TRAPDOOR);
            //System.out.println("/tp " + x + " " + z);
            //System.out.println("/tp " + l.getX() + " " + l.getY() + " " + l.getZ());

            takenPoz.add(false);

        }
    }


    public void PlayerJoin(Player player) {

        if (!gameState.equals(GameState.LOBBY)) {

            //TODO kick player
            return;
        }
        if (Bukkit.getOnlinePlayers().size() >= minplayers) {
            PixelCategorySG.getInstance().gameManager.setGameState(GameState.STARTING);
        }

        int index = -1;
        for (int i = 0; i < takenPoz.size(); i++) {
            if (!takenPoz.get(i)) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            takenPoz.set(index, true);
            PlayerData playerData = new PlayerData(index, player);
            playersInGame.put(player.getUniqueId(), playerData);
            player.teleport(spawnLocations.get(index));
            System.out.println(spawnLocations.get(index).getYaw());


        } else {

            Bukkit.getPlayer(player.getUniqueId()).sendMessage(ChatColor.RED + "No free spawnpoints");
            Bukkit.getPlayer(player.getUniqueId()).kickPlayer("No free spawnpoints");
        }


    }

    public void PlayerLeave(Player player) {
        PlayerData playerData = playersInGame.remove(player.getUniqueId());
        takenPoz.set(playerData.startIndex, false);
    }

    public boolean CanDamage() {
        return System.currentTimeMillis() - 1000 * protectionSec > timeStarted;
    }

    public void StartGame() {
        timeStarted = System.currentTimeMillis();
    }



    }
