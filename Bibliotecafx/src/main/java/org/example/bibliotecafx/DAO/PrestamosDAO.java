package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Prestamos;

import java.util.List;

public interface PrestamosDAO {

    // Método para registrar un préstamo de libro
    void registrarPrestamo(Prestamos prestamo);

    // Método para listar los libros prestados actualmente
    List<Prestamos> listarLibrosPrestados();

    // Método para listar el historial de préstamos de un socio
    List<Prestamos> listarHistorialPrestamosSocio(Integer idSocio);
}
