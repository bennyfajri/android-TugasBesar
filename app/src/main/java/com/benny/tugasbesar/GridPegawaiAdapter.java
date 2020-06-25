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

public class GridPegawaiAdapter extends RecyclerView.Adapter<GridPegawaiAdapter.ViewHolder> {
    private Context context;
    private List<Pegawai> pegawaiList;
    public GridPegawaiAdapter(Context context, List<Pegawai> pegawaiList){
        this.context = context;
        this.pegawaiList = pegawaiList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.grid_view,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pegawai pegawai = pegawaiList.get(position);
        Glide.with(context)
                .load(pegawai.getGambar())
                .into(holder.gridImage);
        holder.nama.setText(pegawai.getNama());
        holder.posisi.setText(pegawai.getPosisi());
    }

    @Override
    public int getItemCount() {
        return pegawaiList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gridImage;
        TextView nama,posisi;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            gridImage = itemView.findViewById(R.id.img_item_photo);
            nama= itemView.findViewById(R.id.txt_name);
            posisi = itemView.findViewById(R.id.txt_posisi);
        }
    }
}
