package kz.kd.hw_105.presentation.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.kd.hw_105.R

class FavoritesAdapter(
    val layoutInflater: LayoutInflater,
    private val listener: IFAnimationInit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val imageList: List<Int> = listOf(
        R.drawable.ic_fav_one,
        R.drawable.ic_fav_two,
        R.drawable.ic_fav_three,
        R.drawable.ic_fav_one,
        R.drawable.ic_fav_two,
        R.drawable.ic_fav_three,
        R.drawable.ic_fav_one,
        R.drawable.ic_fav_two,
        R.drawable.ic_fav_three
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = layoutInflater.inflate(R.layout.items_favorites, parent, false)
        return FavoritesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FavoritesViewHolder) {
            holder.bind(imageList[position])
            holder.itemView.setOnClickListener {
                listener.animationStart(imageList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}