package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name= "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;


    @ElementCollection(targetClass = Lenguaje.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    //@Column(name = "lenguaje")
    private List<Lenguaje> lenguajes;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return
                "Id=" + id +
                        " Titulo de la obra: '" + titulo + '\'' +
                        " Lenguajes: " + lenguajes +
                        " Autores: " + autores;
    }

    public Libro() {}

    public Libro(Long id, String titulo, List<Autor> autores, List<Lenguaje> lenguajes) {
        this.id = id;
        this.titulo = titulo;
        this.autores = autores;
        this.lenguajes = lenguajes;
    }
    public Libro (DatosLibro datosLibro){
        this.id = datosLibro.id();
        this.titulo = datosLibro.titulo();
        this.autores = datosLibro.autores().stream()
                .map(a -> new Autor( a.nombreAutor(), a.añoNac(), a.añoDef(), this)).collect(Collectors.toList());
        this.lenguajes = datosLibro.lenguaje().stream()
                .map(Lenguaje::fromString).collect(Collectors.toList());
    }
}