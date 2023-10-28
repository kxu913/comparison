package com.kevin.comparison.springboottomcat.controller;


import com.kevin.comparison.springboottomcat.domain.RssEntry;
import com.kevin.comparison.springboottomcat.mapper.FeedEntryMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController

public class FeedEntryController {
    private final FeedEntryMapper feedEntryMapper;

    public FeedEntryController(FeedEntryMapper feedEntryMapper) {
        this.feedEntryMapper = feedEntryMapper;
    }

    @GetMapping("/rss")
    public List<RssEntry> getRssEntry() {
        return feedEntryMapper.getRssEntry();
    }

}
