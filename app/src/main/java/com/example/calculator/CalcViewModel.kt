package com.example.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder

class CalcViewModel: ViewModel() {
    private val expressionLiveDataObject =  MutableLiveData("")
    val expressionLiveData: LiveData<String>
    get() = expressionLiveDataObject

    private val resultLiveDataObject = MutableLiveData("")
    val resultLiveData: LiveData<String>
    get() = resultLiveDataObject

    fun appendOnClick(clear: Boolean, str: String) {
        if (clear) {
            resultLiveDataObject.value = ""
            expressionLiveDataObject.value = expressionLiveDataObject.value + str
        } else {
            expressionLiveDataObject.value = expressionLiveDataObject.value + str
            resultLiveDataObject.value = ""
        }
        calculate()
    }

    fun clear() {
        resultLiveDataObject.value = ""
        expressionLiveDataObject.value = ""
    }

    private fun calculate() {
        try {
            val input = ExpressionBuilder(expressionLiveDataObject.value).build()
            val output = input.evaluate()
            val longOutput = output.toLong()

            if (output == longOutput.toDouble()) {
                resultLiveDataObject.value = longOutput.toString()
            } else {
                resultLiveDataObject.value = output.toString()
            }
//            updateHistory()
        } catch (e: Exception) {
        }
    }

    fun equal() {
        expressionLiveDataObject.value = resultLiveDataObject.value
        resultLiveDataObject.value = ""
    }

    fun erase() {
        val str = expressionLiveDataObject.value
        expressionLiveDataObject.value = str!!.dropLast(1)
        calculate()
    }
}