package model;

import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DailyDealsDataSource {

    private static DataSource dataSource;

    public static DataSource getInstance() {
        if (null != dataSource) {
            return dataSource;
        } else {
            OracleDataSource oracleDataSource = null;
            try {
                oracleDataSource = new OracleDataSource();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            oracleDataSource.setURL(System.getProperty("db.url"));
            oracleDataSource.setUser(System.getProperty("db.username"));
            oracleDataSource.setPassword(System.getProperty("db.password"));
            dataSource = oracleDataSource;
            return dataSource;
        }
    }
}
