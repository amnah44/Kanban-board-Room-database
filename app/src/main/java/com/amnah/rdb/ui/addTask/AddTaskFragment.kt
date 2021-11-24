package com.amnah.rdb.ui.addTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.amnah.rdb.databinding.FragmentAddTaskBinding

class AddTaskFragment : Fragment(), View.OnClickListener {
    private lateinit var _binding: FragmentAddTaskBinding
    private val _viewModel: NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTaskBinding.inflate(layoutInflater)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = this
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        _binding.addTask.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        _viewModel.addNote()
        p0?.let { Navigation.findNavController(it).popBackStack() }
    }
}