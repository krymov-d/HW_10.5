package kz.kd.hw_105

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

class FragmentChat : Fragment(R.layout.fragment_chat) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_chat_to_favorites).setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentChat_to_fragmentFavorites)
        }
    }
}