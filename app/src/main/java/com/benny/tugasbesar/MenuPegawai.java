package com.benny.tugasbesar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;

public class MenuPegawai extends AppCompatActivity {
    FrameLayout layoutList;
    FrameLayout layoutDaftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pegawai);
        getSupportActionBar().setTitle("Pegawai");
        layoutList = findViewById(R.id.fl_list_peg);
        layoutDaftar = findViewById(R.id.fl_daftar_peg);
        layoutList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1= new Intent(MenuPegawai.this,ListPegawai.class);
                startActivity(intent_1);
            }
        });
        layoutDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_2= new Intent(MenuPegawai.this,ActivityInputPegawai.class);
                startActivity(intent_2);
            }
        });
    }
}
