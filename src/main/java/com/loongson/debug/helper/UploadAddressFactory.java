package com.loongson.debug.helper;


public class UploadAddressFactory {
    private String uploadAddress;
    private static UploadAddressFactory instance;
    private UploadAddressFactory(){
        uploadAddress = "";
    }
    public static UploadAddressFactory getInstance(){
        if (instance == null) {
            instance = new UploadAddressFactory();
        }
        return instance;
    }

    public String getUploadAddress() {
        return uploadAddress;
    }

    public void setUploadAddress(String uploadAddress) {
        this.uploadAddress = uploadAddress;
    }

    public static void setInstance(UploadAddressFactory instance) {
        UploadAddressFactory.instance = instance;
    }
}
