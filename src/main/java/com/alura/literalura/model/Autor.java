package com.alura.literalura.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nombreAutor;
    private String añoNac;
    private String añoDef;

    @ManyToOne
    private Libro libro;



    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getAñoNac() {
        return añoNac;
    }

    public void setAñoNac(String añoNac) {
        this.añoNac = añoNac;
    }

    public String getAñoDef() {
        return añoDef;
    }

    public void setAñoDef(String añoDef) {
        this.añoDef = añoDef;
    }
    @Override
    public String toString() {
        return
                "Id=" + id +
                        ", Nombre del Autor: '" + nombreAutor + '\'' +
                        ", Año de nacimiento: '" + añoNac + '\'' +
                        ", Año de defunción: '" + añoDef + '\'';

    }


    public Autor() {
    }

    public Autor( String nombreAutor, String añoNac, String añoDef, Libro libro) {

        this.nombreAutor = nombreAutor;
        this.añoNac = añoNac;
        this.añoDef = añoDef;
        this.libro = libro;
    }
}