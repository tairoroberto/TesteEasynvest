package br.com.tairoroberto.testeeasynvest.presenters

import br.com.tairoroberto.testeeasynvest.qualifiers.IO
import br.com.tairoroberto.testeeasynvest.qualifiers.MainThread
import br.com.tairoroberto.testeeasynvest.repositories.FundRepository
import br.com.tairoroberto.testeeasynvest.views.FundView
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class FundPresenter(private val repository: FundRepository) {

    private var io: Scheduler? = null

    private var mainThread: Scheduler? = null

    private var disposable: Disposable? = null

    private var view: FundView? = null

    @Inject
    constructor(repository: FundRepository, @IO io: Scheduler, @MainThread mainThread: Scheduler) : this(repository) {

        this.io = io

        this.mainThread = mainThread
    }


    fun attachView(view: FundView) {
        this.view = view
    }

    fun detachView() {
        if (disposable != null) {
            disposable!!.dispose()
        }

        view = null
    }

    fun getFund() {
        val observable = repository.getFund()

        if (io != null && mainThread != null) {
            observable.subscribeOn(io)
                    .observeOn(mainThread)
        }

        disposable = observable
                .subscribe { fund ->
                    if (view != null) {
                        view!!.setFund(fund)
                    }
                }
    }
}
