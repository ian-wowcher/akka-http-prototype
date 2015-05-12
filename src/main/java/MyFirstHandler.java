import akka.http.javadsl.model.ContentType;
import akka.http.javadsl.model.HttpEntities;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.MediaTypes;
import akka.http.javadsl.model.ResponseEntity;
import akka.http.javadsl.server.Handler;
import akka.http.javadsl.server.RequestContext;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.RouteResult;
import model.Message;

public class MyFirstHandler implements Handler {

    @Override
    public RouteResult handle(RequestContext ctx) {

        HttpResponse response =
                HttpResponse.create()
                .withStatus(StatusCodes.ACCEPTED)
                .withEntity(HttpEntities.create(
                        ContentType.create(MediaTypes.APPLICATION_JSON),
                        "{\"id\": 1, \"message\" : \"hello world\"}")
                );

        return ctx.complete(response);
    }
}
