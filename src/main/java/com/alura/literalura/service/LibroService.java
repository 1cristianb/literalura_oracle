package com.alura.literalura.service;


import com.alura.literalura.model.ApiResponse;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.model.LibroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    private final ApiService apiService;
   // private final AutorService autorService;
   // private final LibroRepository libroRepository;
   // private final AutorRepository autorRepository;
    @Autowired
    public LibroService(ApiService apiService  /*,AutorService autorService, LibroRepository libroRepository,AutorRepository autorRepository*/) {
        this.apiService = apiService;
       /* this.autorService = autorService;
        this.libroRepository = libroRepository;
        this.autorRepository=autorRepository;*/

    }
    public void buscarLibroPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "%20");
        ApiService api= new ApiService();
        var json=api.obtenerDatos(url);
        DataConverter conversor=new DataConverter();

        ApiResponse respuesta = conversor.obtenerDatos(json, ApiResponse.class);
        Libro libro=new Libro(respuesta.results().get(0));
        System.out.println(libro);
    }
}
