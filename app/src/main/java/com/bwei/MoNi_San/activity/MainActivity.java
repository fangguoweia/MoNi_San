package com.bwei.MoNi_San.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bwei.MoNi_San.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btndonghua = findViewById(R.id.btndonghua);
        Button btnditu = findViewById(R.id.btnditu);
        Button btnQQ = findViewById(R.id.btnQQ);
        Button btnshagnpin = findViewById(R.id.btnshagnpin);
        Button btntouxiang = findViewById(R.id.btntouxiang);
        btndonghua.setOnClickListener(this);
        btnditu.setOnClickListener(this);
        btnQQ.setOnClickListener(this);
        btnshagnpin.setOnClickListener(this);
        btntouxiang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btndonghua:
                Intent intent = new Intent(MainActivity.this, DongHuaActivity.class);
                startActivity(intent);
                break;
            case R.id.btnQQ:
                Intent intent1 = new Intent(MainActivity.this, QQActivity.class);
                startActivity(intent1);
                break;
            case R.id.btntouxiang:
                Intent intent2 = new Intent(MainActivity.this, TouXiangActivity.class);
                startActivity(intent2);
                break;
            case R.id.btnshagnpin:
                Intent intent3 = new Intent(MainActivity.this, LieBiaoActivity.class);
                startActivity(intent3);
                break;
            case R.id.btnditu:
                Intent intent4 = new Intent(MainActivity.this, DiTuActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
