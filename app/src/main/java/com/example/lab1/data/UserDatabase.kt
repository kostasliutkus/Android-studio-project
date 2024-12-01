package com.example.lab1.data

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room

@Database(entities = [User::class,Matavimas::class,Stiprumas::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun stiprumaiDao(): StiprumaiDao
    abstract fun matavimaiDao(): MatavimaiDao
    companion object {
        @Volatile
        private var Instance: UserDatabase? = null
        fun getDatabase(context: Context): UserDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}