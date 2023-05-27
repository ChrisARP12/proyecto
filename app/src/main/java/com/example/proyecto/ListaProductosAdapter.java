package com.example.proyecto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ProductosViewHolder>{
    ArrayList<Producto> listaProducto;

    public ListaProductosAdapter(ArrayList<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }
    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_productos,null,false);
        return new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductosViewHolder holder, int position) {
        holder.nombre.setText(listaProducto.get(position).getNombre().toString());
        holder.tipo.setText(listaProducto.get(position).getTipo());
        holder.descripcion.setText(listaProducto.get(position).getDescripcion());
        holder.precio.setText(listaProducto.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return listaProducto.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView nombre,tipo,descripcion,precio;

        public ProductosViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.textNombre);
            tipo = (TextView) itemView.findViewById(R.id.textTipo);
            descripcion = (TextView) itemView.findViewById(R.id.textDescripcion);
            precio = (TextView) itemView.findViewById(R.id.textPrecio);
        }
    }
}
