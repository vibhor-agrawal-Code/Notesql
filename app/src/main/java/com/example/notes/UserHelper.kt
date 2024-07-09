package com.example.notes

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserHelper(context: Context) : SQLiteOpenHelper(context, USER_DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val USER_DATABASE_NAME = "users.db"
        private const val DATABASE_VERSION = 2
        private const val USER_TABLE_NAME = "users"
        private const val USER_ID = "id"
        private const val USER_USERNAME = "username"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $USER_TABLE_NAME ($USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, $USER_USERNAME TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Handle upgrades if needed
    }

    fun insertUser(username: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(USER_USERNAME, username)
        }
        db.insert(USER_TABLE_NAME, null, values)
        db.close()
    }
}