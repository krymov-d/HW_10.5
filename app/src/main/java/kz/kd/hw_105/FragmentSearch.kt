package kz.kd.hw_105

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

class FragmentSearch : Fragment(R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavigation(view)
    }

    private fun initNavigation(view: View) {
        view.findViewById<Button>(R.id.btn_search_to_account).setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentSearch_to_fragmentAccount)
        }
        view.findViewById<Button>(R.id.btn_search_to_convertor).setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentSearch_to_fragmentConvertor)
        }
    }
}