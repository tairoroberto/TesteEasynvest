package br.com.tairoroberto.testeeasynvest.views

import br.com.tairoroberto.testeeasynvest.domain.Cell


interface FormView {

    fun setCells(cells: List<Cell>)

    fun displayError(throwable: Throwable)
}
