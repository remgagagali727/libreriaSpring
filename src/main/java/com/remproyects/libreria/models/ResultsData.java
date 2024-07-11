package com.remproyects.libreria.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.remproyects.libreria.models.libro.LibroData;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultsData(
        @JsonProperty("results") List<LibroData> libroList
) { }
