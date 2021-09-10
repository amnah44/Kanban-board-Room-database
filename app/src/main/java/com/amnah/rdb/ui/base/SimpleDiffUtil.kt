package com.amnah.rdb.ui.base

import androidx.recyclerview.widget.DiffUtil

class SimpleDiffUtil<T>(
    private val oldItem: List<T>,
    private val newItem: List<T>,
    private val checkIfSameItem: (oldItem: T, newItem: T) -> Boolean
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldItem.size

    override fun getNewListSize() = newItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        checkIfSameItem(oldItem[oldItemPosition], newItem[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
}