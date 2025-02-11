package org.example.bibliotecafx.DAO;


import org.example.bibliotecafx.entities.Socios;

import java.util.List;

public interface SociosDAO {
    void agregarSocio(Socios socio);
    void actualizarSocio(Socios socio);
    void eliminarSocio(int id);
    Socios obtenerSocio(int id);
    List<Socios> obtenerTodosLosSocios();
}
