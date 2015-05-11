import akka.http.javadsl.server.Handler;
import akka.http.javadsl.server.RequestContext;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.server.RouteResult;

/**
 * Created by ian.polding on 11/05/2015.
 */
public class MyFirstHandler implements Handler {
    @Override
    public RouteResult handle(RequestContext ctx) {

        ctx.complete("received request for" + ctx.request().getUri().path());

        return null;
    }
}
