package com.wwy.mysigninappnewest;

// 签到记录每一项的数据
public class TeacherRegistrationItem {
    private String registrationName;
    private String registrationCreator;
    private String registrationNum;

    public String getRegistrationName() {
        return registrationName;
    }

    public void setRegistrationName(String registrationName) {
        this.registrationName = registrationName;
    }

    public String getRegistrationCreator() {
        return registrationCreator;
    }

    public void setRegistrationCreator(String registrationCreator) {
        this.registrationCreator = registrationCreator;
    }

    public String getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }
}
