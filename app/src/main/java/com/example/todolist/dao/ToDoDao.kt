package com.example.todolist.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todolist.model.Category
import com.example.todolist.model.ToDo

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDo")
    fun getAll(): List<ToDo>

    @Query("SELECT * FROM ToDo WHERE id=:id")
    fun getToDo(id: String): ToDo

    @Insert
    fun insert(toDo: ToDo): Long
}