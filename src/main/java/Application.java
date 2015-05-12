import akka.actor.ActorSystem;
import akka.http.javadsl.server.Directives;
import akka.http.javadsl.server.HttpService;
import akka.http.javadsl.server.Route;

public class Application {

    public static void main(String[] args) {

        final int port = Integer.valueOf(System.getProperty("port"));

        ActorSystem actorSystem = ActorSystem.create();
        /* A route is a marker interface for a an element that handles a request
        /* A route cannot be implemented manually.
        /* Instead, see the predefined routes in Directives
        */



        Route dispatcherRoute = Directives.handleWith(new DispatcherHandler());
        HttpService.bindRoute("localhost", port, dispatcherRoute, actorSystem);
        System.out.println("Server started on port " + port + "...");
    }
}