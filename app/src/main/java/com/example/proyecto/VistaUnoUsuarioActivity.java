package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class VistaUnoUsuarioActivity extends AppCompatActivity {
    private TextView Bienvenida;
    public String id;

    ArrayList<Producto> listaProducto;
    RecyclerView recyclerViewProductos;
    AdminSQLiteOpenHelper conn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_uno_usuario);
        //Inicializando valores
        Bienvenida = (TextView) findViewById(R.id.txtBienvenida);

        //Recibir Datos del Login
        id = getIntent().getStringExtra("id");

        //Buscando en la base de datos a nuestro usuario
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor file =  BaseDeDatos.rawQuery("select nombre from usuarios where id="+id,null);
        String nombreUsuario = file.getString(0);
        BaseDeDatos.close();
        Bienvenida.setText("Bienvenido "+nombreUsuario);

        conn = new AdminSQLiteOpenHelper(getApplicationContext(),"administracion",null, 1);
        listaProducto=new ArrayList<>();

        recyclerViewProductos= (RecyclerView) findViewById(R.id.activity_lis_productos_uno);
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

    //Metodo para pasar al perfil de usuario
    public void VistaUsuario(View view){
        Intent i = new Intent(this, PerfilActivity.class);
        i.putExtra("id",id);
        startActivity(i);
    }
}