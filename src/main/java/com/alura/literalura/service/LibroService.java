package com.alura.literalura.service;

import com.alura.literalura.model.ApiResponse;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

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

    public void listarLibrosRegistrados() {
        libroRepository.findAll().forEach(System.out::println);
    }

    public void buscarPorLenguaje(String lenguaje) {

        List<Libro> libros = libroRepository.findByLenguajes(lenguaje);
        if (libros.isEmpty()) {
            mostrarMensaje("No se encontraron libros en el idioma: " + lenguaje);
        } else {
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("Libros encontrados en el idioma: " + lenguaje);
            System.out.println("Cantidad de libros : " +libros.size());
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||");
            libros.forEach(System.out::println);
        }
    }

    public void generarEstadisticas() {
       List<Libro>libros= libroRepository.findAll();

        DoubleSummaryStatistics stats=libros.stream()
                .mapToDouble(Libro::getDownloads)
                .summaryStatistics();
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|||||||||||Estadisticas de descargas||||||||||||");
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("Promedio: " + stats.getAverage());
        System.out.println("Máximo: " + stats.getMax());
        System.out.println("Mínimo: " + stats.getMin());
        System.out.println("Total libros: " + stats.getSum());
    }

    public void top10LibrosMasDescargados() {
        List<Libro>libros=libroRepository.findAll().stream()
                .sorted((l1, l2) -> l2.getDownloads().compareTo(l1.getDownloads()))
                .limit(10)
                .collect(Collectors.toList());
        libros.forEach(System.out::println);
    }
}