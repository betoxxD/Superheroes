package com.example.superheroes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorHeroes extends RecyclerView.Adapter<AdaptadorHeroes.ViewHolder> {

    private ArrayList<Heroe> heroes;
    private Context context;
    private LayoutInflater inflater;

    public AdaptadorHeroes (Context context, ArrayList<Heroe> heroes) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.heroes = heroes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.structure_layout,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Heroe heroe = heroes.get(position);
        holder.titulo.setText(heroe.getName());
        Picasso.with(context).load(heroe.getImageurl()).into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView titulo;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.ivImage);
            titulo = (TextView) itemView.findViewById(R.id.txtTitle);
        }
    }
}
