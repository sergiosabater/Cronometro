package com.example.cronometrocountdowtimeraleman.modelo;

public class Usuario { //CLASE POJO (Plain Old Java Object)
    //Esta será una clase POJO (Plain Old Java Object) en ella se la da
    // forma de objeto a una de las tablas de la BBDD


    //Campos de clase
    private int id;
    private String nombre;
    private String apellidos;
    private String correo;
    private String clave;

    //Constructor vacío por defecto
    public Usuario(){
    }

    //métodos setters y getters
    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
