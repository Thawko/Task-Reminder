package com.example.afinal;

public class Hatirlatma {
    private int ID;
    private String baslik,detay;
    private boolean tamamlandi;

    public Hatirlatma() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getDetay() {
        return detay;
    }

    public void setDetay(String detay) {
        this.detay = detay;
    }

    public boolean isTamamlandi() {
        return tamamlandi;
    }

    public void setTamamlandi(boolean tamamlandi) {
        this.tamamlandi = tamamlandi;
    }
}
