package com.remproyects.libreria.models.libro;

import com.remproyects.libreria.models.autor.Autor;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    private Autor autor;

    private String idioma;
    private Integer descargas;

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public Libro() { }

    public Libro(LibroData libroData) {
        this.titulo = libroData.titulo();
        this.autor = new Autor(libroData.autors().get(0), this);
        this.idioma = libroData.idiomas().get(0);
        this.descargas = libroData.descargas();
    }

    @Override
    public String toString() {
        return "------------Libro-----------"
                + "\n Titulo: " + titulo
                + "\n Autor: " + autor.getNombre()
                + "\n Idioma: " + idioma
                + "\n Numero de descargas: " + descargas
                + "\n----------------------------";
    }
}
