package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Autores;
import java.util.List;

public interface AutoresDAO {

    List<Autores> findAll();

    List<Autores> findByNombre(String Nombre);

    Autores save(Autores Autor);

    Autores update(Autores Autor);

    boolean delete(Autores Autor);


}
