package com.example.todolist

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.dao.CategoryDao
import com.example.todolist.dao.ToDoDao
import com.example.todolist.model.Category
import com.example.todolist.model.ToDo

@Database(entities = arrayOf(ToDo::class, Category::class),
    version = 1,
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
    abstract fun categoryDao(): CategoryDao
}