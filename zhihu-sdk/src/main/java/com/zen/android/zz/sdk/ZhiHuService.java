package com.zen.android.zz.sdk;

import com.zen.android.zz.sdk.entry.NewsEntry;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author zen
 * @version 2016/12/7
 */
public interface ZhiHuService {

    @GET("/api/4/news/latest")
    Observable<NewsEntry> getNewsLatest();

}
