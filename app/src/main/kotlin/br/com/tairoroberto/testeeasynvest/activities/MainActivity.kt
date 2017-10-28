package br.com.tairoroberto.testeeasynvest.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.tairoroberto.testeeasynvest.R
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.view.ViewCompat.setRotationX
import android.widget.LinearLayout



class MainActivity : AppCompatActivity() {


    private var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        view_pager.adapter = MainAdapter(this, supportFragmentManager)

        tab_layout.setupWithViewPager(view_pager)

        tab_layout.rotationX = 180f

        val tabListed = tab_layout.getChildAt(0) as LinearLayout
        (0 until tabListed.childCount)
                .map { tabListed.getChildAt(it) as LinearLayout }
                .forEach { it.rotationX = 180f }
    }


    fun success() {
        adapter?.onSuccess()
    }

    fun reset() {
        adapter?.reset()
    }
}
