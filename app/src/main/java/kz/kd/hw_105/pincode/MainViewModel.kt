package kz.kd.hw_105.pincode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _pinCodeLiveData: MutableLiveData<List<String>> = MutableLiveData()
    val pinCodeLiveData: LiveData<List<String>> = _pinCodeLiveData

    private var list = mutableListOf<String>()

    fun addBtnDigitToPinCode(digit: Int) {
        val digitValue = digit.toString()
        if (list.size != 4) {
            list.add(digitValue)
        } else {
            list = list.dropLast(1) as MutableList<String>
            list.add(digitValue)
        }
        _pinCodeLiveData.value = list
    }

    fun deleteDigitFromPinCode() {
        if (list.size > 1) {
            list = list.dropLast(1) as MutableList<String>
            _pinCodeLiveData.value = list
        } else if (list.size == 1) {
            list = mutableListOf()
            _pinCodeLiveData.value = list
        }
    }

    fun deleteAllDigitsFromPinCode() {
        list.clear()
        _pinCodeLiveData.value = list
    }
}