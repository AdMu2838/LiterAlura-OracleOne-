package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.añoNac <= :añoBusqueda AND (a.añoDef IS NULL OR a.añoDef > :añoBusqueda)")
    List<Autor> obtenerVivosPorAño(String añoBusqueda);
}