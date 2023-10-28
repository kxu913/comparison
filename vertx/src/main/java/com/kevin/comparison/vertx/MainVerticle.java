package com.kevin.comparison.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlClient;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) {
        int port = 8081;
        Router router = Router.router(vertx);
        routeGroup(vertx, router);
        VertxOptions options = new VertxOptions();
//        options.setWorkerPoolSize(16);
        Vertx vertx = Vertx.vertx(options);
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(router);
        server.listen(port, http -> {
            if (http.succeeded()) {
                startPromise.complete();
            } else {
                startPromise.fail(http.cause());
            }
        });

    }

    public void routeGroup(Vertx vertx, Router router) {
        router.route(HttpMethod.GET, "/rss").handler(request -> {

            SqlClient client = PgPool.client(vertx, PGUtil.connectOptions, PGUtil.poolOptions);
            client.query(Constants.SQL).execute().onComplete((ar) -> {
                if (ar.succeeded()) {
                    try {
                        RowSet<Row> result = ar.result();
                        List<RssEntry> rssEntryList = new ArrayList<>(result.size());
                        result.forEach(r -> {
                            RssEntry rssEntry = new RssEntry();
                            rssEntry.setFeedId(r.getString("feed_id"));
                            rssEntry.setType(r.getString("type"));
                            rssEntry.setSummary(r.getString("summary"));
                            rssEntry.setTitle(r.getString("title"));
                            rssEntry.setUrl(r.getString("url"));
                            rssEntry.setImage(r.getString("image"));
                            rssEntry.setPublishAt(Date.from(r.getLocalDateTime("publish_at").toInstant(ZoneOffset.UTC)));
                            rssEntryList.add(rssEntry);
                        });
                        request.response().headers().add("transfer-encoding","chunked");
                        request.json(rssEntryList);
                    } catch (Exception e) {
                        request.response().setStatusCode(500);
                        request.end(e.getMessage());
                    }

                }
                client.close();
            }).onFailure(err -> {
                request.response().setStatusCode(500);
                request.end(err.getMessage());
            });

        });
    }
}
