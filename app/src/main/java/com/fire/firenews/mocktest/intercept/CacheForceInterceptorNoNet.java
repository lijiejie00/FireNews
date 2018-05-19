package com.fire.firenews.mocktest.intercept;


import com.fire.firenews.mocktest.NetUtils;
import com.fire.firenews.mocktest.RetrofitCache;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by bernie on 2017/6/13.
 */

public class CacheForceInterceptorNoNet extends BaseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response mockResponse = mockResponse(chain);
        if (mockResponse != null) {
            return mockResponse;
        }
        String url = getOriginUrl(request.url().url().toString());
//        boolean forceCacheNoNet =  RetrofitCache.getInstance().getCacheTime(url).isForceCacheNoNet();
        if (/*forceCacheNoNet&&*/!NetUtils.isConnectNet(RetrofitCache.getInstance().getContext())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();

        }

        String mockUrl = mockUrl(chain);
        if (mockUrl != null) {
            request = request.newBuilder().url(mockUrl).header(KEY_HEADER_PRE_URL, request.url().toString())
                    .build();
        }
        Response response = chain.proceed(request);
        if (response.code() == 504) {
            response = chain.proceed(chain.request());
        }
        return response;
    }
}
