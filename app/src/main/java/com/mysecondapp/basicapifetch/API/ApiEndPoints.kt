package com.mysecondapp.basicapifetch.API

import com.mysecondapp.basicapifetch.models.ApiRes_Coffee_hot
import retrofit2.http.GET

interface ApiEndPoints {

    @GET("hot")
    suspend fun getHOtCoffee() : ApiRes_Coffee_hot

}