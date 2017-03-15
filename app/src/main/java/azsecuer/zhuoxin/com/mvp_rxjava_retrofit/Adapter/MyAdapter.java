package azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import azsecuer.zhuoxin.com.mvp_rxjava_retrofit.Info.Demo;
import azsecuer.zhuoxin.com.mvp_rxjava_retrofit.R;

/**
 * Created by Administrator on 2017/3/14.
 */

public class MyAdapter extends RecyclerView.Adapter {
    private List<Demo.ResultBean.DataBean>list;
    private Context context;

    public MyAdapter(Context context, List<Demo.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Myviewholder(LayoutInflater.from(context).inflate(R.layout.mynews_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Demo.ResultBean.DataBean dataBean=list.get(position);
        ((Myviewholder)holder).textViewtop.setText(dataBean.getTitle());
        ((Myviewholder)holder).textViewleft.setText(dataBean.getAuthor_name());
        ((Myviewholder)holder).textViewright.setText(dataBean.getDate());
         Picasso.with(context).load(dataBean.getThumbnail_pic_s()).into(((Myviewholder)holder).imageView);//图片的缓存异步加载
        ((Myviewholder)holder).linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback!=null){
                    callback.click(position);
                }
            }
        });
        ((Myviewholder)holder).linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(callback!=null){
                    callback.longclick(position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textViewtop,textViewleft,textViewright;
        private LinearLayout linearLayout;
        public Myviewholder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.iv_item);
            textViewtop=(TextView)itemView.findViewById(R.id.item_tv_top);
            textViewleft=(TextView)itemView.findViewById(R.id.item_tv_buttomleft);
            textViewright=(TextView)itemView.findViewById(R.id.item_tv_buttomRight);
            linearLayout= (LinearLayout) itemView.findViewById(R.id.manews_lineatlayout);
        }
    }

    public interface Callback{
        void click(int k);
        void longclick(int k);
    }
    private  Callback callback;

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void remove(Demo.ResultBean.DataBean dataBean){
        list.remove(dataBean);
        notifyDataSetChanged();
    }
}
