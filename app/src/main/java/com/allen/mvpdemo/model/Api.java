package com.allen.mvpdemo.model;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * Created by Allen on 2017/3/16.
 */

public interface Api {

    /**
     * 获取新闻列表
     * 1	war	军事
     * 2	sport	体育
     * 3	tech	科技
     * 4	edu	教育
     * 5	ent	娱乐
     * 6	money	财经
     * 7	gupiao	股票
     * 8	travel	旅游
     * 9	lady	女人
     * http://wangyi.butterfly.mopaasapp.com/news/api?type=war&page=1&limit=10
     */
    @GET("news/api")
    Observable<NewsBean> getNewsList(@Query("type") String type
            , @Query("page") int page
            , @Query("limit") int limit);
}
