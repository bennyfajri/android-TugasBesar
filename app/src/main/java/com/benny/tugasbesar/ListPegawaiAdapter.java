package com.benny.tugasbesar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListPegawaiAdapter extends RecyclerView.Adapter<ListPegawaiAdapter.ViewHolder> {
    private Context context;
    private List<Pegawai> pegawaiList;
    public ListPegawaiAdapter(Context context,List<Pegawai> pegawaiList){
        this.context = context;
        this.pegawaiList = pegawaiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_view,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pegawai pegawai = pegawaiList.get(position);
        Glide.with(context)
                .load(pegawai.getGambar())
                .into(holder.listImage);
        holder.nama.setText(pegawai.getNama());
        holder.posisi.setText(pegawai.getPosisi());
    }

    @Override
    public int getItemCount() {
        return pegawaiList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView listImage;
        TextView nama,posisi;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            listImage = itemView.findViewById(R.id.img_item_photo);
            nama = itemView.findViewById(R.id.txt_name);
            posisi = itemView.findViewById(R.id.txt_posisi);
        }
    }
}
