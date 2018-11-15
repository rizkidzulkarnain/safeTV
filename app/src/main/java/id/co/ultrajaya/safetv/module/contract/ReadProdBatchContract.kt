package id.co.ultrajaya.safetv.module.contract

import id.co.ultrajaya.safetv.model.classutama.ItemMaterial

interface ReadProdBatchContract {
    fun showLoading()
    fun hideLoading()
    fun setSpinner(ilistBatch: ArrayList<String>)
    fun showAlertDialog(imsg: String, itipe: Int)
    fun setFormReadProdBatch(iitemMaterial: ItemMaterial)
}