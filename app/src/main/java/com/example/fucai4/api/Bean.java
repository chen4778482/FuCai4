package com.example.fucai4.api;

/**
 * Created by 42224 on 2018/4/24.
 */

public class Bean {

    /**
     * status : failed
     * info : 密钥或机器码对应！
     * url :
     * data : []
     */

    private String status;
    private String info;
    private String url;
    private Bea data;

    public Bea getData() {
        return data;
    }

    public void setData(Bea data) {
        this.data = data;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public class Bea{
       private String expiry_time;

        public String getExpiry_time() {
            return expiry_time;
        }

        public void setExpiry_time(String expiry_time) {
            this.expiry_time = expiry_time;
        }
    }
}
