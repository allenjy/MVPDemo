package com.allen.mvpdemo.model;

import com.allen.mvpdemo.ApiClient;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * Created by Allen on 2017/3/22.
 */

public class MainDao {

    public void getNewsList(String type, int page, int limit, Observer<NewsBean> observer) {

        ApiClient.create(Api.class).getNewsList(type, page, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
