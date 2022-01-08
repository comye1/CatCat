package com.comye1.catcat.catbreed.models

/*
    Breed 이미지 응답 아이템
 */
data class BreedImageResponseItem(
    val breeds: List<BreedItem>,
    val categories: List<Category>?,
    val id: String,
    val url: String
)