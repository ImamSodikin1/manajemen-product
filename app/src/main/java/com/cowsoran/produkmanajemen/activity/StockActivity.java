package com.cowsoran.produkmanajemen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.cowsoran.produkmanajemen.MainActivity;
import com.cowsoran.produkmanajemen.R;
import com.cowsoran.produkmanajemen.adapter.StockAdapter;
import com.cowsoran.produkmanajemen.helpers.DatabaseHelper;
import com.cowsoran.produkmanajemen.model.Product;

import java.util.ArrayList;
import java.util.List;

public class StockActivity extends AppCompatActivity {

    private ListView stockListView;
    private StockAdapter stockAdapter;
    private List<Product> productList;
    private DatabaseHelper dbHelper;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        ivBack = findViewById(R.id.ivBackStock);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StockActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        stockListView = findViewById(R.id.listStock);
        productList = new ArrayList<>();
        dbHelper = new DatabaseHelper(this);

        // Mendapatkan semua produk dari database
        productList = dbHelper.getAllProducts();

        // Membuat adapter dan mengatur data ke dalam ListView
        stockAdapter = new StockAdapter(this, productList);
        stockListView.setAdapter(stockAdapter);
    }
}