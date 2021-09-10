package com.amnah.rdb.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.amnah.rdb.R
import com.amnah.rdb.data.database.NoteDatabase
import com.amnah.rdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        NoteDatabase.getInstance(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        val navigate = findNavController(R.id.fragment_host)

        NavigationUI.setupActionBarWithNavController(this, navigate)
    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.fragment_host).navigateUp()
        return true
    }


}