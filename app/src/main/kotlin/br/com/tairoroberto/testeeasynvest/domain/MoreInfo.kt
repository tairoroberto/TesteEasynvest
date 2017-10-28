package br.com.tairoroberto.testeeasynvest.domain

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MoreInfo(var month: FundInfo? = null,

                    var year: FundInfo? = null,

                    @SerializedName("12months")

                    var twelveMonths: FundInfo? = null) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readParcelable(FundInfo::class.java.classLoader),
            parcel.readParcelable(FundInfo::class.java.classLoader),
            parcel.readParcelable(FundInfo::class.java.classLoader))

    override fun toString(): String {
        return "MoreInfo{" +
                "month=" + month +
                ", year=" + year +
                ", 12months=" + twelveMonths +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(month, flags)
        parcel.writeParcelable(year, flags)
        parcel.writeParcelable(twelveMonths, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoreInfo> {
        override fun createFromParcel(parcel: Parcel): MoreInfo {
            return MoreInfo(parcel)
        }

        override fun newArray(size: Int): Array<MoreInfo?> {
            return arrayOfNulls(size)
        }
    }
}
