package com.example.cronometrocountdowtimeraleman.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.cronometrocountdowtimeraleman.modelo.Ejercicio;
import com.example.cronometrocountdowtimeraleman.modelo.SesionesEnt;
import com.example.cronometrocountdowtimeraleman.modelo.Usuario;

import java.util.ArrayList;
import java.util.Date;

public class ConexionSQLite extends SQLiteOpenHelper {

    //Creamos el constructor SQLiteHelper (abstracto)
    public ConexionSQLite(@Nullable Context context) {
        super(context, "entrena_peques", null, 1);
    }

    //Implementamos (sobreescribimos) los métodos que nos obliga SQLiteHelper (abstracta)
    // a implementar y a desarrollar adaptandolo a nuestra necesidades
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creación de la tabla Usuario
        db.execSQL("CREATE TABLE USUARIO (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellidos TEXT, email TEXT, clave TEXT)");

        //Creación de la tabla ejercicio
        db.execSQL("CREATE TABLE EJERCICIO (id INTEGER PRIMARY KEY AUTOINCREMENT, nombreEjercicio TEXT, tipoEjercicio TEXT)");

        //Inicializar la table con ejercicios
        db.execSQL("INSERT INTO EJERCICIO (nombreEjercicio, tipoEjercicio) VALUES ('Superior1', 'Superior')");
        db.execSQL("INSERT INTO EJERCICIO (nombreEjercicio, tipoEjercicio) VALUES ('Superior2', 'Superior')");
        db.execSQL("INSERT INTO EJERCICIO (nombreEjercicio, tipoEjercicio) VALUES ('Superior3', 'Superior')");
        db.execSQL("INSERT INTO EJERCICIO (nombreEjercicio, tipoEjercicio) VALUES ('Superior4', 'Superior')");
        db.execSQL("INSERT INTO EJERCICIO (nombreEjercicio, tipoEjercicio) VALUES ('Superior5', 'Superior')");
        db.execSQL("INSERT INTO EJERCICIO (nombreEjercicio, tipoEjercicio) VALUES ('Superior6', 'Superior')");

        //Inicializar la table con ejercicios
        db.execSQL("INSERT INTO EJERCICIO (nombreEjercicio, tipoEjercicio) VALUES ('Cardio1', 'Cardio')");
        db.execSQL("INSERT INTO EJERCICIO (nombreEjercicio, tipoEjercicio) VALUES ('Cardio2', 'Cardio')");
        db.execSQL("INSERT INTO EJERCICIO (nombreEjercicio, tipoEjercicio) VALUES ('Cardio3', 'Cardio')");
        db.execSQL("INSERT INTO EJERCICIO (nombreEjercicio, tipoEjercicio) VALUES ('Cardio4', 'Cardio')");
        db.execSQL("INSERT INTO EJERCICIO (nombreEjercicio, tipoEjercicio) VALUES ('Cardio5', 'Cardio')");
        db.execSQL("INSERT INTO EJERCICIO (nombreEjercicio, tipoEjercicio) VALUES ('Cardio6', 'Cardio')");

        //Creación de la tabla sesionesEnt
        /*db.execSQL("CREATE TABLE SESION(idUsuario INTEGER, idEjercicio INTEGER, fechahora DATETIME," +
                " FOREIGN KEY(idUsuario) REFERENCES USUARIO(id), FOREIGN KEY (idEjercicio) REFERENCES EJERCICIO(id), " +
                "PRIMARY KEY(idUsuario, idEjercicio, fechahora))");*/

        db.execSQL("CREATE TABLE SESION(idUsuario INTEGER, idEjercicio INTEGER, fechahora DATETIME)");

        db.execSQL("INSERT INTO SESION (idUsuario, idEjercicio, fechahora) VALUES (1, 1, '2016-01-01 10:20:05')");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //en caso de actualización de nuestra BBDD que borre y cree de nuevo para actualizar
        db.execSQL("DROP TABLE IF EXISTS USUARIO");
        //tabla ejercicio
        db.execSQL("DROP TABLE IF EXISTS EJERCICIO");
        //tabla sesionesEnt
        db.execSQL("DROP TABLE IF EXISTS SESION");
        
        onCreate(db);
    }

    //Nos creamos este método con el que poder hacer los INSERT en nuestra consulta
    /*public void registrarUsuario(Usuario usuario){

        //Escribimos en nuestraBBDD
        SQLiteDatabase base_de_datos = this.getWritableDatabase();

        //Creamos estas variables que asosciamos para escribir la información
        String nombre = usuario.getNombre();
        String apellidos = usuario.getApellidos();
        String correo = usuario.getCorreo();
        String clave = usuario.getClave();

        //Guardamos toda la información una sentencia
        String sentencia = "INSERT INTO USUARIO (nombre, apellidos, clave, email) VALUES ('"+nombre+"', '"+apellidos+"', '"+correo+"', '"+clave+"')";

        //Ejecutamos la sentencia albergada en el string sentencia
        base_de_datos.execSQL(sentencia);

        //Cerramos nuestra bases de datos
        base_de_datos.close();
    }*/

    ///Con este método creamos los insert y además nos devuelve el id (contentvalues) para poder saber
    //si el usuario ya está registrado o no
    public long registrarUsuario(Usuario usuario){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("nombre", usuario.getNombre());
        values.put("apellidos", usuario.getApellidos());
        values.put("email", usuario.getCorreo());
        values.put("clave", usuario.getClave());

        long resultado = db.insert("USUARIO", "id", values);

        db.close();

        return resultado;

    }

    //Este método finalmente no será necesario pues incluiremos los ejercicios en una tabla en el onCreate
    //Creamos métodos ejercicio (id, nombre, tipo).
    /*public void registroEjercicio(Ejercicio ejercicio){

        //Escribimos en nuestraBBDD
        SQLiteDatabase base_de_datos = this.getWritableDatabase();

        //Creamos estas variables que asociamos para escribir información
        String nombre = ejercicio.getNombreEjercicio();
        String tipo = ejercicio.getTipoEjercicio();

        //Guardamos la información en una sentencia
        String sentencia = "INSERT INTO EJERCICIO (nombre, tipo) VALUES ('"+nombre+"','"+tipo+"')";

        //Ejecutamos la sentencia mediante el método
        base_de_datos.execSQL(sentencia);

        //Cerramos nuestra bases de datos
        base_de_datos.close();
    }*/

    //Creamos metodo registro (usuario, dia, ejercicio).
    public void registrarSesionesEntrenos (int idUsuario, ArrayList<Integer> idSesiones, String fecha){

        //Escribimos en nuestra BBDD
        SQLiteDatabase base_de_datos = this.getWritableDatabase();

        //Creamos estas variables que asociamos para escribir información

        for(int i = 0; i < idSesiones.size(); i++){
            String sentencia = "INSERT INTO SESION (idUsuario, idEjercicio) VALUES ("+idUsuario+", "+idSesiones.get(i)+")";
            base_de_datos.execSQL(sentencia);

        }

        base_de_datos.close();
    }

    public void registroSesion(){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("idUsuario", 1);
        values.put("idEjercicio", 2);
        values.put("fechahora", "2016-01-01 10:20:05");

        long resultado = db.insert("SESION", "id", values);

        db.close();


    }
}
