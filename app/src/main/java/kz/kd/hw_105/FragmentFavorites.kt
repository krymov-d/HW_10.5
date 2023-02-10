package kz.kd.hw_105

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentFavorites : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFavoritesRV(view)
    }

    private fun initFavoritesRV(view: View) {
        val rvFavorites: RecyclerView = view.findViewById(R.id.rv_favorites)
        val favoritesLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val favoritesAdapter = FavoritesAdapter(layoutInflater)
        rvFavorites.adapter = favoritesAdapter
        rvFavorites.layoutManager = favoritesLayoutManager
    }
}