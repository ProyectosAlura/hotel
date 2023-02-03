package com.challenge.hotel.model;

public class Huesped {
    Integer id;
    String nombre;
    String apellido;
    String fechaNacimiento;
    String nacionalidad;
    String telefono;
    Integer idReserva;

    public Huesped(String nombre,String apellido,String fechaNacimiento,String nacionalidad,String telefono,Integer idReserva){
        this.nombre=nombre;
        this.apellido=apellido;
        this.fechaNacimiento=fechaNacimiento;
        this.nacionalidad=nacionalidad;
        this.telefono=telefono;
        this.idReserva=idReserva;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public Integer getId() {
        return id;
    }
    public Integer getIdReserva() {
        return idReserva;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTelefono() {
        return telefono;
    }

}
