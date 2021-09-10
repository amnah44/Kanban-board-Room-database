package com.amnah.rdb.utils

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.navigation.Navigation
import androidx.navigation.R
import androidx.recyclerview.widget.RecyclerView
import com.amnah.rdb.ui.base.BaseAdapter

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items:List<T>?){

    if (items != null){
        ( view.adapter as BaseAdapter<T>?)?.setItem(items)
    }else{
        (view.adapter as BaseAdapter<T>?)?.setItem(emptyList())
    }
}