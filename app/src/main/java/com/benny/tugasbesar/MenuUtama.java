package com.benny.tugasbesar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MenuUtama extends Activity {
    FrameLayout layoutPegawai;
    FrameLayout layoutGaji;
    FrameLayout layoutProduk;
    FrameLayout layoutLokasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        layoutPegawai = findViewById(R.id.fl_pegawai);
        layoutGaji = findViewById(R.id.fl_gaji);
        layoutProduk = findViewById(R.id.fl_produk);
        layoutLokasi = findViewById(R.id.fl_lokasi);

        layoutPegawai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent(MenuUtama.this,MenuPegawai.class);
                startActivity(intent_1);
            }
        });
        layoutGaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_2 = new Intent(MenuUtama.this,ActivityHitungGaji.class);
                startActivity(intent_2);
            }
        });
        layoutProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_3 = new Intent(MenuUtama.this,ActivityProduk.class);
                startActivity(intent_3);
            }
        });
        layoutLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_4 = new Intent(MenuUtama.this,ActivityLokasi.class);
                startActivity(intent_4);
            }
        });
    }
}
