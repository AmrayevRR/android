package com.example.todolist

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class MyApplication: Application() {
    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()

        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "to_do_db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    fun getDatabase(): AppDatabase? {
        return database
    }

    companion object {
        lateinit var instance: MyApplication
    }
}