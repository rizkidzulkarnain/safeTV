package id.co.ultrajaya.safetv.components

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.KeyEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import id.co.ultrajaya.safetv.R
import kotlinx.android.synthetic.main.component_edittext_barcode.view.*
import android.view.inputmethod.InputMethodManager

class LayoutEditTextBarcode {
    private var mEditTextBarcode : View
    private var mLabelEditTextBarcode: String
    private var mContext : Context
    private var mIsReadOnly : Boolean

    constructor(icontext : Context? , ieditTextBarcode : View, ilabel : String, isReadOnly : Boolean) {
        this.mEditTextBarcode = ieditTextBarcode
        this.mLabelEditTextBarcode = ilabel
        this.mContext = icontext!!
        this.mIsReadOnly = isReadOnly
        setLayout()
    }

    fun setLayout(){
        mEditTextBarcode.labelEditTextBarcode.text = mLabelEditTextBarcode
        mEditTextBarcode.editTextBarcode.background = null

        mEditTextBarcode.editTextBarcode.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                mEditTextBarcode.subParentEditTextBarcode.background = ContextCompat.getDrawable(mContext, R.drawable.shape_edittext_focus);
            }else{
                mEditTextBarcode.subParentEditTextBarcode.background = ContextCompat.getDrawable(mContext, R.drawable.shape_edittext_unfocus);
            }
        }

        mEditTextBarcode.editTextBarcode.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER) {
                var abarcode = mEditTextBarcode.editTextBarcode.text.trim()
                mEditTextBarcode.editTextBarcode.setText(abarcode)
                mEditTextBarcode.editTextBarcode.clearFocus()
                hideSoftKeyboard()
                return@OnKeyListener true
            }
            false
        })
        setEnabledBarcode(mEditTextBarcode, !mIsReadOnly) //karena readonly dan enable bertolak belakang
    }

    fun hideSoftKeyboard() {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm!!.hideSoftInputFromWindow(mEditTextBarcode.editTextBarcode.applicationWindowToken, 0)
    }

    companion object {
        fun setEnabledBarcode(ieditTextBarcode : View, isEnableBarcode : Boolean){
            ieditTextBarcode.editTextBarcode.isEnabled = isEnableBarcode
            if(isEnableBarcode){
                ieditTextBarcode.subParentEditTextBarcode.setBackgroundColor(Color.WHITE)
            }else{
                ieditTextBarcode.subParentEditTextBarcode.setBackgroundColor(Color.LTGRAY)
            }
        }

        fun setTextBarcode(ieditTextBarcode : View, iTextBarcode : String){
            ieditTextBarcode.editTextBarcode.setText(iTextBarcode)
        }

        fun setFocusableBarcode(ieditTextBarcode : View){
            ieditTextBarcode.editTextBarcode.requestFocus()
        }
    }
}