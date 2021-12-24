package com.comye1.catcat.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catfact_table")
data class CatFactItemLocal (
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey val id: String,
    val text: String
)