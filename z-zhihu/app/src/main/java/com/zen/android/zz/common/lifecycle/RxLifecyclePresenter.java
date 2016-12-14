package com.zen.android.zz.common.lifecycle;

import easymvp.Presenter;
import easymvp.RxPresenter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;


/**
 * A base class for implementing a {@link Presenter} with RxJava functionality.
 *
 * @author Saeed Masoumi (saeed@6thsolution.com)
 */
public abstract class RxLifecyclePresenter<V> extends RxPresenter<V> {

    private final BehaviorSubject<PresenterEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    public void onViewAttached(V view) {
        super.onViewAttached(view);
        lifecycleSubject.onNext(PresenterEvent.VIEW_ATTACH);
    }

    @Override
    public void onViewDetached() {
        lifecycleSubject.onNext(PresenterEvent.VIEW_DETACH);
        super.onViewDetached();
    }

    @Override
    public void onDestroyed() {
        lifecycleSubject.onNext(PresenterEvent.DESTROY);
        super.onDestroyed();
    }

    protected <T> Observable.Transformer<T, T> bindUntilEvent(final PresenterEvent event) {
        final Observable<PresenterEvent> observable = lifecycleSubject.takeFirst(
                presenterEvent -> presenterEvent.equals(event));
        return src -> src.takeUntil(observable);
    }

    protected <T> Observable.Transformer<T, T> bindUntilDetached() {
        return bindUntilEvent(PresenterEvent.VIEW_DETACH);
    }

    protected <T> Observable.Transformer<T, T> async() {
        return tObservable -> {
            Observable.Transformer<T, T> transformer = bindUntilDetached();
            return tObservable.compose(transformer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        };
    }


}
