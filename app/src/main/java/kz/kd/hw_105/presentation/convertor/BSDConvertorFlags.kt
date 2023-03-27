package kz.kd.hw_105.presentation.convertor

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kz.kd.hw_105.R
import kz.kd.hw_105.domain.models.Currency

class BSDConvertorFlags : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bsd_convertor_flags, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newCurrency = Currency(
            amount = "0",
            flag = 0,
            country = "Hello",
            currencyName = "0"
        )

        val ivUSA: ImageView = view.findViewById(R.id.bsd_convertor_ic_usa)
        val ivTR: ImageView = view.findViewById(R.id.bsd_convertor_ic_tr)
        val ivEU: ImageView = view.findViewById(R.id.bsd_convertor_ic_eu)
        val ivRUS: ImageView = view.findViewById(R.id.bsd_convertor_ic_rus)

        val tvUSA = view.findViewById<TextView>(R.id.bsd_convertor_tv_usa)
        tvUSA.setOnClickListener {
            ivUSA.setBackgroundResource(R.drawable.img_flag_bg_selected)
            newCurrency.flag = R.drawable.ic_usa
            newCurrency.amount = "0.0"
            newCurrency.country = resources.getString(R.string.usa)
            newCurrency.currencyName = resources.getString(R.string.usa_currency)
        }
        val tvTR = view.findViewById<TextView>(R.id.bsd_convertor_tv_tr)
        tvTR.setOnClickListener {
            ivTR.setBackgroundResource(R.drawable.img_flag_bg_selected)
            newCurrency.flag = R.drawable.ic_tr
            newCurrency.amount = "0.0"
            newCurrency.country = resources.getString(R.string.tr)
            newCurrency.currencyName = resources.getString(R.string.tr_currency)
        }
        val tvEU = view.findViewById<TextView>(R.id.bsd_convertor_tv_eu)
        tvEU.setOnClickListener {
            ivEU.setBackgroundResource(R.drawable.img_flag_bg_selected)
            newCurrency.flag = R.drawable.ic_eu
            newCurrency.amount = "0.0"
            newCurrency.country = resources.getString(R.string.eu)
            newCurrency.currencyName = resources.getString(R.string.eu_currency)
        }
        val tvRUS = view.findViewById<TextView>(R.id.bsd_convertor_tv_rus)
        tvRUS.setOnClickListener {
            ivRUS.setBackgroundResource(R.drawable.img_flag_bg_selected)
            newCurrency.flag = R.drawable.ic_rus
            newCurrency.amount = "0.0"
            newCurrency.country = resources.getString(R.string.rus)
            newCurrency.currencyName = resources.getString(R.string.rus_currency)
        }
        val btnAdd = view.findViewById<Button>(R.id.bsd_convertor_flags_btn_add)
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