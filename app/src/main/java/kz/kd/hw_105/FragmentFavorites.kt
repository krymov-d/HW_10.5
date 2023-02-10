package kz.kd.hw_105

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentFavorites : Fragment(), IFAnimationInit {

    private lateinit var ivFavoritesAnim: ImageView
    private lateinit var ivFavoritesAnimClose: ImageView
    private lateinit var mlFavorites: MotionLayout

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
        initFavoritesAnimation(view)
    }

    private fun initFavoritesRV(view: View) {
        val rvFavorites: RecyclerView = view.findViewById(R.id.rv_favorites)
        val favoritesLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val favoritesAdapter = FavoritesAdapter(layoutInflater, this)
        rvFavorites.adapter = favoritesAdapter
        rvFavorites.layoutManager = favoritesLayoutManager
    }

    private fun initFavoritesAnimation(view: View) {
        mlFavorites = view.findViewById(R.id.ml_favorites)
        ivFavoritesAnimClose = view.findViewById(R.id.icon_close)
        mlFavorites.isInteractionEnabled = false
        ivFavoritesAnim = view.findViewById(R.id.iv_favorites_anim)
        ivFavoritesAnimClose.setOnClickListener {
            mlFavorites.isInteractionEnabled = !mlFavorites.isInteractionEnabled
            ivFavoritesAnim.setImageResource(0)
            ivFavoritesAnimClose.setImageResource(0)
        }
    }

    override fun animationStart(image: Int) {
        ivFavoritesAnim.setImageResource(image)
        ivFavoritesAnimClose.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
        mlFavorites.isInteractionEnabled = true
    }
}