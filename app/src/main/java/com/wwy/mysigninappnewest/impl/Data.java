package com.wwy.mysigninappnewest.impl;


//用以储存用户信息的全局变量
public class Data {

    private static String ObjectId="";
    private static String QRCode="";
    public static String getObjectId() {
        return ObjectId;
    }

    public static void setObjectId(String objectId) {
        Data.ObjectId = objectId;
    }

    public static String getQRCode() {
        return QRCode;
    }

    public static void setQRCode(String QRCode) {
        Data.QRCode = QRCode;
    }
}
