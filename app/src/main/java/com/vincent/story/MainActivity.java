package com.vincent.story;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.fujianlian.klinechart.KLineChartView;


public class MainActivity extends AppCompatActivity {

    private KLineChartView kLineChartView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kLineChartView = findViewById(R.id.kLineChartView);

    }
}
