package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;
    private String lenguajes;
    private Integer downloads;

    public Libro(LibroDTO libroDTO) {
        this.title = libroDTO.title();
        if (libroDTO.authors() != null && !libroDTO.authors().isEmpty()) {
            this.autor = new Autor(libroDTO.authors().get(0));
        } else {
            this.autor = null;
        }
        this.lenguajes = libroDTO.languages().get(0);
        this.downloads = libroDTO.downloads();
    }

    public Libro() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(String lenguajes) {
        this.lenguajes = lenguajes;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return String.format("""
            ////////////////////////////////////////////////
            -------------------- LIBRO ---------------------
            ////////////////////////////////////////////////
            Título: %s
            Autor: %s
            Idioma: %s
            Número de descargas: %s
            ////////////////////////////////////////////////""",
                title, autor.getName(), lenguajes, downloads);
    }
}