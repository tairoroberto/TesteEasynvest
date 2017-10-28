package br.com.tairoroberto.testeeasynvest.domain

import android.os.Parcel
import android.os.Parcelable

data class CellContainer(var cells: List<Cell>? = null) : Parcelable {
    constructor(source: Parcel) : this(
            ArrayList<Cell>().apply { source.readList(this, Cell::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeList(cells)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<CellContainer> = object : Parcelable.Creator<CellContainer> {
            override fun createFromParcel(source: Parcel): CellContainer = CellContainer(source)
            override fun newArray(size: Int): Array<CellContainer?> = arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "CellContainer{" +
                "cells=" + cells +
                '}'
    }
}
