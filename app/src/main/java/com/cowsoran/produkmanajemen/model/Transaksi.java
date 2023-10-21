package com.cowsoran.produkmanajemen.model;

import java.util.Date;

public class Transaksi {
    private String namaProduk;
    private Date waktu;
    private String jenisTransaksi;

    public Transaksi(String namaProduk, Date waktu, String jenisTransaksi) {
        this.namaProduk = namaProduk;
        this.waktu = waktu;
        this.jenisTransaksi = jenisTransaksi;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public Date getWaktu() {
        return waktu;
    }

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }
}

