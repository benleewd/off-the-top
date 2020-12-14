package com.github.hbtruong2017.oft.api;

import android.content.Context;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceCallback<T> implements Callback<T> {

    private final Context context;
    private final BiConsumer<Response<T>, T> consumer;

    public ServiceCallback(Context context, BiConsumer<Response<T>, T> consumer) {
        this.context = context;
        this.consumer = consumer;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful() && response.body() != null) {
            consumer.accept(response, response.body());
            return;
        }

        String message = response.message();
        if (StringUtils.isBlank(message)) {
            message = "Error";
        }
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        String message = t.getMessage();
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static <T> ServiceCallback<T> of(Context context, BiConsumer<Response<T>, T> consumer) {
        return new ServiceCallback<>(context, consumer);
    }
}
