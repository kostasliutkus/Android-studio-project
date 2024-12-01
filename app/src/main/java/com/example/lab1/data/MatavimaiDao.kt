package com.example.lab1.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MatavimaiDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(matavimas: Matavimas)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(matavimai: List<Matavimas>)

    @Query("SELECT * from measurements")
    fun getAllMeasurements(): Flow<List<Matavimas>>

    @Query("SELECT * from measurements WHERE matavimas = :id")
    fun getMeasurements(id: String): Flow<List<Matavimas>>

    @Query("DELETE from measurements")
    suspend fun deleteAllMeasurements()
}