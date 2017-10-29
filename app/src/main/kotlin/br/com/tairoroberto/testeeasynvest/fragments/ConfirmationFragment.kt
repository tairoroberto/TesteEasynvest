package br.com.tairoroberto.testeeasynvest.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import br.com.tairoroberto.testeeasynvest.R
import br.com.tairoroberto.testeeasynvest.activities.MainActivity

class ConfirmationFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_confirmation, container, false)

        val textview = view?.findViewById<TextView>(R.id.send_message)
        textview?.setOnClickListener { view1 ->
            val activity = activity as MainActivity

            activity.reset()
        }
        return view
    }

    companion object {

        fun newInstance(): ConfirmationFragment {
            return ConfirmationFragment()
        }
    }
}
