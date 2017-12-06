import db.DB;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class Server extends AbstractVerticle {
    DB db = new DB();
    @Override
    public void start(Future<Void> fut) {
        Router router = Router.router(vertx);
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response
                    .putHeader("content-type", "text/html")
                    .end("API is Working");
        });
        router.get("/users").handler(this::getUsers);
        router.get("/tablespaces").handler(this::getTablespaces);
        router.get("/datafiles").handler(this::getDatafiles);
        router.get("/stats").handler(this::getStats);
        router.get("/sessions").handler(this::getSessions);
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        config().getInteger("http.port", 8083),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );
    }
    private void getUsers(RoutingContext routingContext) {
        get(routingContext, db.getUsers());
    }
    private void getDatafiles(RoutingContext routingContext) {
        get(routingContext, db.getDatafiles());
    }
    private void getTablespaces(RoutingContext routingContext) {
        get(routingContext,db.getTablespaces());
    }
    private void getStats(RoutingContext routingContext) {
        get(routingContext,db.getStats());
    }
    private void getSessions(RoutingContext routingContext) {
        get(routingContext, db.getSessions());
    }
    private void get(RoutingContext routingContext, String json) {
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(json);
    }
}
