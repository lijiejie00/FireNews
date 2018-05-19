package com.fire.firenews.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.fire.firenews.R;
import com.fire.firenews.app.AppConstant;
import com.fire.firenews.bean.NewsChannelTable;
import com.fire.firenews.ui.contract.NewsMainContract;
import com.fire.firenews.ui.model.NewsMainModel;
import com.fire.firenews.ui.presenter.NewsMainPresenter;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.base.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by xiongxueyong on 18/5/16.
 */

public class NewsMainFragment extends BaseFragment<NewsMainPresenter, NewsMainModel> implements NewsMainContract.View {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
//    @Bind(R.id.add_channel_iv)
//    ImageView addChannelIv;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
//    @Bind(R.id.fab)
//    FloatingActionButton fab;
    private BaseFragmentAdapter fragmentAdapter;

    @Override
    public void returnMineNewsChannels(List<NewsChannelTable> newsChannelsMine) {
        if (newsChannelsMine!=null) {
            List<String> channelNames = new ArrayList<>();
            List<Fragment> mNewsFragmentList = new ArrayList<>();
            for (int i = 0; i < newsChannelsMine.size(); i++) {

                channelNames.add(newsChannelsMine.get(i).getNewsChannelName());
                mNewsFragmentList.add(createListFragments(newsChannelsMine.get(i)));
            }
            if(fragmentAdapter==null) {
                fragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mNewsFragmentList, channelNames);
            }else{
                //刷新fragment
                fragmentAdapter.setFragments(getChildFragmentManager(),mNewsFragmentList,channelNames);
            }
            viewPager.setAdapter(fragmentAdapter);
            tabs.setupWithViewPager(viewPager);
//            MyUtils.dynamicSetTabLayoutMode(tabs);
            setPageChangeListener();
        }
    }
    private NewsFrament createListFragments(NewsChannelTable newsChannel) {
        NewsFrament fragment = new NewsFrament();
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.NEWS_ID, newsChannel.getNewsChannelId());
        bundle.putString(AppConstant.NEWS_TYPE, newsChannel.getNewsChannelType());
        bundle.putInt(AppConstant.CHANNEL_POSITION, newsChannel.getNewsChannelIndex());
        fragment.setArguments(bundle);
        return fragment;
    }

    private void setPageChangeListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.app_bar_news;
    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        mPresenter.lodeMineChannelsRequest();
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }
}
