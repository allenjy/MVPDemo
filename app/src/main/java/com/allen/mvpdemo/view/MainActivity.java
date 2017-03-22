package com.allen.mvpdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.mvpdemo.model.NewsBean;
import com.allen.mvpdemo.R;
import com.allen.mvpdemo.presenter.MainPresenterImp;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainView {

    RecyclerView mRecyclerView;
    MyAdapter mAdapter = new MyAdapter();
    private MainPresenterImp presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        presenter = new MainPresenterImp(this);
        presenter.init();
    }

    @Override
    protected void onDestroy() {
        presenter.recycle();
        presenter = null;
        mAdapter = null;
        super.onDestroy();
    }

    public void lastPage(View view) {
        presenter.lastPage();
    }

    public void nextPage(View view) {
        presenter.nextPage();
    }

    @Override
    public void showNewsList(List<NewsBean.News> newses) {
        mAdapter.setData(newses);
    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHold> {

        private List<NewsBean.News> list = new ArrayList<>();

        public void setData(List<NewsBean.News> data) {

            if (data != null) {
                list.clear();
                list = data;
                notifyDataSetChanged();
            }
        }

        class ViewHold extends RecyclerView.ViewHolder {

            private TextView titleTv;
            private ImageView imgIv;

            private ViewHold(View itemView) {
                super(itemView);
                titleTv = (TextView) itemView.findViewById(R.id.title_tv);
                imgIv = (ImageView) itemView.findViewById(R.id.img_iv);
            }
        }


        @Override
        public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ViewHold(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_news, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHold holder, int position) {

            final NewsBean.News news = list.get(position);
            holder.titleTv.setText(news.title);
            Glide.with(getApplicationContext()).load(news.imgurl).centerCrop().into(holder.imgIv);
            holder.imgIv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
                    intent.putExtra("URL", news.docurl);
                    startActivity(intent);
                }
            });
        }


        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
