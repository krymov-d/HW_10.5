package kz.kd.hw_105

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment

class DFConvertorDelete : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dfDeleteView: View = layoutInflater.inflate(R.layout.df_convertor_delete, null)
        val dfDelete: AlertDialog = AlertDialog.Builder(requireContext()).apply {
            setView(dfDeleteView)
        }.create()

        val position = (parentFragment as? IFGetCurrencyPosToDelete)?.getCurrencyPosToDelete()

        with(dfDeleteView) {
            findViewById<Button>(R.id.df_delete_cancel).setOnClickListener {
                dismiss()
            }
            findViewById<Button>(R.id.df_delete_confirm).setOnClickListener {
                if (position != null) {
                    (parentFragment as? IFDeleteCurrency)?.deleteCurrencyAt(position)
                }
                Log.d("DF", "Position to delete = $position")
                dismiss()
            }
        }
        dfDelete.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dfDelete
    }
}