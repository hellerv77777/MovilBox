package hv.hlabs.movilbox.app.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hv.hlabs.movilbox.R
import hv.hlabs.movilbox.app.viewModel.AppViewModel
import hv.hlabs.movilbox.app.database.entities.PostEntity
import hv.hlabs.movilbox.app.view.PostListener
import hv.hlabs.movilbox.app.view.adapters.MyPostRecyclerViewAdapter
import hv.hlabs.movilbox.app.view.adapters.SwipeToDeleteCallback
import hv.hlabs.movilbox.databinding.FragmentPostBinding

class PostFragment : Fragment() , PostListener {

    private lateinit var mBinding: FragmentPostBinding
    private var typePost:Int = 0
    private lateinit var mAdapter: MyPostRecyclerViewAdapter
    private lateinit var mLayout: LinearLayoutManager
    private lateinit var mViewModel: AppViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = FragmentPostBinding.inflate(inflater,container,false)
        mViewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        arguments?.takeIf { it.containsKey("type") }?.apply {
            typePost = getInt("type")
        }



        mAdapter = MyPostRecyclerViewAdapter(this)
        mLayout = LinearLayoutManager(context)
        mBinding.recyclerViewPost.apply {
            setHasFixedSize(true)
            layoutManager = mLayout
            adapter = mAdapter
        }

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition

                mViewModel.deletePost( mAdapter.getPost(pos))

                Toast.makeText(requireContext(),"Post Eliminado con exito!",Toast.LENGTH_SHORT).show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(mBinding.recyclerViewPost)

        mViewModel.getPost(typePost)?.observe(viewLifecycleOwner, {

            it?.let {
                mAdapter.updateItems(it)
            }
        })

        return mBinding.root
    }

    override fun onClickPost(postEntity: PostEntity) {

        postEntity.leido = 1
        mViewModel.updatePost(postEntity)
        val bundle = Bundle()
        bundle.putInt("idUser",postEntity.userId)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
    }

    override fun onClickFavorite(postEntity: PostEntity) {

        if(postEntity.favorite==1){
            postEntity.favorite =0
            Toast.makeText(requireContext(),"Post eliminado de favoritos",Toast.LENGTH_SHORT).show()
        }else{
            postEntity.favorite =1
            Toast.makeText(requireContext(),"Post agregado a favoritos",Toast.LENGTH_SHORT).show()
        }
        mViewModel.updatePost(postEntity)
    }

}