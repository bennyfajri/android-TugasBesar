package com.benny.tugasbesar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditPegawai extends AppCompatActivity {

    EditText edtId,edtNama, edttglLahir, edtAsal,edtFoto;
    Spinner spinBagian, spinStatus;
    RadioGroup radioGroup;
    RadioButton laki,perempuan;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String id,bagian,nama,jekel="",tgl_lahir,daerahasal,status,foto;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormat;
    final String URL_EDIT = "http://10.0.2.2/server_perusahaan/edit_pegawai.php";
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pegawai);

        Bundle bundle = getIntent().getExtras();
        edtId = findViewById(R.id.edtID_edit);
        edtId.setText(bundle.getString("id"));
        edttglLahir = findViewById(R.id.edtTglLahir_edit);
        edttglLahir.setText(bundle.getString("tgl_lahir"));
        edtAsal = findViewById(R.id.edtAsal_edit);
        edtAsal.setText(bundle.getString("daerah_asal"));
        edtFoto = findViewById(R.id.edtPhoto_edit);
        edtFoto.setText(bundle.getString("foto"));
        edtNama = findViewById(R.id.edtNama_edit);
        edtNama.setText(bundle.getString("nama"));

        spinBagian = findViewById(R.id.spinBagian_edit);
        spinBagian.setSelected(Boolean.parseBoolean(bundle.getString("bagian")));
        spinStatus = findViewById(R.id.spinStatus_edit);
        spinStatus.setSelected(Boolean.parseBoolean(bundle.getString("status")));

        laki = findViewById(R.id.rbLaki_edit);
        perempuan = findViewById(R.id.rbPerempuan_edit);

        btnSubmit = findViewById(R.id.btn_submit_edit);

        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        edttglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesClickTanggal();
            }
        });

        requestQueue = Volley.newRequestQueue(EditPegawai.this);
        progressDialog = new ProgressDialog(EditPegawai.this);
        progressDialog.setMessage("Silahka tunggu, data sedang diedit");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesEditPegawai();
            }
        });
    }

    private void prosesEditPegawai() {

    }

    private void prosesClickTanggal() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year,month,dayOfMonth);
                edttglLahir.setText(dateFormat.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
