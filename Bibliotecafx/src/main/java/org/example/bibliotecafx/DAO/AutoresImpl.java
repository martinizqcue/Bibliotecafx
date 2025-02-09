package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.Autores;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AutoresImpl implements AutoresDAO {
    @Override
    public List<Autores> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Realizamos la consulta
        List<Autores> autores = session.createQuery("from Autor", Autores.class).list();

        session.getTransaction().commit();
        session.close();

        // Imprimir el tamaño de la lista para comprobar si contiene elementos
        System.out.println("Tamaño de la lista de autores: " + autores.size());

        return autores;
    }

    public List<Autores> findByNombre(String nombre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Autores> autores = session.createQuery("from Autor where nombre like :nombre", Autores.class)
                .setParameter("nombre", "%" + nombre + "%") // Busca coincidencias parciales
                .list();
        session.close();
        return autores;
    }

    @Override
    public Autores save(Autores autores) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(autores);
        session.getTransaction().commit();
        session.close();
        return autores;

    }

    @Override
    public Autores update(Autores autor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            session.update(autor);  // Actualizamos el autor directamente
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback(); // Revertimos si hay un error
            e.printStackTrace();
        } finally {
            session.close();
        }

        return autor;
    }



    @Override
    public boolean delete(Autores Autor) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(session.get(Autores.class, Autor.getId()));
        session.getTransaction().commit();
        session.close();
        return true;
    }
    /*// Método para agregar un autor
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
    }*/
}
