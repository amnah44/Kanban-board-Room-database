package com.amnah.rdb.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

open interface IBaseInterActionListener

abstract class BaseAdapter<T>(
    private var item: List<T>,
    private val listener: IBaseInterActionListener
) :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {
    abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = item[position]

        when (holder) {
            is ItemViewHolder -> {
                holder.binding.setVariable(BR.item, currentItem)
                holder.binding.setVariable(BR.listener, listener)
            }
        }
    }

    fun setItem(newItems: List<T>) {
        val diffUtil = DiffUtil.calculateDiff(SimpleDiffUtil(item, newItems) { oldItem, newItem ->
            areItemsSame(oldItem, newItem)
        })
        item = newItems
        diffUtil.dispatchUpdatesTo(this)
    }

    fun getItem() = item

    override fun getItemCount() = item.size

    open fun areItemsSame(oldItem: T, newItems: T) = oldItem?.equals(newItems) == true

    class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)

    abstract class BaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}