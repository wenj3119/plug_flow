package com.douwen.top.service;


import com.douwen.top.util.TestHttpUtil;
import org.springframework.stereotype.Service;

@Service
public class HttpTestService {

    public void testGetUrl(
            Integer size,
            String httpUrl
    ) {
        for(int i = 0; i <=size; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            TestHttpUtil.testGetUrl(httpUrl);
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            });
            thread.start();
        }
    }

    public void testPostUrl(
            Integer size,
            String httpPostUrl
    ) {
        for(int i = 0; i <=size; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            TestHttpUtil.testPostUrl(httpPostUrl);
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            });
            thread.start();
        }
    }
}
