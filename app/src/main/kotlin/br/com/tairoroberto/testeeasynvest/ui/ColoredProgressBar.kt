package br.com.tairoroberto.testeeasynvest.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.ProgressBar

import br.com.tairoroberto.testeeasynvest.R

class ColoredProgressBar : ProgressBar {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @Synchronized override fun setProgress(progress: Int) {
        val percentage = progress * 100 / max

        val color: Int

        color = when {
            percentage <= 20 -> R.color.risk_1
            percentage <= 40 -> R.color.risk_2
            percentage <= 60 -> R.color.risk_3
            percentage <= 80 -> R.color.risk_4
            else -> R.color.risk_5
        }

        progressDrawable.setColorFilter(
                ContextCompat.getColor(context, color), android.graphics.PorterDuff.Mode.SRC_IN)

        super.setProgress(progress)
    }
}
