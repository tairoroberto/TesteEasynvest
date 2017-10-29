package br.com.tairoroberto.testeeasynvest.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.tairoroberto.testeeasynvest.domain.Cell
import br.com.tairoroberto.testeeasynvest.extension.dpTopx


abstract class ViewHandler<T : View> internal constructor(parent: ViewGroup?, cell: Cell) {

    var cell: Cell
        set

    var view: T
        set

    val id: Int
        get() = cell.id

    var isVisible: Boolean
        get() = view.visibility == View.VISIBLE
        set(visible) {
            view.visibility = if (visible) View.VISIBLE else View.GONE
        }

    protected abstract val layoutId: Int

    init {
        this.cell = cell

        val inflater = LayoutInflater.from(parent?.context)

        view = inflater.inflate(layoutId, parent, false) as T

        if (cell.topSpacing > 0) {
            val params = view.layoutParams as ViewGroup.MarginLayoutParams

            params.topMargin = parent?.context?.dpTopx(cell.topSpacing) as Int
        }

        view.visibility = if (cell.isHidden) View.GONE else View.VISIBLE

        init(view)
    }

    fun required(): Boolean {
        return cell.isRequired
    }

    protected abstract fun init(view: T)

    abstract fun configureShow(sibling: ViewHandler<*>?)

    abstract fun validate(): Boolean
}
