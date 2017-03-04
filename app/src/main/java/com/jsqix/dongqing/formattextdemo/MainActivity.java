package com.jsqix.dongqing.formattextdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dongqing.formatview.FormatView;

public class MainActivity extends AppCompatActivity {

    private FormatView formatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        formatView = (FormatView) findViewById(R.id.formatView);
        formatView.setSymbolShow(false);
//        formatView.setMoneyText(124.35);

    }
}
