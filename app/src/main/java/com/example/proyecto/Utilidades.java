package com.example.proyecto;

public class Utilidades {
    //Constantes campos tabla usuario
    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_CORREO="correo";
    public static final String CAMPO_NUMERO="numero";
    public static final String CAMPO_CONTRASEÑA="contraseña";

    public static final String CREAR_TABLA_USUARIOS="CREATE TABLE " +
            ""+TABLA_USUARIO+" ("+CAMPO_ID+"INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_CORREO+" TEXT,"+CAMPO_NUMERO+"INTEGER,"+CAMPO_CONTRASEÑA+" TEXT)";

    //Constantes campos tabla Productos
    public static final String TABLA_PRODUCTOS="productos";
    public static final String CAMPO_NOMBRE_PRODUCTO="nombre";
    public static final String CAMPO_TIPO="tipo";
    public static final String CAMPO_DESCRIPCION="descripcion";
    public static final String CAMPO_PRECIO="precio";

    public static final String CREAR_TABLA_PRODUCTOS="CREATE TABLE " +
            ""+TABLA_PRODUCTOS+" ("+CAMPO_NOMBRE_PRODUCTO+" INTEGER PRIMARY KEY, "
            +CAMPO_TIPO+" TEXT, "+CAMPO_DESCRIPCION+" TEXT,"+CAMPO_PRECIO+" INTEGER)";
}
