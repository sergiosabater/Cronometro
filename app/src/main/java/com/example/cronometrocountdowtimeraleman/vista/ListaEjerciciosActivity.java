package com.example.cronometrocountdowtimeraleman.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cronometrocountdowtimeraleman.R;
import com.example.cronometrocountdowtimeraleman.controlador.ConexionSQLite;
import com.example.cronometrocountdowtimeraleman.modelo.Ejercicio;

import java.util.ArrayList;
import java.util.Currency;
import java.util.LinkedHashSet;
import java.util.Set;

public class ListaEjerciciosActivity extends AppCompatActivity {

    //Declaración de variables
    TextView tituloEjercicio;
    CheckBox ejercicio1, ejercicio2, ejercicio3, ejercicio4, ejercicio5, ejercicio6;
    Button botonEnviar;
    //Con estos arraylist guardamos la informacion de los ejercicios seleccionados por el usario que
    // se envia desde el bundle desde este actividad lista_ejercicios a al main activity de nuevo
    //ArrayList <String> nombreEjercicios;
    ArrayList <CheckBox> checkBoxes;
    ArrayList <Ejercicio> ejercicios;
    Set<String> nombreEjercicios;
    String tipoEjercicios;
    ConexionSQLite conexionSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ejercicios);

        conexionSQLite = new ConexionSQLite(this);

        nombreEjercicios = new LinkedHashSet<>();
        checkBoxes = new ArrayList<>();
        ejercicios = new ArrayList<>();

        //Mapeamos las variables con las vistas de la actividad
        tituloEjercicio = findViewById(R.id.TV_listaEjercicios);
        ejercicio1 = findViewById(R.id.CB_Ejercicio1);
        ejercicio2 = findViewById(R.id.CB_Ejercicio2);
        ejercicio3 = findViewById(R.id.CB_Ejercicio3);
        ejercicio4 = findViewById(R.id.CB_Ejercicio4);
        ejercicio5 = findViewById(R.id.CB_Ejercicio5);
        ejercicio6 = findViewById(R.id.CB_Ejercicio6);
        botonEnviar = findViewById(R.id.btn_Enviar);

        //Recepcionamos el bundle envíado desde el activity_main (poner titulos de nombres a los checbocks)
        Bundle informacion = getIntent().getExtras();

        //Creamos la variable para albergar la información mediante el key (SUPERIORES)
        /*String nombreEjercicio1 = informacion.getString("Ejercicio1");
        String nombreEjercicio2 = informacion.getString("Ejercicio2");
        String nombreEjercicio3 = informacion.getString("Ejercicio3");
        String nombreEjercicio4 = informacion.getString("Ejercicio4");
        String nombreEjercicio5 = informacion.getString("Ejercicio5");
        String nombreEjercicio6 = informacion.getString("Ejercicio6");*/
        tipoEjercicios = informacion.getString("tipoEjercicios");

        //implementamos el método
        obtenerEjercicios(tipoEjercicios);

        //se lo agregamos al array de checkboxes
        checkBoxes.add(ejercicio1);
        checkBoxes.add(ejercicio2);
        checkBoxes.add(ejercicio3);
        checkBoxes.add(ejercicio4);
        checkBoxes.add(ejercicio5);
        checkBoxes.add(ejercicio6);


        //con un bucle for recorremos todo
        for(int i = 0; i < checkBoxes.size(); i++){
            checkBoxes.get(i).setText(ejercicios.get(i).getNombreEjercicio());
        }


        //Si la información que enviamos no está vacía, entonces que ponga el texto enviado (SUPERIORES)
        /*if(nombreEjercicio1!=null) {

            //Le colocamos nombre a los ejercicios de la actividad
            ejercicio1.setText(nombreEjercicio1);
            ejercicio2.setText(nombreEjercicio2);
            ejercicio3.setText(nombreEjercicio3);
            ejercicio4.setText(nombreEjercicio4);
            ejercicio5.setText(nombreEjercicio5);
            ejercicio6.setText(nombreEjercicio6);

            checkBoxes.add(ejercicio1);
            checkBoxes.add(ejercicio2);
            checkBoxes.add(ejercicio3);
            checkBoxes.add(ejercicio4);
            checkBoxes.add(ejercicio5);
            checkBoxes.add(ejercicio6);
        }*/

        //con este bón envíamos información desde esta actividad a CronosEntrenar
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Con este bucle recorremos el arraylist de checkboxes
                for(int i = 0; i < checkBoxes.size(); i++){
                    //Si el checkbox esta seleccionado que ponga la información al array list nombre Ejercicios
                    if(checkBoxes.get(i).isChecked()){
                        //Esta línea es magnífica
                        nombreEjercicios.add(checkBoxes.get(i).getText().toString());
                    }
                }

                guardarPreferencias();

                //Creamos el intent dentro del onclick del botón Enviar
                Intent intentEnviar = new Intent (ListaEjerciciosActivity.this, MainActivity.class);



                //bundles para enviar información de los ejercicios, la clave es tipoEjercicios mientras la info
                //es nombreEjercicios agrupada en el list del array.
                //intentEnviar.putExtra(tipoEjercicios, nombreEjercicios); //Quizas poner nombreEjerciciosSup, inf, ab


                //Iniciamos el intent
                startActivity(intentEnviar);
            }
        });

    } //llave de cierre del onCreate

    private void guardarPreferencias() {
        SharedPreferences preferencias = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putStringSet(tipoEjercicios, nombreEjercicios);
        editor.apply();

    }

    private void obtenerEjercicios(String tipoEjercicios){

        SQLiteDatabase db = conexionSQLite.getReadableDatabase();

        String[] parametros = {tipoEjercicios};

        try{
            String select = "SELECT * FROM EJERCICIO WHERE tipoEjercicio = ?";
            Cursor cursor = db.rawQuery(select, parametros);

            while(cursor.moveToNext()){
                Ejercicio ej = new Ejercicio();
                ej.setId(cursor.getInt(0));
                ej.setNombreEjercicio(cursor.getString(1));
                ej.setTipoEjercicio(cursor.getString(2));

                ejercicios.add(ej);

            }
            cursor.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Se ha producido un error", Toast.LENGTH_LONG).show();
        }


    }


}