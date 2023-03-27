package kz.kd.hw_105.presentation.convertor

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import kz.kd.hw_105.R
import kz.kd.hw_105.domain.models.Currency

class BSDConvertor : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bsd_convertor, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newCurrency = Currency(
            amount = "0",
            flag = 0,
            country = "Hello",
            currencyName = "0"
        )

        val newCurrencyCountry =
            view.findViewById<TextInputEditText>(R.id.bsd_convertor_et_currency_country)
        val newCurrencyName =
            view.findViewById<TextInputEditText>(R.id.bsd_convertor_et_currency_name)
        val newCurrencyAmount =
            view.findViewById<TextInputEditText>(R.id.bsd_convertor_et_currency_amount)
        val btnFlag = view.findViewById<Button>(R.id.bsd_convertor_btn_flag)
        val btnAdd = view.findViewById<Button>(R.id.bsd_convertor_btn_add)

        btnFlag.setOnClickListener {
            newCurrency.country = newCurrencyCountry.text.toString()
            newCurrency.currencyName = newCurrencyName.text.toString()
            newCurrency.amount = newCurrencyAmount.text.toString()
            when (newCurrency.country) {
                getString(R.string.kz) -> {
                    newCurrency.flag = R.drawable.ic_kz
                }
                getString(R.string.usa) -> {
                    newCurrency.flag = R.drawable.ic_usa
                }
                getString(R.string.tr) -> {
                    newCurrency.flag = R.drawable.ic_tr
                }
                getString(R.string.eu) -> {
                    newCurrency.flag = R.drawable.ic_eu
                }
                getString(R.string.rus) -> {
                    newCurrency.flag = R.drawable.ic_rus
                }
                else -> Toast.makeText(
                    context,
                    "The country name is not recognized",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnAdd.setOnClickListener {
            (parentFragment as? IFAddCurrency)?.addCurrency(newCurrency)
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { dialogInterface ->
            val dialogView = dialogInterface as BottomSheetDialog
            val bottomSheet =
                dialogView.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as? FrameLayout?
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(bottomSheet)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED //Раскрыт
                behavior.isDraggable = true //Можно двигать вверх и вниз
                isCancelable = true //Можно закрывать кнопкой back
            }
        }
        return dialog
    }
}