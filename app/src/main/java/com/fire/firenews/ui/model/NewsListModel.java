package com.fire.firenews.ui.model;

import com.fire.firenews.api.Api;
import com.fire.firenews.api.ApiConstants;
import com.fire.firenews.api.HostType;
import com.fire.firenews.mocktest.MockApi;
import com.fire.firenews.bean.NewsSummary;
import com.fire.firenews.mocktest.intercept.RxSchedulers;
import com.fire.firenews.ui.contract.NewsListContract;
import com.jaydenxiao.common.commonutils.TimeUtil;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * des:新闻列表model
 * Created by xiongxueyong on 18/5/16.
 */

public class NewsListModel implements NewsListContract.Model {
    /**
     * 获取新闻列表
     *
     * @param type
     * @param id
     * @param startPage
     * @return
     */
//    @Override
    public Observable<List<NewsSummary>> getNewsListData(final String type, final String id, final int startPage) {
        return Api.getDefault(HostType.NETEASE_NEWS_VIDEO).getNewsList(/*Api.getCacheControl(),*/type, id, startPage)
                .flatMap(new Func1<Map<String, List<NewsSummary>>, Observable<NewsSummary>>() {
                    @Override
                    public Observable<NewsSummary> call(Map<String, List<NewsSummary>> map) {
                        if (id.endsWith(ApiConstants.HOUSE_ID)) {
                            // 房产实际上针对地区的它的id与返回key不同
                            return Observable.from(map.get("北京"));
                        }
                        return Observable.from(map.get(id));
                    }
                })
                //转化时间
                .map(new Func1<NewsSummary, NewsSummary>() {
                    @Override
                    public NewsSummary call(NewsSummary newsSummary) {
                        String ptime = TimeUtil.formatDate(newsSummary.getPtime());
                        newsSummary.setPtime(ptime);
                        return newsSummary;
                    }
                })
                .distinct()//去重
                .toSortedList(new Func2<NewsSummary, NewsSummary, Integer>() {
                    @Override
                    public Integer call(NewsSummary newsSummary, NewsSummary newsSummary2) {
                        return newsSummary2.getPtime().compareTo(newsSummary.getPtime());
                    }
                })
                //声明线程调度
                .compose(RxSchedulers.<List<NewsSummary>>io_main());
    }


//        return MockApi.getDefault(HostType.NETEASE_NEWS_VIDEO)
//                .getAticleList(id)
//                .compose(RxSchedulers.<List<NewsSummary>>IoMain());
//}
}
