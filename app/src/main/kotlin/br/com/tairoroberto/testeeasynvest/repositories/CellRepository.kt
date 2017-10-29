package br.com.tairoroberto.testeeasynvest.repositories

import br.com.tairoroberto.testeeasynvest.domain.Cell
import io.reactivex.Observable

interface CellRepository {

    fun list(): Observable<List<Cell>>

}
