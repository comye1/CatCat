package com.comye1.catcat.repository

import android.content.Context
import com.comye1.catcat.database.CatFactDao
import com.comye1.catcat.database.CatFactDatabase
import com.comye1.catcat.models.CatFactItem
import com.comye1.catcat.network.CatFactApi

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

}