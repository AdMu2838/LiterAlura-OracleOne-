package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    //private final String API_KEY = "&apikey=853fc505";
    private ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private Optional<Libro> libroBuscado;
    public Principal(LibroRepository repository, AutorRepository repository2) {
        this.libroRepository = repository;
        this.autorRepository= repository2;
    }

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
                    0- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1 -> buscarLibro();
                case 2 -> mostrarTodosLibros();
                case 3 -> mostrarAutoresRegistrados();
                case 4 -> mostrarVivos();
                case 5 -> obtenerLibrosIdioma();

                case 0 -> System.out.println("Cerrando la aplicación. Gracias por sus consultas.");
                default -> System.out.println("Opción inválida");
            }
        }
    }
    public void obtenerLibrosIdioma() {
        System.out.println("Ingrese el idioma que desea listar: ");
        var idioma = teclado.nextLine();

        try {
            Lenguaje idiomaLenguaje = Lenguaje.fromString(idioma);
            List<Libro> librosEnIdioma = libroRepository.obtenerIdiomas(idiomaLenguaje);

            if (librosEnIdioma.isEmpty()) {
                System.out.println("No se encontraron libros en el idioma " + idiomaLenguaje);
            } else {
                System.out.println("Libros en el idioma " + idiomaLenguaje + ":");
                for (Libro libro : librosEnIdioma) {
                    System.out.println(libro.getTitulo());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Idioma no válido. Por favor, ingrese 'es' para español o 'en' para inglés.");
        }
    }

    private void mostrarVivos() {
        System.out.println("Ingrese el año de búsqueda:");
        var añoBusqueda = teclado.nextLine();
        List<Autor> autoresVivos = autorRepository.obtenerVivosPorAño(añoBusqueda);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + añoBusqueda);
        } else {
            System.out.println("Autores vivos en el año " + añoBusqueda + ":");
            for (Autor autor : autoresVivos) {
                System.out.println(autor.getNombreAutor());
            }
        }
    }

    private void mostrarAutoresRegistrados(){
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    private void mostrarTodosLibros(){
        List<Libro> libros = libroRepository.findAll();
        libros.forEach(System.out::println);
    }
    private void buscarLibro() {
        DatosLibro datosLibro = getDatosLibro();
        if (datosLibro == null) {
            System.out.println("No se encontró el libro.");
            return;
        }

        Libro libroBuscado = new Libro(datosLibro);
        System.out.println("Libro agregado con éxito");
        System.out.println(libroBuscado);
        libroRepository.save(libroBuscado);
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Ingrese el libro que está buscando");
        var libroBuscado = teclado.nextLine().toLowerCase().replace(" ", "%20");

        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + libroBuscado);
        // los resultados
        DatosResult datos = conversor.obtenerDatos(json, DatosResult.class);
        // verificar si hay libros en la lista uwu
        if (datos.libro() == null || datos.libro().isEmpty()) {
            return null;
        }
        // escogemos el primero
        return datos.libro().get(0);
    }

}
