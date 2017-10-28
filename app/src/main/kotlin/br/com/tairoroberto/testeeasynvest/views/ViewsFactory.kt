package br.com.tairoroberto.testeeasynvest.views

import android.view.ViewGroup
import br.com.tairoroberto.testeeasynvest.domain.Cell
import br.com.tairoroberto.testeeasynvest.domain.CellType
import com.annimon.stream.Stream

class ViewsFactory(private val parent: ViewGroup?) {

    fun create(cell: Cell): ViewHandler<*>? {
        return when (cell.type) {
            CellType.FIELD -> CustomFieldView(parent, cell)
            CellType.TEXT -> CustomTextView(parent, cell)
            CellType.IMAGE -> CustomImageView(parent, cell)
            CellType.CHECKBOX -> CustomCheckbox(parent, cell)
            CellType.SEND -> CustomButton(parent, cell)
            else -> null
        }
    }

    fun getHandlers(cells: List<Cell>): List<ViewHandler<*>?> {
        val factory = ViewsFactory(parent)

        val list = cells.map(factory::create)

        list.forEach { viewHandler ->
            val cell = viewHandler?.cell

            if (cell?.show != null) {
                val sibling = Stream.of(list)
                        .filter { viewHandler1 -> viewHandler !== viewHandler1 }
                        .filter { viewHandler1 -> cell.show == viewHandler1?.id }
                        .findFirst()

                sibling.ifPresent({ viewHandler.configureShow(it) })
            }
        }
        return list
    }
}
