package com.xn.manager.model;

/**
 * @Description TODO
 * @Author long
 * @Date 2020/9/16 19:05
 * @Version 1.0
 */
public class ConfigModel {
    /**
     * 外网定制
     */
    private String  foreignAddress;
    /**
     * 内部存储定制
     */
    private String  storeAddress;

    public String getForeignAddress() {
        return foreignAddress;
    }

    public void setForeignAddress(String foreignAddress) {
        this.foreignAddress = foreignAddress;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}
