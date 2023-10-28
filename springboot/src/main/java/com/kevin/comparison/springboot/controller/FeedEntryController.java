package com.kevin.comparison.springboot.controller;


import com.kevin.comparison.springboot.domain.RssEntry;
import com.kevin.comparison.springboot.mapper.FeedEntryMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@AllArgsConstructor
public class FeedEntryController {
    private final FeedEntryMapper feedEntryMapper;

    @GetMapping("/rss")
    public List<RssEntry> getRssEntry() {

        return feedEntryMapper.getRssEntry();
    }

}
