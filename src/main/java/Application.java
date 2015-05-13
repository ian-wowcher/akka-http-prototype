import handler.DispatcherHandler;
import oracle.jdbc.pool.OracleDataSource;
import akka.actor.ActorSystem;
import akka.http.javadsl.server.Directives;
import akka.http.javadsl.server.HttpService;
import akka.http.javadsl.server.Route;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) {

        ActorSystem actorSystem = ActorSystem.create();

        final int port = Integer.valueOf(System.getProperty("port"));

        /* A route is a marker interface for a an element that handles a request
        /* A route cannot be implemented manually.
        /* Instead, see the predefined routes in Directives
        */

        Route dispatcherRoute = Directives.handleWith(new DispatcherHandler());
        HttpService.bindRoute("localhost", port, dispatcherRoute, actorSystem);
        System.out.println("Server started on port " + port + "...");
    }

    public static final DataSource dataSource;

    static {
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
    }
}