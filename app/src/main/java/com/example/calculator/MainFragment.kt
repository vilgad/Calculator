package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.calculator.databinding.FragmentMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var calcViewModel: CalcViewModel

    private var his: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        // by using this everytime during screen rotation a new view model will be created
        // calcViewModel = CalcViewModel()

        // Better approach
        calcViewModel = ViewModelProvider(this)[CalcViewModel::class.java]

        binding.apply {
            btClear.setOnClickListener { clear() }
            btErase.setOnClickListener { erase() }

            btDivide.setOnClickListener { appendOnClick(false, "/") }
            btEquals.setOnClickListener { equal() }
            btMinus.setOnClickListener { appendOnClick(false, "-") }
            btMultiply.setOnClickListener { appendOnClick(false, "*") }
            btPlus.setOnClickListener { appendOnClick(false, "+") }
            btPercentage.setOnClickListener { appendOnClick(false, "%") }

            btDecimal.setOnClickListener { appendOnClick(true, ".") }
            btNine.setOnClickListener { appendOnClick(true, "9") }
            btEight.setOnClickListener { appendOnClick(true, "8") }
            btSeven.setOnClickListener { appendOnClick(true, "7") }
            btSix.setOnClickListener { appendOnClick(true, "6") }
            btFive.setOnClickListener { appendOnClick(true, "5") }
            btFour.setOnClickListener { appendOnClick(true, "4") }
            btThree.setOnClickListener { appendOnClick(true, "3") }
            btTwo.setOnClickListener { appendOnClick(true, "2") }
            btOne.setOnClickListener { appendOnClick(true, "1") }
            btZero.setOnClickListener { appendOnClick(true, "0") }
        }

        binding.btHistory.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToHistoryFragment(his)
            )
        }
        return binding.root
    }

    private fun appendOnClick(clear: Boolean, string: String) {
            if (clear) {
                calcViewModel.result = ""
                calcViewModel.expression = calcViewModel.expression + string
                updateUI()
            } else {
                //tvExpression.append(tvResult.text)
                calcViewModel.expression = calcViewModel.expression + string
                calcViewModel.result = ""
                updateUI()
            }
        calculate()
    }

    private fun clear() {
        calcViewModel.result = ""
        calcViewModel.expression = ""
        updateUI()
    }

    private fun calculate() {
        try {
            val input = ExpressionBuilder(calcViewModel.expression).build()
            val output = input.evaluate()
            val longOutput = output.toLong()

            if (output == longOutput.toDouble()) {
                calcViewModel.result = longOutput.toString()
            } else {
                calcViewModel.result = output.toString()
            }
            updateHistory()
            updateUI()
        } catch (e: Exception) {
        }
    }

    private fun equal() {
        calcViewModel.expression = calcViewModel.result
        calcViewModel.result = ""
        updateUI()
    }

    private fun erase() {
        val str = calcViewModel.expression
        calcViewModel.expression = str.dropLast(1)
        updateUI()
        calculate()
    }

    private fun updateHistory() {
        var old = his
        his = "$old \n ${binding.tvExpression.text}\n${binding.tvResult.text} "
    }

    private fun updateUI() {
        binding.tvExpression.text = calcViewModel.expression
        binding.tvResult.text = calcViewModel.result
    }
}