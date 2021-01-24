package com.dirror.music.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = AppDatabase.DATABASE_VERSION, entities = [MyFavoriteData::class])
abstract class AppDatabase: RoomDatabase() {

    abstract fun myFavoriteDao(): MyFavoriteDao

    companion object {

        const val DATABASE_VERSION = 1

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "app_database")
                // .fallbackToDestructiveMigration() // 上线移除
                .build().apply {
                    instance = this
                }
        }
    }

}