package com.example.app_integradora.Modelos;

import com.google.gson.annotations.SerializedName;

public class Modeltriggers {
    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("value")
    private String value;

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
}
