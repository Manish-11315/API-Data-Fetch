package com.mysecondapp.basicapifetch.API

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object apibuilder {

    fun Retroobject() : ApiEndPoints {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .build()
            )
            .baseUrl("https://api.sampleapis.com/coffee/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create<ApiEndPoints>()
    }
}