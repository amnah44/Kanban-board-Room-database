package com.amnah.rdb.data.repository

import com.amnah.rdb.data.Note
import com.amnah.rdb.data.database.NoteDatabase
import io.reactivex.rxjava3.core.Completable

class NotesRepository {

    val dao = NoteDatabase.getInstanceWithoutContext().noteDao()

    fun insertNewNote(note: Note) : Completable{
        return dao.insertNotes(note)
    }
    fun deleteNote(note: Note) : Completable{
        return dao.deleteNotes(note)
    }
    fun updateNote(note: Note): Completable{
        return dao.updateNotes(note)
    }

    fun getAllNotes()= dao.getAllNotes()

    suspend fun getFilteredItems(searchTerm: String) = dao.getFilteredNotes("%$searchTerm%")
}