package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroActivity extends AppCompatActivity {
    private EditText etcorreo;
    private EditText etcontrasena;
    private EditText etcontrasenaConfirmacion;
    private EditText etnombre,etnumero;
    private CheckBox chech1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etnombre = (EditText) findViewById(R.id.etnombreregistro);
        etcorreo = (EditText) findViewById(R.id.etemailregistro);
        etnumero = (EditText) findViewById(R.id.etptelefonoregistro);
        etcontrasena = (EditText)  findViewById(R.id.etcontrasenaregistro);
        etcontrasenaConfirmacion = (EditText) findViewById(R.id.etconfirmarcontrasenaregistro);
        chech1 = (CheckBox) findViewById(R.id.cbterminosregistro);
    }

    //Metodo para registrar al usuario en la base de datos
    public void registrarUsuario(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        if (etcontrasena.getText().toString().equals(etcontrasenaConfirmacion.getText().toString())) {
            String nombre = etnombre.getText().toString();
            String correo = etcorreo.getText().toString();
            String numero = etnumero.getText().toString();
            String contraseña = etcontrasena.getText().toString();
            if(chech1.isChecked() == true){
                if (!nombre.isEmpty() && !correo.isEmpty() && !numero.isEmpty() && !contraseña.isEmpty()){
                    ContentValues registro = new ContentValues();
                    registro.put("nombre",nombre);
                    registro.put("correo",correo);
                    registro.put("numero",numero);
                    registro.put("contraseña",contraseña);

                    BaseDeDatos.insert("usuarios",null,registro);
                    BaseDeDatos.close();
                    Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(this, "Debe de llenar todos los campos", Toast.LENGTH_SHORT).show();}
            }else{Toast.makeText(this, "Debe de aceptar los terminos y condiciones", Toast.LENGTH_SHORT).show();}
        }else {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para regresar al activity de login.
    public void Anterior(View view) {
        Intent anterior = new Intent(this, LoginActivity.class);
        startActivity(anterior);
    }
}