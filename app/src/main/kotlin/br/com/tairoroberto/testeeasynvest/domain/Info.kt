package br.com.tairoroberto.testeeasynvest.domain

import android.os.Parcel
import android.os.Parcelable


data class Info(var name: String? = null,

                var data: String? = null) : Parcelable {
    override fun toString(): String {
        return "Info{" +
                "name='" + name + '\'' +
                ", data='" + data + '\'' +
                '}'
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(data)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Info> = object : Parcelable.Creator<Info> {
            override fun createFromParcel(source: Parcel): Info = Info(source)
            override fun newArray(size: Int): Array<Info?> = arrayOfNulls(size)
        }
    }
}

