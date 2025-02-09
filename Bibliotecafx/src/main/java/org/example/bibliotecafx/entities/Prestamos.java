package org.example.bibliotecafx.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
public class Prestamos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "libro_isbn", nullable = false)
    private Libros libro;

    @ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
    private Socios socio;

    @Column(name = "fecha_prestamo", nullable = false)
    private LocalDate fechaPrestamo;

    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;

    // Constructor vacío
    public Prestamos() {
    }

    // Constructor completo
    public Prestamos(Libros libro, Socios socio, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.libro = libro;
        this.socio = socio;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Libros getLibro() {
        return libro;
    }

    public void setLibro(Libros libro) {
        this.libro = libro;
    }

    public Socios getSocio() {
        return socio;
    }

    public void setSocio(Socios socio) {
        this.socio = socio;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    // Método toString
    @Override
    public String toString() {
        return "Prestamo{" +
                "ID=" + id +
                ", Libro='" + libro.getTitulo() + '\'' +
                ", Socio='" + socio.getNombre() + '\'' +
                ", Fecha de Préstamo=" + fechaPrestamo +
                ", Fecha de Devolución=" + (fechaDevolucion != null ? fechaDevolucion : "No devuelto") +
                '}';
    }
}
