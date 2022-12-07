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

        val numbers: MutableList<Int> = mutableListOf()
        val code: TextView = findViewById(R.id.code)

        val one1: Button = findViewById(R.id.button_one)
        one1.setOnClickListener {
            removeLast(numbers)
            numbers.add(1)
            code.text = numbers.joinToString(separator = "")
            color(code)
        }
        val two: Button = findViewById(R.id.button_two)
        two.setOnClickListener {
            removeLast(numbers)
            numbers.add(2)
            code.text = numbers.joinToString(separator = "")
            color(code)
        }
        val three: Button = findViewById(R.id.button_three)
        three.setOnClickListener {
            removeLast(numbers)
            numbers.add(3)
            code.text = numbers.joinToString(separator = "")
            color(code)
        }
        val four: Button = findViewById(R.id.button_four)
        four.setOnClickListener {
            removeLast(numbers)
            numbers.add(4)
            code.text = numbers.joinToString(separator = "")
            color(code)
        }
        val five: Button = findViewById(R.id.button_five)
        five.setOnClickListener {
            removeLast(numbers)
            numbers.add(5)
            code.text = numbers.joinToString(separator = "")
            color(code)
        }
        val six: Button = findViewById(R.id.button_six)
        six.setOnClickListener {
            removeLast(numbers)
            numbers.add(6)
            code.text = numbers.joinToString(separator = "")
            color(code)
        }
        val seven: Button = findViewById(R.id.button_seven)
        seven.setOnClickListener {
            removeLast(numbers)
            numbers.add(7)
            code.text = numbers.joinToString(separator = "")
            color(code)
        }
        val eight: Button = findViewById(R.id.button_eight)
        eight.setOnClickListener {
            removeLast(numbers)
            numbers.add(8)
            code.text = numbers.joinToString(separator = "")
            color(code)
        }
        val nine: Button = findViewById(R.id.button_nine)
        nine.setOnClickListener {
            removeLast(numbers)
            numbers.add(9)
            code.text = numbers.joinToString(separator = "")
            color(code)
        }
        val zero: Button = findViewById(R.id.button_zero)
        zero.setOnClickListener {
            removeLast(numbers)
            numbers.add(0)
            code.text = numbers.joinToString(separator = "")
            color(code)
        }
        val del: Button = findViewById(R.id.button_del)
        del.setOnClickListener {
            if (numbers.size != 0) {
                numbers.removeLast()
                code.text = numbers.joinToString(separator = "")
                color(code)
            }
        }

        val ok: Button = findViewById(R.id.button_ok)
        ok.setOnClickListener {
            if (numbers.size == 4 && code.text.equals("1567")) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                code.setTextColor(Color.parseColor("#F91717"))
                Toast.makeText(this, "Wrong PIN!", Toast.LENGTH_SHORT).show()
            }
            numbers.clear()
        }

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