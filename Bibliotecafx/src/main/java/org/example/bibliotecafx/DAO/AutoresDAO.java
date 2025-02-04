package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Autores;
import java.util.List;

public interface AutoresDAO {

    void agregarAutor(Autores autor);

    void modificarAutor(Autores autor);

    void eliminarAutor(Integer id);

    Autores buscarAutorPorNombre(String nombre);

    List<Autores> listarAutores();
}
