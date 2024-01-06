package com.douwen.top.config;



public class LiveConfig {

    private String rtmp;

    private String videoStreamingBitRate = "4000k";

    private String audioStreamingBitRate = "192k";

    private String baseDataPath = "/data/bilibili";

    public LiveConfig() {
    }

    public LiveConfig(String rtmp, String baseDataPath) {
        this.rtmp = rtmp;
        this.baseDataPath = baseDataPath;
    }

    public LiveConfig(String rtmp, String videoStreamingBitRate, String audioStreamingBitRate, String baseDataPath) {
        this.rtmp = rtmp;
        this.videoStreamingBitRate = videoStreamingBitRate;
        this.audioStreamingBitRate = audioStreamingBitRate;
        this.baseDataPath = baseDataPath;
    }

    public String getVideoStreamingBitRate() {
        return videoStreamingBitRate;
    }

    public void setVideoStreamingBitRate(String videoStreamingBitRate) {
        this.videoStreamingBitRate = videoStreamingBitRate;
    }

    public String getAudioStreamingBitRate() {
        return audioStreamingBitRate;
    }

    public void setAudioStreamingBitRate(String audioStreamingBitRate) {
        this.audioStreamingBitRate = audioStreamingBitRate;
    }

    public String getBaseDataPath() {
        return baseDataPath;
    }

    public void setBaseDataPath(String baseDataPath) {
        this.baseDataPath = baseDataPath;
    }

    public String getRtmp() {
        return rtmp;
    }

    public void setRtmp(String rtmp) {
        this.rtmp = rtmp;
    }
}
