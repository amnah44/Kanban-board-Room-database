package com.amnah.rdb.ui.addTask

import androidx.lifecycle.*
import com.amnah.rdb.data.Note
import com.amnah.rdb.data.repository.NotesRepository
import com.amnah.rdb.ui.notes.INoteInterActionClickListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import java.util.*

class AddTaskViewModel : ViewModel() {
    private val repository = NotesRepository()
    val newTaskName = MutableLiveData<String>()
    val newTaskDescription = MutableLiveData<String>()

    fun addNote() {
        viewModelScope.launch {
            repository.insertNewNote(
                Note(
                    0, newTaskName.value,
                    newTaskDescription.value,
                    Date(), true
                )
            )
        }
        newTaskName.postValue("")
        newTaskDescription.postValue("")

    }

}

