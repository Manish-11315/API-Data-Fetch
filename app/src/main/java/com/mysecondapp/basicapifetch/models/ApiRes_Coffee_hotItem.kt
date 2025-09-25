package com.mysecondapp.basicapifetch.models

data class ApiRes_Coffee_hotItem(
    val description: String,
    val id: Int,
    val image: String,
    val ingredients: List<String>,
    val title: String
)