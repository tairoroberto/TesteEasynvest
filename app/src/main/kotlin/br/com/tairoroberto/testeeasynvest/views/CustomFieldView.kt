package br.com.tairoroberto.testeeasynvest.views

import android.graphics.PorterDuff
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.ViewGroup
import br.com.tairoroberto.testeeasynvest.R
import br.com.tairoroberto.testeeasynvest.domain.Cell
import br.com.tairoroberto.testeeasynvest.domain.TypeField
import br.com.tairoroberto.testeeasynvest.extension.getAttr
import com.vicmikhailau.maskededittext.MaskedEditText
import java.util.regex.Pattern

class CustomFieldView internal constructor(parent: ViewGroup?, cell: Cell) : ViewHandler<TextInputLayout>(parent, cell), TextWatcher {

    public override val layoutId: Int
        get() = R.layout.custom_edittext

    override fun init(view: TextInputLayout) {
        view.hint = cell.message

        if (cell.typeField != null) {
            when (cell.typeField) {
                TypeField.TEL_NUMBER -> {
                    (view.editText as MaskedEditText).setMask(MASK)

                    view.editText?.inputType = InputType.TYPE_CLASS_PHONE
                }
                TypeField.TEXT -> view.editText?.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
                else -> {

                }
            }
        }

        if (cell.isRequired) {
            view.editText?.addTextChangedListener(this)
        }
    }

    override fun configureShow(sibling: ViewHandler<*>?) {
        view.setOnClickListener { sibling?.isVisible = true }
    }

    override fun validate(): Boolean {
        val text = view.editText?.text.toString().trim { it <= ' ' }

        var valid = !cell.isRequired

        if (!text.isEmpty()) {
            valid = when (cell.typeField) {
                TypeField.TEL_NUMBER -> text.length >= MASK.length - 1
                TypeField.EMAIL -> isValid(text)
                else -> true
            }
        }

        val colorAttr = if (valid) R.attr.colorEditTextValid else R.attr.colorEditTextInvalid

        val background = view.editText?.background

        background?.setColorFilter(view.context.getAttr(colorAttr), PorterDuff.Mode.SRC_IN)

        return valid
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable) {
        validate()
    }

    private fun isValid(email: String): Boolean {
        val pattern = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        val matcher = pattern.matcher(email)

        return matcher.matches()
    }

    companion object {

        private val MASK = "(##) #####-####"
    }
}
