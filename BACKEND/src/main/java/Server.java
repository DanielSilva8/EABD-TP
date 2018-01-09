import db.DB;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by danys on 12-Dec-17.
 */

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
        router.get("/memory").handler(this::getMemory);

        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        config().getInteger("http.port", System.getProperty("S_PORT") == null ? 8083 : Integer.parseInt(System.getProperty("S_PORT"))),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );

        System.out.println("Server running at: http://localhost:" + (System.getProperty("S_PORT") == null ? 8083 : Integer.parseInt(System.getProperty("S_PORT"))));
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
    private void getMemory(RoutingContext routingContext) {
        get(routingContext, db.getMemory());
    }
    private void get(RoutingContext routingContext, String json) {
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .putHeader("Access-Control-Allow-Origin", "*")
                .putHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
                .end(json);
    }
}
