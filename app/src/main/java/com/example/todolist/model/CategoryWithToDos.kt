package com.example.todolist.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class CategoryWithToDos (
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "category"
    )
    val toDos: ArrayList<ToDo>
)