package com.example.lab1.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StiprumaiDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(stiprumas: Stiprumas)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(stiprumai: List<Stiprumas>)

    @Query("SELECT * from strengths")
    fun getAllStrengths(): Flow<List<Stiprumas>>

    @Query("SELECT * from strengths WHERE id = :id")
    fun getStrength(id: String): Flow<List<Stiprumas>>

    @Query("DELETE from strengths")
    suspend fun deleteAllStrengths()
}