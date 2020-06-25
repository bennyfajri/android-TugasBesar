package com.benny.tugasbesar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ActivityHitungGaji extends AppCompatActivity {

    Toolbar toolbar;
    EditText edtidpeg,edtjml_anak;
    CheckBox chkpokok,chktranspor,chkMakan,chktunjangan_Anak;
    Button btnSubmit;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String idPegawai;
    int total_gaji;
    int gaji_pokok=0;
    int t_makan=0;
    int t_trans=0;
    int t_anak=0;
    int jumlah_anak;
    final String URL_INPUT_GAJI = "http://10.0.2.2/server_perusahaan/entri_gaji.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung_gaji);




        edtidpeg = findViewById(R.id.edtID);
        edtjml_anak = findViewById(R.id.edtJmlAnak);

        chkpokok = findViewById(R.id.chk_Pokok);
        chktranspor = findViewById(R.id.chk_Transportasi);
        chkMakan = findViewById(R.id.chk_Tunjangan_makan);
        chktunjangan_Anak = findViewById(R.id.chk_Tunjangan_anak);

        btnSubmit = findViewById(R.id.btn_Submit);

        requestQueue = Volley.newRequestQueue(ActivityHitungGaji.this);
        progressDialog = new ProgressDialog(ActivityHitungGaji.this);

        tunjanganAnak();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesInsert();
            }
        });

        actionBarTheme();


    }

    private void actionBarTheme() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#77CDD5")));
        actionBar.setTitle("Hitung Gaji");
    }

    private void prosesInsert() {
        progressDialog.show();
        idPegawai = edtidpeg.getText().toString().trim();
        jumlah_anak = Integer.parseInt(edtjml_anak.getText().toString());
        String g_pokok = "Tidak";
        String g_makan="Tidak";
        String g_transpor="Tidak";
        String g_anak = "Tidak";
        if(chkpokok.isChecked()){
            gaji_pokok = 5000000;
            g_pokok = "Ya (Rp. 5.000.000,00)";
        }
        if (chktranspor.isChecked()){
            t_trans = 2000000;
            g_transpor = "Ya (Rp. 2.000.000)";
        }
        if (chkMakan.isChecked()){
            t_makan = 1000000;
            g_makan = "Ya (Rp. 1.000.000)";
        }
        if(chktunjangan_Anak.isChecked()){
            t_anak = 1000000;
            g_anak = "Ya (Rp. 1.000.000)";
        }
        total_gaji = gaji_pokok + t_trans + t_makan + t_anak;

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivityHitungGaji.this).
                setTitle("Konfirmasi")
                .setMessage("Kode Pegawai : " + idPegawai + "\nJumlah Anak : " + jumlah_anak
                        + "\nGaji Pokok :  " + g_pokok
                        + "\nTunjangan Makan : " + g_makan
                        + "\nTunjangan Anak : " + g_anak
                        + "\nTranspor : " + g_transpor)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_INPUT_GAJI, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                Toast.makeText(ActivityHitungGaji.this, "Berhasil Input data", Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(ActivityHitungGaji.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }){
                            @Override
                            protected Map<String,String> getParams(){
                                Map<String,String> params = new HashMap<String, String>();
                                params.put("id_pegawai",idPegawai);
                                params.put("jumlah_anak", String.valueOf(jumlah_anak));
                                params.put("total_gaji", String.valueOf(total_gaji));
                                return params;
                            }
                        };
                        requestQueue.add(stringRequest);
                        Intent intent = new Intent(ActivityHitungGaji.this,DetailGaji.class);
                        intent.putExtra("gaji",total_gaji);
                        startActivity(intent);
                    }
                });
        alertDialog.show();
        clarData();

    }

    private void clarData() {
        edtidpeg.setText("");
        edtjml_anak.setText("");
        chkpokok.toggle();
        chkMakan.toggle();
        chktranspor.toggle();
        chktunjangan_Anak.toggle();
    }

    private void tunjanganAnak() {
        edtjml_anak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlah_anak = Integer.parseInt(edtjml_anak.getText().toString());
                if (jumlah_anak>=3){
                    chktunjangan_Anak.setEnabled(true);
                }
                else {
                    chktunjangan_Anak.setEnabled(false);
                }
            }
        });


    }


}
