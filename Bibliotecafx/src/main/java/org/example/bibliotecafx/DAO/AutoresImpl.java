package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Autores;
import org.example.bibliotecafx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AutoresDAOImpl implements AutoresDAO {

    // Método para agregar un autor
    @Override
    public void agregarAutor(Autores autor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(autor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para modificar un autor
    @Override
    public void modificarAutor(Autores autor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(autor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para eliminar un autor
    @Override
    public void eliminarAutor(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Autores autor = session.get(Autores.class, id);
            if (autor != null) {
                session.delete(autor);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para buscar un autor por nombre
    @Override
    public Autores buscarAutorPorNombre(String nombre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Autores autor = null;

        try {
            autor = session.createQuery("FROM Autores WHERE nombre = :nombre", Autores.class)
                    .setParameter("nombre", nombre)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return autor;
    }

    // Método para listar todos los autores
    @Override
    public List<Autores> listarAutores() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Autores> autores = null;

        try {
            autores = session.createQuery("FROM Autores", Autores.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return autores;
    }
}
