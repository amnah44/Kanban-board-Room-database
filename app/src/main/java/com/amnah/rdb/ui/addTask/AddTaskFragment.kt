package com.amnah.rdb.ui.addTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.R
import com.amnah.rdb.databinding.FragmentAddTaskBinding
import com.amnah.rdb.ui.notes.NotesViewModel

class AddTaskFragment : Fragment(){

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


        _binding.addTask.setOnClickListener {
            findNavController(requireView()).popBackStack()
            }

//        val itemsOfState = listOf("TO-DO", "In Progress", "Done")
//        val spinnerStateAdapter =
//            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemsOfState)
//
//        _binding.spinnerState.apply {
//            adapter = spinnerStateAdapter
//            onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//
//                    viewm
//                }
//
//                override fun onNothingSelected(p0: AdapterView<*>?) {
//                }
//
//            }
//        }
    }
}