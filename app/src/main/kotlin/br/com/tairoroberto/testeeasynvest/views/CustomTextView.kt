package br.com.tairoroberto.testeeasynvest.views

import android.view.ViewGroup
import android.widget.TextView

import br.com.tairoroberto.testeeasynvest.R
import br.com.tairoroberto.testeeasynvest.domain.Cell

class CustomTextView internal constructor(parent: ViewGroup?, cell: Cell) : ViewHandler<TextView>(parent, cell) {

    public override val layoutId: Int
        get() = R.layout.custom_textview

    override fun init(view: TextView) {
        view.text = cell.message
    }

    override fun configureShow(sibling: ViewHandler<*>?) {}

    override fun validate(): Boolean {
        return true
    }
}
