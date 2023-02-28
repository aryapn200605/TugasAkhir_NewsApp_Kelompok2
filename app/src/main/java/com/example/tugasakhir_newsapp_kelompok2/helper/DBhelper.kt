package com.example.tugasakhir_newsapp_kelompok2.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class DBhelper (context: Context, factory: CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

        companion object {
            // deklarasikan dbname, dbversion, table, kolom
            private val DATABASE_NAME = "crud_news"
            private val DATABASE_VERSION = 1
            private val TABLE_NAME = "news"
            val ID_COL = "id"
            val TITLE_COL = "title"
            val DESCRIPTION_COL = "description"
            val IMAGE_COL = "image"
        }

        override fun onCreate(db: SQLiteDatabase?) {
            val query = ("CREATE TABLE $TABLE_NAME " +
                    " ( $ID_COL INTEGER PRIMARY KEY, $TITLE_COL TEXT, $DESCRIPTION_COL TEXT, $IMAGE_COL TEXT )")
            db!!.execSQL(query)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
            db!!.execSQL(dropTableQuery)
            onCreate(db)
        }

        fun addProfile(name : String, birth : String) {
            val values = ContentValues()

            values.put(TITLE_COL, title)
            values.put(DESCRIPTION_COL, description)
            values.put(IMAGE_COL, image)

            val db = this.writableDatabase

            db.insert(TABLE_NAME, null, values)
            db.close()
        }

        fun getProfile() : Cursor? {
            val db = this.readableDatabase

            return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        }
}