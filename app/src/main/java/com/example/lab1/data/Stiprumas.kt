package com.example.lab1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="strengths")
data class Stiprumas(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val sensorius: String ="",
    val stiprumas: Int= 0,
    val matavimas: Int= 0
)
