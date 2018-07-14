package com.example.fucai4.api;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by ${小强同学} on 2017/9/11
 */

public class AppClient {

    public static Retrofit retrofit = null;

    public static String API_SERVER_URL = "http://120.25.165.175/";

    /**
     * int t  1是xml解析，2是json
     */
    public static Retrofit retrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())//支持Gson
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();


        /**
         * Log信息拦截器，代码略  BuildConfig.DEBUG
         */

        if (true) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("Log信息", message.toString());
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }
        /**
         * 设置超时和重连，代码略
         */
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        //以上设置结束，才能build(),不然设置白搭
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }


    public static String Json(String json) {
        JSONObject object = null;
        String puIcon = null;
        try {
            object = new JSONObject(json.toString());
            puIcon = object.optString("puIcon");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return puIcon;
    }


}
