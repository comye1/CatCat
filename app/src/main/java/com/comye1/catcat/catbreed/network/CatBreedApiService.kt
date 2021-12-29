package com.comye1.catcat.catbreed.network

import com.comye1.catcat.catbreed.models.BreedItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.thecatapi.com/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CatBreedApiService {

    @GET("breeds")
    suspend fun getCatBreeds() : List<BreedItem>
}

object CatBreedApi {
    val retrofitService : CatBreedApiService by lazy {
        retrofit.create(CatBreedApiService::class.java)
    }
}