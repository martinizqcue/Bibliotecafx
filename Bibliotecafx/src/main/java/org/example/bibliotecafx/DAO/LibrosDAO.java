package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Autores;
import org.example.bibliotecafx.entities.Libros;

import java.util.List;

public interface LibrosDAO {
    // Operación para agregar un libro
    Libros save(Libros libro);

    void agregarLibro(Libros libro);

    // Operación para modificar un libro
    void modificarLibro(Libros libro);

    // Operación para eliminar un libro
    void eliminarLibro(String isbn);

    // Operación para buscar un libro por título, autor o ISBN
    Libros buscarLibro(String criterio);

    // Operación para listar todos los libros no prestados
    List<Libros> listarLibrosDisponibles();

}
