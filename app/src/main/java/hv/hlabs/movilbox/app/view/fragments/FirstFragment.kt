package hv.hlabs.movilbox.app.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import hv.hlabs.movilbox.app.view.adapters.PageControlAdapter
import hv.hlabs.movilbox.databinding.FragmentFirstBinding


class FirstFragment : Fragment(){

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var pageController: PageControlAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)



        pageController = PageControlAdapter(this)
        _binding?.viewPagerPost?.adapter = pageController

        TabLayoutMediator(_binding!!.tabLayout, _binding!!.viewPagerPost) { tab, position ->
            tab.text = getTittle(position)
        }.attach()

        return binding.root
    }

    private fun getTittle(position: Int) :String {
        when(position){
            0-> return "All Posts"
            1-> return "Favorite Post"
        }
        return ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}