package com.fire.firenews;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fire.firenews.app.AppConstant;
import com.fire.firenews.ui.fragment.NewsMainFragment;
import com.jaydenxiao.common.base.BaseActivity;



/**
 * des:
 * Created by bernie
 * on 2018.05.16
 */
public class MainActivity extends BaseActivity {
    private NewsMainFragment newsMainFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化frament
        initFragment(savedInstanceState);
    }



    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            newsMainFragment = (NewsMainFragment) getSupportFragmentManager().findFragmentByTag("newsMainFragment");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            newsMainFragment = new NewsMainFragment();
            transaction.add(R.id.fl_body, newsMainFragment, "newsMainFragment");
        }

        transaction.commit();
//        SwitchTo(currentTabPosition);
//        tabLayout.setCurrentTab(currentTabPosition);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }
}
