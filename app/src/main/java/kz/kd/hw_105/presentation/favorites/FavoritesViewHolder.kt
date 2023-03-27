package kz.kd.hw_105.presentation.favorites

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kz.kd.hw_105.R

class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivContent: ImageView = itemView.findViewById(R.id.iv_favorites)

    fun bind(image: Int) {
        ivContent.setImageResource(image)
    }
}