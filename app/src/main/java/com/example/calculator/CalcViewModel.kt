package com.example.calculator

import android.util.Log
import androidx.lifecycle.ViewModel

class CalcViewModel: ViewModel() {
    var expression: String
    var result: String

    init {
        expression = ""
        result = ""

//        Log.d("test","View Model created")
    }

//    override fun onCleared() {
//        super.onCleared()
//        Log.d("test","view model destroyed")
//    }
}