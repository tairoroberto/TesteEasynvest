package br.com.tairoroberto.testeeasynvest.repositories

import android.content.res.AssetManager
import br.com.tairoroberto.testeeasynvest.domain.Investment
import br.com.tairoroberto.testeeasynvest.domain.FundContainer
import com.google.gson.Gson
import io.reactivex.Observable
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

class FundRepositoryImpl(assetManager: AssetManager, gson: Gson) : AbstractAssetRepository(assetManager, gson), FundRepository {

    override fun getFund(): Observable<Investment> {
        var `in`: InputStream? = null

        return try {
            try {
                `in` = assetManager.open("fund.json")

                val reader = InputStreamReader(`in`, Charset.forName("UTF-8"))

                Observable.just(gson.fromJson(reader, FundContainer::class.java).fund)
            } finally {
                if (`in` != null) {
                    `in`.close()
                }
            }
        } catch (ex: Exception) {
            Observable.error(ex)
        }
    }
}
