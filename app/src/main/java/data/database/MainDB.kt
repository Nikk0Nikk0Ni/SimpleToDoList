package com.niko.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.niko.data.models.ShopItemDBModel

@Database(entities = [ShopItemDBModel::class], version = 1, exportSchema = false)
abstract class MainDB : RoomDatabase() {

    abstract fun getDao() : MainDBDao

    companion object {
        private var INSTANCE: MainDB? = null
        private val Lock = Any()
        private const val DB_NAME = "MAIN_DB.db"

        fun getInstance(application: Application): MainDB {
            INSTANCE?.let {
                return it
            }
            synchronized(Lock) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application.applicationContext,
                    MainDB::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}