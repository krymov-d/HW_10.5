package kz.kd.hw_105.presentation.pincode

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kz.kd.hw_105.R
import kz.kd.hw_105.SecondActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private var tvList: MutableList<TVCustomPIN> = mutableListOf()
    private lateinit var tvCustomOne: TVCustomPIN
    private lateinit var tvCustomTwo: TVCustomPIN
    private lateinit var tvCustomThree: TVCustomPIN
    private lateinit var tvCustomFour: TVCustomPIN
    private var btnList: MutableList<Button> = mutableListOf()
    private lateinit var btnOne: Button
    private lateinit var btnTwo: Button
    private lateinit var btnThree: Button
    private lateinit var btnFour: Button
    private lateinit var btnFive: Button
    private lateinit var btnSix: Button
    private lateinit var btnSeven: Button
    private lateinit var btnEight: Button
    private lateinit var btnNine: Button
    private lateinit var btnZero: Button
    private lateinit var btnDel: Button
    private lateinit var btnOk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initWidgets()
        setBtnClickListeners()

        viewModel.pinCodeLiveData.observe(this) {
            customPINDefault()
            if (it != null) {
                for (i in it.indices) {
                    tvList[i].text = it[i]
                    tvList[i].setBackgroundToActive()
                }
            }
            customPINDeleted()
        }
    }

    private fun initWidgets() {
        tvCustomOne = findViewById(R.id.first_digit)
        tvCustomTwo = findViewById(R.id.second_digit)
        tvCustomThree = findViewById(R.id.third_digit)
        tvCustomFour = findViewById(R.id.forth_digit)
        tvList.addAll(listOf(tvCustomOne, tvCustomTwo, tvCustomThree, tvCustomFour))
        btnOne = findViewById(R.id.btn_one)
        btnTwo = findViewById(R.id.btn_two)
        btnThree = findViewById(R.id.btn_three)
        btnFour = findViewById(R.id.btn_four)
        btnFive = findViewById(R.id.btn_five)
        btnSix = findViewById(R.id.btn_six)
        btnSeven = findViewById(R.id.btn_seven)
        btnEight = findViewById(R.id.btn_eight)
        btnNine = findViewById(R.id.btn_nine)
        btnZero = findViewById(R.id.btn_zero)
        btnDel = findViewById(R.id.btn_del)
        btnOk = findViewById(R.id.btn_ok)
        btnList.addAll(
            listOf(
                btnZero,
                btnOne,
                btnTwo,
                btnThree,
                btnFour,
                btnFive,
                btnSix,
                btnSeven,
                btnEight,
                btnNine
            )
        )
    }

    private fun setBtnClickListeners() {
        for (btn in btnList) {
            val digit = btnList.indexOf(btn)
            btn.setOnClickListener {
                viewModel.addBtnDigitToPinCode(digit)
            }
        }
        btnDel.setOnClickListener {
            viewModel.deleteDigitFromPinCode()
        }
        btnDel.setOnLongClickListener {
            viewModel.deleteAllDigitsFromPinCode()
            customPINDeletedLong()
            true
        }
        btnOk.setOnClickListener {
            val len = viewModel.pinCodeLiveData.value
            if (len == listOf("1", "5", "6", "7")) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                customPINCorrect()
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Wrong PIN!", Toast.LENGTH_SHORT).show()
                customPINWrong()
            }
        }
    }

    private fun customPINDefault() {
        for (i in tvList.indices) {
            tvList[i].text = ""
            tvList[i].setBackgroundToDefault()
        }
    }

    private fun customPINDeleted() {
        for (i in tvList.indices) {
            if (tvList[i].text == "") {
                tvList[i].setBackgroundToDefault()
            }
        }
    }

    private fun customPINDeletedLong() {
        for (i in tvList.indices) {
            tvList[i].setBackgroundToDefault()
        }
    }

    private fun customPINCorrect() {
        for (i in tvList.indices) {
            tvList[i].setBackgroundToCorrect()
        }
    }

    private fun customPINWrong() {
        for (i in tvList.indices) {
            tvList[i].setBackgroundToError()
        }
    }
}