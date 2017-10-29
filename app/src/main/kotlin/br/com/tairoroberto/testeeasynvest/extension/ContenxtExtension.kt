package br.com.tairoroberto.testeeasynvest.extension

import android.content.Context
import android.util.TypedValue

/**
 * Created by tairo on 10/27/17.
 */
fun Context.dpTopx(dp: Double): Int {
    val metrics = this.resources.displayMetrics

    val density = metrics.density

    return Math.ceil(dp * density).toInt()
}

fun Context.getAttr(attr: Int): Int {
    val typedValue = TypedValue()

    val theme = this.theme

    theme.resolveAttribute(attr, typedValue, true)

    return typedValue.data
}