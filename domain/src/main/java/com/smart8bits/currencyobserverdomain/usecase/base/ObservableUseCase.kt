package com.smart8bits.currencyobserverdomain.usecase.base

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<T>(
    private val executorScheduler: Scheduler,
    private val uiScheduler: Scheduler
) {
    abstract fun buildUseCaseObservable(): Observable<T>

    fun execute(observer: DefaultObserver<T>): DisposableObserver<T>? {
        val observable = buildUseCaseObservable()
            .subscribeOn(executorScheduler)
            .observeOn(uiScheduler)
        return observable.subscribeWith(observer)
    }
}
