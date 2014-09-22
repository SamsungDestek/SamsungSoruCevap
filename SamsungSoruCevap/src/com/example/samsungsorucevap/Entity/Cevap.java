package com.example.samsungsorucevap.Entity;

import java.util.Date;

public class Cevap {

    private int id;
    private int soruId;
    private int uyeId;
    private String icerik;
    private Date tarih;
    private int puan;
    private String uyeAd;
    private boolean dogruCevap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoruId() {
        return soruId;
    }

    public void setSoruId(int soruId) {
        this.soruId = soruId;
    }

    public int getUyeId() {
        return uyeId;
    }

    public void setUyeId(int uyeId) {
        this.uyeId = uyeId;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public int getPuan() {
        return puan;
    }

    public void setPuan(int puan) {
        this.puan = puan;
    }

    public String getUyeAd() {
        return uyeAd;
    }

    public void setUyeAd(String uyeAd) {
        this.uyeAd = uyeAd;
    }

    public boolean isDogruCevap() {
        return dogruCevap;
    }

    public void setDogruCevap(boolean dogruCevap) {
        this.dogruCevap = dogruCevap;
    }


}
