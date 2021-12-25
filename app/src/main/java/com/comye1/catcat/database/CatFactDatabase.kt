package com.comye1.catcat.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CatFactItemLocal::class], version = 1, exportSchema = false)
abstract class CatFactDatabase : RoomDatabase() {
    abstract val catFactDao : CatFactDao

    companion object {
        private var INSTANCE: CatFactDatabase? = null

        fun getInstance(context: Context) : CatFactDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CatFactDatabase::class.java,
                        "catfact_table"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}