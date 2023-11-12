package com.kevin.comparison.vertx;

import com.origin.starter.web.OriginWebApplication;
import com.origin.starter.web.domain.OriginConfig;
import com.origin.starter.web.domain.OriginVertxContext;
import com.origin.starter.web.spi.OriginRouter;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlClient;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RssRouter implements OriginRouter {

    @Override
    public void router(OriginVertxContext originVertxContext, OriginConfig originConfig) {
        Router router = originVertxContext.getRouter();

        router.route(HttpMethod.GET, "/rss").handler(request -> {

            SqlClient client = OriginWebApplication.getBeanFactory().getSqlClient();
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
                        request.response().headers().add("transfer-encoding", "chunked");
                        request.json(rssEntryList);
                    } catch (Exception e) {
                        request.response().setStatusCode(500);
                        request.end(e.getMessage());
                    }

                }
                client.close();
            }).onFailure(err -> {
                request.response().setStatusCode(500);

                request.end();
            });

        });
    }
}
