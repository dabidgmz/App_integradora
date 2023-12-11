package com.example.app_integradora.Modelos;

import com.google.gson.annotations.SerializedName;

public class Modelprincipal {

        @SerializedName("value")
        private String valorUltra;


    public Modelprincipal(String valorUltra) {
        this.valorUltra = valorUltra;
    }

    public String getValue() {
        return valorUltra;
    }

    public void setValorUltra(String valorUltra) {
    }

    public String getValorUltra() { return valorUltra;

    }
}
