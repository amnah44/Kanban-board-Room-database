package com.amnah.rdb.ui.notes

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.lifecycle.*
import androidx.navigation.Navigation
import com.amnah.rdb.R
import com.amnah.rdb.data.Note
import com.amnah.rdb.data.repository.NotesRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import java.util.*
import java.util.Observer

class NotesViewModel : ViewModel(), INoteInterActionClickListener {

    val newTaskName = MutableLiveData<String>()
    val newTaskDescription = MutableLiveData<String>()
    val newTaskAssignedTo = MutableLiveData<String>()
    val newTaskState = MutableLiveData<String>()
    val searchText = MutableStateFlow("")

    private val repository = NotesRepository()

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

    fun addNote() {
        newTaskState.value = "TO-DO"
        repository.insertNewNote(
            Note(
                0, newTaskName.value,
                newTaskDescription.value,
                newTaskAssignedTo.value,
                newTaskState.value,
                Date(), true
            )
        )
            .subscribeOn(Schedulers.io())
            .subscribe()

        newTaskName.postValue("")
        newTaskDescription.postValue("")
        newTaskAssignedTo.postValue("")

    }

    fun loadData() {
        repository.getAllNotes().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onGetNotes, this::onNotesFailed)
    }

    private fun onGetNotes(listOfNotes: List<Note>) {
        _notes.postValue(listOfNotes)
    }

    private fun onNotesFailed(errorMsg: Throwable) {}

    override fun onDeleteItems(note: Note) {
        repository.deleteNote(note).subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun onMoveItems(note: Note) {
        note.stateOfTask = "In progress"
        repository.updateNote(note).subscribeOn(Schedulers.io())
            .subscribe()

    }
}

