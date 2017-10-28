package br.com.tairoroberto.testeeasynvest.domain

import android.os.Parcel
import android.os.Parcelable

class Fund(var title: String? = null,

           var fundName: String? = null,

           var whatIs: String? = null,

           var definition: String? = null,

           var graph: Graph? = null,

           var riskTitle: String? = null,

           var risk: Int = 0,

           var infoTitle: String? = null,

           var moreInfo: MoreInfo? = null,

           var info: List<Info>? = null,

           var downInfo: List<Info>? = null) : Parcelable {

    override fun toString(): String {
        return "Fund{" +
                "title='" + title + '\'' +
                ", fundName='" + fundName + '\'' +
                ", whatIs='" + whatIs + '\'' +
                ", definition='" + definition + '\'' +
                ", graph=" + graph +
                ", riskTitle='" + riskTitle + '\'' +
                ", risk=" + risk +
                ", infoTitle='" + infoTitle + '\'' +
                ", moreInfo=" + moreInfo +
                ", info=" + info +
                ", downInfo=" + downInfo +
                '}'
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readParcelable<Graph>(Graph::class.java.classLoader),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readParcelable<MoreInfo>(MoreInfo::class.java.classLoader),
            source.createTypedArrayList(Info.CREATOR),
            source.createTypedArrayList(Info.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(fundName)
        writeString(whatIs)
        writeString(definition)
        writeParcelable(graph, 0)
        writeString(riskTitle)
        writeInt(risk)
        writeString(infoTitle)
        writeParcelable(moreInfo, 0)
        writeTypedList(info)
        writeTypedList(downInfo)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Fund> = object : Parcelable.Creator<Fund> {
            override fun createFromParcel(source: Parcel): Fund = Fund(source)
            override fun newArray(size: Int): Array<Fund?> = arrayOfNulls(size)
        }
    }
}
