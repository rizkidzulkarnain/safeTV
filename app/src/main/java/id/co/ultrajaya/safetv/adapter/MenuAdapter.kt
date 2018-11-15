package id.co.ultrajaya.safetv.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import id.co.ultrajaya.safetv.R
import id.co.ultrajaya.safetv.model.classutama.MenuClass
import kotlinx.android.synthetic.main.item_menu.view.*
import id.co.ultrajaya.safetv.module.fragment.*
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.MotionEvent




public class MenuAdapter(private val imenuList: ArrayList<MenuClass>, private val mContext: Context) :
    RecyclerView.Adapter<MenuAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.myViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        var fragment: Fragment? = null
        val menuClass = imenuList.get(position)
        val id = menuClass.id

        holder.tvTitleMenu.setText(menuClass.title)
        holder.ivMenu.setBackgroundResource(menuClass.iconMenu)
        holder.cvMenu.setOnClickListener {
            val fragmentClass = getMenuFragment(id)
            if (fragmentClass != null) {
                try {
                    fragment = fragmentClass.newInstance() as Fragment
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                val fragmenActivity: FragmentActivity = mContext as FragmentActivity

                //prepare argument
                val arguments = Bundle()
                arguments.putString("title", menuClass.title)
                fragment!!.arguments = arguments

                val fragmentManager = fragmenActivity.supportFragmentManager
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment!!).commit()
            }
        }

        holder.cvMenu.setOnHoverListener(View.OnHoverListener{ _, event ->
            if (event.action == MotionEvent.ACTION_HOVER_ENTER || event.action == MotionEvent.ACTION_HOVER_MOVE) {
                holder.cvMenu.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white_transparent))
                return@OnHoverListener true
            } else {
                holder.cvMenu.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white_none))
            }
            false
        })
    }

    override fun getItemCount() = imenuList.size

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvTitleMenu: TextView = itemView.tvTitleMenu
        internal var ivMenu: ImageView = itemView.ivMenu
        internal var cvMenu: CardView = itemView.cvMenu
    }

    private fun getMenuFragment(id: Int): Class<*>? {
        val afragmentClass: Class<*>
        when (id) {
            1 -> afragmentClass = ReadProdBatch::class.java
            else -> afragmentClass = UnderConstructionPage::class.java
        }
        return afragmentClass
    }
}