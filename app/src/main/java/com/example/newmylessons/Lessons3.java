package com.example.newmylessons;

public class Lessons3 {
    private int id;
    private String dersadi;
    private String saat;
    private String dakika;
    private String gun;
    private int devamsizlik;
    private int maxdevamsizlik;

    public int getDevamsizlik() {
        return devamsizlik;
    }

    public void setDevamsizlik(int devamsizlik) {
        this.devamsizlik = devamsizlik;
    }

    public int getMaxdevamsizlik() {
        return maxdevamsizlik;
    }

    public void setMaxdevamsizlik(int maxdevamsizlik) {
        this.maxdevamsizlik = maxdevamsizlik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getDakika() {
        return dakika;
    }

    public void setDakika(String dakika) {
        this.dakika = dakika;
    }

    public String getDersadi() {
        return dersadi;
    }

    public void setDersadi(String dersadi) {
        this.dersadi = dersadi;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getGun() {
        return gun;
    }

    public void setGun(String gun) {
        this.gun = gun;
    }
}
