package com.allen.mvpdemo.presenter;

import com.allen.mvpdemo.model.MainDao;
import com.allen.mvpdemo.model.NewsBean;
import com.allen.mvpdemo.view.MainView;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 *
 * Created by Allen on 2017/3/22.
 */

public class MainPresenterImp implements BasePresenter {

    private MainView mView;
    private MainDao mDao;
    private CompositeDisposable compositeDisposable;
    private int pageIndex;
    private final int limit = 5;
    private static final String type = "tech";
    private boolean isLastPage = false;


    public MainPresenterImp(MainView view) {
        this.mView = view;
        this.mDao = new MainDao();
    }

    public void init() {
        compositeDisposable = new CompositeDisposable();
        pageIndex = 1;
        requestNews(pageIndex);
    }

    public void lastPage() {

        if (pageIndex == 1) {
            this.mView.toast("已经是首页");
        } else {
            pageIndex--;
            requestNews(pageIndex);
        }
    }

    public void nextPage() {
        if (isLastPage) {
            this.mView.toast("已经是最后一页");
        } else {
            pageIndex++;
            requestNews(pageIndex);
        }
    }

    /**
     * 请求新闻列表
     *
     * @param page 当前页
     */
    private void requestNews(int page) {

        this.mDao.getNewsList(type, page, limit, new Observer<NewsBean>() {

            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
                mView.showProgress();
            }

            @Override
            public void onNext(NewsBean newsBean) {
                isLastPage = limit > newsBean.size;
                mView.showNewsList(newsBean.list);
            }

            @Override
            public void onError(Throwable e) {
                mView.toast(e.getMessage());
                mView.dismissProgress();
            }

            @Override
            public void onComplete() {
                mView.dismissProgress();
            }
        });
    }

    @Override
    public void recycle() {
        this.compositeDisposable.clear();
        this.compositeDisposable = null;
        this.mView = null;
        this.mDao = null;
    }
}
