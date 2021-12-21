package com.comye1.catcat.models

import com.squareup.moshi.Json

data class CatFactItem(
    @Json(name = "__v") val v: Int,
    @Json(name = "_id") val id: String,
    val createdAt: String,
    val deleted: Boolean,
    val source: String,
    val status: Status,
    val text: String,
    val type: String,
    val updatedAt: String,
    val used: Boolean,
    val user: String
)