package net.acticraft.pixelcategorysg.Arena;

import net.acticraft.pixelcategorysg.MySql.MySQL;
import net.acticraft.pixelcategorysg.PixelCategorySG;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class PlayerData {

    public int startIndex;
    private Long lastDamagedTime = null;
    private UUID damagerUUID = null;
    private UUID uuid;
    private int kills;


    public PlayerData(int startIndex, Player player) {
        this.startIndex = startIndex;



    }

    public void setDamager(Player damager) {
        lastDamagedTime = System.currentTimeMillis();
        damagerUUID = damager.getUniqueId();
    }

    public UUID getLastDamager(boolean ignoreTime) {
        if(ignoreTime || System.currentTimeMillis() - lastDamagedTime < 1000 * 5) {
            return damagerUUID;
        }
        return null;
    }
    public void SaveData(boolean won) {
        String cmd = "";
        int coins = kills * 15;
        if(won) {
            cmd += "INSERT INTO SurvivalGamesStats (UUID, KILLS, DEATHS, WINS, WINSTREAK, COINS) "
                    + "VALUES ('" + this.uuid + "', " + kills + ", 0, 1, 1, " + coins + ") ON DUPLICATE KEY UPDATE GAMESPLAYED = GAMESPLAYED + 1, "
                    + "WINS = WINS + 1, WINSTREAK = WINSTREAK + 1, KILLS = KILLS + " + this.kills + ", COINS = COINS + " + coins + ";";
        } else {
            cmd += "INSERT INTO SurvivalGamesStats (UUID, KILLS, COINS) " + "VALUES ('" + this.uuid + "', " + this.kills + ", " + coins + ") "
                    + "ON DUPLICATE KEY UPDATE GAMESPLAYED = GAMESPLAYED + 1, WINSTREAK = 0, DEATHS = DEATHS + 1, KILLS = KILLS + " + this.kills + ", "
                    + "COINS = COINS + " + coins + ";";
        }
        final String qc = cmd;
        Bukkit.getScheduler().runTaskAsynchronously(PixelCategorySG.getInstance(), new Runnable() {
            @Override
            public void run() {
                Connection con = MySQL.GetConnection();
                Statement stmt = null;
                try {
                    stmt = con.createStatement();
                    stmt.executeUpdate(qc);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if(stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

}
