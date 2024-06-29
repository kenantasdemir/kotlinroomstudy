package com.kenant42.kotlinroomstudy

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsersDAOInterface {
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Insert
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun removeUser(users: User)

    @Query("SELECT * FROM users ORDER BY RANDOM() LIMIT 1")
    suspend fun getLimitedRandomUsers(): List<User>

    @Query("SELECT * FROM users WHERE user_name  LIKE '%' || :wordToBeSearched || '%'")
    suspend fun getMatchingRecords(wordToBeSearched: String): List<User>


    @Query("SELECT * FROM users WHERE user_id = :user_id")
    suspend fun getUser(user_id: Int): User

    @Query("SELECT COUNT(*) FROM users WHERE user_name = :user_name")
    suspend fun recordCount(user_name: String): Int
}