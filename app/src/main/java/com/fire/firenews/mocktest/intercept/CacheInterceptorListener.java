package com.fire.firenews.mocktest.intercept;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bernie on 2017/10/20.
 */

public interface CacheInterceptorListener {

    boolean canCache(Request request, Response response);
}
