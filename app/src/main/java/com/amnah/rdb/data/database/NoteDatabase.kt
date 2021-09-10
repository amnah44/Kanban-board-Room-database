package com.amnah.rdb.data.database

import android.content.Context
import androidx.room.*
import com.amnah.rdb.data.Note

@Database(entities = [Note::class], version = 1)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NotesDao

    companion object {
        private const val DATABASE_NAME = "MyNotesDatabase"

        @Volatile private var instance: NoteDatabase? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {buildDatabase(context).also { instance = it }}

        fun getInstanceWithoutContext(): NoteDatabase{
             return instance!!
        }

        private fun buildDatabase(context: Context): NoteDatabase {
            return Room.databaseBuilder(context, NoteDatabase::class.java, DATABASE_NAME).build()
        }
    }

}