package com.mysecondapp.basicapifetch.Data.repo

import com.mysecondapp.basicapifetch.Data.API.apibuilder
import com.mysecondapp.basicapifetch.Data.models.ApiRes_Coffee_hotItem // Ensure this import is present
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

data class ApiState (
    val loading : Boolean? = false,
    val coffeeList: List<ApiRes_Coffee_hotItem>? = null,
    val error: String? = null
)

class Repo {
    private val retrofitObj = apibuilder.Retroobject()

    suspend fun getdata() : Flow<ApiState> {
        return flow {
            emit(ApiState(loading = true))
            try{
                val data: List<ApiRes_Coffee_hotItem> = retrofitObj.getHOtCoffee()
                emit(ApiState(coffeeList = data, loading = false))
            }
            catch (e : HttpException){
                emit(ApiState(error = e.message(), loading = false))
            }
            catch (e : Exception){
                emit(ApiState(error = e.message, loading = false))
            }
        }
    }
}