package id.co.ultrajaya.safetv.components

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import kotlinx.android.synthetic.main.component_spinner.view.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import id.co.ultrajaya.safetv.R
import android.widget.TextView
import android.view.ViewGroup
import id.co.ultrajaya.safetv.model.classumum.Config


class LayoutSpinner {
    constructor(ispinner: View, ilabel: String, ilistener: LayoutSpinnerContract) {
        ispinner.labelSpinner.text = ilabel

        ispinner.spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                if (selectedItem == Config.HEADER_ITEM_SPINNER)
                    (ispinner.spinner.getChildAt(0) as TextView).setTextColor(Color.DKGRAY)
                else
                    ilistener.onChangeItemSpinnerListener(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //do nothing
            }
        }
    }

    companion object {
        fun setListItemSpinner(icontext: Context, ispinner: View, ilistBatch: ArrayList<String>) {
            ilistBatch.add(0, Config.HEADER_ITEM_SPINNER)
            val spinnerArrayAdapter = object : ArrayAdapter<String>(icontext, R.layout.util_spinner_item, ilistBatch) {
                override fun isEnabled(position: Int): Boolean {
                    return position != 0
                }

                override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getDropDownView(position, convertView, parent)
                    val tv = view as TextView
                    if (position == 0) {
                        tv.setTextColor(Color.DKGRAY)
                    } else {
                        tv.setTextColor(ContextCompat.getColor(icontext, R.color.black))
                    }
                    return view
                }
            }

            spinnerArrayAdapter.setDropDownViewResource(R.layout.util_spinner_item)
            ispinner.spinner.adapter = spinnerArrayAdapter
        }
    }


}