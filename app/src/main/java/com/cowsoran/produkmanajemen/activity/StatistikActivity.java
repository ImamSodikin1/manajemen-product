package com.cowsoran.produkmanajemen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cowsoran.produkmanajemen.R;
import com.cowsoran.produkmanajemen.helpers.DatabaseHelper;
import com.cowsoran.produkmanajemen.model.Product;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class StatistikActivity extends AppCompatActivity {

    private List<Product> productList;
    private BarChart chart;
    private DatabaseHelper databaseHelper;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);

        ivBack = findViewById(R.id.ivBackBar);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StatistikActivity.this, MenuStatistikActivity.class);
                startActivity(i);
                finish();
            }
        });

        databaseHelper = new DatabaseHelper(this);

        // Ambil data produk dari DatabaseHelper
        productList = databaseHelper.getAllProducts();

        // Inisialisasi chart
        chart = findViewById(R.id.chart);

        // Buat dataset untuk chart
        List<IBarDataSet> dataSet = createBarDataSets();

        // Atur konfigurasi chart
        BarData barData = new BarData(dataSet);
        chart.setData(barData);
        chart.setFitBars(true);
        chart.getDescription().setEnabled(false);
        chart.invalidate();

        // Atur konfigurasi sumbu X
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getProductNames()));



    }


    private List<IBarDataSet> createBarDataSets() {
        List<IBarDataSet> dataSets = new ArrayList<>();

        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);

            ArrayList<BarEntry> entries = new ArrayList<>();
            entries.add(new BarEntry(i, databaseHelper.getProductCount(product.getName())));

            BarDataSet dataSet = new BarDataSet(entries, product.getName());
            dataSet.setColor(getRandomColor());
            dataSet.setValueTextSize(12f);
            dataSet.setValueTextColor(Color.WHITE);
            dataSet.setDrawValues(true); // Tampilkan teks

            dataSets.add(dataSet);
        }

        return dataSets;
    }

    private ArrayList<String> getProductNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Product product : productList) {
            names.add(product.getName());
        }
        return names;
    }

    private int getRandomColor() {
        // Generate random RGB color
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return Color.rgb(r, g, b);
    }


}