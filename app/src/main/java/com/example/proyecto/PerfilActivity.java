package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilActivity extends AppCompatActivity {
    private EditText et_nombre, et_correo, et_numero;
    private ImageView et_img;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //Recibiendo id de nuestro usuario
        id = getIntent().getStringExtra("id");

        //Inicializando nuestros text view
        et_nombre = (EditText) findViewById(R.id.etNombreUsuarioPerfil);
        et_correo = (EditText) findViewById(R.id.etUsuarioPerfil) ;
        et_numero = (EditText) findViewById(R.id.etTelefonoUsuarioPerfil);
        et_img = (ImageView) findViewById(R.id.tvImagenUsuarioPerfil);

        //Obteniendo Datos de nuestra base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor file =  BaseDeDatos.rawQuery("select nombre,correo,numero from usuarios where id="+id,null);
        String nombreUsuario = file.getString(0);
        String correoUsuario = file.getString(1);
        String numeroUsuario = file.getString(2);
        BaseDeDatos.close();
        //Dando los valores obtenidos de nuestra consulta a la base de datos
        et_nombre.setText(nombreUsuario);
        et_correo.setText(correoUsuario);
        et_numero.setText(numeroUsuario);
    }

    public void guardar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = et_nombre.getText().toString();
        String correo = et_correo.getText().toString();
        String numero = et_numero.getText().toString();

        if(!nombre.isEmpty() && !correo.isEmpty() && !numero.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("nombre",nombre);
            registro.put("correo",correo);
            registro.put("numero",numero);

            int cantidad = BaseDeDatos.update("usuarios",registro,"id="+id,null);
            BaseDeDatos.close();
            if(cantidad == 1){
                Toast.makeText(this, "Usuario modificado Correctamente", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, VistaUnoUsuarioActivity.class);
                startActivity(i);
            }else{
                Toast.makeText(this, "Error, Usuario no existe", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Debes de rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }


    public void cerrarSesion(View view){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void volver(View view){
        Intent i = new Intent(this, VistaUnoUsuarioActivity.class);
        startActivity(i);
    }
    public void onclick(View view) {
        cargarImagen();
    }
    private void cargarImagen(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicacion"),10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode==RESULT_OK){
            Uri path = data.getData();
            et_img.setImageURI(path);
        }
    }
}