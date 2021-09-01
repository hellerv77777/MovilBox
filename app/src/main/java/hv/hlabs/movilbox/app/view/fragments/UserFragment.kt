package hv.hlabs.movilbox.app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import hv.hlabs.movilbox.R
import hv.hlabs.movilbox.app.viewModel.AppViewModel
import hv.hlabs.movilbox.app.NO_INTERNET
import hv.hlabs.movilbox.app.view.DialogListener
import hv.hlabs.movilbox.app.view.InfoDialog
import hv.hlabs.movilbox.databinding.FragmentSecondBinding
import java.lang.StringBuilder

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UserFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var mViewModel: AppViewModel
    private val binding get() = _binding!!
    private var idUser:Int= 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mViewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        val bundle = this.arguments

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        idUser =   bundle!!.getInt("idUser")


        mViewModel.getDataUser(idUser).observe(viewLifecycleOwner,{

            if(it!=null){

                _binding!!.progress.visibility = View.GONE
                _binding!!.textViewName.text = StringBuilder().append("Nombre: ").append(it.name)
                _binding!!.textViewUserName.text = StringBuilder().append("Usuario: ").append(it.username)
                _binding!!.textViewEmail.text = StringBuilder().append("Email: ").append(it.email)
                _binding!!.textViewAddress.text = StringBuilder().append("Dirección: ")
                    .append(it.address.street).append(" - ").append(it.address.suite).append(" - ").append(it.address.city)
                _binding!!.textViewPhone.text = StringBuilder().append("Telefono: ").append(it.phone)
                _binding!!.textViewWebSite.text = StringBuilder().append("Sitio Web: ").append(it.website)

                _binding!!.textViewCompany.text = StringBuilder().append("Compañia: ").append(it.company.name)
                    .append(" - ").append(it.company.catchPhrase)

                _binding!!.imageUser.visibility = View.VISIBLE

            }else{

                InfoDialog(requireContext(), NO_INTERNET, object : DialogListener {
                    override fun onClickAceptar() {
                        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment,bundle)
                    }
                })
            }

        })


        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}