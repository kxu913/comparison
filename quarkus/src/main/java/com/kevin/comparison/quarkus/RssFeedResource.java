package com.kevin.comparison.quarkus;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/rss")
public class RssFeedResource {
    @Inject
    PgPool client;

    @GET
    public Multi<RssEntry> rssFeed() {
        return client.query("select * from rssentry order by publish_at desc limit 100 ").execute()
                .onItem()
                .transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(RssEntry::from);
    }
}
