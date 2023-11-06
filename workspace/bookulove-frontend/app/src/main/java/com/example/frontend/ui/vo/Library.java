package com.example.frontend.ui.vo;

import com.google.android.gms.maps.model.LatLng;

public class Library {
    String userId;
    LatLng libPos;
    String title;
    String detail;
    public Library(String userId, LatLng libPos, String title, String detail){
        this.userId = userId;
        this.libPos = libPos;
        this.title = title;
        this.detail = detail;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setLibPos(LatLng libPos) {
        this.libPos = libPos;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUserId() {
        return userId;
    }
    public LatLng getLibPos() {
        return libPos;
    }
    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

}
