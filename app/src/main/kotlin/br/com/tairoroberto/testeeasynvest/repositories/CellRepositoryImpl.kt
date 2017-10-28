package br.com.tairoroberto.testeeasynvest.repositories

import android.content.res.AssetManager
import br.com.tairoroberto.testeeasynvest.domain.Cell
import br.com.tairoroberto.testeeasynvest.domain.CellContainer
import com.google.gson.Gson
import io.reactivex.Observable
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

class CellRepositoryImpl(assetManager: AssetManager, gson: Gson) : AbstractAssetRepository(assetManager, gson), CellRepository {

    override fun list(): Observable<List<Cell>> {
        var `in`: InputStream? = null

        return try {
            try {
                `in` = assetManager.open("cells.json")

                val reader = InputStreamReader(`in`, Charset.forName("UTF-8"))

                Observable.just(gson.fromJson(reader, CellContainer::class.java).cells)
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
