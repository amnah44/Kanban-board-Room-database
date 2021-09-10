package com.amnah.rdb.ui.notes

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.amnah.rdb.R
import com.amnah.rdb.data.Note
import com.amnah.rdb.ui.base.BaseAdapter
import com.amnah.rdb.ui.base.IBaseInterActionListener

class NoteAdapter(item: List<Note>, listener: INoteInterActionClickListener) :
    BaseAdapter<Note>(item, listener) {
    override val layoutId = R.layout.item_note
}

interface INoteInterActionClickListener : IBaseInterActionListener{
    fun onDeleteItems(note: Note)
    fun onMoveItems(note: Note)
}