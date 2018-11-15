package id.co.ultrajaya.safetv

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import id.co.ultrajaya.safetv.adapter.MenuAdapter
import id.co.ultrajaya.safetv.model.classutama.MenuClass
import id.co.ultrajaya.safetv.module.fragment.WelcomePage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var _menuList: ArrayList<MenuClass>? = null
    private var _adapter: MenuAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val versionName = packageManager.getPackageInfo(packageName, 0).versionName
        tvVersi.text =  "Versi : " + versionName

        logo.setOnClickListener {
            logoClick()
        }

        _menuList = ArrayList<MenuClass>()
        _adapter = MenuAdapter(_menuList!!, this)

        val alayoutManager = LinearLayoutManager(this)
        rvMenu.setLayoutManager(alayoutManager)
        rvMenu.setAdapter(_adapter)

        prepareMenus()
    }

    fun logoClick(){
        var fragment: Fragment? = null
        val fragmentClass: Class<*>

        fragmentClass = WelcomePage::class.java;
        try {
            fragment = fragmentClass.newInstance() as Fragment
        } catch (e: Exception) {
            e.printStackTrace()
        }

        //prepare parameter
        val arguments = Bundle()
        arguments.putString("title", "WELCOME")
        fragment!!.arguments = arguments

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit()
    }

    fun prepareMenus(){
        val titleMenus = this.resources.getStringArray(R.array.menu_array)
        var i = 1
        titleMenus.forEach {
            var a = MenuClass(i, it, R.drawable.ic_menu_tv)
            _menuList!!.add(a)
            i++
        }

        _adapter!!.notifyDataSetChanged()
    }
}