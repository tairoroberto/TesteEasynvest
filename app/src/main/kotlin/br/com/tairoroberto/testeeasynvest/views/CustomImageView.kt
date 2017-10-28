package br.com.tairoroberto.testeeasynvest.views

import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide

import br.com.tairoroberto.testeeasynvest.R
import br.com.tairoroberto.testeeasynvest.domain.Cell

class CustomImageView internal constructor(parent: ViewGroup?, cell: Cell) : ViewHandler<ImageView>(parent, cell) {

    override val layoutId: Int
        get() = R.layout.custom_imageview

    override fun init(view: ImageView) {
        Glide.with(view.context)
                .load(cell.message)
                .placeholder(R.drawable.placeholder).into(view)
    }

    override fun configureShow(sibling: ViewHandler<*>?) {

    }

    override fun validate(): Boolean {
        return true
    }
}
