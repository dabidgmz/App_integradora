package com.example.app_integradora.Modelos;
import com.google.gson.annotations.SerializedName;

public class Modeltriggers {
    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("value")
    private String value;

    @SerializedName("device_type")
    private String deviceType;

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceType() {
        return deviceType;
    }
}
