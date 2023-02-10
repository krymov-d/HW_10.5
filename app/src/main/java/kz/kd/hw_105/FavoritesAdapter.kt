package kz.kd.hw_105

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FavoritesAdapter(
    val layoutInflater: LayoutInflater
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
                Toast.makeText(
                    holder.itemView.context,
                    "Hello There! General $position",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}