package com.zen.android.zz.sdk;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.config.DatabaseHolder;
import com.squareup.moshi.Moshi;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * @author zen
 * @version 2016/12/7
 */
public class ZhiHu {

    private static final ZhiHu sInstance = new ZhiHu();

    private ZhiHuService mZhiHuService;

    private ZhiHu() {
        Moshi moshi = new Moshi.Builder()
                .build();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(genInterceptor())
                .addNetworkInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com")
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        mZhiHuService = retrofit.create(ZhiHuService.class);
    }

    private Interceptor genInterceptor() {
        return chain -> chain.proceed(chain.request());
    }

    public static ZhiHu get() {
        return sInstance;
    }

    public ZhiHuService service() {
        return mZhiHuService;
    }

    @NonNull
    public static Class<? extends DatabaseHolder> getDatabaseHolderClass(){
        return com.raizlabs.android.dbflow.config.zZhiHuGeneratedDatabaseHolder.class;
    }
}
