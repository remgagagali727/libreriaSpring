package com.remproyects.libreria.models.autor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.remproyects.libreria.models.libro.Libro;
import com.remproyects.libreria.models.libro.LibroData;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorData(
        @JsonProperty("name") String nombre,

        @JsonProperty("birth_year") String fechaNacimiento,

        @JsonProperty("death_year") String fechaMuerte
) { }
