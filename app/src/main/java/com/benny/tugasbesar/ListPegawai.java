package com.benny.tugasbesar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListPegawai extends AppCompatActivity {

    private String title="Mode Card";
    private static final String URL_LIST_PEGAWAI = "http://10.0.2.2/server_perusahaan/list_pegawai.php";
    List<Pegawai> pegawaiList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pegawai);
        setActionBarTitle(title);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pegawaiList = new ArrayList<>();
        pegawaiData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pegawai_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void setActionBarTitle(String title){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
    }

    private void setMode(int itemId) {
        switch (itemId){
            case R.id.list_view:
                title = "Mode List";
                showListView();
                break;
            case R.id.grid_view:
                title = "Mode Grid";
                showGridView();
                break;
            case R.id.card_view:
                title = "Mode Card";
                showCardView();
                break;
        }
        setActionBarTitle(title);
    }

    private void showCardView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardPegawaiAdapter cardPegawaiAdapter = new CardPegawaiAdapter(ListPegawai.this,pegawaiList);
        recyclerView.setAdapter(cardPegawaiAdapter);
    }

    private void showGridView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        GridPegawaiAdapter gridPegawaiAdapter = new GridPegawaiAdapter(ListPegawai.this,pegawaiList);
        recyclerView.setAdapter(gridPegawaiAdapter);
    }

    private void showListView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListPegawaiAdapter listPegawaiAdapter = new ListPegawaiAdapter(ListPegawai.this,pegawaiList);
        recyclerView.setAdapter(listPegawaiAdapter);
    }

    private void pegawaiData() {
        StringRequest stringRequest = new StringRequest
                (Request.Method.GET, URL_LIST_PEGAWAI, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length();i++){
                                JSONObject pegawai = array.getJSONObject(i);
                                pegawaiList.add(new Pegawai(
                                        pegawai.getString("id_pegawai"),
                                        pegawai.getString("nama"),
                                        pegawai.getString("jekel"),
                                        pegawai.getString("tgl_lahir"),
                                        pegawai.getString("daerah_asal"),
                                        pegawai.getString("status"),
                                        pegawai.getString("bagian"),
                                        pegawai.getString("foto")
                                ));  }
                            CardPegawaiAdapter adapter = new CardPegawaiAdapter(ListPegawai.this,pegawaiList);
                            recyclerView.setAdapter(adapter);

                        }catch (JSONException ex){
                            ex.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
