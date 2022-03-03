package net.acticraft.pixelcategorysg.Arena;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    public String worldname;
    public Location center;
    public List<Location> spawnlocations=new ArrayList<>();
    public int maxplayers;
    public int minplayers;
    public double range;

    public Arena(FileConfiguration config) {
        worldname = config.getString("location.world");
        minplayers = config.getInt("min-players");
        maxplayers = config.getInt("max-players");
        range = config.getDouble("spawnradius");

    }

    public void setWorld(World world, FileConfiguration config) {
        double y = config.getDouble("location.y");
        center = new Location(world, config.getDouble("location.x"), y,config.getDouble("location.z"));
        for (int i = 0;i<maxplayers;i++){
            double rad = (double) i/maxplayers*Math.PI*2;
            double x = Math.sin(rad)*range;
            double z = Math.cos(rad)*range;
            Location l = new Location(world,x+center.getX(),0+center.getY(),z+center.getZ()).getBlock().getLocation().add(0.5,0,0.5);
            spawnlocations.add(l);
            System.out.println("/tp " + l.getX() + " " + l.getY() + " " + l.getZ());

        }
    }
}
