package com.alura.literalura.service;

import com.alura.literalura.model.ApiResponse;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    private final ApiService apiService;
    private final AutorService autorService;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    @Autowired
    public LibroService(ApiService apiService,AutorService autorService, LibroRepository libroRepository,AutorRepository autorRepository) {
        this.apiService = apiService;
        this.autorService = autorService;
        this.libroRepository = libroRepository;
        this.autorRepository=autorRepository;

    }
    public void buscarLibroPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "%20");
        ApiService apiService = new ApiService();
        DataConverter dataConverter = new DataConverter();

        try {
            String jsonResponse = apiService.obtenerDatos(url);
            ApiResponse apiResponse = dataConverter.obtenerDatos(jsonResponse, ApiResponse.class);

            if (apiResponse.results().isEmpty()) {
                mostrarMensaje("No se encontró un libro con ese título");
                return;
            }

            Libro nuevoLibro = new Libro(apiResponse.results().get(0));

            if (libroRepository.findByTitleIgnoreCase(nuevoLibro.getTitle()).isPresent()) {
                mostrarMensaje("El libro ya existe");
                return;
            }

            Autor autor = autorRepository.findFirstByName(nuevoLibro.getAutor().getName())
                    .orElseGet(() -> {
                        Autor nuevoAutor = nuevoLibro.getAutor();
                        autorRepository.save(nuevoAutor);
                        return nuevoAutor;
                    });
            nuevoLibro.setAutor(autor);
            libroRepository.save(nuevoLibro);
            System.out.println(nuevoLibro);

        } catch (RuntimeException e) {
            mostrarMensaje("Error: " + e.getMessage());
        }
    }
    private void mostrarMensaje(String mensaje) {
        System.out.println("////////////////////////////////////////////////");
        System.out.println(mensaje);
        System.out.println("////////////////////////////////////////////////");
    }
}