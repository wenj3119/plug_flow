package com.douwen.top.controller;

import com.douwen.top.config.LiveConfig;
import com.douwen.top.model.Result;
import com.douwen.top.service.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
@RequestMapping("/live")
public class liveController {

    @Autowired
    LiveService liveService;

    @PostMapping("/plug_flow")
    public Result<String> plug_flow(
            @RequestBody LiveConfig live
    ) {

        liveService.plug_flow(
                live
        );
        return Result.success(null);
    }

    @PostMapping("/stop")
    public Result<String> plug_flow(
            @RequestParam("threadName") String threadName
    ) {

        liveService.stop(
                threadName
        );
        return Result.success(null);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("baseDataPath") String baseDataPath
    ) {
        // 检查文件是否为空
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload");
        }
        try {
            liveService.uploadFile(file,baseDataPath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
        return ResponseEntity.ok("File uploaded successfully");
    }

}
