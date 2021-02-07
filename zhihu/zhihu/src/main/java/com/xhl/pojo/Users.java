package com.xhl.pojo;

import java.util.Date;

public class Users {
    private Integer id;
    private String uphone;
    private String uemail;
    private String upwd;
    private String uname;
    private Date creat_time;
    private Date update_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Date getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(Date creat_time) {
        this.creat_time = creat_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", uphone='" + uphone + '\'' +
                ", uemail='" + uemail + '\'' +
                ", upwd='" + upwd + '\'' +
                ", uname='" + uname + '\'' +
                ", creat_time=" + creat_time +
                ", update_time=" + update_time +
                '}';
    }
}
