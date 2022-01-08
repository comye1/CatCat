package com.comye1.catcat.catbreed.network

import com.comye1.catcat.BuildConfig
import com.comye1.catcat.catbreed.models.BreedImageResponseItem
import com.comye1.catcat.catbreed.models.BreedItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = "https://api.thecatapi.com/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CatBreedApiService {

    @Headers("x-api-key: ${BuildConfig.THE_CAT_API_KEY}")
    @GET("breeds")
    suspend fun getCatBreeds(): List<BreedItem>

    /*
     id에 해당하는 이미지들 가져오기
    */
    @Headers("x-api-key: ${BuildConfig.THE_CAT_API_KEY}")
    @GET("images/search/")
    suspend fun getCatByBreedId(
        @Query("breed_id") id: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
//        @Query("api_key") key: String = BuildConfig.THE_CAT_API_KEY
    ): List<BreedImageResponseItem>
}

object CatBreedApi {
    val retrofitService: CatBreedApiService by lazy {
        retrofit.create(CatBreedApiService::class.java)
    }
}