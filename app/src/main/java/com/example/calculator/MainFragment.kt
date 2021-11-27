package com.example.calculator

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.calculator.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var expHistory = mutableListOf<String>()
    private var resHistory = mutableListOf<String>()
    private lateinit var binding: FragmentMainBinding
    private lateinit var calcViewModel: CalcViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        // by using this everytime during screen rotation a new view model will be created
        // calcViewModel = CalcViewModel()

        // Better approach
        calcViewModel = ViewModelProvider(this)[CalcViewModel::class.java]

        calcViewModel.expressionLiveData.observe(requireActivity(), {
            binding.tvExpression.text = it
        })
        calcViewModel.resultLiveData.observe(requireActivity(), {
            binding.tvResult.text = "= " + it
        })

        binding.apply {
            btClear.setOnClickListener {
                expHistory.clear()
                resHistory.clear()
                calcViewModel.clear()
            }
            btErase.setOnClickListener { calcViewModel.erase() }

            btDivide.setOnClickListener { calcViewModel.appendOnClick(false, "/") }
            btEquals.setOnClickListener {
                updateHistory()
                calcViewModel.equal()
            }
            btMinus.setOnClickListener { calcViewModel.appendOnClick(false, "-") }
            btMultiply.setOnClickListener { calcViewModel.appendOnClick(false, "*") }
            btPlus.setOnClickListener { calcViewModel.appendOnClick(false, "+") }
            btPercentage.setOnClickListener { calcViewModel.appendOnClick(false, "%") }

            btDecimal.setOnClickListener { calcViewModel.appendOnClick(true, ".") }
            btNine.setOnClickListener { calcViewModel.appendOnClick(true, "9") }
            btEight.setOnClickListener { calcViewModel.appendOnClick(true, "8") }
            btSeven.setOnClickListener { calcViewModel.appendOnClick(true, "7") }
            btSix.setOnClickListener { calcViewModel.appendOnClick(true, "6") }
            btFive.setOnClickListener { calcViewModel.appendOnClick(true, "5") }
            btFour.setOnClickListener { calcViewModel.appendOnClick(true, "4") }
            btThree.setOnClickListener { calcViewModel.appendOnClick(true, "3") }
            btTwo.setOnClickListener { calcViewModel.appendOnClick(true, "2") }
            btOne.setOnClickListener { calcViewModel.appendOnClick(true, "1") }
            btZero.setOnClickListener { calcViewModel.appendOnClick(true, "0") }
            btExpand.setOnClickListener {
                if (binding.cvConstraint.visibility == View.GONE) {
                    TransitionManager.beginDelayedTransition(binding.cvMain, AutoTransition())
                    binding.cvConstraint.visibility = View.VISIBLE
                } else {
                    binding.cvConstraint.visibility = View.GONE
                }
            }

            setHasOptionsMenu(true)     // to enable menu in this fragment

            (activity as MainActivity).supportActionBar?.title = "Calculator"
        }

        return binding.root
    }

    private fun updateHistory() {
        expHistory.add(calcViewModel.expressionLiveData.value.toString())
        resHistory.add(calcViewModel.resultLiveData.value.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Functionality
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToHistoryFragment(
                expHistory.toTypedArray(), resHistory.toTypedArray()
            )
        )
        // Navigate to History Fragment
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }
}