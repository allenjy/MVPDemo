package com.allen.mvpdemo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Created by Allen on 2017/3/20.
 */

public class ApiClient {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Session.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    private ApiClient() {}

    public static <T> T create(Class<T> api) {
        return retrofit.create(api);
    }

}
