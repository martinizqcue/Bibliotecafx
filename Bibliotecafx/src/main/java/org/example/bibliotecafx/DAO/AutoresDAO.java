package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Autores;

import java.util.List;

public interface AutoresDAO {
    // Operación para agregar un autor
    void agregarAutor(Autores autor);

    // Operación para modificar un autor
    void modificarAutor(Autores autor);

    // Operación para eliminar un autor
    void eliminarAutor(Integer id);

    // Operación para buscar un autor por nombre
    Autores buscarAutorPorNombre(String nombre);

    // Operación para listar todos los autores
    List<Autores> listarAutores();
}
