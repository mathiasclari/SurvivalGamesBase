package net.acticraft.pixelcategorysg.MySql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

    private static String host, database, username, password;
    private static int port;
    private static Connection connection;

    public static void Login(String host, String database, String username, String password, int port) {
        MySQL.host = host;
        MySQL.database = database;
        MySQL.username = username;
        MySQL.password = password;
        MySQL.port = port;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection GetConnection() {
        if(connection != null) {
            Login(host, database, username, password, port);
        }
        return connection;
    }

    public static void CreateTables() {
        Connection con = GetConnection();
        String query = "CREATE TABLE IF NOT EXISTS `SurvivalGamesStats` (\n" +
                "  `UUID` VARCHAR(36) NOT NULL,\n" +
                "  `KILLS` INT NULL DEFAULT 0,\n" +
                "  `DEATHS` INT NULL DEFAULT 1,\n" +
                "  `COINS` INT NULL DEFAULT 0,\n" +
                "  `WINS` INT NULL DEFAULT 0,\n"+
                "  `WINSTREAK` INT NULL DEFAULT 0,\n" +
                "  `GAMESPLAYED` INT NULL DEFAULT 1,\n"+
                "  PRIMARY KEY (`UUID`));";
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
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
}









