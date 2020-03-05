package es.iessaladillo.pedrojoya.quilloque.ui.dial

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DialFragmentViewmodel(private val application: Application) : ViewModel() {

    var currentNumber: MutableLiveData<String> = MutableLiveData("")

    fun setCurrentNumber(number: String) {
        currentNumber.value = currentNumber.value + number
    }

    fun deleteNumber() {
        val number = currentNumber.value
        currentNumber.value = number?.substring(0, currentNumber.value!!.length - 1)
    }

    fun clearFullNumbers() {
        currentNumber.value = ""

    }
}