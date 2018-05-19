package com.fire.firenews.db;

import com.fire.firenews.R;
import com.fire.firenews.api.ApiConstants;
import com.fire.firenews.app.AppApplication;
import com.fire.firenews.bean.NewsChannelTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xiongxueyong on 18/5/16.
 */

public class NewsChannelTableManager {
    /**
     * 加载固定新闻类型
     * @return
     */
    public static List<NewsChannelTable> loadNewsChannelsStatic() {
        List<String> channelName = Arrays.asList(AppApplication.getAppContext().getResources().getStringArray(R.array.news_channel_name_static));
        List<String> channelId = Arrays.asList(AppApplication.getAppContext().getResources().getStringArray(R.array.news_channel_id_static));
        ArrayList<NewsChannelTable> newsChannelTables=new ArrayList<>();
        for (int i = 0; i < channelName.size(); i++) {
            NewsChannelTable entity = new NewsChannelTable(channelName.get(i), channelId.get(i)
                    , ApiConstants.getType(channelId.get(i)), i <= 5, i, true);
            newsChannelTables.add(entity);
        }
        return newsChannelTables;
    }
}
