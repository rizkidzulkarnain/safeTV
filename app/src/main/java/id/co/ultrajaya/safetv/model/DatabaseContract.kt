package id.co.ultrajaya.safetv.model

import id.co.ultrajaya.safetv.model.webservice.GET_SP_MV_DET
import id.co.ultrajaya.safetv.model.webservice.SP_GETPROC_PLSQL_DET
import id.co.ultrajaya.safetv.model.webservice.SP_SAVE_MV

interface DatabaseContract {
    interface OnReadProdBatch{
        fun onPostReadProdBatch(aval : GET_SP_MV_DET)
        fun onPostReadWIP(aval : GET_SP_MV_DET)
        fun onPostReadProdDesc(aval : SP_GETPROC_PLSQL_DET)
        fun onPostSaveProdBatch(aval : SP_SAVE_MV)
    }
}