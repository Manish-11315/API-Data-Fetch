package com.mysecondapp.basicapifetch.API

import com.mysecondapp.basicapifetch.models.ApiRes_Coffee_hot

data class ApiState (
    val loading : Boolean = false,
    val is_data_loaded : ApiRes_Coffee_hot? = null,
    val error: String? = null

)
