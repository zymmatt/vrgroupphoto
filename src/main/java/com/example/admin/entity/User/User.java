package com.example.admin.entity.User;

public class User {
    private int id;  //序号1,2,3,4,5
    private String userid;  //随机用户ID名
    private String name;
    private String company;
    private String line;
    private String email;
    private long trigger_timestamp; // 发出申请的时间戳
    private String trigger_time;
    private String photoURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public long getTrigger_timestamp() {
        return trigger_timestamp;
    }

    public void setTrigger_timestamp(long trigger_timestamp) {
        this.trigger_timestamp = trigger_timestamp;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getTrigger_time() {
        return trigger_time;
    }

    public void setTrigger_time(String trigger_time) {
        this.trigger_time = trigger_time;
    }
}
