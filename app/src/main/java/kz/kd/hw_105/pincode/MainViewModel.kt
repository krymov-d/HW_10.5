package kz.kd.hw_105.pincode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val KEY = "Pin Code"

class MainViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {

    private val _pinCodeLiveData: MutableLiveData<List<String>?> = MutableLiveData()
    val pinCodeLiveData: MutableLiveData<List<String>?> = _pinCodeLiveData
    private var list = mutableListOf<String>()

    init {
        val listFromSavedState: List<String>? = stateHandle[KEY]
        if (listFromSavedState != null) {
            _pinCodeLiveData.value = listFromSavedState
        }
    }

    fun addBtnDigitToPinCode(digit: Int) {
        val digitValue = digit.toString()
        if (list.size != 4) {
            list.add(digitValue)
        } else {
            list = list.dropLast(1) as MutableList<String>
            list.add(digitValue)
        }
        _pinCodeLiveData.value = list
        stateHandle[KEY] = list
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