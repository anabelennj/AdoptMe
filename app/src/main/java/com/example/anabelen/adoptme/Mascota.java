package com.example.anabelen.adoptme;

/**
 * Created by Ana Belen on 24/08/2016.
 */
public class Mascota {
    private String tipo, raza, edad, estado, genero;

    public Mascota(String tipo, String raza, String edad, String estado, String genero) {
        this.tipo = tipo;
        this.raza = raza;
        this.edad = edad;
        this.estado = estado;
        this.genero = genero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
