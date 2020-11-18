package com.example.cronometrocountdowtimeraleman.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cronometrocountdowtimeraleman.R;

import java.util.LinkedHashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    //Creamos las variables que vayamos a utilizar con los elementos de las vistas de la actividad
    Button mbtn_grupo1;
    Button mbtn_grupo2;
    Button mbtn_entrenar;
    Button btn_cerrarSesion;

    private Set<String> ejerciciosSuperiores;
    private Set<String> ejerciciosCardio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mapeamos las variables con las vistas (en este caso botones) de la actividad.
        mbtn_grupo1 = findViewById(R.id.btn_grupo1);
        mbtn_grupo2 = findViewById(R.id.btn_grupo2);
        mbtn_entrenar = findViewById(R.id.btn_entrenar);
        btn_cerrarSesion = findViewById(R.id.btn_cerrarSesion);

        //inicializamos las variables de tipo set
        //ejerciciosSuperiores = new LinkedHashSet<>();
        //ejerciciosCardio = new LinkedHashSet<>();

        //Recepcionamos el bundle envíado desde Lista_Ejercicios con los ejercicios seleccionados por el usuario
        Bundle informacion = getIntent().getExtras();

        cargarPreferencias();

        //TODO: INCORPORAR SHARED PREFERENCES

        //Este codigo recepcion el envío desde Lista_Ejercicios con los posibles ejercicios seleccionados por el usuario
        /*if(informacion != null){
            if(informacion.getStringArrayList("superiores") != null){
                ejerciciosSuperiores = informacion.getStringArrayList("superiores");
            }

            if(informacion.getStringArrayList("cardio") != null){
                ejerciciosCardio = informacion.getStringArrayList("cardio");
            }
        }*/


        //Le damos funcionalidad a los botones VER1, desde aquí envíamos datos a activity lista ejercicios
        mbtn_grupo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos un intent
                Intent intentVer = new Intent (MainActivity.this, ListaEjerciciosActivity.class);
                //Pasamos información mediante bundles
                /*intentVer.putExtra("Ejercicio1", "Superior1");
                intentVer.putExtra("Ejercicio2", "Superior2");
                intentVer.putExtra("Ejercicio3", "Superior3");
                intentVer.putExtra("Ejercicio4", "Superior4");
                intentVer.putExtra("Ejercicio5", "Superior5");
                intentVer.putExtra("Ejercicio6", "Superior6");*/
                intentVer.putExtra("tipoEjercicios", "Superior");
                //Iniciamos el intent
                startActivity(intentVer);
            }
        });

        //Le damos funcionalidad a los botones VER2
        mbtn_grupo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos un intent
                Intent intentVer = new Intent (MainActivity.this, ListaEjerciciosActivity.class);
                //Pasamos información mediante bundles
                /*intentVer.putExtra("Ejercicio1", "Cardio1");
                intentVer.putExtra("Ejercicio2", "Cardio2");
                intentVer.putExtra("Ejercicio3", "Cardio3");
                intentVer.putExtra("Ejercicio4", "Cardio4");
                intentVer.putExtra("Ejercicio5", "Cardio5");
                intentVer.putExtra("Ejercicio6", "Cardio6");*/
                intentVer.putExtra("tipoEjercicios", "Cardio");
                //Iniciamos el intent
                startActivity(intentVer);
            }
        });

        //Le damos funcionalidad a los botones A ENTRENAR!!!
        mbtn_entrenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ejerciciosSuperiores.size() != 0 || ejerciciosCardio.size() != 0){
                    //Creamos un intent
                    Intent intentEntrenar= new Intent (MainActivity.this, CronosEntrenarActivity.class);
                    //Con estos putExtra enviamos información a activity_cronos con los ejercicios seleccionados
                    //intentEntrenar.putExtra("Superior", (Parcelable) ejerciciosSuperiores);
                    //intentEntrenar.putExtra("Cardio", (Parcelable) ejerciciosCardio);
                    //Iniciamos el intent
                    startActivity(intentEntrenar);
                }else{
                    Toast.makeText(getApplicationContext(), "Debe seleccionar algún ejercicio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desloguearUsuario();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                Toast.makeText(getApplicationContext(), "Usuario deslogueado", Toast.LENGTH_SHORT).show();

            }
        });
    }//llave de cierre del onCreate

    //SharedPreferences
    private void cargarPreferencias() {
        SharedPreferences preferencias = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        ejerciciosSuperiores = preferencias.getStringSet("Superior", null);
        ejerciciosCardio = preferencias.getStringSet("Cardio", null);
    }

    //método para desloguear al Usuario
    private void desloguearUsuario(){
        SharedPreferences preferencias = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        //No sé como "absorver" el contenido que pone el usuario en los editText, agruparlos y guardarlos
        //en variables de tipo compatibles con los argumentos de este método.
        editor.putInt("usuarioId", 0);
        editor.putString("usuarioNombre", null);
        editor.putString("usuarioApe", null);
        editor.putString("usuarioCorreo", null);
        editor.putString("usuarioClave", null);
        editor.apply();
    }
}