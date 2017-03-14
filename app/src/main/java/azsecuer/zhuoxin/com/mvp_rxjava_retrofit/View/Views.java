package azsecuer.zhuoxin.com.mvp_rxjava_retrofit.View;

import java.util.List;

import azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Info.Demo;

/**
 * Created by Administrator on 2017/3/14.
 */

public interface Views {
    void showprogress();
    void hideprogress();
    void setdata(List<Demo.ResultBean.DataBean> list);
    void showMessage(String s);
}
