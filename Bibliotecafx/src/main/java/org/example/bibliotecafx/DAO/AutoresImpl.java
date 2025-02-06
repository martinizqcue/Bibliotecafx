package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.Autores;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AutoresImpl implements AutoresDAO {

    // Método para agregar un autor
    @Override
    public void agregarAutor(Autores autor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(autor);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para modificar un autor
    @Override
    public void modificarAutor(Autores autor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(autor);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un autor
    @Override
    public void eliminarAutor(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Autores autor = session.get(Autores.class, id);
            if (autor != null) {
                session.delete(autor);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para buscar un autor por nombre
    @Override
    public Autores buscarAutorPorNombre(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Autores WHERE nombre = :nombre", Autores.class)
                    .setParameter("nombre", nombre)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para listar todos los autores
    @Override
    public List<Autores> listarAutores() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Autores> autores = null;

        try {
            transaction = session.beginTransaction();
            autores = session.createQuery("FROM Autores", Autores.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return autores;
    }
}
