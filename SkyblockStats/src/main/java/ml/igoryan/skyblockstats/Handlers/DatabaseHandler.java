package ml.igoryan.skyblockstats.Handlers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
    public static void createTable(Connection db) throws SQLException {
        Statement statement = db.createStatement();

        String sql = "create table if not exists players\n" +
                "(\n" +
                "    id             integer\n" +
                "        constraint players_pk\n" +
                "            primary key autoincrement,\n" +
                "    name           text not null,\n" +
                "    challengesDone integer,\n" +
                "    test           boolean\n" +
                ");\n" +
                "\n" +
                "create unique index players_id_uindex\n" +
                "    on players (id);\n" +
                "\n" +
                "create unique index players_name_uindex\n" +
                "    on players (name);\n" +
                "\n";
        statement.executeUpdate(sql);
        statement.close();
    }
}
