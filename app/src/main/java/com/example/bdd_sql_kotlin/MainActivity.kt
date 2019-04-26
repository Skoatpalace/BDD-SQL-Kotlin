package com.example.bdd_sql_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Database(this)

        if (database.getUsersCount() == 0){
            database.createUser(User("Bob", 10, "bob@bob.com"))
            database.createUser(User("Bobette", 5, "bobette@bob.com"))
            database.createUser(User("John", 30, "John@bob.com"))
            database.createUser(User("Jane", 32, "bob@bob.com"))
        }

        val users = database.getAllUsers()
        for (user in users){
            Log.i("MainActivity", "Utilisateurs base de données: $user")
        }
    }
}
