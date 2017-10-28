package br.com.tairoroberto.testeeasynvest.domain

import com.google.gson.annotations.SerializedName

import java.io.Serializable


class Cell : Serializable {

    var id: Int = 0

    var type: CellType? = null

    var message: String? = null

    @SerializedName("typefield")
    var typeField: TypeField? = null

    var isHidden: Boolean = false

    var topSpacing: Double = 0.toDouble()

    var show: Int? = null

    var isRequired: Boolean = false

    constructor()

    constructor(type: CellType) {
        this.type = type
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val cell = o as Cell?

        if (id != cell?.id) return false
        if (isHidden != cell.isHidden) return false
        if (java.lang.Double.compare(cell.topSpacing, topSpacing) != 0) return false
        if (isRequired != cell.isRequired) return false
        if (type != cell.type) return false
        if (if (message != null) message != cell.message else cell.message != null) return false
        if (typeField !== cell.typeField) return false
        return if (show != null) show == cell.show else cell.show == null

    }

    override fun hashCode(): Int {
        var result: Int = id
        val temp: Long = java.lang.Double.doubleToLongBits(topSpacing)
        result = 31 * result + if (type != null) type?.hashCode() as Int else 0
        result = 31 * result + if (message != null) message?.hashCode() as Int else 0
        result = 31 * result + if (typeField != null) typeField?.hashCode() as Int else 0
        result = 31 * result + if (isHidden) 1 else 0
        result = 31 * result + (temp xor temp.ushr(32)).toInt()
        result = 31 * result + if (show != null) show?.hashCode() as Int else 0
        result = 31 * result + if (isRequired) 1 else 0
        return result
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
