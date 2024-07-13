package com.alura.literalura.model;

public enum Lenguaje {
    ESPAÑOL("es","español"),
    INGLES("en", "ingles"),;

    private String lenguajeGutendex;
    private String lenguajeEspañol;

    Lenguaje(String lenguajeGutendex, String lenguajeEspañol){
        this.lenguajeEspañol = lenguajeEspañol;
        this.lenguajeGutendex = lenguajeGutendex;
    }
    //Convertir la categoria a string
    public static Lenguaje fromString(String text) {
        for (Lenguaje lenguaje: Lenguaje.values()){
            if (lenguaje.lenguajeGutendex.equalsIgnoreCase(text) || lenguaje.lenguajeEspañol.equalsIgnoreCase(text)){
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: "+ text);
    }

}