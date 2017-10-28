package br.com.tairoroberto.testeeasynvest.presenters

import br.com.tairoroberto.testeeasynvest.qualifiers.IO
import br.com.tairoroberto.testeeasynvest.qualifiers.MainThread
import br.com.tairoroberto.testeeasynvest.repositories.CellRepository
import br.com.tairoroberto.testeeasynvest.repositories.FundRepository
import br.com.tairoroberto.testeeasynvest.views.FormView
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class FormPresenter(private val repository: CellRepository) {

    private var view: FormView? = null

    private var disposable: Disposable? = null

    private var io: Scheduler? = null

    private var mainThread: Scheduler? = null

    @Inject
    constructor(repository: CellRepository, @IO io: Scheduler, @MainThread mainThread: Scheduler) : this(repository) {

        this.io = io

        this.mainThread = mainThread
    }

    fun attachView(view: FormView) {
        this.view = view
    }

    fun detachView() {
        if (disposable != null) {
            disposable!!.dispose()
        }

        this.view = null
    }

    fun getCells() {
        if (view != null) {
            val observable = repository.list()

            if (io != null && mainThread != null) {
                observable.subscribeOn(io)
                        .observeOn(mainThread)
            }

            disposable = observable
                    .subscribe({ cells ->
                        if (view != null) {
                            view!!.setCells(cells)
                        }
                    }) { throwable ->
                        if (view != null) {
                            view!!.displayError(throwable)
                        }
                    }
        }
    }

}