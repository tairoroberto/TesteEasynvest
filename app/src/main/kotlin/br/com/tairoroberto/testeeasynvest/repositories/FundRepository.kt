package br.com.tairoroberto.testeeasynvest.repositories

import br.com.tairoroberto.testeeasynvest.domain.Investment
import io.reactivex.Observable

interface FundRepository {

    fun getFund(): Observable<Investment>

}
