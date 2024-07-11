package com.remproyects.libreria.repository;

import com.remproyects.libreria.models.autor.Autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);
    List<Autor> findByFechaNacimientoLessThanEqualAndFechaMuerteGreaterThan(LocalDate localDate, LocalDate localDate1);
}
