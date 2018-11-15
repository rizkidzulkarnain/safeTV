package id.co.ultrajaya.safetv.model

import id.co.ultrajaya.safetv.model.classumum.Config
import id.co.ultrajaya.safetv.model.webservice.GET_SP_MV_DET
import id.co.ultrajaya.safetv.model.classumum.mvitem
import id.co.ultrajaya.safetv.model.classumum.nilaihasil
import id.co.ultrajaya.safetv.model.classutama.ItemMaterial
import id.co.ultrajaya.safetv.model.webservice.SP_GETPROC_PLSQL_DET
import id.co.ultrajaya.safetv.model.webservice.SP_SAVE_MV
import id.co.ultrajaya.safetv.model.webservice.SP_SAVE_PLSQL_MV

class Database {
    companion object {
        /*PROGRAM READ PROD BATCH START*/
        val _servReadProdBatchSQL = Config.ServerDB
        val _servReadProdBatchORC = Config.ServerOracle

        fun GetProdBatch(ilistener: DatabaseContract.OnReadProdBatch) {
            val amvitem = mvitem()
            amvitem.SetData(1, "")
            amvitem.SetData(2, "")

            val aval = GET_SP_MV_DET()
            aval.get_sp(_servReadProdBatchSQL, "1", "", amvitem.Contents(), "PP.prodbatch_read", "", "READ_PB")
            ilistener.onPostReadProdBatch(aval)
        }

        fun GetWIP(ilistener: DatabaseContract.OnReadProdBatch, iselectedItem: String) {
            val amvitem = mvitem()
            amvitem.SetData(1, iselectedItem)
            amvitem.SetData(2, "")

            val aval = GET_SP_MV_DET()
            aval.get_sp(_servReadProdBatchSQL, "1", "", amvitem.Contents(), "PP.prodbatch_read", "", "READ_WIP")
            ilistener.onPostReadWIP(aval)
        }

        fun GetProdDesc(ilistener: DatabaseContract.OnReadProdBatch, iwip: String) {
            val aval = SP_GETPROC_PLSQL_DET()
            val amvitem = mvitem()
            amvitem.SetData(1, iwip)
            amvitem.SetData(2, "")

            aval.get_sp(_servReadProdBatchORC, "1", "", amvitem.Contents(), "XITWMS_READ_ITEM_DESC")
            ilistener.onPostReadProdDesc(aval)
        }

        fun SaveProdBatch(ilistener: DatabaseContract.OnReadProdBatch, iitemMaterial: ItemMaterial) {
            val amvitem = mvitem()

            amvitem.SetData(1, iitemMaterial.ProdBatch)
            amvitem.SetData(2, iitemMaterial.WIP)
            amvitem.SetData(3, iitemMaterial.ProdDesc)
            amvitem.SetData(4, iitemMaterial.Barcode)

            val aval = SP_SAVE_MV()
            aval.save_sp(_servReadProdBatchSQL, "1", "", amvitem.Contents(), "", "", "")
            ilistener.onPostSaveProdBatch(aval)
        }
        /*PROGRAM READ PROD BATCH END*/
    }
}