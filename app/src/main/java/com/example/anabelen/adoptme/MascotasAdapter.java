package com.example.anabelen.adoptme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ana Belen on 24/08/2016.
 */
public class MascotasAdapter extends RecyclerView.Adapter<MascotasAdapter.MyViewHolder> {

    private List<Mascota> mascotasList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tipo, raza, edad, estado, genero;

        public MyViewHolder(View view) {
            super(view);
            tipo = (TextView) view.findViewById(R.id.tipo);
            raza = (TextView) view.findViewById(R.id.raza);
            edad = (TextView) view.findViewById(R.id.edad);
            estado = (TextView) view.findViewById(R.id.estado);
            genero = (TextView) view.findViewById(R.id.genero);
        }
    }

    public MascotasAdapter(List<Mascota> mascotasList) {
        this.mascotasList = mascotasList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mascota_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Mascota mascota = mascotasList.get(position);
        holder.tipo.setText(mascota.getTipo());
        holder.raza.setText(mascota.getRaza());
        holder.edad.setText(mascota.getEdad());
        holder.estado.setText(mascota.getEstado());
        holder.genero.setText(mascota.getGenero());
    }

    @Override
    public int getItemCount() {
        return mascotasList.size();
    }
}
