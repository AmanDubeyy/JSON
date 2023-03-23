package com.example.json

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cities :: class], version = 2)
abstract class CitiesDatabase : RoomDatabase(){
    abstract fun citiesDAO() : CitiesDAO

    companion object {
        @Volatile
        private var INSTANCE: CitiesDatabase? = null

        fun getDatabase(context: Context): CitiesDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CitiesDatabase::class.java, "contactDB"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE!!
        }
    }
}