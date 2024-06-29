package com.kenant42.kotlinroomstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var vt: CustomDatabaseHelper
    private lateinit var usersDAO: UsersDAOInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        vt = CustomDatabaseHelper.accessDB(this)!!
        usersDAO = vt.getUsersDAO()
        getAllUsers()
    }


    fun getAllUsers() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val users = usersDAO.getAllUsers()
            for (user in users) {
                Log.e("USER ID: ", user.user_id.toString())
                Log.e("USER NAME: ", user.user_name)
                Log.e("USER AGE: ", user.user_age.toString())
            }
        }
    }

    fun saveUser() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val newUser = User(0, "Veli", 35)
            usersDAO.addUser(newUser)
        }
    }

    fun update() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val updatedUser = User(3, "Yeni Ahmet", 50)
            usersDAO.updateUser(updatedUser)
        }
    }

    fun removeUser() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val deletedUser = User(2, "Ahmet", 14)
            usersDAO.removeUser(deletedUser)
        }
    }

    fun getLimitedRandomUsers() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val users = usersDAO.getLimitedRandomUsers()

            for (user in users) {
                for (user in users) {
                    Log.e("USER ID: ", user.user_id.toString())
                    Log.e("USER NAME: ", user.user_name)
                    Log.e("USER AGE: ", user.user_age.toString())
                }
            }
        }
    }

    fun search(wordToBeSearched: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val users = usersDAO.getMatchingRecords(wordToBeSearched)
            for (user in users) {
                Log.e("USER ID: ", user.user_id.toString())
                Log.e("USER NAME: ", user.user_name)
                Log.e("USER AGE: ", user.user_age.toString())
            }
        }
    }

    fun getUser(user_id: Int) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val user = usersDAO.getUser(user_id)
            Log.e("USER ID: ", user.user_id.toString())
            Log.e("USER NAME: ", user.user_name)
            Log.e("USER AGE: ", user.user_age.toString())
        }
    }

    fun getMatchingRecordCount(wordToBeSearched: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val recordCount = usersDAO.recordCount(wordToBeSearched)
            Log.e("RECORD COUNT ", recordCount.toString())

        }
    }
}