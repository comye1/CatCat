package com.comye1.catcat.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catfact_table")
data class CatFactItemLocal (
    @PrimaryKey val id: String,
    val text: String
)