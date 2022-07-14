package com.app.awsapp.retrofit;

import android.content.Context;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Authoer Divya
 * @Date 09-07-2022
 * <p>
 * Information
 **/
public class RetrofitClient {

    public static Retrofit getRetrofit(Context context) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new NetworkInterceptor(context))
                .addInterceptor(interceptor)
                .build();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl("https://0jp6moiq74.execute-api.eu-west-2.amazonaws.com/dev/auth/")
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create());

//      OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(new NetworkInterceptor(context));
        return builder.build();
    }
}
