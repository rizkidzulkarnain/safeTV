package id.co.ultrajaya.safetv.model.classumum

import android.os.Looper
import id.co.ultrajaya.safetv.model.Database

class Config {
    companion object {
        var UrlDefault = "http://b2b.ultrajaya.co.id/ws_middle3/service.asmx"
        var Url = "http://b2b.ultrajaya.co.id/ws_middle3/service.asmx"
        var Url5 = "http://b2b.ultrajaya.co.id/ws_middle5/service.asmx"

        val ServerDB = "1000PRODUKSI"
        var ServerLog = "1000SAFELOG91"
        var ServerOracle = "1000ORC"
        var ServerOracleQA = "QA3300ORC"
        var HEADER_ITEM_SPINNER = "-- Pilih Salah Satu --"


        val AM: Char = 254.toChar()
        val VM: Char = 253.toChar()
        val VN: Char = 250.toChar()

        //hanya default, modifier static tandanya bisa di ubah di tempat manapun
        var VERSION = "1.0"
    }
}