package com.github.hbtruong2017.oft.api;

import com.github.hbtruong2017.oft.api.model.Appointment;
import com.github.hbtruong2017.oft.api.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AppointmentService {

    @GET("/users/{userId}/appointments")
    Call<List<Appointment>> get(@Path("userId") String userId);

    @POST("/users/{userId}/appointments")
    Call<Appointment> post(@Path("userId") String userId, @Body Appointment appointment);

}