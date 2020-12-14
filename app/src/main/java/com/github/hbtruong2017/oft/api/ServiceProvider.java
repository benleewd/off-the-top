package com.github.hbtruong2017.oft.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ServiceProvider {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.cs461.fuxing.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <T> T getService(Class<T> clazz) {
        return retrofit.create(clazz);
    }
}
