/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.comparison.springboot.mapper;


import com.kevin.comparison.springboot.domain.RssEntry;
import org.apache.ibatis.annotations.*;

import java.util.List;



@Mapper
public interface FeedEntryMapper {
    @Results(id = "RssEntry", value = {

            @Result(property = "feedId", column = "feed_id"),
            @Result(property = "type", column = "type"),
            @Result(property = "title", column = "title"),
            @Result(property = "summary", column = "summary"),
            @Result(property = "url", column = "url"),
            @Result(property = "image", column = "image"),
            @Result(property = "publishAt", column = "publish_at"),
    })
    @Select("select * from rssentry order by publish_at desc limit 100")
    public List<RssEntry> getRssEntry();
}