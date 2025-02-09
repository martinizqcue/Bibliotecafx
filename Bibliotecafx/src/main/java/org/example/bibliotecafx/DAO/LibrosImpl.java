package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Autores;
import org.example.bibliotecafx.entities.Libros;
import org.example.bibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LibrosImpl implements LibrosDAO {

    @Override
    public Libros save(Libros libro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(libro);
        session.getTransaction().commit();
        session.close();
        return libro;
    }


    @Override
    public void agregarLibro(Libros libro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void modificarLibro(Libros libro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void eliminarLibro(String isbn) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Libros libro = session.get(Libros.class, isbn);
            if (libro != null) {
                session.delete(libro);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Libros buscarLibro(String criterio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Libros libro = null;
        try {
            libro = session.createQuery("FROM Libros WHERE titulo = :criterio OR isbn = :criterio", Libros.class)
                    .setParameter("criterio", criterio)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return libro;
    }

    @Override
    public List<Libros> listarLibrosDisponibles() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Libros> libros = null;
        try {
            libros = session.createQuery("FROM Libros WHERE isbn NOT IN (SELECT libro.isbn FROM Prestamos WHERE fechaDevolucion IS NULL)", Libros.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return libros;
    }
}
