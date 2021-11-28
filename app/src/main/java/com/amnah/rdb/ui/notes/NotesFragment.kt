package com.amnah.rdb.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.amnah.rdb.R
import com.amnah.rdb.databinding.FragmentNotesBinding

class NotesFragment : Fragment(), View.OnClickListener {
    private lateinit var _binding: FragmentNotesBinding
    private val _viewModel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(layoutInflater)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = this

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.addFab.setOnClickListener(this)

        val noteAdapter = NoteAdapter(mutableListOf(), _viewModel)
        _binding.recyclerView.adapter = noteAdapter

        
    }

    override fun onClick(p0: View?) {
        p0?.let {
            Navigation.findNavController(p0).navigate(R.id.action_notesFragment_to_addTaskFragment)
        }    }

}