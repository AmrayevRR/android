package com.example.todolist.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todolist.model.Category
import com.example.todolist.model.CategoryWithToDos
import com.example.todolist.model.ToDo

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): List<Category>

    @Query("SELECT * FROM category WHERE id=:id")
    fun getCategory(id: String): Category

    @Insert
    fun insert(category: Category): Long
}