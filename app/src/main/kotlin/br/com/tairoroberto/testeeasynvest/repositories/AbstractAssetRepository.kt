package br.com.tairoroberto.testeeasynvest.repositories

import android.content.res.AssetManager

import com.google.gson.Gson

abstract class AbstractAssetRepository internal constructor(internal var assetManager: AssetManager, protected var gson: Gson)
