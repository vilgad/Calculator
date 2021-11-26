package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)

        val args = HistoryFragmentArgs.fromBundle(requireArguments())

        binding.myRecyclerView.setHasFixedSize(true)
        val linearLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        binding.myRecyclerView.layoutManager = linearLayoutManager

        binding.myRecyclerView.adapter = HistoryRecyclerViewAdapter(args.expression,args.result,args.counts)
        findNavController().navigate(HistoryFragmentDirections.actionHistoryFragmentToMainFragment())

        (activity as MainActivity).supportActionBar?.title = "History"  // To change the Toolbar title in History fragment

        return binding.root
    }
}