package id.co.ultrajaya.safetv.components

import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_read_prodbatch.view.*
import kotlinx.android.synthetic.main.component_button.view.*
import kotlinx.android.synthetic.main.component_edittext.view.*

class LayoutButton {
    constructor(ibutton : View, ilabel : String, ilistener : LayoutButtonContract, itag : String) {
        ibutton.button.text = ilabel
        ibutton.button.tag = itag

        ibutton.button.setOnClickListener {
            ilistener.onClickButton(itag)
        }
    }
}