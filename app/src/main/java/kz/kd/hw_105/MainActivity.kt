package kz.kd.hw_105

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonClicked()

    }

    private fun buttonClicked() {

        val numbers: MutableList<Int> = mutableListOf()
        val code: TextView = findViewById(R.id.code)

        val btnOne: Button = findViewById(R.id.btn_one)
        btnOne.setOnClickListener {
            btnWork(numbers, 1, code)
        }
        val btnTwo: Button = findViewById(R.id.btn_two)
        btnTwo.setOnClickListener {
            btnWork(numbers, 2, code)
        }
        val btnThree: Button = findViewById(R.id.btn_three)
        btnThree.setOnClickListener {
            btnWork(numbers, 3, code)
        }
        val btnFour: Button = findViewById(R.id.btn_four)
        btnFour.setOnClickListener {
            btnWork(numbers, 4, code)
        }
        val btnFive: Button = findViewById(R.id.btn_five)
        btnFive.setOnClickListener {
            btnWork(numbers, 5, code)
        }
        val btnSix: Button = findViewById(R.id.btn_six)
        btnSix.setOnClickListener {
            btnWork(numbers, 6, code)
        }
        val btnSeven: Button = findViewById(R.id.btn_seven)
        btnSeven.setOnClickListener {
            btnWork(numbers, 7, code)
        }
        val btnEight: Button = findViewById(R.id.btn_eight)
        btnEight.setOnClickListener {
            btnWork(numbers, 8, code)
        }
        val btnNine: Button = findViewById(R.id.btn_nine)
        btnNine.setOnClickListener {
            btnWork(numbers, 9, code)
        }
        val btnZero: Button = findViewById(R.id.btn_zero)
        btnZero.setOnClickListener {
            btnWork(numbers, 0, code)
        }
        val btnDel: Button = findViewById(R.id.btn_del)
        btnDel.setOnClickListener {
            if (numbers.size != 0) {
                numbers.removeLast()
                code.text = numbers.joinToString(separator = "")
                color(code)
            }
        }

        val btnOk: Button = findViewById(R.id.btn_ok)
        btnOk.setOnClickListener {
            if (numbers.size == 4 && code.text.equals("1567")) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                code.setTextColor(Color.parseColor("#F91717"))
                Toast.makeText(this, "Wrong PIN!", Toast.LENGTH_SHORT).show()
            }
            numbers.clear()
        }
    }

    private fun btnWork(numbers: MutableList<Int>, digit: Int, code: TextView) {
        removeLast(numbers)
        numbers.add(digit)
        code.text = numbers.joinToString(separator = "")
        color(code)
    }

    private fun color(code: TextView) {
        code.setTextColor(Color.parseColor("#1789DB"))
    }

    private fun removeLast(numbers: MutableList<Int>) {
        while (numbers.size > 3) {
            numbers.removeLast()
        }
    }
}