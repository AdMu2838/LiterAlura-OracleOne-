package com.alura.literalura.principal;

import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=853fc505";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraMenu() {
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                    ***** ELIJA UNA OPCION *****
                    1- Buscar libro por título
                    2- Lista de libros registrados
                    3- Lista de autores registrados
                    4- Lista de autores vivos en un año determinado
                    5- Lista de libros por idioma
                    6- Top 10 libros más descargados
                    7- Estadísticas
                    0- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {


                case 0 -> System.out.println("Cerrando la aplicación. Gracias por sus consultas.");
                default -> System.out.println("Opción inválida");
            }
        }
    }

}
