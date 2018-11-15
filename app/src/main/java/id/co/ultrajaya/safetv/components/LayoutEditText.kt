package id.co.ultrajaya.safetv.components

import android.content.Context
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import kotlinx.android.synthetic.main.component_edittext.view.*
import android.view.View.OnFocusChangeListener
import id.co.ultrajaya.safetv.R
import kotlinx.android.synthetic.main.component_edittext_barcode.view.*


class LayoutEditText {
    private var mEditText : View
    private var mLabelEditText: String
    private var mContext : Context
    private var mIsReadOnly : Boolean

    constructor(icontext : Context? , ieditText : View, ilabel : String, isReadOnly : Boolean) {
        this.mEditText = ieditText
        this.mLabelEditText = ilabel
        this.mContext = icontext!!
        this.mIsReadOnly = isReadOnly
        setLayout()
    }

    fun setLayout(){
        mEditText.labelEditText.text = mLabelEditText
        mEditText.editText.background = null

        mEditText.editText.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                mEditText.subParentEditText.background = ContextCompat.getDrawable(mContext, R.drawable.shape_edittext_focus);
            }else{
                mEditText.subParentEditText.background = ContextCompat.getDrawable(mContext, R.drawable.shape_edittext_unfocus);
            }
        }

        setEnabled(mEditText, !mIsReadOnly) //karena readonly dan enable bertolak belakang
    }

    companion object {
        fun setEnabled(ieditText : View, isEnable : Boolean){
            ieditText.editText.isEnabled = isEnable
            if(isEnable){
                ieditText.subParentEditText.setBackgroundColor(Color.WHITE)
            }else{
                ieditText.subParentEditText.setBackgroundColor(Color.LTGRAY)
            }
        }

        fun setText(ieditText : View, iText : String?){
            ieditText.editText.setText(iText)
        }

        fun setFocusable(ieditText : View){
            ieditText.editText.requestFocus()
        }
    }
}