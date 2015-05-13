package handler;

import akka.http.javadsl.model.ContentType;
import akka.http.javadsl.model.HttpEntities;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.MediaTypes;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.Handler;
import akka.http.javadsl.server.RequestContext;
import akka.http.javadsl.server.RouteResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.routing.ApplicationRoutes;
import model.Deal;
import model.DealsListing;
import model.SimpleDeal;

import java.util.List;

public class DispatcherHandler implements Handler {

    @Override
    public RouteResult handle(RequestContext ctx) {

        final String path = ctx.request().getUri().path().substring(1);

        if (path.equals(ApplicationRoutes.HELLO_WORLD.uri())) {
            final HttpResponse response =
                    HttpResponse.create()
                            .withStatus(StatusCodes.OK)
                            .withEntity(HttpEntities.create(
                                            ContentType.create(MediaTypes.APPLICATION_JSON),
                                            "{\"message\" : \"Hello world!!!\"}")
                            );
            return ctx.complete(response);
        } else if (!path.equals("/")) {

            //TODO make this non-blocking by wrapping in a future and getting an actor with a dedicated thread pool to do DB operations
            final DealsListing dealsListing = DealsListing.getByLocation(path);
            final List<Deal> dealList = dealsListing.deals;

            ObjectMapper objectMapper = new ObjectMapper();
            String dealListJsonString = null;
            try {
                dealListJsonString = objectMapper.writer().writeValueAsString(dealList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            final HttpResponse response =
                    HttpResponse.create()
                            .withStatus(StatusCodes.OK)
                            .withEntity(HttpEntities.create(
                                            ContentType.create(MediaTypes.APPLICATION_JSON),
                                            dealListJsonString)
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