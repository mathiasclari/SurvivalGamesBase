package net.acticraft.pixelcategorysg.ScoreBoard;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
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
        createBoard(e.getPlayer());
    }

    public void createBoard(Player p) {


        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("LobbyScoreBoard","dummy", ChatColor.GOLD+""+ChatColor.BOLD+ "SurvivalGames");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore("");
        score.setScore(3);
        Score score2 = obj.getScore(ChatColor.YELLOW + "Online Players: "+ ChatColor.WHITE + Bukkit.getOnlinePlayers().size());
        score2.setScore(2);
        Score score3 = obj.getScore(ChatColor.YELLOW + " ");
        score3.setScore(2);
        Score score4 = obj.getScore(ChatColor.YELLOW + "Online Players: "+ ChatColor.WHITE + Bukkit.getOnlinePlayers().size());
        score4.setScore(2);
        Score score5 = obj.getScore(ChatColor.YELLOW + " ");
        score5.setScore(2);
        Score score6 = obj.getScore(ChatColor.GOLD + "play.acticraft.net");
        score6.setScore(1);
        p.setScoreboard(board);

    }
}
