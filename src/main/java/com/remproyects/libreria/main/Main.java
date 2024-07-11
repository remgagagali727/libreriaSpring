package com.remproyects.libreria.main;

import com.remproyects.libreria.data.JsonTransformer;
import com.remproyects.libreria.models.ResultsData;
import com.remproyects.libreria.models.autor.Autor;
import com.remproyects.libreria.models.libro.Libro;
import com.remproyects.libreria.repository.AutorRepository;
import com.remproyects.libreria.repository.LibroRepository;
import com.remproyects.libreria.service.ApiConnector;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    private final JsonTransformer jsonTransformer = new JsonTransformer();

    public Main(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
        boolean salir = true;
        while (salir) {
            char opc = showMenu("""
                    Elije una opcion:
                    1. Buscar libro
                    2. Libros Registrados
                    3. Autores Registrados
                    4. Autores vivos en un año
                    5. Libros por idioma
                    
                    0. Salir
                    """).charAt(0);
            switch (opc) {
                case '1':
                    searchBook();
                    break;
                case '2':
                    showAllBooks();
                    break;
                case '3':
                    showAllAutors();
                    break;
                case '4':
                    showIfLiving();
                    break;
                case '5':
                    showByLanguage();
                    break;
                case '0':
                    salir = false;
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
        System.exit(0);
    }

    private void showByLanguage() {
        String idioma = showMenu("""
                Ingrese el idioma para buscar los libros:
                es - español
                en - ingles
                fr - frances
                pt - portugues
                """).substring(0,2);
        List<Libro> libros = libroRepository.findByIdioma(idioma);
        libros.forEach(System.out::println);
    }

    private void showIfLiving() {
        boolean salir = true;
        LocalDate localDate = LocalDate.MAX;
        while (salir) {
            String year = showMenu("Dame un año");
            try {
                localDate = LocalDate.parse(year+"/01/01", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                salir = false;
            } catch (DateTimeParseException e) {
                System.out.println("Dato de fecha no valido intente de nuevo");
            }
        }
        Autor autor = new Autor();
        List<Autor> autors = autorRepository.findByFechaNacimientoLessThanEqualAndFechaMuerteGreaterThan(localDate, localDate);
        autors.forEach(A -> System.out.println(
                A.toString(libroRepository.findByAutor(A)
                )));
    }

    private void showAllAutors() {
        List<Autor> autors = autorRepository.findAll();
        autors.forEach(A -> System.out.println(
                A.toString(libroRepository.findByAutor(A)
                )));
    }

    private void showAllBooks() {
        List<Libro> libros = libroRepository.findAll();
        libros.forEach(System.out::println);
    }

    private void searchBook() {
        String name = showMenu("Dame el nombre del libro");
        String json = ApiConnector.getData("http://gutendex.com/books/?search=" + name.replace(" ", "+"));
        ResultsData resultsData = jsonTransformer.fromJson(json, ResultsData.class);
        if(resultsData.libroList().isEmpty()) {
            System.out.println("Libro no encontrado");
            return;
        }
        Libro libro = new Libro(resultsData.libroList().get(0));
        Optional<Autor> autor = autorRepository.findByNombre(libro.getAutor().getNombre());
        if(autor.isPresent()) {
            libro.setAutor(autor.get());
            Optional<Libro> nLibro = libroRepository.findByTitulo(libro.getTitulo());
            if(nLibro.isPresent()) {
                System.out.println("No se puede agregar el mismo libro 2 veces");
            } else {
                libroRepository.save(libro);
                System.out.println("Libro agregado correctamente");
            }
        } else {
            autorRepository.save(libro.getAutor());
        }
    }

    private String showMenu(String s) {
        System.out.println(s);
        return new Scanner(System.in).nextLine();
    }

}
