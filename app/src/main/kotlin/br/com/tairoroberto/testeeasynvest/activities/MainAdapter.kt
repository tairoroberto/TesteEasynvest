package br.com.tairoroberto.testeeasynvest.activities

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup

import br.com.tairoroberto.testeeasynvest.R
import br.com.tairoroberto.testeeasynvest.fragments.ContactFragment
import br.com.tairoroberto.testeeasynvest.fragments.InvestmentFragment

class MainAdapter(private val context: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var contactFragment: ContactFragment? = null

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            InvestmentFragment.newInstance()
        } else {
            ContactFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment

        if (fragment is ContactFragment) {
            contactFragment = fragment
        }

        return fragment
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any) {
        if (position == 1) {
            contactFragment = null
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        val resId = if (position == 0) R.string.fragment_investment else R.string.fragment_contact

        return context.getString(resId)
    }

    fun onSuccess() {
        if (contactFragment != null) {
            contactFragment?.onSuccess()
        }
    }

    fun reset() {
        if (contactFragment != null) {
            contactFragment?.reset()
        }
    }
}
