package br.com.tairoroberto.testeeasynvest

import javax.inject.Singleton


import br.com.tairoroberto.testeeasynvest.fragments.FormFragment
import br.com.tairoroberto.testeeasynvest.fragments.InvestmentFragment
import br.com.tairoroberto.testeeasynvest.modules.AndroidModule
import br.com.tairoroberto.testeeasynvest.modules.GsonModule
import br.com.tairoroberto.testeeasynvest.modules.RepositoriesModule
import br.com.tairoroberto.testeeasynvest.modules.SchdulersModule
import dagger.Component


@Singleton
@Component(modules = arrayOf(AndroidModule::class, GsonModule::class, RepositoriesModule::class, SchdulersModule::class))
interface AppComponent {

    fun inject(fragment: FormFragment)

    fun inject(fundFragment: InvestmentFragment)
}
