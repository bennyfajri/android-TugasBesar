package com.benny.tugasbesar;

public class Pegawai {
    private String id;
    private String nama;
    private String jekel;
    private String tgl_lahir;
    private String daerah_asal;
    private String status;
    private String posisi;
    private String gambar;

    public Pegawai(String id,String nama, String jekel,String tgl_lahir,String daerah_asal,String status,String posisi,String gambar){
        this.id = id;
        this.nama = nama;
        this.jekel = jekel;
        this.tgl_lahir = tgl_lahir;
        this.daerah_asal = daerah_asal;
        this.status = status;
        this.posisi = posisi;
        this.gambar = gambar;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }


    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJekel() {
        return jekel;
    }

    public void setJekel(String jekel) {
        this.jekel = jekel;
    }

    public String getDaerah_asal() {
        return daerah_asal;
    }

    public void setDaerah_asal(String daerah_asal) {
        this.daerah_asal = daerah_asal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }
}
