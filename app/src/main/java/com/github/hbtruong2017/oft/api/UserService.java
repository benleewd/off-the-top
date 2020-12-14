package com.github.hbtruong2017.oft.api;

import com.github.hbtruong2017.oft.api.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @GET("/users/{userId}")
    Call<User> get(@Path("userId") String userId);

    @POST("/users/auth")
    Call<User> auth(@Body User user);

    @POST("/users")
    Call<User> post(@Body User user);

    @PATCH("/users/{userId}")
    Call<User> patch(@Path("userId") String userId, @Body User user);

    @POST("/users/{userId}/picture")
    Call<User> postPicture(@Path("userId") String userId, @Body User user);
}