package com.comye1.catcat.catfact.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CatFactDao {
    @Query("SELECT * FROM catfact_table")
    fun getAll() : List<CatFactItemLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(catFactItemLocal: CatFactItemLocal)

    @Query("DELETE FROM catfact_table")
    suspend fun clear()
}