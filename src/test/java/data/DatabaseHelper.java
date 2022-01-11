package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHelper {

    private final static Connection conn = establishConnection();
    private final static QueryRunner runn = new QueryRunner();

    private DatabaseHelper() {
    }

    @SneakyThrows
    private static Connection establishConnection() {
        return DriverManager.getConnection("jdbc:mysql://localhost:9999/app", "app", "pass");
    }

    @SneakyThrows
    public static String getCode() {
    return runn.query(conn, "SELECT code FROM auth_codes", new ScalarHandler<>());
    }

    @SneakyThrows
    public static void clearCodes() {
        runn.execute(conn, "TRUNCATE auth_codes");
    }

    @SneakyThrows
    public static void clearTables() {
        runn.execute(conn, "TRUNCATE auth_codes");
        runn.execute(conn, "TRUNCATE cards;");
        runn.execute(conn, "TRUNCATE card_transactions;");
        runn.execute(conn, "DELETE FROM users WHERE status LIKE '%ive';");
    }
}

