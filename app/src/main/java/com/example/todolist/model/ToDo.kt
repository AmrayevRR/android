package com.example.todolist.model

import androidx.room.*
import androidx.room.ForeignKey.NO_ACTION

@Entity
data class ToDo (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val description: String,
    val status: String,
    val category: Int,
    @Embedded val duration: Duration
)