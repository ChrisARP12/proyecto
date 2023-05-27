package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    EditText txtCorreo, txtContraseña;
    Button btnInicio, btnRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtCorreo = (EditText) findViewById(R.id.etlogologin);
        txtContraseña = (EditText) findViewById(R.id.etpasswordlogin);
        btnInicio = (Button) findViewById(R.id.btnIniciar);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);

        recuperar();

        if (getIntent().getStringExtra("correo") != null) {
            String correo = getIntent().getStringExtra("correo");
            String contrasenia = getIntent().getStringExtra("contrasena");

            txtCorreo.setText(correo);
            txtContraseña.setText(contrasenia);
            guardar();
        }
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pasando los valores a tipo texto
                String correoU = txtCorreo.getText().toString();
                String contraseña = txtContraseña.getText().toString();

                if (correoU=="admin" && contraseña=="admin"){
                    Intent admin = new Intent(LoginActivity.this, AdminActivity.class);
                    startActivity(admin);
                }

                //Abriendo la base de datos en escritura
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(LoginActivity.this,"administracion",null,1);
                SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

                if (!correoU.isEmpty()){
                    if (!contraseña.isEmpty()){
                        Cursor file =  BaseDeDatos.rawQuery("select id,correo,contraseña from usuarios where correo="+correoU+"and contraseña="+contraseña,null);
                        if (file.moveToFirst()){
                            String id = file.getString(0);
                            String correo = file.getString(1);
                            String contraseñab = file.getString(2);
                            BaseDeDatos.close();
                            if (correoU==correo && contraseña==contraseñab){
                                Intent intentInicio = new Intent(LoginActivity.this, VistaUnoUsuarioActivity.class);
                                intentInicio.putExtra("id",id);
                                startActivity(intentInicio);
                            }else{Toast.makeText(LoginActivity.this, "No existe el usuario", Toast.LENGTH_SHORT).show();}
                        }else{Toast.makeText(LoginActivity.this, "No existe el usuario", Toast.LENGTH_SHORT).show();}
                    }else{Toast.makeText(LoginActivity.this, "Debe ingresar una contraseña", Toast.LENGTH_SHORT).show();}
                }else{ Toast.makeText(LoginActivity.this, "Debe ingresar un correo", Toast.LENGTH_SHORT).show();}


            }
        });
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegistro = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intentRegistro);
            }
        });
    }

    public void recuperar(){
        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String s1 = prefe.getString("correo","");
        txtCorreo.setText(s1);
    }

    public void guardar() {
        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferencias.edit();
        editor.putString("correo", txtCorreo.getText().toString());
        editor.commit();
    }
}