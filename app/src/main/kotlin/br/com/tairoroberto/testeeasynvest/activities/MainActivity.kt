package br.com.tairoroberto.testeeasynvest.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import br.com.tairoroberto.testeeasynvest.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private var adapter: MainAdapter? = null
    private val REQUEST_SDCARD_PERMISSIONS = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        adapter = MainAdapter(this, supportFragmentManager)
        view_pager.adapter = adapter

        tab_layout.setupWithViewPager(view_pager)

        tab_layout.rotationX = 180f

        val tabListed = tab_layout.getChildAt(0) as LinearLayout
        (0 until tabListed.childCount)
                .map { tabListed.getChildAt(it) as LinearLayout }
                .forEach { it.rotationX = 180f }

        mayRequestSdCardPermissions()
    }

    fun mayRequestSdCardPermissions(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.atention))
            builder.setMessage(getString(R.string.rational_permission))
            builder.setNegativeButton(getString(R.string.not), { dialog, _ ->
                dialog.dismiss()
            })
            builder.setPositiveButton(getString(R.string.yes), { dialog, _ ->
                dialog.dismiss()
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_SDCARD_PERMISSIONS)
            })
            builder.create()
            builder.show()
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_SDCARD_PERMISSIONS)
        }
        return false
    }



    /**
     * Callback received when a permissions request has been completed.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode == REQUEST_SDCARD_PERMISSIONS) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                return
            }
        }
    }


    fun success() {
        adapter?.onSuccess()
    }

    fun reset() {
        adapter?.reset()
    }
}
