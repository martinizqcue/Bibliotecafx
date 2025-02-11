package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.Prestamos;
import org.example.bibliotecafx.entities.Libros;
import org.example.bibliotecafx.entities.Socios;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PrestamosImpl implements PrestamosDAO {


    public List<Libros> getAllLibros() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Libros", Libros.class).list();
        }
    }


    public List<Socios> getAllSocios() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Socios", Socios.class).list();
        }
    }

    @Override
    public List<Prestamos> getAllPrestamos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Prestamos", Prestamos.class).list();
        }
    }

    @Override
    public List<Prestamos> getHistorialPrestamosPorSocio(Socios socio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Prestamos WHERE socio = :socio", Prestamos.class)
                    .setParameter("socio", socio)
                    .list();
        }
    }

    @Override
    public void registrarPrestamo(Prestamos prestamo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(prestamo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
