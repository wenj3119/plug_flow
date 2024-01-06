package com.douwen.top.service;

import com.douwen.top.config.LiveConfig;
import com.douwen.top.util.LiveUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class LiveService {

    private static Map<String, Thread> threadMap = new HashMap<>();

    public void plug_flow(
            LiveConfig live
    ) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        boolean b = LiveUtil.livePlugFlow(
                                live.getBaseDataPath(),
                                live.getRtmp()
                        );
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        });
        thread.start();
        System.out.println("thread: " + thread.getName());
        threadMap.put(thread.getName(), thread);

    }

    public void stop(
            String threadName
    ) {
        Thread thread = threadMap.get(threadName);
        if (Objects.isNull(thread)) {
            return;
        }
        thread.interrupt();
    }

    public void uploadFile(
            MultipartFile file,
            String baseDataPath
    ) throws IOException {
        File file1 = new File(baseDataPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        // 执行文件上传操作，例如保存到磁盘或存储到数据库
        file.transferTo(new File(baseDataPath + File.separator + fileName));
        // 这里只是简单地打印文件名
        System.out.println("Uploaded file name: " + fileName);

    }
}
