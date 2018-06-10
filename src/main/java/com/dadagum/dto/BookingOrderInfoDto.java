package com.dadagum.dto;

public class BookingOrderInfoDto {
    private String name;
    private String director;
    private String actors;
    private double price;
    private double last_len;
    private String type;
    private String introduction;
    private String start_time;
    private int video_hall;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLast_len() {
        return last_len;
    }

    public void setLast_len(double last_len) {
        this.last_len = last_len;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
