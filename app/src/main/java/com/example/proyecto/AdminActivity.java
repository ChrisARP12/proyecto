package com.example.proyecto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    private TextView bienvenida;
    ArrayList<Producto> listaProducto;
    RecyclerView recyclerViewProductos;
    AdminSQLiteOpenHelper conn;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //Mensaje de bienvenida al admin
        bienvenida = (TextView) findViewById(R.id.txtBienvenida);
        bienvenida.setText("Bienvenido Admin, aquí podras modificar tus productos");

        conn = new AdminSQLiteOpenHelper(getApplicationContext(),"administracion",null, 1);
        listaProducto=new ArrayList<>();

        recyclerViewProductos= (RecyclerView) findViewById(R.id.activity_list_productos);
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(this));

        consultarListaProductos();

        ListaProductosAdapter adapter=new ListaProductosAdapter(listaProducto);
        recyclerViewProductos.setAdapter(adapter);

    }
    private void consultarListaProductos() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Producto producto=null;
        // listaUsuarios=new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_PRODUCTOS,null);

        while (cursor.moveToNext()){
            producto=new Producto();
            producto.setNombre(cursor.getString(0));
            producto.setTipo(cursor.getString(1));
            producto.setDescripcion(cursor.getString(2));
            producto.setPrecio(cursor.getString(3));

            listaProducto.add(producto);
        }
    }
    public void cerrarSesion(View view){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
