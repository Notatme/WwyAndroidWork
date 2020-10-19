package com.wwy.mysigninappnewest.pojo;

import cn.bmob.v3.BmobObject;

public class Student extends BmobObject {
private String number;
private  String name;
private String password;
private String selfNumber;
private String schoolname;
private String major;
private  String Adress;
private  String qiandaocode;


    //st_number   学号
    //username_register  姓名
    //password_register   密码
    //phone_register    //手机号码
    //school_register    //学校，名称
    //register_btn    //注册


public Student() {

}

    public Student(String tableName, String number, String name, String password, String selfNumber, String schoolname, String major, String adress, String qiandaocode) {
        super(tableName);
        this.number = number;
        this.name = name;
        this.password = password;
        this.selfNumber = selfNumber;
        this.schoolname = schoolname;
        this.major = major;
        Adress = adress;
        this.qiandaocode = qiandaocode;
    }

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

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getQiandaocode() {
        return qiandaocode;
    }

    public void setQiandaocode(String qiandaocode) {
        this.qiandaocode = qiandaocode;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    //st_number   学号
    //username_register  姓名
    //password_register   密码
    //phone_register    //手机号码
    //school_register    //学校，名称
    //register_btn    //注册


    @Override
    public String toString() {
        return "Student{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", selfNumber='" + selfNumber + '\'' +
                ", schoolname='" + schoolname + '\'' +
                ", major='" + major + '\'' +
                ", Adress='" + Adress + '\'' +
                ", qiandaocode='" + qiandaocode + '\'' +
                '}';
    }
}
