package com.app.awsapp.retrofit;

import com.app.awsapp.model.LoginData;
import com.app.awsapp.model.RegistrationClassModel;
import com.app.awsapp.model.UserData;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @Authoer Divya
 * @Date 10-07-2022
 * <p>
 * Information
 **/
public interface UserServices {
    @POST("register")
    Call<RegistrationClassModel> signup(@Body UserData userData);

    @POST("login")
    Call<RegistrationClassModel> signIn(@Body LoginData userData);
}
