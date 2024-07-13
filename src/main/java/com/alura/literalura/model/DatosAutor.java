package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        //Json alias nos permite leer y con jsonproperty nos permite leer y escribir
        @JsonAlias("name") String nombreAutor,
        @JsonAlias("birth_year") String añoNac,
        @JsonAlias("death_year") String añoDef
) {
}