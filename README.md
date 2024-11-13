# LiterAlura | Challenge de Alura - ONE Oracle
<p align="center" >
  <img src="https://app.aluracursos.com/assets/images/logos/logo-aluraespanhol.svg" alt="Alura logo">
  <img src="https://cdn2.gnarususercontent.com.br/6/449886/e4621638-6168-4948-a623-76dcfdefd99c.png" alt="ONE Oracle logo">
</p>

**LiterAlura** es un proyecto interactivo que permite la gestión y consulta de un catálogo de libros y autores. Fue desarrollado para explorar y practicar habilidades de desarrollo en Java, integrando consultas a una API de libros, manejo de datos JSON, persistencia en base de datos y funcionalidades como estadísticas y búsqueda optimizada.

## Descripción

El proyecto permite a los usuarios:
- Buscar libros por título y mostrar detalles, incluyendo el autor y los idiomas disponibles.
- Guardar libros y autores en una base de datos si no existen previamente.
- Consultar un top 10 de libros más descargados.
- Buscar autores por nombre y listar autores por año de nacimiento.
- Generar estadísticas sobre las descargas de los libros.

## Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación principal.
- **Spring Boot**: Framework para la creación de aplicaciones Java.
- **JPA (Java Persistence API)**: Para la gestión de la persistencia de datos.
- **PostgreSQL**: Base de datos utilizada para almacenamiento de datos.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.

## Configuración y Ejecución

1. Clona este repositorio:
   ```bash
   git clone https://github.com/1cristianb/literalura_oracle.git
   cd literalura_oracle
   
2. Configura la base de datos en el archivo application.properties, en este caso se utilizaron variables de entorno.Ejemplo:
```
spring.application.name=literalura

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.HSQLDialect

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format_sql=true
```
3. Compila y ejecuta el proyecto

## Autor
Cristian Boschi
