package net.acticraft.pixelcategorysg.GameManager;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.Set;

public class BlockManager {

    private GameManager gameManager;

    public BlockManager(GameManager gameManager){
        this.gameManager = gameManager;
    }

    private Set<Material> allowedToBreak = new HashSet<>();

    private final Set<Block> placedByPlayer = new HashSet<>();

    public void setPlaced(Block block) {
        placedByPlayer.add(block);
    }
    public boolean isPlacedByPlayer(Block block) {
        return placedByPlayer.contains(block);
    }
    public void reset() {
        placedByPlayer.clear();
    }

    public boolean canBreak(Block block){return allowedToBreak.contains(block.getType());}
}
