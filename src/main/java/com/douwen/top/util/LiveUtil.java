package com.douwen.top.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LiveUtil {

    public static boolean livePlugFlow(
            String baseDataPath,
            String rtmp
    ) throws IOException, InterruptedException {

        List<String> videos = getVideos(baseDataPath, new ArrayList<>());
        for (String video : videos) {
            // Create the ffmpeg command
            // -preset ultrafast -vcodec libx264 -g 25 -b:v "4000k" -c:a aac -b:a "192k" -strict -2 -f flv
            String ffmpegCommand = String.format(
                    "ffmpeg -re -i %s -c:v libx264 -crf 23 -preset slow  -c:a aac -b:a 192k -vf scale=1280:720 -f flv %s",
                    video,
                    rtmp
            );

            // Execute the ffmpeg command
            Process process = Runtime.getRuntime().exec(ffmpegCommand);

            // Wait for the process to finish
            process.waitFor();
        }
        return true;
    }



    private static List<String> getVideos(
            String path,
            List<String> videos
    ){
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if(f.isFile()){
                    videos.add(f.getAbsolutePath());
                }else {
                    getVideos(f.getPath(),videos);
                }
            }
        }
        return videos;
    }
}
