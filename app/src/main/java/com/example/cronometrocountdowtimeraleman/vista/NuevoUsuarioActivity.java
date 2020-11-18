package com.example.cronometrocountdowtimeraleman.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cronometrocountdowtimeraleman.R;
import com.example.cronometrocountdowtimeraleman.controlador.ConexionSQLite;
import com.example.cronometrocountdowtimeraleman.modelo.Usuario;


public class NuevoUsuarioActivity extends AppCompatActivity {

    //Creamos las variables para operar y asignar funcionalidad
    private Button mBtn_Guardar;
    private EditText et_nombre, et_apellidos, et_correo, et_clave, et_clave2;
    private Usuario nuevoUsuario;
    private long idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);

        //Mapeamos las variables con los elementos de la vista
        mBtn_Guardar = findViewById(R.id.btn_guardarNuevoUsuario);
        et_nombre = findViewById(R.id.et_nombre);
        et_apellidos = findViewById(R.id.et_apellidos);
        et_correo = findViewById(R.id.et_email);
        et_clave = findViewById(R.id.et_clave);
        et_clave2 = findViewById(R.id.et_clave2);


        //Creamos funcionalidad al boton guardar
        mBtn_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Instanciamos al objeto Usuario mediante su constructor por defecto
                nuevoUsuario = new Usuario();
                //Colocamos la informacion introducida por el usuario y lo pasamos
                // a String para poderla tratar
                nuevoUsuario.setNombre(et_nombre.getText().toString());
                nuevoUsuario.setApellidos(et_apellidos.getText().toString());
                nuevoUsuario.setCorreo(et_correo.getText().toString());
                nuevoUsuario.setClave(et_clave.getText().toString());

                //Esta variable no forma parte del objeto Usuario pq no formará
                // parte de las tablas de BBDD
                String clave2 = et_clave2.getText().toString();

                //Creamos condicionales que digan que todos los campos a rellenar son obligatorios
                //Así el usuario obtiene información de usabilidad con nuestra app
                if(nuevoUsuario.getNombre().isEmpty()
                        || nuevoUsuario.getApellidos().isEmpty()
                        || nuevoUsuario.getCorreo().isEmpty()
                        || nuevoUsuario.getClave().isEmpty()
                        || clave2.isEmpty()){

                    mostrarMensaje("Debe rellenar todos los campos");

                //Si los campos password no coinciden, entonces
                }else if(nuevoUsuario.getClave().equals(clave2) == false){

                    mostrarMensaje("¡Compruebe la contraseña!");

                }else{

                    //Realizamos la conexión a la BBDD
                    ConexionSQLite conexion = new ConexionSQLite(getApplicationContext());

                    //Aquí recogemos el long ID que retorna el metodo registrarUsuario
                    idUsuario = conexion.registrarUsuario(nuevoUsuario);
                    mostrarMensaje("Usuario registrado correctamente");

                    //Después de registrar al usuario le guardamos mediante un sharedpreferences
                    //toda su información para no tener que volver a loguearse de nuevo
                    guardarPreferenciasUsuario(nuevoUsuario);

                    //IR A LA APLICACIÓN PRINCIPAL
                    Intent i = new Intent(NuevoUsuarioActivity.this, MainActivity.class);
                    startActivity(i);
                }

            }
        });

    } //llave de cierre del metodo onCreate

    //Metodo para mostrar mensajes en un string y ahorrar código largo de hacer cada vez un toast
    private void mostrarMensaje(String mensaje){
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    //Metodo para guardar las preferencias (sharedPreferences) al crear al usuario.
    private void guardarPreferenciasUsuario(Usuario usuario){
        SharedPreferences preferencias = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        //Aquí le pasamos la información y hacemos que idUsuario esté asignado para saber el id que
        //tenga un usuario. Si ya tiene ID quiere decir que está registrado y no necesita loguearse de nuevo
        editor.putInt("usuarioId", (int) idUsuario);
        editor.putString("usuarioNombre", usuario.getNombre());
        editor.putString("usuarioApe", usuario.getApellidos());
        editor.putString("usuarioCorreo", usuario.getCorreo());
        editor.putString("usuarioClave", usuario.getClave());
        editor.apply();
    }
}