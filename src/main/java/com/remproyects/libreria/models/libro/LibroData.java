package com.remproyects.libreria.models.libro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.remproyects.libreria.models.autor.AutorData;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroData(
     @JsonProperty("title") String titulo,
     @JsonProperty("authors") List<AutorData> autors,
     @JsonProperty("languages") List<String> idiomas,
     @JsonProperty("download_count")Integer descargas
) {  }
