package azsecuer.zhuoxin.com.mvp_rxjava_retrofit;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/14.
 */

public class MyListen<T> extends Subscriber<T> implements Listen<T> {
    private Listen<T> listen;

    public Listen<T> getListen() {
        return listen;
    }

    public void setListen(Listen<T> listen) {
        this.listen = listen;
    }

    public MyListen(Listen<T> listen) {
        this.listen = listen;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {
        listen.onNext(t);
    }
}
