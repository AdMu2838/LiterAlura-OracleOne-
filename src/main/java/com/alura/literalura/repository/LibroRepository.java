package com.alura.literalura.repository;

import com.alura.literalura.model.Lenguaje;
import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {


    @Query("SELECT l FROM Libro l JOIN l.lenguajes leng WHERE leng = :idioma")
    List<Libro> obtenerIdiomas(Lenguaje idioma);
}