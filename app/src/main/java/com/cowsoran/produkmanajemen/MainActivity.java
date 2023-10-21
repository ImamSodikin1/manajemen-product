package com.cowsoran.produkmanajemen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cowsoran.produkmanajemen.activity.DashboardActivity;
import com.cowsoran.produkmanajemen.activity.MenuStatistikActivity;
import com.cowsoran.produkmanajemen.activity.ProdukActivity;
import com.cowsoran.produkmanajemen.activity.StatistikActivity;
import com.cowsoran.produkmanajemen.activity.StockActivity;
import com.cowsoran.produkmanajemen.activity.TransaksiActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView ivProduk;
    private ImageView ivDashboard;
    private ImageView ivStock;
    private ImageView ivTransaksi;
    private ImageView ivStatistik;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivProduk = findViewById(R.id.ivProduct);
        ivProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ProdukActivity.class);
                startActivity(i);
                finish();
            }
        });

        ivDashboard = findViewById(R.id.ivDashboard);
        ivDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(i);
                finish();
            }
        });

        ivStock = findViewById(R.id.ivStock);
        ivStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, StockActivity.class);
                startActivity(i);
                finish();
            }
        });

        ivTransaksi = findViewById(R.id.ivTransaksi);
        ivTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TransaksiActivity.class);
                startActivity(i);
                finish();
            }
        });

        ivStatistik = findViewById(R.id.ivStatistik);
        ivStatistik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MenuStatistikActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}