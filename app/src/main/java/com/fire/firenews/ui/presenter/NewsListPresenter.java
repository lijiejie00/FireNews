package com.fire.firenews.ui.presenter;

import com.fire.firenews.R;
import com.fire.firenews.app.AppConstant;
import com.fire.firenews.bean.NewsSummary;
import com.fire.firenews.ui.contract.NewsListContract;
import com.jaydenxiao.common.baserx.RxSubscriber;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by xiongxueyong on 18/5/16.
 */

public class NewsListPresenter extends NewsListContract.Presenter {
    @Override
    public void onStart() {
        super.onStart();
        //监听返回顶部动作
        mRxManage.on(AppConstant.NEWS_LIST_TO_TOP, new Action1<Object>() {
            @Override
            public void call(Object o) {
                mView.scrolltoTop();
            }
        });
    }

    /**
     * 请求获取列表数据
     * @param type
     * @param id
     * @param startPage
     */
    @Override
    public void getNewsListDataRequest(String type, String id, int startPage) {
        mRxManage.add(mModel.getNewsListData(type,id,startPage).subscribe(new RxSubscriber<List<NewsSummary>>(mContext,false) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }

            @Override
            protected void _onNext(List<NewsSummary> newsSummaries) {
                mView.returnNewsListData(newsSummaries);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
