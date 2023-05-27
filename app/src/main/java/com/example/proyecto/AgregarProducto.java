package com.example.proyecto;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarProducto extends AppCompatActivity {
    private EditText et_nombre,et_tipo,et_descripcion,et_precio;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarproducto);

        //Inicializando Valores
        et_nombre = (EditText) findViewById(R.id.txtNombre);
        et_tipo = (EditText) findViewById(R.id.txtTipo);
        et_descripcion = (EditText) findViewById(R.id.txtDescripcion);
        et_precio = (EditText) findViewById(R.id.txtPrecio);

    }

    public void Volver(View view){
        Intent i = new Intent(this, AdminActivity.class);
        startActivity(i);
    }

    public void Guardar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = et_nombre.getText().toString();
        String tipo = et_tipo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!nombre.isEmpty() && !tipo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("nombre",nombre);
            registro.put("tipo",tipo);
            registro.put("descripcion",descripcion);
            registro.put("precio",precio);

            BaseDeDatos.insert("productos",null,registro);
            BaseDeDatos.close();
            Intent i = new Intent(this, AdminActivity.class);
            startActivity(i);

            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debe de llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}
