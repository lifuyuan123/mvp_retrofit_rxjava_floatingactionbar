package azsecuer.zhuoxin.com.mvp_rxjava_retrofit;

import azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Info.Demo;

/**
 * Created by Administrator on 2017/3/14.
 */

public interface Listen<T> {
    void onNext(T t);
}
