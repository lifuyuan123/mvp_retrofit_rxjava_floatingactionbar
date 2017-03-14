package azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Retrofit;


import azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Info.Demo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/14.
 */

public interface server {
    @GET("/toutiao/index")
    Observable<Demo> getdata(@Query("type")String type, @Query("key") String key);

}
