package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;

import org.example.bibliotecafx.entities.Socios;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SociosImpl implements SociosDAO {

    @Override
    public void agregarSocio(Socios socio) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(socio);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarSocio(Socios socio) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(socio);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarSocio(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Socios socio = session.get(Socios.class, id);
            if (socio != null) {
                session.remove(socio);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Socios obtenerSocio(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Socios.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Socios> obtenerTodosLosSocios() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Socios", Socios.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Socios> findByNombre(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Socios WHERE nombre LIKE :nombre";
            Query<Socios> query = session.createQuery(hql, Socios.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Socios> findByTelefono(int telefono) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Socios WHERE telefono = :telefono";
            Query<Socios> query = session.createQuery(hql, Socios.class);
            query.setParameter("telefono", telefono);  // Usamos el parámetro de tipo int
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public List<Socios> findAll() {
        return obtenerTodosLosSocios();
    }
}
