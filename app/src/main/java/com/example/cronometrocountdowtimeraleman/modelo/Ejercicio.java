package com.example.cronometrocountdowtimeraleman.modelo;

public class Ejercicio { //CLASE POJO (Plain Old Java Object)

    //Campos de clase que tendrá nuestra tabla Ejercicio
    private int id;
    private String nombreEjercicio;
    private String tipoEjercicio;

    //Creamos constructor por defecto
    public Ejercicio(){

    }

    //Creamos setter y getters de todos los campos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombre) {
        this.nombreEjercicio = nombre;
    }

    public String getTipoEjercicio() {
        return tipoEjercicio;
    }

    public void setTipoEjercicio(String tipo) {
        this.tipoEjercicio = tipo;
    }
}
