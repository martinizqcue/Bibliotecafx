package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.Autores;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AutoresImpl implements AutoresDAO {

    @Override
    public List<Autores> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Autores> autores = session.createQuery("from Autores", Autores.class).list();
            System.out.println("Tamaño de la lista de autores: " + autores.size());
            return autores;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Autores> findByNombre(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Autores where nombre like :nombre", Autores.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Autores save(Autores autor) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(autor);
            transaction.commit();
            return autor;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Autores update(Autores autor) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(autor);
            transaction.commit();
            return autor;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Autores autor) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Autores autorExistente = session.get(Autores.class, autor.getId());
            if (autorExistente != null) {
                session.delete(autorExistente);
                transaction.commit();
                return true;
            } else {
                System.out.println("El autor con ID " + autor.getId() + " no existe.");
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
