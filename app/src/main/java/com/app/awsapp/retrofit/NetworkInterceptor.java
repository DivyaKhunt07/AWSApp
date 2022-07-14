package com.app.awsapp.retrofit;

import android.content.Context;

import androidx.annotation.NonNull;

import com.app.awsapp.R;
import com.app.awsapp.exceptions.NoInternetException;
import com.app.awsapp.managers.NetworkManager;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @Author Divya
 * @Date 09-07-2022
 * <p>
 * Information
 **/
public class NetworkInterceptor implements Interceptor {

    private final Context context;

    public NetworkInterceptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (!NetworkManager.isConnected(context)) {
            throw new NoInternetException(context.getString(R.string.no_internet_connection));
        } else {
            return chain.proceed(chain.request());
        }
    }
}
