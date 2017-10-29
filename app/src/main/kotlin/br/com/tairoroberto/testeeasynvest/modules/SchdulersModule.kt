package br.com.tairoroberto.testeeasynvest.modules

import br.com.tairoroberto.testeeasynvest.qualifiers.IO
import br.com.tairoroberto.testeeasynvest.qualifiers.MainThread
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class SchdulersModule {

    @Provides
    @IO
    fun providesIOScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @MainThread
    fun providesMainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}
