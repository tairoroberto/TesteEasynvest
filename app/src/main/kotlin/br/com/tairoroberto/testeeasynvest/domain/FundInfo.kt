package br.com.tairoroberto.testeeasynvest.domain

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class FundInfo(var fund: Double? = null,

                    @SerializedName("CDI")
                    var cdi: Double? = null) : Parcelable {

    override fun toString(): String {
        return "FundInfo{" +
                "fund=" + fund +
                ", cdi=" + cdi +
                '}'
    }

    constructor(source: Parcel) : this(
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Double::class.java.classLoader) as Double?
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(fund)
        writeValue(cdi)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<FundInfo> = object : Parcelable.Creator<FundInfo> {
            override fun createFromParcel(source: Parcel): FundInfo = FundInfo(source)
            override fun newArray(size: Int): Array<FundInfo?> = arrayOfNulls(size)
        }
    }
}
