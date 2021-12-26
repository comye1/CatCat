package com.comye1.catcat.catpic.network

import com.comye1.catcat.catpic.models.CatPic
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


// 1. Create a network layer

// 2. Create a Retrofit object with the base URL and the converter factory
private const val BASE_URL = "https://thatcopy.pw/catapi/" // baseUrl must end in /

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CatPicApiService {

    @GET("rest")
    suspend fun getCatPic(): CatPic
}

// Singleton Pattern ( <- object declaration )
// expose the service to the rest of the app using object declaration.
object CatPicApi {
    val retrofitService : CatPicApiService by lazy {
        retrofit.create(CatPicApiService::class.java)
    }
}