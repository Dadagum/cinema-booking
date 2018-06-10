package com.dadagum.bean;

public class FilmPeriod {
    private int fid;
    private int pid;
    private String start_time;
    private int video_hall;

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public int getVideo_hall() {
        return video_hall;
    }

    public void setVideo_hall(int video_hall) {
        this.video_hall = video_hall;
    }
}
