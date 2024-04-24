package com.ezediaz.exploradordefarmacias.ui.lista;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ezediaz.exploradordefarmacias.R;
import com.ezediaz.exploradordefarmacias.modelo.Farmacia;

import java.util.List;

public class FarmaciaAdapter extends RecyclerView.Adapter<FarmaciaAdapter.ViewHolderPepe> {
    private List<Farmacia> listaDeFarmacias;
    private LayoutInflater li;

    public FarmaciaAdapter(List<Farmacia> listaDeFarmacias,  LayoutInflater li) {
        this.listaDeFarmacias = listaDeFarmacias;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderPepe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item_farmacia, parent, false);
        return new ViewHolderPepe(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPepe holder, int position) {
        Farmacia farmacia = listaDeFarmacias.get(position);
        holder.nombre.setText(farmacia.getNombre());
        holder.direccion.setText(farmacia.getDireccion());
        holder.foto.setImageResource(farmacia.getFoto());

        holder.btnVerMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(holder.itemView);
                Bundle bundle = new Bundle();
                bundle.putSerializable("farmacia", farmacia);
                navController.navigate(R.id.action_nav_lista_to_farmacia, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDeFarmacias.size();
    }

    public class ViewHolderPepe extends RecyclerView.ViewHolder {
        TextView nombre, direccion;
        ImageView foto;
        Button btnVerMas;

        public ViewHolderPepe(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombre);
            direccion = itemView.findViewById(R.id.tvDireccion);
            foto = itemView.findViewById(R.id.ivFoto);
            btnVerMas = itemView.findViewById(R.id.btnVerMas);
        }
    }
}
