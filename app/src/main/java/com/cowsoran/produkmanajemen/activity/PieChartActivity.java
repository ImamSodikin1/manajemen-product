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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {

    private List<Product> productList;
    private PieChart chart;
    private DatabaseHelper databaseHelper;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        ivBack = findViewById(R.id.ivBackPieChart);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PieChartActivity.this, MenuStatistikActivity.class);
                startActivity(i);
                finish();
            }
        });

        databaseHelper = new DatabaseHelper(this);

        // Ambil data produk dari DatabaseHelper
        productList = databaseHelper.getAllProducts();

        // Inisialisasi chart
        chart = findViewById(R.id.pie_chart);

        // Buat dataset untuk chart
        PieDataSet dataSet = createPieDataSet();

        // Atur konfigurasi chart
        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(12f);
        chart.setData(pieData);
        chart.getDescription().setEnabled(false);
        chart.setDrawEntryLabels(false);
        chart.setUsePercentValues(true);
        chart.setHoleRadius(50f);
        chart.setTransparentCircleRadius(55f);
        chart.setDrawCenterText(true);
        chart.setCenterText("Stock Produk");
        chart.setCenterTextSize(14f);
        chart.setCenterTextColor(Color.BLACK);
        chart.setEntryLabelColor(Color.BLACK);
        chart.setEntryLabelTextSize(12f);
        chart.getLegend().setEnabled(false);
        chart.invalidate();
    }

    private PieDataSet createPieDataSet() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            entries.add(new PieEntry(databaseHelper.getProductCount(product.getName()), product.getName()));
            colors.add(getColorForProduct(product.getName()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueLineColor(Color.WHITE);
        dataSet.setValueLineWidth(0.5f);

        return dataSet;
    }

    private int getColorForProduct(String productName) {
        // Dapatkan indeks warna berdasarkan nama produk
        int colorIndex = productName.hashCode() % ColorTemplate.COLORFUL_COLORS.length;
        if (colorIndex < 0) {
            colorIndex += ColorTemplate.COLORFUL_COLORS.length;
        }
        return ColorTemplate.COLORFUL_COLORS[colorIndex];
    }
}