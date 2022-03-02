package net.acticraft.pixelcategorysg.ScoreBoard;

import fr.mrmicky.fastboard.FastBoard;
import net.acticraft.pixelcategorysg.PixelCategorySG;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LobbySB implements Listener {


    private final Map<UUID, FastBoard> boards = new HashMap<>();

    public LobbySB() {
        Bukkit.getServer().getScheduler().runTaskTimer(PixelCategorySG.getInstance(), () -> {
            for (FastBoard board : this.boards.values()) {
                updateBoard(board);
            }
        }, 0, 20);
    }

    @EventHandler

    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        FastBoard lobbysb = new FastBoard(player);

        lobbysb.updateTitle(ChatColor.of("#0F7AD9")+""+ChatColor.BOLD + "SurvivalGames");

        this.boards.put(player.getUniqueId(), lobbysb);


    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        FastBoard lobbysb = this.boards.remove(player.getUniqueId());

        if (lobbysb != null) {
            lobbysb.delete();
        }

    }


    private void updateBoard(FastBoard lobbysb) {
        lobbysb.updateLines(
                "",
                ChatColor.of("#738291")+"» "+ChatColor.of("#89B6DE")+"Kills: " + ChatColor.of("#C4CDD6")+lobbysb.getPlayer().getStatistic(Statistic.PLAYER_KILLS),
                "",
                ChatColor.of("#738291")+"» "+ChatColor.of("#89B6DE")+"Wins:" + ChatColor.of("#C4CDD6")+" 10",
                "",
                ChatColor.of("#738291")+"» "+ChatColor.of("#89B6DE")+"Deaths: " + ChatColor.of("#C4CDD6")+lobbysb.getPlayer().getStatistic(Statistic.DEATHS),
                "",
                ChatColor.of("#738291")+"» "+ChatColor.of("#89B6DE")+"Coins:" + ChatColor.of("#C4CDD6")+" 1000",
                "",
                ChatColor.of("#0F7AD9")+""+ChatColor.BOLD + "www.acticraft.net"


        );
    }













    /*
    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        if(e.getPlayer().getWorld().getName().equals("world")) {

           // createBoard(e.getPlayer());
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
    */





}
