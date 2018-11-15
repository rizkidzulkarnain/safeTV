package id.co.ultrajaya.safetv.module.fragment
import id.co.ultrajaya.safetv.R

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_read_prodbatch.*
import kotlinx.android.synthetic.main.component_header.view.*


class UnderConstructionPage : Fragment() {

    var mTitle : String ?= null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_under_construction_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val bundle = this.arguments
        mTitle = bundle?.getString("title")?.toUpperCase() ?: "TITLE"
        header.titleFragment.text = mTitle

        fillProdBatch();
    }

    fun fillProdBatch(){
        /*val plants = arrayOf("Laceflower", "Sugar maple", "Mountain mahogany", "Butterfly weed")

        val plantsList = ArrayList(Arrays.asList(plants))

        val spinnerArrayAdapter = ArrayAdapter<String>(plantsList)

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item)
        spinnerPB.set = plantsList*/
    }
}