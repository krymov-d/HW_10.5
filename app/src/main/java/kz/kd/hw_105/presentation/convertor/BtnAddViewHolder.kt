package kz.kd.hw_105.presentation.convertor

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import kz.kd.hw_105.R

class BtnAddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val btnAdd: Button = itemView.findViewById(R.id.btn_add)
}