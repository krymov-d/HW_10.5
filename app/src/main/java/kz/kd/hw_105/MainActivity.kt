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


    private var code: String = ""

    private fun buttonClicked() {
        val tvPIN: TextView = findViewById(R.id.code)

        val btnOne: Button = findViewById(R.id.btn_one)
        btnOne.setOnClickListener {
            tvPIN.text = btnWork(digit = 1, tvPIN = tvPIN)
        }

        val btnTwo: Button = findViewById(R.id.btn_two)
        btnTwo.setOnClickListener {
            tvPIN.text = btnWork(digit = 2, tvPIN = tvPIN)
        }

        val btnThree: Button = findViewById(R.id.btn_three)
        btnThree.setOnClickListener {
            tvPIN.text = btnWork(digit = 3, tvPIN = tvPIN)
        }
        val btnFour: Button = findViewById(R.id.btn_four)
        btnFour.setOnClickListener {
            tvPIN.text = btnWork(digit = 4, tvPIN = tvPIN)
        }
        val btnFive: Button = findViewById(R.id.btn_five)
        btnFive.setOnClickListener {
            tvPIN.text = btnWork(digit = 5, tvPIN = tvPIN)
        }
        val btnSix: Button = findViewById(R.id.btn_six)
        btnSix.setOnClickListener {
            tvPIN.text = btnWork(digit = 6, tvPIN = tvPIN)
        }
        val btnSeven: Button = findViewById(R.id.btn_seven)
        btnSeven.setOnClickListener {
            tvPIN.text = btnWork(digit = 7, tvPIN = tvPIN)
        }
        val btnEight: Button = findViewById(R.id.btn_eight)
        btnEight.setOnClickListener {
            tvPIN.text = btnWork(digit = 8, tvPIN = tvPIN)
        }
        val btnNine: Button = findViewById(R.id.btn_nine)
        btnNine.setOnClickListener {
            tvPIN.text = btnWork(digit = 9, tvPIN = tvPIN)
        }
        val btnZero: Button = findViewById(R.id.btn_zero)
        btnZero.setOnClickListener {
            tvPIN.text = btnWork(digit = 0, tvPIN = tvPIN)
        }

        /*
        val btnDel: Button = findViewById(R.id.btn_del)
        btnDel.setOnClickListener {
            if (numbers.size != 0) {
                numbers.removeLast()
                tvPIN.text = numbers.joinToString(separator = "")
                color(tvPIN)
            }
        }

        val btnOk: Button = findViewById(R.id.btn_ok)
        btnOk.setOnClickListener {
            if (numbers.size == 4 && tvPIN.text.equals("1567")) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                tvPIN.setTextColor(Color.parseColor("#F91717"))
                Toast.makeText(this, "Wrong PIN!", Toast.LENGTH_SHORT).show()
            }
            numbers.clear()
        }
        */
    }


    private fun btnWork(digit: Int, tvPIN: TextView): String {
        code = tvPIN.text.toString()
        val len = code.length
        val char = digit.toChar()

        if (len == 4) {
            if (code[3] == char) {
                if (code[2] == char) {
                    if(code[1] == char) {
                        if(code[0] == char) {
                            return code
                        }
                        else {
                            code = ""
                            code = "$digit" + "$digit" + "$digit" + "$digit"
                            return code
                        }
                    }
                    else {
                        code = code.dropLast(3)
                        code = code + "$digit" + "$digit" + "$digit"
                        return code
                    }
                }
                else {
                    code = code.dropLast(2)
                    code = code + "$digit" + "$digit"
                    return code
                }
            }
            else {
                code = code.dropLast(1)
                code += "$digit"
                return code
            }
        }
        else {
            return code + "$digit"
        }
    }

    private fun color(code: TextView) {
        code.setTextColor(Color.parseColor("#1789DB"))
    }
}