package com.kevin.comparison.springboot.domain;

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
    private Date publishAt;
}
