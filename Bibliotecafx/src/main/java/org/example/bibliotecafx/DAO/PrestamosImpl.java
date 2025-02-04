package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Prestamos;
import org.example.bibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PrestamosImpl implements PrestamosDAO {

    // Método para registrar un préstamo de libro
    @Override
    public void registrarPrestamo(Prestamos prestamo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(prestamo);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para listar los libros prestados actualmente
    @Override
    public List<Prestamos> listarLibrosPrestados() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Prestamos WHERE fechaDevolucion IS NULL", Prestamos.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para listar el historial de préstamos de un socio
    @Override
    public List<Prestamos> listarHistorialPrestamosSocio(Integer idSocio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Prestamos WHERE socio.id = :idSocio", Prestamos.class)
                    .setParameter("idSocio", idSocio)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
