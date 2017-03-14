package azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

import azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Adapter.MyAdapter;
import azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Info.Demo;
import azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Listen;
import azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Moldel.Moldels;
import azsecuer.zhuoxin.com.mvp_rxjava_retrofit.MyListen;
import azsecuer.zhuoxin.com.mvp_rxjava_retrofit.R;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private List<Demo.ResultBean.DataBean> lists=new ArrayList<>();
    private Subscriber<Demo> s;
    private Listen<Demo> l;
    private FloatingActionButton fab1,fab2;
    private FloatingActionsMenu fabmenu;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview= (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("新闻");
        toolbar.setTitleTextColor(Color.CYAN);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fab1= (FloatingActionButton) findViewById(R.id.FAB1);
        fab2= (FloatingActionButton) findViewById(R.id.FAB2);
        fabmenu= (FloatingActionsMenu) findViewById(R.id.FABmenu);
        fab1.setImageResource(R.drawable.ic_timer_24dp);
        fab2.setImageResource(R.drawable.ic_accessibility_24dp);

        initdata();
    }

    private void initdata() {
//        s=new Subscriber<Demo>() {
//            @Override
//            public void onCompleted() {
//                Log.i("1111","onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i("1111","onError"+e.toString());
//            }
//
//            @Override
//            public void onNext(Demo dataBeen) {
//                lists=dataBeen.getResult().getData();
//                Log.i("1111",lists.toString());
//                MyAdapter adapter=new MyAdapter(MainActivity.this,lists);
//                recyclerview.setAdapter(adapter);
//            }
//        };
        //监听的实现
        l=new Listen<Demo>() {
            @Override
            public void onNext(Demo demo) {
                lists=demo.getResult().getData();
                Log.i("1111",lists.toString());
                MyAdapter adapter=new MyAdapter(MainActivity.this,lists);
                recyclerview.setAdapter(adapter);
            }
        };
        //传入监听者
        Moldels.getInstance().loaddata(new MyListen(l),"top");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
