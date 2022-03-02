package net.acticraft.pixelcategorysg.ArenaAPI;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    int id;
    Location spawn;
    Location l1;
    Location l2;
    List<String> players = new ArrayList<String>();

    public Arena(int id, Location spawn, Location l1, Location l2) {
        this.id = id;
        this.spawn = spawn;
        this.l1 = l1;
        this.l2 = l2;
    }

    public int getId() {
        return this.id;
    }

    public Location getSpawn() {
        return this.spawn;
    }

    public List getPlayers() {
        return this.players;
    }
}
