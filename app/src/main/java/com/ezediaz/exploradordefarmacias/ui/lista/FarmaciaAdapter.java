package com.ezediaz.exploradordefarmacias.ui.lista;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ezediaz.exploradordefarmacias.R;
import com.ezediaz.exploradordefarmacias.modelo.Farmacia;

import java.util.List;

public class FarmaciaAdapter extends RecyclerView.Adapter<FarmaciaAdapter.ViewHolderPepe> {
    private List<Farmacia> listaDeFarmacias;
    private Context context;
    private LayoutInflater li;

    public FarmaciaAdapter(List<Farmacia> listaDeFarmacias, Context context, LayoutInflater li){
        this.listaDeFarmacias = listaDeFarmacias;
        this.context = context;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderPepe onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = li.inflate(R.layout.item_farmacia,parent,false);
        return new ViewHolderPepe(view);
    }

    public void onBindViewHolder(@NonNull ViewHolderPepe holder, int position){
        String t = "";
        Farmacia farmacia = listaDeFarmacias.get(position);
        holder.nombre.setText(farmacia.getNombre());
        t = holder.direccion.getText().toString() + " " + farmacia.getDireccion();
        holder.direccion.setText(t);
        holder.foto.setImageResource(farmacia.getFoto());
        holder.btnVerMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FarmaciaActivity.class);
                intent.putExtra("farmacia", farmacia);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return listaDeFarmacias.size();
    }

    public class ViewHolderPepe extends RecyclerView.ViewHolder{
        TextView nombre, direccion;
        ImageView foto;
        Button btnVerMas;

        public ViewHolderPepe(@NonNull View itemView){
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombre);
            direccion = itemView.findViewById(R.id.tvDireccion);
            foto = itemView.findViewById(R.id.ivFoto);
            btnVerMas = itemView.findViewById(R.id.btnVerMas);
        }
    }
}