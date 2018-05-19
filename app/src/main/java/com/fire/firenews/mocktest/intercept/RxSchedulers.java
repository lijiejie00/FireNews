package com.fire.firenews.mocktest.intercept;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * RxJava调度管理
 * Created by bernie
 * on 2016.08.14:50
 */
public class RxSchedulers {
    public static <T> Observable.Transformer<T, T> io_main() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
//                        .compose(CacheTransformer.<T>emptyTransformer())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Observable.Transformer<T, T> IoMain() {

        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                System.out.println();
                return tObservable.compose(CacheTransformer.<T>emptyTransformer())
                        .subscribeOn(Schedulers.io())

                        .observeOn(AndroidSchedulers.mainThread()).map(new Func1<T, T>() {
                            @Override
                            public Object call(Object t) {

                                return t;
                            }
                        });
            }
        };
    }
}
