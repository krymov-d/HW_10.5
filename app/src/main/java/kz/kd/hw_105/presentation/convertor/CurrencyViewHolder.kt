package kz.kd.hw_105.presentation.convertor

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import kz.kd.hw_105.R
import kz.kd.hw_105.domain.models.Currency

class CurrencyViewHolder(itemView: View, private val updateCurrencyList: IFUpdateCurrencyList) :
    RecyclerView.ViewHolder(itemView) {
    private val etAmount: EditText = itemView.findViewById(R.id.et_it_amount)
    private val tlAmount: TextInputLayout = itemView.findViewById(R.id.et_l_amount)
    private val ivFlag: ImageView = itemView.findViewById(R.id.iv_flag)
    private val tvCountry: TextView = itemView.findViewById(R.id.tv_country)

    private var currencyAmount: Double = 0.0
    private lateinit var currencyAmountString: String

    fun bind(currency: Currency) {
        etAmount.setText(currency.amount)
        etAmount.addTextChangedListener {
            currencyAmountString = etAmount.text.toString()
            //App used to crash when field was empty
            currencyAmount = if (currencyAmountString.isBlank()) {
                0.0
            } else {
                currencyAmountString.toDouble()
            }
            updateCurrencyList.updateCurrencyList(currency.country, currencyAmount)
        }
        tlAmount.hint = currency.currencyName
        ivFlag.setImageResource(currency.flag)
        tvCountry.text = currency.country
    }
}