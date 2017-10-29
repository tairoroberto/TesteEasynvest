package br.com.tairoroberto.testeeasynvest.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.tairoroberto.testeeasynvest.R

class ContactFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_contact, container, false)

        val fm = childFragmentManager

        val fragment = fm.findFragmentById(R.id.content_frame)

        if (fragment == null) {
            fm.beginTransaction()
                    .replace(R.id.content_frame, FormFragment.newInstance())
                    .commit()
        }

        return view
    }

    fun onSuccess() {
        val fm = childFragmentManager

        fm.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.content_frame, ConfirmationFragment.newInstance())
                .commit()
    }

    fun reset() {
        val fm = childFragmentManager

        fm.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.content_frame, FormFragment.newInstance())
                .commit()
    }

    companion object {

        fun newInstance(): ContactFragment {
            return ContactFragment()
        }
    }
}
