package com.deepak.assign.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.deepak.assign.R
import com.deepak.assign.databinding.ItemRecyclerViewBinding
import com.deepak.assign.entity.PositionModel
import kotlinx.android.synthetic.main.item_recycler_view.view.*

class JobAdapter(private val list: List<PositionModel>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<JobAdapter.JobViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): JobViewHolder {
        val binding: ItemRecyclerViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(p0.context),
            R.layout.item_recycler_view, p0, false
        )
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, p1: Int) {
        val model = list[p1]
        holder.viewItemViewBinding.response = model
        holder.itemView.item_recycler_view_lin_ly.setOnClickListener {
            listener.onItemClick(model.url)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class JobViewHolder(binding: ItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        var viewItemViewBinding: ItemRecyclerViewBinding = binding
    }

    interface OnItemClickListener {
        fun onItemClick(url: String)
    }
}