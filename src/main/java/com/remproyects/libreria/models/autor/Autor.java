package com.remproyects.libreria.models.autor;

import com.remproyects.libreria.models.libro.Libro;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private LocalDate fechaNacimiento;

    private LocalDate fechaMuerte;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Libro> libros;

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public LocalDate getFechaMuerte() {
        return fechaMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    private LocalDate localDateFromYearString(String year){
        return LocalDate.of(Integer.parseInt(year),1,1);
    }

    public Autor() {}

    public Autor(AutorData autorData, Libro libro) {
        this.fechaMuerte = localDateFromYearString(autorData.fechaMuerte());
        this.fechaNacimiento = localDateFromYearString(autorData.fechaNacimiento());
        this.nombre = autorData.nombre();
        this.libros = List.of(libro);
    }


    public String toString(List<Libro> libros) {
        StringBuilder librosString = new StringBuilder();
        libros.forEach(L -> librosString.append(", ").append(L.getTitulo()));
        return "Autor: " + this.getNombre() +
                "\nFecha de nacimiento: " + this.getFechaNacimiento().getYear() +
                "\nFecha de fallecimiento: " + this.getFechaMuerte().getYear() +
                "\nLibros: [" + librosString.substring(2) + "]";

    }
}
