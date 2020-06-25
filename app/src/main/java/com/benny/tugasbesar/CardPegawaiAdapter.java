package com.benny.tugasbesar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardPegawaiAdapter extends RecyclerView.Adapter<CardPegawaiAdapter.ViewHolder> {
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    private final String URL_DELETE_PEGAWAI = "http://10.0.2.2/server_perusahaan/delete_pegawai.php";
    private Context context;
    private List<Pegawai> pegawaiList;
    public CardPegawaiAdapter(Context context, List<Pegawai> pegawaiList){
        this.context = context;
        this.pegawaiList = pegawaiList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Pegawai pegawai = pegawaiList.get(position);
        Glide.with(context)
                .load(pegawai.getGambar())
                .into(holder.cardImage);
        holder.nama.setText("Nama : " + pegawai.getNama());
        holder.status.setText("Status : " + pegawai.getStatus());
        holder.posisi.setText("Bagian : " + pegawai.getPosisi());
        holder.asal.setText("Daerah asal :  : " +pegawai.getDaerah_asal());
        holder.jekel.setText("jenis Kelamin : " +pegawai.getJekel());
        holder.id.setText("ID : " +pegawai.getId());
        holder.tgl_lahir.setText("Tgl Lahir : " + pegawai.getTgl_lahir());
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context,EditPegawai.class);
                intent1.putExtra("id",pegawaiList.get(holder.getAdapterPosition()).getId());
                intent1.putExtra("nama",pegawaiList.get(holder.getAdapterPosition()).getNama());
                intent1.putExtra("bagian",pegawaiList.get(holder.getAdapterPosition()).getPosisi());
                intent1.putExtra("daerah_asal",pegawaiList.get(holder.getAdapterPosition()).getDaerah_asal());
                intent1.putExtra("tgl_lahir",pegawaiList.get(holder.getAdapterPosition()).getTgl_lahir());
                context.startActivity(intent1);
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                        .setTitle("Konfirmasi")
                        .setMessage("Apakah anda yakin ingin menghapus data ini?")
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestQueue = Volley.newRequestQueue(context);
                                progressDialog = new ProgressDialog(context);
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DELETE_PEGAWAI, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        progressDialog.dismiss();
                                        Toast.makeText(context, "Berhasil menghapus data", Toast.LENGTH_LONG).show();
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        progressDialog.dismiss();
                                        Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                                    }
                                }){
                                    @Override
                                    protected Map<String,String> getParams(){
                                        Map<String,String> params = new HashMap<String, String>();
                                        params.put("id_pegawai",pegawaiList.get(holder.getAdapterPosition()).getId());
                                        return params;
                                    }
                                };
                                requestQueue.add(stringRequest);
                                dialog.dismiss();
                                Intent intent2 = new Intent(context,MenuPegawai.class);
                                context.startActivity(intent2);
                            }
                        });
                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return pegawaiList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImage;
        TextView nama;
        TextView posisi;
        TextView jekel;
        TextView asal;
        TextView status;
        TextView id;
        TextView tgl_lahir;
        Button btn_edit,btn_delete;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.img_item_photo);
            nama = itemView.findViewById(R.id.txt_name);
            posisi = itemView.findViewById(R.id.txt_posisi);
            jekel = itemView.findViewById(R.id.txt_jekel);
            asal = itemView.findViewById(R.id.txt_daerahAsal);
            status = itemView.findViewById(R.id.txt_status);
            id = itemView.findViewById(R.id.txt_id);
            tgl_lahir = itemView.findViewById(R.id.txt_tglLahir);
            btn_edit = itemView.findViewById(R.id.btn_edit_pegawai);
            btn_delete = itemView.findViewById(R.id.btn_delete_pegawai);
        }
    }
}
