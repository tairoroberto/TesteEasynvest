package br.com.tairoroberto.testeeasynvest.modules

import android.app.Application
import android.content.res.AssetManager

import dagger.Module
import dagger.Provides

@Module
class AndroidModule(private val application: Application) {

    @Provides
    fun providesAssetManager(): AssetManager {
        return application.assets
    }

}
