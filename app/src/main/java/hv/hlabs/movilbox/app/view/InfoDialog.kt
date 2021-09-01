package hv.hlabs.movilbox.app.view

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import hv.hlabs.movilbox.R
import hv.hlabs.movilbox.app.MSG_BORRAR
import hv.hlabs.movilbox.app.MSG_NO_INTERNET
import hv.hlabs.movilbox.databinding.InfoDialogBinding

class InfoDialog (var context: Context, var type:Int, var mListener: DialogListener?) {

    var dialog: Dialog = Dialog(context)

    private var mBinding:InfoDialogBinding = InfoDialogBinding.inflate(dialog.layoutInflater)

    init {

        dialog.setContentView(mBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

        if(type==1){
            mBinding.textViewMsg.text = MSG_BORRAR
            mBinding.lottieAnimated.setAnimation(R.raw.delete)
        }else if(type ==2){
            mBinding.lottieAnimated.setAnimation(R.raw.wifi)
            mBinding.textViewMsg.text = MSG_NO_INTERNET
            mBinding.buttonCancelar.visibility = View.GONE
        }

        else if(type ==3){
            mBinding.lottieAnimated.setAnimation(R.raw.wifi)
            mBinding.textViewMsg.text = MSG_NO_INTERNET
            mBinding.buttonCancelar.visibility = View.GONE
        }

        mBinding.buttonAceptar.setOnClickListener {

            if(mListener!=null){
                mListener?.onClickAceptar()
            }

            dialog.dismiss()
        }

        mBinding.buttonCancelar.setOnClickListener {
            dialog.dismiss()
        }


        dialog.create()
        dialog.show()
    }

}