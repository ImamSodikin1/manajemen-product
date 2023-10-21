package com.cowsoran.produkmanajemen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cowsoran.produkmanajemen.R;
import com.cowsoran.produkmanajemen.helpers.DatabaseHelper;
import com.cowsoran.produkmanajemen.model.Product;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class LineChartActivity extends AppCompatActivity {

    private List<Product> productList;
    private LineChart lineChart;
    private DatabaseHelper databaseHelper;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        ivBack = findViewById(R.id.ivbackLine);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LineChartActivity.this, MenuStatistikActivity.class);
                startActivity(i);
                finish();
            }
        });

        databaseHelper = new DatabaseHelper(this);
        productList = databaseHelper.getAllProducts();

        lineChart = findViewById(R.id.lineChart);

        LineData lineData = createLineData();
        lineChart.setData(lineData);
        lineChart.setNoDataText("Tidak ada data yang tersedia.");
        lineChart.setDrawGridBackground(false);

        Description description = new Description();
        description.setText("Stock Produk");
        lineChart.setDescription(description);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setDrawGridLines(true);

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);

        lineChart.getLegend().setEnabled(false);
        lineChart.invalidate();

    }


    private LineData createLineData() {
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            entries.add(new Entry(i, databaseHelper.getProductCount(product.getName())));
            labels.add(product.getName());
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setCircleHoleRadius(2f);
        lineDataSet.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        lineDataSet.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        lineDataSet.setDrawValues(true);
        lineDataSet.setValueTextSize(12f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData lineData = new LineData(dataSets);
        lineData.setDrawValues(false);
        lineData.setHighlightEnabled(false);

        return lineData;
    }

}