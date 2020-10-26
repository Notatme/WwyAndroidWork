package com.wwy.mysigninappnewest.pojo;

import cn.bmob.v3.BmobObject;

public class Teacher extends BmobObject {
    private String number;
    private  String name;
    private String password;
    private String selfNumber;
    private String schoolname;
    private String major;
    private  String  Tqiandaocode;

    public Teacher(){}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSelfNumber() {
        return selfNumber;
    }

    public void setSelfNumber(String selfNumber) {
        this.selfNumber = selfNumber;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTqiandaocode() {
        return Tqiandaocode;
    }

    public void setTqiandaocode(String tqiandaocode) {
        Tqiandaocode = tqiandaocode;
    }

    public Teacher(String number, String name, String password, String selfNumber, String schoolname, String major, String tqiandaocode) {
        this.number = number;
        this.name = name;
        this.password = password;
        this.selfNumber = selfNumber;
        this.schoolname = schoolname;
        this.major = major;
        Tqiandaocode = tqiandaocode;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", selfNumber='" + selfNumber + '\'' +
                ", schoolname='" + schoolname + '\'' +
                ", major='" + major + '\'' +
                ", Tqiandaocode='" + Tqiandaocode + '\'' +
                '}';
    }
}
