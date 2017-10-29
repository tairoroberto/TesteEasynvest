package br.com.tairoroberto.testeeasynvest.views

import android.view.ViewGroup
import android.widget.Button

import br.com.tairoroberto.testeeasynvest.R
import br.com.tairoroberto.testeeasynvest.domain.Cell


class CustomButton internal constructor(parent: ViewGroup?, cell: Cell) : ViewHandler<Button>(parent, cell) {

    public override val layoutId: Int
        get() = R.layout.custom_button

    override fun init(view: Button) {
        view.text = cell.message
    }

    override fun configureShow(sibling: ViewHandler<*>?) {

    }

    override fun validate(): Boolean {
        return true
    }
}
