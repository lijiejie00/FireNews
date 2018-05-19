package com.fire.firenews.api;

import com.fire.firenews.bean.NewsSummary;
import com.fire.firenews.mocktest.anno.Mock;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by xiongxueyong on 18/5/16.
 */

public interface ApiService {

    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String, List<NewsSummary>>> getNewsList(
//            @Header("Cache-Control") String cacheControl,
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);


    @Mock(assets = "mock/{id}.json")
    @GET("Android/10/6/{id}")
    Observable<List<NewsSummary>> getAticleList( @Path("id") String id);

}
