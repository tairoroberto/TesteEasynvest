package br.com.tairoroberto.testeeasynvest.domain

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Cell(@SerializedName("id")
                var id: Int = 0,

                @SerializedName("type")
                var type: CellType? = null,

                @SerializedName("message")
                var message: String? = null,

                @SerializedName("typefield")
                var typeField: TypeField? = null,

                @SerializedName("hidden")
                var isHidden: Boolean = false,

                @SerializedName("topSpacing")
                var topSpacing: Double = 0.toDouble(),

                @SerializedName("show")
                var show: Int? = null,

                @SerializedName("required")
                var isRequired: Boolean = false) : Parcelable {

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readValue(Int::class.java.classLoader)?.let { CellType.values()[it as Int] },
            source.readString(),
            source.readValue(Int::class.java.classLoader)?.let { TypeField.values()[it as Int] },
            1 == source.readInt(),
            source.readDouble(),
            source.readValue(Int::class.java.classLoader) as Int?,
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeValue(type?.ordinal)
        writeString(message)
        writeValue(typeField?.ordinal)
        writeInt((if (isHidden) 1 else 0))
        writeDouble(topSpacing)
        writeValue(show)
        writeInt((if (isRequired) 1 else 0))
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<Cell> = object : Parcelable.Creator<Cell> {
            override fun createFromParcel(source: Parcel): Cell = Cell(source)
            override fun newArray(size: Int): Array<Cell?> = arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Cell{" +
                "id=" + id +
                ", type=" + type +
                ", message='" + message + '\'' +
                ", typeField=" + typeField +
                ", hidden=" + isHidden +
                ", topSpacing=" + topSpacing +
                ", show=" + show +
                ", required=" + isRequired +
                '}'
    }
}
