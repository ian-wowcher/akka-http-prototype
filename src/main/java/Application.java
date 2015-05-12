import akka.actor.ActorSystem;
import akka.http.javadsl.server.Directives;
import akka.http.javadsl.server.HttpService;
import akka.http.javadsl.server.Route;

public class Application {

    public static void main(String[] args) {

        final int port = 8181;

        ActorSystem actorSystem = ActorSystem.create();
        /* A route is a marker interface for a an element that handles a request
        /* A route cannot be implemented manually.
        /* Instead, see the predefined routes in Directives
        */

        Route myCustomRoute = Directives.handleWith(new MyFirstHandler());

        Route helloWorldDirective = Directives.complete("hello world from the complete directive");

        HttpService.bindRoute("localhost", port, myCustomRoute, actorSystem);
        System.out.println("Server started on port " + port + "...");
    }
}