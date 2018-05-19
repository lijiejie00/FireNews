package com.fire.firenews.ui.contract;

import android.database.Observable;

import com.fire.firenews.bean.NewsChannelTable;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import java.util.List;

/**
 * Created by xiongxueyong on 18/5/16.
 */

public interface NewsMainContract {

    interface Model extends BaseModel {
        rx.Observable<List<NewsChannelTable>> lodeMineNewsChannels();
    }

    interface View extends BaseView {
        void returnMineNewsChannels(List<NewsChannelTable> newsChannelsMine);
    }
    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void lodeMineChannelsRequest();
    }
}
