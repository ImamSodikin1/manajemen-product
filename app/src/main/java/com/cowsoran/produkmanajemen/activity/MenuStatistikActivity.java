package com.cowsoran.produkmanajemen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cowsoran.produkmanajemen.MainActivity;
import com.cowsoran.produkmanajemen.R;

public class MenuStatistikActivity extends AppCompatActivity {

    private Button btnBar;
    private Button btnPie;
    private Button btnLine;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_statistik);

        ivBack = findViewById(R.id.ivBakStatistik);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuStatistikActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnBar = findViewById(R.id.btnBarChart);
        btnBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuStatistikActivity.this, StatistikActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnPie = findViewById(R.id.btnPieChart);
        btnPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuStatistikActivity.this, PieChartActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnLine = findViewById(R.id.btnGrafikChart);
        btnLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuStatistikActivity.this, LineChartActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}