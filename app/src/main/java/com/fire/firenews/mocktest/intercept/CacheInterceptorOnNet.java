package com.fire.firenews.mocktest.intercept;

import android.text.TextUtils;

import com.fire.firenews.mocktest.RetrofitCache;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bernie on 2017/6/13.
 */

public class CacheInterceptorOnNet extends BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response  mockResponse = mockResponse(chain);
        if (mockResponse!=null){
            return mockResponse;
        }
        String url = getOriginUrl(request.url().url().toString());

        String mockPreUrl = request.header(KEY_HEADER_PRE_URL);
        if (!TextUtils.isEmpty(mockPreUrl)){
            url = mockPreUrl;
        }

        Long maxAge = RetrofitCache.getInstance().getCacheTime(url).getTime();
        Response response = chain.proceed(request);
        CacheInterceptorListener listener = (CacheInterceptorListener) RetrofitCache.getInstance().getCacheInterceptorListener();

        if (listener!=null&&!listener.canCache(request,response)){
            return response;
        }
        return   response.newBuilder()
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public,max-age="+maxAge)
                .removeHeader("Pragma")
                .build();
    }
}
