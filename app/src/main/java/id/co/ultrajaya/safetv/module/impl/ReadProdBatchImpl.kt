package id.co.ultrajaya.safetv.module.impl

import android.os.Looper
import android.support.v4.app.FragmentActivity
import id.co.ultrajaya.safetv.model.Database
import id.co.ultrajaya.safetv.model.DatabaseContract
import id.co.ultrajaya.safetv.model.classumum.Config
import id.co.ultrajaya.safetv.model.classutama.ItemMaterial
import id.co.ultrajaya.safetv.model.webservice.GET_SP_MV_DET
import id.co.ultrajaya.safetv.model.webservice.SP_GETPROC_PLSQL_DET
import id.co.ultrajaya.safetv.model.webservice.SP_SAVE_MV
import id.co.ultrajaya.safetv.module.contract.ReadProdBatchContract

class ReadProdBatchImpl : DatabaseContract.OnReadProdBatch {
    private var _readProdBatchView: ReadProdBatchContract
    private var _fragmentActivity: FragmentActivity
    private var _itemMaterial: ItemMaterial ?= null

    constructor(ireadProdBatchView: ReadProdBatchContract, ifragmentActivity: FragmentActivity) {
        this._readProdBatchView = ireadProdBatchView
        this._fragmentActivity = ifragmentActivity
    }

    fun getListProdBatch() {
        _readProdBatchView.showLoading()
        Thread(Runnable {
            Looper.prepare()
            Database.GetProdBatch(this)
            Looper.loop()
        }).start()
    }

    fun getProdDesc() {
        _readProdBatchView.showLoading()
        Thread(Runnable {
            Looper.prepare()
            Database.GetProdDesc(this, _itemMaterial!!.WIP!!)
            Looper.loop()
        }).start()
    }

    fun getWIP(iselectedItem: String) {
        _itemMaterial = ItemMaterial()
        _itemMaterial!!.ProdBatch = iselectedItem
        _readProdBatchView.showLoading()
        Thread(Runnable {
            Looper.prepare()
            Database.GetWIP(this, iselectedItem)
            Looper.loop()
        }).start()
    }

    fun saveProdBatch(ibarcode : String) {
        var abarcode = if (ibarcode == "") null else ibarcode
        _itemMaterial?.Barcode = abarcode
        if (_itemMaterial != null &&
            _itemMaterial?.ProdBatch != null &&
            _itemMaterial?.WIP != null &&
            _itemMaterial?.ProdDesc != null &&
            _itemMaterial?.Barcode != null
        ) {
            _readProdBatchView.showLoading()
            Thread(Runnable {
                Looper.prepare()
                Database.SaveProdBatch(this, _itemMaterial!!)
                Looper.loop()
            }).start()
        } else {
            _readProdBatchView.showAlertDialog("Ada kolom yang kosong !", 1)
        }

    }

    override fun onPostReadProdBatch(aval: GET_SP_MV_DET) {
        var alistbatch: ArrayList<String>

        _fragmentActivity.runOnUiThread {
            if (aval.getIErrMsg() == null) {
                alistbatch = ArrayList<String>()
                if (aval.get_at1().split(Config.AM)[0].get(0) != Config.VN) {
                    val ajml = aval.get_at1().split(Character.toString(Config.Companion.VM)).size
                    for (aidx in 0 until ajml) {
                        var aprodbatch = aval._at1.split(Character.toString(Config.Companion.VM))[aidx]
                        alistbatch.add(aprodbatch)
                    }
                    _readProdBatchView.setSpinner(alistbatch)
                    _readProdBatchView.hideLoading()
                } else {
                    _readProdBatchView.showAlertDialog("Tidak Ada Data", 1)
                    _readProdBatchView.hideLoading()
                }
            } else {
                _readProdBatchView.showAlertDialog(aval.iErrMsg, 1)
                _readProdBatchView.hideLoading()
            }
        }
    }

    override fun onPostReadWIP(aval: GET_SP_MV_DET) {
        _fragmentActivity.runOnUiThread {
            if (aval.getIErrMsg() == null) {
                if (aval.get_at2().split(Config.AM)[0].get(0) != Config.VN) {
                    val ajml = aval.get_at2().split(Character.toString(Config.VM)).size
                    for (aidx in 0 until ajml) {
                        _itemMaterial!!.WIP = aval._at2.split(Character.toString(Config.VM))[aidx]
                    }
                    getProdDesc()
                } else {
                    _readProdBatchView.showAlertDialog("Tidak Ada Data", 1)
                    _readProdBatchView.hideLoading()
                }
            } else {
                _readProdBatchView.showAlertDialog(aval.iErrMsg, 1)
                _readProdBatchView.hideLoading()
            }
        }
    }

    override fun onPostReadProdDesc(aval: SP_GETPROC_PLSQL_DET) {
        _fragmentActivity.runOnUiThread {
            if (aval.get_ErrMsg().equals("anyType{}")) {
                if (aval.get_at2().split(Config.AM)[0].get(0) != Config.VN) {
                    val ajml = aval.get_at2().split(Character.toString(Config.VM)).size
                    for (aidx in 0 until ajml) {
                        _itemMaterial!!.ProdDesc = aval._at2.split(Character.toString(Config.VM))[aidx]
                    }
                    _readProdBatchView.setFormReadProdBatch(_itemMaterial!!)
                    _readProdBatchView.hideLoading()
                } else {
                    _readProdBatchView.showAlertDialog("Tidak Ada Data", 1)
                    _readProdBatchView.hideLoading()
                }
            } else {
                _readProdBatchView.showAlertDialog(aval.iErrMsg, 1)
                _readProdBatchView.hideLoading()
            }
        }
    }

    override fun onPostSaveProdBatch(aval: SP_SAVE_MV) {
        if (aval.iErrMsg.equals("")) {
            _readProdBatchView.showAlertDialog(aval._DispMsg, 2)
        } else {
            _readProdBatchView.showAlertDialog(aval.iErrMsg, 1)
        }
    }
}