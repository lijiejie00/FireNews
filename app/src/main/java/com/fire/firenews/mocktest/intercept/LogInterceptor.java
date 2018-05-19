package com.fire.firenews.mocktest.intercept;

import android.util.Log;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by xiongxueyong on 18/5/18.
 */

public class LogInterceptor implements Interceptor {


    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        StringBuffer sb = new StringBuffer();


        okhttp3.Response response = chain.proceed(chain.request());
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();


        sb.append("======== request: " + request.toString() + "\r\n ======== request headers: " + request.headers().toString() + "\r\n======= response header:" + response.headers().toString() + "\r\n---------- response body:\r\n");
        try {
            showLog(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }


    private static void showLog(String str) {
        str = str.trim();
        int index = 0;
        int maxLength = 2000;
        String finalString = "";

        while (index < str.length()) {
            if (str.length() <= index + maxLength) {
                finalString = str.substring(index);
            } else {
                finalString = str.substring(index, index + maxLength);
            }
            index += maxLength;
            Log.i("", finalString.trim());
        }
    }
}
