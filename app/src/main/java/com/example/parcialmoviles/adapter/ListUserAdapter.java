package com.example.parcialmoviles.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.UsuarioViewHolder> {



    public class UsuarioViewHolder extends RecyclerView.ViewHolder {


        public UsuarioViewHolder(@NonNull View itemView) {


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();

                }
            });
        }
    }


}
