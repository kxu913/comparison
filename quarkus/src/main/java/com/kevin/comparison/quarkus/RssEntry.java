package com.kevin.comparison.quarkus;



import io.vertx.mutiny.sqlclient.Row;

import java.time.ZoneOffset;
import java.util.Date;


public class RssEntry {
    private String feedId;
    private String type;
    private String summary;
    private String title;
    private String url;
    private String image;
    private Date publishAt;

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(Date publishAt) {
        this.publishAt = publishAt;
    }


    public static RssEntry from(Row r){
        RssEntry rssEntry =  new RssEntry();
        rssEntry.setFeedId(r.getString("feed_id"));
        rssEntry.setType(r.getString("type"));
        rssEntry.setSummary(r.getString("summary"));
        rssEntry.setTitle(r.getString("title"));
        rssEntry.setUrl(r.getString("url"));
        rssEntry.setImage(r.getString("image"));
        rssEntry.setPublishAt(Date.from(r.getLocalDateTime("publish_at").toInstant(ZoneOffset.UTC)));
        return  rssEntry;

    }
}
