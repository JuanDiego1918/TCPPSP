package com.example.juan.tcppsp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juan.tcppsp.R;
import com.example.juan.tcppsp.Utilidades.proyectosVo;

import java.util.ArrayList;

public class proyectosAdapter extends RecyclerView.Adapter<proyectosAdapter.Proyectos> implements View.OnClickListener{

    View.OnClickListener listener;
    ArrayList<proyectosVo> listaProyectos;

    public proyectosAdapter(ArrayList<proyectosVo> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }

    @Override
    public Proyectos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_lista_proyectos,null,false);
        view.setOnClickListener(this);
        return new Proyectos(view);
    }

    @Override
    public void onBindViewHolder(Proyectos holder, int position) {
        holder.nombre.setText(listaProyectos.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return listaProyectos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class Proyectos extends RecyclerView.ViewHolder {
        TextView nombre;
        public Proyectos(View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nombreProyectoAdapter);
        }
    }
}
