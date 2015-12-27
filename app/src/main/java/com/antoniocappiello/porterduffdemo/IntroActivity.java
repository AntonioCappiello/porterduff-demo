package com.antoniocappiello.porterduffdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntroActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.demoWithColorFilter)
    public void showDemoWithColorFilter(View view){
        startActivity(new Intent(this, PorterDuffWithColorFilterActivity.class));
    }

    @OnClick(R.id.demoWithImage)
    public void showDemoWithImage(View view){
        startActivity(new Intent(this, PorterDuffWithImageActivity.class));
    }
}
