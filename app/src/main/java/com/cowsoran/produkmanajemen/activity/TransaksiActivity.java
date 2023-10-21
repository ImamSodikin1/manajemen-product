package com.cowsoran.produkmanajemen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.cowsoran.produkmanajemen.MainActivity;
import com.cowsoran.produkmanajemen.R;
import com.cowsoran.produkmanajemen.adapter.TransaksiAdapter;
import com.cowsoran.produkmanajemen.helpers.DatabaseHelper;
import com.cowsoran.produkmanajemen.model.Product;
import com.cowsoran.produkmanajemen.model.Transaksi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransaksiActivity extends AppCompatActivity {

    private ListView transaksiListView;
    private TransaksiAdapter transaksiAdapter;
    private List<Transaksi> transaksiList;
    private DatabaseHelper dbHelper;
    private ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        ivBack  = findViewById(R.id.ivBackTransaksi);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TransaksiActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        transaksiListView = findViewById(R.id.listTransaksi);
        transaksiList = new ArrayList<>();
        dbHelper = new DatabaseHelper(this);

        // Mendapatkan semua data produk dari database
        List<Product> productList = dbHelper.getAllProducts();

        // Membuat objek Transaksi dari data produk
        for (Product product : productList) {
            Transaksi transaksiMasuk = new Transaksi(product.getName(), new Date(), "Masuk");
            transaksiList.add(transaksiMasuk);
        }

        // Membuat adapter dan mengatur data ke dalam ListView
        transaksiAdapter = new TransaksiAdapter(this, transaksiList);
        transaksiListView.setAdapter(transaksiAdapter);
    }
}