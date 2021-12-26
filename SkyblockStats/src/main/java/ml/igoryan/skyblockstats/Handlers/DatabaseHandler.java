package ml.igoryan.skyblockstats.Handlers;

import ml.igoryan.skyblockstats.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
    public static void createTable(Connection db, String table) throws SQLException {
        Statement statement = db.createStatement();


        String sql = "create table if not exists SkyblockStatsPlayers\n" +
                "(\n" +
                "    id             integer\n" +
                "        constraint SkyblockStatsPlayers_pk\n" +
                "            primary key autoincrement,\n" +
                "    name           text not null,\n" +
                "    challengesDone integer,\n" +
                "    test           boolean\n" +
                ");\n" +
                "\n" +
                "create unique index SkyblockStatsPlayers_id_uindex\n" +
                "    on SkyblockStatsPlayers (id);\n" +
                "\n" +
                "create unique index SkyblockStatsPlayers_name_uindex\n" +
                "    on SkyblockStatsPlayers (name);\n" +
                "\n";
        System.out.print(sql);
        statement.executeUpdate(sql);
        statement.close();
    }

    public static void getPlayer(Connection db) throws SQLException {

    }

    public void addPlayer(Connection db, String playerName) throws SQLException {
        String sql = "INSERT INTO SkyblockStatsPlayers(name,challengesDone,test) VALUES(?,?,?)";

        try {
            PreparedStatement pstmt = db.prepareStatement(sql);
            pstmt.setString(1, playerName);
            pstmt.setInt(2, 0);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
