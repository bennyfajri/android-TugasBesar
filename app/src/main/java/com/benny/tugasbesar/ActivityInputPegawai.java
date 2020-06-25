package com.benny.tugasbesar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActivityInputPegawai extends AppCompatActivity {

    EditText edtID, edtNama, edtTglLahir, edtDaerahAsal,edtFoto;
    Spinner spinBagian, spinStatus;
    RadioGroup radioGroup;
    RadioButton laki_Laki, perempuan;
    Button insertButton;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String id,bagian,nama,jekel="",tgl_lahir,daerahAsal,status,foto;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormat;
    final String URL_DAFTAR = "http://10.0.2.2/server_perusahaan/entri_pegawai.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pegawai);

        edtID = findViewById(R.id.edtID);
        edtNama = findViewById(R.id.edtNama);
        edtTglLahir = findViewById(R.id.edtTglLahir);
        edtDaerahAsal = findViewById(R.id.edtAsal);
        edtFoto = findViewById(R.id.edtPhoto);

        spinBagian = findViewById(R.id.spinBagian);
        spinStatus = findViewById(R.id.spinStatus);

        radioGroup = findViewById(R.id.rgJekel);
        laki_Laki = findViewById(R.id.rbLaki);
        perempuan = findViewById(R.id.rbPerempuan);

        insertButton = findViewById(R.id.btnSubmit);

        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        edtTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerTglLahir();
            }
        });

        requestQueue = Volley.newRequestQueue(ActivityInputPegawai.this);
        progressDialog = new ProgressDialog(ActivityInputPegawai.this);
        progressDialog.setMessage("Silahkan Tunggu, input data ke server sedang berlangsung");
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesInputPegawai();
            }
        });

    }

    private void prosesInputPegawai() {
        progressDialog.show();
        id = edtID.getText().toString().trim();
        bagian = spinBagian.getSelectedItem().toString();
        nama = edtNama.getText().toString().trim();
        if (laki_Laki.isChecked()){
            jekel = laki_Laki.getText().toString();
        }
        else{
            jekel = perempuan.getText().toString();
        }
        tgl_lahir = edtTglLahir.getText().toString();
        daerahAsal = edtDaerahAsal.getText().toString().trim();
        status = spinStatus.getSelectedItem().toString();
        foto = edtFoto.getText().toString();
        clearData();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DAFTAR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText(ActivityInputPegawai.this, "Berhasil Daftar", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(ActivityInputPegawai.this,MenuPegawai.class);
                startActivity(intent2);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ActivityInputPegawai.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("id_pegawai",id);
                params.put("bagian",bagian);
                params.put("nama",nama);
                params.put("jekel",jekel);
                params.put("tgl_lahir",tgl_lahir);
                params.put("daerah_asal",daerahAsal);
                params.put("status",status);
                params.put("foto",foto);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void clearData() {
        edtID.setText("");
        edtNama.setText("");
        edtTglLahir.setText("");
        edtDaerahAsal.setText("");
        edtFoto.setText("");
        laki_Laki.toggle();
        perempuan.toggle();

    }

    private void DatePickerTglLahir() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year,month,dayOfMonth);
                edtTglLahir.setText(dateFormat.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
