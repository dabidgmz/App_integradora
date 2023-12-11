package com.example.app_integradora.Modelos;

import com.google.gson.annotations.SerializedName;

public class Modelprincipal {
    @SerializedName("value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

