package com.example.bdd_sql_kotlin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context, "test.db", null, 1){

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT, age INTEGER, email TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun createUser(user: User){
        val values = ContentValues()
        values.put("name", user.name)
        values.put("age", user.age)
        values.put("email", user.email)

        writableDatabase.insert("users", null, values)
    }

    fun getUsersCount(): Int = DatabaseUtils.queryNumEntries( readableDatabase, "users", null).toInt()

    fun getAllUsers(): MutableList<User>{
        val users = mutableListOf<User>()

        readableDatabase.rawQuery("SELECT * FROM users", null).use { cursor ->
            while (cursor.moveToNext()){
                val user = User(
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getInt(cursor.getColumnIndex("age")),
                    cursor.getString(cursor.getColumnIndex("email"))
                )
                users.add(user)
            }
        }

        return users
    }
}