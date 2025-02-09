package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Socios;

import java.util.List;

public interface SociosDAO {

    // Método para agregar un nuevo socio
    void agregarSocio(Socios socio);

    // Método para modificar un socio
    void modificarSocio(Socios socio);

    // Método para eliminar un socio
    void eliminarSocio(Integer id);

    // Método para buscar un socio por nombre
    Socios buscarSocioPorNombre(String nombre);

    // Método para buscar un socio por número de teléfono
    Socios buscarSocioPorTelefono(Integer telefono);

    // Método para listar todos los socios
    List<Socios> listarSocios();
}
