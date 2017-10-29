package br.com.tairoroberto.testeeasynvest

import android.app.Application
import br.com.tairoroberto.testeeasynvest.modules.AndroidModule

/**
 * Created by tairo on 25/10/17.
 */
class CustomApplication: Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .androidModule(AndroidModule(this))
                .build()
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }
}