package com.example.cronometrocountdowtimeraleman.modelo;

import java.util.Date;

public class SesionesEnt { //CLASE POJO (Plain Old Java Object)

    //Campos de clase de la tabla sesiones entrenamiento
    private int id;
    private String nombreUsuario;
    private Date fecha;
    private String nombreEjercicio;
    private String tipoEjercicio;

    //Constructor vacío por defecto
    public SesionesEnt(){

    }

    //Métodos setter y getter de todos los campos de clase

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getTipoEjercicio() {
        return tipoEjercicio;
    }

    public void setTipoEjercicio(String tipoEjercicio) {
        this.tipoEjercicio = tipoEjercicio;
    }
}
