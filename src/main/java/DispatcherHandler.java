import akka.http.javadsl.model.ContentType;
import akka.http.javadsl.model.HttpEntities;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.MediaTypes;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.Handler;
import akka.http.javadsl.server.RequestContext;
import akka.http.javadsl.server.RouteResult;

public class DispatcherHandler implements Handler {

    @Override
    public RouteResult handle(RequestContext ctx) {

        if (ctx.request().getUri().path().equals(ApplicationRoutes.HELLO_WORLD.uri())) {
            final HttpResponse response =
                    HttpResponse.create()
                            .withStatus(StatusCodes.OK)
                            .withEntity(HttpEntities.create(
                                            ContentType.create(MediaTypes.APPLICATION_JSON),
                                            "{\"message\" : \"Hello world!!!\"}")
                            );
            return ctx.complete(response);
        } else {
            final HttpResponse notFound =
                    HttpResponse.create()
                            .withStatus(StatusCodes.NOT_FOUND)
                            .withEntity(HttpEntities.create(
                                            ContentType.create(MediaTypes.APPLICATION_JSON),
                                            "{\"id\": 1, \"message\" : \"try a different uri\"}")
                            );
            return ctx.complete(notFound);
        }
    }
}
