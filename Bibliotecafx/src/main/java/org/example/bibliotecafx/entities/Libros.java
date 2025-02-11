package org.example.bibliotecafx.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Libros")
public class Libros implements Serializable {

    @Id
    @Column(length = 13)
    private String isbn;


    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autores autor;


    private String editorial;

    @Column(name = "anio_publicacion")
    private int anioPublicacion;

    // Constructor vacío
    public Libros() {
    }

    // Constructor completo
    public Libros(String isbn, String titulo, Autores autor, String editorial, int anioPublicacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
    }

    // Getters y Setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    // Método toString
    @Override
    public String toString() {
        return " ISBN:" + isbn + " Titulo:" + titulo + " Autor:" +  autor + " Editorial:" + editorial + " AñoPublicacion:" + anioPublicacion;
    }
}
