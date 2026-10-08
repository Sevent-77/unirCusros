package com.example.unircursos;

import com.example.unircursos.Curso;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    private ArrayList<Curso> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    public CourseAdapter(ArrayList<Curso> list) {
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtNome.setText(list.get(position).getNome());
        holder.textType.setText(list.get(position).getTurno());
        holder.textDescription.setText(list.get(position).getCampus());

        Glide.with(holder.itemView)
                .load(list.get(position).getImagem())
                .into(holder.imgAvatar);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNome;
        TextView textType;
        TextView textDescription;
        ImageView imgAvatar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNome = itemView.findViewById(R.id.txtNome);
            textType = itemView.findViewById(R.id.textType);
            textDescription = itemView.findViewById(R.id.textDescription);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();

                    if (position == RecyclerView.NO_POSITION) {
                        return;
                    }
                    Intent intent = new Intent(view.getContext(), CoursePage.class);
                    intent.putExtra("Nome", list.get(position).getNome());
                    intent.putExtra("Turno", list.get(position).getTurno());
                    intent.putExtra("Imagem", list.get(position).getImagem());
                    intent.putExtra("Campus", list.get(position).getCampus());
                    intent.putExtra("Grau", list.get(position).getGrau());
                    intent.putExtra("Site", list.get(position).getSite());
                    intent.putExtra("Descricao", list.get(position).getDescricao());
                    view.getContext().startActivity(intent);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getBindingAdapterPosition();

                    if (position == RecyclerView.NO_POSITION) {
                        return false;
                    }
                    return true;
                }
            });
        }
    }
}