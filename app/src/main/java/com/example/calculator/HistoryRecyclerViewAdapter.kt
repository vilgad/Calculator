package com.example.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.databinding.HistoryViewBinding

class HistoryRecyclerViewAdapter(
    private val expression: Array<String>,
    private val result: Array<String>
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
            binding.tvHistoryExpression.text = expression[position]
            binding.tvHistoryResult.text = "= " + result[position]
        }
    }

    override fun getItemCount(): Int {
        return expression.size
    }

    class MyViewHolder(val binding: HistoryViewBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
