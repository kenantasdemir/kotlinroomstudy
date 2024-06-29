package com.kenant42.kotlinroomstudy


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 2)
abstract class CustomDatabaseHelper : RoomDatabase() {
    abstract fun getUsersDAO(): UsersDAOInterface

    companion object {
        var INSTANCE: CustomDatabaseHelper? = null

        fun accessDB(context: Context): CustomDatabaseHelper? {
            if (INSTANCE == null) {
                synchronized(CustomDatabaseHelper::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CustomDatabaseHelper::class.java,
                        "users.sqlite"
                    ).createFromAsset("users.sqlite").build()
                }
            }
            return INSTANCE
        }
    }
}