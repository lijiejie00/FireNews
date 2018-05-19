package com.fire.firenews.ui.contract;

import com.fire.firenews.bean.NewsSummary;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import java.util.List;

import rx.Observable;

/**
 *  des:新闻列表contract
 * Created by xiongxueyong on 18/5/16.
 */

public interface NewsListContract {

    interface Model extends BaseModel {
        //请求获取新闻
        Observable<List<NewsSummary>> getNewsListData(String type, final String id, int startPage);
    }

    interface View extends BaseView {
        //返回获取的新闻
        void returnNewsListData(List<NewsSummary> newsSummaries);
        //返回顶部
        void scrolltoTop();
    }
    public abstract static class Presenter extends BasePresenter<View, Model> {
        //发起获取新闻请求
        public abstract void getNewsListDataRequest(String type, final String id, int startPage);
    }
}
