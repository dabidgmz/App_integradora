package com.example.app_integradora.Retroft;

public class InsertarVitrinaRequest {
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    String nombre;
    String descripcion;
    String empresa;

    public InsertarVitrinaRequest(String nombre, String descripcion, String empresa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.empresa = empresa;
    }

}
