package net.acticraft.pixelcategorysg.ScoreBoard;

import net.acticraft.pixelcategorysg.Stats.PlayerStatsHG;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.awt.*;
import java.util.Arrays;

public class LobbySB implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        if(e.getPlayer().getWorld().getName().equals("world")) {

            createBoard(e.getPlayer());
        }

    }

    public void createBoard(Player p) {


        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("LobbyScoreBoard","dummy", ChatColor.GOLD+""+ChatColor.BOLD+ "SurvivalGames");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score5 = obj.getScore(ChatColor.GOLD +"✦ "+ChatColor.GRAY + "Wins: "+ ChatColor.WHITE +  "1");
        score5.setScore(5);
        Score score6 = obj.getScore(ChatColor.GOLD +"✦ "+ChatColor.GRAY + "Kills: "+ ChatColor.WHITE +  "1");
        score6.setScore(4);
        Score score7 = obj.getScore(ChatColor.GOLD +"✦ "+ChatColor.GRAY + "Deaths: "+ ChatColor.WHITE +  "1");
        score7.setScore(3);
        Score score9 = obj.getScore(ChatColor.GOLD+""+ChatColor.BOLD+ "play.acticraft.net");
        score9.setScore(1);
        p.setScoreboard(board);

    }
}
