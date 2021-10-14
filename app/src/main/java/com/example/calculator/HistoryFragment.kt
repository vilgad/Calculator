package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.calculator.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)

        val args = HistoryFragmentArgs.fromBundle(requireArguments())
        binding.historyFragTvHistory.text = "${args.history}"

        binding.historyFragBtClose.setOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_mainFragment)
        }

        return binding.root
    }
}