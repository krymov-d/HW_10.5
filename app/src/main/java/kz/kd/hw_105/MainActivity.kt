package kz.kd.hw_105

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val KEY = "Pin Code"

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCustomPIN()
        btnClicked()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        visibleCode = tvPINCode.text.toString()
        outState.putString(KEY, visibleCode)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        tvPINCode.text = savedInstanceState.getString(KEY)
    }

    private var visibleCode: String = ""
    private lateinit var tvPINCode: TextView
    private lateinit var tvCustomOne: TVCustomPIN
    private lateinit var tvCustomTwo: TVCustomPIN
    private lateinit var tvCustomThree: TVCustomPIN
    private lateinit var tvCustomFour: TVCustomPIN

    private fun initCustomPIN() {
        tvCustomOne = findViewById(R.id.first_digit)
        tvCustomTwo = findViewById(R.id.second_digit)
        tvCustomThree = findViewById(R.id.third_digit)
        tvCustomFour = findViewById(R.id.forth_digit)
    }

    private fun btnClicked() {
        tvPINCode = findViewById(R.id.tv_pin_code)

        val btnOne: Button = findViewById(R.id.btn_one)
        btnOne.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 1, tvPINCode = tvPINCode)
            customPINAdded(1)
        }

        val btnTwo: Button = findViewById(R.id.btn_two)
        btnTwo.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 2, tvPINCode = tvPINCode)
            customPINAdded(2)
        }

        val btnThree: Button = findViewById(R.id.btn_three)
        btnThree.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 3, tvPINCode = tvPINCode)
            customPINAdded(3)
        }
        val btnFour: Button = findViewById(R.id.btn_four)
        btnFour.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 4, tvPINCode = tvPINCode)
            customPINAdded(4)
        }
        val btnFive: Button = findViewById(R.id.btn_five)
        btnFive.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 5, tvPINCode = tvPINCode)
            customPINAdded(5)
        }
        val btnSix: Button = findViewById(R.id.btn_six)
        btnSix.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 6, tvPINCode = tvPINCode)
            customPINAdded(6)
        }
        val btnSeven: Button = findViewById(R.id.btn_seven)
        btnSeven.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 7, tvPINCode = tvPINCode)
            customPINAdded(7)
        }
        val btnEight: Button = findViewById(R.id.btn_eight)
        btnEight.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 8, tvPINCode = tvPINCode)
            customPINAdded(8)
        }
        val btnNine: Button = findViewById(R.id.btn_nine)
        btnNine.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 9, tvPINCode = tvPINCode)
            customPINAdded(9)
        }
        val btnZero: Button = findViewById(R.id.btn_zero)
        btnZero.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 0, tvPINCode = tvPINCode)
            customPINAdded(0)
        }

        val btnDel: Button = findViewById(R.id.btn_del)
        btnDel.setOnClickListener {
            color(tvPINCode)
            visibleCode = tvPINCode.text.toString()
            val len = visibleCode.length
            if (len == 0) {
                Toast.makeText(this, "Empty PIN", Toast.LENGTH_SHORT).show()
            } else {
                visibleCode = visibleCode.dropLast(1)
                tvPINCode.text = visibleCode
                customPINDeleted()
            }
        }
        btnDel.setOnLongClickListener {
            tvPINCode.text = ""
            customPINDeletedLong()
            true
        }

        val btnOk: Button = findViewById(R.id.btn_ok)
        btnOk.setOnClickListener {
            visibleCode = tvPINCode.text.toString()
            val len = visibleCode.length
            if (len == 0) {
                Toast.makeText(this, "Enter PIN", Toast.LENGTH_SHORT).show()
            } else if (tvPINCode.text.equals("1567")) {
                tvPINCode.setTextColor(Color.parseColor("#17DB5A"))
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                customPINCorrect()
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                tvPINCode.setTextColor(Color.parseColor("#F91717"))
                Toast.makeText(this, "Wrong PIN!", Toast.LENGTH_SHORT).show()
                customPINWrong()
            }
        }
    }

    private fun btnDigitAdd(digit: Int, tvPINCode: TextView): String {
        visibleCode = tvPINCode.text.toString()
        val len = visibleCode.length
        return if (len == 4) {
            visibleCode = visibleCode.dropLast(1)
            visibleCode += "$digit"
            visibleCode
        } else {
            visibleCode + "$digit"
        }
    }

    private fun color(code: TextView) {
        code.setTextColor(Color.parseColor("#1789DB"))
    }

    private fun customPINAdded(digit: Int) {
        if (tvCustomOne.text == "") {
            tvCustomOne.text = digit.toString()
            tvCustomOne.setBackgroundToActive()
        } else if (tvCustomTwo.text == "") {
            tvCustomTwo.text = digit.toString()
            tvCustomTwo.setBackgroundToActive()
        } else if (tvCustomThree.text == "") {
            tvCustomThree.text = digit.toString()
            tvCustomThree.setBackgroundToActive()
        } else if (tvCustomFour.text == "") {
            tvCustomFour.text = digit.toString()
            tvCustomFour.setBackgroundToActive()
        } else {
            tvCustomFour.text = digit.toString()
            tvCustomFour.setBackgroundToActive()
        }
    }

    private fun customPINDeleted() {
        if (tvCustomFour.text != "") {
            tvCustomFour.text = ""
            tvCustomFour.setBackgroundToDefault()
        } else if (tvCustomFour.text == "" && tvCustomThree.text != "") {
            tvCustomThree.text = ""
            tvCustomFour.setBackgroundToDefault()
            tvCustomThree.setBackgroundToDefault()
        } else if (tvCustomThree.text == "" && tvCustomTwo.text != "") {
            tvCustomTwo.text = ""
            tvCustomFour.setBackgroundToDefault()
            tvCustomThree.setBackgroundToDefault()
            tvCustomTwo.setBackgroundToDefault()
        } else if (tvCustomTwo.text == "" && tvCustomOne.text != "") {
            tvCustomOne.text = ""
            tvCustomFour.setBackgroundToDefault()
            tvCustomThree.setBackgroundToDefault()
            tvCustomTwo.setBackgroundToDefault()
            tvCustomOne.setBackgroundToDefault()
        }
    }

    private fun customPINDeletedLong() {
        tvCustomFour.text = ""
        tvCustomFour.setBackgroundToDefault()
        tvCustomThree.text = ""
        tvCustomThree.setBackgroundToDefault()
        tvCustomTwo.text = ""
        tvCustomTwo.setBackgroundToDefault()
        tvCustomOne.text = ""
        tvCustomOne.setBackgroundToDefault()
    }

    private fun customPINCorrect() {
        tvCustomFour.setBackgroundToCorrect()
        tvCustomThree.setBackgroundToCorrect()
        tvCustomTwo.setBackgroundToCorrect()
        tvCustomOne.setBackgroundToCorrect()
    }

    private fun customPINWrong() {
        tvCustomFour.setBackgroundToError()
        tvCustomThree.setBackgroundToError()
        tvCustomTwo.setBackgroundToError()
        tvCustomOne.setBackgroundToError()
    }
}