package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Libros;
import org.example.bibliotecafx.entities.Prestamos;
import org.example.bibliotecafx.entities.Socios;

import java.util.List;

public interface PrestamosDAO {

    List<Prestamos> getAllPrestamos();
    List<Prestamos> getHistorialPrestamosPorSocio(Socios socio);
    void registrarPrestamo(Prestamos prestamo);
}
