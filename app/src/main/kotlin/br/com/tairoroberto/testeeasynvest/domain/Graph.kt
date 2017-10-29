package br.com.tairoroberto.testeeasynvest.domain

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*


data class Graph(@SerializedName("CDI")
            var cdi: FloatArray? = null,

            var fund: FloatArray? = null,

            var x: Array<String>? = null) : Parcelable {

    override fun toString(): String {
        return "Graph{" +
                "cdi=" + Arrays.toString(cdi) +
                ", fund=" + Arrays.toString(fund) +
                ", x=" + Arrays.toString(x) +
                '}'
    }

    constructor(source: Parcel) : this(
            source.createFloatArray(),
            source.createFloatArray(),
            source.createStringArray()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeFloatArray(cdi)
        writeFloatArray(fund)
        writeStringArray(x)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Graph

        if (!Arrays.equals(cdi, other.cdi)) return false
        if (!Arrays.equals(fund, other.fund)) return false
        if (!Arrays.equals(x, other.x)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cdi?.let { Arrays.hashCode(it) } ?: 0
        result = 31 * result + (fund?.let { Arrays.hashCode(it) } ?: 0)
        result = 31 * result + (x?.let { Arrays.hashCode(it) } ?: 0)
        return result
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Graph> = object : Parcelable.Creator<Graph> {
            override fun createFromParcel(source: Parcel): Graph = Graph(source)
            override fun newArray(size: Int): Array<Graph?> = arrayOfNulls(size)
        }
    }
}
