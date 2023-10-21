package com.cowsoran.produkmanajemen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.cowsoran.produkmanajemen.MainActivity;
import com.cowsoran.produkmanajemen.R;
import com.cowsoran.produkmanajemen.adapter.ProductAdapter;
import com.cowsoran.produkmanajemen.helpers.DatabaseHelper;
import com.cowsoran.produkmanajemen.model.Product;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity  {

    private ListView productsListView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private DatabaseHelper dbHelper;
    private ImageView ivBack;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ivBack = findViewById(R.id.ivBackDasborad);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });


        productsListView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);


        // Retrieve all products from the database
        productList = dbHelper.getAllProducts();

        // Create an adapter to populate the ListView
        productAdapter = new ProductAdapter(this, productList);
        productsListView.setAdapter(productAdapter);


    }


}