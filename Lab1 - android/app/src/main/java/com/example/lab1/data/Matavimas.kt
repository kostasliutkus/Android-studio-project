package com.example.lab1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="measurements")
data class Matavimas(
    @PrimaryKey(autoGenerate = true)
    val Matavimas: Int = 0,
    val y: Int =0,
    val x: Int= 0,
    val atstumas: Double = 0.0
)
