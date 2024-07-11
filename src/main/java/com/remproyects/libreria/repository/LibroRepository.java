package com.remproyects.libreria.repository;

import com.remproyects.libreria.models.autor.Autor;
import com.remproyects.libreria.models.libro.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByAutor(Autor autor);
    List<Libro> findByIdioma(String idioma);
    Optional<Libro> findByTitulo(String titulo);
}
