package kz.kd.hw_105.presentation.pincode

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import kz.kd.hw_105.R

class LLCustomPIN @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {
    private var maxDigit: Int = 0
    private var view: View

    init {
        view = inflate(context, R.layout.custom_layout_pin, this)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LLCustomPIN,
            0, 0
        ).apply {
            try {
                maxDigit = this.getInt(R.styleable.LLCustomPIN_pin_length, 0)
            } finally {
                recycle()
            }
        }
    }
}