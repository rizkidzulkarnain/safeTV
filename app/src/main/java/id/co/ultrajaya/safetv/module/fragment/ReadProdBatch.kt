package id.co.ultrajaya.safetv.module.fragment

import id.co.ultrajaya.safetv.R

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_read_prodbatch.*
import id.co.ultrajaya.safetv.components.*
import kotlinx.android.synthetic.main.component_header.view.*
import kotlin.collections.ArrayList
import id.co.ultrajaya.safetv.model.classumum.LoadingDialog
import id.co.ultrajaya.safetv.model.classumum.AlertDialogCustom
import id.co.ultrajaya.safetv.model.classutama.ItemMaterial
import id.co.ultrajaya.safetv.module.contract.ReadProdBatchContract
import id.co.ultrajaya.safetv.module.impl.ReadProdBatchImpl
import kotlinx.android.synthetic.main.component_edittext.view.*
import id.co.ultrajaya.safetv.components.LayoutSpinner
import kotlinx.android.synthetic.main.component_button.view.*
import kotlinx.android.synthetic.main.component_edittext_barcode.view.*
import android.widget.EditText



class ReadProdBatch : Fragment(), ReadProdBatchContract, LayoutSpinnerContract, LayoutButtonContract {
    lateinit var mTitle: String
    lateinit var _loadingDialog: LoadingDialog;
    lateinit var _alertDialog: AlertDialogCustom;

    lateinit var _ReadProdBatchImpl: ReadProdBatchImpl


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_read_prodbatch, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        _ReadProdBatchImpl = ReadProdBatchImpl(this, activity!!)

        _loadingDialog = LoadingDialog(activity!!)
        _alertDialog = AlertDialogCustom(activity!!)

        val bundle = this.arguments
        mTitle = bundle?.getString("title")?.toUpperCase() ?: "TITLE"
        header.titleFragment.text = mTitle

        setComponent()
        _ReadProdBatchImpl.getListProdBatch()
    }

    fun setComponent() {
        LayoutSpinner(spPB, "No. PB", this)
        LayoutEditText(context, etWIP, "WIP Produk", true)
        LayoutEditText(context, etProdDesc, "Prod. Desc.", true)
        LayoutEditTextBarcode(context, etBarcode, "Barcode", true)
        LayoutButton(btSave, "SAVE", this, "savepb")
    }

    override fun onChangeItemSpinnerListener(iselectedItem: String) {
        _ReadProdBatchImpl.getWIP(iselectedItem)
    }

    override fun onClickButton(itag : String) {
        when (itag) {
            "savepb" -> {
                var abarcode : String = etBarcode.editTextBarcode.text.toString()
                _ReadProdBatchImpl.saveProdBatch(abarcode)
            }
            else -> showAlertDialog("Tidak tag id button tersebut", 1)
        }
    }

    override fun setSpinner(ilistBatch: ArrayList<String>) {
        LayoutSpinner.setListItemSpinner(activity!!.applicationContext, spPB, ilistBatch)
    }

    override fun showLoading() {
        _loadingDialog.show()
    }

    override fun hideLoading() {
        _loadingDialog.hide()
    }

    override fun showAlertDialog(imsg: String, itipe: Int) {
        _alertDialog.showAlertDialog(imsg, itipe)
    }

    override fun setFormReadProdBatch(iitemMaterial: ItemMaterial) {
        LayoutEditText.setText(etWIP, iitemMaterial.WIP)
        LayoutEditText.setText(etProdDesc, iitemMaterial.ProdDesc)
        LayoutEditTextBarcode.setEnabledBarcode(etBarcode, true)
        LayoutEditTextBarcode.setTextBarcode(etBarcode, "")
        LayoutEditTextBarcode.setFocusableBarcode(etBarcode)
    }
}