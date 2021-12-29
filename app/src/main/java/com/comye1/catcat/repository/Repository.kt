package com.comye1.catcat.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.comye1.catcat.catbreed.models.BreedItem
import com.comye1.catcat.catbreed.network.CatBreedApi
import com.comye1.catcat.catpic.models.CatPic
import com.comye1.catcat.catpic.network.CatPicApi
import com.comye1.catcat.catfact.database.CatFactDao
import com.comye1.catcat.catfact.database.CatFactDatabase
import com.comye1.catcat.catfact.database.CatFactItemLocal
import com.comye1.catcat.catfact.models.CatFactItem
import com.comye1.catcat.catfact.network.CatFactApi
import com.comye1.catcat.utils.getDate

class Repository private constructor(context: Context) {

    companion object {
        private var INSTANCE: Repository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = Repository(context = context)
            }
        }

        fun get(): Repository {
            return INSTANCE ?: throw IllegalStateException("Repository must be initialized")
        }
    }

    private val catFactDao: CatFactDao =
        CatFactDatabase
            .getInstance(context = context)
            .catFactDao

    suspend fun getCatFacts(): List<CatFactItem> =
        CatFactApi.retrofitService.getCatFacts()

    private val catFactSharedPref: SharedPreferences = context.getSharedPreferences(
        "CATFACT",
        Context.MODE_PRIVATE
    )

    // Retrofit -> Database
    suspend fun fetchCatFacts(): List<CatFactItemLocal> {

        return if (catFactSharedPref.getString("FETCHED_DATE", null) == getDate()) { // 캐시가 있는 경우
            Log.d("fetch", "from database")
            catFactDao.getAll()
        } else { // 캐시가 없는 경우
            getCatFacts().forEach {
                catFactDao.insert(CatFactItemLocal(it.id, it.text))
            }
            // 날짜 저장
            with(catFactSharedPref.edit()) {
                putString("FETCHED_DATE", getDate())
                commit()
            }
            Log.d("fetch", "from retrofit")
            catFactDao.getAll()
        }
    }

    suspend fun getCatPic(): CatPic =
        CatPicApi.retrofitService.getCatPic()

    suspend fun getCatBreeds(): List<BreedItem> =
        CatBreedApi.retrofitService.getCatBreeds()
}