package com.comye1.catcat.repository

import com.comye1.catcat.models.CatFactItem
import com.comye1.catcat.network.CatFactApi

class Repository {

    suspend fun getCatFacts(): List<CatFactItem>
        = CatFactApi.retrofitService.getCatFacts()

}