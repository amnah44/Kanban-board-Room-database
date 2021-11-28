package com.amnah.rdb.data.repository

import com.amnah.rdb.data.Note
import com.amnah.rdb.data.database.NoteDatabase
import io.reactivex.rxjava3.core.Completable

class NotesRepository {

    private val dao = NoteDatabase.getInstanceWithoutContext().noteDao()

    suspend fun insertNewNote(note: Note){
        return dao.insertNotes(note)
    }
    suspend fun deleteNote(note: Note){
        return dao.deleteNotes(note)
    }
    suspend fun updateNote(note: Note){
        return dao.updateNotes(note)
    }

    fun getAllNotes()= dao.getAllNotes()

    suspend fun getFilteredItems(searchTerm: String) = dao.getFilteredNotes("%$searchTerm%")
}