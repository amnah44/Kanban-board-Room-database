package com.amnah.rdb.data.database

import androidx.room.*
import com.amnah.rdb.data.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert
    suspend fun insertNotes(note: Note)

    @Delete
    suspend fun deleteNotes(note: Note)

    @Update
    suspend fun updateNotes(note: Note)

    @Query("SELECT * FROM NOTE_TABLE ORDER BY id DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM NOTE_TABLE WHERE Name LIKE :searchTerms ORDER BY id DESC")
    suspend fun getFilteredNotes(searchTerms: String): List<Note>
}