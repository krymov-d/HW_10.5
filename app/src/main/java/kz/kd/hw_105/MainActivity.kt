package kz.kd.hw_105

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
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

    private fun btnClicked() {
        tvPINCode = findViewById(R.id.tv_pin_code)

        val btnOne: Button = findViewById(R.id.btn_one)
        btnOne.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 1, tvPINCode = tvPINCode)
        }

        val btnTwo: Button = findViewById(R.id.btn_two)
        btnTwo.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 2, tvPINCode = tvPINCode)
        }

        val btnThree: Button = findViewById(R.id.btn_three)
        btnThree.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 3, tvPINCode = tvPINCode)
        }
        val btnFour: Button = findViewById(R.id.btn_four)
        btnFour.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 4, tvPINCode = tvPINCode)
        }
        val btnFive: Button = findViewById(R.id.btn_five)
        btnFive.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 5, tvPINCode = tvPINCode)
        }
        val btnSix: Button = findViewById(R.id.btn_six)
        btnSix.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 6, tvPINCode = tvPINCode)
        }
        val btnSeven: Button = findViewById(R.id.btn_seven)
        btnSeven.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 7, tvPINCode = tvPINCode)
        }
        val btnEight: Button = findViewById(R.id.btn_eight)
        btnEight.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 8, tvPINCode = tvPINCode)
        }
        val btnNine: Button = findViewById(R.id.btn_nine)
        btnNine.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 9, tvPINCode = tvPINCode)
        }
        val btnZero: Button = findViewById(R.id.btn_zero)
        btnZero.setOnClickListener {
            color(tvPINCode)
            tvPINCode.text = btnDigitAdd(digit = 0, tvPINCode = tvPINCode)
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
            }
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
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                tvPINCode.setTextColor(Color.parseColor("#F91717"))
                Toast.makeText(this, "Wrong PIN!", Toast.LENGTH_SHORT).show()
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
}