package com.mvp.movie.api;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hardik on 01/11/17.
 */

public class RestClient {
    public static final String BASE_URL = "http://api.themoviedb.org/";
    private static APIService service;

    public static APIService getRestClient() {

        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getHttpClientInterceptor().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            service = retrofit.create(APIService.class);
        }
        return service;
    }

    @NonNull
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private static OkHttpClient.Builder getHttpClientInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builderHttpClient = new OkHttpClient.Builder();
        builderHttpClient.connectTimeout(5, TimeUnit.MINUTES);
        builderHttpClient.readTimeout(5, TimeUnit.MINUTES);
        builderHttpClient.writeTimeout(5, TimeUnit.MINUTES);
        builderHttpClient.addInterceptor(logging);
        builderHttpClient.retryOnConnectionFailure(true);
        return builderHttpClient;
    }
}