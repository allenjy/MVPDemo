package com.allen.mvpdemo.view;

import com.allen.mvpdemo.model.NewsBean;

import java.util.List;

/**
 * Created by Allen on 2017/3/22.
 */

public interface MainView extends BaseView {

    void showNewsList(List<NewsBean.News> newses);
}
