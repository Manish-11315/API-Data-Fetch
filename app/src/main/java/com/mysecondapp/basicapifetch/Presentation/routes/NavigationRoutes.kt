package com.mysecondapp.basicapifetch.Presentation.routes

import kotlinx.serialization.Serializable

@Serializable
object Homescreen

@Serializable
data class Descriptionscreen(
    val description: String?,
    val id: Int?,
    val image: String?,
    val ingredients: List<String>?,
    val title: String?
)