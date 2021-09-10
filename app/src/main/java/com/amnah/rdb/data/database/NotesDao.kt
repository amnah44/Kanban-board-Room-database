package com.amnah.rdb.data.database

import androidx.room.*
import com.amnah.rdb.data.Note
import io.reactivex.internal.operators.completable.CompletableAmb
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface NotesDao {
    @Insert
    fun insertNotes(note: Note): Completable

    @Delete
    fun deleteNotes(note: Note): Completable

    @Update
    fun updateNotes(note: Note):Completable

    @Query("SELECT * FROM NOTE_TABLE ORDER BY id DESC")
    fun getAllNotes(): Observable<List<Note>>

    @Query("SELECT * FROM NOTE_TABLE WHERE Name LIKE :searchTerms ORDER BY id DESC")
    suspend fun getFilteredNotes(searchTerms: String): List<Note>
}