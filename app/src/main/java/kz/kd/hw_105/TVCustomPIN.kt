package kz.kd.hw_105

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class TVCustomPIN @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatTextView(context, attrs, defStyleAttr) {
    private var pinDigit = ""
    private var radius = 18f
    private var customStrokeWidth = 3f
    private var colorRed = ColorStateList.valueOf(resources.getColor(R.color.btn_bg_border_pressed))
    private var colorBlue = ColorStateList.valueOf(resources.getColor(R.color.btn_bg_blue))
    private var colorBlueLight =
        ColorStateList.valueOf(resources.getColor(R.color.btn_bg_blue_light))
    private var colorWhite = ColorStateList.valueOf(resources.getColor(R.color.btn_bg_white))
    private var colorGreen =
        ColorStateList.valueOf(resources.getColor(R.color.btn_bg_pressed_gradient_start))
    private lateinit var shapeAppearanceModel: ShapeAppearanceModel
    private lateinit var shapeDrawable: MaterialShapeDrawable

    init {
        setBackground()
        setBackgroundToDefault()
    }

    private fun setBackground() {
        text = pinDigit
        shapeAppearanceModel = ShapeAppearanceModel().toBuilder().apply {
            setAllCorners(CornerFamily.ROUNDED, radius)
        }.build()
    }

    fun setBackgroundToDefault() {
        shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            strokeWidth = customStrokeWidth
            strokeColor = colorBlueLight
            fillColor = colorWhite
        }
        background = shapeDrawable
    }

    fun setBackgroundToActive() {
        shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            strokeWidth = customStrokeWidth
            strokeColor = colorBlue
            fillColor = colorWhite
            setTextColor(colorBlue)
        }
        background = shapeDrawable
    }

    fun setBackgroundToError() {
        shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            strokeWidth = customStrokeWidth
            strokeColor = colorRed
            fillColor = colorWhite
            setTextColor(colorRed)
        }
        background = shapeDrawable
    }

    fun setBackgroundToCorrect() {
        shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            strokeWidth = customStrokeWidth
            strokeColor = colorGreen
            fillColor = colorWhite
            setTextColor(colorGreen)
        }
        background = shapeDrawable
    }
}