package hv.hlabs.movilbox.app.view.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import hv.hlabs.movilbox.app.view.fragments.PostFragment

class PageControlAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private  var ARG_OBJECT = "type"
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        val fragment: Fragment = PostFragment()
        fragment.arguments = Bundle().apply { putInt(ARG_OBJECT, position) }
        return fragment
    }
}
