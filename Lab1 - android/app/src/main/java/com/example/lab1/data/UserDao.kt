package com.example.lab1.data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(user: List<User>)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * from users WHERE mac = :mac")
    fun getUser(mac: String): Flow<List<User>>

    @Query("SELECT * from users ORDER BY mac ASC")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT DISTINCT mac FROM users")
    fun getUniqueUsers(): Flow<List<String>>

    @Query("""
    SELECT mac FROM users
    ORDER BY id DESC
    LIMIT 1 
""")
    suspend fun getLatestUser(): String

    @Query("DELETE from users")
    suspend fun deleteAllUsers()
}