package azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Moldel;


import java.util.ArrayList;
import java.util.List;

import azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Info.Demo;
import azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Retrofit.server;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/14.
 */

public class Moldels {
private server servers;
    private Retrofit retrofit;

   public List<Demo.ResultBean.DataBean>list=new ArrayList<>();
    public Moldels() {
        retrofit=new Retrofit.Builder().
                baseUrl("http://v.juhe.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        servers=retrofit.create(server.class);
    }

    public void loaddata(Subscriber subscriber,String type){
        servers.getdata(type, "aa4ef3e8bb94a9ac75be9e58fe00c1df")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);


    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final Moldels INSTANCE = new Moldels();
    }

    //获取单例
    public static Moldels getInstance(){
        return SingletonHolder.INSTANCE;
    }

}
