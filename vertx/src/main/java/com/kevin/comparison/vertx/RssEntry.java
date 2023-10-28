package com.kevin.comparison.vertx;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RssEntry {

    private String feedId;
    private String type;
    private String summary;
    private String title;
    private String url;
    private String image;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date publishAt;
}