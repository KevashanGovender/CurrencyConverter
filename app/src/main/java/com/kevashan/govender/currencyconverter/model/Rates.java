package com.kevashan.govender.currencyconverter.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rates implements Serializable
{

    @SerializedName("AUD")
    @Expose
    private Double aUD;
    @SerializedName("CAD")
    @Expose
    private Double cAD;
    @SerializedName("SEK")
    @Expose
    private Double sEK;
    @SerializedName("USD")
    @Expose
    private Double uSD;
    @SerializedName("ZAR")
    @Expose
    private Double zAR;

    public Double getaUD() {
        return aUD;
    }

    public void setaUD(Double aUD) {
        this.aUD = aUD;
    }

    public Double getcAD() {
        return cAD;
    }

    public void setcAD(Double cAD) {
        this.cAD = cAD;
    }

    public Double getsEK() {
        return sEK;
    }

    public void setsEK(Double sEK) {
        this.sEK = sEK;
    }

    public Double getuSD() {
        return uSD;
    }

    public void setuSD(Double uSD) {
        this.uSD = uSD;
    }

    public Double getzAR() {
        return zAR;
    }

    public void setzAR(Double zAR) {
        this.zAR = zAR;
    }
}



