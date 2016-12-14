package com.zen.android.zz.sdk.entry;

import com.squareup.moshi.Json;
import com.zen.android.zz.sdk.model.Story;

import java.util.List;

/**
 * @author zen
 * @version 2016/12/7
 */
public class NewsEntry {

    String      date;
    List<Story> stories;
    @Json(name = "top_stories")
    List<Story> topStories;

    public String getDate() {
        return date;
    }

    public List<Story> getStories() {
        return stories;
    }

    public List<Story> getTopStories() {
        return topStories;
    }
}
