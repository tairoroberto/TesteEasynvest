package br.com.tairoroberto.testeeasynvest.domain

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class FundContainer(@SerializedName("screen")
                         var fund: Fund? = null) : Parcelable {


    constructor(parcel: Parcel) : this(parcel.readParcelable<Fund>(Fund::class.java.classLoader))

    override fun toString(): String {
        return "FundContainer{" +
                "fund=" + fund +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(fund, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FundContainer> {
        override fun createFromParcel(parcel: Parcel): FundContainer {
            return FundContainer(parcel)
        }

        override fun newArray(size: Int): Array<FundContainer?> {
            return arrayOfNulls(size)
        }
    }
}


