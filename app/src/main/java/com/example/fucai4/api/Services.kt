package com.example.fucai4.api

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.http.*

/**
 * Created by Administrator on 2018/3/6.
 */
interface Services {


    @GET("index.php?m=Admin&c=Api&a=checkSecretCode")
    abstract fun querypeople(@Query("mCode") mCode: String, @Query("secretCode") secretCode: String, @Query("version") version: String): Call<ResponseBody>

    /******** Helper class that sets up a new services  */
    object Creator {
        fun newService(): Services {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(logging).build()
            val retrofit = Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build()
            return retrofit.create(Services::class.java)
        }
    }

    companion object {
        val ENDPOINT = "http://www.osanwen.com"
    }
}

