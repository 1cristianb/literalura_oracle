package com.alura.literalura.app;

import com.alura.literalura.service.AutorService;
import com.alura.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class App {
    private final LibroService libroService;
    private final AutorService autorService;
    private Scanner scanner = new Scanner(System.in);
    @Autowired
    public App(LibroService libroService,AutorService autorService) {
        this.libroService = libroService;
        this.autorService=autorService;
    }
    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("------------------------------------------------");
            System.out.println("************ Bienvenido a LiterAlura ***********");
            System.out.println("------------------------------------------------");
            System.out.println("1. Buscar libro por título.");
            System.out.println("2. Listar libros registrados.");
            System.out.println("3. Listar autores registrados.");
            System.out.println("4. Listar autores vivos en un determinado año.");
            System.out.println("5. Listar libros y su cantidad por idioma.");
            System.out.println("------------------------------------------------");
            System.out.println("6. Estadisticas de libros descargados.");
            System.out.println("7. Top 10 libros más descargados.");
            System.out.println("8. Buscar autor por nombre.");
            System.out.println("9. Ordenar autores por año de nacimiento.");
            System.out.println("------------------------------------------------");
            System.out.println("0. Salir.");
            System.out.println("------------------------------------------------");
            System.out.println("Elige una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosPorFecha();
                        break;
                    case 5:
                        listarLibrosPorIdioma();
                        break;
                    case 6:
                        generarEstadisticas();
                        break;
                    case 7:
                        top10LibrosMasDescargados();
                        break;
                    case 8:
                        buscarAutorPorNombre();
                        break;
                    case 9:
                        ordenarAutoresPorNacimiento();
                        break;
                    case 0:
                        salir = true;
                        System.out.println("Saliendo de LiterAlura. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida, por favor intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                scanner.nextLine();
            }
        }
    }

    private void ordenarAutoresPorNacimiento() {
        autorService.ordenarAutoresPorNacimiento();
    }

    private void buscarAutorPorNombre() {
        System.out.println("Ingrese el nombre del autor: ");
        String nombre = scanner.nextLine();
        autorService.buscarAutorPorNombre(nombre);
    }

    private void top10LibrosMasDescargados() {
        libroService.top10LibrosMasDescargados();
    }

    private void generarEstadisticas() {
        libroService.generarEstadisticas();
    }

    private void listarAutoresVivosPorFecha() {
        System.out.println("Ingrese el año: ");
        int fecha = scanner.nextInt();
        scanner.nextLine();
        autorService.listarAutoresVivosPorFecha(fecha);
    }

    private void listarAutoresRegistrados() {
        autorService.listarAutoresRegistrados();
    }

    private void listarLibrosRegistrados() {
        libroService.listarLibrosRegistrados();
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        libroService.buscarLibroPorTitulo(titulo);
    }
    private void listarLibrosPorIdioma() {
        boolean salir = false;

        while (!salir) {
            System.out.println("------------------------------------------------");
            System.out.println("1. INGLES(EN)");
            System.out.println("2. ESPAÑOL(ES)");
            System.out.println("3. PORTUGUES(PT)");
            System.out.println("4. ALEMAN(DE)");
            System.out.println("5. ITALIANO(IT)");
            System.out.println("6. FRANCES(FR)");
            System.out.println("7. LATIN(LA)");
            System.out.println("0. Salir.");
            System.out.println("------------------------------------------------");
            System.out.println("Elige una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        buscarPorLenguaje("en");
                        break;
                    case 2:
                        buscarPorLenguaje("es");
                        break;
                    case 3:
                        buscarPorLenguaje("pt");
                        break;
                    case 4:
                        buscarPorLenguaje("de");
                        break;
                    case 5:
                        buscarPorLenguaje("it");
                        break;
                    case 6:
                        buscarPorLenguaje("fr");
                        break;
                    case 7:
                        buscarPorLenguaje("la");
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida, por favor intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                scanner.nextLine();
            }
        }
    }

    private void buscarPorLenguaje(String lenguaje)
    {
        libroService.buscarPorLenguaje(lenguaje);
    }
}
