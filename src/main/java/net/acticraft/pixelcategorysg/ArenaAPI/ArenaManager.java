package net.acticraft.pixelcategorysg.ArenaAPI;

import net.acticraft.pixelcategorysg.PixelCategorySG;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArenaManager {

    private static PixelCategorySG plugin;
    private static ArenaManager arenaManager;

    // Player's location before joining an arena
    public Map<String, Location> locs = new HashMap<String, Location>();

    // Player's inventory and armor before joining an arena
    Map<String, ItemStack[]> inventory = new HashMap<String, ItemStack[]>();
    Map<String, ItemStack[]> armor = new HashMap<String, ItemStack[]>();

    List<Arena> arenas = new ArrayList<Arena>();
    int arenaSize = 0;

    public ArenaManager() {

    }

    // Static method to get the Manager
    public static ArenaManager getManager() {
        if (arenaManager == null)
            arenaManager = new ArenaManager();

        return arenaManager; // NOT THREAD SAFE!
    }

    /**
     * Gets an existing arena
     *
     * @param id The id number of an arena
     * @return The arena that the id number corresponds to
     */
    public Arena getArena(int id) {
        for (Arena arena : arenas) {
            if (arena.getId() == id) {
                return arena;
            }
        }
        return null;
    }

    /**
     * Adds a player to an existing arena
     *
     * @param p  A player
     * @param id The id number of an arena
     */
    public void addPlayer(Player p, int id) {
        Arena arena = getArena(id);
        if (arena == null) {
            p.sendMessage("Invalid arena!");
            return;
        }

        // Add player to arena players and save inventory
        arena.players.add(p.getName());
        inventory.put(p.getName(), p.getInventory().getContents());
        armor.put(p.getName(), p.getInventory().getArmorContents());

        p.getInventory().setArmorContents(null);
        p.getInventory().clear();

        locs.put(p.getName(), p.getLocation());
        p.resetMaxHealth();
        p.teleport(arena.getSpawn());
    }

    /**
     * Removes a player from an existing arena
     *
     * @param p A player currently inside an arena
     */
    public void removePlayer(Player p) {
        Arena activeArena = null;
        for (Arena arena : arenas) {
            if (arena.getPlayers().contains(p.getName())) {
                activeArena = arena;
            }
        }
        if (activeArena == null || !activeArena.getPlayers().contains(p.getName())) {
            p.sendMessage("Invalid operation!");
            return;
        }

        // Remove player from arena players and restore inventory
        activeArena.getPlayers().remove(p.getName());

        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        p.getInventory().setContents(inventory.get(p.getName()));
        p.getInventory().setArmorContents(armor.get(p.getName()));

        inventory.remove(p.getName());
        armor.remove(p.getName());
        p.teleport(locs.get(p.getName()));
        locs.remove(p.getName());

        p.setFireTicks(0);
        p.resetMaxHealth();
    }

    /**
     * Creates a new arena
     *
     * @param spawn The arena spawn location
     * @param l1    The top left of the arena bounds
     * @param l2    The bottom right of the arena bounds
     * @return The arena created
     */
    public Arena createArena(Location spawn, Location l1, Location l2) {
        int id = arenaSize + 1;
        arenaSize++;

        Arena arena = new Arena(id, spawn, l1, l2);
        arenas.add(arena);

        // Sets the arena spawns and bounds in a config file
        plugin.getConfig().set("Arenas." + id + ".spawn", serializeLoc(spawn));
        plugin.getConfig().set("Arenas." + id + ".l1", serializeLoc(l1));
        plugin.getConfig().set("Arenas." + id + ".l2", serializeLoc(l2));

        List<Integer> list = plugin.getConfig().getIntegerList("Arenas.Arenas");
        list.add(id);
        plugin.getConfig().set("Arenas.Arenas", list);
        plugin.saveConfig();

        return arena;
    }

    /**
     * Removes an existing arena
     *
     * @param id The id number of an existing arena
     */
    public void removeArena(int id) {
        Arena arena = getArena(id);
        if(arena == null) {
            return;
        }
        arenas.remove(arena);

        plugin.getConfig().set("Arenas." + id, null);

        List<Integer> list = plugin.getConfig().getIntegerList("Arenas.Arenas");
        list.remove(id);
        plugin.getConfig().set("Arenas.Arenas", list);
        plugin.saveConfig();
    }

    /**
     * Checks if a player is currently playing inside an arena
     *
     * @param p A player
     * @return If the player is currently playing inside an arena
     */
    public boolean isInGame(Player p) {
        for (Arena arena : arenas) {
            if (arena.getPlayers().contains(p.getName()))
                return true;
        }
        return false;
    }

    /**
     * Loads an existing arena from arena data
     *
     * @param spawn The existing arena's spawn location
     * @param l1 The existing arena's top left arena bound
     * @param l2 The existing arena's bottom right arena bound
     * @return The existing arena
     */
    public Arena reloadArena(Location spawn, Location l1, Location l2) {
        int id = arenaSize + 1;
        arenaSize++;

        Arena arena = new Arena(id, spawn, l1, l2);
        arenas.add(arena);

        return arena;
    }

    /**
     * Loads existing arenas from a config file
     */
    public void loadGames(){
        arenaSize = 0;

        if(plugin.getConfig().getIntegerList("Arenas.Arenas").isEmpty()){
            return;
        }

        for(int id : plugin.getConfig().getIntegerList("Arenas.Arenas")){
            Location spawn = deserializeLoc(plugin.getConfig().getString("Arenas." + id + ".spawn"));
            Location l1 = deserializeLoc(plugin.getConfig().getString("Arenas." + id + ".l1"));
            Location l2 = deserializeLoc(plugin.getConfig().getString("Arenas." + id + ".l2"));
            Arena arena = reloadArena(spawn, l1, l2);
            arena.id = id;
        }
    }

    /**
     * Converts a location into a string of co-ordinates, separated by commas
     *
     * @param l A location
     * @return A string of co-ordinates separated by commas
     */
    public String serializeLoc(Location l) {
        return l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ();
    }

    /**
     * Converts a string of co-ordinates separated by commas into a location
     *
     * @param string A string of co-ordinates separated by commas
     * @return A location
     */
    public Location deserializeLoc(String string) {
        String[] st = string.split(",");
        return new Location(Bukkit.getWorld(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
    }
}

