package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Autores;
import org.example.bibliotecafx.entities.Libros;

import java.util.List;

public interface LibrosDAO {

    boolean guardar(Libros libro);

    boolean modificar(Libros libro);

    boolean eliminar(Libros libro);

    List<Libros> buscarPorTitulo(String titulo);

    List<Libros> buscarPorAutor(String autor);

    List<Libros> buscarPorIsbn(String isbn);

    // Buscar libros por un término de búsqueda general
    List<Libros> buscar(String query);

    List<Libros> listarLibrosDisponibles();

    List<Libros> listarTodosLibros();

}
