package com.amnah.rdb.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amnah.rdb.data.Note
import com.amnah.rdb.data.repository.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class NotesViewModel: ViewModel(), INoteInterActionClickListener{
    private val repository = NotesRepository()
    val searchText = MutableStateFlow("")

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    init {
        loadData()

        viewModelScope.launch {
            searchText.debounce(1000).collect {
                val filteringResult = repository.getFilteredItems(it)
                _notes.postValue(filteringResult)
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            repository.getAllNotes().collect {
                _notes.postValue(it)
            }
        }
    }

    override fun onDeleteItems(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    override fun onMoveItems(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

}