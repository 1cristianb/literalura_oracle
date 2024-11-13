package com.alura.literalura.service;


import com.alura.literalura.model.Autor;
import com.alura.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;
    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }
    public void listarAutoresRegistrados() {
        autorRepository.findAll().forEach(System.out::println);
    }

    public void listarAutoresVivosPorFecha(int fecha) {
        List<Autor> autoresVivos=autorRepository.findAutoresVivosEnAnio(fecha);
        if (autoresVivos.isEmpty()) {
            mostrarMensaje("No se encontraron autores vivos en el año: " + fecha);
        } else {
            System.out.println("Autores vivos en el año " + fecha +" :");
            autoresVivos.forEach(System.out::println);
        }
    }
    private void mostrarMensaje(String mensaje) {
        System.out.println("////////////////////////////////////////////////");
        System.out.println(mensaje);
        System.out.println("////////////////////////////////////////////////");
    }

}