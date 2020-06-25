package com.benny.tugasbesar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailGaji extends AppCompatActivity {

    TextView txtNama, txtJumlahAnak, txtID,txtGaji;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gaji);

        txtNama = findViewById(R.id.txt_Nama_detail);
        txtJumlahAnak = findViewById(R.id.txt_JumlahAnak_detail);
        txtID = findViewById(R.id.txt_ID_detail);
        txtGaji = findViewById(R.id.txt_gaji_detail);

        Bundle bundle = getIntent().getExtras();
        int jumlahAnak = Integer.parseInt(bundle.getString("jumlah_anak"));
        if (jumlahAnak == 0){
            jumlahAnak = Integer.parseInt("Tidak ada Anak");
        }
        txtID.setText("ID pegawai : " + bundle.getString("id"));
        txtNama.setText("Nama       : " + bundle.getString("nama"));
        txtJumlahAnak.setText("Jumlah Anak : " + jumlahAnak);
        txtGaji.setText("Gaji anda : " +   bundle.getString("gaji"));

    }
}
