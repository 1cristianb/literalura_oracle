package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro> books;

    public Autor(AutorDTO autorDTO) {
        this.name = autorDTO.name();
        this.birthYear = autorDTO.birthYear();
        this.deathYear = autorDTO.deathYear();
        this.books=new ArrayList<>();
    }
    public Autor(){}
    public List<Libro> getBooks() {
        return books;
    }

    public void setBooks(List<Libro> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        return String.format("""
        ////////////////////////////////////////////////
        -------------------- AUTOR ---------------------
        ////////////////////////////////////////////////
        Nombre: %s
        Año de nacimiento: %s
        Año de muerte: %s
        ////////////////////////////////////////////////"""
                , name, birthYear, deathYear);
    }
}

