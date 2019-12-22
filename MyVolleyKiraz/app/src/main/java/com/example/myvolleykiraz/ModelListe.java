package com.example.myvolleykiraz;

public class ModelListe {

    private int Id;
    private String DersAdi;
    private int DersKategoriId;
    private String Durum;

    public ModelListe(int id, String dersAdi, int dersKategoriId, String durum) {
        Id = id;
        DersAdi = dersAdi;
        DersKategoriId = dersKategoriId;
        Durum = durum;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDersAdi() {
        return DersAdi;
    }

    public void setDersAdi(String dersAdi) {
        DersAdi = dersAdi;
    }

    public int getDersKategoriId() {
        return DersKategoriId;
    }

    public void setDersKategoriId(int dersKategoriId) {
        DersKategoriId = dersKategoriId;
    }

    public String getDurum() {
        return Durum;
    }

    public void setDurum(String durum) {
        Durum = durum;
    }

    @Override
    public String toString() {
        return "Id=" + Id +
                "/n DersAdi='" + DersAdi + '\'' +
                "/n DersKategoriId=" + DersKategoriId +
                "/n, Durum='" + Durum + '\'' ;
    }
}
