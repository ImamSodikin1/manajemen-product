package com.cowsoran.produkmanajemen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cowsoran.produkmanajemen.R;
import com.cowsoran.produkmanajemen.model.Transaksi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TransaksiAdapter extends ArrayAdapter<Transaksi> {

    public TransaksiAdapter(Context context, List<Transaksi> transaksiList) {
        super(context, 0, transaksiList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_transaksi, parent, false);
        }

        Transaksi transaksi = getItem(position);

        TextView namaProdukTextView = convertView.findViewById(R.id.tvNamaProdukTransaksi);
        TextView waktuTransaksiTextView = convertView.findViewById(R.id.tvWaktuTransaksi);
        TextView jenisTransaksiTextView = convertView.findViewById(R.id.tvJenisTransaksi);

        namaProdukTextView.setText(transaksi.getNamaProduk());
        waktuTransaksiTextView.setText("Waktu: " + getFormattedTimestamp(transaksi.getWaktu()));
        jenisTransaksiTextView.setText("Jenis Transaksi: " + transaksi.getJenisTransaksi());

        return convertView;
    }

    private String getFormattedTimestamp(Date waktu) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return dateFormat.format(waktu);
    }
}

