package com.example.calculator

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.databinding.HistoryViewBinding

class HistoryRecyclerViewAdapter(
    private val expression: String,
    private val result: String,
    private val count: Int
) : RecyclerView.Adapter<HistoryRecyclerViewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding: HistoryViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.history_view, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            binding.tvHistoryExpression.text = expression
            binding.tvHistoryResult.text = result
        }
    }

    override fun getItemCount(): Int {
        return count
    }

    class MyViewHolder(val binding: HistoryViewBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
