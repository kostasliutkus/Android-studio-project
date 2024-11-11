package com.example.lab1.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Type

@Entity(tableName ="users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val mac: String ="",
    val stiprumas: String ="",
    val sensorius: String=""
) : Type
