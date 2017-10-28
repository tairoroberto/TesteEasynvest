package br.com.tairoroberto.testeeasynvest.views

import android.view.ViewGroup
import android.widget.CheckBox

import br.com.tairoroberto.testeeasynvest.R
import br.com.tairoroberto.testeeasynvest.domain.Cell

class CustomCheckbox internal constructor(parent: ViewGroup?, cell: Cell) : ViewHandler<CheckBox>(parent, cell) {

    override val layoutId: Int
        get() = R.layout.checkbox

    override fun init(view: CheckBox) {
        view.text = cell.message
    }

    override fun configureShow(sibling: ViewHandler<*>?) {
        view.setOnCheckedChangeListener { buttonView, isChecked -> sibling?.isVisible = isChecked }
    }

    override fun validate(): Boolean {
        return !cell.isRequired || view.isChecked
    }
}
