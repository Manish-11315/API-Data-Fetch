package com.mysecondapp.basicapifetch.Domain

import com.mysecondapp.basicapifetch.Data.models.ApiRes_Coffee_hotItem
import retrofit2.http.GET

interface ApiEndPoints {

    @GET("hot")
    suspend fun getHOtCoffee() : List<ApiRes_Coffee_hotItem>
}